package com.exemple.exemplul3;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class PersonController {
    @Autowired
    PersonRepository repository;
    @GetMapping("jpa/persoane")
    public List<Person> findAll() {
        return repository.findAll();
    }
    @GetMapping("jpa/persoane/{id}")
    public Person findById(@PathVariable int id) {
        Optional<Person> optionalPersoana = repository.findById(id);
        if(optionalPersoana.isPresent())
            return optionalPersoana.get();
        else
            throw new PersonNotFoundException("Persoana cu id-ul " + id + " nu a fost gasita");
    }
    @PostMapping("jpa/persoane")
    public void savePerson(@RequestBody Person person) {
        repository.save(person);
    }
    @DeleteMapping("jpa/persoane/{id}")
    public void deleteById(@PathVariable int id) {
        repository.deleteById(id);
    }
}
