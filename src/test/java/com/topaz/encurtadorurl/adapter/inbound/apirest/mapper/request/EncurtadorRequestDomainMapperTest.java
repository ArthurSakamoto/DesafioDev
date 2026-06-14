package com.topaz.encurtadorurl.adapter.inbound.apirest.mapper.request;

import com.topaz.encurtadorurl.adapter.inbound.apirest.dto.request.RequestEncurtadorUrlDTO;
import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import org.junit.Test;

import static org.junit.Assert.*;

public class EncurtadorRequestDomainMapperTest {

    @Test
    public void deveMapearRequestParaDomain() {
        RequestEncurtadorUrlDTO dto =
                new RequestEncurtadorUrlDTO(
                        "https://google.com",
                        "google"
                );
        EncurtadorUrlDomain domain =
                EncurtadorRequestDomainMapper.mapearDomain(dto);
        assertNotNull(domain);
        assertEquals("https://google.com", domain.getOriginalUrl());
        assertEquals("google", domain.getAlias());
    }

    @Test
    public void deveRetornarNullQuandoRequestForNull() {
        EncurtadorUrlDomain domain = EncurtadorRequestDomainMapper.mapearDomain(null);
        assertNull(domain);
    }
}
