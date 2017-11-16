package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;

/**
 * Created by bagus on 14/11/17.
 */
@Entity
@Table(name = "tembusan_surat_perintah_non_pejabat")
public class TembusanSuratPerintahNonPejabat {
    @EmbeddedId
    private TembusanSuratPerintahNonPejabatId tembusanSuratPerintahNonPejabatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_surat_perintah",
            insertable = false,
            updatable = false,
            referencedColumnName = "kd_surat_perintah")
    private SuratPerintahNonPejabat suratPerintahNonPejabat;

    public TembusanSuratPerintahNonPejabatId getTembusanSuratPerintahNonPejabatId() {
        return tembusanSuratPerintahNonPejabatId;
    }

    public void setTembusanSuratPerintahNonPejabatId(TembusanSuratPerintahNonPejabatId tembusanSuratPerintahNonPejabatId) {
        this.tembusanSuratPerintahNonPejabatId = tembusanSuratPerintahNonPejabatId;
    }

    public SuratPerintahNonPejabat getSuratPerintahNonPejabat() {
        return suratPerintahNonPejabat;
    }

    public void setSuratPerintahNonPejabat(SuratPerintahNonPejabat suratPerintahNonPejabat) {
        this.suratPerintahNonPejabat = suratPerintahNonPejabat;
    }

}
