package org.gym.servet.config;

import org.gym.servet.service.WebVisitFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class filterConfig {

    @Autowired
    private WebVisitFilter webVisitFilter;

    @Bean
    public FilterRegistrationBean Filter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(webVisitFilter);
        registrationBean.addUrlPatterns("/**");
        return  registrationBean;
    }
}
