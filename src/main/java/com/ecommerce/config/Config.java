package com.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 18/09/2023
 * @Time: 16:08
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.ecommerce.dao")
@EnableJpaAuditing
public class Config {
}
