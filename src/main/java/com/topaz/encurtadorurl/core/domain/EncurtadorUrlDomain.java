package com.topaz.encurtadorurl.core.domain;

public class EncurtadorUrlDomain {
    private String originalUrl;
    private String alias;

    public EncurtadorUrlDomain() {
    }

    public EncurtadorUrlDomain(String originalUrl, String alias) {
        this.originalUrl = originalUrl;
        this.alias = alias;
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
}
