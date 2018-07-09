package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 08/10/17.
 */
@Entity
@Table(name = "sop_uraian_tugas_jabatan")
public class SopUraianTugasJabatan {
    @EmbeddedId
    private SopUraianTugasJabatanId sopUraianTugasJabatanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_sop",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_sop")
    private Sop sop;

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
                    referencedColumnName = "kd_jabatan")
    })
    private UraianTugasJabatan uraianTugasJabatan;

    public SopUraianTugasJabatanId getSopUraianTugasJabatanId() {
        return sopUraianTugasJabatanId;
    }

    public void setSopUraianTugasJabatanId(SopUraianTugasJabatanId sopUraianTugasJabatanId) {
        this.sopUraianTugasJabatanId = sopUraianTugasJabatanId;
    }

    public UraianTugasJabatan getUraianTugasJabatan() {
        return uraianTugasJabatan;
    }

    public Sop getSop() {
        return sop;
    }

    public void setSop(Sop sop) {
        this.sop = sop;
    }
}
