package com.pemda.ekinerjademo.wrapper.output;

public class DraftlembarDisposisiWrapper {
    private String kdLembarDisposisi;
    private String dariSuratDisposisi;
    private Long tanggalPenerimaanSuratDisposisi;
    private boolean approvedBySekdin;
    private boolean signedByKadin;

    public DraftlembarDisposisiWrapper() {
    }

    public DraftlembarDisposisiWrapper(String kdLembarDisposisi, String dariSuratDisposisi, Long tanggalPenerimaanSuratDisposisi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.dariSuratDisposisi = dariSuratDisposisi;
        this.tanggalPenerimaanSuratDisposisi = tanggalPenerimaanSuratDisposisi;
    }

    public DraftlembarDisposisiWrapper(String kdLembarDisposisi, String dariSuratDisposisi, Long tanggalPenerimaanSuratDisposisi, boolean approvedBySekdin, boolean signedByKadin) {
        this.kdLembarDisposisi = kdLembarDisposisi;
        this.dariSuratDisposisi = dariSuratDisposisi;
        this.tanggalPenerimaanSuratDisposisi = tanggalPenerimaanSuratDisposisi;
        this.approvedBySekdin = approvedBySekdin;
        this.signedByKadin = signedByKadin;
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

    public boolean isApprovedBySekdin() {
        return approvedBySekdin;
    }

    public void setApprovedBySekdin(boolean approvedBySekdin) {
        this.approvedBySekdin = approvedBySekdin;
    }

    public boolean isSignedByKadin() {
        return signedByKadin;
    }

    public void setSignedByKadin(boolean signedByKadin) {
        this.signedByKadin = signedByKadin;
    }
}
