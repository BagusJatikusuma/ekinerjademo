package com.pemda.ekinerjademo.wrapper.input;

/**
 * Created by bagus on 25/12/17.
 */
public class LaporanBawahanInputWrapper {
    private String kdSurat;
    private Integer kdJenisSurat;
    private String alasanPenolakan;

    public String getKdSurat() {
        return kdSurat;
    }

    public void setKdSurat(String kdSurat) {
        this.kdSurat = kdSurat;
    }

    public Integer getKdJenisSurat() {
        return kdJenisSurat;
    }

    public void setKdJenisSurat(Integer kdJenisSurat) {
        this.kdJenisSurat = kdJenisSurat;
    }

    public String getAlasanPenolakan() {
        return alasanPenolakan;
    }

    public void setAlasanPenolakan(String alasanPenolakan) {
        this.alasanPenolakan = alasanPenolakan;
    }
}
