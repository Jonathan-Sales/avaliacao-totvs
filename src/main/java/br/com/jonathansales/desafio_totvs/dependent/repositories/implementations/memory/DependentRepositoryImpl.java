package br.com.jonathansales.desafio_totvs.dependent.repositories.implementations.memory;

import br.com.jonathansales.desafio_totvs.dependent.models.Dependent;
import br.com.jonathansales.desafio_totvs.dependent.repositories.DependentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class DependentRepositoryImpl implements DependentRepository {

    private final List<Dependent> dependents;
    private final AtomicLong atomicLong = new AtomicLong();

    public DependentRepositoryImpl() {
        this.dependents = new ArrayList<>();
    }

    @Override
    public Dependent save(Dependent dependent) {
        dependent.setId(atomicLong.incrementAndGet());
        this.dependents.add(dependent);

        return dependent;
    }

    @Override
    public List<Dependent> saveAll(List<Dependent> dependents) {
        for (Dependent dependent : dependents) {
            dependent.setId(atomicLong.incrementAndGet());
            this.dependents.add(dependent);
        }
        return dependents;
    }

    @Override
    public Dependent findById(Long dependentId) {
        return this.dependents.stream().filter(dependent -> dependent.getId().equals(dependentId)).findFirst().orElse(null);
    }

    @Override
    public List<Dependent> findByPersonId(Long personId) {
        return this.dependents.stream().filter(dependent -> dependent.getPersonId().equals(personId)).collect(Collectors.toList());
    }

    @Override
    public Dependent update(Long dependentId, Dependent dependentUpdate) {
        this.dependents
                .stream()
                .filter(filterDependent -> filterDependent.getId().equals(dependentId))
                .findFirst()
                .ifPresent(savedDependent -> {
                    savedDependent.setName(dependentUpdate.getName());
                    savedDependent.setType(dependentUpdate.getType());
                });

        return findById(dependentId);
    }

    @Override
    public void delete(Long dependentId) {
        this.dependents.removeIf(dependent -> dependent.getId().equals(dependentId));
    }

    @Override
    public void deleteByPersonId(Long personId) {
        this.dependents.removeIf(dependent -> dependent.getPersonId() == personId);
    }
}
