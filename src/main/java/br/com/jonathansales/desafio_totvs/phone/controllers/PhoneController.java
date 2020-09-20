package br.com.jonathansales.desafio_totvs.phone.controllers;

import br.com.jonathansales.desafio_totvs.address.models.Address;
import br.com.jonathansales.desafio_totvs.errors.DefaultAppException;
import br.com.jonathansales.desafio_totvs.phone.controllers.form.PhoneForm;
import br.com.jonathansales.desafio_totvs.phone.models.Phone;
import br.com.jonathansales.desafio_totvs.phone.models.dtos.PhoneDTO;
import br.com.jonathansales.desafio_totvs.phone.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;

    @GetMapping("/{phoneId}")
    public ResponseEntity<PhoneDTO> getOne(@PathVariable Long phoneId) {
        Phone phone = phoneRepository.findById(phoneId);
        if (Objects.isNull(phone)) {
            throw new DefaultAppException(404, "There is no phone for id: " + phoneId);
        }

        return ResponseEntity.ok().body(new PhoneDTO(phone));
    }

    @PutMapping("/{phoneId}")
    public ResponseEntity<PhoneDTO> update(@PathVariable Long phoneId, @RequestBody @Valid PhoneForm phoneForm) {
        Phone updatedPhone = phoneRepository.update(phoneId, phoneForm.toPhone());
        if (Objects.isNull(updatedPhone)) {
            throw new DefaultAppException(404, "There is no phone for id: " + phoneId);
        }

        return ResponseEntity.ok().body(new PhoneDTO(updatedPhone));
    }

    @DeleteMapping("/{phoneId}")
    public ResponseEntity<?> delete(@PathVariable Long phoneId) {
        Phone phone = phoneRepository.findById(phoneId);
        if (Objects.isNull(phone)) {
            throw new DefaultAppException(404, "There is no phone for id: " + phoneId);
        }

        phoneRepository.delete(phoneId);

        return ResponseEntity.ok().build();
    }

}
