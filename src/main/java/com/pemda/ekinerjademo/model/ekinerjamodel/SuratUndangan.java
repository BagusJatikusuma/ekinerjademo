package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bayu on 09/01/18.
 */
@Entity
@Table(name = "surat_undangan")
public class SuratUndangan {
    @Id
    @Column(name = "kd_surat_undangan")
    private String kdSuratUndangan;

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

    @Column(name = "kd_jabatan_penerima_surat_undangan")
    private String kdJabatanPenerimaSuratUndangan;
    @Column(name = "tanggal_pembuatan_milis")
    private Long tanggalPembuatanSurat;
    @Column(name = "kota_pembuatan_surat")
    private String kotaPembuatanSurat;
    @Column(name = "sifat")
    private String sifat;
    @Column(name = "lampiran")
    private String lampiran;
    @Column(name = "hal")
    private String hal;
    @Column(name = "nip_penerima_surat_undangan")
    private String nipPenerimaSuratUndangan;
    @Column(name = "bagian_pembuka_surat_undangan")
    private String bagianPembukaSuratUndangan;
    @Column(name = "bagian_isi_hari_surat_undangan")
    private String bagianIsiHariSuratUndangan;
    @Column(name = "bagian_isi_tanggal_surat_undangan")
    private Integer bagianIsiTanggalSuratUndangan;
    @Column(name = "bagian_isi_waktu_surat_undangan")
    private String bagianIsiWaktuSuratUndangan;
    @Column(name = "bagian_isi_tempat_surat_undangan")
    private String bagianIsiTempatSuratUndangan;
    @Column(name = "bagian_isi_acara_surat_undangan")
    private String bagianIsiAcaraSuratUndangan;
    @Column(name = "bagian_penutup_surat_undangan")
    private String bagianPenutupSuratUndangan;
    @Column(name = "nip_penandatangan")
    private String nipPenandatangan;
    @Column(name = "nip_pembuat_surat")
    private String nipPembuatSurat;

    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
    @Column(name = "jenis_naskah_penugasan")
    private Integer jenisNaskahPenugasan;
    @Column(name = "kd_naskah_penugasan")
    private String kdNaskahPenugasan;
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

    @OneToOne(mappedBy = "suratUndangan", fetch = FetchType.LAZY)
    private SuratUndanganNonPejabat suratUndanganNonPejabat;
    @OneToOne(mappedBy = "suratUndangan", fetch = FetchType.LAZY)
    private SuratUndanganPejabat suratUndanganPejabat;
    @OneToMany(mappedBy = "suratUndangan")
    private List<TembusanSuratUndangan> tembusanSuratUndanganList;

    public String getKdSuratUndangan() {
        return kdSuratUndangan;
    }

    public void setKdSuratUndangan(String kdSuratUndangan) {
        this.kdSuratUndangan = kdSuratUndangan;
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

    public String getKdJabatanPenerimaSuratUndangan() {
        return kdJabatanPenerimaSuratUndangan;
    }

    public void setKdJabatanPenerimaSuratUndangan(String kdJabatanPenerimaSuratUndangan) {
        this.kdJabatanPenerimaSuratUndangan = kdJabatanPenerimaSuratUndangan;
    }

    public Long getTanggalPembuatanSurat() {
        return tanggalPembuatanSurat;
    }

    public void setTanggalPembuatanSurat(Long tanggalPembuatanSurat) {
        this.tanggalPembuatanSurat = tanggalPembuatanSurat;
    }

    public String getKotaPembuatanSurat() {
        return kotaPembuatanSurat;
    }

    public void setKotaPembuatanSurat(String kotaPembuatanSurat) {
        this.kotaPembuatanSurat = kotaPembuatanSurat;
    }

    public String getSifat() {
        return sifat;
    }

    public void setSifat(String sifat) {
        this.sifat = sifat;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public String getNipPenerimaSuratUndangan() {
        return nipPenerimaSuratUndangan;
    }

    public void setNipPenerimaSuratUndangan(String nipPenerimaSuratUndangan) {
        this.nipPenerimaSuratUndangan = nipPenerimaSuratUndangan;
    }

    public String getBagianPembukaSuratUndangan() {
        return bagianPembukaSuratUndangan;
    }

    public void setBagianPembukaSuratUndangan(String bagianPembukaSuratUndangan) {
        this.bagianPembukaSuratUndangan = bagianPembukaSuratUndangan;
    }

    public String getBagianIsiHariSuratUndangan() {
        return bagianIsiHariSuratUndangan;
    }

    public void setBagianIsiHariSuratUndangan(String bagianIsiHariSuratUndangan) {
        this.bagianIsiHariSuratUndangan = bagianIsiHariSuratUndangan;
    }

    public Integer getBagianIsiTanggalSuratUndangan() {
        return bagianIsiTanggalSuratUndangan;
    }

    public void setBagianIsiTanggalSuratUndangan(Integer bagianIsiTanggalSuratUndangan) {
        this.bagianIsiTanggalSuratUndangan = bagianIsiTanggalSuratUndangan;
    }

    public String getBagianIsiWaktuSuratUndangan() {
        return bagianIsiWaktuSuratUndangan;
    }

    public void setBagianIsiWaktuSuratUndangan(String bagianIsiWaktuSuratUndangan) {
        this.bagianIsiWaktuSuratUndangan = bagianIsiWaktuSuratUndangan;
    }

    public String getBagianIsiTempatSuratUndangan() {
        return bagianIsiTempatSuratUndangan;
    }

    public void setBagianIsiTempatSuratUndangan(String bagianIsiTempatSuratUndangan) {
        this.bagianIsiTempatSuratUndangan = bagianIsiTempatSuratUndangan;
    }

    public String getBagianIsiAcaraSuratUndangan() {
        return bagianIsiAcaraSuratUndangan;
    }

    public void setBagianIsiAcaraSuratUndangan(String bagianIsiAcaraSuratUndangan) {
        this.bagianIsiAcaraSuratUndangan = bagianIsiAcaraSuratUndangan;
    }

    public String getBagianPenutupSuratUndangan() {
        return bagianPenutupSuratUndangan;
    }

    public void setBagianPenutupSuratUndangan(String bagianPenutupSuratUndangan) {
        this.bagianPenutupSuratUndangan = bagianPenutupSuratUndangan;
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

    public Integer getJenisNaskahPenugasan() {
        return jenisNaskahPenugasan;
    }

    public void setJenisNaskahPenugasan(Integer jenisNaskahPenugasan) {
        this.jenisNaskahPenugasan = jenisNaskahPenugasan;
    }

    public String getKdNaskahPenugasan() {
        return kdNaskahPenugasan;
    }

    public void setKdNaskahPenugasan(String kdNaskahPenugasan) {
        this.kdNaskahPenugasan = kdNaskahPenugasan;
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

    public SuratUndanganNonPejabat getSuratUndanganNonPejabat() {
        return suratUndanganNonPejabat;
    }

    public void setSuratUndanganNonPejabat(SuratUndanganNonPejabat suratUndanganNonPejabat) {
        this.suratUndanganNonPejabat = suratUndanganNonPejabat;
    }

    public SuratUndanganPejabat getSuratUndanganPejabat() {
        return suratUndanganPejabat;
    }

    public void setSuratUndanganPejabat(SuratUndanganPejabat suratUndanganPejabat) {
        this.suratUndanganPejabat = suratUndanganPejabat;
    }

    public List<TembusanSuratUndangan> getTembusanSuratUndanganList() {
        return tembusanSuratUndanganList;
    }

    public void setTembusanSuratUndanganList(List<TembusanSuratUndangan> tembusanSuratUndanganList) {
        this.tembusanSuratUndanganList = tembusanSuratUndanganList;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }
}
