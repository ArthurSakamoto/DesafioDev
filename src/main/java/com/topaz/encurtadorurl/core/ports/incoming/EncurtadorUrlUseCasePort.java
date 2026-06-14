package com.topaz.encurtadorurl.core.ports.incoming;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;

public interface EncurtadorUrlUseCasePort {
    EncurtadorUrlDomain encurtadorUrl(EncurtadorUrlDomain domain);
    String getOriginalUrl(String alias);
}
