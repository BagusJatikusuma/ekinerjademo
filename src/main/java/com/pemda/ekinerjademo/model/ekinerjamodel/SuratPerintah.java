package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by bagus on 14/11/17.
 */
@Entity
@Table(name = "surat_perintah")
public class SuratPerintah {
    @Id
    @Column(name = "kd_surat_perintah")
    private String kdSuratPerintah;
    @Column(name = "nip_pembuat")
    private String nipPembuat;
    @Column(name = "nip_penandatangan")
    private String nipPenandatangan;
    @Column(name = "nomor_surat_1")
    private Integer nomorSurat1;
    @Column(name = "nomor_surat_2")
    private String nomorSurat2;
    @Column(name = "nomor_surat_3")
    private String nomorSurat3;
    @Column(name = "nomor_tahun")
    private Integer nomorTahun;
    @Column(name = "dasar")
    private String dasar;
    @Column(name = "untuk")
    private String untuk;
    @Column(name = "tempat")
    private String tempat;
    @Column(name = "tanggal_perintah_milis")
    private Long tanggalPerintahMilis;
    @Column(name = "ttd_path")
    private String ttdPath;
    @Column(name = "menimbang")
    private String menimbang;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
    @Column(name = "status_penyebaran")
    private Integer statusPenyebaran;
    @Column(name = "approval_penandatangan")
    private Integer approvalPenandatangan;
    @Column(name = "path_penilaian")
    private String pathPenilaian;
    @Column(name = "status_baca")
    private Integer statusBaca;
    @Column(name = "kd_naskah_penugasan")
    private String kdNaskahPenugasan;
    @Column(name = "jenis_naskah_penugasan")
    private Integer jenisNaskahPenugasan;
    @Column(name = "durasi_pengerjaan")
    private Integer durasiPengerjaan;
    @Column(name = "nip_penilai")
    private String nipPenilai;
    @Column(name = "status_penilaian")
    private Integer statusPenilaian;
    @Column(name = "alasan_penolakan")
    private String alasanPenolakan;

    @OneToMany(mappedBy = "suratPerintah")
    private Set<TargetSuratPerintahPegawai> targetSuratPerintahPegawaiList;
    @OneToMany(mappedBy = "suratPerintah")
    private Set<TargetSuratPerintahPejabat> targetSuratPerintahPejabatSet;
    @OneToOne(mappedBy = "suratPerintah", fetch = FetchType.LAZY)
    private SuratPerintahPejabat suratPerintahPejabat;
    @OneToOne(mappedBy = "suratPerintah", fetch = FetchType.LAZY)
    private SuratPerintahNonPejabat suratPerintahNonPejabat;
    @OneToMany(mappedBy = "suratPerintah")
    private Set<TembusanSuratPerintah> tembusanSuratPerintahList;


    public String getKdSuratPerintah() {
        return kdSuratPerintah;
    }

    public void setKdSuratPerintah(String kdSuratPerintah) {
        this.kdSuratPerintah = kdSuratPerintah;
    }

    public Integer getNomorSurat1() {
        return nomorSurat1;
    }

    public void setNomorSurat1(Integer nomorSurat1) {
        this.nomorSurat1 = nomorSurat1;
    }

    public String getNomorSurat2() {
        return nomorSurat2;
    }

    public void setNomorSurat2(String nomorSurat2) {
        this.nomorSurat2 = nomorSurat2;
    }

    public String getNomorSurat3() {
        return nomorSurat3;
    }

    public void setNomorSurat3(String nomorSurat3) {
        this.nomorSurat3 = nomorSurat3;
    }

    public Integer getNomorTahun() {
        return nomorTahun;
    }

    public void setNomorTahun(Integer nomorTahun) {
        this.nomorTahun = nomorTahun;
    }

    public String getDasar() {
        return dasar;
    }

    public void setDasar(String dasar) {
        this.dasar = dasar;
    }

    public String getUntuk() {
        return untuk;
    }

    public void setUntuk(String untuk) {
        this.untuk = untuk;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Long getTanggalPerintahMilis() {
        return tanggalPerintahMilis;
    }

    public void setTanggalPerintahMilis(Long tanggalPerintahMilis) {
        this.tanggalPerintahMilis = tanggalPerintahMilis;
    }

    public String getTtdPath() {
        return ttdPath;
    }

    public void setTtdPath(String ttdPath) {
        this.ttdPath = ttdPath;
    }

    public String getNipPembuat() {
        return nipPembuat;
    }

    public void setNipPembuat(String nipPembuat) {
        this.nipPembuat = nipPembuat;
    }

    public Set<TargetSuratPerintahPegawai> getTargetSuratPerintahPegawaiList() {
        return targetSuratPerintahPegawaiList;
    }

    public void setTargetSuratPerintahPegawaiList(Set<TargetSuratPerintahPegawai> targetSuratPerintahPegawaiList) {
        this.targetSuratPerintahPegawaiList = targetSuratPerintahPegawaiList;
    }

    public Set<TembusanSuratPerintah> getTembusanSuratPerintahList() {
        return tembusanSuratPerintahList;
    }

    public void setTembusanSuratPerintahList(Set<TembusanSuratPerintah> tembusanSuratPerintahList) {
        this.tembusanSuratPerintahList = tembusanSuratPerintahList;
    }

    public String getMenimbang() {
        return menimbang;
    }

    public void setMenimbang(String menimbang) {
        this.menimbang = menimbang;
    }

    public Set<TargetSuratPerintahPejabat> getTargetSuratPerintahPejabatSet() {
        return targetSuratPerintahPejabatSet;
    }

    public void setTargetSuratPerintahPejabatSet(Set<TargetSuratPerintahPejabat> targetSuratPerintahPejabatSet) {
        this.targetSuratPerintahPejabatSet = targetSuratPerintahPejabatSet;
    }

    public SuratPerintahPejabat getSuratPerintahPejabat() {
        return suratPerintahPejabat;
    }

    public void setSuratPerintahPejabat(SuratPerintahPejabat suratPerintahPejabat) {
        this.suratPerintahPejabat = suratPerintahPejabat;
    }

    public SuratPerintahNonPejabat getSuratPerintahNonPejabat() {
        return suratPerintahNonPejabat;
    }

    public void setSuratPerintahNonPejabat(SuratPerintahNonPejabat suratPerintahNonPejabat) {
        this.suratPerintahNonPejabat = suratPerintahNonPejabat;
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

    public Integer getStatusPenyebaran() {
        return statusPenyebaran;
    }

    public void setStatusPenyebaran(Integer statusPenyebaran) {
        this.statusPenyebaran = statusPenyebaran;
    }

    public Integer getApprovalPenandatangan() {
        return approvalPenandatangan;
    }

    public void setApprovalPenandatangan(Integer approvalPenandatangan) {
        this.approvalPenandatangan = approvalPenandatangan;
    }

    public String getPathPenilaian() {
        return pathPenilaian;
    }

    public void setPathPenilaian(String pathPenilaian) {
        this.pathPenilaian = pathPenilaian;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
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
