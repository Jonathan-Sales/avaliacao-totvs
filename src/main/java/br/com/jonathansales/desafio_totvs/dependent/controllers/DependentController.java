package br.com.jonathansales.desafio_totvs.dependent.controllers;

import br.com.jonathansales.desafio_totvs.dependent.controllers.form.DependentForm;
import br.com.jonathansales.desafio_totvs.dependent.models.Dependent;
import br.com.jonathansales.desafio_totvs.dependent.models.dtos.DependentDTO;
import br.com.jonathansales.desafio_totvs.dependent.repositories.DependentRepository;
import br.com.jonathansales.desafio_totvs.errors.DefaultAppException;
import br.com.jonathansales.desafio_totvs.person.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/dependent")
public class DependentController {

    @Autowired
    private DependentRepository dependentRepository;

    @GetMapping("/{dependentId}")
    public ResponseEntity<DependentDTO> getOne(@PathVariable Long dependentId) {
        Dependent dependent = dependentRepository.findById(dependentId);
        if (Objects.isNull(dependent)) {
            throw new DefaultAppException(404, "There is no dependent for id: " + dependentId);
        }

        return ResponseEntity.ok().body(new DependentDTO(dependent));
    }

    @PutMapping("/{dependentId}")
    public ResponseEntity<DependentDTO> update(@PathVariable Long dependentId, @RequestBody @Valid DependentForm dependentForm) {
        Dependent updatedDependent = dependentRepository.update(dependentId, dependentForm.toDependent());
        if (Objects.isNull(updatedDependent)) {
            throw new DefaultAppException(404, "There is no dependent for id: " + dependentId);
        }

        return ResponseEntity.ok().body(new DependentDTO(updatedDependent));
    }

    @DeleteMapping("/{dependentId}")
    public ResponseEntity<?> delete(@PathVariable Long dependentId) {
        Dependent dependent = dependentRepository.findById(dependentId);
        if (Objects.isNull(dependent)) {
            throw new DefaultAppException(404, "There is no dependent for id: " + dependentId);
        }

        dependentRepository.delete(dependentId);

        return ResponseEntity.ok().build();
    }

}
