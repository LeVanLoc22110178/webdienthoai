package com.leloc.vn.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private String secretKey = "secretKey"; // Chú ý: bạn nên sử dụng một key mạnh và bảo mật hơn trong thực tế

    // Getter cho secretKey để JwtAuthenticationFilter có thể truy cập
    public String getSecretKey() {
        return secretKey;
    }

    // Tạo token JWT bổ sung thêm userId và email
    public String generateToken(String username, String role, Long userId, String email) {
        return Jwts.builder()
                .setSubject(username) // Thêm thông tin người dùng
                .claim("role", role)  // Thêm role vào token
                .claim("userId", userId) // Thêm userId vào token
                .claim("email", email) // Thêm email vào token
                .setIssuedAt(new Date()) // Thời gian cấp token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Hết hạn sau 1 giờ
                .signWith(SignatureAlgorithm.HS256, secretKey) // Ký bằng secretKey
                .compact();  // Trả về token
    }

    // Phương thức cũ để tương thích ngược nếu cần
    public String generateToken(String username, String role) {
        return generateToken(username, role, null, null);
    }

    // Lấy thông tin username từ token
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Lấy thông tin userId từ token
    public Long extractUserId(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Long.class);
    }

    // Lấy thông tin email từ token
    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
    }

    // Lấy tất cả claims từ token
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // Kiểm tra token có hết hạn không
    public boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private Date extractExpirationDate(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    // Kiểm tra token có hợp lệ không
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}