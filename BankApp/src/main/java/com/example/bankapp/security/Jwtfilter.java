package com.example.bankapp.security;

import com.example.bankapp.entity.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.StringUtils.hasText;

@Component
public class Jwtfilter extends GenericFilterBean { // начинаем с него
    private final UserDetailsService userDetailsService;
    private final Jwtprovider jwtprovider;

    public Jwtfilter(UserDetailsService userDetailsService, Jwtprovider jwtprovider) {
        this.userDetailsService = userDetailsService;
        this.jwtprovider = jwtprovider;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null) {
            String email = jwtprovider.extractUsername(token); // достаем емейл - логин в нашем случае
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(email);  // CustomUserDetails хранилище юзера
            if (jwtprovider.isTokenValid(token, customUserDetails)) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails,
                        null, customUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION); // достаем из хидера запроса токен
        if (hasText(bearer) && bearer.startsWith("Bearer ")) { // строка начинается с Bearer , этим кодом мы ее обрезаем, получая только токен
            return bearer.substring(7);
        }
        return null;
    }
}
