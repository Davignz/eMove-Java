package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Carona;
import br.com.fiap.conexao.ConexaoFactory;

public class CaronaDAO {

	private Connection connection;

	public CaronaDAO(Connection connection) {
		this.connection = connection;
	}

	public CaronaDAO() {
		try {

			this.connection = new ConexaoFactory().conexao();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erro ao obter conexão: " + e.getMessage());
		}
	}

	public void criarCarona(Carona carona) throws SQLException {
		String sql = "INSERT INTO CARONAS (data_hora, longitude_partida, latitude_partida, longitude_chegada, latitude_chegada, "
				+ "preco_carona, quilometragem, qtd_carbono, id_pagamento, cpf_motorista, placa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("id carona: "+ carona.getIdCarona());

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, carona.getDataHora());
			stmt.setBigDecimal(2, carona.getLongitudePartida());
			stmt.setBigDecimal(3, carona.getLatitudePartida());
			stmt.setBigDecimal(4, carona.getLongitudeChegada());
			stmt.setBigDecimal(5, carona.getLatitudeChegada());
			stmt.setDouble(6, carona.getPrecoCarona());
			stmt.setDouble(7, carona.getQuilometragem());
			stmt.setDouble(8, carona.getQtdCarbono());
			stmt.setInt(9, carona.getIdPagamento());
			stmt.setString(10, carona.getCpfMotorista());
			stmt.setString(11, carona.getPlaca());
			System.out.println("dados carona: " + carona.getLatitudePartida());
			stmt.executeUpdate();
			
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					carona.setIdCarona(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Carona getCaronaById(int id) throws SQLException {
		String sql = "SELECT * FROM CARONAS WHERE id_carona = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToCarona(rs);
				}
			}
		}
		return null;
	}

	public List<Carona> listarCaronas() throws SQLException {
		List<Carona> caronas = new ArrayList<>();
		String sql = "SELECT * FROM CARONAS";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				caronas.add(mapResultSetToCarona(rs));
			}
		}
		return caronas;
	}

	private Carona mapResultSetToCarona(ResultSet rs) throws SQLException {
		Carona carona = new Carona();
		carona.setIdCarona(rs.getInt("id_carona"));
		carona.setDataHora(rs.getString("data_hora"));
		carona.setLongitudePartida(rs.getBigDecimal("longitude_partida"));
		carona.setLatitudePartida(rs.getBigDecimal("latitude_partida"));
		carona.setLongitudeChegada(rs.getBigDecimal("longitude_chegada"));
		carona.setLatitudeChegada(rs.getBigDecimal("latitude_chegada"));
		carona.setPrecoCarona(rs.getDouble("preco_carona"));
		carona.setQuilometragem(rs.getDouble("quilometragem"));
		carona.setQtdCarbono(rs.getDouble("qtd_carbono"));
		carona.setIdPagamento(rs.getInt("id_pagamento"));
		carona.setCpfMotorista(rs.getString("cpf_motorista"));
		carona.setPlaca(rs.getString("placa"));
		return carona;
	}
}
