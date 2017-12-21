package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

/**
 * Created by bayu on 30/10/17.
 */
public class AjuanUraianTugasNonDpaPegawaiWrapper {

    private String nipPegawai;
    private String namaPegawai;
    private String kdJabatan;
    private String jabatan;
    private String eselon;
    private List<UraianTugasPegawaiTahunanWrapper> uraianTugasDiajukan;
    private List<UraianTugasPegawaiTahunanWrapper> uraianTugasTidakDipilih;

    public List<UraianTugasPegawaiTahunanWrapper> getUraianTugasDiajukan() {
        return uraianTugasDiajukan;
    }

    public void setUraianTugasDiajukan(List<UraianTugasPegawaiTahunanWrapper> uraianTugasDiajukan) {
        this.uraianTugasDiajukan = uraianTugasDiajukan;
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

    public List<UraianTugasPegawaiTahunanWrapper> getUraianTugasTidakDipilih() {
        return uraianTugasTidakDipilih;
    }

    public void setUraianTugasTidakDipilih(List<UraianTugasPegawaiTahunanWrapper> uraianTugasTidakDipilih) {
        this.uraianTugasTidakDipilih = uraianTugasTidakDipilih;
    }

    public String getEselon() {
        return eselon;
    }

    public void setEselon(String eselon) {
        this.eselon = eselon;
    }
}
