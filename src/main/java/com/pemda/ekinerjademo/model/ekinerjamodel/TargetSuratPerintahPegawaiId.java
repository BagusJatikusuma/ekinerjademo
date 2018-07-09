package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 14/11/17.
 */
@Embeddable
public class TargetSuratPerintahPegawaiId implements Serializable {
    @Column(name = "kd_surat_perintah")
    private String kdSuratPerintah;
    @Column(name = "nip_pegawai")
    private String nipPegawai;

    public TargetSuratPerintahPegawaiId() {
    }
    public TargetSuratPerintahPegawaiId(String kdSuratPerintah, String nipPegawai) {
        this.kdSuratPerintah = kdSuratPerintah;
        this.nipPegawai = nipPegawai;
    }

    public String getKdSuratPerintah() {
        return kdSuratPerintah;
    }

    public void setKdSuratPerintah(String kdSuratPerintah) {
        this.kdSuratPerintah = kdSuratPerintah;
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

        TargetSuratPerintahPegawaiId that = (TargetSuratPerintahPegawaiId) o;

        if (!kdSuratPerintah.equals(that.kdSuratPerintah)) return false;
        return nipPegawai.equals(that.nipPegawai);
    }

    @Override
    public int hashCode() {
        int result = kdSuratPerintah.hashCode();
        result = 31 * result + nipPegawai.hashCode();
        return result;
    }
}
