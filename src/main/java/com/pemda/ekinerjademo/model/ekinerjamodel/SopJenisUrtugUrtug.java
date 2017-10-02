package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 26/09/17.
 */
@Entity
@Table(name = "sop_jenis_urtug_urtug")
public class SopJenisUrtugUrtug {
    @EmbeddedId
    private SopJenisUrtugUrtugId sopJenisUrtugUrtugId;

    @ManyToOne
    @JoinColumn(name = "kd_sop", insertable = false, updatable = false)
    private Sop sop;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "kd_jenis_urtug",
                    referencedColumnName = "kd_jenis_urtug",
                    insertable = false,
                    updatable = false),
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
    private JenisUrtugUrtug jenisUrtugUrtug;


    public SopJenisUrtugUrtugId getSopJenisUrtugUrtugId() {
        return sopJenisUrtugUrtugId;
    }

    public void setSopJenisUrtugUrtugId(SopJenisUrtugUrtugId sopJenisUrtugUrtugId) {
        this.sopJenisUrtugUrtugId = sopJenisUrtugUrtugId;
    }

    public Sop getSop() {
        return sop;
    }

    public void setSop(Sop sop) {
        this.sop = sop;
    }

    public JenisUrtugUrtug getJenisUrtugUrtug() {
        return jenisUrtugUrtug;
    }

    public void setJenisUrtugUrtug(JenisUrtugUrtug jenisUrtugUrtug) {
        this.jenisUrtugUrtug = jenisUrtugUrtug;
    }

}
