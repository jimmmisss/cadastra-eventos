package br.com.evento.controller;

import br.com.evento.entity.Evento;
import br.com.evento.entity.PageRequest;
import br.com.evento.entity.dto.EventoDto;
import br.com.evento.service.EventoService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@Path("/v1/eventos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventoController {

    @Inject
    EventoService service;

    @GET
    @Path("/{id}")
    public Evento findById(@PathParam("id") Integer id) {
        return service.findById(id);
    }

    @GET
    @Path("/page")
    public List<Evento> pageOf(@BeanParam PageRequest pageRequest) {
        return service.pageOf(pageRequest);
    }

    @POST
    public Response persist(@Valid EventoDto eventoDto) {
        service.create(eventoDto);
        return Response.status(CREATED).build();
    }

    @PUT
    @Path("/full/{id}")
    public Response update(@PathParam("id") Integer id, @Valid EventoDto eventoDto) {
        service.update(id, eventoDto);
        return Response.status(NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        service.delete(id);
        return Response.status(NO_CONTENT).build();
    }
}
