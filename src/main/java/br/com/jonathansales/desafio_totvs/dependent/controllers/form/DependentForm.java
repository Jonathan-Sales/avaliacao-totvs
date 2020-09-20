package br.com.jonathansales.desafio_totvs.dependent.controllers.form;

import br.com.jonathansales.desafio_totvs.dependent.models.Dependent;

public class DependentForm {

    private Long id;
    private String name;
    private String type;

    private Long personId;

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

    public Dependent toDependent(){
        Dependent dependent = new Dependent();
        dependent.setId(this.id);
        dependent.setName(this.name);
        dependent.setType(this.type);
        dependent.setPersonId(this.personId);

        return dependent;
    }
}
