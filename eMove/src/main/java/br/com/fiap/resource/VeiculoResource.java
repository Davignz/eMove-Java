package br.com.fiap.resource;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.beans.Veiculo;
import br.com.fiap.bo.VeiculoBO;
import br.com.fiap.exceptions.VeiculoNaoEncontradoException;
@Path("/veiculo")
public class VeiculoResource {
	private VeiculoBO veiculoBO = new VeiculoBO();

    // Método GET para buscar veículo por placa
    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVeiculoByCPF(@PathParam("cpf") String placa) throws SQLException, VeiculoNaoEncontradoException {
        Veiculo veiculo = veiculoBO.getVeiculoByPlaca(placa);
		return Response.ok(veiculo).build();
    }
}
