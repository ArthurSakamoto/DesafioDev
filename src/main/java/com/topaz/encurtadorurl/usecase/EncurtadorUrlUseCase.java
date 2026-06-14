package com.topaz.encurtadorurl.usecase;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import com.topaz.encurtadorurl.core.ports.incoming.EncurtadorUrlUseCasePort;
import com.topaz.encurtadorurl.core.ports.outgoing.UrlRepositoryPort;

import java.util.UUID;

public class EncurtadorUrlUseCase implements EncurtadorUrlUseCasePort {

    private final UrlRepositoryPort repository;

    public EncurtadorUrlUseCase(UrlRepositoryPort repository){
        this.repository = repository;
    }

    @Override
    public EncurtadorUrlDomain encurtadorUrl(EncurtadorUrlDomain domain) {
        if (domain.getAlias() != null && !domain.getAlias().trim().isEmpty()) {
            String cleanAlias = domain.getAlias().trim();
            if (repository.findByAlias(cleanAlias).isPresent()){
                throw new IllegalArgumentException("O alias " + cleanAlias + " já está em uso!");
            }
            return repository.save(new EncurtadorUrlDomain(domain.getOriginalUrl(), cleanAlias));
        }

        String geradorAlias;
        do{
            geradorAlias = UUID.randomUUID().toString().substring(0, 6);
        } while (repository.findByAlias(geradorAlias).isPresent());

        return repository.save(new EncurtadorUrlDomain(domain.getOriginalUrl(), geradorAlias));
    }

    @Override
    public String getOriginalUrl(String alias) {
        return repository.findByAlias(alias)
                .map(EncurtadorUrlDomain::getOriginalUrl)
                .orElseThrow(() -> new IllegalArgumentException("URL encurtada inválida!"));
    }
}
