package com.topaz.encurtadorurl.adapter.outbound.persistence;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import com.topaz.encurtadorurl.core.ports.outgoing.UrlRepositoryPort;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class JpaUrlRepository implements UrlRepositoryPort {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<EncurtadorUrlDomain> findByAlias(String alias) {
        try{
            UrlEntity entity = em.createQuery(
                    "SELECT u FROM UrlEntity u WHERE u.alias = :alias",
                    UrlEntity.class).setParameter("alias", alias)
                    .getSingleResult();
            return Optional.of(
                    new EncurtadorUrlDomain(
                            entity.getOrginalUrl(),
                            entity.getAlias()
                    )
            );
        } catch (javax.persistence.NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public EncurtadorUrlDomain save(EncurtadorUrlDomain domain) {
        UrlEntity entity = new UrlEntity();
        entity.setOrginalUrl(domain.getOriginalUrl());
        entity.setAlias(domain.getAlias());
        em.persist(entity);
        return domain;
    }
}
