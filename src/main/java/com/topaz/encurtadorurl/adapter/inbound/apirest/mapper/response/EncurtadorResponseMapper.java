package com.topaz.encurtadorurl.adapter.inbound.apirest.mapper.response;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import com.topaz.encurtadorurl.adapter.inbound.apirest.dto.response.ResponseEncurtadorUrlDTO;

public class EncurtadorResponseMapper {

    private EncurtadorResponseMapper(){

    }

    public static ResponseEncurtadorUrlDTO toResponse(
            EncurtadorUrlDomain domain,
            String urlEncurtada) {

        ResponseEncurtadorUrlDTO response = new ResponseEncurtadorUrlDTO();
        response.setAlias(domain.getAlias());
        response.setOriginalUrl(domain.getOriginalUrl());
        response.setUrlEncurtada(urlEncurtada);

        return response;
    }
}
