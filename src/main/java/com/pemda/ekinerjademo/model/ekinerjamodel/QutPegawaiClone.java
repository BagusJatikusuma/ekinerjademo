package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by bayu on 10/12/17.
 */

@Entity
@Table(name = "qut_pegawai_clone")
public class QutPegawaiClone {
    @Id
    @Column(name = "NIP")
    public String nip;

    @Column(name = "NIPLm")
    public String nipLm;

    @Column(name = "nama")
    private String nama;

    @Column(name = "GlrDpn")
    private String glrDpn;

    @Column(name = "GlrBlk")
    private String glrBlk;

    @Column(name = "TmtLahir")
    private String tmtLahir;

    @Column(name = "TglLahir")
    private Date tglLahir;

    @Column(name = "JnsKlm")
    private String jnsKlm;

    @Column(name = "Agama")
    private String agama;

    @Column(name = "StKwn")
    private String stKwn;

    @Column(name = "JmlAnak")
    private Integer jmlAnak;

    @Column(name = "AlRumah")
    private String alRumah;

    @Column(name = "TlpRumah")
    private String tlpRumah;

    @Column(name = "TlpHP")
    private String tlpHP;

    @Column(name = "GolDarah")
    private String golDarah;

    @Column(name = "TmtCpns")
    private Date tmtCpns;

    @Column(name = "TmtPNS")
    private Date tmtPNS;

    @Column(name = "KdJnPg")
    private String kdJnPg;

    @Column(name = "JnsKepeg")
    private String jnsKepeg;

    @Column(name = "PjbAngAwl")
    private String pjbAngAwl;

    @Column(name = "Gol")
    private String gol;

    @Column(name = "Pangkat")
    private String pangkat;

    @Column(name = "TmtGol")
    private Date tmtGol;

    @Column(name = "NoSKgol")
    private String noSKgol;

    @Column(name = "TglSKgol")
    private Date tglSKgol;

    @Column(name = "PjTTDSKKP")
    private String pjTTDSKKP;

    @Column(name = "KdJabatan")
    private String kdJabatan;

    @Column(name = "Jabatan")
    private String jabatan;

    @Column(name = "KdUntSbr")
    private  String kdUntSbr;

    @Column(name = "UnkerFUng")
    private String unkerFUng;

    @Column(name = "KdJjJab")
    private String kdJjJab;

    @Column(name = "JenJAb")
    private String jenJAb;

    @Column(name = "AKLm")
    private String AKLm;

    @Column(name = "TunJafung")
    private Float tunJafung;

    @Column(name = "Eselon")
    private String eselon;

    @Column(name = "Jabatan1")
    private String jabatan1;

    @Column(name = "UnitSBr")
    private String unitSBr;

    @Column(name = "TmtJAb")
    private Date tmtJAb;

    @Column(name = "NoSKJab")
    private String noSKJab;

    @Column(name = "TglSKJb")
    private Date tglSKJb;

    @Column(name = "PjTTDSKJb")
    private String pjTTDSKJb;

    @Column(name = "pada")
    private String pada;

    @Column(name = "UnitKerja")
    private String unitKerja;

    @Column(name = "TnjJabatan")
    private Float tnjJabatan;

    @Column(name = "TunJab")
    private Float tunJab;

    @Column(name = "MkTh")
    private Integer mkTh;

    @Column(name = "MkBl")
    private Integer mkBl;

    @Column(name = "GajiPokok")
    private Float gajiPokok;

    @Column(name = "KdDikStruAkr")
    private Integer kdDikStruAkr;

    @Column(name = "KdDikStruAkr2")
    private Integer kdDikStruAkr2;

    @Column(name = "DikStruk")
    private String dikStruk;

    @Column(name = "Penyelenggara")
    private String penyelenggara;

    @Column(name = "AngkatanSt")
    private String angkatanSt;

    @Column(name = "JumlahJam")
    private Float jumlahJam;

    @Column(name = "TglIjasah")
    private Date tglIjasah;

    @Column(name = "NoIjasahDS")
    private String noIjasahDS;

    @Column(name = "TlpUnitKrj")
    private String tlpUnitKrj;

    @Column(name = "KdPendFormal")
    private Integer kdPendFormal;

    @Column(name = "Pendidikan")
    private String pendidikan;

    @Column(name = "Fakultas")
    private String fakultas;

    @Column(name = "Jurusan")
    private String jurusan;

    @Column(name = "TglLulus")
    private Date tglLulus;

    @Column(name = "NoIjasahPF")
    private String noIjasahPF;

    @Column(name = "Alumni")
    private String alumni;

    @Column(name = "ThLsPen")
    private Integer thLsPen;

    @Column(name = "NmKepSek")
    private String nmKepSek;

    @Column(name = "Operator1")
    private String operator1;

    @Column(name = "sUpdate")
    private String sUpdate;

    @Column(name = "sts")
    private String sts;

    @Column(name = "NmLEN")
    private String nmLEN;

    @Column(name = "Gab")
    private String gab;

    @Column(name = "KdUnitKerja")
    private String kdUnitKerja;

    @Column(name = "BUP")
    private Float BUP;

    @Column(name = "NoKarpeg")
    private String noKarpeg;

    @Column(name = "NoKaris_Karsu")
    private String noKaris_Karsu;

    @Column(name = "NoTaspen")
    private String noTaspen;

    @Column(name = "IDKdHukum")
    private String IDKdHukum;

    @Column(name = "KetHukum")
    private String ketHukum;

    @Column(name = "Pensiun")
    private Date pensiun;

    @Column(name = "JnsJab")
    private String jnsJab;

    @Column(name = "ProyeksiKP")
    private Date proyeksiKP;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNipLm() {
        return nipLm;
    }

    public void setNipLm(String nipLm) {
        this.nipLm = nipLm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGlrDpn() {
        return glrDpn;
    }

    public void setGlrDpn(String glrDpn) {
        this.glrDpn = glrDpn;
    }

    public String getGlrBlk() {
        return glrBlk;
    }

    public void setGlrBlk(String glrBlk) {
        this.glrBlk = glrBlk;
    }

    public String getTmtLahir() {
        return tmtLahir;
    }

    public void setTmtLahir(String tmtLahir) {
        this.tmtLahir = tmtLahir;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getJnsKlm() {
        return jnsKlm;
    }

    public void setJnsKlm(String jnsKlm) {
        this.jnsKlm = jnsKlm;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStKwn() {
        return stKwn;
    }

    public void setStKwn(String stKwn) {
        this.stKwn = stKwn;
    }

    public Integer getJmlAnak() {
        return jmlAnak;
    }

    public void setJmlAnak(Integer jmlAnak) {
        this.jmlAnak = jmlAnak;
    }

    public String getAlRumah() {
        return alRumah;
    }

    public void setAlRumah(String alRumah) {
        this.alRumah = alRumah;
    }

    public String getTlpRumah() {
        return tlpRumah;
    }

    public void setTlpRumah(String tlpRumah) {
        this.tlpRumah = tlpRumah;
    }

    public String getTlpHP() {
        return tlpHP;
    }

    public void setTlpHP(String tlpHP) {
        this.tlpHP = tlpHP;
    }

    public String getGolDarah() {
        return golDarah;
    }

    public void setGolDarah(String golDarah) {
        this.golDarah = golDarah;
    }

    public Date getTmtCpns() {
        return tmtCpns;
    }

    public void setTmtCpns(Date tmtCpns) {
        this.tmtCpns = tmtCpns;
    }

    public Date getTmtPNS() {
        return tmtPNS;
    }

    public void setTmtPNS(Date tmtPNS) {
        this.tmtPNS = tmtPNS;
    }

    public String getKdJnPg() {
        return kdJnPg;
    }

    public void setKdJnPg(String kdJnPg) {
        this.kdJnPg = kdJnPg;
    }

    public String getJnsKepeg() {
        return jnsKepeg;
    }

    public void setJnsKepeg(String jnsKepeg) {
        this.jnsKepeg = jnsKepeg;
    }

    public String getPjbAngAwl() {
        return pjbAngAwl;
    }

    public void setPjbAngAwl(String pjbAngAwl) {
        this.pjbAngAwl = pjbAngAwl;
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

    public Date getTmtGol() {
        return tmtGol;
    }

    public void setTmtGol(Date tmtGol) {
        this.tmtGol = tmtGol;
    }

    public String getNoSKgol() {
        return noSKgol;
    }

    public void setNoSKgol(String noSKgol) {
        this.noSKgol = noSKgol;
    }

    public Date getTglSKgol() {
        return tglSKgol;
    }

    public void setTglSKgol(Date tglSKgol) {
        this.tglSKgol = tglSKgol;
    }

    public String getPjTTDSKKP() {
        return pjTTDSKKP;
    }

    public void setPjTTDSKKP(String pjTTDSKKP) {
        this.pjTTDSKKP = pjTTDSKKP;
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

    public String getKdUntSbr() {
        return kdUntSbr;
    }

    public void setKdUntSbr(String kdUntSbr) {
        this.kdUntSbr = kdUntSbr;
    }

    public String getUnkerFUng() {
        return unkerFUng;
    }

    public void setUnkerFUng(String unkerFUng) {
        this.unkerFUng = unkerFUng;
    }

    public String getKdJjJab() {
        return kdJjJab;
    }

    public void setKdJjJab(String kdJjJab) {
        this.kdJjJab = kdJjJab;
    }

    public String getJenJAb() {
        return jenJAb;
    }

    public void setJenJAb(String jenJAb) {
        this.jenJAb = jenJAb;
    }

    public String getAKLm() {
        return AKLm;
    }

    public void setAKLm(String AKLm) {
        this.AKLm = AKLm;
    }

    public Float getTunJafung() {
        return tunJafung;
    }

    public void setTunJafung(Float tunJafung) {
        this.tunJafung = tunJafung;
    }

    public String getEselon() {
        return eselon;
    }

    public void setEselon(String eselon) {
        this.eselon = eselon;
    }

    public String getJabatan1() {
        return jabatan1;
    }

    public void setJabatan1(String jabatan1) {
        this.jabatan1 = jabatan1;
    }

    public String getUnitSBr() {
        return unitSBr;
    }

    public void setUnitSBr(String unitSBr) {
        this.unitSBr = unitSBr;
    }

    public Date getTmtJAb() {
        return tmtJAb;
    }

    public void setTmtJAb(Date tmtJAb) {
        this.tmtJAb = tmtJAb;
    }

    public String getNoSKJab() {
        return noSKJab;
    }

    public void setNoSKJab(String noSKJab) {
        this.noSKJab = noSKJab;
    }

    public Date getTglSKJb() {
        return tglSKJb;
    }

    public void setTglSKJb(Date tglSKJb) {
        this.tglSKJb = tglSKJb;
    }

    public String getPjTTDSKJb() {
        return pjTTDSKJb;
    }

    public void setPjTTDSKJb(String pjTTDSKJb) {
        this.pjTTDSKJb = pjTTDSKJb;
    }

    public String getPada() {
        return pada;
    }

    public void setPada(String pada) {
        this.pada = pada;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }

    public Float getTnjJabatan() {
        return tnjJabatan;
    }

    public void setTnjJabatan(Float tnjJabatan) {
        this.tnjJabatan = tnjJabatan;
    }

    public Float getTunJab() {
        return tunJab;
    }

    public void setTunJab(Float tunJab) {
        this.tunJab = tunJab;
    }

    public Integer getMkTh() {
        return mkTh;
    }

    public void setMkTh(Integer mkTh) {
        this.mkTh = mkTh;
    }

    public Integer getMkBl() {
        return mkBl;
    }

    public void setMkBl(Integer mkBl) {
        this.mkBl = mkBl;
    }

    public Float getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(Float gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public Integer getKdDikStruAkr() {
        return kdDikStruAkr;
    }

    public void setKdDikStruAkr(Integer kdDikStruAkr) {
        this.kdDikStruAkr = kdDikStruAkr;
    }

    public Integer getKdDikStruAkr2() {
        return kdDikStruAkr2;
    }

    public void setKdDikStruAkr2(Integer kdDikStruAkr2) {
        this.kdDikStruAkr2 = kdDikStruAkr2;
    }

    public String getDikStruk() {
        return dikStruk;
    }

    public void setDikStruk(String dikStruk) {
        this.dikStruk = dikStruk;
    }

    public String getPenyelenggara() {
        return penyelenggara;
    }

    public void setPenyelenggara(String penyelenggara) {
        this.penyelenggara = penyelenggara;
    }

    public String getAngkatanSt() {
        return angkatanSt;
    }

    public void setAngkatanSt(String angkatanSt) {
        this.angkatanSt = angkatanSt;
    }

    public Float getJumlahJam() {
        return jumlahJam;
    }

    public void setJumlahJam(Float jumlahJam) {
        this.jumlahJam = jumlahJam;
    }

    public Date getTglIjasah() {
        return tglIjasah;
    }

    public void setTglIjasah(Date tglIjasah) {
        this.tglIjasah = tglIjasah;
    }

    public String getNoIjasahDS() {
        return noIjasahDS;
    }

    public void setNoIjasahDS(String noIjasahDS) {
        this.noIjasahDS = noIjasahDS;
    }

    public String getTlpUnitKrj() {
        return tlpUnitKrj;
    }

    public void setTlpUnitKrj(String tlpUnitKrj) {
        this.tlpUnitKrj = tlpUnitKrj;
    }

    public Integer getKdPendFormal() {
        return kdPendFormal;
    }

    public void setKdPendFormal(Integer kdPendFormal) {
        this.kdPendFormal = kdPendFormal;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public Date getTglLulus() {
        return tglLulus;
    }

    public void setTglLulus(Date tglLulus) {
        this.tglLulus = tglLulus;
    }

    public String getNoIjasahPF() {
        return noIjasahPF;
    }

    public void setNoIjasahPF(String noIjasahPF) {
        this.noIjasahPF = noIjasahPF;
    }

    public String getAlumni() {
        return alumni;
    }

    public void setAlumni(String alumni) {
        this.alumni = alumni;
    }

    public Integer getThLsPen() {
        return thLsPen;
    }

    public void setThLsPen(Integer thLsPen) {
        this.thLsPen = thLsPen;
    }

    public String getNmKepSek() {
        return nmKepSek;
    }

    public void setNmKepSek(String nmKepSek) {
        this.nmKepSek = nmKepSek;
    }

    public String getOperator1() {
        return operator1;
    }

    public void setOperator1(String operator1) {
        this.operator1 = operator1;
    }

    public String getsUpdate() {
        return sUpdate;
    }

    public void setsUpdate(String sUpdate) {
        this.sUpdate = sUpdate;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getNmLEN() {
        return nmLEN;
    }

    public void setNmLEN(String nmLEN) {
        this.nmLEN = nmLEN;
    }

    public String getGab() {
        return gab;
    }

    public void setGab(String gab) {
        this.gab = gab;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public Float getBUP() {
        return BUP;
    }

    public void setBUP(Float BUP) {
        this.BUP = BUP;
    }

    public String getNoKarpeg() {
        return noKarpeg;
    }

    public void setNoKarpeg(String noKarpeg) {
        this.noKarpeg = noKarpeg;
    }

    public String getNoKaris_Karsu() {
        return noKaris_Karsu;
    }

    public void setNoKaris_Karsu(String noKaris_Karsu) {
        this.noKaris_Karsu = noKaris_Karsu;
    }

    public String getNoTaspen() {
        return noTaspen;
    }

    public void setNoTaspen(String noTaspen) {
        this.noTaspen = noTaspen;
    }

    public String getIDKdHukum() {
        return IDKdHukum;
    }

    public void setIDKdHukum(String IDKdHukum) {
        this.IDKdHukum = IDKdHukum;
    }

    public String getKetHukum() {
        return ketHukum;
    }

    public void setKetHukum(String ketHukum) {
        this.ketHukum = ketHukum;
    }

    public Date getPensiun() {
        return pensiun;
    }

    public void setPensiun(Date pensiun) {
        this.pensiun = pensiun;
    }

    public String getJnsJab() {
        return jnsJab;
    }

    public void setJnsJab(String jnsJab) {
        this.jnsJab = jnsJab;
    }

    public Date getProyeksiKP() {
        return proyeksiKP;
    }

    public void setProyeksiKP(Date proyeksiKP) {
        this.proyeksiKP = proyeksiKP;
    }

}
