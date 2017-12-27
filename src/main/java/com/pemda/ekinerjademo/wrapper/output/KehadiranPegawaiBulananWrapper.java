package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

/**
 * Created by bagus on 27/12/17.
 */
public class KehadiranPegawaiBulananWrapper {
    private String nipPegawai;
    private String namaPegawai;
    private Integer bulan;
    private Integer tahun;
    private List<Integer> daftarTanggalHadir;
    private Integer jumlahMenitKerja;

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public Integer getBulan() {
        return bulan;
    }

    public void setBulan(Integer bulan) {
        this.bulan = bulan;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public List<Integer> getDaftarTanggalHadir() {
        return daftarTanggalHadir;
    }

    public void setDaftarTanggalHadir(List<Integer> daftarTanggalHadir) {
        this.daftarTanggalHadir = daftarTanggalHadir;
    }

    public Integer getJumlahMenitKerja() {
        return jumlahMenitKerja;
    }

    public void setJumlahMenitKerja(Integer jumlahMenitKerja) {
        this.jumlahMenitKerja = jumlahMenitKerja;
    }
}
