package com.leloc.vn.services.impl;

import com.leloc.vn.dto.request.CategoryRequestDTO;
import com.leloc.vn.dto.response.CategoryResponseDTO;
import com.leloc.vn.entity.Category;
import com.leloc.vn.mapper.CategoryMapper;
import com.leloc.vn.repository.CategoryRepository;
import com.leloc.vn.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;



    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return categoryMapper.toResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryMapper.toEntity(categoryRequestDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setCategoryName(categoryRequestDTO.getCategoryName());
            category = categoryRepository.save(category);
        }
        return categoryMapper.toResponseDTO(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
