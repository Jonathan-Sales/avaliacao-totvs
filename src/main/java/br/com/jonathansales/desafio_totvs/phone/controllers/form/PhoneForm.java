package br.com.jonathansales.desafio_totvs.phone.controllers.form;

import br.com.jonathansales.desafio_totvs.phone.models.Phone;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PhoneForm {

    private Long id;
    @NotNull @NotEmpty
    private String number;
    @NotNull @NotEmpty
    private String countryCode;
    @NotNull @NotEmpty
    private String cityCode;
    @NotNull @NotEmpty
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

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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

    public Phone toPhone(){
        Phone phone = new Phone();
        phone.setId(this.id);
        phone.setNumber(this.number);
        phone.setCountryCode(this.countryCode);
        phone.setCityCode(this.cityCode);
        phone.setType(this.type);
        phone.setPersonId(this.personId);

        return phone;
    }
}
