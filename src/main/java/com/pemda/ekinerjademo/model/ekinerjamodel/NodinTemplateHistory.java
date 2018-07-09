package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by bagus on 04/11/17.
 */
@Entity
@Table(name = "nodin_template_history")
public class NodinTemplateHistory {
    @Id
    @Column(name = "kd_history")
    private String kdHistory;
    @Column(name = "nip_pegawai")
    private String nipPegawai;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
    @Column(name = "nm_instansi")
    private String nmInstansi;
    @Column(name = "nomor_surat_1")
    private Integer nomorSurat1;
    @Column(name = "nomor_surat_2")
    private Integer nomorSurat2;
    @Column(name = "nomor_surat_3")
    private String nomorSurat3;
    @Column(name = "nomor_surat_tahun")
    private Integer nomorSuratTahun;
    @Column(name = "nm_tujuan")
    private String nmTujuan;
    @Column(name = "nm_pemberi")
    private String nmPemberi;
    @Column(name = "hal")
    private String hal;
    @Column(name = "tanggal")
    private Date tanggal;
    @Column(name = "pembuka_surat")
    private String pembukaSurat;
    @Column(name = "isi_surat")
    private String isiSurat;
    @Column(name = "penutup_surat")
    private String penutupSurat;
    @Column(name = "nm_lengkap")
    private String nmLengkap;
    @Column(name = "tembusan_surat")
    private String tembusanSurat;
    @Column(name = "created_date_time_milis")
    private Long createdDateTimeMilis;
    @Column(name = "document_file_name")
    private String documentFileName;

    public NodinTemplateHistory() {
    }
    public NodinTemplateHistory(
            String kdHistory,
            String nipPegawai,
            String kdUnitKerja,
            String nmInstansi,
            Integer nomorSurat1,
            Integer nomorSurat2,
            String nomorSurat3,
            Integer nomorSuratTahun,
            String nmTujuan,
            String nmPemberi,
            String hal,
            Date tanggal,
            String pembukaSurat,
            String isiSurat,
            String penutupSurat,
            String nmLengkap,
            String tembusanSurat,
            Long createdDateTimeMilis,
            String documentFileName) {
        this.kdHistory = kdHistory;
        this.nipPegawai = nipPegawai;
        this.kdUnitKerja = kdUnitKerja;
        this.nmInstansi = nmInstansi;
        this.nomorSurat1 = nomorSurat1;
        this.nomorSurat2 = nomorSurat2;
        this.nomorSurat3 = nomorSurat3;
        this.nomorSuratTahun = nomorSuratTahun;
        this.nmTujuan = nmTujuan;
        this.nmPemberi = nmPemberi;
        this.hal = hal;
        this.tanggal = tanggal;
        this.pembukaSurat = pembukaSurat;
        this.isiSurat = isiSurat;
        this.penutupSurat = penutupSurat;
        this.nmLengkap = nmLengkap;
        this.tembusanSurat = tembusanSurat;
        this.createdDateTimeMilis = createdDateTimeMilis;
        this.documentFileName = documentFileName;
    }

    public String getKdHistory() {
        return kdHistory;
    }

    public void setKdHistory(String kdHistory) {
        this.kdHistory = kdHistory;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getNmInstansi() {
        return nmInstansi;
    }

    public void setNmInstansi(String nmInstansi) {
        this.nmInstansi = nmInstansi;
    }

    public Integer getNomorSurat1() {
        return nomorSurat1;
    }

    public void setNomorSurat1(Integer nomorSurat1) {
        this.nomorSurat1 = nomorSurat1;
    }

    public Integer getNomorSurat2() {
        return nomorSurat2;
    }

    public void setNomorSurat2(Integer nomorSurat2) {
        this.nomorSurat2 = nomorSurat2;
    }

    public String getNomorSurat3() {
        return nomorSurat3;
    }

    public void setNomorSurat3(String nomorSurat3) {
        this.nomorSurat3 = nomorSurat3;
    }

    public Integer getNomorSuratTahun() {
        return nomorSuratTahun;
    }

    public void setNomorSuratTahun(Integer nomorSuratTahun) {
        this.nomorSuratTahun = nomorSuratTahun;
    }

    public String getNmTujuan() {
        return nmTujuan;
    }

    public void setNmTujuan(String nmTujuan) {
        this.nmTujuan = nmTujuan;
    }

    public String getNmPemberi() {
        return nmPemberi;
    }

    public void setNmPemberi(String nmPemberi) {
        this.nmPemberi = nmPemberi;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getPembukaSurat() {
        return pembukaSurat;
    }

    public void setPembukaSurat(String pembukaSurat) {
        this.pembukaSurat = pembukaSurat;
    }

    public String getIsiSurat() {
        return isiSurat;
    }

    public void setIsiSurat(String isiSurat) {
        this.isiSurat = isiSurat;
    }

    public String getPenutupSurat() {
        return penutupSurat;
    }

    public void setPenutupSurat(String penutupSurat) {
        this.penutupSurat = penutupSurat;
    }

    public String getNmLengkap() {
        return nmLengkap;
    }

    public void setNmLengkap(String nmLengkap) {
        this.nmLengkap = nmLengkap;
    }

    public String getTembusanSurat() {
        return tembusanSurat;
    }

    public void setTembusanSurat(String tembusanSurat) {
        this.tembusanSurat = tembusanSurat;
    }

    public Long getCreatedDateTimeMilis() {
        return createdDateTimeMilis;
    }

    public void setCreatedDateTimeMilis(Long createdDateTimeMilis) {
        this.createdDateTimeMilis = createdDateTimeMilis;
    }

    public String getDocumentFileName() {
        return documentFileName;
    }

    public void setDocumentFileName(String documentFileName) {
        this.documentFileName = documentFileName;
    }
}
