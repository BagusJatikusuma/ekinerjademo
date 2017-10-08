package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bagus on 08/10/17.
 */
@Entity
@Table(name = "template")
public class Template {
    @Id
    @Column(name = "kd_template")
    private String kdTemplate;
    @Column(name = "nm_template")
    private String nmTemplate;
    @Column(name = "url")
    private String url;

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
