package de.lokal.programmierung.notizen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*NotizenApplication.java
 *The main entry point that bootstraps 
 *the Spring Boot application and launches 
 *the embedded Tomcat server.
*/

@SpringBootApplication
public class NotizenApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotizenApplication.class, args);
	}

}
