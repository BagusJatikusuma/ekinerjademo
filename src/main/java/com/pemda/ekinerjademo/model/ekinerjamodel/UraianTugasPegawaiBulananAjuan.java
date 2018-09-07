package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "uraian_tugas_pegawai_bulanan_ajuan")
public class UraianTugasPegawaiBulananAjuan {
    @EmbeddedId
    private UraianTugasPegawaiBulananAjuanId uraianTugasPegawaiBulananAjuanId;
    @Column(name = "target_kuantitas_diajukan")
    private Integer targetKuantitasDiajukan;

    public UraianTugasPegawaiBulananAjuanId getUraianTugasPegawaiBulananAjuanId() {
        return uraianTugasPegawaiBulananAjuanId;
    }

    public void setUraianTugasPegawaiBulananAjuanId(UraianTugasPegawaiBulananAjuanId uraianTugasPegawaiBulananAjuanId) {
        this.uraianTugasPegawaiBulananAjuanId = uraianTugasPegawaiBulananAjuanId;
    }

    public Integer getTargetKuantitasDiajukan() {
        return targetKuantitasDiajukan;
    }

    public void setTargetKuantitasDiajukan(Integer targetKuantitasDiajukan) {
        this.targetKuantitasDiajukan = targetKuantitasDiajukan;
    }
}
