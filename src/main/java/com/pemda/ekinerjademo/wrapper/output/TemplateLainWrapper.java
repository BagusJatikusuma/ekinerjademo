package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 26/12/17.
 */
public class TemplateLainWrapper {
    private String kdTemplateLain;
    private String keterangan;
    private Long tanggalPembuatanMilis;
    private String pathFile;

    public TemplateLainWrapper() {
    }

    public TemplateLainWrapper(
            String kdTemplateLain,
            String keterangan,
            Long tanggalPembuatanMilis,
            String pathFile) {
        this.kdTemplateLain = kdTemplateLain;
        this.keterangan = keterangan;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.pathFile = pathFile;
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

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
}
