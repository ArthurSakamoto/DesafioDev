package com.topaz.encurtadorurl.core.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestEncurtadorUrlDTO {

    @NotNull
    private String url;

    @Size(max = 50)
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
