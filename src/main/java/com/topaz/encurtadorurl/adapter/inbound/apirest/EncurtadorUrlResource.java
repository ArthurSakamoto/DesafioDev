package com.topaz.encurtadorurl.adapter.inbound.apirest;

import com.topaz.encurtadorurl.adapter.inbound.apirest.dto.request.RequestEncurtadorUrlDTO;
import com.topaz.encurtadorurl.adapter.inbound.apirest.dto.response.ResponseEncurtadorUrlDTO;
import com.topaz.encurtadorurl.adapter.inbound.apirest.mapper.request.EncurtadorRequestDomainMapper;
import com.topaz.encurtadorurl.adapter.inbound.apirest.mapper.response.EncurtadorResponseMapper;
import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import com.topaz.encurtadorurl.core.ports.incoming.EncurtadorUrlUseCasePort;
import com.topaz.encurtadorurl.exception.AliasAlreadyExistsException;
import com.topaz.encurtadorurl.exception.AliasNotFoundException;
import com.topaz.encurtadorurl.usecase.EncurtadorUrlUseCase;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/")
public class EncurtadorUrlResource {

    @Inject
    private EncurtadorUrlUseCasePort useCase;

    @Context
    private UriInfo uriInfo;

    @POST
    @Path("/encurtador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response encurtar(RequestEncurtadorUrlDTO request){
        if (request == null || request.getUrl() == null || request.getUrl().trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("A URL original é obrigatória!")
                    .build();
        }
        try{
            EncurtadorUrlDomain domain = EncurtadorRequestDomainMapper.mapearDomain(request);
            EncurtadorUrlDomain resultado = useCase.encurtadorUrl(domain);
            String urlEncurtada = uriInfo.getBaseUri().toString() + resultado.getAlias();

            ResponseEncurtadorUrlDTO response = EncurtadorResponseMapper.mapeadorResponse(resultado, urlEncurtada);
            return Response.status(Response.Status.CREATED)
                    .entity(response)
                    .build();
        } catch (AliasAlreadyExistsException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{alias}")
    public Response redirecionar(@PathParam("alias") String alias) {
        try {
            String originalUrl = useCase.getOriginalUrl(alias);
            return Response.seeOther(URI.create(originalUrl)).build();
        } catch (AliasNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }
}