package br.com.fiap.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.beans.Motorista;
import br.com.fiap.conexao.ConexaoFactory;

public class MotoristaDAO {

    private Connection connection;

    public MotoristaDAO(Connection connection) {
        this.connection = connection;  // Método que cria a conexão com o banco
    }

    public MotoristaDAO() {
        try {
            
            this.connection = new ConexaoFactory().conexao();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao obter conexão: " + e.getMessage());
        }
    }

	// Método para buscar um motorista por CPF
    public Motorista buscarPorCPF(String cpf) throws SQLException {
        String sql = "SELECT * FROM MOTORISTAS WHERE cpf_motorista = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extrairMotoristaDoResultSet(rs);
            }
        }
        return null;
    }

    // Método para inserir um novo motorista
    public void inserir(Motorista motorista) throws SQLException {
        String sql = "INSERT INTO MOTORISTAS (nome, cpf_motorista, email, telefone, cep, status_motorista, total_carbono_motorista) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCpf());
            stmt.setString(3, motorista.getEmail());
            stmt.setString(4, motorista.getTelefone());
            stmt.setString(5, motorista.getCep());
            stmt.setString(6, motorista.getStatusMotorista());
            stmt.setDouble(7, motorista.getTotalCarbono());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar as informações de um motorista
    public void atualizar(Motorista motorista) throws SQLException {
        String sql = "UPDATE MOTORISTAS SET nome = ?, email = ?, telefone = ?, cep = ?, status_motorista = ?, total_carbono_motorista = ? " +
                     "WHERE cpf_motorista = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getEmail());
            stmt.setString(3, motorista.getTelefone());
            stmt.setString(4, motorista.getCep());
            stmt.setString(5, motorista.setStatusMotorista("Ativo"));
            stmt.setDouble(6, motorista.setTotalCarbono(0));
            stmt.setString(7, motorista.getCpf());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um motorista pelo CPF
    public void deletar(String cpf) throws SQLException {
        String sql = "DELETE FROM MOTORISTAS WHERE cpf_motorista = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }

    // Método auxiliar para extrair os dados do ResultSet e criar um objeto Motorista
    private Motorista extrairMotoristaDoResultSet(ResultSet rs) throws SQLException {
        Motorista motorista = new Motorista();
        motorista.setNome(rs.getString("nome"));
        motorista.setCpf(rs.getString("cpf_motorista"));
        motorista.setEmail(rs.getString("email"));
        motorista.setTelefone(rs.getString("telefone"));
        motorista.setCep(rs.getString("cep"));
        motorista.setStatusMotorista(rs.getString("status_motorista"));
        motorista.setTotalCarbono(rs.getDouble("total_carbono_motorista"));
        return motorista;
    }
}
