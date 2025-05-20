package com.leloc.vn.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Class tiện ích để lấy thông tin xác thực từ SecurityContext
 */
@Component
public class JwtAuthenticationContext {

    /**
     * Lấy userId từ SecurityContext
     * Phương thức này hoạt động vì JwtAuthenticationFilter đã gán userId vào WebAuthenticationDetailsSource
     */
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getDetails() instanceof JwtUserDetails) {
            return ((JwtUserDetails) authentication.getDetails()).getUserId();
        }
        return null;
    }

    /**
     * Lấy email từ SecurityContext
     */
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName(); // getName trả về username (email trong trường hợp này)
        }
        return null;
    }

    /**
     * Lấy role từ SecurityContext
     */
    public String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !authentication.getAuthorities().isEmpty()) {
            return authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        }
        return null;
    }

    /**
     * Class nội bộ để lưu thông tin chi tiết của user
     */
    public static class JwtUserDetails {
        private final Long userId;
        private final String email;

        public JwtUserDetails(Long userId, String email) {
            this.userId = userId;
            this.email = email;
        }

        public Long getUserId() {
            return userId;
        }

        public String getEmail() {
            return email;
        }
    }
}