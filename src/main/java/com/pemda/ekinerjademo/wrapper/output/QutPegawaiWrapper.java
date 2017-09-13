package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 13/09/17.
 */
public class QutPegawaiWrapper {
    private String nipPegawai;
    private String nama;
    private String jabatan;
    private String unitKerja;

    public QutPegawaiWrapper() {}
    public QutPegawaiWrapper(String nipPegawai, String nama, String jabatan, String unitKerja) {
        this.nipPegawai = nipPegawai;
        this.nama = nama;
        this.jabatan = jabatan;
        this.unitKerja = unitKerja;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }
}
