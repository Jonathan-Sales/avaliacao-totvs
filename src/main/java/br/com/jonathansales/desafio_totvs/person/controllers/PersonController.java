package br.com.jonathansales.desafio_totvs.person.controllers;

import br.com.jonathansales.desafio_totvs.address.models.Address;
import br.com.jonathansales.desafio_totvs.dependent.models.Dependent;
import br.com.jonathansales.desafio_totvs.errors.DefaultAppException;
import br.com.jonathansales.desafio_totvs.person.controllers.form.PersonForm;
import br.com.jonathansales.desafio_totvs.person.models.Person;
import br.com.jonathansales.desafio_totvs.phone.models.Phone;
import br.com.jonathansales.desafio_totvs.address.models.dtos.AddressDTO;
import br.com.jonathansales.desafio_totvs.dependent.models.dtos.DependentDTO;
import br.com.jonathansales.desafio_totvs.person.models.dtos.PersonDTO;
import br.com.jonathansales.desafio_totvs.phone.models.dtos.PhoneDTO;
import br.com.jonathansales.desafio_totvs.address.respositories.AddressRepository;
import br.com.jonathansales.desafio_totvs.dependent.repositories.DependentRepository;
import br.com.jonathansales.desafio_totvs.person.repositories.PersonRepository;
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
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private DependentRepository dependentRepository;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll() {
        List<Person> people = personRepository.findAll();

        List<PersonDTO> personDTOs = people
                .stream()
                .map(person -> {
                    PersonDTO personDTO = new PersonDTO(person);

                    List<Address> addresses = addressRepository.findByPersonId(person.getId());
                    List<AddressDTO> addressesDTOs = addresses.stream().map(AddressDTO::new).collect(Collectors.toList());
                    personDTO.setAddresses(addressesDTOs);

                    List<Phone> phones = phoneRepository.findByPersonId(person.getId());
                    List<PhoneDTO> phonesDTOs = phones.stream().map(PhoneDTO::new).collect(Collectors.toList());
                    personDTO.setPhones(phonesDTOs);

                    List<Dependent> dependents = dependentRepository.findByPersonId(person.getId());
                    List<DependentDTO> dependentsDTOs = dependents.stream().map(DependentDTO::new).collect(Collectors.toList());
                    personDTO.setDependents(dependentsDTOs);

                    return personDTO;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(personDTOs);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDTO> getOne(@PathVariable Long personId) {
        Person person = personRepository.findById(personId);
        if (Objects.isNull(person)) {
            throw new DefaultAppException(404, "There is no person for id: " + personId);
        }

        person.setAddresses(addressRepository.findByPersonId(person.getId()));
        person.setPhones(phoneRepository.findByPersonId(person.getId()));
        person.setDependents(dependentRepository.findByPersonId(person.getId()));

        return ResponseEntity.ok().body(new PersonDTO(person));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@RequestBody @Valid PersonForm personForm, UriComponentsBuilder uriBuilder) {
        Person person = personForm.toPerson();

        Person isPersonRegisteredWithSameCpfCnpj = personRepository.findByCpfCnpj(person.getCpfCnpj());
        if (isPersonRegisteredWithSameCpfCnpj != null) {
            throw new DefaultAppException(409, "This CPF/CNPJ is already registered");
        }

        PersonDTO createdPersonDTO = new PersonDTO(personRepository.save(person));

        if (Objects.nonNull(person.getAddresses())) {
            for (Address address : person.getAddresses()) {
                address.setPersonId(createdPersonDTO.getId());
            }
            List<Address> createdAddresses = addressRepository.saveAll(person.getAddresses());
            List<AddressDTO> createdAddressesDTO = createdAddresses.stream().map(AddressDTO::new).collect(Collectors.toList());
            createdPersonDTO.setAddresses(createdAddressesDTO);
        }

        if (Objects.nonNull(person.getPhones())) {
            for (Phone phone : person.getPhones()) {
                phone.setPersonId(createdPersonDTO.getId());
            }
            List<Phone> createdPhones = phoneRepository.saveAll(person.getPhones());
            List<PhoneDTO> createdPhonesDTO = createdPhones.stream().map(PhoneDTO::new).collect(Collectors.toList());
            createdPersonDTO.setPhones(createdPhonesDTO);
        }

        if (Objects.nonNull(person.getDependents())) {
            for (Dependent dependent : person.getDependents()) {
                dependent.setPersonId(createdPersonDTO.getId());
            }
            List<Dependent> createdDependents = dependentRepository.saveAll(person.getDependents());
            List<DependentDTO> createdDependentsDTO = createdDependents.stream().map(DependentDTO::new).collect(Collectors.toList());
            createdPersonDTO.setDependents(createdDependentsDTO);
        }

        URI uri = uriBuilder
                .path("/person/{id}")
                .buildAndExpand(createdPersonDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(createdPersonDTO);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long personId, @RequestBody @Valid PersonForm personForm) {
        Person updatedPerson = personRepository.update(personId, personForm.toPerson());
        if (Objects.isNull(updatedPerson)) {
            throw new DefaultAppException(404, "There is no person for id: " + personId);
        }

        return ResponseEntity.ok().body(new PersonDTO(updatedPerson));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<?> delete(@PathVariable Long personId) {
        Person person = personRepository.findById(personId);
        if (Objects.isNull(person)) {
            throw new DefaultAppException(404, "There is no person for id: " + personId);
        }

        addressRepository.deleteByPersonId(personId);
        phoneRepository.deleteByPersonId(personId);
        dependentRepository.deleteByPersonId(personId);
        personRepository.delete(personId);

        return ResponseEntity.ok().build();
    }
}

