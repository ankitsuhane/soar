package com.soar.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * This defines the scope of the Spring Context.
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.soar"})
@PropertySource("classpath:application.properties")
public class AppConfig {

}