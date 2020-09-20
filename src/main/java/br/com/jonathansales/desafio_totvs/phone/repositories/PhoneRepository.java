package br.com.jonathansales.desafio_totvs.phone.repositories;

import br.com.jonathansales.desafio_totvs.phone.models.Phone;

import java.util.List;

public interface PhoneRepository {
    Phone save(Phone phone);
    List<Phone> saveAll(List<Phone> phones);
    Phone findById(Long dependentId);
    List<Phone> findByPersonId(Long personId);
    Phone update(Long phoneId, Phone phone);
    void delete(Long phoneId);
    void deleteByPersonId(Long personId);

}
