package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KegiatanPegawaiBulananId implements Serializable {
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

    @Column(name = "bulan_pengerjaan")
    private Integer bulanPengerjaan;

    public KegiatanPegawaiBulananId() {
    }

    public KegiatanPegawaiBulananId(Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg, Integer kdKeg, String nipPegawai, String kdStatusPenanggungJawab, Integer bulanPengerjaan) {
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
        this.bulanPengerjaan = bulanPengerjaan;
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

    public Integer getBulanPengerjaan() {
        return bulanPengerjaan;
    }

    public void setBulanPengerjaan(Integer bulanPengerjaan) {
        this.bulanPengerjaan = bulanPengerjaan;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KegiatanPegawaiBulananId that = (KegiatanPegawaiBulananId) o;
        return Objects.equals(kdUrusan, that.kdUrusan) &&
                Objects.equals(kdBidang, that.kdBidang) &&
                Objects.equals(kdUnit, that.kdUnit) &&
                Objects.equals(kdSub, that.kdSub) &&
                Objects.equals(tahun, that.tahun) &&
                Objects.equals(kdProg, that.kdProg) &&
                Objects.equals(idProg, that.idProg) &&
                Objects.equals(kdKeg, that.kdKeg) &&
                Objects.equals(nipPegawai, that.nipPegawai) &&
                Objects.equals(kdStatusPenanggungJawab, that.kdStatusPenanggungJawab) &&
                Objects.equals(bulanPengerjaan, that.bulanPengerjaan);
    }

    @Override
    public int hashCode() {

        return Objects.hash(kdUrusan, kdBidang, kdUnit, kdSub, tahun, kdProg, idProg, kdKeg, nipPegawai, kdStatusPenanggungJawab, bulanPengerjaan);
    }
}
