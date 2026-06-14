package com.topaz.encurtadorurl.adapter.inbound.apirest.mapper.response;

import com.topaz.encurtadorurl.adapter.inbound.apirest.dto.response.ResponseEncurtadorUrlDTO;
import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EncurtadorResponseMapperTest {

    @Test
    public void deveMapearDomainParaResponse() {

        EncurtadorUrlDomain domain =
                new EncurtadorUrlDomain(
                        "https://google.com",
                        "google"
                );

        ResponseEncurtadorUrlDTO response =
                EncurtadorResponseMapper.mapeadorResponse(
                        domain,
                        "http://localhost:8080/api/google"
                );
        assertNotNull(response);
        assertEquals("https://google.com", response.getOriginalUrl());
        assertEquals("google", response.getAlias());
        assertEquals("http://localhost:8080/api/google", response.getUrlEncurtada());
    }
}
