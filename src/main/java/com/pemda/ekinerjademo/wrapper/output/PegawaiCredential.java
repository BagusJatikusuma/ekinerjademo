package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 09/09/17.
 */
public class PegawaiCredential {
    private String nipPegawai;
    private String namaPegawai;
    private String role;
    private String token;

    public PegawaiCredential() {}
    public PegawaiCredential(String nipPegawai, String namaPegawai, String role, String token) {
        this.nipPegawai = nipPegawai;
        this.namaPegawai = namaPegawai;
        this.role = role;
        this.token = token;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
