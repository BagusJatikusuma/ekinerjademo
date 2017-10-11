package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 05/10/17.
 */
@Entity
@Table(name = "urtug_kegiatan")
public class UrtugKegiatan {
    @EmbeddedId
    private UrtugKegiatanId urtugKegiatanId;

    @ManyToOne(fetch = FetchType.LAZY)
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
                    referencedColumnName = "kd_jabatan"),
            @JoinColumn(
                    name = "kd_jenis_urtug",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "kd_jenis_urtug"),
            @JoinColumn(
                    name = "tahun_urtug",
                    insertable = false,
                    updatable = false,
                    referencedColumnName = "tahun_urtug")
    })
    private UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug;

    public UrtugKegiatanId getUrtugKegiatanId() {
        return urtugKegiatanId;
    }

    public void setUrtugKegiatanId(UrtugKegiatanId urtugKegiatanId) {
        this.urtugKegiatanId = urtugKegiatanId;
    }

    public UraianTugasJabatanJenisUrtug getUraianTugasJabatanJenisUrtug() {
        return uraianTugasJabatanJenisUrtug;
    }

    public void setUraianTugasJabatanJenisUrtug(UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug) {
        this.uraianTugasJabatanJenisUrtug = uraianTugasJabatanJenisUrtug;
    }
}
