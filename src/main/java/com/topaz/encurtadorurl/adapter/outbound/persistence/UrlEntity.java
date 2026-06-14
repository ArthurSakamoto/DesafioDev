package com.topaz.encurtadorurl.adapter.outbound.persistence;

import javax.persistence.*;

@Entity
@Table(name = "urls")
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String orginalUrl;

    @Column(nullable = false, unique = true)
    private String alias;

    public UrlEntity() {
    }

    public String getOrginalUrl() {
        return orginalUrl;
    }

    public void setOrginalUrl(String orginalUrl) {
        this.orginalUrl = orginalUrl;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
