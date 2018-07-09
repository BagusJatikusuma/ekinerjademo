package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by bagus on 27/09/17.
 */
@Embeddable
public class UraianTugasPegawaiTahunanId implements Serializable {
    @Column(name = "kd_urtug")
    private String kdUrtug;

    @Column(name = "kd_jabatan")
    private String kdJabatan;

    @Column(name = "kd_jenis_urtug")
    private String kdJenisUrtug;

    @Column(name = "tahun_urtug")
    private Integer tahunUrtug;

    @Column(name = "nip_pegawai")
    private String nipPegawai;

    public UraianTugasPegawaiTahunanId() {}
    public UraianTugasPegawaiTahunanId(
            String kdUrtug,
            String kdJabatan,
            String kdJenisUrtug,
            Integer tahunUrtug,
            String nipPegawai) {
        this.kdUrtug = kdUrtug;
        this.kdJabatan = kdJabatan;
        this.kdJenisUrtug = kdJenisUrtug;
        this.tahunUrtug = tahunUrtug;
        this.nipPegawai = nipPegawai;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getKdUrtug() {
        return kdUrtug;
    }

    public void setKdUrtug(String kdUrtug) {
        this.kdUrtug = kdUrtug;
    }

    public String getKdJabatan() {
        return kdJabatan;
    }

    public void setKdJabatan(String kdJabatan) {
        this.kdJabatan = kdJabatan;
    }

    public String getKdJenisUrtug() {
        return kdJenisUrtug;
    }

    public void setKdJenisUrtug(String kdJenisUrtug) {
        this.kdJenisUrtug = kdJenisUrtug;
    }

    public Integer getTahunUrtug() {
        return tahunUrtug;
    }

    public void setTahunUrtug(Integer tahunUrtug) {
        this.tahunUrtug = tahunUrtug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UraianTugasPegawaiTahunanId that = (UraianTugasPegawaiTahunanId) o;

        if (!kdUrtug.equals(that.kdUrtug)) return false;
        if (!kdJabatan.equals(that.kdJabatan)) return false;
        if (!kdJenisUrtug.equals(that.kdJenisUrtug)) return false;
        if (!tahunUrtug.equals(that.tahunUrtug)) return false;
        return nipPegawai.equals(that.nipPegawai);
    }

    @Override
    public int hashCode() {
        int result = kdUrtug.hashCode();
        result = 31 * result + kdJabatan.hashCode();
        result = 31 * result + kdJenisUrtug.hashCode();
        result = 31 * result + tahunUrtug.hashCode();
        result = 31 * result + nipPegawai.hashCode();
        return result;
    }

}
