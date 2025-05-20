package com.leloc.vn.controllers.api;


import com.leloc.vn.dto.request.CategoryRequestDTO;
import com.leloc.vn.dto.response.CategoryResponseDTO;
import com.leloc.vn.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class CategoryControllersApi {

    @Autowired
    private ICategoryService categoryService;


    @GetMapping("/api/v1/categories")
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }



    @GetMapping("/api/v1/categories/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/api/v1/create-categories")
    public CategoryResponseDTO createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.createCategory(categoryRequestDTO);
    }

    @PutMapping("/api/v1/update-categories/{id}")
    public CategoryResponseDTO updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.updateCategory(id, categoryRequestDTO);
    }
    @DeleteMapping("/api/v1/delete-categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
