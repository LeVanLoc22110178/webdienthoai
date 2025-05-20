package com.leloc.vn.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class ApiPageResponseDTO<T> {

    private List<T> data;
    private long totalItems;
    private int totalPages;
    private int currentPage;
    private int pageSize;

    // Constructors
    public ApiPageResponseDTO(List<T> data, long totalItems, int totalPages, int currentPage, int pageSize) {
        this.data = data;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    // Getters and Setters (Lombok generates them automatically with @Data)
}
