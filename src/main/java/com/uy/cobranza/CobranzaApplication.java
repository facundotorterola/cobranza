package com.uy.cobranza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CobranzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CobranzaApplication.class, args);
	}

}



