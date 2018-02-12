package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by bayu on 08/12/17.
 */
@Entity
@Table(name = "nota_dinas")
public class NotaDinas {
    @Id
    @Column(name = "kd_nota_dinas")
    private String kdNotaDinas;
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

    @Column(name = "kd_jabatan_penerima_nota_dinas")
    private String kdJabatanPenerimaNotaDinas;
    @Column(name = "nip_pemberi_nota_dinas")
    private String nipPemberiNotaDinas;
    @Column(name = "hal")
    private String hal;
    @Column(name = "tanggal_pembuatan_milis")
    private Long tanggalPembuatanMilis;
    @Column(name = "isi_nota_dinas")
    private String isiNotaDinas;
    @Column(name = "nip_penandatangan")
    private String nipPenandatangan;
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

    @Column(name = "kd_barcode")
    private String kdBarcode;

    @OneToMany(mappedBy = "notaDinas")
    private List<TembusanNotaDinas> tembusanNotaDinasList;

    public String getKdNotaDinas() {
        return kdNotaDinas;
    }

    public void setKdNotaDinas(String kdNotaDinas) {
        this.kdNotaDinas = kdNotaDinas;
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

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
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

    public String getKdBarcode() {
        return kdBarcode;
    }

    public void setKdBarcode(String kdBarcode) {
        this.kdBarcode = kdBarcode;
    }

    public List<TembusanNotaDinas> getTembusanNotaDinasList() {
        return tembusanNotaDinasList;
    }

    public void setTembusanNotaDinasList(List<TembusanNotaDinas> tembusanNotaDinasList) {
        this.tembusanNotaDinasList = tembusanNotaDinasList;
    }
}
