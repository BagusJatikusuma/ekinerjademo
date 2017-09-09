package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
@Entity
@Table(name = "akun_pegawai")
public class AkunPegawai {
    @Id
    @Column(name = "nip_pegawai")
    private String nipPegawai;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "akunPegawai")
    private List<RincianEKinerja> rincianEKinerjaList;

    public AkunPegawai() {}

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RincianEKinerja> getRincianEKinerjaList() {
        return rincianEKinerjaList;
    }

    public void setRincianEKinerjaList(List<RincianEKinerja> rincianEKinerjaList) {
        this.rincianEKinerjaList = rincianEKinerjaList;
    }
}
