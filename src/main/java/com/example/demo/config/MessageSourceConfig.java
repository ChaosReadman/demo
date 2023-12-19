package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import lombok.val;

@Configuration
public class MessageSourceConfig {
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
        bundle.setBasenames("i18n/messages_ja");
        bundle.setDefaultEncoding("UTF-8");
        return bundle;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        // langパラメータでロケールを切り替える
        val interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang"); // ロケール切替に使うパラメータ名を指定する
        return interceptor;
    }

}