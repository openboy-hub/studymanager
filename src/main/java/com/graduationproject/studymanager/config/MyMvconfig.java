package com.graduationproject.studymanager.config;




import com.graduationproject.studymanager.component.DateFormatterConvert;
import com.graduationproject.studymanager.component.LoginHandlerInterceptor;
import com.graduationproject.studymanager.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class MyMvconfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter(){
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/main.html").setViewName("main");
                registry.addViewController("/").setViewName("login");
            }
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
               registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/","/register","/login","/css/**","/fonts/**","/images/**", "/js/**","/error/**");
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                /*String path = "D:\\intellj2017\\studymanager\\src\\main\\resources\\static\\images\\";
                registry.addResourceHandler("/images/**").addResourceLocations("file:"+path);*/
            }

            @Override
            public void addFormatters(FormatterRegistry registry) {
                //registry.addConverter(new DateFormatterConvert());
            }
        };
    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
