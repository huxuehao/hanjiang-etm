package com.hxh.etm.common.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * 描述：拦截器配置
 *
 * @author huxuehao
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final ObjectMapper objectMapper;

    public InterceptorConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter =
                new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /* 拦截所有的请求，通过请求映射到的方法上的注解进行判断是否需要权限验证 */
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/op-sql/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /* 静态资源处理器，将/ui/**请求指引到classpath:/ui/下 */
        registry.addResourceHandler("/ui/**").addResourceLocations("classpath:/ui/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /* 添加映射路径 */
        registry.addMapping("/**")
                /* 设置放行哪些原始域 SpringBoot2.4.4下低版本使用.allowedOrigins("*") */
                .allowedOrigins("*")
                /* 放行哪些请求方式 */
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // .allowedMethods("*") //或者放行全部
                /* 放行哪些原始请求头部信息 */
                .allowedHeaders("*");
    }
}
