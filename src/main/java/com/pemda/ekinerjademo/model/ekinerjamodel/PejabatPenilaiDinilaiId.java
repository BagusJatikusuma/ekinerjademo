package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 25/10/17.
 */
@Embeddable
public class PejabatPenilaiDinilaiId implements Serializable {
    @Column(name = "nip_penilai")
    private String nipPenilai;
    @Column(name = "kd_jabatan_dinilai")
    private String kdJabatanDinilai;

    public PejabatPenilaiDinilaiId() {
    }
    public PejabatPenilaiDinilaiId(String nipPenilai, String kdJabatanDinilai) {
        this.nipPenilai = nipPenilai;
        this.kdJabatanDinilai = kdJabatanDinilai;
    }

    public String getNipPenilai() {
        return nipPenilai;
    }

    public void setNipPenilai(String nipPenilai) {
        this.nipPenilai = nipPenilai;
    }

    public String getKdJabatanDinilai() {
        return kdJabatanDinilai;
    }

    public void setKdJabatanDinilai(String kdJabatanDinilai) {
        this.kdJabatanDinilai = kdJabatanDinilai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PejabatPenilaiDinilaiId that = (PejabatPenilaiDinilaiId) o;

        if (!nipPenilai.equals(that.nipPenilai)) return false;
        return kdJabatanDinilai.equals(that.kdJabatanDinilai);
    }

    @Override
    public int hashCode() {
        int result = nipPenilai.hashCode();
        result = 31 * result + kdJabatanDinilai.hashCode();
        return result;
    }
}
