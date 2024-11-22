package br.com.fiap.beans;

public class CadastroCaronaRequest {
    private Carona carona;

    public CadastroCaronaRequest() {
        super();
    }

    public CadastroCaronaRequest(Carona carona) {
        super();
        this.carona = carona;
    }

    public Carona getCarona() {
        return carona;
    }

    public void setCarona(Carona carona) {
        this.carona = carona;
    }
}
