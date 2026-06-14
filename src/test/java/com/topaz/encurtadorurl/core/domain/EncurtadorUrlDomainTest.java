package com.topaz.encurtadorurl.core.domain;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class EncurtadorUrlDomainTest {

    @Test
    public void deveCriarObjetoComConstrutorVazio() {
        EncurtadorUrlDomain domain = new EncurtadorUrlDomain();
        assertNotNull(domain);
        assertNull(domain.getOriginalUrl());
        assertNull(domain.getAlias());
    }

    @Test
    public void deveCriarObjetoComConstrutorCompleto() {
        EncurtadorUrlDomain domain =
                new EncurtadorUrlDomain(
                        "https://google.com",
                        "google"
                );
        assertEquals("https://google.com", domain.getOriginalUrl());
        assertEquals("google", domain.getAlias());
    }

    @Test
    public void deveDefinirOriginalUrl() {
        EncurtadorUrlDomain domain = new EncurtadorUrlDomain();
        domain.setOriginalUrl("https://topaz.com.br");
        assertEquals("https://topaz.com.br", domain.getOriginalUrl());
    }

    @Test
    public void deveDefinirAlias() {
        EncurtadorUrlDomain domain = new EncurtadorUrlDomain();
        domain.setAlias("topaz");
        assertEquals("topaz", domain.getAlias());
    }
}
