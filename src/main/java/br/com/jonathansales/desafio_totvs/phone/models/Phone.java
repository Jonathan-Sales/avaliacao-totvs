package br.com.jonathansales.desafio_totvs.phone.models;

import java.util.Objects;

public class Phone {

    private Long id;
    private String number;
    private String countryCode;
    private String cityCode;
    private String type;

    private Long personId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countyCode) {
        this.countryCode = countyCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String ddd) {
        this.cityCode = ddd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return id == phone.id &&
                Objects.equals(number, phone.number) &&
                Objects.equals(countryCode, phone.countryCode) &&
                Objects.equals(cityCode, phone.cityCode) &&
                Objects.equals(type, phone.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, countryCode, cityCode, type);
    }
}
