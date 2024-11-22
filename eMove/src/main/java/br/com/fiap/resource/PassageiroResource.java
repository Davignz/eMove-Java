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

import br.com.fiap.beans.CadastroPassageiroRequest;
import br.com.fiap.beans.Passageiro;
import br.com.fiap.bo.PassageiroBO;
import br.com.fiap.exceptions.PassageiroNaoEncontradoException;

@Path("/passageiro")
public class PassageiroResource {

    private PassageiroBO passageiroBO = new PassageiroBO();

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPassageiroByCPF(@PathParam("cpf") String cpf) throws SQLException {
        try {
            Passageiro passageiro = passageiroBO.getPassageiroByCPF(cpf);
            return Response.ok(passageiro).build();
        } catch (PassageiroNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarPassageiro(CadastroPassageiroRequest request) {
        System.out.println("Dados recebidos: " + request);
        Passageiro passageiro = request.getPassageiro();
        System.out.println("CPF: " + passageiro.getCpf());

        if (passageiro.getCpf() == null || passageiro.getCpf().trim().length() != 11) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Erro: CPF inválido!")
                .build();
        }

        // Chamada ao DAO
        try {
            passageiroBO.inserirPassageiro(passageiro);
            return Response.status(Response.Status.CREATED).entity(passageiro).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPassageiro(@PathParam("cpf") String cpf, CadastroPassageiroRequest request) throws SQLException {
        try {
            Passageiro passageiro = request.getPassageiro();
            passageiro.setCpf(cpf);
            passageiroBO.updatePassageiro(passageiro);
            return Response.ok().build();
        } catch (PassageiroNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarPassageiro(@PathParam("cpf") String cpf) throws SQLException {
        try {
            passageiroBO.deletePassageiro(cpf);
            return Response.noContent().build();
        } catch (PassageiroNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
