package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 22/01/18.
 */
public class PengumumanWrapper {
    private String nomorUrusan;
    private Integer nomorUrut;
    private String nomorPasanganUrut;
    private String nomorUnit;
    private Integer nomorTahun;

    private String tentang;
    private String isiPengumuman;

    private String nipPenandatangan;
    private String namaPenandatangan;
    private String jabatanPenandatangan;
    private String unitKerjaPenandatangan;
    private String gelarDepanPenandatangan;
    private String gelarBelakangPenandatangan;
    private String pangkatPenandatangan;
    private String golonganPenandatangan;

    private String kotaPembuatanSurat;
    private Long tanggalPembuatanMilis;
    private byte[] barcodeImage;

    public PengumumanWrapper() {
    }

    public PengumumanWrapper(String nomorUrusan, Integer nomorUrut, String nomorPasanganUrut, String nomorUnit, Integer nomorTahun, String tentang, String isiPengumuman, String nipPenandatangan, String namaPenandatangan, String jabatanPenandatangan, String unitKerjaPenandatangan, String gelarDepanPenandatangan, String gelarBelakangPenandatangan, String pangkatPenandatangan, String golonganPenandatangan, String kotaPembuatanSurat, Long tanggalPembuatanMilis, byte[] barcodeImage) {
        this.nomorUrusan = nomorUrusan;
        this.nomorUrut = nomorUrut;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.tentang = tentang;
        this.isiPengumuman = isiPengumuman;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
        this.gelarDepanPenandatangan = gelarDepanPenandatangan;
        this.gelarBelakangPenandatangan = gelarBelakangPenandatangan;
        this.pangkatPenandatangan = pangkatPenandatangan;
        this.golonganPenandatangan = golonganPenandatangan;
        this.kotaPembuatanSurat = kotaPembuatanSurat;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.barcodeImage = barcodeImage;
    }

    public String getNomorUrusan() {
        return nomorUrusan;
    }

    public void setNomorUrusan(String nomorUrusan) {
        this.nomorUrusan = nomorUrusan;
    }

    public Integer getNomorUrut() {
        return nomorUrut;
    }

    public void setNomorUrut(Integer nomorUrut) {
        this.nomorUrut = nomorUrut;
    }

    public String getNomorPasanganUrut() {
        return nomorPasanganUrut;
    }

    public void setNomorPasanganUrut(String nomorPasanganUrut) {
        this.nomorPasanganUrut = nomorPasanganUrut;
    }

    public String getNomorUnit() {
        return nomorUnit;
    }

    public void setNomorUnit(String nomorUnit) {
        this.nomorUnit = nomorUnit;
    }

    public Integer getNomorTahun() {
        return nomorTahun;
    }

    public void setNomorTahun(Integer nomorTahun) {
        this.nomorTahun = nomorTahun;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getIsiPengumuman() {
        return isiPengumuman;
    }

    public void setIsiPengumuman(String isiPengumuman) {
        this.isiPengumuman = isiPengumuman;
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

    public String getGelarDepanPenandatangan() {
        return gelarDepanPenandatangan;
    }

    public void setGelarDepanPenandatangan(String gelarDepanPenandatangan) {
        this.gelarDepanPenandatangan = gelarDepanPenandatangan;
    }

    public String getGelarBelakangPenandatangan() {
        return gelarBelakangPenandatangan;
    }

    public void setGelarBelakangPenandatangan(String gelarBelakangPenandatangan) {
        this.gelarBelakangPenandatangan = gelarBelakangPenandatangan;
    }

    public String getPangkatPenandatangan() {
        return pangkatPenandatangan;
    }

    public void setPangkatPenandatangan(String pangkatPenandatangan) {
        this.pangkatPenandatangan = pangkatPenandatangan;
    }

    public String getGolonganPenandatangan() {
        return golonganPenandatangan;
    }

    public void setGolonganPenandatangan(String golonganPenandatangan) {
        this.golonganPenandatangan = golonganPenandatangan;
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

    public byte[] getBarcodeImage() {
        return barcodeImage;
    }

    public void setBarcodeImage(byte[] barcodeImage) {
        this.barcodeImage = barcodeImage;
    }
}
