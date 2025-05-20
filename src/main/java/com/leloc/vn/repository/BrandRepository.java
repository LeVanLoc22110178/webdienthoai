package com.leloc.vn.repository;

import com.leloc.vn.entity.Brand;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    // Define any custom query methods here if needed

}
