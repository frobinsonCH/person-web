package uk.gov.companieshouse.personweb.personweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.gov.companieshouse.personweb.personweb.model.Person;
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
    @GetMapping("/add-person")
    public String showAddPersonPage(Model model){
        model.addAttribute("person",new Person());
        return "add-person";

    }
    @PostMapping("/add-person")
    public String addPerson(Model model, @ModelAttribute Person person){
       model.addAttribute("person",person);
       personService.addPerson(person);
        return "add-person";
    }
    @GetMapping("/delete-person")
    public String showDeletePersonPage(Model model){
        model.addAttribute("id","");
        return "delete-person";

    }
    @PostMapping("/delete-person")
    public String deletePerson(Model model, @RequestParam String id){
        model.addAttribute("id",id);
        personService.deletePerson(id);
        return "redirect:/people";
    }

}
