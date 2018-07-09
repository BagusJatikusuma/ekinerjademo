package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 07/02/18.
 */
@Embeddable
public class PenanggungJawabKegiatanId implements Serializable {
    @Column(name = "kd_urusan")
    private Integer kdUrusan;

    @Column(name = "kd_bidang")
    private Integer kdBidang;

    @Column(name = "kd_unit")
    private Integer kdUnit;

    @Column(name = "kd_sub")
    private Integer kdSub;

    @Column(name = "tahun")
    private Integer tahun;

    @Column(name = "kd_prog")
    private Integer kdProg;

    @Column(name = "id_prog")
    private Integer idProg;

    @Column(name = "kd_keg")
    private Integer kdKeg;

    @Column(name = "nip_pegawai")
    private String nipPegawai;

    @Column(name = "kd_status_penanggung_jawab")
    private String kdStatusPenanggungJawab;

    public PenanggungJawabKegiatanId() {
    }
    public PenanggungJawabKegiatanId(
            Integer kdUrusan,
            Integer kdBidang,
            Integer kdUnit,
            Integer kdSub,
            Integer tahun,
            Integer kdProg,
            Integer idProg,
            Integer kdKeg,
            String nipPegawai,
            String kdStatusPenanggungJawab) {
        this.kdUrusan = kdUrusan;
        this.kdBidang = kdBidang;
        this.kdUnit = kdUnit;
        this.kdSub = kdSub;
        this.tahun = tahun;
        this.kdProg = kdProg;
        this.idProg = idProg;
        this.kdKeg = kdKeg;
        this.nipPegawai = nipPegawai;
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
    }

    public Integer getKdUrusan() {
        return kdUrusan;
    }

    public void setKdUrusan(Integer kdUrusan) {
        this.kdUrusan = kdUrusan;
    }

    public Integer getKdBidang() {
        return kdBidang;
    }

    public void setKdBidang(Integer kdBidang) {
        this.kdBidang = kdBidang;
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

    public Integer getKdKeg() {
        return kdKeg;
    }

    public void setKdKeg(Integer kdKeg) {
        this.kdKeg = kdKeg;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getKdStatusPenanggungJawab() {
        return kdStatusPenanggungJawab;
    }

    public void setKdStatusPenanggungJawab(String kdStatusPenanggungJawab) {
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PenanggungJawabKegiatanId that = (PenanggungJawabKegiatanId) o;

        if (!kdUrusan.equals(that.kdUrusan)) return false;
        if (!kdBidang.equals(that.kdBidang)) return false;
        if (!kdUnit.equals(that.kdUnit)) return false;
        if (!kdSub.equals(that.kdSub)) return false;
        if (!tahun.equals(that.tahun)) return false;
        if (!kdProg.equals(that.kdProg)) return false;
        if (!idProg.equals(that.idProg)) return false;
        if (!kdKeg.equals(that.kdKeg)) return false;
        if (!nipPegawai.equals(that.nipPegawai)) return false;
        return kdStatusPenanggungJawab.equals(that.kdStatusPenanggungJawab);
    }

    @Override
    public int hashCode() {
        int result = kdUrusan.hashCode();
        result = 31 * result + kdBidang.hashCode();
        result = 31 * result + kdUnit.hashCode();
        result = 31 * result + kdSub.hashCode();
        result = 31 * result + tahun.hashCode();
        result = 31 * result + kdProg.hashCode();
        result = 31 * result + idProg.hashCode();
        result = 31 * result + kdKeg.hashCode();
        result = 31 * result + nipPegawai.hashCode();
        result = 31 * result + kdStatusPenanggungJawab.hashCode();
        return result;
    }
}
