package br.com.fiap.beans;

public class Motorista {
	private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private String cep;
    private String statusMotorista;
    private double totalCarbono;
    
	public Motorista() {
		super();
		
	}
	public Motorista(String cpf, String nome, String email, String telefone, String cep, String statusMotorista,
			double totalCarbono) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cep = cep;
		this.statusMotorista = statusMotorista;
		this.totalCarbono = totalCarbono;
	}
	public Motorista(String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getStatusMotorista() {
		return statusMotorista;
	}
	public String setStatusMotorista(String statusMotorista) {
		return this.statusMotorista = statusMotorista;
	}
	public double getTotalCarbono() {
		return totalCarbono;
	}
	public double setTotalCarbono(double totalCarbono) {
		return this.totalCarbono = totalCarbono;
	}
    
    
}
