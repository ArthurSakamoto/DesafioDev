package com.topaz.encurtadorurl.adapter.outbound.persistence;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UrlEntityTest {

    @Test
    public void deveDefinirEObterValoresCorretamente() {
        UrlEntity entity = new UrlEntity();

        entity.setOriginalUrl("https://google.com");
        entity.setAlias("ggl");

        assertEquals("https://google.com", entity.getOriginalUrl());
        assertEquals("ggl", entity.getAlias());
    }
}
