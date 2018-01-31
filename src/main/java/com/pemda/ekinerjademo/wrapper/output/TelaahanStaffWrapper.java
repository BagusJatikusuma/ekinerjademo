package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

public class TelaahanStaffWrapper {
    private String kdTelaahanStaf;

    private String tentang;
    private String persoalan;
    private String praanggapan;
    private String faktaYangMemppengaruhi;
    private String analisis;
    private String simpulan;
    private String saran;

    private CustomPegawaiCredential nipPenandatangan;
    private Long tanggalPembuatanMilis;
    private CustomPegawaiCredential nipPembuatSurat;
    private String unitKerjaPembuatSurat;


    public TelaahanStaffWrapper() {
    }

    public TelaahanStaffWrapper(String kdTelaahanStaf,
                                String tentang,
                                String persoalan,
                                String praanggapan,
                                String faktaYangMemppengaruhi,
                                String analisis,
                                String simpulan,
                                String saran,
                                CustomPegawaiCredential nipPenandatangan,
                                Long tanggalPembuatanMilis,
                                CustomPegawaiCredential nipPembuatSurat,
                                String unitKerjaPembuatSurat) {
        this.kdTelaahanStaf = kdTelaahanStaf;
        this.tentang = tentang;
        this.persoalan = persoalan;
        this.praanggapan = praanggapan;
        this.faktaYangMemppengaruhi = faktaYangMemppengaruhi;
        this.analisis = analisis;
        this.simpulan = simpulan;
        this.saran = saran;
        this.nipPenandatangan = nipPenandatangan;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.nipPembuatSurat = nipPembuatSurat;
        this.unitKerjaPembuatSurat = unitKerjaPembuatSurat;
    }

    public String getKdTelaahanStaf() {
        return kdTelaahanStaf;
    }

    public void setKdTelaahanStaf(String kdTelaahanStaf) {
        this.kdTelaahanStaf = kdTelaahanStaf;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getPersoalan() {
        return persoalan;
    }

    public void setPersoalan(String persoalan) {
        this.persoalan = persoalan;
    }

    public String getPraanggapan() {
        return praanggapan;
    }

    public void setPraanggapan(String praanggapan) {
        this.praanggapan = praanggapan;
    }

    public String getFaktaYangMemppengaruhi() {
        return faktaYangMemppengaruhi;
    }

    public void setFaktaYangMemppengaruhi(String faktaYangMemppengaruhi) {
        this.faktaYangMemppengaruhi = faktaYangMemppengaruhi;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public String getSimpulan() {
        return simpulan;
    }

    public void setSimpulan(String simpulan) {
        this.simpulan = simpulan;
    }

    public String getSaran() {
        return saran;
    }

    public void setSaran(String saran) {
        this.saran = saran;
    }

    public CustomPegawaiCredential getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(CustomPegawaiCredential nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public CustomPegawaiCredential getNipPembuatSurat() {
        return nipPembuatSurat;
    }

    public void setNipPembuatSurat(CustomPegawaiCredential nipPembuatSurat) {
        this.nipPembuatSurat = nipPembuatSurat;
    }

    public String getUnitKerjaPembuatSurat() {
        return unitKerjaPembuatSurat;
    }

    public void setUnitKerjaPembuatSurat(String unitKerjaPembuatSurat) {
        this.unitKerjaPembuatSurat = unitKerjaPembuatSurat;
    }
}
