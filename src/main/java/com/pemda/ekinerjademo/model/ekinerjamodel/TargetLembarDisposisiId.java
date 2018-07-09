package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 18/11/17.
 */
@Embeddable
public class TargetLembarDisposisiId implements Serializable {
    @Column(name = "kd_lembar_disposisi")
    private String kdLembarDisposisi;
    @Column(name = "nip_pegawai")
    private String nipPegawai;

    public TargetLembarDisposisiId() {
    }
    public TargetLembarDisposisiId(String kdLembarDisposisi, String nipPegawai) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.nipPegawai = nipPegawai;
    }

    public String getKdLembarDisposisi() {
        return kdLembarDisposisi;
    }

    public void setKdLembarDisposisi(String kdLembarDisposisi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
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

        TargetLembarDisposisiId that = (TargetLembarDisposisiId) o;

        if (!kdLembarDisposisi.equals(that.kdLembarDisposisi)) return false;
        return nipPegawai.equals(that.nipPegawai);
    }

    @Override
    public int hashCode() {
        int result = kdLembarDisposisi.hashCode();
        result = 31 * result + nipPegawai.hashCode();
        return result;
    }
}
