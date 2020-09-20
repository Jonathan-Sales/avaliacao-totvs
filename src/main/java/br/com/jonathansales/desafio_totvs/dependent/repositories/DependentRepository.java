package br.com.jonathansales.desafio_totvs.dependent.repositories;

import br.com.jonathansales.desafio_totvs.dependent.models.Dependent;

import java.util.List;

public interface DependentRepository {
    Dependent save(Dependent dependent);
    List<Dependent> saveAll(List<Dependent> dependents);
    Dependent findById(Long dependentId);
    List<Dependent> findByPersonId(Long personId);
    Dependent update(Long dependentId, Dependent dependent);
    void delete(Long dependentId);
    void deleteByPersonId(Long personId);


}
