package br.com.fiap.bo;


import java.sql.SQLException;

import br.com.fiap.beans.Motorista;
import br.com.fiap.dao.MotoristaDAO;
import br.com.fiap.exceptions.MotoristaNaoEncontradoException;

public class MotoristaBO {

    private MotoristaDAO motoristaDAO = new MotoristaDAO();

    public Motorista getMotoristaByCPF(String cpf) throws SQLException, MotoristaNaoEncontradoException {
        Motorista motorista = motoristaDAO.buscarPorCPF(cpf);
        if (motorista == null) {
            throw new MotoristaNaoEncontradoException("Motorista não encontrado.");
        }
        return motorista;
    }

    public void inserirMotorista(Motorista motorista) throws SQLException {
    	motorista.setStatusMotorista("Ativo");
    	motorista.setTotalCarbono(0);
        motoristaDAO.inserir(motorista);
    }

    public void atualizarMotorista(Motorista motorista) throws SQLException {
    	Motorista motoristaExistente = motoristaDAO.buscarPorCPF(motorista.getCpf());
        if (motoristaExistente == null) {
            throw new MotoristaNaoEncontradoException("Motorista com CPF " + motorista.getCpf() + " não encontrado.");
        }
        motoristaDAO.atualizar(motorista);
    }

    public void deletarMotorista(String cpf) throws SQLException {
    	Motorista motoristaExistente = motoristaDAO.buscarPorCPF(cpf);
        if (motoristaExistente == null) {
            throw new MotoristaNaoEncontradoException("Motorista com CPF " + cpf + " não encontrado.");
        }
        motoristaDAO.deletar(cpf);
    }
}
