package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        // file:///C:/greenworkspace/springbootlab/upload
        registry.addResourceHandler("/upload/**") // jsp 페이지에서 이 주소 패턴이 나오면 발동한다.
                .addResourceLocations("file:///" + uploadFolder)
                .setCachePeriod(60 * 10 * 6)// 1시간동안 캐싱을할거고
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
        // 얘가 발동한다.
    }

}
