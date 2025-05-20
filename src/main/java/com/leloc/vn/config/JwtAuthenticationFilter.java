package com.leloc.vn.config;

import com.leloc.vn.config.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Lấy JWT token từ request
            String jwt = getJwtFromRequest(request);

            // Kiểm tra token có tồn tại và hợp lệ không
            if (StringUtils.hasText(jwt) && !jwtUtil.isTokenExpired(jwt)) {
                // Lấy thông tin từ JWT
                Claims claims = jwtUtil.extractAllClaims(jwt);

                // Lấy username từ token (thường là email)
                String username = claims.getSubject();

                // Lấy ID người dùng và email từ token
                Long userId = claims.get("userId", Long.class);
                String email = claims.get("email", String.class);

                // Lấy role từ token
                String role = claims.get("role", String.class);

                // Tạo authorities từ role
                List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_" + role)
                );

                // Tạo chi tiết người dùng
                JwtAuthenticationContext.JwtUserDetails userDetails = new JwtAuthenticationContext.JwtUserDetails(userId, email);

                // Tạo authentication object
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, authorities);

                // Lưu thông tin user ID và email vào authentication details
                authentication.setDetails(userDetails);

                // Lưu authentication vào SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Thêm thông tin user ID và email vào request attributes để các controller có thể sử dụng
                request.setAttribute("userId", userId);
                request.setAttribute("email", email);
                request.setAttribute("role", role);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    // Helper method để lấy JWT token từ request
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}