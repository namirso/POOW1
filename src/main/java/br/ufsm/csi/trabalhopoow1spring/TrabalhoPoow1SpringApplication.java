package br.ufsm.csi.trabalhopoow1spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TrabalhoPoow1SpringApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(TrabalhoPoow1SpringApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TrabalhoPoow1SpringApplication.class, args);
    }

}
