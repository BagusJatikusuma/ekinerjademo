package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 14/11/17.
 */
@Entity
@Table(name = "tembusan_surat_perintah")
public class TembusanSuratPerintah {
    @EmbeddedId
    private TembusanSuratPerintahId tembusanSuratPerintahId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_surat_perintah",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_perintah")
    private SuratPerintah suratPerintah;

    public TembusanSuratPerintahId getTembusanSuratPerintahId() {
        return tembusanSuratPerintahId;
    }

    public void setTembusanSuratPerintahId(TembusanSuratPerintahId tembusanSuratPerintahId) {
        this.tembusanSuratPerintahId = tembusanSuratPerintahId;
    }

    public SuratPerintah getSuratPerintah() {
        return suratPerintah;
    }

    public void setSuratPerintah(SuratPerintah suratPerintah) {
        this.suratPerintah = suratPerintah;
    }

}
