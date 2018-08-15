package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "anjab_jabatan")
public class AnjabJabatan {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_instansi")
    private Integer idInstansi;

    @Column(name = "id_jabatan")
    private Integer idJabatan;

    @Column(name = "kode_jabatan_bisma")
    private String kodeJabatanBisma;

    @Column(name = "nama_jabatan")
    private String namaJabatan;

    @Column(name = "jenis_jabatan")
    private String jenisJabatan;

    @Column(name = "eselon_jabatan")
    private String eselonJabatan;;

    @Column(name = "id_parent")
    private Integer idParent;

    @Column(name = "kode_jabatan")
    private String kodeJabatan;

    @Column(name = "eselon_i")
    private String eselonI;

    @Column(name = "eselon_ii")
    private String eselonII;

    @Column(name = "eselon_iii")
    private String eselonIII;

    @Column(name = "eselon_iv")
    private String eselonIV;

    @Column(name = "ikhtisar_jabatan")
    private String ikhtisarJabatan;

    @Column(name = "urutan")
    private Integer urutan;

    @Column(name = "progres_pengisian")
    private Double progresPengisian;

    @Column(name = "jumlah_abk_bkd")
    private Integer jumlahAbkBkd;

    @Column(name = "jumlah_pns_bkd")
    private Integer jumlahPnsBkd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdInstansi() {
        return idInstansi;
    }

    public void setIdInstansi(Integer idInstansi) {
        this.idInstansi = idInstansi;
    }

    public Integer getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(Integer idJabatan) {
        this.idJabatan = idJabatan;
    }

    public String getKodeJabatanBisma() {
        return kodeJabatanBisma;
    }

    public void setKodeJabatanBisma(String kodeJabatanBisma) {
        this.kodeJabatanBisma = kodeJabatanBisma;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public String getJenisJabatan() {
        return jenisJabatan;
    }

    public void setJenisJabatan(String jenisJabatan) {
        this.jenisJabatan = jenisJabatan;
    }

    public String getEselonJabatan() {
        return eselonJabatan;
    }

    public void setEselonJabatan(String eselonJabatan) {
        this.eselonJabatan = eselonJabatan;
    }

    public Integer getIdParent() {
        return idParent;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }

    public String getKodeJabatan() {
        return kodeJabatan;
    }

    public void setKodeJabatan(String kodeJabatan) {
        this.kodeJabatan = kodeJabatan;
    }

    public String getEselonI() {
        return eselonI;
    }

    public void setEselonI(String eselonI) {
        this.eselonI = eselonI;
    }

    public String getEselonII() {
        return eselonII;
    }

    public void setEselonII(String eselonII) {
        this.eselonII = eselonII;
    }

    public String getEselonIII() {
        return eselonIII;
    }

    public void setEselonIII(String eselonIII) {
        this.eselonIII = eselonIII;
    }

    public String getEselonIV() {
        return eselonIV;
    }

    public void setEselonIV(String eselonIV) {
        this.eselonIV = eselonIV;
    }

    public String getIkhtisarJabatan() {
        return ikhtisarJabatan;
    }

    public void setIkhtisarJabatan(String ikhtisarJabatan) {
        this.ikhtisarJabatan = ikhtisarJabatan;
    }

    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
    }

    public Double getProgresPengisian() {
        return progresPengisian;
    }

    public void setProgresPengisian(Double progresPengisian) {
        this.progresPengisian = progresPengisian;
    }

    public Integer getJumlahAbkBkd() {
        return jumlahAbkBkd;
    }

    public void setJumlahAbkBkd(Integer jumlahAbkBkd) {
        this.jumlahAbkBkd = jumlahAbkBkd;
    }

    public Integer getJumlahPnsBkd() {
        return jumlahPnsBkd;
    }

    public void setJumlahPnsBkd(Integer jumlahPnsBkd) {
        this.jumlahPnsBkd = jumlahPnsBkd;
    }
}
