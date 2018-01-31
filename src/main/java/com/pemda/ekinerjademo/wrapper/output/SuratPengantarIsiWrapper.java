package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 31/01/18.
 */
public class SuratPengantarIsiWrapper {
    private String naskahDinasYangDikirim;
    private Integer banyakNaskah;
    private String keterangan;

    public SuratPengantarIsiWrapper() {
    }

    public SuratPengantarIsiWrapper(String naskahDinasYangDikirim, Integer banyakNaskah, String keterangan) {
        this.naskahDinasYangDikirim = naskahDinasYangDikirim;
        this.banyakNaskah = banyakNaskah;
        this.keterangan = keterangan;
    }

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
