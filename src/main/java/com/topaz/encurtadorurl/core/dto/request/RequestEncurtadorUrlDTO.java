package com.topaz.encurtadorurl.core.dto.request;

public class RequestEncurtadorUrlDTO {
    private String url;
    private String alias;

    public RequestEncurtadorUrlDTO() {
    }

    public RequestEncurtadorUrlDTO(String url, String alias) {
        this.url = url;
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
