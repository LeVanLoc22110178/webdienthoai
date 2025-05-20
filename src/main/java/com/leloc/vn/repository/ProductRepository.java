package com.leloc.vn.repository;

import com.leloc.vn.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory_CategoryId(Long categoryId, Pageable pageable);

}
