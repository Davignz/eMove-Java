package br.com.fiap.beans;

public class CadastroPassageiroRequest {
    private Passageiro passageiro;

    public CadastroPassageiroRequest() {
        super();
    }

    public CadastroPassageiroRequest(Passageiro passageiro) {
        super();
        this.passageiro = passageiro;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

}
