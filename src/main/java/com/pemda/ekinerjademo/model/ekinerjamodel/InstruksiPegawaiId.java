package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 22/11/17.
 */
@Embeddable
public class InstruksiPegawaiId implements Serializable {
    @Column(name = "nip_pegawai")
    private String nipPegawai;
    @Column(name = "kd_instruksi")
    private String kdInstruksi;

    public InstruksiPegawaiId() {
    }
    public InstruksiPegawaiId(String nipPegawai, String kdInstruksi) {
        this.nipPegawai = nipPegawai;
        this.kdInstruksi = kdInstruksi;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getKdInstruksi() {
        return kdInstruksi;
    }

    public void setKdInstruksi(String kdInstruksi) {
        this.kdInstruksi = kdInstruksi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstruksiPegawaiId that = (InstruksiPegawaiId) o;

        if (!nipPegawai.equals(that.nipPegawai)) return false;
        return kdInstruksi.equals(that.kdInstruksi);
    }

    @Override
    public int hashCode() {
        int result = nipPegawai.hashCode();
        result = 31 * result + kdInstruksi.hashCode();
        return result;
    }
}
