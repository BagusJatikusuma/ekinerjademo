package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;

public class CompletedLembarDisposisiInputWrapper {
    private String kdDraftLembarDisposisi;
    private Integer tktKeamanan;
    private Long tglPenyelesaianMilis;
    private List<String> daftarTargetLembarDisposisi;
    private boolean isTargetJabatan;
    private String nipPenerus;
    private String isiDisposisi;
    private Integer durasiPengerjaan;

    private String kdUrtug;
    private Integer tahunUrtug;
    private String nipPelengkap;

    private String kdLembarDisposisiParent;

    public Integer getTktKeamanan() {
        return tktKeamanan;
    }

    public void setTktKeamanan(Integer tktKeamanan) {
        this.tktKeamanan = tktKeamanan;
    }

    public Long getTglPenyelesaianMilis() {
        return tglPenyelesaianMilis;
    }

    public void setTglPenyelesaianMilis(Long tglPenyelesaianMilis) {
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
    }

    public List<String> getDaftarTargetLembarDisposisi() {
        return daftarTargetLembarDisposisi;
    }

    public void setDaftarTargetLembarDisposisi(List<String> daftarTargetLembarDisposisi) {
        this.daftarTargetLembarDisposisi = daftarTargetLembarDisposisi;
    }

    public String getIsiDisposisi() {
        return isiDisposisi;
    }

    public void setIsiDisposisi(String isiDisposisi) {
        this.isiDisposisi = isiDisposisi;
    }

    public Integer getDurasiPengerjaan() {
        return durasiPengerjaan;
    }

    public void setDurasiPengerjaan(Integer durasiPengerjaan) {
        this.durasiPengerjaan = durasiPengerjaan;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public Integer getTahunUrtug() {
        return tahunUrtug;
    }

    public void setTahunUrtug(Integer tahunUrtug) {
        this.tahunUrtug = tahunUrtug;
    }

    public String getKdDraftLembarDisposisi() {
        return kdDraftLembarDisposisi;
    }

    public void setKdDraftLembarDisposisi(String kdDraftLembarDisposisi) {
        this.kdDraftLembarDisposisi = kdDraftLembarDisposisi;
    }

    public String getNipPelengkap() {
        return nipPelengkap;
    }

    public void setNipPelengkap(String nipPelengkap) {
        this.nipPelengkap = nipPelengkap;
    }

    public String getKdLembarDisposisiParent() {
        return kdLembarDisposisiParent;
    }

    public void setKdLembarDisposisiParent(String kdLembarDisposisiParent) {
        this.kdLembarDisposisiParent = kdLembarDisposisiParent;
    }

    public boolean isTargetJabatan() {
        return isTargetJabatan;
    }

    public void setTargetJabatan(boolean targetJabatan) {
        isTargetJabatan = targetJabatan;
    }

    public String getNipPenerus() {
        return nipPenerus;
    }

    public void setNipPenerus(String nipPenerus) {
        this.nipPenerus = nipPenerus;
    }
}
