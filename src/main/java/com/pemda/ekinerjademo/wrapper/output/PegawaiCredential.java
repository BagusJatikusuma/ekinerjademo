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

    public PegawaiCredential() {}
    public PegawaiCredential(String nipPegawai, String namaPegawai, Role role, String token) {
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
}
