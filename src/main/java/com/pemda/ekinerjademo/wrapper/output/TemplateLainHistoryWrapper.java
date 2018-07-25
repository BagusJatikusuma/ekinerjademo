package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 27/12/17.
 */
public class TemplateLainHistoryWrapper {
    private String kdTemplateLain;

    private String keterangan;

    private String pathFile;

    private String pathFileExtension;

    private Integer statusPenilaian;

    private Long tanggalPembuatanMilis;

    private String alasanPenolakan;

    private String jenisSurat;

    private Integer kdJenisSurat;

    private Integer approvalPenandatangan;

    public TemplateLainHistoryWrapper() {
    }
    public TemplateLainHistoryWrapper(
            String kdTemplateLain,
            String keterangan,
            String pathFile,
            Integer statusPenilaian,
            Long tanggalPembuatanMilis,
            String alasanPenolakan,
            String jenisSurat,
            Integer kdJenisSurat) {
        this.kdTemplateLain = kdTemplateLain;
        this.keterangan = keterangan;
        this.pathFile = pathFile;
        this.statusPenilaian = statusPenilaian;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.alasanPenolakan = alasanPenolakan;
        this.jenisSurat = jenisSurat;
        this.kdJenisSurat = kdJenisSurat;
    }

    public TemplateLainHistoryWrapper(
            String kdTemplateLain,
            String keterangan,
            String pathFile,
            String pathFileExtension,
            Integer statusPenilaian,
            Long tanggalPembuatanMilis,
            String alasanPenolakan,
            String jenisSurat,
            Integer kdJenisSurat) {
        this.kdTemplateLain = kdTemplateLain;
        this.keterangan = keterangan;
        this.pathFile = pathFile;
        this.pathFileExtension = pathFileExtension;
        this.statusPenilaian = statusPenilaian;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.alasanPenolakan = alasanPenolakan;
        this.jenisSurat = jenisSurat;
        this.kdJenisSurat = kdJenisSurat;
    }

    public TemplateLainHistoryWrapper(String kdTemplateLain, String keterangan, String pathFile, String pathFileExtension, Integer statusPenilaian, Long tanggalPembuatanMilis, String alasanPenolakan, String jenisSurat, Integer kdJenisSurat, Integer approvalPenandatangan) {
        this.kdTemplateLain = kdTemplateLain;
        this.keterangan = keterangan;
        this.pathFile = pathFile;
        this.pathFileExtension = pathFileExtension;
        this.statusPenilaian = statusPenilaian;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.alasanPenolakan = alasanPenolakan;
        this.jenisSurat = jenisSurat;
        this.kdJenisSurat = kdJenisSurat;
        this.approvalPenandatangan = approvalPenandatangan;
    }

    public String getKdTemplateLain() {
        return kdTemplateLain;
    }

    public void setKdTemplateLain(String kdTemplateLain) {
        this.kdTemplateLain = kdTemplateLain;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public Integer getStatusPenilaian() {
        return statusPenilaian;
    }

    public void setStatusPenilaian(Integer statusPenilaian) {
        this.statusPenilaian = statusPenilaian;
    }

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public String getAlasanPenolakan() {
        return alasanPenolakan;
    }

    public void setAlasanPenolakan(String alasanPenolakan) {
        this.alasanPenolakan = alasanPenolakan;
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

    public String getPathFileExtension() {
        return pathFileExtension;
    }

    public void setPathFileExtension(String pathFileExtension) {
        this.pathFileExtension = pathFileExtension;
    }

    public Integer getApprovalPenandatangan() {
        return approvalPenandatangan;
    }

    public void setApprovalPenandatangan(Integer approvalPenandatangan) {
        this.approvalPenandatangan = approvalPenandatangan;
    }
}
