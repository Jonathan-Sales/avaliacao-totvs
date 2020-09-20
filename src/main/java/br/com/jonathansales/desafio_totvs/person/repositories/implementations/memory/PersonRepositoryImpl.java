package br.com.jonathansales.desafio_totvs.person.repositories.implementations.memory;

import br.com.jonathansales.desafio_totvs.person.models.Person;
import br.com.jonathansales.desafio_totvs.person.repositories.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final List<Person> people;
    private final AtomicLong atomicLong = new AtomicLong();

    public PersonRepositoryImpl() {
        people = new ArrayList<>();
    }

    @Override
    public List<Person> findAll() {
        return people;
    }

    @Override
    public Person findById(Long personId) {
        return this.people
                .stream()
                .filter(person -> person.getId().equals(personId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Person findByCpfCnpj(String cpfCnpj) {
        return people
                .stream()
                .filter(person -> person.getCpfCnpj().equals(cpfCnpj))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Person save(Person person) {
        person.setId(atomicLong.incrementAndGet());
        people.add(person);

        return person;
    }

    @Override
    public Person update(Long personId, Person personUpdate) {
        this.people
                .stream()
                .filter(filterPerson -> filterPerson.getId().equals(personId))
                .findFirst()
                .ifPresent(savedPerson -> {
                    savedPerson.setName(personUpdate.getName());
                    savedPerson.setTradingName(personUpdate.getTradingName());
                    savedPerson.setCpfCnpj(personUpdate.getCpfCnpj());
                    savedPerson.setOccupation(personUpdate.getOccupation());
                    savedPerson.setSalary(personUpdate.getSalary());
                    savedPerson.setBirthday(personUpdate.getBirthday());
                });

        return findById(personId);
    }

    @Override
    public void delete(Long personId) {
        this.people.removeIf(person -> person.getId().equals(personId));
    }

}
