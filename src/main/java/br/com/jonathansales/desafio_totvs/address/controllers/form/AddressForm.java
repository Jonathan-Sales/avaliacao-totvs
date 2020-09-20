package br.com.jonathansales.desafio_totvs.address.controllers.form;

import br.com.jonathansales.desafio_totvs.address.models.Address;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressForm {

    private Long id;
    @NotNull
    @NotEmpty
    private String addressType;
    @NotNull
    @NotEmpty
    private String type;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String number;
    @NotNull
    @NotEmpty
    private String complement;
    @NotNull
    @NotEmpty
    private String zipCode;
    @NotNull
    @NotEmpty
    private String neighborhood;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @NotEmpty
    private String state;
    @NotNull
    @NotEmpty
    private String country;

    private Long personId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Address toAddress() {
        Address address = new Address();
        address.setId(this.id);
        address.setAddressType(this.addressType);
        address.setType(this.type);
        address.setName(this.name);
        address.setNumber(this.number);
        address.setComplement(this.complement);
        address.setZipCode(this.zipCode);
        address.setNeighborhood(this.neighborhood);
        address.setCity(this.city);
        address.setState(this.state);
        address.setCountry(this.country);
        address.setPersonId(this.personId);

        return address;
    }
}
