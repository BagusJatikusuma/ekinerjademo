package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 05/12/17.
 */
@Embeddable
public class NomorUrutSuratUnitKerjaId implements Serializable {
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
    @Column(name = "tahun")
    private Integer tahun;

    public NomorUrutSuratUnitKerjaId() {
    }
    public NomorUrutSuratUnitKerjaId(String kdUnitKerja, Integer tahun) {
        this.kdUnitKerja = kdUnitKerja;
        this.tahun = tahun;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NomorUrutSuratUnitKerjaId that = (NomorUrutSuratUnitKerjaId) o;

        if (!kdUnitKerja.equals(that.kdUnitKerja)) return false;
        return tahun.equals(that.tahun);
    }

    @Override
    public int hashCode() {
        int result = kdUnitKerja.hashCode();
        result = 31 * result + tahun.hashCode();
        return result;
    }
}
