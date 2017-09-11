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

    @Column(name = "created_by")
    private String createdBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kd_urtug", insertable = false, updatable = false, referencedColumnName = "kd_urtug")
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
