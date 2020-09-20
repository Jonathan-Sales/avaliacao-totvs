package br.com.jonathansales.desafio_totvs.person.models.dtos;

import br.com.jonathansales.desafio_totvs.address.models.dtos.AddressDTO;
import br.com.jonathansales.desafio_totvs.dependent.models.dtos.DependentDTO;
import br.com.jonathansales.desafio_totvs.person.models.Person;
import br.com.jonathansales.desafio_totvs.phone.models.dtos.PhoneDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonDTO {

    private Long id;
    private String name;
    private String tradingName;
    private String cpfCnpj;
    private String occupation;
    private Double salary;
    private LocalDate birthday;

    private List<AddressDTO> addresses;

    private List<PhoneDTO> phones;

    private List<DependentDTO> dependents;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.tradingName = person.getTradingName();
        this.cpfCnpj = person.getCpfCnpj();
        this.occupation = person.getOccupation();
        this.salary = person.getSalary();
        this.birthday = person.getBirthday();

        this.addresses = new ArrayList<>();
        if (Objects.nonNull(person.getAddresses())) {
            person.getAddresses().forEach(address -> addresses.add(new AddressDTO(address)));
        }

        this.phones = new ArrayList<>();
        if (Objects.nonNull(person.getPhones())) {
            person.getPhones().forEach(phone -> phones.add(new PhoneDTO(phone)));
        }

        this.dependents = new ArrayList<>();
        if (Objects.nonNull(person.getDependents())) {
            person.getDependents().forEach(dependent -> dependents.add(new DependentDTO(dependent)));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public List<DependentDTO> getDependents() {
        return dependents;
    }

    public void setDependents(List<DependentDTO> dependents) {
        this.dependents = dependents;
    }

}

