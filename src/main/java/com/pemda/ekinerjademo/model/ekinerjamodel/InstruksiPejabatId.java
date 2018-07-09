package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 22/11/17.
 */
@Embeddable
public class InstruksiPejabatId implements Serializable {
    @Column(name = "kd_jabatan")
    private String kdJabatan;
    @Column(name = "kd_instruksi")
    private String kdInstruksi;

    public InstruksiPejabatId() {
    }
    public InstruksiPejabatId(String kdJabatan, String kdInstruksi) {
        this.kdJabatan = kdJabatan;
        this.kdInstruksi = kdInstruksi;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
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

        InstruksiPejabatId that = (InstruksiPejabatId) o;

        if (!kdJabatan.equals(that.kdJabatan)) return false;
        return kdInstruksi.equals(that.kdInstruksi);
    }

    @Override
    public int hashCode() {
        int result = kdJabatan.hashCode();
        result = 31 * result + kdInstruksi.hashCode();
        return result;
    }

}
