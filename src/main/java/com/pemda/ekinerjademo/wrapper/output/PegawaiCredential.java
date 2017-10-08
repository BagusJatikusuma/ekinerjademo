package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.model.ekinerjamodel.Role;

/**
 * Created by bagus on 09/09/17.
 */
public class PegawaiCredential {
    private String nipPegawai;
    private String namaPegawai;
    private Role role;
    private String token;
    private String jabatan;
    private String unit;
    private String kdUnitKerja;
    private String kdJabatan;
    private String pangkat;
    private String golongan;

    public PegawaiCredential() {}
    public PegawaiCredential(
            String nipPegawai,
            String namaPegawai,
            Role role,
            String token,
            String jabatan,
            String unit,
            String kdJabatan) {
        this.nipPegawai = nipPegawai;
        this.namaPegawai = namaPegawai;
        this.role = role;
        this.token = token;
        this.jabatan = jabatan;
        this.unit = unit;
        this.kdJabatan = kdJabatan;
    }
    public PegawaiCredential(
            String nipPegawai,
            String namaPegawai,
            Role role,
            String token,
            String jabatan,
            String unit,
            String kdUnitKerja,
            String kdJabatan) {
        this.nipPegawai = nipPegawai;
        this.namaPegawai = namaPegawai;
        this.role = role;
        this.token = token;
        this.jabatan = jabatan;
        this.unit = unit;
        this.kdUnitKerja = kdUnitKerja;
        this.kdJabatan = kdJabatan;
    }
    public PegawaiCredential(
            String nipPegawai,
            String namaPegawai,
            Role role,
            String token,
            String jabatan,
            String unit,
            String kdUnitKerja,
            String kdJabatan,
            String pangkat,
            String golongan) {
        this.nipPegawai = nipPegawai;
        this.namaPegawai = namaPegawai;
        this.role = role;
        this.token = token;
        this.jabatan = jabatan;
        this.unit = unit;
        this.kdUnitKerja = kdUnitKerja;
        this.kdJabatan = kdJabatan;
        this.pangkat = pangkat;
        this.golongan = golongan;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }
}
