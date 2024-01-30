package id.bootcamp.generatepdf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PdfConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //Path Lokasi folder pdf
        String path = new FileSystemResource("").getFile().getAbsolutePath();
        path += "\\uploads\\pdf\\";

        //Tambah fungsi jika membuka localhost/images/...
        //nanti membuka file dari folder uploads
        registry.addResourceHandler("/pdf/**")
                .addResourceLocations("file:///" + path);

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
