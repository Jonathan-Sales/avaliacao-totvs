package br.com.jonathansales.desafio_totvs.address.controllers;

import br.com.jonathansales.desafio_totvs.address.controllers.form.AddressForm;
import br.com.jonathansales.desafio_totvs.address.models.Address;
import br.com.jonathansales.desafio_totvs.address.models.dtos.AddressDTO;
import br.com.jonathansales.desafio_totvs.address.respositories.AddressRepository;
import br.com.jonathansales.desafio_totvs.errors.DefaultAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDTO> getOne(@PathVariable Long addressId) {
        Address address = addressRepository.findById(addressId);
        if (Objects.isNull(address)) {
            throw new DefaultAppException(404, "There is no address for id: " + addressId);
        }

        return ResponseEntity.ok().body(new AddressDTO(address));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDTO> update(@PathVariable Long addressId, @RequestBody @Valid AddressForm addressForm) {
        Address updatedAddress = addressRepository.update(addressId, addressForm.toAddress());
        if (Objects.isNull(updatedAddress)) {
            throw new DefaultAppException(404, "There is no address for id: " + addressId);
        }

        return ResponseEntity.ok(new AddressDTO(updatedAddress));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> delete(@PathVariable Long addressId) {
        Address address = addressRepository.findById(addressId);
        if (Objects.isNull(address)) {
            throw new DefaultAppException(404, "There is no address for id: " + addressId);
        }

        addressRepository.delete(addressId);

        return ResponseEntity.ok().build();
    }

}
