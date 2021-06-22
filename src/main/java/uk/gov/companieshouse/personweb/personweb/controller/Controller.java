package uk.gov.companieshouse.personweb.personweb.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/test")
    public String showTestPage(){
        return "test";
    }

}
