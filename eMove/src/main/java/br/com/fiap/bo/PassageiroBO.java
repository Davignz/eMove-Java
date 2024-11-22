package br.com.fiap.bo;
import java.sql.SQLException;

import br.com.fiap.beans.Passageiro;
import br.com.fiap.dao.PassageiroDAO;
import br.com.fiap.exceptions.PassageiroNaoEncontradoException;

public class PassageiroBO {
    private PassageiroDAO passageiroDAO;

    public PassageiroBO() {
        passageiroDAO = new PassageiroDAO();
    }

 // Busca passageiro por CPF
    public Passageiro getPassageiroByCPF(String cpf) throws PassageiroNaoEncontradoException, SQLException {
        Passageiro passageiro = passageiroDAO.buscarPorCPF(cpf);
        if (passageiro == null) {
            throw new PassageiroNaoEncontradoException("Passageiro com CPF " + cpf + " não encontrado.");
        }
        return passageiro;
    }

    // Insere um novo passageiro
    public void inserirPassageiro(Passageiro passageiro) throws SQLException {
        // Regras de negócio (se houver) podem ser aplicadas aqui
        passageiro.setSaldo(200.00); // Define saldo inicial
        passageiro.setTotalCarbono(0.00); // Define carbono inicial como 0
        passageiroDAO.inserir(passageiro);
    }

    // Atualiza os dados de um passageiro
    public void updatePassageiro(Passageiro passageiro) throws PassageiroNaoEncontradoException, SQLException {
        // Verifica se o passageiro existe
        Passageiro passageiroExistente = passageiroDAO.buscarPorCPF(passageiro.getCpf());
        if (passageiroExistente == null) {
            throw new PassageiroNaoEncontradoException("Passageiro com CPF " + passageiro.getCpf() + " não encontrado.");
        }
        passageiroDAO.atualizar(passageiro);
    }

    // Deleta um passageiro por CPF
    public void deletePassageiro(String cpf) throws PassageiroNaoEncontradoException, SQLException {
        // Verifica se o passageiro existe antes de tentar deletar
        Passageiro passageiroExistente = passageiroDAO.buscarPorCPF(cpf);
        if (passageiroExistente == null) {
            throw new PassageiroNaoEncontradoException("Passageiro com CPF " + cpf + " não encontrado.");
        }
        passageiroDAO.deletar(cpf);
    }

}
