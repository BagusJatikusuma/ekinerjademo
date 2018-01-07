package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bayu on 07/01/18.
 */
@Embeddable
public class TembusanSuratDinasId implements Serializable {
    @Column(name = "kd_surat_dinas")
    private String kdSuratDinas;
    @Column(name = "kd_jabatan")
    private String kdJabatan;

    public TembusanSuratDinasId() {
    }

    public TembusanSuratDinasId(String kdSuratDinas, String kdJabatan) {
        this.kdSuratDinas = kdSuratDinas;
        this.kdJabatan = kdJabatan;
    }

    public String getKdSuratDinas() {
        return kdSuratDinas;
    }

    public void setKdSuratDinas(String kdSuratDinas) {
        this.kdSuratDinas = kdSuratDinas;
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

        TembusanSuratDinasId that = (TembusanSuratDinasId) o;

        if (!kdSuratDinas.equals(that.kdSuratDinas)) return false;
        return kdJabatan.equals(that.kdJabatan);
    }

    @Override
    public int hashCode() {
        int result = kdSuratDinas.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        return result;
    }
}
