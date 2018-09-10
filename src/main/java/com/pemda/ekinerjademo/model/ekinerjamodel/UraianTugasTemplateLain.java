package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

@Entity
@Table(name = "uraian_tugas_template_lain")
public class UraianTugasTemplateLain {
    @EmbeddedId
    private UraianTugasTemplateLainId uraianTugasTemplateLainId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_template_lain",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_template_lain")
    private TemplateLain templateLain;

    public UraianTugasTemplateLainId getUraianTugasTemplateLainId() {
        return uraianTugasTemplateLainId;
    }

    public void setUraianTugasTemplateLainId(UraianTugasTemplateLainId uraianTugasTemplateLainId) {
        this.uraianTugasTemplateLainId = uraianTugasTemplateLainId;
    }

    public TemplateLain getTemplateLain() {
        return templateLain;
    }

    public void setTemplateLain(TemplateLain templateLain) {
        this.templateLain = templateLain;
    }
}
