package net.javaguides.usermanagement.web;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.javaguides.usermanagement.dao.MatiereDAO;
import net.javaguides.usermanagement.model.Matiere;

import java.sql.SQLException;
import java.util.List;

@Path("/matieres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatiereResource {

    private final MatiereDAO dao = new MatiereDAO();

    @GET
    public List<Matiere> getAllMatieres() throws SQLException {
        return dao.selectAllMatieres();
    }

    @POST
    public Response addMatiere(Matiere matiere) {
        try {
            dao.insertMatiere(matiere);
            return Response.status(Response.Status.CREATED).entity(matiere).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idMatiere}")
    public Response getMatiereById(@PathParam("idMatiere") Long idMatiere) {
        Matiere matiere = dao.selectMatiere(idMatiere);
		if (matiere != null) {
		    return Response.ok(matiere).build();
		} else {
		    return Response.status(Response.Status.NOT_FOUND).build();
		}
    }

    @PUT
    @Path("/{idMatiere}")
    public Response updateMatiere(@PathParam("idMatiere") Long idMatiere, Matiere matiere) {
        try {
            matiere.setIdMatiere(idMatiere);
            dao.updateMatiere(matiere);
            return Response.ok(matiere).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idMatiere}")
    public Response deleteMatiere(@PathParam("idMatiere") Long idMatiere) {
        try {
            dao.deleteMatiere(idMatiere);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
