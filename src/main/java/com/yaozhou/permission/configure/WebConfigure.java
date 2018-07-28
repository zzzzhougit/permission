package com.yaozhou.permission.configure;

import com.yaozhou.permission.filters.impl.NeedLoginInterceptor;
import com.yaozhou.permission.filters.impl.ParamsDecodeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

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

    /**
     * 参数解码
     * @return
     */
    @Bean
    public FilterRegistrationBean paramsDecodeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new ParamsDecodeFilter());
        registrationBean.addUrlPatterns("/");
        registrationBean.setName(ParamsDecodeFilter.class.getSimpleName());
        registrationBean.setOrder(1);

        return registrationBean;
    }

    /*@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer Container) {
                container;
            }
        };
    }*/

}
