package com.topaz.encurtadorurl.config;

import com.topaz.encurtadorurl.core.ports.incoming.EncurtadorUrlUseCasePort;
import com.topaz.encurtadorurl.core.ports.outgoing.UrlRepositoryPort;
import com.topaz.encurtadorurl.usecase.EncurtadorUrlUseCase;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@Dependent
public class CoreConfig {

    @Inject
    private UrlRepositoryPort repositoryPort;

    @Produces
    @ApplicationScoped
    public EncurtadorUrlUseCasePort encurtadorUrlUseCase(){
        return new EncurtadorUrlUseCase(repositoryPort);
    }
}
