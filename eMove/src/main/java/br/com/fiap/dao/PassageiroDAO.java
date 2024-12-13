package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.beans.Passageiro;
import br.com.fiap.conexao.ConexaoFactory;

public class PassageiroDAO {
	
    private Connection connection;

    public PassageiroDAO(Connection connection) {
        this.connection = connection;
    }
    
    public PassageiroDAO() {
        try {
            
            this.connection = new ConexaoFactory().conexao();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao obter conexão: " + e.getMessage());
        }
    }
    
 // Inserir passageiro
    public void inserir(Passageiro passageiro) throws SQLException {
        String sql = "INSERT INTO PASSAGEIROS (nome, cpf_passageiro, email, telefone, cep, saldo, total_carbono_passageiro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        	stmt.setString(1, passageiro.getNome());
            stmt.setString(2, passageiro.getCpf());
            stmt.setString(3, passageiro.getEmail());
            stmt.setString(4, passageiro.getTelefone());
            stmt.setString(5, passageiro.getCep());
            stmt.setDouble(6, passageiro.getSaldo());
            stmt.setDouble(7, passageiro.getTotalCarbono());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
        	System.out.println("Erro ao inserir passageiro: " + e.getMessage());
            e.printStackTrace();
		}
    }

    // Buscar passageiro por CPF
    public Passageiro buscarPorCPF(String cpf) throws SQLException {
        String sql = "SELECT * FROM PASSAGEIROS WHERE cpf_passageiro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPassageiro(rs);
                }
            }
        }
        return null;
    }

    // Atualizar passageiro
    public void atualizar(Passageiro passageiro) throws SQLException {
        String sql = "UPDATE PASSAGEIROS SET nome = ?, email = ?, telefone = ?, cep = ?, saldo = ?, total_carbono_passageiro = ? WHERE cpf_passageiro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, passageiro.getNome());
            stmt.setString(2, passageiro.getEmail());
            stmt.setString(3, passageiro.getTelefone());
            stmt.setString(4, passageiro.getCep());
            stmt.setDouble(5, passageiro.getSaldo());
            stmt.setDouble(6, passageiro.getTotalCarbono());
            stmt.setString(7, passageiro.getCpf());
            stmt.executeUpdate();
        }
    }

    // Deletar passageiro por CPF
    public void deletar(String cpf) throws SQLException {
        String sql = "DELETE FROM PASSAGEIROS WHERE cpf_passageiro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }
    
 // Mapeamento do ResultSet para o objeto Passageiro
    private Passageiro mapResultSetToPassageiro(ResultSet rs) throws SQLException {
        Passageiro passageiro = new Passageiro();
        passageiro.setCpf(rs.getString("cpf_passageiro"));
        passageiro.setNome(rs.getString("nome"));
        passageiro.setEmail(rs.getString("email"));
        passageiro.setTelefone(rs.getString("telefone"));
        passageiro.setCep(rs.getString("cep"));
        passageiro.setSaldo(rs.getDouble("saldo"));
        passageiro.setTotalCarbono(rs.getDouble("total_carbono_passageiro"));
        return passageiro;
    }
}
