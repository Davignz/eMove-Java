package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fiap.beans.Veiculo;
import br.com.fiap.conexao.ConexaoFactory;

public class VeiculoDAO {

    private Connection connection;

    public VeiculoDAO(Connection connection) {
        this.connection = connection;
    }

    public VeiculoDAO() {
        try {
            
            this.connection = new ConexaoFactory().conexao();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao obter conexão: " + e.getMessage());
        }
    }
    
 // Método para buscar veículo por placa
    public Veiculo getVeiculoByCPF(String cpf) throws SQLException {
        String sql = "SELECT placa, modelo, marca, autonomia_bateria, capacidade, cpf_motorista FROM VEICULOS WHERE cpf_motorista = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setAutonomiaBateria(rs.getInt("autonomia_bateria"));
                    veiculo.setCapacidade(rs.getInt("capacidade"));
                    veiculo.setCpfMotorista(rs.getString("cpf_motorista"));
                    return veiculo;
                } else {
                    throw new SQLException("Veículo do motorista com CPF " + cpf + " não encontrado.");
                }
            }
        }
    }


	// Método para inserir o veículo no banco de dados
    public void inserir(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO VEICULOS (placa, modelo, marca, autonomia_bateria, capacidade, cpf_motorista) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getMarca());
            stmt.setInt(4, veiculo.getAutonomiaBateria());
            stmt.setInt(5, veiculo.getCapacidade());
            stmt.setString(6, veiculo.getCpfMotorista());
            stmt.executeUpdate();
        }
    }
    
    public void atualizar(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE VEICULOS SET modelo = ?, marca = ?, autonomia_bateria = ?, capacidade = ? " +
                     "WHERE placa = ?";
        

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setInt(3, veiculo.getAutonomiaBateria());
            stmt.setInt(4, veiculo.getCapacidade());
            stmt.setString(5, veiculo.getPlaca());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erro: Nenhum registro foi atualizado.");
            }
            System.out.println("Veiculo atualizado: " + veiculo);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // Re-throw the exception after logging it
        }
    }


    // Método para excluir veículo associado a um motorista
    public void deletarPorMotorista(String cpfMotorista) throws SQLException {
        String sql = "DELETE FROM VEICULOS WHERE cpf_motorista = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpfMotorista);
            stmt.executeUpdate();
        }
    }
}
