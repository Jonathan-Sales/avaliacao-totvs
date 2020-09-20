package br.com.jonathansales.desafio_totvs.person.repositories;

import br.com.jonathansales.desafio_totvs.person.models.Person;

import java.util.List;

public interface PersonRepository{

    List<Person> findAll();
    Person findById(Long personId);
    Person findByCpfCnpj(String cpfCnpj);
    Person save(Person person);
    Person update(Long personId, Person personUpdate);
    void delete(Long personId);

}
