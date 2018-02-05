package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bayu on 07/01/18.
 */
@Entity
@Table(name = "surat_dinas")
public class SuratDinas {
    @Id
    @Column(name = "kd_surat_dinas")
    private String kdSuratDinas;

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

    @Column(name = "sifat")
    private String sifat;
    @Column(name = "lampiran")
    private Integer lampiran;
    @Column(name = "hal")
    private String hal;
    @Column(name = "kd_jabatan_penerima_surat_dinas")
    private String kdJabatanPenerimaSuratDinas;
    @Column(name = "tanggal_pembuatan_milis")
    private Long tanggalPembuatanMilis;
    @Column(name = "kota_pembuatan_surat")
    private String kotaPembuatanSurat;
    @Column(name = "isi_surat_dinas")
    private String isiSuratDinas;
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

    @OneToOne(mappedBy = "suratDinas", fetch = FetchType.LAZY)
    private SuratDinasNonPejabat suratDinasNonPejabat;
    @OneToOne(mappedBy = "suratDinas", fetch = FetchType.LAZY)
    private SuratDinasPejabat suratDinasPejabat;
    @OneToMany(mappedBy = "suratDinas")
    private List<TembusanSuratDinas> tembusanSuratDinasList;

    public String getKdSuratDinas() {
        return kdSuratDinas;
    }

    public void setKdSuratDinas(String kdSuratDinas) {
        this.kdSuratDinas = kdSuratDinas;
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

    public String getSifat() {
        return sifat;
    }

    public void setSifat(String sifat) {
        this.sifat = sifat;
    }

    public Integer getLampiran() {
        return lampiran;
    }

    public void setLampiran(Integer lampiran) {
        this.lampiran = lampiran;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public String getKdJabatanPenerimaSuratDinas() {
        return kdJabatanPenerimaSuratDinas;
    }

    public void setKdJabatanPenerimaSuratDinas(String kdJabatanPenerimaSuratDinas) {
        this.kdJabatanPenerimaSuratDinas = kdJabatanPenerimaSuratDinas;
    }

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public String getKotaPembuatanSurat() {
        return kotaPembuatanSurat;
    }

    public void setKotaPembuatanSurat(String kotaPembuatanSurat) {
        this.kotaPembuatanSurat = kotaPembuatanSurat;
    }

    public String getIsiSuratDinas() {
        return isiSuratDinas;
    }

    public void setIsiSuratDinas(String isiSuratDinas) {
        this.isiSuratDinas = isiSuratDinas;
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

    public SuratDinasNonPejabat getSuratDinasNonPejabat() {
        return suratDinasNonPejabat;
    }

    public void setSuratDinasNonPejabat(SuratDinasNonPejabat suratDinasNonPejabat) {
        this.suratDinasNonPejabat = suratDinasNonPejabat;
    }

    public SuratDinasPejabat getSuratDinasPejabat() {
        return suratDinasPejabat;
    }

    public void setSuratDinasPejabat(SuratDinasPejabat suratDinasPejabat) {
        this.suratDinasPejabat = suratDinasPejabat;
    }

    public List<TembusanSuratDinas> getTembusanSuratDinasList() {
        return tembusanSuratDinasList;
    }

    public void setTembusanSuratDinasList(List<TembusanSuratDinas> tembusanSuratDinasList) {
        this.tembusanSuratDinasList = tembusanSuratDinasList;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }
}
