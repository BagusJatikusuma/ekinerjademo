package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by bagus on 27/11/17.
 */
@Entity
@Table(name = "surat_tugas")
public class SuratTugas {
    @Id
    @Column(name = "kd_surat_tugas")
    private String kdSuratTugas;

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
    @Column(name = "nomor_pasangan_urut")
    private String nomorPasanganUrut;

    @Column(name = "menimbang")
    private String menimbang;
    @Column(name = "dasar")
    private String dasar;
    @Column(name = "untuk")
    private String untuk;
    @Column(name = "tempat")
    private String tempat;

    @Column(name = "tanggal_tugas_milis")
    private Long tanggalTugasMilis;
    @Column(name = "ttd_path")
    private String ttdPath;
    @Column(name = "status_penyebaran")
    private Integer statusPenyebaran;
    @Column(name = "approval_penandatangan")
    private Integer approvalPenandatangan;
    @Column(name = "path_penilaian")
    private String pathPenilaian;
    @Column(name = "status_baca")
    private Integer statusBaca;
    @Column(name = "durasi_pengerjaan")
    private Integer durasiPengerjaan;
    @Column(name = "nip_penilai")
    private String nipPenilai;
    @Column(name = "status_penilaian")
    private Integer statusPenilaian;
    @Column(name = "alasan_penolakan")
    private String alasanPenolakan;

    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;

    @Column(name = "kd_naskah_penugasan")
    private String kdNaskahPenugasan;
    @Column(name = "jenis_naskah_penugasan")
    private Integer jenisNaskahPenugasan;

    @OneToMany(mappedBy = "suratTugas")
    private Set<TargetSuratTugasPegawai> targetSuratTugasPegawaiSet;
    @OneToMany(mappedBy = "suratTugas")
    private Set<TargetSuratTugasPejabat> targetSuratTugasPejabatSet;
    @OneToMany(mappedBy = "suratTugas")
    private Set<TembusanSuratTugas> tembusanSuratTugasSet;
    @OneToOne(mappedBy = "suratTugas", fetch = FetchType.LAZY)
    private SuratTugasPejabat suratTugasPejabat;
    @OneToOne(mappedBy = "suratTugas", fetch = FetchType.LAZY)
    private SuratTugasNonPejabat suratTugasNonPejabat;

    public String getKdSuratTugas() {
        return kdSuratTugas;
    }

    public void setKdSuratTugas(String kdSuratTugas) {
        this.kdSuratTugas = kdSuratTugas;
    }

    public String getNipPembuat() {
        return nipPembuat;
    }

    public void setNipPembuat(String nipPembuat) {
        this.nipPembuat = nipPembuat;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
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

    public Long getTanggalTugasMilis() {
        return tanggalTugasMilis;
    }

    public void setTanggalTugasMilis(Long tanggalTugasMilis) {
        this.tanggalTugasMilis = tanggalTugasMilis;
    }

    public String getTtdPath() {
        return ttdPath;
    }

    public void setTtdPath(String ttdPath) {
        this.ttdPath = ttdPath;
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

    public Set<TargetSuratTugasPegawai> getTargetSuratTugasPegawaiSet() {
        return targetSuratTugasPegawaiSet;
    }

    public void setTargetSuratTugasPegawaiSet(Set<TargetSuratTugasPegawai> targetSuratTugasPegawaiSet) {
        this.targetSuratTugasPegawaiSet = targetSuratTugasPegawaiSet;
    }

    public Set<TargetSuratTugasPejabat> getTargetSuratTugasPejabatSet() {
        return targetSuratTugasPejabatSet;
    }

    public void setTargetSuratTugasPejabatSet(Set<TargetSuratTugasPejabat> targetSuratTugasPejabatSet) {
        this.targetSuratTugasPejabatSet = targetSuratTugasPejabatSet;
    }

    public Set<TembusanSuratTugas> getTembusanSuratTugasSet() {
        return tembusanSuratTugasSet;
    }

    public void setTembusanSuratTugasSet(Set<TembusanSuratTugas> tembusanSuratTugasSet) {
        this.tembusanSuratTugasSet = tembusanSuratTugasSet;
    }

    public SuratTugasPejabat getSuratTugasPejabat() {
        return suratTugasPejabat;
    }

    public void setSuratTugasPejabat(SuratTugasPejabat suratTugasPejabat) {
        this.suratTugasPejabat = suratTugasPejabat;
    }

    public SuratTugasNonPejabat getSuratTugasNonPejabat() {
        return suratTugasNonPejabat;
    }

    public void setSuratTugasNonPejabat(SuratTugasNonPejabat suratTugasNonPejabat) {
        this.suratTugasNonPejabat = suratTugasNonPejabat;
    }

    public String getNomorPasanganUrut() {
        return nomorPasanganUrut;
    }

    public void setNomorPasanganUrut(String nomorPasanganUrut) {
        this.nomorPasanganUrut = nomorPasanganUrut;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getMenimbang() {
        return menimbang;
    }

    public void setMenimbang(String menimbang) {
        this.menimbang = menimbang;
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
