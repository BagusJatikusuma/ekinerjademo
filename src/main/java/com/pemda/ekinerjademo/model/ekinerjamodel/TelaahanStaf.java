package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bayu on 15/12/17.
 */
@Entity
@Table(name = "telaahan_staf")
public class TelaahanStaf {
    @Id
    @Column(name = "kd_telaahan_staf")
    private String kdTelaahanStaf;
    @Column(name = "tentang")
    private String tentang;
    @Column(name = "persoalan")
    private String persoalan;
    @Column(name = "praanggapan")
    private String praanggapan;
    @Column(name = "fakta_yang_mempengaruhi")
    private String faktaYangMempengaruhi;
    @Column(name = "analisis")
    private String analisis;
    @Column(name = "simpulan")
    private String simpulan;
    @Column(name = "saran")
    private String saran;

    @Column(name = "status_baca")
    private Integer statusBaca;
    @Column(name = "nip_penandatangan")
    private String nipPenandatangan;
    @Column(name = "tanggal_pembuatan_milis")
    private Long tanggalPembuatanMilis;
    @Column(name = "nip_pembuat_surat")
    private String nipPembuatSurat;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;

    @Column(name = "durasi_pengerjaan")
    private Integer durasiPengerjaan;
    @Column(name = "nip_penilai")
    private String nipPenilai;
    @Column(name = "status_penilaian")
    private Integer statusPenilaian;
    @Column(name = "alasan_penolakan")
    private String alasanPenolakan;

    public String getKdTelaahanStaf() {
        return kdTelaahanStaf;
    }

    public void setKdTelaahanStaf(String kdTelaahanStaf) {
        this.kdTelaahanStaf = kdTelaahanStaf;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getPersoalan() {
        return persoalan;
    }

    public void setPersoalan(String persoalan) {
        this.persoalan = persoalan;
    }

    public String getPraanggapan() {
        return praanggapan;
    }

    public void setPraanggapan(String praanggapan) {
        this.praanggapan = praanggapan;
    }

    public String getFaktaYangMempengaruhi() {
        return faktaYangMempengaruhi;
    }

    public void setFaktaYangMempengaruhi(String faktaYangMempengaruhi) {
        this.faktaYangMempengaruhi = faktaYangMempengaruhi;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public String getSimpulan() {
        return simpulan;
    }

    public void setSimpulan(String simpulan) {
        this.simpulan = simpulan;
    }

    public String getSaran() {
        return saran;
    }

    public void setSaran(String saran) {
        this.saran = saran;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
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

    public Integer getDurasiPengerjaan() {
        return durasiPengerjaan;
    }

    public void setDurasiPengerjaan(Integer durasiPengerjaan) {
        this.durasiPengerjaan = durasiPengerjaan;
    }

    public String getNipPenilai() {
        return nipPenilai;
    }

    public void setNipPenilai(String nipPenilai) {
        this.nipPenilai = nipPenilai;
    }

    public Integer getStatusPenilaian() {
        return statusPenilaian;
    }

    public void setStatusPenilaian(Integer statusPenilaian) {
        this.statusPenilaian = statusPenilaian;
    }

    public String getAlasanPenolakan() {
        return alasanPenolakan;
    }

    public void setAlasanPenolakan(String alasanPenolakan) {
        this.alasanPenolakan = alasanPenolakan;
    }
}
