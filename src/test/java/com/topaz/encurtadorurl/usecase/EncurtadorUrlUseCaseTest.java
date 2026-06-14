package com.topaz.encurtadorurl.usecase;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import com.topaz.encurtadorurl.core.ports.outgoing.UrlRepositoryPort;
import com.topaz.encurtadorurl.exception.AliasAlreadyExistsException;
import com.topaz.encurtadorurl.exception.AliasNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EncurtadorUrlUseCaseTest {

    private UrlRepositoryPort repository;
    private EncurtadorUrlUseCase useCase;

    @Before
    public void setup() {
        repository = Mockito.mock(UrlRepositoryPort.class);
        useCase = new EncurtadorUrlUseCase(repository);
    }

    @Test
    public void deveSalvarQuandoAliasInformado() {

        EncurtadorUrlDomain domain =
                new EncurtadorUrlDomain(
                        "https://google.com",
                        "google"
                );

        when(repository.findByAlias("google"))
                .thenReturn(Optional.empty());

        when(repository.save(any(EncurtadorUrlDomain.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EncurtadorUrlDomain resultado =
                useCase.encurtadorUrl(domain);

        assertEquals("google", resultado.getAlias());
        assertEquals("https://google.com", resultado.getOriginalUrl());

        verify(repository).save(any(EncurtadorUrlDomain.class));
    }

    @Test(expected = AliasAlreadyExistsException.class)
    public void deveLancarExcecaoQuandoAliasExiste() {

        EncurtadorUrlDomain domain =
                new EncurtadorUrlDomain(
                        "https://google.com",
                        "google"
                );

        when(repository.findByAlias("google"))
                .thenReturn(Optional.of(domain));

        useCase.encurtadorUrl(domain);
    }

    @Test
    public void deveGerarAliasAutomaticamente() {

        EncurtadorUrlDomain domain =
                new EncurtadorUrlDomain(
                        "https://google.com",
                        null
                );

        when(repository.findByAlias(anyString()))
                .thenReturn(Optional.empty());

        when(repository.save(any(EncurtadorUrlDomain.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EncurtadorUrlDomain resultado =
                useCase.encurtadorUrl(domain);

        assertNotNull(resultado.getAlias());

        assertFalse(resultado.getAlias().isEmpty());

        assertEquals(
                "https://google.com",
                resultado.getOriginalUrl()
        );
    }

    @Test
    public void deveRetornarUrlOriginal() {

        EncurtadorUrlDomain domain =
                new EncurtadorUrlDomain(
                        "https://google.com",
                        "google"
                );

        when(repository.findByAlias("google"))
                .thenReturn(Optional.of(domain));

        String resultado =
                useCase.getOriginalUrl("google");

        assertEquals(
                "https://google.com",
                resultado
        );
    }

    @Test(expected = AliasNotFoundException.class)
    public void deveLancarExcecaoQuandoAliasNaoExiste() {

        when(repository.findByAlias("inexistente"))
                .thenReturn(Optional.empty());

        useCase.getOriginalUrl("inexistente");
    }
}
