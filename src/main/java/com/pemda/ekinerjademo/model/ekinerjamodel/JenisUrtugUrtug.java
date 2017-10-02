package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bagus on 26/09/17.
 */
@Entity
@Table(name = "jenis_urtug_urtug")
public class JenisUrtugUrtug implements Serializable {
    @EmbeddedId
    private JenisUrtugUrtugId jenisUrtugUrtugId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kd_jenis_urtug", insertable = false, updatable = false)
    private JenisUrtug jenisUrtug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(
                    name = "kd_urtug",
                    referencedColumnName = "kd_urtug",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "kd_jabatan",
                    referencedColumnName = "kd_jabatan",
                    insertable = false,
                    updatable = false)
    })
    private UraianTugasJabatan uraianTugasJabatan;


    public JenisUrtugUrtugId getJenisUrtugUrtugId() {
        return jenisUrtugUrtugId;
    }

    public void setJenisUrtugUrtugId(JenisUrtugUrtugId jenisUrtugUrtugId) {
        this.jenisUrtugUrtugId = jenisUrtugUrtugId;
    }

    public JenisUrtug getJenisUrtug() {
        return jenisUrtug;
    }

    public void setJenisUrtug(JenisUrtug jenisUrtug) {
        this.jenisUrtug = jenisUrtug;
    }

    public UraianTugasJabatan getUraianTugasJabatan() {
        return uraianTugasJabatan;
    }

    public void setUraianTugasJabatan(UraianTugasJabatan uraianTugasJabatan) {
        this.uraianTugasJabatan = uraianTugasJabatan;
    }
}
