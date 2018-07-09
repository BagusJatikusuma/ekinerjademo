package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

/**
 * Created by bagus on 27/12/17.
 */
public class LaporanKinerjaPegawaiWrapper {
    private String nipPegawai;
    private String namaPegawai;
    private Integer tanggal;
    private Integer bulan;
    private Integer tahun;
    private List<KinerjaPegawaiWrapper> daftarKinerjaPegawaiWrapper;

    public LaporanKinerjaPegawaiWrapper() {
    }

    public LaporanKinerjaPegawaiWrapper(String nipPegawai, String namaPegawai, Integer tanggal, Integer bulan, Integer tahun, List<KinerjaPegawaiWrapper> daftarKinerjaPegawaiWrapper) {
        this.nipPegawai = nipPegawai;
        this.namaPegawai = namaPegawai;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
        this.daftarKinerjaPegawaiWrapper = daftarKinerjaPegawaiWrapper;
    }

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

    public Integer getTanggal() {
        return tanggal;
    }

    public void setTanggal(Integer tanggal) {
        this.tanggal = tanggal;
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

    public List<KinerjaPegawaiWrapper> getDaftarKinerjaPegawaiWrapper() {
        return daftarKinerjaPegawaiWrapper;
    }

    public void setDaftarKinerjaPegawaiWrapper(List<KinerjaPegawaiWrapper> daftarKinerjaPegawaiWrapper) {
        this.daftarKinerjaPegawaiWrapper = daftarKinerjaPegawaiWrapper;
    }
}
