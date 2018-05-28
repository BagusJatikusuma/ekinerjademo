package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

public class DraftSuratApprovalWrapper {
    private String kdSurat;
    private String createdDate;
    private Long createdDateMilis;

    private boolean isSuratPejabat;

    private String nipPemberi;
    private String namaPemberi;
    private String jabatanPemberi;

    private Integer statusPenyebaran;

    private String jenisSurat;
    private Integer kdJenisSurat;
    private List<JabatanWrapper> jabatanTargetList;

    public DraftSuratApprovalWrapper() {
    }

    public DraftSuratApprovalWrapper(
            String kdSurat,
            String createdDate,
            Long createdDateMilis,
            boolean isSuratPejabat,
            String nipPemberi,
            String namaPemberi,
            String jabatanPemberi,
            Integer statusPenyebaran) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.createdDateMilis = createdDateMilis;
        this.isSuratPejabat = isSuratPejabat;
        this.nipPemberi = nipPemberi;
        this.namaPemberi = namaPemberi;
        this.jabatanPemberi = jabatanPemberi;
        this.statusPenyebaran = statusPenyebaran;
    }

    public DraftSuratApprovalWrapper(String kdSurat, String createdDate, Long createdDateMilis, boolean isSuratPejabat, String nipPemberi, String namaPemberi, String jabatanPemberi, Integer statusPenyebaran, String jenisSurat, Integer kdJenisSurat) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.createdDateMilis = createdDateMilis;
        this.isSuratPejabat = isSuratPejabat;
        this.nipPemberi = nipPemberi;
        this.namaPemberi = namaPemberi;
        this.jabatanPemberi = jabatanPemberi;
        this.statusPenyebaran = statusPenyebaran;
        this.jenisSurat = jenisSurat;
        this.kdJenisSurat = kdJenisSurat;
    }

    public DraftSuratApprovalWrapper(String kdSurat, String createdDate, Long createdDateMilis, boolean isSuratPejabat, String nipPemberi, String namaPemberi, String jabatanPemberi, Integer statusPenyebaran, String jenisSurat, Integer kdJenisSurat, List<JabatanWrapper> jabatanTargetList) {
        this.kdSurat = kdSurat;
        this.createdDate = createdDate;
        this.createdDateMilis = createdDateMilis;
        this.isSuratPejabat = isSuratPejabat;
        this.nipPemberi = nipPemberi;
        this.namaPemberi = namaPemberi;
        this.jabatanPemberi = jabatanPemberi;
        this.statusPenyebaran = statusPenyebaran;
        this.jenisSurat = jenisSurat;
        this.kdJenisSurat = kdJenisSurat;
        this.jabatanTargetList = jabatanTargetList;
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

    public Integer getStatusPenyebaran() {
        return statusPenyebaran;
    }

    public void setStatusPenyebaran(Integer statusPenyebaran) {
        this.statusPenyebaran = statusPenyebaran;
    }

    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public Integer getKdJenisSurat() {
        return kdJenisSurat;
    }

    public void setKdJenisSurat(Integer kdJenisSurat) {
        this.kdJenisSurat = kdJenisSurat;
    }

    public List<JabatanWrapper> getJabatanTargetList() {
        return jabatanTargetList;
    }

    public void setJabatanTargetList(List<JabatanWrapper> jabatanTargetList) {
        this.jabatanTargetList = jabatanTargetList;
    }
}
