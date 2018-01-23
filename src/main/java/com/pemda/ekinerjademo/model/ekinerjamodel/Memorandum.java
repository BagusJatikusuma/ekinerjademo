package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bagus on 04/01/18.
 */
@Entity
@Table(name = "memorandum")
public class Memorandum {
    @Id
    @Column(name = "kd_memorandum")
    private String kdMemorandum;

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

    @Column(name = "nip_penerima_memorandum")
    private String nipPenerimaMemorandum;
    @Column(name = "nip_pemberi_memorandum")
    private String nipPemberiMemorandum;
    @Column(name = "hal")
    private String hal;
    @Column(name = "tanggal_pembuatan_milis")
    private Long tanggalPembuatanMilis;
    @Column(name = "isi_memorandum")
    private String isiMemorandum;
    @Column(name = "nip_pembuat_surat")
    private String nipPembuatSurat;
    @Column(name = "nip_penandatangan")
    private String nipPenandatangan;
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

    @Column(name = "approval_penandatangan")
    private Integer approvalPenandatangan;
    @Column(name = "status_penyebaran")
    private Integer statusPenyebaran;
    @Column(name = "status_baca")
    private Integer statusBaca;

    @OneToOne(mappedBy = "memorandum", fetch = FetchType.LAZY)
    private MemorandumNonPejabat memorandumNonPejabat;
    @OneToOne(mappedBy = "memorandum", fetch = FetchType.LAZY)
    private MemorandumPejabat memorandumPejabat;
    @OneToMany(mappedBy = "memorandum")
    private List<TembusanMemorandum> tembusanMemorandumList;

    public String getKdMemorandum() {
        return kdMemorandum;
    }

    public void setKdMemorandum(String kdMemorandum) {
        this.kdMemorandum = kdMemorandum;
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

    public String getNipPenerimaMemorandum() {
        return nipPenerimaMemorandum;
    }

    public void setNipPenerimaMemorandum(String nipPenerimaMemorandum) {
        this.nipPenerimaMemorandum = nipPenerimaMemorandum;
    }

    public String getNipPemberiMemorandum() {
        return nipPemberiMemorandum;
    }

    public void setNipPemberiMemorandum(String nipPemberiMemorandum) {
        this.nipPemberiMemorandum = nipPemberiMemorandum;
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

    public String getIsiMemorandum() {
        return isiMemorandum;
    }

    public void setIsiMemorandum(String isiMemorandum) {
        this.isiMemorandum = isiMemorandum;
    }

    public String getNipPembuatSurat() {
        return nipPembuatSurat;
    }

    public void setNipPembuatSurat(String nipPembuatSurat) {
        this.nipPembuatSurat = nipPembuatSurat;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
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

    public MemorandumNonPejabat getMemorandumNonPejabat() {
        return memorandumNonPejabat;
    }

    public void setMemorandumNonPejabat(MemorandumNonPejabat memorandumNonPejabat) {
        this.memorandumNonPejabat = memorandumNonPejabat;
    }

    public MemorandumPejabat getMemorandumPejabat() {
        return memorandumPejabat;
    }

    public void setMemorandumPejabat(MemorandumPejabat memorandumPejabat) {
        this.memorandumPejabat = memorandumPejabat;
    }

    public List<TembusanMemorandum> getTembusanMemorandumList() {
        return tembusanMemorandumList;
    }

    public void setTembusanMemorandumList(List<TembusanMemorandum> tembusanMemorandumList) {
        this.tembusanMemorandumList = tembusanMemorandumList;
    }

    public Integer getApprovalPenandatangan() {
        return approvalPenandatangan;
    }

    public void setApprovalPenandatangan(Integer approvalPenandatangan) {
        this.approvalPenandatangan = approvalPenandatangan;
    }

    public Integer getStatusPenyebaran() {
        return statusPenyebaran;
    }

    public void setStatusPenyebaran(Integer statusPenyebaran) {
        this.statusPenyebaran = statusPenyebaran;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }
}
