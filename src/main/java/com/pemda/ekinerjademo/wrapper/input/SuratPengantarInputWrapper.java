package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;

/**
 * Created by bagus on 22/01/18.
 */
public class SuratPengantarInputWrapper {
    private String nomorUrusan;
    private String nomorPasanganUrut;
    private String nomorUnit;

    private Long tanggalPembuatanMilis;
    private Long tanggalDiterimaSuratPengantar;
    private String kdJabatanPenerimaSuratPengantar;
    private String nipPenerimaSuratPengantar;
    private String nipPemberiSuratPengantar;
    private String nomorTeleponPemberi;
    private String nipPembuatSurat;

    private String kdUnitKerja;
    private String kdNaskahPenugasan;
    private Integer jenisNaskahPenugasan;
    private Integer durasiPengerjaan;

    private String kdSuratPengantarBawahan;

    private List<SuratPengantarIsiInputWrapper> suratPengantarIsiList;

    private String kdUrtug;
    private Integer tahunUrtug;

    public String getNomorUrusan() {
        return nomorUrusan;
    }

    public void setNomorUrusan(String nomorUrusan) {
        this.nomorUrusan = nomorUrusan;
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

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public Long getTanggalDiterimaSuratPengantar() {
        return tanggalDiterimaSuratPengantar;
    }

    public void setTanggalDiterimaSuratPengantar(Long tanggalDiterimaSuratPengantar) {
        this.tanggalDiterimaSuratPengantar = tanggalDiterimaSuratPengantar;
    }

    public String getKdJabatanPenerimaSuratPengantar() {
        return kdJabatanPenerimaSuratPengantar;
    }

    public void setKdJabatanPenerimaSuratPengantar(String kdJabatanPenerimaSuratPengantar) {
        this.kdJabatanPenerimaSuratPengantar = kdJabatanPenerimaSuratPengantar;
    }

    public String getNipPenerimaSuratPengantar() {
        return nipPenerimaSuratPengantar;
    }

    public void setNipPenerimaSuratPengantar(String nipPenerimaSuratPengantar) {
        this.nipPenerimaSuratPengantar = nipPenerimaSuratPengantar;
    }

    public String getNipPemberiSuratPengantar() {
        return nipPemberiSuratPengantar;
    }

    public void setNipPemberiSuratPengantar(String nipPemberiSuratPengantar) {
        this.nipPemberiSuratPengantar = nipPemberiSuratPengantar;
    }

    public String getNomorTeleponPemberi() {
        return nomorTeleponPemberi;
    }

    public void setNomorTeleponPemberi(String nomorTeleponPemberi) {
        this.nomorTeleponPemberi = nomorTeleponPemberi;
    }

    public String getNipPembuatSurat() {
        return nipPembuatSurat;
    }

    public void setNipPembuatSurat(String nipPembuatSurat) {
        this.nipPembuatSurat = nipPembuatSurat;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getKdNaskahPenugasan() {
        return kdNaskahPenugasan;
    }

    public void setKdNaskahPenugasan(String kdNaskahPenugasan) {
        this.kdNaskahPenugasan = kdNaskahPenugasan;
    }

    public Integer getJenisNaskahPenugasan() {
        return jenisNaskahPenugasan;
    }

    public void setJenisNaskahPenugasan(Integer jenisNaskahPenugasan) {
        this.jenisNaskahPenugasan = jenisNaskahPenugasan;
    }

    public Integer getDurasiPengerjaan() {
        return durasiPengerjaan;
    }

    public void setDurasiPengerjaan(Integer durasiPengerjaan) {
        this.durasiPengerjaan = durasiPengerjaan;
    }

    public String getKdSuratPengantarBawahan() {
        return kdSuratPengantarBawahan;
    }

    public void setKdSuratPengantarBawahan(String kdSuratPengantarBawahan) {
        this.kdSuratPengantarBawahan = kdSuratPengantarBawahan;
    }

    public List<SuratPengantarIsiInputWrapper> getSuratPengantarIsiList() {
        return suratPengantarIsiList;
    }

    public void setSuratPengantarIsiList(List<SuratPengantarIsiInputWrapper> suratPengantarIsiList) {
        this.suratPengantarIsiList = suratPengantarIsiList;
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
