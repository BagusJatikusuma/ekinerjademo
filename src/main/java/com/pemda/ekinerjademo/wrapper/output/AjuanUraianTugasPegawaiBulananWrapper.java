package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

public class AjuanUraianTugasPegawaiBulananWrapper {
    private String nipPegawai;
    private String namaPegawai;
    private String kdJabatan;
    private String jabatan;
    private String kdUnitKerja;
    private String unitKerja;
    private String eselon;
    private List<UraianTugasPegawaiTahunanWrapper> uraianTugasDiajukan;
    private List<UraianTugasPegawaiTahunanWrapper> uraianTugasTidakDipilih;
    /**
     *  0 -> belum ada pengajuan dari bawahan
     *  1 -> sudah ada pengajuan dari bawahan tapi belum ditangani
     *  2 -> sudah ada pengajuan dari bawahan dan sudah ditangani
     *  3 -> ada ajuan baru dari bawahan
     */
    private int statusPenangangan;



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

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }

    public String getEselon() {
        return eselon;
    }

    public void setEselon(String eselon) {
        this.eselon = eselon;
    }

    public List<UraianTugasPegawaiTahunanWrapper> getUraianTugasDiajukan() {
        return uraianTugasDiajukan;
    }

    public void setUraianTugasDiajukan(List<UraianTugasPegawaiTahunanWrapper> uraianTugasDiajukan) {
        this.uraianTugasDiajukan = uraianTugasDiajukan;
    }

    public List<UraianTugasPegawaiTahunanWrapper> getUraianTugasTidakDipilih() {
        return uraianTugasTidakDipilih;
    }

    public void setUraianTugasTidakDipilih(List<UraianTugasPegawaiTahunanWrapper> uraianTugasTidakDipilih) {
        this.uraianTugasTidakDipilih = uraianTugasTidakDipilih;
    }

    public int getStatusPenangangan() {
        return statusPenangangan;
    }

    public void setStatusPenangangan(int statusPenangangan) {
        this.statusPenangangan = statusPenangangan;
    }
}
