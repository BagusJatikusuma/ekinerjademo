package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

import java.util.List;

/**
 * Created by bagus on 23/11/17.
 */
public class DokumenSuratInstruksiWrapper {
    private String kdInstruksi;
    private String judulInstruksi;
    private String nomor;
    private Integer tahun;
    private String tentang;
    private String kdJabatanPenandatangan;
    private String jabatanPenandaTangan;
    private String alasan;
    private List<String> daftarIsiInstruksi;
    private String dikeluarkanDi;
    private String tanggalDibuat;
    private String nipPenandatangan;
    private String namaPenandatangan;
    private List<QutPegawaiWrapper> targetPegawaiList;
    private List<JabatanWrapper> targetJabatanList;
    private boolean suratPejabat;
    private String jabatanSuratPejabat;
    private String unitKerja;
    private String gelarDepanPenandatangan;
    private String gelarBelakangPenandantangan;
    private String pangkatPenandatangan;
    private String golonganPenandatangan;
    private byte[] barcodeImage;

    public DokumenSuratInstruksiWrapper() {
    }

    public DokumenSuratInstruksiWrapper(String kdInstruksi, String judulInstruksi, String nomor, Integer tahun, String tentang, String kdJabatanPenandatangan, String jabatanPenandaTangan, String alasan, List<String> daftarIsiInstruksi, String dikeluarkanDi, String tanggalDibuat, String nipPenandatangan, String namaPenandatangan, List<QutPegawaiWrapper> targetPegawaiList, List<JabatanWrapper> targetJabatanList, boolean suratPejabat, String jabatanSuratPejabat, String unitKerja, String gelarDepanPenandatangan, String gelarBelakangPenandantangan, String pangkatPenandatangan, String golonganPenandatangan, byte[] barcodeImage) {
        this.kdInstruksi = kdInstruksi;
        this.judulInstruksi = judulInstruksi;
        this.nomor = nomor;
        this.tahun = tahun;
        this.tentang = tentang;
        this.kdJabatanPenandatangan = kdJabatanPenandatangan;
        this.jabatanPenandaTangan = jabatanPenandaTangan;
        this.alasan = alasan;
        this.daftarIsiInstruksi = daftarIsiInstruksi;
        this.dikeluarkanDi = dikeluarkanDi;
        this.tanggalDibuat = tanggalDibuat;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.targetPegawaiList = targetPegawaiList;
        this.targetJabatanList = targetJabatanList;
        this.suratPejabat = suratPejabat;
        this.jabatanSuratPejabat = jabatanSuratPejabat;
        this.unitKerja = unitKerja;
        this.gelarDepanPenandatangan = gelarDepanPenandatangan;
        this.gelarBelakangPenandantangan = gelarBelakangPenandantangan;
        this.pangkatPenandatangan = pangkatPenandatangan;
        this.golonganPenandatangan = golonganPenandatangan;
        this.barcodeImage = barcodeImage;
    }

    public String getKdInstruksi() {
        return kdInstruksi;
    }

    public void setKdInstruksi(String kdInstruksi) {
        this.kdInstruksi = kdInstruksi;
    }

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

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getKdJabatanPenandatangan() {
        return kdJabatanPenandatangan;
    }

    public void setKdJabatanPenandatangan(String kdJabatanPenandatangan) {
        this.kdJabatanPenandatangan = kdJabatanPenandatangan;
    }

    public String getJabatanPenandaTangan() {
        return jabatanPenandaTangan;
    }

    public void setJabatanPenandaTangan(String jabatanPenandaTangan) {
        this.jabatanPenandaTangan = jabatanPenandaTangan;
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

    public String getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(String tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
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

    public List<QutPegawaiWrapper> getTargetPegawaiList() {
        return targetPegawaiList;
    }

    public void setTargetPegawaiList(List<QutPegawaiWrapper> targetPegawaiList) {
        this.targetPegawaiList = targetPegawaiList;
    }

    public List<JabatanWrapper> getTargetJabatanList() {
        return targetJabatanList;
    }

    public void setTargetJabatanList(List<JabatanWrapper> targetJabatanList) {
        this.targetJabatanList = targetJabatanList;
    }

    public boolean isSuratPejabat() {
        return suratPejabat;
    }

    public void setSuratPejabat(boolean suratPejabat) {
        this.suratPejabat = suratPejabat;
    }

    public String getJabatanSuratPejabat() {
        return jabatanSuratPejabat;
    }

    public void setJabatanSuratPejabat(String jabatanSuratPejabat) {
        this.jabatanSuratPejabat = jabatanSuratPejabat;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }

    public String getGelarDepanPenandatangan() {
        return gelarDepanPenandatangan;
    }

    public void setGelarDepanPenandatangan(String gelarDepanPenandatangan) {
        this.gelarDepanPenandatangan = gelarDepanPenandatangan;
    }

    public String getGelarBelakangPenandantangan() {
        return gelarBelakangPenandantangan;
    }

    public void setGelarBelakangPenandantangan(String gelarBelakangPenandantangan) {
        this.gelarBelakangPenandantangan = gelarBelakangPenandantangan;
    }

    public String getPangkatPenandatangan() {
        return pangkatPenandatangan;
    }

    public void setPangkatPenandatangan(String pangkatPenandatangan) {
        this.pangkatPenandatangan = pangkatPenandatangan;
    }

    public String getGolonganPenandatangan() {
        return golonganPenandatangan;
    }

    public void setGolonganPenandatangan(String golonganPenandatangan) {
        this.golonganPenandatangan = golonganPenandatangan;
    }

    public byte[] getBarcodeImage() {
        return barcodeImage;
    }

    public void setBarcodeImage(byte[] barcodeImage) {
        this.barcodeImage = barcodeImage;
    }
}
