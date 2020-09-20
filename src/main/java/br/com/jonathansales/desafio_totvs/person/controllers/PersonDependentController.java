package br.com.jonathansales.desafio_totvs.person.controllers;

import br.com.jonathansales.desafio_totvs.dependent.controllers.form.DependentForm;
import br.com.jonathansales.desafio_totvs.dependent.models.Dependent;
import br.com.jonathansales.desafio_totvs.dependent.models.dtos.DependentDTO;
import br.com.jonathansales.desafio_totvs.dependent.repositories.DependentRepository;
import br.com.jonathansales.desafio_totvs.errors.DefaultAppException;
import br.com.jonathansales.desafio_totvs.person.models.Person;
import br.com.jonathansales.desafio_totvs.person.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person/{personId}/dependent")
public class PersonDependentController {

    @Autowired
    private DependentRepository dependentRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<DependentDTO>> getAll(@PathVariable Long personId) {
        Person person = personRepository.findById(personId);
        if (Objects.isNull(person)) {
            throw new DefaultAppException(404, "There is no person for id: " + personId);
        }

        List<Dependent> dependents = dependentRepository.findByPersonId(personId);

        List<DependentDTO> dependentsDTOs = dependents.stream().map(DependentDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(dependentsDTOs);
    }

    @PostMapping
    public ResponseEntity<DependentDTO> create(@PathVariable Long personId, @RequestBody @Valid DependentForm dependentForm, UriComponentsBuilder uriBuilder) {
        Person person = personRepository.findById(personId);
        if (Objects.isNull(person)) {
            throw new DefaultAppException(404, "There is no person for id: " + personId);
        }

        Dependent dependent = dependentForm.toDependent();
        dependent.setPersonId(personId);

        Dependent createdDependent = dependentRepository.save(dependent);

        URI uri = uriBuilder
                .path("/person/{personId}/dependent/{id}")
                .buildAndExpand(personId, createdDependent.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DependentDTO(createdDependent));
    }



}
