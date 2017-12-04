package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by bagus on 22/11/17.
 */
@Entity
@Table(name = "surat_instruksi")
public class SuratInstruksi {
    @Id
    @Column(name = "kd_instruksi")
    private String kdInstruksi;
    @Column(name = "judul_instruksi")
    private String judulInstruksi;
    @Column(name = "nomor")
    private String nomor;
    @Column(name = "tahun")
    private Integer tahun;
    @Column(name = "tentang")
    private String tentang;
    @Column(name = "alasan")
    private String alasan;
    @Column(name = "isi_instruksi")
    private String isiInstruksi;
    @Column(name = "dikeluarkan_di")
    private String dikeluarkanDi;
    @Column(name = "created_date_milis")
    private Long createddateMilis;
    @Column(name = "nip_pembuat")
    private String nipPembuat;
    @Column(name = "nip_penandatangan")
    private String nipPenandatangan;
    @OneToMany(mappedBy = "suratInstruksi")
    private Set<InstruksiPegawai> instruksiPegawaiSet;
    @OneToMany(mappedBy = "suratInstruksi")
    private Set<InstruksiPejabat> instruksiPejabatSet;
    @OneToOne(mappedBy = "suratInstruksi", fetch = FetchType.LAZY)
    private SuratInstruksiPejabat suratInstruksiPejabat;
    @OneToOne(mappedBy = "suratInstruksi", fetch = FetchType.LAZY)
    private SuratInstruksiNonPejabat suratInstruksiNonPejabat;

    public SuratInstruksi() {
    }
    public SuratInstruksi(String kdInstruksi) {
        this.kdInstruksi = kdInstruksi;
    }

    public String getKdInstruksi() {
        return kdInstruksi;
    }

    public void setKdInstruksi(String kdInstruksi) {
        this.kdInstruksi = kdInstruksi;
    }

    public String getJudulInstruksi() {
        return judulInstruksi;
    }

    public void setJudulInstruksi(String judulInstruksi) {
        this.judulInstruksi = judulInstruksi;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getIsiInstruksi() {
        return isiInstruksi;
    }

    public void setIsiInstruksi(String isiInstruksi) {
        this.isiInstruksi = isiInstruksi;
    }

    public String getDikeluarkanDi() {
        return dikeluarkanDi;
    }

    public void setDikeluarkanDi(String dikeluarkanDi) {
        this.dikeluarkanDi = dikeluarkanDi;
    }

    public Long getCreateddateMilis() {
        return createddateMilis;
    }

    public void setCreateddateMilis(Long createddateMilis) {
        this.createddateMilis = createddateMilis;
    }

    public String getNipPembuat() {
        return nipPembuat;
    }

    public void setNipPembuat(String nipPembuat) {
        this.nipPembuat = nipPembuat;
    }

    public Set<InstruksiPegawai> getInstruksiPegawaiSet() {
        return instruksiPegawaiSet;
    }

    public void setInstruksiPegawaiSet(Set<InstruksiPegawai> instruksiPegawaiSet) {
        this.instruksiPegawaiSet = instruksiPegawaiSet;
    }

    public Set<InstruksiPejabat> getInstruksiPejabatSet() {
        return instruksiPejabatSet;
    }

    public void setInstruksiPejabatSet(Set<InstruksiPejabat> instruksiPejabatSet) {
        this.instruksiPejabatSet = instruksiPejabatSet;
    }

    public SuratInstruksiPejabat getSuratInstruksiPejabat() {
        return suratInstruksiPejabat;
    }

    public void setSuratInstruksiPejabat(SuratInstruksiPejabat suratInstruksiPejabat) {
        this.suratInstruksiPejabat = suratInstruksiPejabat;
    }

    public SuratInstruksiNonPejabat getSuratInstruksiNonPejabat() {
        return suratInstruksiNonPejabat;
    }

    public void setSuratInstruksiNonPejabat(SuratInstruksiNonPejabat suratInstruksiNonPejabat) {
        this.suratInstruksiNonPejabat = suratInstruksiNonPejabat;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }
}
