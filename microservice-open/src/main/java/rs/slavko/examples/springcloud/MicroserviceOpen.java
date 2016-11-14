package rs.slavko.examples.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceOpen {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceOpen.class, args);
	}

}
