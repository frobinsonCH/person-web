package uk.gov.companieshouse.personweb.personweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.gov.companieshouse.personweb.personweb.model.Person;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {
    private final RestTemplate restTemplate;
    @Autowired
    public PersonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Person> getPeople(){
        HttpEntity<String> entity = new HttpEntity<>("");
        Person[] people = restTemplate.getForEntity("Http://localhost:8080/people",Person[].class,entity).getBody();
        return Arrays.asList(people);
    }

    public ResponseEntity<Person> addPerson(Person person){
        HttpEntity<Person> entity = new HttpEntity<>(person);
        return restTemplate.postForEntity("Http://localhost:8080/add-person",entity,Person.class);
    }
    public ResponseEntity<HttpStatus> deletePerson(String id){
        HttpEntity<String> entity = new HttpEntity<>(id);
        return restTemplate.exchange("Http://localhost:8080/delete-person", HttpMethod.DELETE, entity, HttpStatus.class);
    }
}
