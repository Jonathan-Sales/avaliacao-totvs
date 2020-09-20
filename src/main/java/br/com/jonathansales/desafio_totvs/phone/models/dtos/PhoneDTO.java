package br.com.jonathansales.desafio_totvs.phone.models.dtos;

import br.com.jonathansales.desafio_totvs.phone.models.Phone;

public class PhoneDTO {

    private Long id;
    private String number;
    private String countryCode;
    private String cityCode;
    private String type;

    public PhoneDTO() {
    }

    public PhoneDTO(Phone phone) {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.countryCode = phone.getCountryCode();
        this.cityCode = phone.getCityCode();
        this.type = phone.getType();
    }

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

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

}
