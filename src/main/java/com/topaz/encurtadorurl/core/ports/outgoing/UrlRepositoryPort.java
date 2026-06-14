package com.topaz.encurtadorurl.core.ports.outgoing;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;

import java.util.Optional;

public interface UrlRepositoryPort {
    Optional<EncurtadorUrlDomain> findByAlias(String alias);
    EncurtadorUrlDomain save(EncurtadorUrlDomain domain);
}
