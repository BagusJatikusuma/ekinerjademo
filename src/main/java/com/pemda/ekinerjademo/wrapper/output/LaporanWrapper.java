package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 21/01/18.
 */
public class LaporanWrapper {
    private String kdLaporan;

    private String tentang;
    private String umum;
    private String maksudDanTujuan;
    private String ruangLingkup;
    private String dasar;
    private String kegiatanYangDilaksanakan;
    private String hasilYangDicapai;
    private String simpulanDanSaran;
    private String penutup;

    private String nipPenandatangan;
    private String namaPenandatangan;
    private String jabatanPenandatangan;
    private String unitKerjaPenandatangan;
    private String gelarDepanPenandantangan;
    private String gelarBelakangPenandatangan;
    private String pangkatPenandantangan;
    private String golonganPenandatangan;

    private Integer statusBaca;
    private String kotaPembuatanSurat;
    private Long tanggalPembuatanMilis;

    private String nipPembuatSurat;
    private String namaPembuatSurat;
    private String jabatanPembuatSurat;
    private String unitKerjaPembuatSurat;
    private String gelarDepanPembuatSurat;
    private String gelarBelakangPembuatSurat;
    private String pangkatPembuatSurat;
    private String golonganPembuatSurat;
    private String barcodeImage;


    public LaporanWrapper(){
    }

    public LaporanWrapper(String kdLaporan,
                          String tentang,
                          String umum,
                          String maksudDanTujuan,
                          String ruangLingkup,
                          String dasar,
                          String kegiatanYangDilaksanakan,
                          String hasilYangDicapai,
                          String simpulanDanSaran,
                          String penutup,
                          String nipPenandatangan,
                          String namaPenandatangan,
                          String jabatanPenandatangan,
                          String unitKerjaPenandatangan,
                          String gelarDepanPenandantangan,
                          String gelarBelakangPenandatangan,
                          String pangkatPenandantangan,
                          String golonganPenandatangan,
                          Integer statusBaca,
                          String kotaPembuatanSurat,
                          Long tanggalPembuatanMilis,
                          String nipPembuatSurat,
                          String namaPembuatSurat,
                          String jabatanPembuatSurat,
                          String unitKerjaPembuatSurat,
                          String gelarDepanPembuatSurat,
                          String gelarBelakangPembuatSurat,
                          String pangkatPembuatSurat,
                          String golonganPembuatSurat,
                          String barcodeImage) {
        this.kdLaporan = kdLaporan;
        this.tentang = tentang;
        this.umum = umum;
        this.maksudDanTujuan = maksudDanTujuan;
        this.ruangLingkup = ruangLingkup;
        this.dasar = dasar;
        this.kegiatanYangDilaksanakan = kegiatanYangDilaksanakan;
        this.hasilYangDicapai = hasilYangDicapai;
        this.simpulanDanSaran = simpulanDanSaran;
        this.penutup = penutup;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
        this.gelarDepanPenandantangan = gelarDepanPenandantangan;
        this.gelarBelakangPenandatangan = gelarBelakangPenandatangan;
        this.pangkatPenandantangan = pangkatPenandantangan;
        this.golonganPenandatangan = golonganPenandatangan;
        this.statusBaca = statusBaca;
        this.kotaPembuatanSurat = kotaPembuatanSurat;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.nipPembuatSurat = nipPembuatSurat;
        this.namaPembuatSurat = namaPembuatSurat;
        this.jabatanPembuatSurat = jabatanPembuatSurat;
        this.unitKerjaPembuatSurat = unitKerjaPembuatSurat;
        this.gelarDepanPembuatSurat = gelarDepanPembuatSurat;
        this.gelarBelakangPembuatSurat = gelarBelakangPembuatSurat;
        this.pangkatPembuatSurat = pangkatPembuatSurat;
        this.golonganPembuatSurat = golonganPembuatSurat;
        this.barcodeImage = barcodeImage;
    }

    public String getKdLaporan() {
        return kdLaporan;
    }

    public void setKdLaporan(String kdLaporan) {
        this.kdLaporan = kdLaporan;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getUmum() {
        return umum;
    }

    public void setUmum(String umum) {
        this.umum = umum;
    }

    public String getMaksudDanTujuan() {
        return maksudDanTujuan;
    }

    public void setMaksudDanTujuan(String maksudDanTujuan) {
        this.maksudDanTujuan = maksudDanTujuan;
    }

    public String getRuangLingkup() {
        return ruangLingkup;
    }

    public void setRuangLingkup(String ruangLingkup) {
        this.ruangLingkup = ruangLingkup;
    }

    public String getDasar() {
        return dasar;
    }

    public void setDasar(String dasar) {
        this.dasar = dasar;
    }

    public String getKegiatanYangDilaksanakan() {
        return kegiatanYangDilaksanakan;
    }

    public void setKegiatanYangDilaksanakan(String kegiatanYangDilaksanakan) {
        this.kegiatanYangDilaksanakan = kegiatanYangDilaksanakan;
    }

    public String getHasilYangDicapai() {
        return hasilYangDicapai;
    }

    public void setHasilYangDicapai(String hasilYangDicapai) {
        this.hasilYangDicapai = hasilYangDicapai;
    }

    public String getSimpulanDanSaran() {
        return simpulanDanSaran;
    }

    public void setSimpulanDanSaran(String simpulanDanSaran) {
        this.simpulanDanSaran = simpulanDanSaran;
    }

    public String getPenutup() {
        return penutup;
    }

    public void setPenutup(String penutup) {
        this.penutup = penutup;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public String getNamaPenandatangan() {
        return namaPenandatangan;
    }

    public void setNamaPenandatangan(String namaPenandatangan) {
        this.namaPenandatangan = namaPenandatangan;
    }

    public String getJabatanPenandatangan() {
        return jabatanPenandatangan;
    }

    public void setJabatanPenandatangan(String jabatanPenandatangan) {
        this.jabatanPenandatangan = jabatanPenandatangan;
    }

    public String getUnitKerjaPenandatangan() {
        return unitKerjaPenandatangan;
    }

    public void setUnitKerjaPenandatangan(String unitKerjaPenandatangan) {
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
    }

    public String getGelarDepanPenandantangan() {
        return gelarDepanPenandantangan;
    }

    public void setGelarDepanPenandantangan(String gelarDepanPenandantangan) {
        this.gelarDepanPenandantangan = gelarDepanPenandantangan;
    }

    public String getGelarBelakangPenandatangan() {
        return gelarBelakangPenandatangan;
    }

    public void setGelarBelakangPenandatangan(String gelarBelakangPenandatangan) {
        this.gelarBelakangPenandatangan = gelarBelakangPenandatangan;
    }

    public String getPangkatPenandantangan() {
        return pangkatPenandantangan;
    }

    public void setPangkatPenandantangan(String pangkatPenandantangan) {
        this.pangkatPenandantangan = pangkatPenandantangan;
    }

    public String getGolonganPenandatangan() {
        return golonganPenandatangan;
    }

    public void setGolonganPenandatangan(String golonganPenandatangan) {
        this.golonganPenandatangan = golonganPenandatangan;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }

    public String getKotaPembuatanSurat() {
        return kotaPembuatanSurat;
    }

    public void setKotaPembuatanSurat(String kotaPembuatanSurat) {
        this.kotaPembuatanSurat = kotaPembuatanSurat;
    }

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public String getNipPembuatSurat() {
        return nipPembuatSurat;
    }

    public void setNipPembuatSurat(String nipPembuatSurat) {
        this.nipPembuatSurat = nipPembuatSurat;
    }

    public String getNamaPembuatSurat() {
        return namaPembuatSurat;
    }

    public void setNamaPembuatSurat(String namaPembuatSurat) {
        this.namaPembuatSurat = namaPembuatSurat;
    }

    public String getJabatanPembuatSurat() {
        return jabatanPembuatSurat;
    }

    public void setJabatanPembuatSurat(String jabatanPembuatSurat) {
        this.jabatanPembuatSurat = jabatanPembuatSurat;
    }

    public String getUnitKerjaPembuatSurat() {
        return unitKerjaPembuatSurat;
    }

    public void setUnitKerjaPembuatSurat(String unitKerjaPembuatSurat) {
        this.unitKerjaPembuatSurat = unitKerjaPembuatSurat;
    }

    public String getGelarDepanPembuatSurat() {
        return gelarDepanPembuatSurat;
    }

    public void setGelarDepanPembuatSurat(String gelarDepanPembuatSurat) {
        this.gelarDepanPembuatSurat = gelarDepanPembuatSurat;
    }

    public String getGelarBelakangPembuatSurat() {
        return gelarBelakangPembuatSurat;
    }

    public void setGelarBelakangPembuatSurat(String gelarBelakangPembuatSurat) {
        this.gelarBelakangPembuatSurat = gelarBelakangPembuatSurat;
    }

    public String getPangkatPembuatSurat() {
        return pangkatPembuatSurat;
    }

    public void setPangkatPembuatSurat(String pangkatPembuatSurat) {
        this.pangkatPembuatSurat = pangkatPembuatSurat;
    }

    public String getGolonganPembuatSurat() {
        return golonganPembuatSurat;
    }

    public void setGolonganPembuatSurat(String golonganPembuatSurat) {
        this.golonganPembuatSurat = golonganPembuatSurat;
    }

    public String getBarcodeImage() {
        return barcodeImage;
    }

    public void setBarcodeImage(String barcodeImage) {
        this.barcodeImage = barcodeImage;
    }
}
