package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

/**
 * Created by bagus on 24/01/18.
 */
public class SuratUndanganWrapper {
    private String kdSuratUndangan;

    private String nomorUrusan;
    private Integer nomorUrut;
    private String nomorPasanganUrut;
    private String nomorUnit;
    private Integer nomorTahun;

    private String kdJabatanPenerimaSuratUndangan;
    private String jabatanPenerimaSuratUndangan;
    private Long tanggalPembuatanSurat;
    private String kotaPembuatanSurat;
    private String sifat;
    private String lampiran;
    private String hal;

    private String nipPenerimaSuratUndangan;
    private String namaPenerimaSuratUndangan;
    private String unitKerjaPenerimaSuratUndangan;
    private String gelarDepanPenerimaSuratUndangan;
    private String gelarBelakangPenerimaSuratUndangan;
    private String pangkatPenerimaSuratUndangan;
    private String golonganPenerimaSuratUndangan;

    private String bagianPembukaSuratUndangan;
    private String bagianIsiHariSuratUndangan;
    private Long bagianIsiTanggalSuratUndangan;
    private String bagianIsiWaktuSuratUndangan;
    private String bagianIsiTempatSuratUndangan;
    private String bagianIsiAcaraSuratUndangan;
    private String bagianPenutupSuratUndangan;

    private String nipPenandatangan;
    private String namaPenandatangan;
    private String unitKerjaPenandatangan;
    private String jabatanPenandatangan;
    private String gelarDepanPenandatangan;
    private String gelarBelakangPenandatangan;
    private String pangkatPenandatangan;
    private String golonganPenandatangan;

    private List<JabatanWrapper> tembusanSuratUndanganList;

    private boolean isSuratPejabat;

    public SuratUndanganWrapper() {
    }
    public SuratUndanganWrapper(
            String kdSuratUndangan,
            String nomorUrusan,
            Integer nomorUrut,
            String nomorPasanganUrut,
            String nomorUnit,
            Integer nomorTahun,
            String kdJabatanPenerimaSuratUndangan,
            String jabatanPenerimaSuratUndangan,
            Long tanggalPembuatanSurat,
            String kotaPembuatanSurat,
            String sifat,
            String lampiran,
            String hal,
            String nipPenerimaSuratUndangan,
            String namaPenerimaSuratUndangan,
            String unitKerjaPenerimaSuratUndangan,
            String gelarDepanPenerimaSuratUndangan, String gelarBelakangPenerimaSuratUndangan, String pangkatPenerimaSuratUndangan, String golonganPenerimaSuratUndangan, String bagianPembukaSuratUndangan,
            String bagianIsiHariSuratUndangan,
            Long bagianIsiTanggalSuratUndangan,
            String bagianIsiWaktuSuratUndangan,
            String bagianIsiTempatSuratUndangan,
            String bagianIsiAcaraSuratUndangan,
            String bagianPenutupSuratUndangan,
            String nipPenandatangan,
            String namaPenandatangan,
            String unitKerjaPenandatangan,
            String jabatanPenandatangan, String gelarDepanPenandatangan, String gelarBelakangPenandatangan, String pangkatPenandatangan, String golonganPenandatangan, List<JabatanWrapper> tembusanSuratUndanganList,
            boolean isSuratPejabat) {
        this.kdSuratUndangan = kdSuratUndangan;
        this.nomorUrusan = nomorUrusan;
        this.nomorUrut = nomorUrut;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.kdJabatanPenerimaSuratUndangan = kdJabatanPenerimaSuratUndangan;
        this.jabatanPenerimaSuratUndangan = jabatanPenerimaSuratUndangan;
        this.tanggalPembuatanSurat = tanggalPembuatanSurat;
        this.kotaPembuatanSurat = kotaPembuatanSurat;
        this.sifat = sifat;
        this.lampiran = lampiran;
        this.hal = hal;
        this.nipPenerimaSuratUndangan = nipPenerimaSuratUndangan;
        this.namaPenerimaSuratUndangan = namaPenerimaSuratUndangan;
        this.unitKerjaPenerimaSuratUndangan = unitKerjaPenerimaSuratUndangan;
        this.gelarDepanPenerimaSuratUndangan = gelarDepanPenerimaSuratUndangan;
        this.gelarBelakangPenerimaSuratUndangan = gelarBelakangPenerimaSuratUndangan;
        this.pangkatPenerimaSuratUndangan = pangkatPenerimaSuratUndangan;
        this.golonganPenerimaSuratUndangan = golonganPenerimaSuratUndangan;
        this.bagianPembukaSuratUndangan = bagianPembukaSuratUndangan;
        this.bagianIsiHariSuratUndangan = bagianIsiHariSuratUndangan;
        this.bagianIsiTanggalSuratUndangan = bagianIsiTanggalSuratUndangan;
        this.bagianIsiWaktuSuratUndangan = bagianIsiWaktuSuratUndangan;
        this.bagianIsiTempatSuratUndangan = bagianIsiTempatSuratUndangan;
        this.bagianIsiAcaraSuratUndangan = bagianIsiAcaraSuratUndangan;
        this.bagianPenutupSuratUndangan = bagianPenutupSuratUndangan;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
        this.gelarDepanPenandatangan = gelarDepanPenandatangan;
        this.gelarBelakangPenandatangan = gelarBelakangPenandatangan;
        this.pangkatPenandatangan = pangkatPenandatangan;
        this.golonganPenandatangan = golonganPenandatangan;
        this.tembusanSuratUndanganList = tembusanSuratUndanganList;
        this.isSuratPejabat = isSuratPejabat;
    }

    public String getKdSuratUndangan() {
        return kdSuratUndangan;
    }

    public void setKdSuratUndangan(String kdSuratUndangan) {
        this.kdSuratUndangan = kdSuratUndangan;
    }

    public String getNomorUrusan() {
        return nomorUrusan;
    }

    public void setNomorUrusan(String nomorUrusan) {
        this.nomorUrusan = nomorUrusan;
    }

    public Integer getNomorUrut() {
        return nomorUrut;
    }

    public void setNomorUrut(Integer nomorUrut) {
        this.nomorUrut = nomorUrut;
    }

    public String getNomorPasanganUrut() {
        return nomorPasanganUrut;
    }

    public void setNomorPasanganUrut(String nomorPasanganUrut) {
        this.nomorPasanganUrut = nomorPasanganUrut;
    }

    public String getNomorUnit() {
        return nomorUnit;
    }

    public void setNomorUnit(String nomorUnit) {
        this.nomorUnit = nomorUnit;
    }

    public Integer getNomorTahun() {
        return nomorTahun;
    }

    public void setNomorTahun(Integer nomorTahun) {
        this.nomorTahun = nomorTahun;
    }

    public String getKdJabatanPenerimaSuratUndangan() {
        return kdJabatanPenerimaSuratUndangan;
    }

    public void setKdJabatanPenerimaSuratUndangan(String kdJabatanPenerimaSuratUndangan) {
        this.kdJabatanPenerimaSuratUndangan = kdJabatanPenerimaSuratUndangan;
    }

    public String getJabatanPenerimaSuratUndangan() {
        return jabatanPenerimaSuratUndangan;
    }

    public void setJabatanPenerimaSuratUndangan(String jabatanPenerimaSuratUndangan) {
        this.jabatanPenerimaSuratUndangan = jabatanPenerimaSuratUndangan;
    }

    public Long getTanggalPembuatanSurat() {
        return tanggalPembuatanSurat;
    }

    public void setTanggalPembuatanSurat(Long tanggalPembuatanSurat) {
        this.tanggalPembuatanSurat = tanggalPembuatanSurat;
    }

    public String getKotaPembuatanSurat() {
        return kotaPembuatanSurat;
    }

    public void setKotaPembuatanSurat(String kotaPembuatanSurat) {
        this.kotaPembuatanSurat = kotaPembuatanSurat;
    }

    public String getSifat() {
        return sifat;
    }

    public void setSifat(String sifat) {
        this.sifat = sifat;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public String getNipPenerimaSuratUndangan() {
        return nipPenerimaSuratUndangan;
    }

    public void setNipPenerimaSuratUndangan(String nipPenerimaSuratUndangan) {
        this.nipPenerimaSuratUndangan = nipPenerimaSuratUndangan;
    }

    public String getNamaPenerimaSuratUndangan() {
        return namaPenerimaSuratUndangan;
    }

    public void setNamaPenerimaSuratUndangan(String namaPenerimaSuratUndangan) {
        this.namaPenerimaSuratUndangan = namaPenerimaSuratUndangan;
    }

    public String getUnitKerjaPenerimaSuratUndangan() {
        return unitKerjaPenerimaSuratUndangan;
    }

    public void setUnitKerjaPenerimaSuratUndangan(String unitKerjaPenerimaSuratUndangan) {
        this.unitKerjaPenerimaSuratUndangan = unitKerjaPenerimaSuratUndangan;
    }

    public String getBagianPembukaSuratUndangan() {
        return bagianPembukaSuratUndangan;
    }

    public void setBagianPembukaSuratUndangan(String bagianPembukaSuratUndangan) {
        this.bagianPembukaSuratUndangan = bagianPembukaSuratUndangan;
    }

    public String getBagianIsiHariSuratUndangan() {
        return bagianIsiHariSuratUndangan;
    }

    public void setBagianIsiHariSuratUndangan(String bagianIsiHariSuratUndangan) {
        this.bagianIsiHariSuratUndangan = bagianIsiHariSuratUndangan;
    }

    public Long getBagianIsiTanggalSuratUndangan() {
        return bagianIsiTanggalSuratUndangan;
    }

    public void setBagianIsiTanggalSuratUndangan(Long bagianIsiTanggalSuratUndangan) {
        this.bagianIsiTanggalSuratUndangan = bagianIsiTanggalSuratUndangan;
    }

    public String getBagianIsiWaktuSuratUndangan() {
        return bagianIsiWaktuSuratUndangan;
    }

    public void setBagianIsiWaktuSuratUndangan(String bagianIsiWaktuSuratUndangan) {
        this.bagianIsiWaktuSuratUndangan = bagianIsiWaktuSuratUndangan;
    }

    public String getBagianIsiTempatSuratUndangan() {
        return bagianIsiTempatSuratUndangan;
    }

    public void setBagianIsiTempatSuratUndangan(String bagianIsiTempatSuratUndangan) {
        this.bagianIsiTempatSuratUndangan = bagianIsiTempatSuratUndangan;
    }

    public String getBagianIsiAcaraSuratUndangan() {
        return bagianIsiAcaraSuratUndangan;
    }

    public void setBagianIsiAcaraSuratUndangan(String bagianIsiAcaraSuratUndangan) {
        this.bagianIsiAcaraSuratUndangan = bagianIsiAcaraSuratUndangan;
    }

    public String getBagianPenutupSuratUndangan() {
        return bagianPenutupSuratUndangan;
    }

    public void setBagianPenutupSuratUndangan(String bagianPenutupSuratUndangan) {
        this.bagianPenutupSuratUndangan = bagianPenutupSuratUndangan;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public String getNamaPenandatangan() {
        return namaPenandatangan;
    }

    public void setNamaPenandatangan(String namaPenandatangan) {
        this.namaPenandatangan = namaPenandatangan;
    }

    public String getUnitKerjaPenandatangan() {
        return unitKerjaPenandatangan;
    }

    public void setUnitKerjaPenandatangan(String unitKerjaPenandatangan) {
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
    }

    public List<JabatanWrapper> getTembusanSuratUndanganList() {
        return tembusanSuratUndanganList;
    }

    public void setTembusanSuratUndanganList(List<JabatanWrapper> tembusanSuratUndanganList) {
        this.tembusanSuratUndanganList = tembusanSuratUndanganList;
    }

    public boolean isSuratPejabat() {
        return isSuratPejabat;
    }

    public void setSuratPejabat(boolean suratPejabat) {
        isSuratPejabat = suratPejabat;
    }

    public String getJabatanPenandatangan() {
        return jabatanPenandatangan;
    }

    public void setJabatanPenandatangan(String jabatanPenandatangan) {
        this.jabatanPenandatangan = jabatanPenandatangan;
    }

    public String getGelarDepanPenandatangan() {
        return gelarDepanPenandatangan;
    }

    public void setGelarDepanPenandatangan(String gelarDepanPenandatangan) {
        this.gelarDepanPenandatangan = gelarDepanPenandatangan;
    }

    public String getGelarBelakangPenandatangan() {
        return gelarBelakangPenandatangan;
    }

    public void setGelarBelakangPenandatangan(String gelarBelakangPenandatangan) {
        this.gelarBelakangPenandatangan = gelarBelakangPenandatangan;
    }

    public String getPangkatPenandatangan() {
        return pangkatPenandatangan;
    }

    public void setPangkatPenandatangan(String pangkatPenandatangan) {
        this.pangkatPenandatangan = pangkatPenandatangan;
    }

    public String getGolonganPenandatangan() {
        return golonganPenandatangan;
    }

    public void setGolonganPenandatangan(String golonganPenandatangan) {
        this.golonganPenandatangan = golonganPenandatangan;
    }

    public String getGelarDepanPenerimaSuratUndangan() {
        return gelarDepanPenerimaSuratUndangan;
    }

    public void setGelarDepanPenerimaSuratUndangan(String gelarDepanPenerimaSuratUndangan) {
        this.gelarDepanPenerimaSuratUndangan = gelarDepanPenerimaSuratUndangan;
    }

    public String getGelarBelakangPenerimaSuratUndangan() {
        return gelarBelakangPenerimaSuratUndangan;
    }

    public void setGelarBelakangPenerimaSuratUndangan(String gelarBelakangPenerimaSuratUndangan) {
        this.gelarBelakangPenerimaSuratUndangan = gelarBelakangPenerimaSuratUndangan;
    }

    public String getPangkatPenerimaSuratUndangan() {
        return pangkatPenerimaSuratUndangan;
    }

    public void setPangkatPenerimaSuratUndangan(String pangkatPenerimaSuratUndangan) {
        this.pangkatPenerimaSuratUndangan = pangkatPenerimaSuratUndangan;
    }

    public String getGolonganPenerimaSuratUndangan() {
        return golonganPenerimaSuratUndangan;
    }

    public void setGolonganPenerimaSuratUndangan(String golonganPenerimaSuratUndangan) {
        this.golonganPenerimaSuratUndangan = golonganPenerimaSuratUndangan;
    }
}
