package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 27/11/17.
 */
@Entity
@Table(name = "tembusan_surat_tugas")
public class TembusanSuratTugas {
    @EmbeddedId
    private TembusanSuratTugasId tembusanSuratTugasId;

    @ManyToOne
    @JoinColumn(
            name = "kd_surat_tugas",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_tugas")
    private SuratInstruksi suratInstruksi;

    public TembusanSuratTugasId getTembusanSuratTugasId() {
        return tembusanSuratTugasId;
    }

    public void setTembusanSuratTugasId(TembusanSuratTugasId tembusanSuratTugasId) {
        this.tembusanSuratTugasId = tembusanSuratTugasId;
    }

    public SuratInstruksi getSuratInstruksi() {
        return suratInstruksi;
    }

    public void setSuratInstruksi(SuratInstruksi suratInstruksi) {
        this.suratInstruksi = suratInstruksi;
    }
}
