package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;

public class CompletedLembarDisposisiInputWrapper {
    private String kdDraftLembarDisposisi;
    private Integer tktKeamanan;
    private Long tglPenyelesaianMilis;
    private List<String> daftarTargetJabatanLembarDisposisi;
    private String isiDisposisi;
    private Integer durasiPengerjaan;

    private String kdUrtug;
    private Integer tahunUrtug;

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

    public List<String> getDaftarTargetJabatanLembarDisposisi() {
        return daftarTargetJabatanLembarDisposisi;
    }

    public void setDaftarTargetJabatanLembarDisposisi(List<String> daftarTargetJabatanLembarDisposisi) {
        this.daftarTargetJabatanLembarDisposisi = daftarTargetJabatanLembarDisposisi;
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
}
