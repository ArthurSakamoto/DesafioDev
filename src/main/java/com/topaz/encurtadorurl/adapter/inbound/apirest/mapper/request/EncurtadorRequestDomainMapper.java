package com.topaz.encurtadorurl.core.mapper.request;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import com.topaz.encurtadorurl.core.dto.request.RequestEncurtadorUrlDTO;

public class EncurtadorRequestDomainMapper {

    private EncurtadorRequestDomainMapper(){

    }

    public static EncurtadorUrlDomain mapearDomain(RequestEncurtadorUrlDTO request) {

        if (request == null) {
            return null;
        }

        return new EncurtadorUrlDomain(
                request.getUrl(),
                request.getAlias()
        );
    }
}
