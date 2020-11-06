package axiom.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
 

@SpringBootApplication(scanBasePackages = { "axiom.app" })
@ComponentScan(basePackages = { "axiom.app" })
public class ApiApplication {
	private static ApplicationContext applicationContext;
 
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(ApiApplication.class, args);
		 
	} 
} 
 
