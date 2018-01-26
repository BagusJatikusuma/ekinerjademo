package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

public class SuratEdaranWrapper {
    private Integer nomorUrut;
    private Integer nomorTahun;

    private String tentang;
    private String latarBelakang;
    private String maksudDanTujuan;
    private String ruangLingkup;
    private String dasar;
    private List<SuratEdaranSubWrapper> subLain;

    private Long tanggalPembuatanMilis;
    private String kotaPembuatanSurat;

    private String nipPenandatangan;
    private String namaPenandatangan;
    private String jabatanPenandatangan;
    private String unitKerjaPenandatangan;

    public SuratEdaranWrapper() {
    }

    public SuratEdaranWrapper(Integer nomorUrut,
                              Integer nomorTahun,
                              String tentang,
                              String latarBelakang,
                              String maksudDanTujuan,
                              String ruangLingkup,
                              String dasar,
                              List<SuratEdaranSubWrapper> subLain,
                              Long tanggalPembuatanMilis,
                              String kotaPembuatanSurat,
                              String nipPenandatangan,
                              String namaPenandatangan,
                              String jabatanPenandatangan,
                              String unitKerjaPenandatangan) {
        this.nomorUrut = nomorUrut;
        this.nomorTahun = nomorTahun;
        this.tentang = tentang;
        this.latarBelakang = latarBelakang;
        this.maksudDanTujuan = maksudDanTujuan;
        this.ruangLingkup = ruangLingkup;
        this.dasar = dasar;
        this.subLain = subLain;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.kotaPembuatanSurat = kotaPembuatanSurat;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
    }

    public Integer getNomorUrut() {
        return nomorUrut;
    }

    public void setNomorUrut(Integer nomorUrut) {
        this.nomorUrut = nomorUrut;
    }

    public Integer getNomorTahun() {
        return nomorTahun;
    }

    public void setNomorTahun(Integer nomorTahun) {
        this.nomorTahun = nomorTahun;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getMaksudDanTujuan() {
        return maksudDanTujuan;
    }

    public void setMaksudDanTujuan(String maksudDanTujuan) {
        this.maksudDanTujuan = maksudDanTujuan;
    }

    public String getRuangLingkup() {
        return ruangLingkup;
    }

    public void setRuangLingkup(String ruangLingkup) {
        this.ruangLingkup = ruangLingkup;
    }

    public String getDasar() {
        return dasar;
    }

    public void setDasar(String dasar) {
        this.dasar = dasar;
    }

    public List<SuratEdaranSubWrapper> getSubLain() {
        return subLain;
    }

    public void setSubLain(List<SuratEdaranSubWrapper> subLain) {
        this.subLain = subLain;
    }

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public String getKotaPembuatanSurat() {
        return kotaPembuatanSurat;
    }

    public void setKotaPembuatanSurat(String kotaPembuatanSurat) {
        this.kotaPembuatanSurat = kotaPembuatanSurat;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public String getNamaPenandatangan() {
        return namaPenandatangan;
    }

    public void setNamaPenandatangan(String namaPenandatangan) {
        this.namaPenandatangan = namaPenandatangan;
    }

    public String getJabatanPenandatangan() {
        return jabatanPenandatangan;
    }

    public void setJabatanPenandatangan(String jabatanPenandatangan) {
        this.jabatanPenandatangan = jabatanPenandatangan;
    }

    public String getUnitKerjaPenandatangan() {
        return unitKerjaPenandatangan;
    }

    public void setUnitKerjaPenandatangan(String unitKerjaPenandatangan) {
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
    }
}
