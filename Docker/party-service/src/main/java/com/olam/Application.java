package com.olam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.DispatcherServlet;

import com.olam.score.party.util.PartyUtil;

@SpringBootApplication
@EnableJpaRepositories
//@Configuration
@EnableDiscoveryClient
//@ComponentScan(basePackages = { "com.olam.score.party.util" }, basePackageClasses =PartyUtil.class)
public class Application {

	
	public static void main(String[] args) {
		
		//SpringApplication.run(Application.class, args);
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
        DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/*@Bean
	public PartyUtil getPartyUtil() {
		return new PartyUtil();
	}*/

	
	

}
