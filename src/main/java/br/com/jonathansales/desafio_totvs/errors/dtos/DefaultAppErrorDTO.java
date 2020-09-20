package br.com.jonathansales.desafio_totvs.errors.dtos;

public class DefaultAppErrorDTO {

    private String message;

    public DefaultAppErrorDTO() {
    }

    public DefaultAppErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
