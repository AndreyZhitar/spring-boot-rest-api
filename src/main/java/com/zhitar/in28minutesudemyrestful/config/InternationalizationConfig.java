package com.zhitar.in28minutesudemyrestful.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class InternationalizationConfig {

//    @Bean
//    public LocaleResolver localeResolver() {
//        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
//        localeResolver.setDefaultLocale(Locale.US);
//        return localeResolver;
//    }

//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        return messageSource;
//    }
}
