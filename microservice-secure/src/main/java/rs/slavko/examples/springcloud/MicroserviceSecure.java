package rs.slavko.examples.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableResourceServer
public class MicroserviceSecure {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSecure.class, args);
	}

//	@Bean
//	public OAuth2RestTemplate oauth2RestTemplate(
//			OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
//		return new OAuth2RestTemplate(resource, context);
//	}
}
