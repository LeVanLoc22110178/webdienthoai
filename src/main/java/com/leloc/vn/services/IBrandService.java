package com.leloc.vn.services;

import com.leloc.vn.dto.request.BrandRequestDTO;
import com.leloc.vn.dto.response.BrandResponseDTO;
import com.leloc.vn.dto.response.CategoryResponseDTO;

import java.util.List;

public interface IBrandService {
   public List<BrandResponseDTO> getAllBrands();
   public BrandResponseDTO getBrandById(Long id);

   public BrandResponseDTO createBrand(BrandRequestDTO brandRequestDTO);
   public BrandResponseDTO updateBrand(Long id, BrandRequestDTO brandRequestDTO);
   public void deleteBrand(Long id);

}
