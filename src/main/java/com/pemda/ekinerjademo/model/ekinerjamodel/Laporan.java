package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bayu on 07/12/17.
 */
@Entity
@Table(name = "laporan")
public class Laporan {

    @Id
    @Column(name = "kd_laporan")
    private String kdLaporan;

    @Column(name = "tentang")
    private String tentang;
    @Column(name = "umum")
    private String umum;
    @Column(name = "maksud_dan_tujuan")
    private String maksudDanTujuan;
    @Column(name = "ruang_lingkup")
    private String ruangLingkup;
    @Column(name = "dasar")
    private String dasar;
    @Column(name = "kegiatan_yang_dilaksanakan")
    private String kegiatanYangDilaksanakan;
    @Column(name = "hasil_yang_dicapai")
    private String hasilYangDicapai;
    @Column(name = "simpulan_dan_saran")
    private String simpulanDanSaran;
    @Column(name = "penutup")
    private String penutup;
    @Column(name = "nip_penandatangan")
    private String nipPenandatangan;

    @Column(name = "status_baca")
    private Integer statusBaca;
    @Column(name = "kota_pembuatan_surat")
    private String kotaPembuatanSurat;
    @Column(name = "tanggal_pembuatan_milis")
    private Long tanggalPembuatanMilis;
    @Column(name = "nip_pembuat_surat")
    private String nipPembuatSurat;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;

    public String getKdLaporan() {
        return kdLaporan;
    }

    public void setKdLaporan(String kdLaporan) {
        this.kdLaporan = kdLaporan;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getUmum() {
        return umum;
    }

    public void setUmum(String umum) {
        this.umum = umum;
    }

    public String getMaksudDanTujuan() {
        return maksudDanTujuan;
    }

    public void setMaksudDanTujuan(String maksudDanTujuan) {
        this.maksudDanTujuan = maksudDanTujuan;
    }

    public String getRuangLingkup() {
        return ruangLingkup;
    }

    public void setRuangLingkup(String ruangLingkup) {
        this.ruangLingkup = ruangLingkup;
    }

    public String getDasar() {
        return dasar;
    }

    public void setDasar(String dasar) {
        this.dasar = dasar;
    }

    public String getKegiatanYangDilaksanakan() {
        return kegiatanYangDilaksanakan;
    }

    public void setKegiatanYangDilaksanakan(String kegiatanYangDilaksanakan) {
        this.kegiatanYangDilaksanakan = kegiatanYangDilaksanakan;
    }

    public String getHasilYangDicapai() {
        return hasilYangDicapai;
    }

    public void setHasilYangDicapai(String hasilYangDicapai) {
        this.hasilYangDicapai = hasilYangDicapai;
    }

    public String getSimpulanDanSaran() {
        return simpulanDanSaran;
    }

    public void setSimpulanDanSaran(String simpulanDanSaran) {
        this.simpulanDanSaran = simpulanDanSaran;
    }

    public String getPenutup() {
        return penutup;
    }

    public void setPenutup(String penutup) {
        this.penutup = penutup;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
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
}
