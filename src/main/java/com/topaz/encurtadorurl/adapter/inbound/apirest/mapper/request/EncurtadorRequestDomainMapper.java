package com.topaz.encurtadorurl.adapter.inbound.apirest.mapper.request;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import com.topaz.encurtadorurl.adapter.inbound.apirest.dto.request.RequestEncurtadorUrlDTO;

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
