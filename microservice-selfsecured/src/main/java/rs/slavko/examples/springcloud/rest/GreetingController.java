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

    @RequestMapping(method = RequestMethod.GET, value = "/greeting")
    public String greeting() {
        logger.info("greeting endpoint invoked!");
        return "Hello from microservice-self-secured!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/greeting-secure")
    public String greetingSecure() {
        logger.info("greeting-secure endpoint invoked!");
        return "Hello {secure} from microservice-self-secured!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/greeting-secure-user")
    public String greetingSecureUser() {
        logger.info("greeting-secure-user endpoint invoked!");
        return "Hello {secure-user} from microservice-self-secured!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/greeting-secure-admin")
    public String greetingSecureAdmin() {
        logger.info("greeting-secure-admin endpoint invoked!");
        return "Hello {secure-admin} from microservice-self-secured!";
    }
}
