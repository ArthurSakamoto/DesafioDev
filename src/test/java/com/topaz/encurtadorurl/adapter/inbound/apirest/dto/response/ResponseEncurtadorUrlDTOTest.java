package com.topaz.encurtadorurl.adapter.inbound.apirest.dto.response;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseEncurtadorUrlDTOTest {

    @Test
    public void deveCriarResponseComConstrutorVazio() {
        ResponseEncurtadorUrlDTO dto = new ResponseEncurtadorUrlDTO();
        assertNotNull(dto);
        assertNull(dto.getOriginalUrl());
        assertNull(dto.getAlias());
        assertNull(dto.getUrlEncurtada());
    }

    @Test
    public void deveCriarResponseComConstrutorCompleto() {
        ResponseEncurtadorUrlDTO dto = new ResponseEncurtadorUrlDTO(
                        "https://google.com",
                        "google",
                        "http://localhost:8080/api/google"
                );
        assertEquals("https://google.com", dto.getOriginalUrl());
        assertEquals("google", dto.getAlias());
        assertEquals("http://localhost:8080/api/google", dto.getUrlEncurtada());
    }

    @Test
    public void deveAlterarOriginalUrl() {
        ResponseEncurtadorUrlDTO dto = new ResponseEncurtadorUrlDTO();
        dto.setOriginalUrl("https://topaz.com.br");
        assertEquals("https://topaz.com.br", dto.getOriginalUrl());
    }

    @Test
    public void deveAlterarAlias() {
        ResponseEncurtadorUrlDTO dto = new ResponseEncurtadorUrlDTO();
        dto.setAlias("topaz");
        assertEquals("topaz", dto.getAlias());
    }

    @Test
    public void deveAlterarUrlEncurtada() {
        ResponseEncurtadorUrlDTO dto = new ResponseEncurtadorUrlDTO();
        dto.setUrlEncurtada("http://localhost:8080/api/topaz");
        assertEquals("http://localhost:8080/api/topaz", dto.getUrlEncurtada());
    }
}
