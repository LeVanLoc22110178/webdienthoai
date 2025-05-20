package com.leloc.vn.controllers.api;


import com.leloc.vn.dto.request.ProductRequestDTO;
import com.leloc.vn.dto.response.ApiPageResponseDTO;
import com.leloc.vn.dto.response.ProductResponseDTO;
import com.leloc.vn.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductControllersApi {

    @Autowired
    private IProductService productService;

    @GetMapping("/api/v1/products")
    public ApiPageResponseDTO<ProductResponseDTO> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(page, size);
    }

    @GetMapping("/api/v1/products/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id) {

        return productService.getProductById(id);
    }

    @PostMapping("/api/v1/create-products")
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return productService.createProduct(productRequestDTO);
    }

    @GetMapping("/api/v1/products/category/{categoryId}")
    public ApiPageResponseDTO<ProductResponseDTO> getProductsByCategoryId(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getProductsByCategoryId(categoryId, page, size);
    }
    @PutMapping("/api/v1/update-products/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        return productService.updateProduct(id, productRequestDTO);
    }
    @DeleteMapping("/api/v1/delete-products/{id}")
    public Map<String, String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return Map.of("message", "Product deleted successfully");
    }
}
