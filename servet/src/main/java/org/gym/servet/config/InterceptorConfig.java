package org.gym.servet.config;

import org.gym.servet.utils.JwtInterceptor;
import org.gym.servet.utils.AdJwtInterceptor;
import org.gym.servet.service.WebVisitFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Register JwtInterceptor
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/addreserve", "/getreserve/{userID}", "/getreserve");
            //

        // Register AdjwtInterceptor
        registry.addInterceptor(AdJwtInterceptor())
                .addPathPatterns("/postuser", "/getreserve","/getreserve/{userID}", "/deleteuser/"
               ,"/pageuserreserve" ) ;
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    @Bean
    public AdJwtInterceptor AdJwtInterceptor() {
        return new AdJwtInterceptor();
    }

}