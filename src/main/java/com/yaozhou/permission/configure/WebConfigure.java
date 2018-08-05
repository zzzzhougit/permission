package com.yaozhou.permission.configure;

import com.yaozhou.permission.filters.impl.NeedLoginInterceptor;
import com.yaozhou.permission.filters.impl.ParamsDecodeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * @author Yao.Zhou
 * @since 2018/7/18 0:16
 */
@Configuration
public class WebConfigure implements WebMvcConfigurer {

    /**
     * 设置默认首页
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/admin");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }


    /**
     * 添加拦截器, 默认全部拦截, 只有标有注解时才做逻辑验证
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NeedLoginInterceptor()).addPathPatterns("/**");
    }

    /**
     * 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setFilter(paramsDecodeFilter());
        registrationBean.setName(ParamsDecodeFilter.class.getSimpleName());

        return registrationBean;
    }

    /**
     * 参数解码过滤器
     * @return
     */
    @Bean
    public Filter paramsDecodeFilter() {
        return new ParamsDecodeFilter();
    }

}
