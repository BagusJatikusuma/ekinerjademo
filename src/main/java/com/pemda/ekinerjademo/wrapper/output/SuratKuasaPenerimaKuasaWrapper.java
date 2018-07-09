package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 22/01/18.
 */
public class SuratKuasaPenerimaKuasaWrapper {
    private String kdSurat;
    private String createdDate;
    private Long createdDateMilis;
    private String nipPemberi;
    private String namaPemberi;
    private String jabatanPemberi;

    public SuratKuasaPenerimaKuasaWrapper() {
    }

    public SuratKuasaPenerimaKuasaWrapper(
            String kdSurat,
            String createdDate,
            Long createdDateMilis,
            String nipPemberi,
            String namaPemberi,
            String jabatanPemberi) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.createdDateMilis = createdDateMilis;
        this.nipPemberi = nipPemberi;
        this.namaPemberi = namaPemberi;
        this.jabatanPemberi = jabatanPemberi;
    }

    public String getKdSurat() {
        return kdSurat;
    }

    public void setKdSurat(String kdSurat) {
        this.kdSurat = kdSurat;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedDateMilis() {
        return createdDateMilis;
    }

    public void setCreatedDateMilis(Long createdDateMilis) {
        this.createdDateMilis = createdDateMilis;
    }

    public String getNipPemberi() {
        return nipPemberi;
    }

    public void setNipPemberi(String nipPemberi) {
        this.nipPemberi = nipPemberi;
    }

    public String getNamaPemberi() {
        return namaPemberi;
    }

    public void setNamaPemberi(String namaPemberi) {
        this.namaPemberi = namaPemberi;
    }

    public String getJabatanPemberi() {
        return jabatanPemberi;
    }

    public void setJabatanPemberi(String jabatanPemberi) {
        this.jabatanPemberi = jabatanPemberi;
    }
}
