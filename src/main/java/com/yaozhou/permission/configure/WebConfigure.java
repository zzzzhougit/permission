package com.yaozhou.permission.configure;

import com.yaozhou.permission.interceptors.impl.NeedLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Yao.Zhou
 * @since 2018/7/18 0:16
 */
@Configuration
public class WebConfigure implements WebMvcConfigurer {

    /**
     * 添加拦截器, 默认全部拦截, 只有标有注解时才做逻辑验证
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NeedLoginInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
