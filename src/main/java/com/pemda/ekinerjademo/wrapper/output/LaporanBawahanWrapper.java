package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 24/12/17.
 */
public class LaporanBawahanWrapper {
    private String kdSurat;
    private String jenisSurat;
    private String nipBawahan;
    private Integer StatusPenilaian;
    private Integer kdJenisSurat;

    public LaporanBawahanWrapper() {
    }
    public LaporanBawahanWrapper(
            String kdSurat,
            String jenisSurat,
            String nipBawahan,
            Integer statusPenilaian) {
        this.kdSurat = kdSurat;
        this.jenisSurat = jenisSurat;
        this.nipBawahan = nipBawahan;
        StatusPenilaian = statusPenilaian;
    }
    public LaporanBawahanWrapper(
            String kdSurat,
            String jenisSurat,
            String nipBawahan,
            Integer statusPenilaian,
            Integer kdJenisSurat) {
        this.kdSurat = kdSurat;
        this.jenisSurat = jenisSurat;
        this.nipBawahan = nipBawahan;
        StatusPenilaian = statusPenilaian;
        this.kdJenisSurat = kdJenisSurat;
    }

    public String getKdSurat() {
        return kdSurat;
    }

    public void setKdSurat(String kdSurat) {
        this.kdSurat = kdSurat;
    }

    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public String getNipBawahan() {
        return nipBawahan;
    }

    public void setNipBawahan(String nipBawahan) {
        this.nipBawahan = nipBawahan;
    }

    public Integer getStatusPenilaian() {
        return StatusPenilaian;
    }

    public void setStatusPenilaian(Integer statusPenilaian) {
        StatusPenilaian = statusPenilaian;
    }

    public Integer getKdJenisSurat() {
        return kdJenisSurat;
    }

    public void setKdJenisSurat(Integer kdJenisSurat) {
        this.kdJenisSurat = kdJenisSurat;
    }
}
