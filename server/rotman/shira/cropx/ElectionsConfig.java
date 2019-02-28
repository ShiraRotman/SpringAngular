package rotman.shira.cropx;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

@Configuration @EnableWebSecurity
class AppSecurityConfigurer extends WebSecurityConfigurerAdapter
{
    @Override protected void configure(HttpSecurity security) throws Exception
    { security.cors(); }

    @Bean public CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration config=new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("HEAD","GET","POST"));
        config.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }
}