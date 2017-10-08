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

    @Column(name = "kd_jenis_urtug")
    private String kdJenisUrtug;

    public SopUraianTugasJabatanId() {
    }
    public SopUraianTugasJabatanId(
            String kdSop,
            String kdUrtug,
            String kdJabatan,
            String kdJenisUrtug) {
        this.kdSop = kdSop;
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
        this.kdJenisUrtug = kdJenisUrtug;
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

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SopUraianTugasJabatanId that = (SopUraianTugasJabatanId) o;

        if (!kdSop.equals(that.kdSop)) return false;
        if (!kdUrtug.equals(that.kdUrtug)) return false;
        if (!kdJabatan.equals(that.kdJabatan)) return false;
        return kdJenisUrtug.equals(that.kdJenisUrtug);
    }

    @Override
    public int hashCode() {
        int result = kdSop.hashCode();
        result = 31 * result + kdUrtug.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        result = 31 * result + kdJenisUrtug.hashCode();
        return result;
    }

}
