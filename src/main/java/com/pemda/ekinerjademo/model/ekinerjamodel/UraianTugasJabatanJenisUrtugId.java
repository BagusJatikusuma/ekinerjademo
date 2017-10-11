package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 10/10/17.
 */
@Embeddable
public class UraianTugasJabatanJenisUrtugId implements Serializable {
    @Column(name = "kd_urtug")
    private String kdUrtug;

    @Column(name = "kd_jabatan")
    private String kdJabatan;

    @Column(name = "kd_jenis_urtug")
    private String kdJenisUrtug;

    @Column(name = "tahun_urtug")
    private Integer tahunUrtug;

    public UraianTugasJabatanJenisUrtugId() {
    }
    public UraianTugasJabatanJenisUrtugId(
            String kdUrtug,
            String kdJabatan,
            String kdJenisUrtug,
            Integer tahunUrtug) {
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
        this.kdJenisUrtug = kdJenisUrtug;
        this.tahunUrtug = tahunUrtug;
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

    public Integer getTahunUrtug() {
        return tahunUrtug;
    }

    public void setTahunUrtug(Integer tahunUrtug) {
        this.tahunUrtug = tahunUrtug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UraianTugasJabatanJenisUrtugId that = (UraianTugasJabatanJenisUrtugId) o;

        if (!kdUrtug.equals(that.kdUrtug)) return false;
        if (!kdJabatan.equals(that.kdJabatan)) return false;
        if (!kdJenisUrtug.equals(that.kdJenisUrtug)) return false;
        return tahunUrtug.equals(that.tahunUrtug);
    }

    @Override
    public int hashCode() {
        int result = kdUrtug.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        result = 31 * result + kdJenisUrtug.hashCode();
        result = 31 * result + tahunUrtug.hashCode();
        return result;
    }

}
