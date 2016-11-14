package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by slavko.komarica on 11/14/2016.
 */
@RestController
public class UserController {
    @RequestMapping("/me")
    public String home(Principal user) {
        return "Hello " + user == null ? "null" : user.getName();
    }
}
