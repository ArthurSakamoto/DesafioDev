package com.topaz.encurtadorurl.core.dto.response;

import java.io.Serializable;

public class ResponseEncurtadorUrlDTO implements Serializable {

    private String originalUrl;
    private String alias;
    private String urlEncurtada;

    public ResponseEncurtadorUrlDTO(String originalUrl, String alias, String urlEncurtada) {
        this.originalUrl = originalUrl;
        this.alias = alias;
        this.urlEncurtada = urlEncurtada;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUrlEncurtada() {
        return urlEncurtada;
    }

    public void setUrlEncurtada(String urlEncurtada) {
        this.urlEncurtada = urlEncurtada;
    }
}
