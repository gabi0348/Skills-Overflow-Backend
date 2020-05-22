package com.internshipProject.SkillsOverflowBackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    public MvcConfig() {
        super();
    }

    // API

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        super.addViewControllers(registry);

        registry.addViewController("/anonymous.html");

        registry.addViewController("/login.html");
        registry.addViewController("/homepage.html");
        registry.addViewController("/admin/adminpage.html");
        registry.addViewController("/accessDenied");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("D:\\Proiect DB- skills overflow\\Skills-Overflow-FrontEnd\\skills-overflow\\src");
        registry.addResourceHandler("/*.tsx").addResourceLocations("/WEB-INF/view/react/build/");
        registry.addResourceHandler("/*.json").addResourceLocations("/WEB-INF/view/react/build/");
        registry.addResourceHandler("/*.ico").addResourceLocations("/WEB-INF/view/react/build/");
        registry.addResourceHandler("/index.html").addResourceLocations("D:\\Proiect DB- skills overflow\\Skills-Overflow-FrontEnd\\skills-overflow\\public\\index.html");
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");

        return bean;
    }
}
