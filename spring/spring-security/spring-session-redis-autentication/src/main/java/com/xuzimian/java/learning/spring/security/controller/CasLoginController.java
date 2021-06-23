package com.xuzimian.java.learning.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cas")
public class CasLoginController {

    @GetMapping("/login")
    public String hello(@RequestParam("service") String service) {
        System.out.println(service);
        return "redirect:/login";
    }
}
