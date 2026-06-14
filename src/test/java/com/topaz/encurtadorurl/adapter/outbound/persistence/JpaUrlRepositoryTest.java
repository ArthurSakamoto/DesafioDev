package com.topaz.encurtadorurl.adapter.outbound.persistence;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JpaUrlRepositoryTest {

    @InjectMocks
    private JpaUrlRepository repository;

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<UrlEntity> query;

    @Test
    public void deveSalvarDomain() {
        EncurtadorUrlDomain domain = new EncurtadorUrlDomain(
                "https://google.com",
                "google"
        );

        repository.save(domain);

        ArgumentCaptor<UrlEntity> captor = ArgumentCaptor.forClass(UrlEntity.class);
        verify(em).persist(captor.capture());
        UrlEntity entity = captor.getValue();

        assertEquals("https://google.com", entity.getOriginalUrl());
        assertEquals("google", entity.getAlias());
    }

    @Test
    public void deveEncontrarAlias() {
        UrlEntity entity = new UrlEntity();

        entity.setOriginalUrl("https://google.com");
        entity.setAlias("google");

        when(em.createQuery(anyString(), eq(UrlEntity.class))).thenReturn(query);
        when(query.setParameter(eq("alias"), eq("google"))).thenReturn(query);
        when(query.getSingleResult()).thenReturn(entity);

        Optional<EncurtadorUrlDomain> resultado = repository.findByAlias("google");

        assertTrue(resultado.isPresent());
        assertEquals("https://google.com", resultado.get().getOriginalUrl());

        assertEquals("google", resultado.get().getAlias());
    }

    @Test
    public void deveRetornarOptionalVazioQuandoNaoEncontrarAlias() {
        when(em.createQuery(anyString(), eq(UrlEntity.class))).thenReturn(query);
        when(query.setParameter(eq("alias"), eq("google"))).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new NoResultException());

        Optional<EncurtadorUrlDomain> resultado = repository.findByAlias("google");

        assertFalse(resultado.isPresent());
    }
}
