package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 22/01/18.
 */
public class SuratKuasaWrapper {
    private String nomorUrusan;
    private Integer nomorUrut;
    private String nomorPasanganUrut;
    private String nomorUnit;
    private Integer nomorTahun;

    private String nipPemberiKuasa;
    private String namaPemberiKuasa;
    private String jabatanPemberiKuasa;
    private String unitKerjaPemberiKuasa;

    private String nipPenerimaKuasa;
    private String namaPenerimaKuasa;
    private String jabatanPenerimaKuasa;
    private String unitKerjaPenerimaKuasa;

    private String isiKuasa;
    private String kotaPembuatanSurat;
    private Long tanggalPembuatanMilis;

    public SuratKuasaWrapper() {
    }

    public SuratKuasaWrapper(
            String nomorUrusan,
            Integer nomorUrut,
            String nomorPasanganUrut,
            String nomorUnit,
            Integer nomorTahun,
            String nipPemberiKuasa,
            String namaPemberiKuasa,
            String jabatanPemberiKuasa,
            String unitKerjaPemberiKuasa,
            String nipPenerimaKuasa,
            String namaPenerimaKuasa,
            String jabatanPenerimaKuasa,
            String unitKerjaPenerimaKuasa,
            String isiKuasa,
            String kotaPembuatanSurat,
            Long tanggalPembuatanMilis) {
        this.nomorUrusan = nomorUrusan;
        this.nomorUrut = nomorUrut;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.nipPemberiKuasa = nipPemberiKuasa;
        this.namaPemberiKuasa = namaPemberiKuasa;
        this.jabatanPemberiKuasa = jabatanPemberiKuasa;
        this.unitKerjaPemberiKuasa = unitKerjaPemberiKuasa;
        this.nipPenerimaKuasa = nipPenerimaKuasa;
        this.namaPenerimaKuasa = namaPenerimaKuasa;
        this.jabatanPenerimaKuasa = jabatanPenerimaKuasa;
        this.unitKerjaPenerimaKuasa = unitKerjaPenerimaKuasa;
        this.isiKuasa = isiKuasa;
        this.kotaPembuatanSurat = kotaPembuatanSurat;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
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

    public String getNipPemberiKuasa() {
        return nipPemberiKuasa;
    }

    public void setNipPemberiKuasa(String nipPemberiKuasa) {
        this.nipPemberiKuasa = nipPemberiKuasa;
    }

    public String getNamaPemberiKuasa() {
        return namaPemberiKuasa;
    }

    public void setNamaPemberiKuasa(String namaPemberiKuasa) {
        this.namaPemberiKuasa = namaPemberiKuasa;
    }

    public String getJabatanPemberiKuasa() {
        return jabatanPemberiKuasa;
    }

    public void setJabatanPemberiKuasa(String jabatanPemberiKuasa) {
        this.jabatanPemberiKuasa = jabatanPemberiKuasa;
    }

    public String getUnitKerjaPemberiKuasa() {
        return unitKerjaPemberiKuasa;
    }

    public void setUnitKerjaPemberiKuasa(String unitKerjaPemberiKuasa) {
        this.unitKerjaPemberiKuasa = unitKerjaPemberiKuasa;
    }

    public String getNipPenerimaKuasa() {
        return nipPenerimaKuasa;
    }

    public void setNipPenerimaKuasa(String nipPenerimaKuasa) {
        this.nipPenerimaKuasa = nipPenerimaKuasa;
    }

    public String getNamaPenerimaKuasa() {
        return namaPenerimaKuasa;
    }

    public void setNamaPenerimaKuasa(String namaPenerimaKuasa) {
        this.namaPenerimaKuasa = namaPenerimaKuasa;
    }

    public String getJabatanPenerimaKuasa() {
        return jabatanPenerimaKuasa;
    }

    public void setJabatanPenerimaKuasa(String jabatanPenerimaKuasa) {
        this.jabatanPenerimaKuasa = jabatanPenerimaKuasa;
    }

    public String getUnitKerjaPenerimaKuasa() {
        return unitKerjaPenerimaKuasa;
    }

    public void setUnitKerjaPenerimaKuasa(String unitKerjaPenerimaKuasa) {
        this.unitKerjaPenerimaKuasa = unitKerjaPenerimaKuasa;
    }

    public String getIsiKuasa() {
        return isiKuasa;
    }

    public void setIsiKuasa(String isiKuasa) {
        this.isiKuasa = isiKuasa;
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

}
