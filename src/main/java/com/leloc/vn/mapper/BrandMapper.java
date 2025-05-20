package com.leloc.vn.mapper;

import com.leloc.vn.dto.request.BrandRequestDTO;
import com.leloc.vn.dto.response.BrandResponseDTO;
import com.leloc.vn.entity.Brand;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapper {
    Brand toEntity(BrandRequestDTO brandRequestDTO);
    BrandResponseDTO toResponseDTO(Brand brand);
}
