package com.pemda.ekinerjademo.wrapper.output;

public class SuratDisposisiWrapper {
    private Integer jenisSurat;
    private Object suratDisposisi;

    public SuratDisposisiWrapper() {
    }
    public SuratDisposisiWrapper(Integer jenisSurat, Object suratDisposisi) {
        this.jenisSurat = jenisSurat;
        this.suratDisposisi = suratDisposisi;
    }

    public Integer getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(Integer jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public Object getSuratDisposisi() {
        return suratDisposisi;
    }

    public void setSuratDisposisi(Object suratDisposisi) {
        this.suratDisposisi = suratDisposisi;
    }
}
