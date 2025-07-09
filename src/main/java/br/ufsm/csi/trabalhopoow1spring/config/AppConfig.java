package br.ufsm.csi.trabalhopoow1spring.config;

import br.ufsm.csi.trabalhopoow1spring.security.AutorizadorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private AutorizadorInterceptor autorizadorInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autorizadorInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/logout", "/cadastro");
    }
}