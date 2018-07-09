package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bayu on 10/01/18.
 */
@Embeddable
public class TargetSuratKeteranganId implements Serializable {
    @Column(name = "kd_surat_keterangan")
    private String kdSuratKeterangan;
    @Column(name = "nip_pegawai")
    private String nipPegawai;

    public TargetSuratKeteranganId() {
    }

    public TargetSuratKeteranganId(String kdSuratKeterangan, String nipPegawai) {
        this.kdSuratKeterangan = kdSuratKeterangan;
        this.nipPegawai = nipPegawai;
    }

    public String getKdSuratKeterangan() {
        return kdSuratKeterangan;
    }

    public void setKdSuratKeterangan(String kdSuratKeterangan) {
        this.kdSuratKeterangan = kdSuratKeterangan;
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

        TargetSuratKeteranganId that = (TargetSuratKeteranganId) o;

        if (!kdSuratKeterangan.equals(that.kdSuratKeterangan)) return false;
        return nipPegawai.equals(that.nipPegawai);
    }

    @Override
    public int hashCode() {
        int result = kdSuratKeterangan.hashCode();
        result = 31 * result + nipPegawai.hashCode();
        return result;
    }
}
