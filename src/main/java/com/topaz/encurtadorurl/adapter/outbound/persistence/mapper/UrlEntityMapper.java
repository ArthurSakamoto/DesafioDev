package com.topaz.encurtadorurl.adapter.outbound.persistence.mapper;

import com.topaz.encurtadorurl.adapter.outbound.persistence.UrlEntity;
import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;

public class UrlEntityMapper {
    private UrlEntityMapper() {
    }

    public static UrlEntity mapeadorEntity(EncurtadorUrlDomain domain) {

        UrlEntity entity = new UrlEntity();
        entity.setOriginalUrl(domain.getOriginalUrl());
        entity.setAlias(domain.getAlias());

        return entity;
    }

    public static EncurtadorUrlDomain toDomain(UrlEntity entity) {

        return new EncurtadorUrlDomain(
                entity.getOriginalUrl(),
                entity.getAlias()
        );
    }
}
