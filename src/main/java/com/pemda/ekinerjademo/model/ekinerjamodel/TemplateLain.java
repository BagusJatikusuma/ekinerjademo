package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bagus on 26/12/17.
 */
@Entity
@Table(name = "template_lain")
public class TemplateLain {
    @Id
    @Column(name = "kd_template_lain")
    private String kdTemplateLain;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
    @Column(name = "nip_pembuat")
    private String nipPegawai;
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "path_file")
    private String pathFile;
    @Column(name = "kd_naskah_penugasan")
    private String kdNaskahPenugasan;
    @Column(name = "jenis_naskah_penugasan")
    private Integer jenisNaskahPenugasan;

    @Column(name = "kd_urtug")
    private String kdUrtug;
    @Column(name = "kd_jabatan")
    private String kdJabatan;
    @Column(name = "tahun_urtug")
    private Integer tahunUrtug;
    @Column(name = "kd_jenis_urtug")
    private String kdJenisUrtug;
    @Column(name = "bulan_urtug")
    private Integer bulanUrtug;

    @Column(name = "path_penilaian")
    private String pathPenilaian;
    @Column(name = "status_penilaian")
    private Integer statusPenilaian;
    @Column(name = "tanggal_pembuatan_milis")
    private Long tanggalPembuatanMilis;
    @Column(name = "alasan_penolakan")
    private String alasanPenolakan;
    @Column(name = "durasi_pengerjaan")
    private Integer durasiPengerjaan;

    @Column(name = "status_baca")
    private Integer statusBaca;

    @Column(name = "approval_sekretaris")
    private Integer approvalSekretaris;
    @Column(name = "approval_penandatangan")
    private Integer approvalPenandatangan;

    public String getKdTemplateLain() {
        return kdTemplateLain;
    }

    public void setKdTemplateLain(String kdTemplateLain) {
        this.kdTemplateLain = kdTemplateLain;
    }

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

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
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

    public String getPathPenilaian() {
        return pathPenilaian;
    }

    public void setPathPenilaian(String pathPenilaian) {
        this.pathPenilaian = pathPenilaian;
    }

    public Integer getStatusPenilaian() {
        return statusPenilaian;
    }

    public void setStatusPenilaian(Integer statusPenilaian) {
        this.statusPenilaian = statusPenilaian;
    }

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public String getAlasanPenolakan() {
        return alasanPenolakan;
    }

    public void setAlasanPenolakan(String alasanPenolakan) {
        this.alasanPenolakan = alasanPenolakan;
    }

    public Integer getDurasiPengerjaan() {
        return durasiPengerjaan;
    }

    public void setDurasiPengerjaan(Integer durasiPengerjaan) {
        this.durasiPengerjaan = durasiPengerjaan;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }

    public Integer getApprovalSekretaris() {
        return approvalSekretaris;
    }

    public void setApprovalSekretaris(Integer approvalSekretaris) {
        this.approvalSekretaris = approvalSekretaris;
    }

    public Integer getApprovalPenandatangan() {
        return approvalPenandatangan;
    }

    public void setApprovalPenandatangan(Integer approvalPenandatangan) {
        this.approvalPenandatangan = approvalPenandatangan;
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
}
