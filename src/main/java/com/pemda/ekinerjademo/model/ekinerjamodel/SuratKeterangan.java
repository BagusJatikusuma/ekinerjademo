package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bayu on 10/01/18.
 */
@Entity
@Table(name = "surat_keterangan")
public class SuratKeterangan {
    @Id
    @Column(name = "kd_surat_keterangan")
    private String kdSuratKeterangan;

    @Column(name = "nomor_urusan")
    private String nomorUrusan;
    @Column(name = "nomor_urut")
    private Integer nomorUrut;
    @Column(name = "nomor_pasangan_urut")
    private String nomorPasanganUrut;
    @Column(name = "nomor_unit")
    private String nomorUnit;
    @Column(name = "nomor_tahun")
    private Integer nomorTahun;

    @Column(name = "nip_penandatangan")
    private String nipPenandatangan;
    @Column(name = "isi_surat_keterangan")
    private String isiSuratKeterangan;
    @Column(name = "kota_pembuatan_surat")
    private String kotaPembuatanSurat;
    @Column(name = "tanggal_pembuatan_surat")
    private Long tanggalPembuatanSuratMilis;
    @Column(name = "nip_pembuat_surat")
    private String nipPembuatSurat;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
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
    @Column(name = "alasan_penolakan")
    private String alasanPenolakan;
    @Column(name = "status_baca")
    private Integer statusBaca;
    @Column(name = "nip_pegawai_keterangan")
    private String nipPegawaiKeterangan;

    @Column(name = "kd_barcode")
    private String kdBarcode;

    @Column(name = "kd_urtug")
    private String kdUrtug;
    @Column(name = "tahun_urtug")
    private Integer tahunUrtug;

    @OneToMany(mappedBy = "suratKeterangan")
    private List<TargetSuratKeterangan> targetSuratKeteranganList;

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

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }

    public String getNipPegawaiKeterangan() {
        return nipPegawaiKeterangan;
    }

    public void setNipPegawaiKeterangan(String nipPegawaiKeterangan) {
        this.nipPegawaiKeterangan = nipPegawaiKeterangan;
    }

    public String getKdBarcode() {
        return kdBarcode;
    }

    public void setKdBarcode(String kdBarcode) {
        this.kdBarcode = kdBarcode;
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

    public List<TargetSuratKeterangan> getTargetSuratKeteranganList() {
        return targetSuratKeteranganList;
    }

    public void setTargetSuratKeteranganList(List<TargetSuratKeterangan> targetSuratKeteranganList) {
        this.targetSuratKeteranganList = targetSuratKeteranganList;
    }
}
