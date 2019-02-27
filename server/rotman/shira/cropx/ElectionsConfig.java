package rotman.shira.cropx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration class ElectionsConfig
{
    public static class AppMVCConfigurer implements WebMvcConfigurer
    {
        @Override public void addCorsMappings(CorsRegistry registry)
        { registry.addMapping("/**").allowedOrigins("http://localhost:4200"); }
    }

    @Bean public WebMvcConfigurer corsConfigurer()
    { return new AppMVCConfigurer(); }
}
