package br.com.jonathansales.desafio_totvs.phone.repositories.implementations.memory;

import br.com.jonathansales.desafio_totvs.phone.models.Phone;
import br.com.jonathansales.desafio_totvs.phone.repositories.PhoneRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PhoneRepositoryImpl implements PhoneRepository {

    private final List<Phone> phones;
    private final AtomicLong atomicLong = new AtomicLong();

    public PhoneRepositoryImpl() {
        this.phones = new ArrayList<>();
    }

    @Override
    public Phone save(Phone phone) {
        phone.setId(atomicLong.incrementAndGet());
        this.phones.add(phone);

        return phone;
    }

    @Override
    public List<Phone> saveAll(List<Phone> phones) {
        for (Phone phone : phones) {
            phone.setId(atomicLong.incrementAndGet());
            this.phones.add(phone);
        }
        return phones;
    }

    @Override
    public Phone findById(Long phoneId) {
        return this.phones.stream().filter(phone -> phone.getId().equals(phoneId)).findFirst().orElse(null);
    }

    @Override
    public List<Phone> findByPersonId(Long personId) {
        return this.phones.stream().filter(phone -> phone.getPersonId().equals(personId)).collect(Collectors.toList());
    }

    @Override
    public Phone update(Long phoneId, Phone phoneUpdate) {
        this.phones
                .stream()
                .filter(filterPhone -> filterPhone.getId().equals(phoneId))
                .findFirst()
                .ifPresent(savedPhone -> {
                    savedPhone.setNumber(phoneUpdate.getNumber());
                    savedPhone.setCountryCode(phoneUpdate.getCountryCode());
                    savedPhone.setCityCode(phoneUpdate.getCityCode());
                    savedPhone.setType(phoneUpdate.getType());
                });

        return findById(phoneId);
    }

    @Override
    public void delete(Long phoneId) {
        this.phones.removeIf(phone -> phone.getId().equals(phoneId));
    }

    @Override
    public void deleteByPersonId(Long personId) {
        this.phones.removeIf(phone -> phone.getPersonId().equals(personId));
    }

}
