package com.topaz.encurtadorurl.adapter.inbound.web;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import com.topaz.encurtadorurl.core.ports.incoming.EncurtadorUrlUseCasePort;
import com.topaz.encurtadorurl.exception.AliasAlreadyExistsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EncurtadorUrlBeanTest {

    @InjectMocks
    private EncurtadorUrlBean bean;

    @Mock
    private EncurtadorUrlUseCasePort useCase;

    @Mock
    private FacesContext facesContext;

    @Mock
    private ExternalContext externalContext;



    @Test
    public void deveTestarGettersESetters() {
        bean.setOriginalUrl("url");
        bean.setAlias("alias");
        bean.setUrlGerada("gerada");

        assertEquals("url", bean.getOriginalUrl());
        assertEquals("alias", bean.getAlias());
        assertEquals("gerada", bean.getUrlGerada());
    }
}