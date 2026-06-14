package com.topaz.encurtadorurl.adapter.inbound.apirest.dto.request;

import org.junit.Test;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertEquals;

public class RequestEncurtadorUrlDTOTest {
    @Test
    public void deveCriarObjetoComConstrutorVazio() {
        RequestEncurtadorUrlDTO request = new RequestEncurtadorUrlDTO();
        assertNotNull(request);
        assertNull(request.getUrl());
        assertNull(request.getAlias());
    }

    @Test
    public void deveCriarObjetoComConstrutorCompleto() {
        RequestEncurtadorUrlDTO request =
                new RequestEncurtadorUrlDTO(
                        "https://google.com",
                        "google"
                );
        assertEquals("https://google.com", request.getUrl());
        assertEquals("google", request.getAlias());
    }

    @Test
    public void deveDefinirOriginalUrl() {
        RequestEncurtadorUrlDTO request = new RequestEncurtadorUrlDTO();
        request.setUrl("https://topaz.com.br");
        assertEquals("https://topaz.com.br", request.getUrl());
    }

    @Test
    public void deveDefinirAlias() {
        RequestEncurtadorUrlDTO request = new RequestEncurtadorUrlDTO();
        request.setAlias("topaz");
        assertEquals("topaz", request.getAlias());
    }
}
