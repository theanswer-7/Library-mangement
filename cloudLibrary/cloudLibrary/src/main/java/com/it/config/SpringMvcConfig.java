package com.it.config;


import com.it.interceptor.ResouceInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan("com.it.controller")
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new ResouceInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/css/**","/js/**","/img/**");
    }
//    开启静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //    开启静态资源，css,js,img


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.jsp("/admin/",".jsp");
    }



}
