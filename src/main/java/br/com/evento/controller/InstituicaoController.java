package br.com.evento.controller;

import br.com.evento.entity.Instituicao;
import br.com.evento.entity.PageRequest;
import br.com.evento.service.InstituicaoService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@Path("/v1/instituicoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstituicaoController {

    @Inject
    InstituicaoService service;

    @GET
    @Path("/{id}")
    public Instituicao findById(@PathParam("id") Integer id) {
        return service.findById(id);
    }

    @GET
    @Path("/page")
    public List<Instituicao> pageOf(@BeanParam PageRequest pageRequest) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        return service.pageOf(pageRequest);
    }

    @POST
    public Response persist(@Valid Instituicao request) {
        service.create(request);
        return Response.status(CREATED).build();
    }

    @PUT
    @Path("/full/{id}")
    public Response update(@PathParam("id") Integer id, @Valid Instituicao request) {
        service.update(id, request);
        return Response.status(NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        service.delete(id);
        return Response.status(NO_CONTENT).build();
    }
}
