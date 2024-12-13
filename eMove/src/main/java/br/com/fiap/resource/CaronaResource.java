package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.beans.CadastroCaronaRequest;
import br.com.fiap.beans.Carona;
import br.com.fiap.bo.CaronaBO;

@Path("/carona")
public class CaronaResource {

    private CaronaBO caronaBO = new CaronaBO();

    // Método GET para buscar uma carona pelo ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCaronaById(@PathParam("id") int id) {
        try {
            Carona carona = caronaBO.getCaronaById(id);
            if (carona != null) {
                return Response.ok(carona).build();
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Carona não encontrada.").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar carona.").build();
        }
    }

    @Path("/caronas")
    // Método GET para listar todas as caronas
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCaronas() {
        try {
            List<Carona> caronas = caronaBO.listarCaronas();
            return Response.ok(caronas).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar caronas.").build();
        }
    }

    // Método POST para criar uma nova carona
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarCarona(CadastroCaronaRequest request) {
    	System.out.println("Dados recebidos: " + request);
        Carona carona = request.getCarona();
        System.out.println("CPF: " + carona.getCpfMotorista());
        try {
            caronaBO.criarCarona(carona);
            return Response.status(Response.Status.CREATED).entity("Carona criada com sucesso.").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar carona.").build();
        }
    }
}
