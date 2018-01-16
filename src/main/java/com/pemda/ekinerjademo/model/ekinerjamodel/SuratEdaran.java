package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bayu on 10/01/18.
 */
@Entity
@Table(name = "surat_edaran")
public class SuratEdaran {
    @Id
    @Column(name = "kd_surat_edaran")
    private String kdSuratEdaran;

    @Column(name = "nomor_urut")
    private Integer nomorUrut;
    @Column(name = "nomor_tahun")
    private Integer nomorTahun;

    @Column(name = "tentang")
    private String tentang;
    @Column(name = "tanggal_pembuatan_milis")
    private Long tanggalPembuatanMilis;
    @Column(name = "kota_pembuatan_surat")
    private String kotaPembuatanSurat;
    @Column(name = "nip_penandatangan")
    private String nipPenandatangan;
    @Column(name = "nip_pembuat_surat")
    private String nipPembuatSurat;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
    @Column(name = "latar_belakang")
    private String latarBelakang;
    @Column(name = "maksud_dan_tujuan")
    private String maksudDanTujuan;
    @Column(name = "ruang_lingkup")
    private String ruangLingkup;
    @Column(name = "dasar")
    private String dasar;

    @Column(name = "kd_naskah_penugasan")
    private String kdNaskahPenugasan;
    @Column(name = "jenis_naskah_penugasan")
    private Integer jenisNaskahPenugasan;
    @Column(name = "durasi_pengerjaan")
    private Integer durasiPengerjaan;
    @Column(name = "path_penilaian")
    private String pathPenilaian;
    @Column(name = "nip_penilai")
    private String nipPenilai;
    @Column(name = "status_penilaian")
    private Integer statusPenilaian;
    @Column(name = "alasan_penilaian")
    private String alasanPenolakan;

    @OneToOne(mappedBy = "suratEdaran", fetch = FetchType.LAZY)
    private SuratEdaranNonPejabat suratEdaranNonPejabat;
    @OneToOne(mappedBy = "suratEdaran", fetch = FetchType.LAZY)
    private SuratEdaranPejabat suratEdaranPejabat;
    @OneToMany(mappedBy = "suratEdaran", fetch = FetchType.LAZY)
    private List<SuratEdaranSub> suratEdaranSubList;

    public String getKdSuratEdaran() {
        return kdSuratEdaran;
    }

    public void setKdSuratEdaran(String kdSuratEdaran) {
        this.kdSuratEdaran = kdSuratEdaran;
    }

    public Integer getNomorUrut() {
        return nomorUrut;
    }

    public void setNomorUrut(Integer nomorUrut) {
        this.nomorUrut = nomorUrut;
    }

    public Integer getNomorTahun() {
        return nomorTahun;
    }

    public void setNomorTahun(Integer nomorTahun) {
        this.nomorTahun = nomorTahun;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public String getKotaPembuatanSurat() {
        return kotaPembuatanSurat;
    }

    public void setKotaPembuatanSurat(String kotaPembuatanSurat) {
        this.kotaPembuatanSurat = kotaPembuatanSurat;
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

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
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

    public String getPathPenilaian() {
        return pathPenilaian;
    }

    public void setPathPenilaian(String pathPenilaian) {
        this.pathPenilaian = pathPenilaian;
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

    public SuratEdaranNonPejabat getSuratEdaranNonPejabat() {
        return suratEdaranNonPejabat;
    }

    public void setSuratEdaranNonPejabat(SuratEdaranNonPejabat suratEdaranNonPejabat) {
        this.suratEdaranNonPejabat = suratEdaranNonPejabat;
    }

    public SuratEdaranPejabat getSuratEdaranPejabat() {
        return suratEdaranPejabat;
    }

    public void setSuratEdaranPejabat(SuratEdaranPejabat suratEdaranPejabat) {
        this.suratEdaranPejabat = suratEdaranPejabat;
    }

    public List<SuratEdaranSub> getSuratEdaranSubList() {
        return suratEdaranSubList;
    }

    public void setSuratEdaranSubList(List<SuratEdaranSub> suratEdaranSubList) {
        this.suratEdaranSubList = suratEdaranSubList;
    }
}
