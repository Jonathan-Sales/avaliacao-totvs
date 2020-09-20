package br.com.jonathansales.desafio_totvs.person.controllers;

import br.com.jonathansales.desafio_totvs.errors.DefaultAppException;
import br.com.jonathansales.desafio_totvs.person.models.Person;
import br.com.jonathansales.desafio_totvs.person.repositories.PersonRepository;
import br.com.jonathansales.desafio_totvs.phone.controllers.form.PhoneForm;
import br.com.jonathansales.desafio_totvs.phone.models.Phone;
import br.com.jonathansales.desafio_totvs.phone.models.dtos.PhoneDTO;
import br.com.jonathansales.desafio_totvs.phone.repositories.PhoneRepository;
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
@RequestMapping("/person/{personId}/phone")
public class PersonPhoneController {

    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<PhoneDTO>> getAll(@PathVariable Long personId) {
        Person person = personRepository.findById(personId);
        if (Objects.isNull(person)) {
            throw new DefaultAppException(404, "There is no person for id: " + personId);
        }

        List<Phone> phones = phoneRepository.findByPersonId(personId);

        List<PhoneDTO> phonesDTOs = phones.stream().map(PhoneDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(phonesDTOs);
    }

    @PostMapping
    public ResponseEntity<PhoneDTO> create(@PathVariable Long personId, @RequestBody @Valid PhoneForm phoneForm, UriComponentsBuilder uriBuilder) {
        Person person = personRepository.findById(personId);
        if (Objects.isNull(person)) {
            throw new DefaultAppException(404, "There is no person for id: " + personId);
        }

        Phone phone = phoneForm.toPhone();
        phone.setPersonId(personId);

        Phone createdPhone = phoneRepository.save(phone);

        URI uri = uriBuilder
                .path("/person/{personId}/phone/{id}")
                .buildAndExpand(personId, createdPhone.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new PhoneDTO(createdPhone));
    }

}
