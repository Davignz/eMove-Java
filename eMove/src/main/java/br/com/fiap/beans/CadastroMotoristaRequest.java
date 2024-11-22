package br.com.fiap.beans;

public class CadastroMotoristaRequest {
    private Motorista motorista;
    private Veiculo veiculo;


    public CadastroMotoristaRequest() {
		super();
	}

	public CadastroMotoristaRequest(Motorista motorista, Veiculo veiculo) {
		super();
		this.motorista = motorista;
		this.veiculo = veiculo;
	}

	public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
