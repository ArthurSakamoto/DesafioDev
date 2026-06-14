package com.topaz.encurtadorurl.adapter.outbound.persistence.mapper;

import com.topaz.encurtadorurl.adapter.outbound.persistence.UrlEntity;
import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import org.junit.Test;
import static org.junit.Assert.*;

public class UrlEntityMapperTest {

    @Test
    public void deveMapearDomainParaEntity() {
        EncurtadorUrlDomain domain =
                new EncurtadorUrlDomain(
                        "https://google.com",
                        "google"
                );
        UrlEntity entity = UrlEntityMapper.mapeadorEntity(domain);
        assertNotNull(entity);
        assertEquals("https://google.com", entity.getOriginalUrl());
        assertEquals("google", entity.getAlias());
    }

    @Test
    public void deveMapearEntityParaDomain() {
        UrlEntity entity = new UrlEntity();
        entity.setOriginalUrl("https://google.com");
        entity.setAlias("google");
        EncurtadorUrlDomain domain = UrlEntityMapper.toDomain(entity);
        assertNotNull(domain);
        assertEquals("https://google.com", domain.getOriginalUrl());
        assertEquals("google", domain.getAlias());
    }
}
