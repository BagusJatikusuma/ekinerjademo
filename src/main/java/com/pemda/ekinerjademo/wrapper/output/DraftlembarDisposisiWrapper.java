package com.pemda.ekinerjademo.wrapper.output;

public class DraftlembarDisposisiWrapper {
    private String kdLembarDisposisi;
    private String dariSuratDisposisi;
    private Long tanggalPenerimaanSuratDisposisi;

    public DraftlembarDisposisiWrapper() {
    }

    public DraftlembarDisposisiWrapper(String kdLembarDisposisi, String dariSuratDisposisi, Long tanggalPenerimaanSuratDisposisi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.dariSuratDisposisi = dariSuratDisposisi;
        this.tanggalPenerimaanSuratDisposisi = tanggalPenerimaanSuratDisposisi;
    }

    public String getKdLembarDisposisi() {
        return kdLembarDisposisi;
    }

    public void setKdLembarDisposisi(String kdLembarDisposisi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
    }

    public String getDariSuratDisposisi() {
        return dariSuratDisposisi;
    }

    public void setDariSuratDisposisi(String dariSuratDisposisi) {
        this.dariSuratDisposisi = dariSuratDisposisi;
    }

    public Long getTanggalPenerimaanSuratDisposisi() {
        return tanggalPenerimaanSuratDisposisi;
    }

    public void setTanggalPenerimaanSuratDisposisi(Long tanggalPenerimaanSuratDisposisi) {
        this.tanggalPenerimaanSuratDisposisi = tanggalPenerimaanSuratDisposisi;
    }
}
