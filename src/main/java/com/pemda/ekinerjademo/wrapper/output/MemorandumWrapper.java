package com.pemda.ekinerjademo.wrapper.output;

import javax.persistence.Column;

/**
 * Created by bagus on 21/01/18.
 */
public class MemorandumWrapper {
    private String kdMemorandum;

    private String nomorUrusan;
    private Integer nomorUrut;
    private String nomorPasanganUrut;
    private String nomorUnit;
    private Integer nomorTahun;

    private String nipPenerimaMemorandum;
    private String namaPenerimaMemorandum;
    private String jabatanPenerimaMemorandum;
    private String unitKerjaPenerimaMemorandum;

    private String nipPemberiMemorandum;
    private String namaPemberiMemorandum;
    private String jabatanPemberiMemorandum;
    private String unitKerjaPemberiMemorandum;

    private String hal;
    private Long tanggalPembuatanMilis;
    private String isiMemorandum;

    private String nipPembuatSurat;
    private String namaPembuatSurat;
    private String jabatanPembuatSurat;
    private String unitKerjaPembuatSurat;

    private String nipPenandatangan;
    private String namaPenandatangan;
    private String jabatanPenandatangan;
    private String unitKerjaPenandatangan;

    public MemorandumWrapper() {
    }

    public MemorandumWrapper(
            String kdMemorandum,
            String nomorUrusan,
            Integer nomorUrut,
            String nomorPasanganUrut,
            String nomorUnit,
            Integer nomorTahun,
            String nipPenerimaMemorandum,
            String namaPenerimaMemorandum,
            String jabatanPenerimaMemorandum,
            String unitKerjaPenerimaMemorandum,
            String nipPemberiMemorandum,
            String namaPemberiMemorandum,
            String jabatanPemberiMemorandum,
            String unitKerjaPemberiMemorandum,
            String hal,
            Long tanggalPembuatanMilis,
            String isiMemorandum,
            String nipPembuatSurat,
            String namaPembuatSurat,
            String jabatanPembuatSurat,
            String unitKerjaPembuatSurat,
            String nipPenandatangan,
            String namaPenandatangan,
            String jabatanPenandatangan,
            String unitKerjaPenandatangan) {
        this.kdMemorandum = kdMemorandum;
        this.nomorUrusan = nomorUrusan;
        this.nomorUrut = nomorUrut;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.nipPenerimaMemorandum = nipPenerimaMemorandum;
        this.namaPenerimaMemorandum = namaPenerimaMemorandum;
        this.jabatanPenerimaMemorandum = jabatanPenerimaMemorandum;
        this.unitKerjaPenerimaMemorandum = unitKerjaPenerimaMemorandum;
        this.nipPemberiMemorandum = nipPemberiMemorandum;
        this.namaPemberiMemorandum = namaPemberiMemorandum;
        this.jabatanPemberiMemorandum = jabatanPemberiMemorandum;
        this.unitKerjaPemberiMemorandum = unitKerjaPemberiMemorandum;
        this.hal = hal;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.isiMemorandum = isiMemorandum;
        this.nipPembuatSurat = nipPembuatSurat;
        this.namaPembuatSurat = namaPembuatSurat;
        this.jabatanPembuatSurat = jabatanPembuatSurat;
        this.unitKerjaPembuatSurat = unitKerjaPembuatSurat;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
    }

    public String getKdMemorandum() {
        return kdMemorandum;
    }

    public void setKdMemorandum(String kdMemorandum) {
        this.kdMemorandum = kdMemorandum;
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

    public String getNipPenerimaMemorandum() {
        return nipPenerimaMemorandum;
    }

    public void setNipPenerimaMemorandum(String nipPenerimaMemorandum) {
        this.nipPenerimaMemorandum = nipPenerimaMemorandum;
    }

    public String getNamaPenerimaMemorandum() {
        return namaPenerimaMemorandum;
    }

    public void setNamaPenerimaMemorandum(String namaPenerimaMemorandum) {
        this.namaPenerimaMemorandum = namaPenerimaMemorandum;
    }

    public String getJabatanPenerimaMemorandum() {
        return jabatanPenerimaMemorandum;
    }

    public void setJabatanPenerimaMemorandum(String jabatanPenerimaMemorandum) {
        this.jabatanPenerimaMemorandum = jabatanPenerimaMemorandum;
    }

    public String getUnitKerjaPenerimaMemorandum() {
        return unitKerjaPenerimaMemorandum;
    }

    public void setUnitKerjaPenerimaMemorandum(String unitKerjaPenerimaMemorandum) {
        this.unitKerjaPenerimaMemorandum = unitKerjaPenerimaMemorandum;
    }

    public String getNipPemberiMemorandum() {
        return nipPemberiMemorandum;
    }

    public void setNipPemberiMemorandum(String nipPemberiMemorandum) {
        this.nipPemberiMemorandum = nipPemberiMemorandum;
    }

    public String getNamaPemberiMemorandum() {
        return namaPemberiMemorandum;
    }

    public void setNamaPemberiMemorandum(String namaPemberiMemorandum) {
        this.namaPemberiMemorandum = namaPemberiMemorandum;
    }

    public String getJabatanPemberiMemorandum() {
        return jabatanPemberiMemorandum;
    }

    public void setJabatanPemberiMemorandum(String jabatanPemberiMemorandum) {
        this.jabatanPemberiMemorandum = jabatanPemberiMemorandum;
    }

    public String getUnitKerjaPemberiMemorandum() {
        return unitKerjaPemberiMemorandum;
    }

    public void setUnitKerjaPemberiMemorandum(String unitKerjaPemberiMemorandum) {
        this.unitKerjaPemberiMemorandum = unitKerjaPemberiMemorandum;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public String getIsiMemorandum() {
        return isiMemorandum;
    }

    public void setIsiMemorandum(String isiMemorandum) {
        this.isiMemorandum = isiMemorandum;
    }

    public String getNipPembuatSurat() {
        return nipPembuatSurat;
    }

    public void setNipPembuatSurat(String nipPembuatSurat) {
        this.nipPembuatSurat = nipPembuatSurat;
    }

    public String getNamaPembuatSurat() {
        return namaPembuatSurat;
    }

    public void setNamaPembuatSurat(String namaPembuatSurat) {
        this.namaPembuatSurat = namaPembuatSurat;
    }

    public String getJabatanPembuatSurat() {
        return jabatanPembuatSurat;
    }

    public void setJabatanPembuatSurat(String jabatanPembuatSurat) {
        this.jabatanPembuatSurat = jabatanPembuatSurat;
    }

    public String getUnitKerjaPembuatSurat() {
        return unitKerjaPembuatSurat;
    }

    public void setUnitKerjaPembuatSurat(String unitKerjaPembuatSurat) {
        this.unitKerjaPembuatSurat = unitKerjaPembuatSurat;
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
