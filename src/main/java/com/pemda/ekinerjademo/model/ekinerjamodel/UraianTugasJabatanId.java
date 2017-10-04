package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by bagus on 08/09/17.
 */
@Embeddable
public class UraianTugasJabatanId implements Serializable {
    @Column(name = "kd_urtug")
    private String kdUrtug;

    @Column(name = "kd_jabatan")
    private String kdJabatan;

    @Column(name = "kd_jenis_urtug")
    private String kdJenisUrtug;

    public UraianTugasJabatanId() {}
    public UraianTugasJabatanId(String kdUrtug, String kdJabatan) {
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
    }
    public UraianTugasJabatanId(String kdUrtug, String kdJabatan, String kdJenisUrtug) {
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
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

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UraianTugasJabatanId)) return false;
        UraianTugasJabatanId that = (UraianTugasJabatanId) o;
        return Objects.equals(getKdUrtug(), that.getKdUrtug()) &&
                Objects.equals(getKdJabatan(), that.getKdJabatan()) &&
                Objects.equals(getKdJenisUrtug(), that.getKdJenisUrtug());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKdUrtug(), getKdJabatan(), getKdJenisUrtug());
    }
}
