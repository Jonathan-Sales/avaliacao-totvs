package br.com.jonathansales.desafio_totvs.address.respositories.implementations.memory;

import br.com.jonathansales.desafio_totvs.address.models.Address;
import br.com.jonathansales.desafio_totvs.address.respositories.AddressRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private final List<Address> addresses;
    private final AtomicLong atomicLong = new AtomicLong();

    public AddressRepositoryImpl() {
        this.addresses = new ArrayList<>();
    }

    @Override
    public Address findById(Long addressId) {
        return this.addresses.stream().filter(address -> address.getId().equals(addressId)).findFirst().orElse(null);
    }

    @Override
    public List<Address> findByPersonId(Long personId) {
        return this.addresses.stream().filter(address -> address.getPersonId().equals(personId)).collect(Collectors.toList());
    }

    @Override
    public Address save(Address address) {
        address.setId(atomicLong.incrementAndGet());
        this.addresses.add(address);

        return address;
    }

    @Override
    public List<Address> saveAll(List<Address> addresses) {
        for (Address address : addresses) {
            address.setId(atomicLong.incrementAndGet());
            this.addresses.add(address);
        }
        return addresses;
    }

    @Override
    public Address update(Long addressId, Address addressUpdate) {
        this.addresses
                .stream()
                .filter(filterAddress -> filterAddress.getId().equals(addressId))
                .findFirst()
                .ifPresent(savedAddress -> {
                    savedAddress.setAddressType(addressUpdate.getAddressType());
                    savedAddress.setType(addressUpdate.getType());
                    savedAddress.setName(addressUpdate.getName());
                    savedAddress.setNumber(addressUpdate.getNumber());
                    savedAddress.setComplement(addressUpdate.getComplement());
                    savedAddress.setZipCode(addressUpdate.getZipCode());
                    savedAddress.setNeighborhood(addressUpdate.getNeighborhood());
                    savedAddress.setCity(addressUpdate.getCity());
                    savedAddress.setState(addressUpdate.getState());
                    savedAddress.setCountry(addressUpdate.getCountry());
                });

        return findById(addressId);
    }

    @Override
    public void delete(Long addressId) {
        this.addresses.removeIf(address -> address.getId().equals(addressId));
    }

    @Override
    public void deleteByPersonId(Long personId) {
        this.addresses.removeIf(address -> address.getPersonId().equals(personId));
    }
}
