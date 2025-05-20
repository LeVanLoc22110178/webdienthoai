package com.leloc.vn.services;

import com.leloc.vn.dto.request.ProductRequestDTO;
import com.leloc.vn.dto.response.ApiPageResponseDTO;
import com.leloc.vn.dto.response.ProductResponseDTO;

import java.util.List;
import java.util.Map;

public interface IProductService {
    public ApiPageResponseDTO<ProductResponseDTO> getAllProducts(int page, int size);
    public ProductResponseDTO getProductById(Long id);
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    public ApiPageResponseDTO<ProductResponseDTO> getProductsByCategoryId(Long categoryId, int page, int size);
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);
    public void deleteProduct(Long id);
}
