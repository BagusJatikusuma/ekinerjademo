package com.pemda.ekinerjademo.model.ekinerjamodel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 14/11/17.
 */
@Entity
@Table(name = "surat_perintah_non_pejabat")
public class SuratPerintahNonPejabat {
    @Id
    @Column(name = "kd_surat_perintah")
    private String kdSuratPerintah;
    @Column(name = "nip_pembuat")
    private String nipPembuat;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
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
    @Column(name = "kd_jabatan")
    private String kdJabatan;
    @Column(name = "ttd_path")
    private String ttdPath;
    @Column(name = "menimbang")
    private String menimbang;
    @OneToMany(mappedBy = "suratPerintahNonPejabat")
    private Set<TargetSuratPerintahNonPejabat> targetSuratPerintahNonPejabatList;
    @OneToMany(mappedBy = "suratPerintahNonPejabat")
    private Set<TembusanSuratPerintahNonPejabat> tembusanSuratPerintahNonPejabatList;


    public String getKdSuratPerintah() {
        return kdSuratPerintah;
    }

    public void setKdSuratPerintah(String kdSuratPerintah) {
        this.kdSuratPerintah = kdSuratPerintah;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
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

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
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

    public Set<TargetSuratPerintahNonPejabat> getTargetSuratPerintahNonPejabatList() {
        return targetSuratPerintahNonPejabatList;
    }

    public void setTargetSuratPerintahNonPejabatList(Set<TargetSuratPerintahNonPejabat> targetSuratPerintahNonPejabatList) {
        this.targetSuratPerintahNonPejabatList = targetSuratPerintahNonPejabatList;
    }

    public Set<TembusanSuratPerintahNonPejabat> getTembusanSuratPerintahNonPejabatList() {
        return tembusanSuratPerintahNonPejabatList;
    }

    public void setTembusanSuratPerintahNonPejabatList(Set<TembusanSuratPerintahNonPejabat> tembusanSuratPerintahNonPejabatList) {
        this.tembusanSuratPerintahNonPejabatList = tembusanSuratPerintahNonPejabatList;
    }

    public String getMenimbang() {
        return menimbang;
    }

    public void setMenimbang(String menimbang) {
        this.menimbang = menimbang;
    }
}
