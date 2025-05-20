package com.leloc.vn.controllers.api;

import com.leloc.vn.dto.request.BrandRequestDTO;
import com.leloc.vn.dto.response.BrandResponseDTO;
import com.leloc.vn.mapper.BrandMapper;
import com.leloc.vn.services.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class BrandControllersApi {

    @Autowired
    private IBrandService brandService;


    @GetMapping("/api/v1/brands")
    public List<BrandResponseDTO> getAllBrands() {
        return brandService.getAllBrands();
    }
    @GetMapping("/api/v1/brands/{id}")
    public BrandResponseDTO getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    @PutMapping("/api/v1/update-brands/{id}")
    public BrandResponseDTO updateBrand(@PathVariable Long id,@RequestBody BrandRequestDTO brandRequestDTO) {
        return brandService.updateBrand(id, brandRequestDTO);
    }

    @PostMapping("/api/v1/create-brands")
    public BrandResponseDTO createBrand(@RequestBody BrandRequestDTO brandRequestDTO) {
        return brandService.createBrand(brandRequestDTO);
    }
    @DeleteMapping("/api/v1/delete-brands/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }
}
