package br.com.jonathansales.desafio_totvs.person.controllers;

import br.com.jonathansales.desafio_totvs.address.controllers.form.AddressForm;
import br.com.jonathansales.desafio_totvs.address.models.Address;
import br.com.jonathansales.desafio_totvs.address.models.dtos.AddressDTO;
import br.com.jonathansales.desafio_totvs.address.respositories.AddressRepository;
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
@RequestMapping("/person/{personId}/address")
public class PersonAddressController {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAll(@PathVariable Long personId) {
        Person person = personRepository.findById(personId);
        if (Objects.isNull(person)) {
            throw new DefaultAppException(404, "There is no person for id: " + personId);
        }

        List<Address> addresses = addressRepository.findByPersonId(personId);

        List<AddressDTO> addressesDTOs = addresses.stream().map(AddressDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(addressesDTOs);
    }

    @PostMapping
    public ResponseEntity<AddressDTO> create(@PathVariable Long personId, @RequestBody @Valid AddressForm addressForm, UriComponentsBuilder uriBuilder) {
        Person person = personRepository.findById(personId);
        if (Objects.isNull(person)) {
            throw new DefaultAppException(404, "There is no person for id: " + personId);
        }

        Address address = addressForm.toAddress();
        address.setPersonId(personId);

        Address createdAddress = addressRepository.save(address);

        URI uri = uriBuilder
                .path("/person/{personId}/address/{id}")
                .buildAndExpand(personId, createdAddress.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new AddressDTO(createdAddress));
    }


}
