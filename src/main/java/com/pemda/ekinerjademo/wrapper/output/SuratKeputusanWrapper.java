package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

public class SuratKeputusanWrapper {
    private Integer nomorUrut;
    private Integer nomorTahun;

    private String nipPenandatangan;
    private String namaPenandatangan;
    private String jabatanPenandatangan;
    private String unitKerjaPenandatangan;

    private String selaku;
    private String tentang;
    private List<String> menimbang;
    private List<String> mengingat;
    private List<String> menetapkan;

    private Long tanggalPembuatanMilis;
    private String kotaPembuatanSurat;

    public SuratKeputusanWrapper() {
    }

    public SuratKeputusanWrapper(Integer nomorUrut,
                                 Integer nomorTahun,
                                 String nipPenandatangan,
                                 String namaPenandatangan,
                                 String jabatanPenandatangan,
                                 String unitKerjaPenandatangan,
                                 String selaku,
                                 String tentang,
                                 List<String> menimbang,
                                 List<String> mengingat,
                                 List<String> menetapkan,
                                 Long tanggalPembuatanMilis,
                                 String kotaPembuatanSurat) {
        this.nomorUrut = nomorUrut;
        this.nomorTahun = nomorTahun;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
        this.selaku = selaku;
        this.tentang = tentang;
        this.menimbang = menimbang;
        this.mengingat = mengingat;
        this.menetapkan = menetapkan;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.kotaPembuatanSurat = kotaPembuatanSurat;
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

    public String getSelaku() {
        return selaku;
    }

    public void setSelaku(String selaku) {
        this.selaku = selaku;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public List<String> getMenimbang() {
        return menimbang;
    }

    public void setMenimbang(List<String> menimbang) {
        this.menimbang = menimbang;
    }

    public List<String> getMengingat() {
        return mengingat;
    }

    public void setMengingat(List<String> mengingat) {
        this.mengingat = mengingat;
    }

    public List<String> getMenetapkan() {
        return menetapkan;
    }

    public void setMenetapkan(List<String> menetapkan) {
        this.menetapkan = menetapkan;
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
}
