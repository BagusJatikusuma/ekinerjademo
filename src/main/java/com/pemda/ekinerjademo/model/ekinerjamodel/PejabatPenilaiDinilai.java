package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by bagus on 25/10/17.
 */
@Entity
@Table(name = "pejabat_penilai_dinilai")
public class PejabatPenilaiDinilai {
    @EmbeddedId
    private PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId;

    public PejabatPenilaiDinilaiId getPejabatPenilaiDinilaiId() {
        return pejabatPenilaiDinilaiId;
    }

    public void setPejabatPenilaiDinilaiId(PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId) {
        this.pejabatPenilaiDinilaiId = pejabatPenilaiDinilaiId;
    }

}
