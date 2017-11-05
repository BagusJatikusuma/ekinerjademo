package com.pemda.ekinerjademo.wrapper.report;

import java.util.Date;
/**
 * Created by waddi on 03/11/17.
 */
public class NotaDinasBean {
    private String namaInstansi;
    private String nomorSurat; // dari sistem increment berdasarkan unit kerja
    private String namaTujuan;
    private String namaPemberi;
    private String hal;
    private String tanggal;
    private String pembukaSurat;
    private String isiSurat;
    private String penutupSurat;
    private String namaLengkap;
    private String tembusanSurat; // list tembusan surat

    public NotaDinasBean() {}
    public NotaDinasBean(String namaInstansi, String nomorSurat, String namaTujuan, String namaPemberi, String hal, String tanggal, String pembukaSurat, String isiSurat, String penutupSurat, String namaLengkap, String tembusanSurat) {
        this.namaInstansi = namaInstansi;
        this.nomorSurat = nomorSurat;
        this.namaTujuan = namaTujuan;
        this.namaPemberi = namaPemberi;
        this.hal = hal;
        this.tanggal = tanggal;
        this.pembukaSurat = pembukaSurat;
        this.isiSurat = isiSurat;
        this.penutupSurat = penutupSurat;
        this.namaLengkap = namaLengkap;
        this.tembusanSurat = tembusanSurat;
    }

    public String getNamaInstansi() {
        return namaInstansi;
    }

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }

    public String getNomorSurat() {
        return nomorSurat;
    }

    public void setNomorSurat(String nomorSurat) {
        this.nomorSurat = nomorSurat;
    }

    public String getNamaTujuan() {
        return namaTujuan;
    }

    public void setNamaTujuan(String namaTujuan) {
        this.namaTujuan = namaTujuan;
    }

    public String getNamaPemberi() {
        return namaPemberi;
    }

    public void setNamaPemberi(String namaPemberi) {
        this.namaPemberi = namaPemberi;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPembukaSurat() {
        return pembukaSurat;
    }

    public void setPembukaSurat(String pembukaSurat) {
        this.pembukaSurat = pembukaSurat;
    }

    public String getIsiSurat() {
        return isiSurat;
    }

    public void setIsiSurat(String isiSurat) {
        this.isiSurat = isiSurat;
    }

    public String getPenutupSurat() {
        return penutupSurat;
    }

    public void setPenutupSurat(String penutupSurat) {
        this.penutupSurat = penutupSurat;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.penutupSurat = namaLengkap;
    }

    public String getTembusanSurat() {
        return tembusanSurat;
    }

    public void setTembusanSurat(String tembusanSurat) {
        this.tembusanSurat = tembusanSurat;
    }
}
