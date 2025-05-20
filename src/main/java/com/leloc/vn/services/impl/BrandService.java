package com.leloc.vn.services.impl;

import com.leloc.vn.dto.request.BrandRequestDTO;
import com.leloc.vn.dto.response.BrandResponseDTO;
import com.leloc.vn.dto.response.CategoryResponseDTO;
import com.leloc.vn.entity.Brand;
import com.leloc.vn.mapper.BrandMapper;
import com.leloc.vn.mapper.CategoryMapper;
import com.leloc.vn.repository.BrandRepository;
import com.leloc.vn.services.IBrandService;
import com.leloc.vn.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService implements IBrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<BrandResponseDTO> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(brandMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BrandResponseDTO getBrandById(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        return brandMapper.toResponseDTO(brand);
    }

    @Override
    public BrandResponseDTO createBrand(BrandRequestDTO brandRequestDTO) {
        Brand brand = brandMapper.toEntity(brandRequestDTO);
        brand = brandRepository.save(brand);
        System.out.println("Brand saved: " + brand);
        return brandMapper.toResponseDTO(brand);
    }

    @Override
    public BrandResponseDTO updateBrand(Long id, BrandRequestDTO brandRequestDTO) {
        Brand brand = brandRepository.findById(id).orElse(null);
        System.out.println("Brand saved: " + brand);
        if (brand != null) {
            brand.setBrandName(brandRequestDTO.getBrandName());
            brand = brandRepository.save(brand);

        }
        return brandMapper.toResponseDTO(brand);
    }
    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
