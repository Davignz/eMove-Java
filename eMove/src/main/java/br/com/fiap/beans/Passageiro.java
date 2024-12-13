package br.com.fiap.beans;

public class Passageiro {
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private String cep;
    private double saldo;
    private double totalCarbono;

    public Passageiro() {}

    public Passageiro(String cpf, String nome, String email, String telefone, String cep, double saldo, double totalCarbono) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
        this.saldo = saldo;
        this.totalCarbono = totalCarbono;
    }

	public Passageiro(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	// Getters e Setters
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getTotalCarbono() {
        return totalCarbono;
    }

    public void setTotalCarbono(double totalCarbono) {
        this.totalCarbono = totalCarbono;
    }

    public void calcularCarbono(double emissao) {
        this.totalCarbono += emissao;
    }

    public void atualizarSaldo(double valor) {
        this.saldo += valor;
    }
}
