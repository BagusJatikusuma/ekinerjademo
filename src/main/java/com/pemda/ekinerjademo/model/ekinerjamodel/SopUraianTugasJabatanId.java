package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 08/10/17.
 */
@Embeddable
public class SopUraianTugasJabatanId implements Serializable {
    @Column(name = "kd_sop")
    private String kdSop;

    @Column(name = "kd_urtug")
    private String kdUrtug;

    @Column(name = "kd_jabatan")
    private String kdJabatan;

    public SopUraianTugasJabatanId() {
    }
    public SopUraianTugasJabatanId(
            String kdSop,
            String kdUrtug,
            String kdJabatan) {
        this.kdSop = kdSop;
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
    }

    public String getKdSop() {
        return kdSop;
    }

    public void setKdSop(String kdSop) {
        this.kdSop = kdSop;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SopUraianTugasJabatanId that = (SopUraianTugasJabatanId) o;

        if (!kdSop.equals(that.kdSop)) return false;
        if (!kdUrtug.equals(that.kdUrtug)) return false;
        return kdJabatan.equals(that.kdJabatan);
    }

    @Override
    public int hashCode() {
        int result = kdSop.hashCode();
        result = 31 * result + kdUrtug.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        return result;
    }
}
