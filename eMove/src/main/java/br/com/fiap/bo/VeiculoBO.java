package br.com.fiap.bo;

import java.sql.SQLException;

import br.com.fiap.beans.Veiculo;
import br.com.fiap.dao.VeiculoDAO;

public class VeiculoBO {

    private VeiculoDAO veiculoDAO = new VeiculoDAO();
    
    public Veiculo getVeiculoByPlaca(String placa)throws SQLException {
    	Veiculo veiculo = veiculoDAO.getVeiculoByCPF(placa);
    	veiculoDAO.getVeiculoByCPF(placa);
    	return veiculo;
    }

    public void inserir(Veiculo veiculo) throws SQLException {
        veiculoDAO.inserir(veiculo);
    }
    
    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        veiculoDAO.atualizar(veiculo);
    }

    public void deletarVeiculoPorMotorista(String cpfMotorista) throws SQLException {
        veiculoDAO.deletarPorMotorista(cpfMotorista);
    }
}

