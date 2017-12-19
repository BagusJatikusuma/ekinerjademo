package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 16/11/17.
 */
public class SuratPerintahTargetWrapper {
    private String kdSurat;
    private String createdDate;
    private Long createdDateMilis;
    private boolean isSuratPejabat;
    private String nipPemberi;
    private String namaPemberi;
    private String jabatanPemberi;
    private Integer statusBaca;

    public SuratPerintahTargetWrapper() {
    }
    public SuratPerintahTargetWrapper(
            String kdSurat,
            String createdDate,
            boolean isSuratPejabat,
            String nipPemberi,
            String namaPemberi,
            String jabatanPemberi) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.isSuratPejabat = isSuratPejabat;
        this.nipPemberi = nipPemberi;
        this.namaPemberi = namaPemberi;
        this.jabatanPemberi = jabatanPemberi;
    }
    public SuratPerintahTargetWrapper(
            String kdSurat,
            String createdDate,
            Long createdDateMilis,
            boolean isSuratPejabat,
            String nipPemberi,
            String namaPemberi,
            String jabatanPemberi) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.createdDateMilis = createdDateMilis;
        this.isSuratPejabat = isSuratPejabat;
        this.nipPemberi = nipPemberi;
        this.namaPemberi = namaPemberi;
        this.jabatanPemberi = jabatanPemberi;
    }
    public SuratPerintahTargetWrapper(
            String kdSurat,
            String createdDate,
            Long createdDateMilis,
            boolean isSuratPejabat,
            String nipPemberi,
            String namaPemberi,
            String jabatanPemberi,
            Integer statusBaca) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.createdDateMilis = createdDateMilis;
        this.isSuratPejabat = isSuratPejabat;
        this.nipPemberi = nipPemberi;
        this.namaPemberi = namaPemberi;
        this.jabatanPemberi = jabatanPemberi;
        this.statusBaca = statusBaca;
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

    public boolean isSuratPejabat() {
        return isSuratPejabat;
    }

    public void setSuratPejabat(boolean suratPejabat) {
        isSuratPejabat = suratPejabat;
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

    public Long getCreatedDateMilis() {
        return createdDateMilis;
    }

    public void setCreatedDateMilis(Long createdDateMilis) {
        this.createdDateMilis = createdDateMilis;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }
}
