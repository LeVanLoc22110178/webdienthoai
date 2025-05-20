package com.leloc.vn.services.impl;

import com.leloc.vn.dto.request.ProductRequestDTO;
import com.leloc.vn.dto.response.ApiPageResponseDTO;
import com.leloc.vn.dto.response.ProductResponseDTO;
import com.leloc.vn.entity.Brand;
import com.leloc.vn.entity.Category;
import com.leloc.vn.entity.Product;
import com.leloc.vn.mapper.ProductMapper;
import com.leloc.vn.repository.BrandRepository;
import com.leloc.vn.repository.CategoryRepository;
import com.leloc.vn.repository.ProductRepository;
import com.leloc.vn.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public BrandRepository brandRepository;

    @Autowired
    public ProductMapper productMapper;

    @Override
    public ApiPageResponseDTO<ProductResponseDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponseDTO> productResponseDTOs = productPage.getContent().stream()
                .map(productMapper::toResponseDTO)
                .collect(Collectors.toList());

        return new ApiPageResponseDTO<>(productResponseDTOs, productPage.getTotalElements(), productPage.getTotalPages(), productPage.getNumber(), productPage.getSize());
    }


    @Override
    public ProductResponseDTO getProductById(Long id) {
        return productMapper.toResponseDTO(productRepository.findById(id).orElse(null));
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        product = productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }


    @Override
    public ApiPageResponseDTO<ProductResponseDTO> getProductsByCategoryId(Long categoryId, int page, int size) {
        // Tạo đối tượng Pageable
        Pageable pageable = PageRequest.of(page, size);

        // Lấy trang dữ liệu sản phẩm theo categoryId và phân trang
        Page<Product> productPage = productRepository.findByCategory_CategoryId(categoryId, pageable);

        // Chuyển trang dữ liệu thành danh sách DTO
        List<ProductResponseDTO> productResponseDTOs = productPage.getContent().stream()
                .map(productMapper::toResponseDTO)
                .collect(Collectors.toList());

        // Trả về ApiPageResponseDTO chứa dữ liệu sản phẩm và thông tin phân trang
        return new ApiPageResponseDTO<>(productResponseDTOs, productPage.getTotalElements(), productPage.getTotalPages(), productPage.getNumber(), productPage.getSize());
    }




    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        // Lấy đối tượng product từ repository, nếu không tìm thấy trả về null
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            // Nếu không tìm thấy sản phẩm, trả về lỗi hoặc giá trị null
            return null; // Hoặc ném lỗi, ví dụ: throw new ProductNotFoundException("Product not found");
        } else {
            // Cập nhật các trường của đối tượng product đã tồn tại với dữ liệu mới
            product.setProductName(productRequestDTO.getProductName());
            product.setDescription(productRequestDTO.getDescription());
            product.setPrice(productRequestDTO.getPrice());
            product.setStockQuantity(productRequestDTO.getStockQuantity());
            product.setReleaseDate(productRequestDTO.getReleaseDate());
            product.setImageUrl(productRequestDTO.getImageUrl());
            product.setStatus(productRequestDTO.getStatus());

            // Cập nhật category nếu có thay đổi
            if (productRequestDTO.getCategoryId() != null) {
                // Lấy category từ cơ sở dữ liệu thay vì tạo mới
                Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElse(null);
                if (category != null) {
                    product.setCategory(category);
                }
            }

            // Cập nhật brand nếu có thay đổi
            if (productRequestDTO.getBrandId() != null) {
                // Lấy brand từ cơ sở dữ liệu thay vì tạo mới
                Brand brand = brandRepository.findById(productRequestDTO.getBrandId()).orElse(null);
                if (brand != null) {
                    product.setBrand(brand);
                }
            }

            // Lưu lại đối tượng product đã được cập nhật
            product = productRepository.save(product); // Hibernate sẽ thực hiện update ở đây

            // Trả về ProductResponseDTO đã được ánh xạ từ product
            return productMapper.toResponseDTO(product);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        // Xóa sản phẩm bằng cách sử dụng repository
        productRepository.deleteById(id);
    }
}
