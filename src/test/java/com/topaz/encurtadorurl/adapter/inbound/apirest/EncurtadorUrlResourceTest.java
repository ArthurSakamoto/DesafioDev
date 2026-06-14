package com.topaz.encurtadorurl.adapter.inbound.apirest;

import com.topaz.encurtadorurl.adapter.inbound.apirest.dto.request.RequestEncurtadorUrlDTO;
import com.topaz.encurtadorurl.adapter.inbound.apirest.dto.response.ResponseEncurtadorUrlDTO;
import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import com.topaz.encurtadorurl.core.ports.incoming.EncurtadorUrlUseCasePort;
import com.topaz.encurtadorurl.exception.AliasAlreadyExistsException;
import com.topaz.encurtadorurl.exception.AliasNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.RuntimeDelegate;

import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EncurtadorUrlResourceTest {

    @InjectMocks
    private EncurtadorUrlResource resource;

    @Mock
    private EncurtadorUrlUseCasePort useCase;

    @Mock
    private UriInfo uriInfo;

    @Before
    public void setup() {

        RuntimeDelegate.setInstance(
                new org.glassfish.jersey.internal.RuntimeDelegateImpl()
        );
    }

    @Test
    public void deveRetornarBadRequestQuandoUrlForNula() {
        RequestEncurtadorUrlDTO request = new RequestEncurtadorUrlDTO();
        Response response = resource.encurtar(request);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("A URL original é obrigatória!", response.getEntity());
    }

    @Test
    public void deveEncurtarUrlComSucesso() {
        RequestEncurtadorUrlDTO request =
                new RequestEncurtadorUrlDTO(
                        "https://google.com",
                        "google"
                );
        EncurtadorUrlDomain domain =
                new EncurtadorUrlDomain(
                        "https://google.com",
                        "google"
                );

        when(useCase.encurtadorUrl(any(EncurtadorUrlDomain.class)))
                .thenReturn(domain);
        URI baseUri = URI.create("http://localhost:8080/api/");
        when(uriInfo.getBaseUri()).thenReturn(baseUri);
        Response response = resource.encurtar(request);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        ResponseEncurtadorUrlDTO dto = (ResponseEncurtadorUrlDTO) response.getEntity();

        assertEquals("https://google.com", dto.getOriginalUrl());
        assertEquals("google", dto.getAlias());
        assertEquals("http://localhost:8080/api/google", dto.getUrlEncurtada());
    }

    @Test
    public void deveRetornarConflictQuandoAliasJaExiste() {
        RequestEncurtadorUrlDTO request =
                new RequestEncurtadorUrlDTO(
                        "https://google.com",
                        "google"
                );

        when(useCase.encurtadorUrl(any(EncurtadorUrlDomain.class))).thenThrow(new AliasAlreadyExistsException());

        Response response = resource.encurtar(request);
        assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
        assertEquals("Alias já existe no banco de dados", response.getEntity());
    }

    @Test
    public void deveRedirecionarParaUrlOriginal() {
        when(useCase.getOriginalUrl("google")).thenReturn("https://google.com");
        Response response = resource.redirecionar("google");

        assertEquals(Response.Status.SEE_OTHER.getStatusCode(), response.getStatus());
        assertEquals(URI.create("https://google.com"), response.getLocation());
    }

    @Test
    public void deveRetornarNotFoundQuandoAliasNaoExiste() {
        when(useCase.getOriginalUrl("google")).thenThrow(new AliasNotFoundException());
        Response response = resource.redirecionar("google");
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals("Alias não encontrado", response.getEntity());
    }
}
