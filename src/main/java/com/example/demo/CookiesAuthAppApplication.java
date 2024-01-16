package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.TestDI;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//(basePackages = {"com.example.entities", "com.example.repositories"})
@SpringBootApplication
@EnableJpaRepositories
public class CookiesAuthAppApplication implements CommandLineRunner {

	   @Autowired
	    private ApplicationContext appContext;
	   
	public static void main(String[] args) {
		SpringApplication.run(CookiesAuthAppApplication.class, args);
		
		
	}
	
	  @Override
	    public void run(String... args) throws Exception {

	        String[] beans = ((ListableBeanFactory) appContext).getBeanDefinitionNames();
	        Arrays.sort(beans);
	        for (String bean : beans) {
	            System.out.println(bean);
	        }

	    }

}
