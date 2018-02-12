package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;

/**
 * Created by bagus on 23/11/17.
 */
public class SuratInstruksiInputWrapper {
    private String judulInstruksi;
    private String nomor;
    private String tentang;
    private String alasan;
    private List<String> daftarIsiInstruksi;
    private String dikeluarkanDi;
    private Long tanggalDibuat;
    private String nipPembuat;
    private String nipPenandatangan;
    private List<String> targetPegawaiList;
    private List<String> targetJabatanList;
    private boolean suratPejabat;
    private String kdJabatanSuratPejabat;
    private String kdUnitKerja;
    private String kdSuratInstruksiParent;
    private Integer durasiPengerjaan;

    private String kdUrtug;
    private Integer tahunUrtug;

    public String getJudulInstruksi() {
        return judulInstruksi;
    }

    public void setJudulInstruksi(String judulInstruksi) {
        this.judulInstruksi = judulInstruksi;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public List<String> getDaftarIsiInstruksi() {
        return daftarIsiInstruksi;
    }

    public void setDaftarIsiInstruksi(List<String> daftarIsiInstruksi) {
        this.daftarIsiInstruksi = daftarIsiInstruksi;
    }

    public String getDikeluarkanDi() {
        return dikeluarkanDi;
    }

    public void setDikeluarkanDi(String dikeluarkanDi) {
        this.dikeluarkanDi = dikeluarkanDi;
    }

    public Long getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(Long tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public String getNipPembuat() {
        return nipPembuat;
    }

    public void setNipPembuat(String nipPembuat) {
        this.nipPembuat = nipPembuat;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public List<String> getTargetPegawaiList() {
        return targetPegawaiList;
    }

    public void setTargetPegawaiList(List<String> targetPegawaiList) {
        this.targetPegawaiList = targetPegawaiList;
    }

    public List<String> getTargetJabatanList() {
        return targetJabatanList;
    }

    public void setTargetJabatanList(List<String> targetJabatanList) {
        this.targetJabatanList = targetJabatanList;
    }

    public boolean isSuratPejabat() {
        return suratPejabat;
    }

    public void setSuratPejabat(boolean suratPejabat) {
        this.suratPejabat = suratPejabat;
    }

    public String getKdJabatanSuratPejabat() {
        return kdJabatanSuratPejabat;
    }

    public void setKdJabatanSuratPejabat(String kdJabatanSuratPejabat) {
        this.kdJabatanSuratPejabat = kdJabatanSuratPejabat;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getKdSuratInstruksiParent() {
        return kdSuratInstruksiParent;
    }

    public void setKdSuratInstruksiParent(String kdSuratInstruksiParent) {
        this.kdSuratInstruksiParent = kdSuratInstruksiParent;
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
}
