package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 08/10/17.
 */
@Entity
@Table(name = "sop_template")
public class SopTemplate {
    @EmbeddedId
    private SopTemplateId sopTemplateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_sop",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_sop")
    private Sop sop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_template",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_template")
    private Template template;

    public SopTemplateId getSopTemplateId() {
        return sopTemplateId;
    }

    public void setSopTemplateId(SopTemplateId sopTemplateId) {
        this.sopTemplateId = sopTemplateId;
    }

    public Sop getSop() {
        return sop;
    }

    public void setSop(Sop sop) {
        this.sop = sop;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
