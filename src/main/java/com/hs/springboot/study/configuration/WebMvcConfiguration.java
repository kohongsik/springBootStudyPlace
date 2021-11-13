package com.hs.springboot.study.configuration;

import com.hs.springboot.study.interceptor.LoggerInterceptor;
import com.hs.springboot.study.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoggerInterceptor());
        // registry.addInterceptor(new TestInterceptor());
    }
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry
                .addMapping("/**")
                .allowedOrigins("http://localhost:9090")
                .allowedMethods("GET", "POST")
                .maxAge(3000);


    }

}
