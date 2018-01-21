package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bayu on 09/01/18.
 */
@Embeddable
public class TembusanSuratUndanganId implements Serializable {
    @Column(name = "kd_surat_undangan")
    private String kdSuratUndangan;
    @Column(name = "kd_jabatan")
    private String kdJabatan;

    public TembusanSuratUndanganId() {
    }

    public TembusanSuratUndanganId(String kdSuratUndangan, String kdJabatan) {
        this.kdSuratUndangan = kdSuratUndangan;
        this.kdJabatan = kdJabatan;
    }

    public String getKdSuratUndangan() {
        return kdSuratUndangan;
    }

    public void setKdSuratUndangan(String kdSuratUndangan) {
        this.kdSuratUndangan = kdSuratUndangan;
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

        TembusanSuratUndanganId that = (TembusanSuratUndanganId) o;

        if (!kdSuratUndangan.equals(that.kdSuratUndangan)) return false;
        return kdJabatan.equals(that.kdJabatan);
    }

    @Override
    public int hashCode() {
        int result = kdSuratUndangan.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        return result;
    }
}
