package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bayu on 07/12/17.
 */
@Entity
@Table(name = "surat_kuasa")
public class SuratKuasa {
    @Id
    @Column(name = "kd_surat_kuasa")
    private String kdSuratKuasa;
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

    @Column(name = "nip_pemberi_kuasa")
    private String nipPemberiKuasa;
    @Column(name = "nip_penerima_kuasa")
    private String nipPenerimaKuasa;

    @Column(name = "isi_kuasa")
    private String isiKuasa;

    @Column(name = "kota_pembuatan_surat")
    private String kotaPembuatanSurat;
    @Column(name = "tanggal_pembuatan_milis")
    private Long tanggalPembuatanMilis;
    @Column(name = "nip_pembuat_surat")
    private String nipPembuatSurat;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;

    public String getKdSuratKuasa() {
        return kdSuratKuasa;
    }

    public void setKdSuratKuasa(String kdSuratKuasa) {
        this.kdSuratKuasa = kdSuratKuasa;
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

    public String getNipPemberiKuasa() {
        return nipPemberiKuasa;
    }

    public void setNipPemberiKuasa(String nipPemberiKuasa) {
        this.nipPemberiKuasa = nipPemberiKuasa;
    }

    public String getNipPenerimaKuasa() {
        return nipPenerimaKuasa;
    }

    public void setNipPenerimaKuasa(String nipPenerimaKuasa) {
        this.nipPenerimaKuasa = nipPenerimaKuasa;
    }

    public String getIsiKuasa() {
        return isiKuasa;
    }

    public void setIsiKuasa(String isiKuasa) {
        this.isiKuasa = isiKuasa;
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
