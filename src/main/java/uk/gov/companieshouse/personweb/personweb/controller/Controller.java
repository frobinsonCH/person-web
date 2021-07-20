package uk.gov.companieshouse.personweb.personweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String showTestPage() {
        return "test";
    }

    @GetMapping("/people")
    public String showAllPeople(Model model) {
        var people = personService.getPeople();

        model.addAttribute("people", people);
        return "people";
    }

    @GetMapping("/add-person")
    public String showAddPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "add-person";

    }

    @PostMapping("/add-person")
    public String addPerson(Model model, @ModelAttribute Person person) {
        person = personService.addPerson(person).getBody();
        model.addAttribute("person", person);
        return "redirect:/person/" + person.getId();
    }

    @GetMapping("/delete-person")
    public String showDeletePersonPage() {
        return "delete-person";

    }

    @PostMapping("/delete-person")
    public String deletePerson(@RequestParam String id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }

    @GetMapping("/person/{id}")
    public String showPersonPage(Model model, @PathVariable String id) {
        var person = personService.getPersonById(id).getBody();
        model.addAttribute("person", person);
        return "person";
    }

    @PostMapping("/replace-person")
    public String replacePerson(Model model, @ModelAttribute Person person) {
        model.addAttribute("person", person);
        personService.replacePersonById(person);
        return "redirect:/person/" + person.getId();
    }

    @GetMapping("/replace-person")
    public String showReplacePersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "replace-person";

    }
}
