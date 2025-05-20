package com.leloc.vn.mapper;


import com.leloc.vn.dto.request.CategoryRequestDTO;
import com.leloc.vn.dto.response.CategoryResponseDTO;
import com.leloc.vn.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryResponseDTO toResponseDTO(Category category);

    Category toEntity(CategoryRequestDTO categoryRequestDTO);

}
