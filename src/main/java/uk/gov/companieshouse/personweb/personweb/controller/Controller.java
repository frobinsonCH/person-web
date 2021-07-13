package uk.gov.companieshouse.personweb.personweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uk.gov.companieshouse.personweb.personweb.service.PersonService;

@org.springframework.stereotype.Controller
public class Controller {
    private final PersonService personService;
    @Autowired
    public Controller(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/test")
    public String showTestPage(){
        return "test";
    }
    @GetMapping("/people")
    public String showAllPeople(Model model){
        var people = personService.getPeople();

        model.addAttribute("people",people);
        return "people";
    }

}
