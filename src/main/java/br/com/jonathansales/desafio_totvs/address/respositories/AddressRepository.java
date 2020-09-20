package br.com.jonathansales.desafio_totvs.address.respositories;

import br.com.jonathansales.desafio_totvs.address.models.Address;

import java.util.List;

public interface AddressRepository {
    Address findById(Long addressId);
    List<Address> findByPersonId(Long personId);
    Address save(Address address);
    List<Address> saveAll(List<Address> addresses);
    Address update(Long addressId, Address addressUpdate);
    void delete(Long addressId);
    void deleteByPersonId(Long personId);
}
