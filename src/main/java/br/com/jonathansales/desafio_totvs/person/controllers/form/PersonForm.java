package br.com.jonathansales.desafio_totvs.person.controllers.form;

import br.com.jonathansales.desafio_totvs.address.controllers.form.AddressForm;
import br.com.jonathansales.desafio_totvs.address.models.Address;
import br.com.jonathansales.desafio_totvs.dependent.controllers.form.DependentForm;
import br.com.jonathansales.desafio_totvs.dependent.models.Dependent;
import br.com.jonathansales.desafio_totvs.person.models.Person;
import br.com.jonathansales.desafio_totvs.phone.controllers.form.PhoneForm;
import br.com.jonathansales.desafio_totvs.phone.models.Phone;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonForm {

    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    private String tradingName;
    @NotNull
    @NotEmpty
    private String cpfCnpj;
    @NotNull
    @NotEmpty
    private String occupation;
    @NotNull
    @Min(0)
    private Double salary;
    @NotNull
    private LocalDate birthday;

    private List<AddressForm> addresses;
    private List<PhoneForm> phones;
    private List<DependentForm> dependents;

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

    public List<AddressForm> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressForm> addresses) {
        this.addresses = addresses;
    }

    public List<PhoneForm> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneForm> phones) {
        this.phones = phones;
    }

    public List<DependentForm> getDependents() {
        return dependents;
    }

    public void setDependents(List<DependentForm> dependents) {
        this.dependents = dependents;
    }

    public Person toPerson() {
        Person person = new Person();
        person.setId(this.id);
        person.setName(this.name);
        person.setTradingName(this.tradingName);
        person.setCpfCnpj(this.cpfCnpj);
        person.setOccupation(this.occupation);
        person.setSalary(this.salary);
        person.setBirthday(this.birthday);

        if (Objects.nonNull(this.addresses)) {
            List<Address> addresses = this.addresses.stream().map(AddressForm::toAddress).collect(Collectors.toList());
            person.setAddresses(addresses);
        }

        if (Objects.nonNull(this.phones)) {
            List<Phone> phones = this.phones.stream().map(PhoneForm::toPhone).collect(Collectors.toList());
            person.setPhones(phones);
        }

        if (Objects.nonNull(this.dependents)) {
            List<Dependent> dependents = this.dependents.stream().map(DependentForm::toDependent).collect(Collectors.toList());
            person.setDependents(dependents);
        }

        return person;
    }
}
