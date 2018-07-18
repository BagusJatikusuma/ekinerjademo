package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by bayu on 18/07/18.
 */

@Entity
@Table(name = "rekapitulasi_perilaku_kerja_pegawai")
public class RekapitulasiPerilakuKerjaPegawai {

    @Column(name = "bulan_tahun_rekapitulasi")
    private long bulanTahunRekapulasi;

    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
    @Column(name = "kd_jabatan")
    private String kdJabatan;
    @Column(name = "nip_pegawai")
    private String nipPegawai;

    @Column(name = "data_hadir")
    private int dataHadir;
    @Column(name = "nilai_hadir")
    private int nilaiHadir;

    @Column(name = "data_perekaman_datang_pulang")
    private int dataPerekamanDatangPulang;
    @Column(name = "nilai_perekaman_datang_pulang")
    private int nilaiPerekamanDatangPulang;

    @Column(name = "data_hadir_apel")
    private int dataHadirApel;
    @Column(name = "nilai_hadir_apel")
    private float nilaiHadirApel;

    @Column(name = "data_hadir_rapat")
    private int dataHadirRapat;
    @Column(name = "nilai_hadir_rapat")
    private int nilaiHadirRapat;

    @Column(name = "data_razia")
    private int dataRazia;
    @Column(name = "nilai_razia")
    private int nilaiRazia;

    @Column(name = "data_manipulasi_data")
    private boolean dataManipulasiData;
    @Column(name = "nilai_manipulasi_data")
    private boolean nilaiManipulasiData;

    @Column(name = "total_faktor_pengurangan_tpp")
    private float totalFaktorPeuranganTpp;
    @Column(name = "nilai_kebalikan")
    private float nilaiKebalikan;

    public long getBulanTahunRekapulasi() {
        return bulanTahunRekapulasi;
    }

    public void setBulanTahunRekapulasi(long bulanTahunRekapulasi) {
        this.bulanTahunRekapulasi = bulanTahunRekapulasi;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public int getDataHadir() {
        return dataHadir;
    }

    public void setDataHadir(int dataHadir) {
        this.dataHadir = dataHadir;
    }

    public int getNilaiHadir() {
        return nilaiHadir;
    }

    public void setNilaiHadir(int nilaiHadir) {
        this.nilaiHadir = nilaiHadir;
    }

    public int getDataPerekamanDatangPulang() {
        return dataPerekamanDatangPulang;
    }

    public void setDataPerekamanDatangPulang(int dataPerekamanDatangPulang) {
        this.dataPerekamanDatangPulang = dataPerekamanDatangPulang;
    }

    public int getNilaiPerekamanDatangPulang() {
        return nilaiPerekamanDatangPulang;
    }

    public void setNilaiPerekamanDatangPulang(int nilaiPerekamanDatangPulang) {
        this.nilaiPerekamanDatangPulang = nilaiPerekamanDatangPulang;
    }

    public int getDataHadirApel() {
        return dataHadirApel;
    }

    public void setDataHadirApel(int dataHadirApel) {
        this.dataHadirApel = dataHadirApel;
    }

    public float getNilaiHadirApel() {
        return nilaiHadirApel;
    }

    public void setNilaiHadirApel(float nilaiHadirApel) {
        this.nilaiHadirApel = nilaiHadirApel;
    }

    public int getDataHadirRapat() {
        return dataHadirRapat;
    }

    public void setDataHadirRapat(int dataHadirRapat) {
        this.dataHadirRapat = dataHadirRapat;
    }

    public int getNilaiHadirRapat() {
        return nilaiHadirRapat;
    }

    public void setNilaiHadirRapat(int nilaiHadirRapat) {
        this.nilaiHadirRapat = nilaiHadirRapat;
    }

    public int getDataRazia() {
        return dataRazia;
    }

    public void setDataRazia(int dataRazia) {
        this.dataRazia = dataRazia;
    }

    public int getNilaiRazia() {
        return nilaiRazia;
    }

    public void setNilaiRazia(int nilaiRazia) {
        this.nilaiRazia = nilaiRazia;
    }

    public boolean isDataManipulasiData() {
        return dataManipulasiData;
    }

    public void setDataManipulasiData(boolean dataManipulasiData) {
        this.dataManipulasiData = dataManipulasiData;
    }

    public boolean isNilaiManipulasiData() {
        return nilaiManipulasiData;
    }

    public void setNilaiManipulasiData(boolean nilaiManipulasiData) {
        this.nilaiManipulasiData = nilaiManipulasiData;
    }

    public float getTotalFaktorPeuranganTpp() {
        return totalFaktorPeuranganTpp;
    }

    public void setTotalFaktorPeuranganTpp(float totalFaktorPeuranganTpp) {
        this.totalFaktorPeuranganTpp = totalFaktorPeuranganTpp;
    }

    public float getNilaiKebalikan() {
        return nilaiKebalikan;
    }

    public void setNilaiKebalikan(float nilaiKebalikan) {
        this.nilaiKebalikan = nilaiKebalikan;
    }
}
