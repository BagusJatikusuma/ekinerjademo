package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 29/09/17.
 */
@Embeddable
public class TaProgramId implements Serializable {
    @Column(name = "Kd_Urusan")
    private Integer kdUrusan;
    @Column(name = "Kd_Bidang")
    private Integer kdBIdang;
    @Column(name = "Kd_Unit")
    private Integer kdUnit;
    @Column(name = "Kd_Sub")
    private Integer kdSub;
    @Column(name = "Tahun")
    private Integer tahun;
    @Column(name = "Kd_Prog")
    private Integer kdProg;
    @Column(name = "ID_Prog")
    private Integer idProg;

    public TaProgramId() {
    }
    public TaProgramId(
            Integer kdUrusan,
            Integer kdBIdang,
            Integer kdUnit,
            Integer kdSub,
            Integer tahun,
            Integer kdProg,
            Integer idProg) {
        this.kdUrusan = kdUrusan;
        this.kdBIdang = kdBIdang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
        this.tahun = tahun;
        this.kdProg = kdProg;
        this.idProg = idProg;
    }

    public Integer getKdUrusan() {
        return kdUrusan;
    }

    public void setKdUrusan(Integer kdUrusan) {
        this.kdUrusan = kdUrusan;
    }

    public Integer getKdBIdang() {
        return kdBIdang;
    }

    public void setKdBIdang(Integer kdBIdang) {
        this.kdBIdang = kdBIdang;
    }

    public Integer getKdUnit() {
        return kdUnit;
    }

    public void setKdUnit(Integer kdUnit) {
        this.kdUnit = kdUnit;
    }

    public Integer getKdSub() {
        return kdSub;
    }

    public void setKdSub(Integer kdSub) {
        this.kdSub = kdSub;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public Integer getKdProg() {
        return kdProg;
    }

    public void setKdProg(Integer kdProg) {
        this.kdProg = kdProg;
    }

    public Integer getIdProg() {
        return idProg;
    }

    public void setIdProg(Integer idProg) {
        this.idProg = idProg;
    }
}
