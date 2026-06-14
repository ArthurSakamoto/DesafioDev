package com.topaz.encurtadorurl.core.mapper.response;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import com.topaz.encurtadorurl.core.dto.response.ResponseEncurtadorUrlDTO;

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
