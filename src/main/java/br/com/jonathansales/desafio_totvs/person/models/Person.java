package br.com.jonathansales.desafio_totvs.person.models;

import br.com.jonathansales.desafio_totvs.address.models.Address;
import br.com.jonathansales.desafio_totvs.dependent.models.Dependent;
import br.com.jonathansales.desafio_totvs.phone.models.Phone;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Person {

    private Long id;
    private String name;
    private String tradingName;
    private String cpfCnpj;
    private String occupation;
    private Double salary;
    private LocalDate birthday;

    private List<Phone> phones;
    private List<Address> addresses;
    private List<Dependent> dependents;

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

    public void setTradingName(String trandingName) {
        this.tradingName = trandingName;
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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name) &&
                Objects.equals(tradingName, person.tradingName) &&
                Objects.equals(cpfCnpj, person.cpfCnpj) &&
                Objects.equals(occupation, person.occupation) &&
                Objects.equals(salary, person.salary) &&
                Objects.equals(birthday, person.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tradingName, cpfCnpj, occupation, salary, birthday);
    }
}
