package com.leloc.vn.security;

import com.leloc.vn.config.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // Định nghĩa PasswordEncoder để mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // Cấu hình bảo mật cho ứng dụng sử dụng SecurityFilterChain với cú pháp lambda mới
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Sử dụng cấu hình lambda thay vì phương thức cũ
               .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF protection

                // Cấu hình authorization requests
                .authorizeHttpRequests(auth -> auth

                     .requestMatchers("/**").permitAll() // Cho phép truy cập vào tất cả các endpoint
                     .anyRequest().authenticated() // Yêu cầu xác thực cho các yêu cầu khác
                )


                // Tắt form login mặc định
                .formLogin(AbstractHttpConfigurer::disable)

                // Tắt HTTP Basic Authentication
                .httpBasic(AbstractHttpConfigurer::disable)

                // Cấu hình session management
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sử dụng Stateless session
                )

                // Thêm JWT Filter vào chuỗi filter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }
}