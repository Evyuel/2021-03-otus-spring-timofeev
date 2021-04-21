package ru.otus.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.otus.spring")
@PropertySource("classpath:application.properties")
public class SpringConfig {

}
