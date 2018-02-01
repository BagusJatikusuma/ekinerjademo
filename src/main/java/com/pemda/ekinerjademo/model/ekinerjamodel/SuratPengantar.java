package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bayu on 09/01/18.
 */
@Entity
@Table(name = "surat_pengantar")
public class SuratPengantar {
    @Id
    @Column(name = "kd_surat_pengantar")
    private String kdSuratPengantar;

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

    @Column(name = "tanggal_pembuatan_milis")
    private Long tanggalPembuatanMilis;
    @Column(name = "tanggal_diterima_surat_pengantar")
    private Long tanggalDiterimaSuratPengantar;
    @Column(name = "kd_jabatan_penerima_surat_pengantar")
    private String kdJabatanPenerimaSuratPengantar;
    @Column(name = "nip_pemberi_surat_pengantar")
    private String nipPemberiSuratPengantar;
    @Column(name = "nomor_telepon_pemberi")
    private String nomorTeleponPemberi;
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

    @Column(name = "nip_penerima")
    private String nipPenerima;

    @OneToMany(mappedBy = "suratPengantar")
    private List<SuratPengantarIsi> suratPengantarIsiList;

    public String getKdSuratPengantar() {
        return kdSuratPengantar;
    }

    public void setKdSuratPengantar(String kdSuratPengantar) {
        this.kdSuratPengantar = kdSuratPengantar;
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

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public Long getTanggalDiterimaSuratPengantar() {
        return tanggalDiterimaSuratPengantar;
    }

    public void setTanggalDiterimaSuratPengantar(Long tanggalDiterimaSuratPengantar) {
        this.tanggalDiterimaSuratPengantar = tanggalDiterimaSuratPengantar;
    }

    public String getKdJabatanPenerimaSuratPengantar() {
        return kdJabatanPenerimaSuratPengantar;
    }

    public void setKdJabatanPenerimaSuratPengantar(String kdJabatanPenerimaSuratPengantar) {
        this.kdJabatanPenerimaSuratPengantar = kdJabatanPenerimaSuratPengantar;
    }

    public String getNipPemberiSuratPengantar() {
        return nipPemberiSuratPengantar;
    }

    public void setNipPemberiSuratPengantar(String nipPemberiSuratPengantar) {
        this.nipPemberiSuratPengantar = nipPemberiSuratPengantar;
    }

    public String getNomorTeleponPemberi() {
        return nomorTeleponPemberi;
    }

    public void setNomorTeleponPemberi(String nomorTeleponPemberi) {
        this.nomorTeleponPemberi = nomorTeleponPemberi;
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

    public List<SuratPengantarIsi> getSuratPengantarIsiList() {
        return suratPengantarIsiList;
    }

    public void setSuratPengantarIsiList(List<SuratPengantarIsi> suratPengantarIsiList) {
        this.suratPengantarIsiList = suratPengantarIsiList;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }

    public String getNipPenerima() {
        return nipPenerima;
    }

    public void setNipPenerima(String nipPenerima) {
        this.nipPenerima = nipPenerima;
    }
}
