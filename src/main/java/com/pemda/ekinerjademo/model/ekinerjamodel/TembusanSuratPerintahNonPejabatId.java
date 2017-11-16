package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 14/11/17.
 */
@Embeddable
public class TembusanSuratPerintahNonPejabatId implements Serializable {
    @Column(name = "kd_surat_perintah")
    private String kdSuratPerintah;
    @Column(name = "kd_jabatan")
    private String kdJabatan;

    public TembusanSuratPerintahNonPejabatId() {
    }
    public TembusanSuratPerintahNonPejabatId(String kdSuratPerintah, String kdJabatan) {
        this.kdSuratPerintah = kdSuratPerintah;
        this.kdJabatan = kdJabatan;
    }

    public String getKdSuratPerintah() {
        return kdSuratPerintah;
    }

    public void setKdSuratPerintah(String kdSuratPerintah) {
        this.kdSuratPerintah = kdSuratPerintah;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TembusanSuratPerintahNonPejabatId that = (TembusanSuratPerintahNonPejabatId) o;

        if (!kdSuratPerintah.equals(that.kdSuratPerintah)) return false;
        return kdJabatan.equals(that.kdJabatan);
    }

    @Override
    public int hashCode() {
        int result = kdSuratPerintah.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        return result;
    }

}
