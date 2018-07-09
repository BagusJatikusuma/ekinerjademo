package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 08/10/17.
 */
public class TemplateWrapper {
    private String kdTemplate;
    private String nmTemplate;
    private String url;

    public TemplateWrapper() {
    }
    public TemplateWrapper(String kdTemplate, String nmTemplate, String url) {
        this.kdTemplate = kdTemplate;
        this.nmTemplate = nmTemplate;
        this.url = url;
    }

    public String getKdTemplate() {
        return kdTemplate;
    }

    public void setKdTemplate(String kdTemplate) {
        this.kdTemplate = kdTemplate;
    }

    public String getNmTemplate() {
        return nmTemplate;
    }

    public void setNmTemplate(String nmTemplate) {
        this.nmTemplate = nmTemplate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
