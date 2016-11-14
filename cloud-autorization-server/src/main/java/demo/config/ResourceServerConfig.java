package demo.config;

import demo.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@Configuration
@EnableResourceServer
@RestController
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @RequestMapping("/me")
    public ResponseEntity me(Principal principal) {
        String user = null;
        if(principal != null) {
            user = principal.getName();
        }

        return Optional.ofNullable(user)
                .map(a -> new ResponseEntity<String>(a, HttpStatus.OK))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**", "/login").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    HttpSessionSecurityContextRepository contextRepository() {
        return new HttpSessionSecurityContextRepository();
    }
}
