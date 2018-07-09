package com.pemda.ekinerjademo.wrapper.output;

public class SuratEdaranSubWrapper {
    private String kdSuratEdaran;
    private String namaSub;
    private String isiSub;

    public SuratEdaranSubWrapper() {
    }

    public SuratEdaranSubWrapper(String kdSuratEdaran,
                                 String namaSub,
                                 String isiSub) {
        this.kdSuratEdaran = kdSuratEdaran;
        this.namaSub = namaSub;
        this.isiSub = isiSub;
    }

    public String getKdSuratEdaran() {
        return kdSuratEdaran;
    }

    public void setKdSuratEdaran(String kdSuratEdaran) {
        this.kdSuratEdaran = kdSuratEdaran;
    }

    public String getNamaSub() {
        return namaSub;
    }

    public void setNamaSub(String namaSub) {
        this.namaSub = namaSub;
    }

    public String getIsiSub() {
        return isiSub;
    }

    public void setIsiSub(String isiSub) {
        this.isiSub = isiSub;
    }
}
