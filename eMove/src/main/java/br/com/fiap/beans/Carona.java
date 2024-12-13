package br.com.fiap.beans;

import java.math.BigDecimal;

public class Carona {

    private int idCarona;
    private String dataHora;
    private BigDecimal longitudePartida;
    private BigDecimal latitudePartida;
    private BigDecimal longitudeChegada;
    private BigDecimal latitudeChegada;
    private double precoCarona;
    private double quilometragem;
    private double qtdCarbono;
    private int idPagamento;
    private String cpfMotorista;
    private String placa;

    public Carona() {
    }

    public Carona(int idCarona, String dataHora, BigDecimal longitudePartida, BigDecimal latitudePartida,
                  BigDecimal longitudeChegada, BigDecimal latitudeChegada, double precoCarona, double quilometragem,
                  double qtdCarbono, int idPagamento, String cpfMotorista, String placa) {
        this.idCarona = idCarona;
        this.dataHora = dataHora;
        this.longitudePartida = longitudePartida;
        this.latitudePartida = latitudePartida;
        this.longitudeChegada = longitudeChegada;
        this.latitudeChegada = latitudeChegada;
        this.precoCarona = precoCarona;
        this.quilometragem = quilometragem;
        this.qtdCarbono = qtdCarbono;
        this.idPagamento = idPagamento;
        this.cpfMotorista = cpfMotorista;
        this.placa = placa;
    }

    public int getIdCarona() {
        return idCarona;
    }

    public void setIdCarona(int idCarona) {
        this.idCarona = idCarona;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String localDateTime) {
        this.dataHora = localDateTime;
    }

    public BigDecimal getLongitudePartida() {
        return longitudePartida;
    }

    public void setLongitudePartida(BigDecimal longitudePartida) {
        this.longitudePartida = longitudePartida;
    }

    public BigDecimal getLatitudePartida() {
        return latitudePartida;
    }

    public void setLatitudePartida(BigDecimal latitudePartida) {
        this.latitudePartida = latitudePartida;
    }

    public BigDecimal getLongitudeChegada() {
        return longitudeChegada;
    }

    public void setLongitudeChegada(BigDecimal longitudeChegada) {
        this.longitudeChegada = longitudeChegada;
    }

    public BigDecimal getLatitudeChegada() {
        return latitudeChegada;
    }

    public void setLatitudeChegada(BigDecimal latitudeChegada) {
        this.latitudeChegada = latitudeChegada;
    }

    public double getPrecoCarona() {
        return precoCarona;
    }

    public void setPrecoCarona(double precoCarona) {
        this.precoCarona = precoCarona;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public double getQtdCarbono() {
        return qtdCarbono;
    }

    public void setQtdCarbono(double qtdCarbono) {
        this.qtdCarbono = qtdCarbono;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getCpfMotorista() {
        return cpfMotorista;
    }

    public void setCpfMotorista(String cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
