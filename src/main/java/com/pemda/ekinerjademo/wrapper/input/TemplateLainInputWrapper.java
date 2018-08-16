package com.pemda.ekinerjademo.wrapper.input;

import java.util.List;

/**
 * Created by bagus on 26/12/17.
 */
public class TemplateLainInputWrapper {
    private String kdUnitKerja;

    private String nipPegawai;

    private String keterangan;

    private Integer durasiPengerjaan;

    private String kdNaskahPenugasan;

    private Integer jenisNaskahPenugasan;

    /**bagian awal pelaporan urtug non dpa**/
    private String kdUrtug;

    private String kdJabatan;

    private Integer tahunUrtug;
    /** tambahan untuk rekap data urtug **/
    private String kdJenisUrtug;
    private Integer bulanUrtug;
    /** end tambahan untuk rekap data urtug **/
    /**bagian akhir pelaporan urtug non dpa**/

    /**bagian awal pelaporan dpa**/
    private Integer kdUrusan;
    private Integer kdBidang;
    private Integer kdUnit;
    private Integer kdSub;
    private Integer tahun;
    private Integer kdProg;
    private Integer idProg;
    private Integer kdKeg;
    private String kdStatusPenanggungJawab;
    /**bagian akhir pelaporan dpa**/

    private String namaFile;

    private String kdTemplateLainBawahan;

    private String namaFileLaporanBawahan;

    private List<UrtugBulananIdInputWrapper> daftarUrtugAtasan;

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public Integer getTahunUrtug() {
        return tahunUrtug;
    }

    public void setTahunUrtug(Integer tahunUrtug) {
        this.tahunUrtug = tahunUrtug;
    }

    public String getKdTemplateLainBawahan() {
        return kdTemplateLainBawahan;
    }

    public void setKdTemplateLainBawahan(String kdTemplateLainBawahan) {
        this.kdTemplateLainBawahan = kdTemplateLainBawahan;
    }

    public String getNamaFileLaporanBawahan() {
        return namaFileLaporanBawahan;
    }

    public void setNamaFileLaporanBawahan(String namaFileLaporanBawahan) {
        this.namaFileLaporanBawahan = namaFileLaporanBawahan;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public Integer getDurasiPengerjaan() {
        return durasiPengerjaan;
    }

    public void setDurasiPengerjaan(Integer durasiPengerjaan) {
        this.durasiPengerjaan = durasiPengerjaan;
    }

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    public Integer getBulanUrtug() {
        return bulanUrtug;
    }

    public void setBulanUrtug(Integer bulanUrtug) {
        this.bulanUrtug = bulanUrtug;
    }

    public List<UrtugBulananIdInputWrapper> getDaftarUrtugAtasan() {
        return daftarUrtugAtasan;
    }

    public void setDaftarUrtugAtasan(List<UrtugBulananIdInputWrapper> daftarUrtugAtasan) {
        this.daftarUrtugAtasan = daftarUrtugAtasan;
    }

    public Integer getKdUrusan() {
        return kdUrusan;
    }

    public void setKdUrusan(Integer kdUrusan) {
        this.kdUrusan = kdUrusan;
    }

    public Integer getKdBidang() {
        return kdBidang;
    }

    public void setKdBidang(Integer kdBidang) {
        this.kdBidang = kdBidang;
    }

    public Integer getKdUnit() {
        return kdUnit;
    }

    public void setKdUnit(Integer kdUnit) {
        this.kdUnit = kdUnit;
    }

    public Integer getKdSub() {
        return kdSub;
    }

    public void setKdSub(Integer kdSub) {
        this.kdSub = kdSub;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public Integer getKdProg() {
        return kdProg;
    }

    public void setKdProg(Integer kdProg) {
        this.kdProg = kdProg;
    }

    public Integer getIdProg() {
        return idProg;
    }

    public void setIdProg(Integer idProg) {
        this.idProg = idProg;
    }

    public Integer getKdKeg() {
        return kdKeg;
    }

    public void setKdKeg(Integer kdKeg) {
        this.kdKeg = kdKeg;
    }

    public String getKdStatusPenanggungJawab() {
        return kdStatusPenanggungJawab;
    }

    public void setKdStatusPenanggungJawab(String kdStatusPenanggungJawab) {
        this.kdStatusPenanggungJawab = kdStatusPenanggungJawab;
    }
}
