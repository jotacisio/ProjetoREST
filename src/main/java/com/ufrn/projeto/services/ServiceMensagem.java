package com.ufrn.projeto.services;

import com.ufrn.projeto.dao.interfaces.ITemperaturaDao;
import com.ufrn.projeto.dao.implementations.TemperaturaDaoImp;
import com.ufrn.projeto.model.Mensagem;
import com.ufrn.projeto.model.OutputMessage;
import com.ufrn.projeto.model.Temperatura;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.criterion.Order;

@Path("/mensagem")
public class ServiceMensagem {

    private final AtomicLong counter = new AtomicLong();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensagem(
            @QueryParam("nome") @DefaultValue("World!") String nome) {
        Mensagem m = new Mensagem(counter.incrementAndGet(), "Hello, " + nome);
        return Response.status(Response.Status.CREATED).entity(m).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Temperatura t) {
        try {
            ITemperaturaDao temperaturaDAO = new TemperaturaDaoImp();
            temperaturaDAO.save(t);
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500, e.getMessage()))
                    .build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(t)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        ITemperaturaDao temperaturaDAO;
        temperaturaDAO = new TemperaturaDaoImp();
        Temperatura obj = temperaturaDAO.findById(id);
        if (obj == null) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }
        try {
            temperaturaDAO.delete(obj);
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500, e.getMessage()))
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(new OutputMessage(200, "Objeto removido."))
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Temperatura t) {
        try {
            ITemperaturaDao temperaturaDAO = new TemperaturaDaoImp();
            temperaturaDAO.save(t);
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500, e.getMessage()))
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(t)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listById(@PathParam("id") int id) {
        try {
            ITemperaturaDao temperaturaDAO = new TemperaturaDaoImp();
            Temperatura obj = temperaturaDAO.findById(id);
            if (obj == null) {
                return Response
                        .status(Response.Status.NO_CONTENT)
                        .build();
            } else {
                return Response
                        .status(Response.Status.OK)
                        .entity(obj)
                        .build();
            }
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500, e.getMessage()))
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(
            @QueryParam("orderby") @DefaultValue("id") String orderBy,
            @QueryParam("sort") @DefaultValue("asc") String sort) {
        try {
            ITemperaturaDao temperaturaDAO = new TemperaturaDaoImp();
            List<Temperatura> obj;
            if (sort.equals("desc")) {
                obj = temperaturaDAO.findAll(Order.desc(orderBy));
            } else {
                obj = temperaturaDAO.findAll(Order.asc(orderBy));
            }
            if (obj == null) {
                return Response
                        .status(Response.Status.NO_CONTENT)
                        .build();
            } else {
                return Response
                        .status(Response.Status.OK)
                        .entity(obj)
                        .build();
            }
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500, e.getMessage()))
                    .build();
        }
    }
}
