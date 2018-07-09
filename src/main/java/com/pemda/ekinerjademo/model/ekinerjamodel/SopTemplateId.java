package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 08/10/17.
 */
@Embeddable
public class SopTemplateId implements Serializable {
    @Column(name = "kd_sop")
    private String kdSop;
    @Column(name = "kd_template")
    private String kdTemplate;

    public SopTemplateId() {
    }
    public SopTemplateId(String kdSop, String kdTemplate) {
        this.kdSop = kdSop;
        this.kdTemplate = kdTemplate;
    }

    public String getKdSop() {
        return kdSop;
    }

    public void setKdSop(String kdSop) {
        this.kdSop = kdSop;
    }

    public String getKdTemplate() {
        return kdTemplate;
    }

    public void setKdTemplate(String kdTemplate) {
        this.kdTemplate = kdTemplate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SopTemplateId that = (SopTemplateId) o;

        if (!kdSop.equals(that.kdSop)) return false;
        return kdTemplate.equals(that.kdTemplate);
    }

    @Override
    public int hashCode() {
        int result = kdSop.hashCode();
        result = 31 * result + kdTemplate.hashCode();
        return result;
    }
}
