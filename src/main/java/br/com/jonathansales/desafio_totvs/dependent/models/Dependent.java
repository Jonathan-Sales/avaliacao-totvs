package br.com.jonathansales.desafio_totvs.dependent.models;

import java.util.Objects;

public class Dependent {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependent dependent = (Dependent) o;
        return id == dependent.id &&
                Objects.equals(name, dependent.name) &&
                Objects.equals(type, dependent.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
