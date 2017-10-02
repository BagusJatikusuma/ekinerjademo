package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 26/09/17.
 */
@Embeddable
public class SopJenisUrtugUrtugId implements Serializable {
    @Column(name = "kd_sop")
    private String kdSop;

    @Column(name = "kd_jenis_urtug")
    private String kdJenisUrtug;

    @Column(name = "kd_urtug")
    private String kdUrtug;

    @Column(name = "kd_jabatan")
    private String kdJabatan;


    public SopJenisUrtugUrtugId() {}
    public SopJenisUrtugUrtugId(String kdSop, String kdJenisUrtug, String kdUrtug, String kdJabatan) {
        this.kdSop = kdSop;
        this.kdJenisUrtug = kdJenisUrtug;
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
    }

    public String getKdSop() {
        return kdSop;
    }

    public void setKdSop(String kdSop) {
        this.kdSop = kdSop;
    }

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
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

        SopJenisUrtugUrtugId that = (SopJenisUrtugUrtugId) o;

        if (!kdSop.equals(that.kdSop)) return false;
        if (!kdJenisUrtug.equals(that.kdJenisUrtug)) return false;
        if (!kdUrtug.equals(that.kdUrtug)) return false;
        return kdJabatan.equals(that.kdJabatan);
    }

    @Override
    public int hashCode() {
        int result = kdSop.hashCode();
        result = 31 * result + kdJenisUrtug.hashCode();
        result = 31 * result + kdUrtug.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        return result;
    }

}
