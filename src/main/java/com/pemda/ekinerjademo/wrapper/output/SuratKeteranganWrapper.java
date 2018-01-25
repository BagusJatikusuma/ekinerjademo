package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

import java.util.List;

public class SuratKeteranganWrapper {
    private String nomorUrusan;
    private Integer nomorUrut;
    private String nomorPasanganUrut;
    private String nomorUnit;
    private Integer nomorTahun;

    private String nipPenandatangan;
    private String namaPenandatangan;
    private String jabatanPenandatangan;
    private String unitKerjaPenandatangan;

    private String isiSuratKeterangan;
    private String kotaPembuatanSurat;
    private Long tanggalPembuatanMilis;

    private List<CustomPegawaiCredential> daftarPegawaiKeterangan;

    public SuratKeteranganWrapper() {
    }

    public SuratKeteranganWrapper(String nomorUrusan,
                                  Integer nomorUrut,
                                  String nomorPasanganUrut,
                                  String nomorUnit,
                                  Integer nomorTahun,
                                  String nipPenandatangan,
                                  String namaPenandatangan,
                                  String jabatanPenandatangan,
                                  String unitKerjaPenandatangan,
                                  String isiSuratKeterangan,
                                  String kotaPembuatanSurat,
                                  Long tanggalPembuatanMilis,
                                  List<CustomPegawaiCredential> daftarPegawaiKeterangan) {
        this.nomorUrusan = nomorUrusan;
        this.nomorUrut = nomorUrut;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
        this.isiSuratKeterangan = isiSuratKeterangan;
        this.kotaPembuatanSurat = kotaPembuatanSurat;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.daftarPegawaiKeterangan = daftarPegawaiKeterangan;
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

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public List<CustomPegawaiCredential> getDaftarPegawaiKeterangan() {
        return daftarPegawaiKeterangan;
    }

    public void setDaftarPegawaiKeterangan(List<CustomPegawaiCredential> daftarPegawaiKeterangan) {
        this.daftarPegawaiKeterangan = daftarPegawaiKeterangan;
    }
}
