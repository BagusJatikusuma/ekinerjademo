package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 10/10/17.
 */
@Entity
@Table(name = "uraian_tugas_jabatan_jenis_urtug")
public class UraianTugasJabatanJenisUrtug {
    @EmbeddedId
    private UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(
                    name = "kd_urtug",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_urtug"),
            @JoinColumn(
                    name = "kd_jabatan",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_jabatan")
    })
    private UraianTugasJabatan uraianTugasJabatan;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "kd_jenis_urtug",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_jenis_urtug")
    private JenisUrtug jenisUrtug;

    public UraianTugasJabatanJenisUrtugId getUraianTugasJabatanJenisUrtugId() {
        return uraianTugasJabatanJenisUrtugId;
    }

    public void setUraianTugasJabatanJenisUrtugId(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId) {
        this.uraianTugasJabatanJenisUrtugId = uraianTugasJabatanJenisUrtugId;
    }

    public UraianTugasJabatan getUraianTugasJabatan() {
        return uraianTugasJabatan;
    }

    public void setUraianTugasJabatan(UraianTugasJabatan uraianTugasJabatan) {
        this.uraianTugasJabatan = uraianTugasJabatan;
    }

    public JenisUrtug getJenisUrtug() {
        return jenisUrtug;
    }

    public void setJenisUrtug(JenisUrtug jenisUrtug) {
        this.jenisUrtug = jenisUrtug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UraianTugasJabatanJenisUrtug that = (UraianTugasJabatanJenisUrtug) o;

        if (!uraianTugasJabatanJenisUrtugId.equals(that.uraianTugasJabatanJenisUrtugId)) return false;
        if (uraianTugasJabatan != null ? !uraianTugasJabatan.equals(that.uraianTugasJabatan) : that.uraianTugasJabatan != null)
            return false;
        return jenisUrtug != null ? jenisUrtug.equals(that.jenisUrtug) : that.jenisUrtug == null;
    }

    @Override
    public int hashCode() {
        int result = uraianTugasJabatanJenisUrtugId.hashCode();
        result = 31 * result + (uraianTugasJabatan != null ? uraianTugasJabatan.hashCode() : 0);
        result = 31 * result + (jenisUrtug != null ? jenisUrtug.hashCode() : 0);
        return result;
    }
}
