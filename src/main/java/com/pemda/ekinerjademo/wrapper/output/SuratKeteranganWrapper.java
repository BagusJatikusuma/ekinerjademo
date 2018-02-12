package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

import java.util.List;

/**
 * Created by bagus on 25/01/18.
 */
public class SuratKeteranganWrapper {
    private String kdSuratKeterangan;

    private String nomorUrusan;
    private Integer nomorUrut;
    private String nomorPasanganUrut;
    private String nomorUnit;
    private Integer nomorTahun;

    private String nipPenandatangan;
    private String namaPenandatangan;
    private String jabatanPenandatangan;
    private String unitKerjaPenandatangan;
    private String pangkatPenandatangan;
    private String golonganPenandatangan;
    private String gelarDepanPenandatangan;
    private String gelarBelakangPenandatangan;

    private String isiSuratKeterangan;
    private String kotaPembuatanSurat;
    private Long tanggalPembuatanSuratMilis;
    private List<CustomPegawaiCredential> nipPegawaiKeteranganList;
    private List<CustomPegawaiCredential> targetPegawaiSuratKeteranganList;
    private byte[] barcodeImage;

    public SuratKeteranganWrapper() {
    }

    public SuratKeteranganWrapper(String kdSuratKeterangan, String nomorUrusan, Integer nomorUrut, String nomorPasanganUrut, String nomorUnit, Integer nomorTahun, String nipPenandatangan, String namaPenandatangan, String jabatanPenandatangan, String unitKerjaPenandatangan, String pangkatPenandatangan, String golonganPenandatangan, String gelarDepanPenandatangan, String gelarBelakangPenandatangan, String isiSuratKeterangan, String kotaPembuatanSurat, Long tanggalPembuatanSuratMilis, List<CustomPegawaiCredential> nipPegawaiKeteranganList, List<CustomPegawaiCredential> targetPegawaiSuratKeteranganList, byte[] barcodeImage) {
        this.kdSuratKeterangan = kdSuratKeterangan;
        this.nomorUrusan = nomorUrusan;
        this.nomorUrut = nomorUrut;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
        this.pangkatPenandatangan = pangkatPenandatangan;
        this.golonganPenandatangan = golonganPenandatangan;
        this.gelarDepanPenandatangan = gelarDepanPenandatangan;
        this.gelarBelakangPenandatangan = gelarBelakangPenandatangan;
        this.isiSuratKeterangan = isiSuratKeterangan;
        this.kotaPembuatanSurat = kotaPembuatanSurat;
        this.tanggalPembuatanSuratMilis = tanggalPembuatanSuratMilis;
        this.nipPegawaiKeteranganList = nipPegawaiKeteranganList;
        this.targetPegawaiSuratKeteranganList = targetPegawaiSuratKeteranganList;
        this.barcodeImage = barcodeImage;
    }

    public String getKdSuratKeterangan() {
        return kdSuratKeterangan;
    }

    public void setKdSuratKeterangan(String kdSuratKeterangan) {
        this.kdSuratKeterangan = kdSuratKeterangan;
    }

    public String getNomorUrusan() {
        return nomorUrusan;
    }

    public void setNomorUrusan(String nomorUrusan) {
        this.nomorUrusan = nomorUrusan;
    }

    public Integer getNomorUrut() {
        return nomorUrut;
    }

    public void setNomorUrut(Integer nomorUrut) {
        this.nomorUrut = nomorUrut;
    }

    public String getNomorPasanganUrut() {
        return nomorPasanganUrut;
    }

    public void setNomorPasanganUrut(String nomorPasanganUrut) {
        this.nomorPasanganUrut = nomorPasanganUrut;
    }

    public String getNomorUnit() {
        return nomorUnit;
    }

    public void setNomorUnit(String nomorUnit) {
        this.nomorUnit = nomorUnit;
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

    public String getGelarDepanPenandatangan() {
        return gelarDepanPenandatangan;
    }

    public void setGelarDepanPenandatangan(String gelarDepanPenandatangan) {
        this.gelarDepanPenandatangan = gelarDepanPenandatangan;
    }

    public String getGelarBelakangPenandatangan() {
        return gelarBelakangPenandatangan;
    }

    public void setGelarBelakangPenandatangan(String gelarBelakangPenandatangan) {
        this.gelarBelakangPenandatangan = gelarBelakangPenandatangan;
    }

    public String getIsiSuratKeterangan() {
        return isiSuratKeterangan;
    }

    public void setIsiSuratKeterangan(String isiSuratKeterangan) {
        this.isiSuratKeterangan = isiSuratKeterangan;
    }

    public String getKotaPembuatanSurat() {
        return kotaPembuatanSurat;
    }

    public void setKotaPembuatanSurat(String kotaPembuatanSurat) {
        this.kotaPembuatanSurat = kotaPembuatanSurat;
    }

    public Long getTanggalPembuatanSuratMilis() {
        return tanggalPembuatanSuratMilis;
    }

    public void setTanggalPembuatanSuratMilis(Long tanggalPembuatanSuratMilis) {
        this.tanggalPembuatanSuratMilis = tanggalPembuatanSuratMilis;
    }

    public List<CustomPegawaiCredential> getNipPegawaiKeteranganList() {
        return nipPegawaiKeteranganList;
    }

    public void setNipPegawaiKeteranganList(List<CustomPegawaiCredential> nipPegawaiKeteranganList) {
        this.nipPegawaiKeteranganList = nipPegawaiKeteranganList;
    }

    public List<CustomPegawaiCredential> getTargetPegawaiSuratKeteranganList() {
        return targetPegawaiSuratKeteranganList;
    }

    public void setTargetPegawaiSuratKeteranganList(List<CustomPegawaiCredential> targetPegawaiSuratKeteranganList) {
        this.targetPegawaiSuratKeteranganList = targetPegawaiSuratKeteranganList;
    }

    public byte[] getBarcodeImage() {
        return barcodeImage;
    }

    public void setBarcodeImage(byte[] barcodeImage) {
        this.barcodeImage = barcodeImage;
    }
}
