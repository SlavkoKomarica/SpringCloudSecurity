package rs.slavko.examples.springcloud.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by slavko.komarica on 11/2/2016.
 */
@RestController
public class GreetingController {

    private Logger logger = LoggerFactory.getLogger(GreetingController.class);

//    @Autowired
//    private OAuth2RestTemplate oAuth2RestTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "/greeting")
    public String greeting(@RequestParam(value = "cheatCode", required = false) String cheadCode) {
        logger.info("greeting endpoint invoked!");
        return "Hello"/*+getAuthenticatedUser()*/+"from Secure microservice!";
    }

//    public String getAuthenticatedUser() {
//        return oAuth2RestTemplate.getForObject("http://localhost:8181/uaa/me", String.class);
//    }
}
