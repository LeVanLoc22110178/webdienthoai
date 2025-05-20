package com.leloc.vn.services;


import com.leloc.vn.dto.request.CategoryRequestDTO;
import com.leloc.vn.dto.response.CategoryResponseDTO;
import com.leloc.vn.entity.Category;

import java.util.List;

public interface ICategoryService {
    public List<CategoryResponseDTO> getAllCategories();
    public CategoryResponseDTO getCategoryById(Long id);

    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO);
    public void deleteCategory(Long id);
}
