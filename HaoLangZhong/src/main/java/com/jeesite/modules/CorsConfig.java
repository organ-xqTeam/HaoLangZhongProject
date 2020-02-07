package com.jeesite.modules;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
public class CorsConfig  {
	private CorsConfiguration buildConfig() {

		CorsConfiguration corsConfiguration = new CorsConfiguration();
		
		corsConfiguration.addAllowedHeader("*"); // 允许任何头

		corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）
		//corsConfiguration.addExposedHeader("Authorization");
		//corsConfiguration.addExposedHeader("*");
		//corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOrigin("*"); // 允许任何域名使用
		return corsConfiguration;
	}
	
	@Bean
	public CorsFilter corsFilter() {//export
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig()); // 4
		return new CorsFilter(source);
	}
}
