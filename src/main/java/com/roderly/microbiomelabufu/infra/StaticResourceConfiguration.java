package com.roderly.microbiomelabufu.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    @Autowired
    private FileStorageProperties fileStorageLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Adiciona o caminho do diretório como um local de recursos estáticos
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + fileStorageLocation + "/");
    }
}