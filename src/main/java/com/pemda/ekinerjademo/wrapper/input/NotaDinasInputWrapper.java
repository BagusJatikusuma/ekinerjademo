package com.pemda.ekinerjademo.wrapper.input;

import java.util.Set;

/**
 * Created by bayu on 08/12/17.
 */
public class NotaDinasInputWrapper {
    private String nomorUrusan;
    private String nomorPasanganUrut;
    private String nomorUnit;
    private String kdJabatanPenerimaNotaDinas;
    private String nipPemberiNotaDinas;
    private String hal;
    private String isiNotaDinas;
    private String nipPenandatangan;
    private String nipPembuatSurat;
    private String kdUnitKerja;
    private Integer durasiPengerjaan;

    private Set<String> kdTembusanList;

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

    public String getKdJabatanPenerimaNotaDinas() {
        return kdJabatanPenerimaNotaDinas;
    }

    public void setKdJabatanPenerimaNotaDinas(String kdJabatanPenerimaNotaDinas) {
        this.kdJabatanPenerimaNotaDinas = kdJabatanPenerimaNotaDinas;
    }

    public String getNipPemberiNotaDinas() {
        return nipPemberiNotaDinas;
    }

    public void setNipPemberiNotaDinas(String nipPemberiNotaDinas) {
        this.nipPemberiNotaDinas = nipPemberiNotaDinas;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public String getIsiNotaDinas() {
        return isiNotaDinas;
    }

    public void setIsiNotaDinas(String isiNotaDinas) {
        this.isiNotaDinas = isiNotaDinas;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
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

    public Set<String> getKdTembusanList() {
        return kdTembusanList;
    }

    public void setKdTembusanList(Set<String> kdTembusanList) {
        this.kdTembusanList = kdTembusanList;
    }

    public Integer getDurasiPengerjaan() {
        return durasiPengerjaan;
    }

    public void setDurasiPengerjaan(Integer durasiPengerjaan) {
        this.durasiPengerjaan = durasiPengerjaan;
    }
}
