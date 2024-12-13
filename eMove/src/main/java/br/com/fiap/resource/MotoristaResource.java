package br.com.fiap.resource;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.beans.CadastroMotoristaRequest;
import br.com.fiap.beans.Motorista;
import br.com.fiap.beans.Veiculo;
import br.com.fiap.bo.MotoristaBO;
import br.com.fiap.bo.VeiculoBO;
import br.com.fiap.exceptions.MotoristaNaoEncontradoException;

@Path("/motorista")
public class MotoristaResource {

	private MotoristaBO motoristaBO = new MotoristaBO();
    private VeiculoBO veiculoBO = new VeiculoBO();

    // Método GET para buscar motorista por CPF
    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMotoristaByCPF(@PathParam("cpf") String cpf) throws SQLException {
        try {
            Motorista motorista = motoristaBO.getMotoristaByCPF(cpf);
            return Response.ok(motorista).build();
        } catch (MotoristaNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarMotoristaECarro(CadastroMotoristaRequest request) {
        Motorista motorista = request.getMotorista();
        Veiculo veiculo = request.getVeiculo();

        // Verificar se o CPF do motorista é válido
        if (motorista.getCpf() == null || motorista.getCpf().trim().length() != 11) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro: CPF inválido!")
                    .build();
        }

        try {
            // 1. Inserir o motorista no banco de dados
            motoristaBO.inserirMotorista(motorista);

            // 2. Associar o motorista ao veículo e inserir o veículo no banco
            veiculo.setCpfMotorista(motorista.getCpf());  // Associar o CPF do motorista ao veículo
            veiculoBO.inserir(veiculo);

            return Response.status(Response.Status.CREATED)
                    .entity("Motorista e veículo cadastrados com sucesso!")
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar motorista e veículo.")
                    .build();
        }
    }

    @PUT
    @Path("/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarMotorista(@PathParam("cpf") String cpf, CadastroMotoristaRequest request) throws SQLException {
        Motorista motorista = request.getMotorista();
        Veiculo veiculo = request.getVeiculo();

        // Verificar se o CPF do motorista é válido
        if (motorista.getCpf() == null || motorista.getCpf().trim().length() != 11) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro: CPF inválido!")
                    .build();
        }

        try {
            // 1. Atualizar o motorista no banco de dados
            motorista.setCpf(cpf);  // Garantir que o CPF do motorista seja o mesmo que o recebido na URL
            motoristaBO.atualizarMotorista(motorista);

            // 2. Atualizar o veículo associado ao motorista
            veiculo.setCpfMotorista(cpf);  // Associar o CPF do motorista ao veículo
            veiculoBO.atualizarVeiculo(veiculo);

            return Response.ok().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar motorista e veículo.")
                    .build();
        }
    }

    @DELETE
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarMotorista(@PathParam("cpf") String cpf) throws SQLException {
        try {
            // 1. Excluir o veículo associado ao motorista
            veiculoBO.deletarVeiculoPorMotorista(cpf);

            // 2. Excluir o motorista
            motoristaBO.deletarMotorista(cpf);

            return Response.noContent().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir motorista e veículo.")
                    .build();
        }
    }
}
