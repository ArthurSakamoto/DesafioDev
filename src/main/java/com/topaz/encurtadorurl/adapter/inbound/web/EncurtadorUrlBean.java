package com.topaz.encurtadorurl.adapter.inbound.web;

import com.topaz.encurtadorurl.core.domain.EncurtadorUrlDomain;
import com.topaz.encurtadorurl.core.ports.incoming.EncurtadorUrlUseCasePort;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class EncurtadorUrlBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EncurtadorUrlUseCasePort useCase;

    private String originalUrl;
    private String alias;
    private String urlGerada;

    public void encurtar() {
        try {
            EncurtadorUrlDomain resultado =
                    useCase.encurtadorUrl(
                            new EncurtadorUrlDomain(
                                    originalUrl,
                                    alias
                            )
                    );
            String baseUrl =
                    FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getRequestScheme()
                            + "://"
                            + FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getRequestServerName()
                            + ":"
                            + FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getRequestServerPort()
                            + FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getRequestContextPath();
            this.urlGerada = baseUrl + "/api/" + resultado.getAlias();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Sucesso!",
                            "URL encurtada com sucesso!"
                    )
            );
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Erro",
                            e.getMessage()
                    )
            );
        }
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

    public String getUrlGerada() {
        return urlGerada;
    }

    public void setUrlGerada(String urlGerada) {
        this.urlGerada = urlGerada;
    }
}