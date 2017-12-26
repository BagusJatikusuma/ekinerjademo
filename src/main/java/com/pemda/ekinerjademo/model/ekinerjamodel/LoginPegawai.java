package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bayu on 26/12/17.
 */
@Entity
@Table(name = "login_pegawai")
public class LoginPegawai {
    @Id
    @Column(name = "id_login_pegawai")
    private Integer idLoginPegawai;

    @Column(name = "nip_pegawai")
    private String nipPegawai;
    @Column(name = "login_milis")
    private Long loginMilis;
    @Column(name = "tanggal_login")
    private Integer tanggalLogin;
    @Column(name = "bulan_login")
    private Integer bulanLogin;
    @Column(name = "tahun_login")
    private Integer tahunLogin;
    @Column(name = "logout_milis")
    private Long logoutMilis;
    @Column(name = "tanggal_logout")
    private Integer tanggalLogout;
    @Column(name = "bulan_logout")
    private Integer bulanLogout;
    @Column(name = "tahun_logout")
    private Integer tahunLogout;

    public Integer getIdLoginPegawai() {
        return idLoginPegawai;
    }

    public void setIdLoginPegawai(Integer idLoginPegawai) {
        this.idLoginPegawai = idLoginPegawai;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public Long getLoginMilis() {
        return loginMilis;
    }

    public void setLoginMilis(Long loginMilis) {
        this.loginMilis = loginMilis;
    }

    public Integer getTanggalLogin() {
        return tanggalLogin;
    }

    public void setTanggalLogin(Integer tanggalLogin) {
        this.tanggalLogin = tanggalLogin;
    }

    public Integer getBulanLogin() {
        return bulanLogin;
    }

    public void setBulanLogin(Integer bulanLogin) {
        this.bulanLogin = bulanLogin;
    }

    public Integer getTahunLogin() {
        return tahunLogin;
    }

    public void setTahunLogin(Integer tahunLogin) {
        this.tahunLogin = tahunLogin;
    }

    public Long getLogoutMilis() {
        return logoutMilis;
    }

    public void setLogoutMilis(Long logoutMilis) {
        this.logoutMilis = logoutMilis;
    }

    public Integer getTanggalLogout() {
        return tanggalLogout;
    }

    public void setTanggalLogout(Integer tanggalLogout) {
        this.tanggalLogout = tanggalLogout;
    }

    public Integer getBulanLogout() {
        return bulanLogout;
    }

    public void setBulanLogout(Integer bulanLogout) {
        this.bulanLogout = bulanLogout;
    }

    public Integer getTahunLogout() {
        return tahunLogout;
    }

    public void setTahunLogout(Integer tahunLogout) {
        this.tahunLogout = tahunLogout;
    }
}
