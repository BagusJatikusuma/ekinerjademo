package com.pemda.ekinerjademo.wrapper.input;

/**
 * Created by bagus on 22/01/18.
 */
public class SuratPengantarIsiInputWrapper {
    private String naskahDinasYangDikirim;
    private Integer banyakNaskah;
    private String keterangan;

    public String getNaskahDinasYangDikirim() {
        return naskahDinasYangDikirim;
    }

    public void setNaskahDinasYangDikirim(String naskahDinasYangDikirim) {
        this.naskahDinasYangDikirim = naskahDinasYangDikirim;
    }

    public Integer getBanyakNaskah() {
        return banyakNaskah;
    }

    public void setBanyakNaskah(Integer banyakNaskah) {
        this.banyakNaskah = banyakNaskah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
