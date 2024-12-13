package br.com.fiap.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.beans.Motorista;
import br.com.fiap.beans.Passageiro;
import br.com.fiap.conexao.ConexaoFactory;

@Path("/login")
public class LoginResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(String email) throws ClassNotFoundException {
		email = email.replace("\"", "").trim().toLowerCase();
	    if (email == null || email.isEmpty()) {
	        return Response.status(Response.Status.BAD_REQUEST).entity("E-mail inválido ou vazio").build();
	    }

	    // SQL corrigido com TRIM
	    String sqlMotorista = "SELECT cpf_motorista, nome FROM MOTORISTAS WHERE TRIM(email) = ?";
	    String sqlPassageiro = "SELECT cpf_passageiro, nome FROM PASSAGEIROS WHERE TRIM(email) = ?";

	    try (Connection conn = new ConexaoFactory().conexao();
	         PreparedStatement stmtMotorista = conn.prepareStatement(sqlMotorista);
	         PreparedStatement stmtPassageiro = conn.prepareStatement(sqlPassageiro)) {

	        // Log do email recebido
	        System.out.println("E-mail recebido: '" + email + "'");

	        // Tratamento do email
	        email = email.trim().toLowerCase();

	        // Consulta em MOTORISTAS
	        stmtMotorista.setString(1, email);
	        ResultSet rsMotorista = stmtMotorista.executeQuery();

	        if (rsMotorista.next()) {
	            System.out.println("Motorista encontrado!");
	            Motorista motorista = new Motorista(
	                rsMotorista.getString("cpf_motorista"),
	                rsMotorista.getString("nome")
	            );
	            return Response.ok(motorista).build();
	        }

	        // Consulta em PASSAGEIROS
	        stmtPassageiro.setString(1, email);
	        ResultSet rsPassageiro = stmtPassageiro.executeQuery();

	        if (rsPassageiro.next()) {
	            System.out.println("Passageiro encontrado!");
	            Passageiro passageiro = new Passageiro(
	                rsPassageiro.getString("cpf_passageiro"),
	                rsPassageiro.getString("nome")
	            );
	            return Response.ok(passageiro).build();
	        }

	        // Caso nenhum usuário seja encontrado
	        System.out.println("Email não encontrado em Motoristas nem Passageiros.");
	        return Response.status(Response.Status.UNAUTHORIZED).entity("Email não encontrado").build();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao acessar o banco de dados").build();
	    }
	}

}
