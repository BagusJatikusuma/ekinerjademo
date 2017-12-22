package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bayu on 07/12/17.
 */
@Entity
@Table(name = "berita_acara")
public class BeritaAcara {

    @Id
    @Column(name = "kd_berita_acara")
    private String kdBeritaAcara;
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

    @Column(name = "nip_pihak_kesatu")
    private String nipPihakKesatu;
    @Column(name = "peran_pihak_kesatu")
    private String peranPihakKesatu;
    @Column(name = "status_approval_pihak_kesatu")
    private Integer statusApprovalPihakKesatu;

    @Column(name = "nip_pihak_kedua")
    private String nipPihakKedua;
    @Column(name = "peran_pihak_kedua")
    private String peranPihakKedua;
    @Column(name = "status_approval_pihak_kedua")
    private Integer statusApprovalPihakKedua;

    @Column(name = "isi_berita_acara")
    private String isiBeritaAcara;
    @Column(name = "dasar_berita_acara")
    private String dasarBeritaAcara;
    @Column(name = "nip_mengetahui")
    private String nipMengetahui;
    @Column(name = "status_approval_nip_mengetahui")
    private Integer statusApprovalNipMengetahui;

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

    @Column(name = "kd_naskah_penugasan")
    private String kdNaskahPenugasan;
    @Column(name = "jenis_naskah_penugasan")
    private Integer jenisNaskahPenugasan;

    public String getKdBeritaAcara() {
        return kdBeritaAcara;
    }

    public void setKdBeritaAcara(String kdBeritaAcara) {
        this.kdBeritaAcara = kdBeritaAcara;
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

    public String getNipPihakKesatu() {
        return nipPihakKesatu;
    }

    public void setNipPihakKesatu(String nipPihakKesatu) {
        this.nipPihakKesatu = nipPihakKesatu;
    }

    public String getPeranPihakKesatu() {
        return peranPihakKesatu;
    }

    public void setPeranPihakKesatu(String peranPihakKesatu) {
        this.peranPihakKesatu = peranPihakKesatu;
    }

    public Integer getStatusApprovalPihakKesatu() {
        return statusApprovalPihakKesatu;
    }

    public void setStatusApprovalPihakKesatu(Integer statusApprovalPihakKesatu) {
        this.statusApprovalPihakKesatu = statusApprovalPihakKesatu;
    }

    public String getNipPihakKedua() {
        return nipPihakKedua;
    }

    public void setNipPihakKedua(String nipPihakKedua) {
        this.nipPihakKedua = nipPihakKedua;
    }

    public String getPeranPihakKedua() {
        return peranPihakKedua;
    }

    public void setPeranPihakKedua(String peranPihakKedua) {
        this.peranPihakKedua = peranPihakKedua;
    }

    public Integer getStatusApprovalPihakKedua() {
        return statusApprovalPihakKedua;
    }

    public void setStatusApprovalPihakKedua(Integer statusApprovalPihakKedua) {
        this.statusApprovalPihakKedua = statusApprovalPihakKedua;
    }

    public String getIsiBeritaAcara() {
        return isiBeritaAcara;
    }

    public void setIsiBeritaAcara(String isiBeritaAcara) {
        this.isiBeritaAcara = isiBeritaAcara;
    }

    public String getDasarBeritaAcara() {
        return dasarBeritaAcara;
    }

    public void setDasarBeritaAcara(String dasarBeritaAcara) {
        this.dasarBeritaAcara = dasarBeritaAcara;
    }

    public String getNipMengetahui() {
        return nipMengetahui;
    }

    public void setNipMengetahui(String nipMengetahui) {
        this.nipMengetahui = nipMengetahui;
    }

    public Integer getStatusApprovalNipMengetahui() {
        return statusApprovalNipMengetahui;
    }

    public void setStatusApprovalNipMengetahui(Integer statusApprovalNipMengetahui) {
        this.statusApprovalNipMengetahui = statusApprovalNipMengetahui;
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
}
