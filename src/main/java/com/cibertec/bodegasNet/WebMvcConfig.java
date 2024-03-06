package com.cibertec.bodegasNet;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cibertec.bodegasNet.interceptors.AuthInterceptor;
import com.cibertec.bodegasNet.interceptors.MenuInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(new AuthInterceptor())
        .addPathPatterns("/auth/**", "/admin", "/dashboard") // Aplicar a todas las rutas
        .excludePathPatterns("/css/**", "/icons/**", "/images/**"); // Excluir
        registry.addInterceptor(new MenuInterceptor());
    }
}