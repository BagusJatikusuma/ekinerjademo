package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

/**
 * Created by bagus on 27/11/17.
 */
@Embeddable
public class TargetSuratTugasPegawaiId implements Serializable {
    @Column(name = "kd_surat_tugas")
    private String kdSuratTugas;
    @Column(name = "nip_pegawai")
    private String nipPegawai;

    public TargetSuratTugasPegawaiId() {
    }
    public TargetSuratTugasPegawaiId(String kdSuratTugas, String nipPegawai) {
        this.kdSuratTugas = kdSuratTugas;
        this.nipPegawai = nipPegawai;
    }

    public String getKdSuratTugas() {
        return kdSuratTugas;
    }

    public void setKdSuratTugas(String kdSuratTugas) {
        this.kdSuratTugas = kdSuratTugas;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TargetSuratTugasPegawaiId that = (TargetSuratTugasPegawaiId) o;

        if (!kdSuratTugas.equals(that.kdSuratTugas)) return false;
        return nipPegawai.equals(that.nipPegawai);
    }

    @Override
    public int hashCode() {
        int result = kdSuratTugas.hashCode();
        result = 31 * result + nipPegawai.hashCode();
        return result;
    }

}
