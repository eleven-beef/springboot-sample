package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.filter.LoggingFilter;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * ValidationメッセージをUTF-8で設定できるようにする
	 */
	@Override
	public Validator getValidator() {
		return validator();
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

	private MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// プロパティファイルの名前やディレクトリも変更可能
		messageSource.setBasename("classpath:/ValidationMessages");
		// UTF-8に設定
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public FilterRegistrationBean filter1() {
		FilterRegistrationBean been = new FilterRegistrationBean();
		been.setFilter(new LoggingFilter());
		been.setOrder(1);
		return been;
	}

	@Bean
	public FilterRegistrationBean filter2() {
		FilterRegistrationBean been = new FilterRegistrationBean();
		been.setFilter(new MDCInsertingServletFilter());
		been.setOrder(2);
		return been;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
