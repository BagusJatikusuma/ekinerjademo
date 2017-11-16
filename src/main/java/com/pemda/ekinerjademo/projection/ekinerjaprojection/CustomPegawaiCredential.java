package com.pemda.ekinerjademo.projection.ekinerjaprojection;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by bagus on 16/11/17.
 */
public class CustomPegawaiCredential {
    private String nip;
    private String nama;
    private String gol;
    private String pangkat;
    private String kdJabatan;
    private String jabatan;
    private String kdUnitKerja;
    private String unitKerja;

    public CustomPegawaiCredential() {
    }
    public CustomPegawaiCredential(
            String nip,
            String nama,
            String gol,
            String pangkat,
            String kdJabatan,
            String jabatan,
            String kdUnitKerja,
            String unitKerja) {
        this.nip = nip;
        this.nama = nama;
        this.gol = gol;
        this.pangkat = pangkat;
        this.kdJabatan = kdJabatan;
        this.jabatan = jabatan;
        this.kdUnitKerja = kdUnitKerja;
        this.unitKerja = unitKerja;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGol() {
        return gol;
    }

    public void setGol(String gol) {
        this.gol = gol;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
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
}
