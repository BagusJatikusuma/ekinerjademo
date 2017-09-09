package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bagus on 08/09/17.
 */
@Entity
@Table(name = "uraian_tugas_jabatan")
public class UraianTugasJabatan implements Serializable {
    @EmbeddedId
    private UraianTugasJabatanId uraianTugasJabatanId;

    @ManyToOne
    @JoinColumn(name = "kd_urtug", insertable = false, updatable = false)
    private UraianTugas uraianTugas;

    public UraianTugasJabatanId getUraianTugasJabatanId() {
        return uraianTugasJabatanId;
    }

    public void setUraianTugasJabatanId(UraianTugasJabatanId uraianTugasJabatanId) {
        this.uraianTugasJabatanId = uraianTugasJabatanId;
    }

    public UraianTugas getUraianTugas() {
        return uraianTugas;
    }

    public void setUraianTugas(UraianTugas uraianTugas) {
        this.uraianTugas = uraianTugas;
    }
}
