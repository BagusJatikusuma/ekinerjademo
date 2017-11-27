package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 27/11/17.
 */
@Embeddable
public class TargetSuratTugasPejabatId implements Serializable {
    @Column(name = "kd_surat_tugas")
    private String kdSuratTugas;
    @Column(name = "kd_jabatan")
    private String kdJabatan;

    public TargetSuratTugasPejabatId() {
    }
    public TargetSuratTugasPejabatId(String kdSuratTugas, String kdJabatan) {
        this.kdSuratTugas = kdSuratTugas;
        this.kdJabatan = kdJabatan;
    }

    public String getKdSuratTugas() {
        return kdSuratTugas;
    }

    public void setKdSuratTugas(String kdSuratTugas) {
        this.kdSuratTugas = kdSuratTugas;
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

        TargetSuratTugasPejabatId that = (TargetSuratTugasPejabatId) o;

        if (!kdSuratTugas.equals(that.kdSuratTugas)) return false;
        return kdJabatan.equals(that.kdJabatan);
    }

    @Override
    public int hashCode() {
        int result = kdSuratTugas.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        return result;
    }
}
