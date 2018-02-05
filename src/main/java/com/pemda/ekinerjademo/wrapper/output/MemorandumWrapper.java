package com.pemda.ekinerjademo.wrapper.output;

import java.util.List;

/**
 * Created by bagus on 21/01/18.
 */
public class MemorandumWrapper {
    private String kdMemorandum;

    private String nomorUrusan;
    private Integer nomorUrut;
    private String nomorPasanganUrut;
    private String nomorUnit;
    private Integer nomorTahun;

    private String nipPenerimaMemorandum;
    private String namaPenerimaMemorandum;
    private String jabatanPenerimaMemorandum;
    private String unitKerjaPenerimaMemorandum;
    private String gelarDepanPenerimaMemorandum;
    private String gelarBelakangPenerimaMemorandum;
    private String pangkatPenerimaMemorandum;
    private String golonganPenerimaMemorandum;

    private String nipPemberiMemorandum;
    private String namaPemberiMemorandum;
    private String jabatanPemberiMemorandum;
    private String unitKerjaPemberiMemorandum;
    private String gelarDepanPemberiMemorandum;
    private String gelarBelakangPemberiMemorandum;
    private String pangkatPemberiMemorandum;
    private String golonganPemberiMemorandum;

    private String hal;
    private Long tanggalPembuatanMilis;
    private String isiMemorandum;

    private String nipPembuatSurat;
    private String namaPembuatSurat;
    private String jabatanPembuatSurat;
    private String unitKerjaPembuatSurat;
    private String gelarDepanPembuatSurat;
    private String gelarBelakangPembuatSurat;
    private String pangkatPembuatSurat;
    private String golonganPembuatSurat;

    private String nipPenandatangan;
    private String namaPenandatangan;
    private String jabatanPenandatangan;
    private String unitKerjaPenandatangan;
    private String gelarDepanPenandatangan;
    private String gelarBelakangPenandatangan;
    private String pangkatPenandatangan;
    private String golonganPenandatangan;
    private List<JabatanWrapper> tembusanMemorandumList;

    public MemorandumWrapper() {
    }

    public MemorandumWrapper(
            String kdMemorandum,
            String nomorUrusan,
            Integer nomorUrut,
            String nomorPasanganUrut,
            String nomorUnit,
            Integer nomorTahun,
            String nipPenerimaMemorandum,
            String namaPenerimaMemorandum,
            String jabatanPenerimaMemorandum,
            String unitKerjaPenerimaMemorandum,
            String gelarDepanPenerimaMemorandum, String gelarBelakangPenerimaMemorandum, String pangkatPenerimaMemorandum, String golonganPenerimaMemorandum, String nipPemberiMemorandum,
            String namaPemberiMemorandum,
            String jabatanPemberiMemorandum,
            String unitKerjaPemberiMemorandum,
            String gelarDepanPemberiMemorandum, String gelarBelakangPemberiMemorandum, String pangkatPemberiMemorandum, String golonganPemberiMemorandum, String hal,
            Long tanggalPembuatanMilis,
            String isiMemorandum,
            String nipPembuatSurat,
            String namaPembuatSurat,
            String jabatanPembuatSurat,
            String unitKerjaPembuatSurat,
            String gelarDepanPembuatSurat, String gelarBelakangPembuatSurat, String pangkatPembuatSurat, String golonganPembuatSurat, String nipPenandatangan,
            String namaPenandatangan,
            String jabatanPenandatangan,
            String unitKerjaPenandatangan, String gelarDepanPenandatangan, String gelarBelakangPenandatangan, String pangkatPenandatangan, String golonganPenandatangan, List<JabatanWrapper> tembusanMemorandumList) {
        this.kdMemorandum = kdMemorandum;
        this.nomorUrusan = nomorUrusan;
        this.nomorUrut = nomorUrut;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.nipPenerimaMemorandum = nipPenerimaMemorandum;
        this.namaPenerimaMemorandum = namaPenerimaMemorandum;
        this.jabatanPenerimaMemorandum = jabatanPenerimaMemorandum;
        this.unitKerjaPenerimaMemorandum = unitKerjaPenerimaMemorandum;
        this.gelarDepanPenerimaMemorandum = gelarDepanPenerimaMemorandum;
        this.gelarBelakangPenerimaMemorandum = gelarBelakangPenerimaMemorandum;
        this.pangkatPenerimaMemorandum = pangkatPenerimaMemorandum;
        this.golonganPenerimaMemorandum = golonganPenerimaMemorandum;
        this.nipPemberiMemorandum = nipPemberiMemorandum;
        this.namaPemberiMemorandum = namaPemberiMemorandum;
        this.jabatanPemberiMemorandum = jabatanPemberiMemorandum;
        this.unitKerjaPemberiMemorandum = unitKerjaPemberiMemorandum;
        this.gelarDepanPemberiMemorandum = gelarDepanPemberiMemorandum;
        this.gelarBelakangPemberiMemorandum = gelarBelakangPemberiMemorandum;
        this.pangkatPemberiMemorandum = pangkatPemberiMemorandum;
        this.golonganPemberiMemorandum = golonganPemberiMemorandum;
        this.hal = hal;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.isiMemorandum = isiMemorandum;
        this.nipPembuatSurat = nipPembuatSurat;
        this.namaPembuatSurat = namaPembuatSurat;
        this.jabatanPembuatSurat = jabatanPembuatSurat;
        this.unitKerjaPembuatSurat = unitKerjaPembuatSurat;
        this.gelarDepanPembuatSurat = gelarDepanPembuatSurat;
        this.gelarBelakangPembuatSurat = gelarBelakangPembuatSurat;
        this.pangkatPembuatSurat = pangkatPembuatSurat;
        this.golonganPembuatSurat = golonganPembuatSurat;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
        this.gelarDepanPenandatangan = gelarDepanPenandatangan;
        this.gelarBelakangPenandatangan = gelarBelakangPenandatangan;
        this.pangkatPenandatangan = pangkatPenandatangan;
        this.golonganPenandatangan = golonganPenandatangan;
        this.tembusanMemorandumList = tembusanMemorandumList;
    }

    public String getKdMemorandum() {
        return kdMemorandum;
    }

    public void setKdMemorandum(String kdMemorandum) {
        this.kdMemorandum = kdMemorandum;
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

    public String getNipPenerimaMemorandum() {
        return nipPenerimaMemorandum;
    }

    public void setNipPenerimaMemorandum(String nipPenerimaMemorandum) {
        this.nipPenerimaMemorandum = nipPenerimaMemorandum;
    }

    public String getNamaPenerimaMemorandum() {
        return namaPenerimaMemorandum;
    }

    public void setNamaPenerimaMemorandum(String namaPenerimaMemorandum) {
        this.namaPenerimaMemorandum = namaPenerimaMemorandum;
    }

    public String getJabatanPenerimaMemorandum() {
        return jabatanPenerimaMemorandum;
    }

    public void setJabatanPenerimaMemorandum(String jabatanPenerimaMemorandum) {
        this.jabatanPenerimaMemorandum = jabatanPenerimaMemorandum;
    }

    public String getUnitKerjaPenerimaMemorandum() {
        return unitKerjaPenerimaMemorandum;
    }

    public void setUnitKerjaPenerimaMemorandum(String unitKerjaPenerimaMemorandum) {
        this.unitKerjaPenerimaMemorandum = unitKerjaPenerimaMemorandum;
    }

    public String getNipPemberiMemorandum() {
        return nipPemberiMemorandum;
    }

    public void setNipPemberiMemorandum(String nipPemberiMemorandum) {
        this.nipPemberiMemorandum = nipPemberiMemorandum;
    }

    public String getNamaPemberiMemorandum() {
        return namaPemberiMemorandum;
    }

    public void setNamaPemberiMemorandum(String namaPemberiMemorandum) {
        this.namaPemberiMemorandum = namaPemberiMemorandum;
    }

    public String getJabatanPemberiMemorandum() {
        return jabatanPemberiMemorandum;
    }

    public void setJabatanPemberiMemorandum(String jabatanPemberiMemorandum) {
        this.jabatanPemberiMemorandum = jabatanPemberiMemorandum;
    }

    public String getUnitKerjaPemberiMemorandum() {
        return unitKerjaPemberiMemorandum;
    }

    public void setUnitKerjaPemberiMemorandum(String unitKerjaPemberiMemorandum) {
        this.unitKerjaPemberiMemorandum = unitKerjaPemberiMemorandum;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public String getIsiMemorandum() {
        return isiMemorandum;
    }

    public void setIsiMemorandum(String isiMemorandum) {
        this.isiMemorandum = isiMemorandum;
    }

    public String getNipPembuatSurat() {
        return nipPembuatSurat;
    }

    public void setNipPembuatSurat(String nipPembuatSurat) {
        this.nipPembuatSurat = nipPembuatSurat;
    }

    public String getNamaPembuatSurat() {
        return namaPembuatSurat;
    }

    public void setNamaPembuatSurat(String namaPembuatSurat) {
        this.namaPembuatSurat = namaPembuatSurat;
    }

    public String getJabatanPembuatSurat() {
        return jabatanPembuatSurat;
    }

    public void setJabatanPembuatSurat(String jabatanPembuatSurat) {
        this.jabatanPembuatSurat = jabatanPembuatSurat;
    }

    public String getUnitKerjaPembuatSurat() {
        return unitKerjaPembuatSurat;
    }

    public void setUnitKerjaPembuatSurat(String unitKerjaPembuatSurat) {
        this.unitKerjaPembuatSurat = unitKerjaPembuatSurat;
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

    public String getJabatanPenandatangan() {
        return jabatanPenandatangan;
    }

    public void setJabatanPenandatangan(String jabatanPenandatangan) {
        this.jabatanPenandatangan = jabatanPenandatangan;
    }

    public String getUnitKerjaPenandatangan() {
        return unitKerjaPenandatangan;
    }

    public void setUnitKerjaPenandatangan(String unitKerjaPenandatangan) {
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
    }

    public List<JabatanWrapper> getTembusanMemorandumList() {
        return tembusanMemorandumList;
    }

    public void setTembusanMemorandumList(List<JabatanWrapper> tembusanMemorandumList) {
        this.tembusanMemorandumList = tembusanMemorandumList;
    }

    public String getGelarDepanPenerimaMemorandum() {
        return gelarDepanPenerimaMemorandum;
    }

    public void setGelarDepanPenerimaMemorandum(String gelarDepanPenerimaMemorandum) {
        this.gelarDepanPenerimaMemorandum = gelarDepanPenerimaMemorandum;
    }

    public String getGelarBelakangPenerimaMemorandum() {
        return gelarBelakangPenerimaMemorandum;
    }

    public void setGelarBelakangPenerimaMemorandum(String gelarBelakangPenerimaMemorandum) {
        this.gelarBelakangPenerimaMemorandum = gelarBelakangPenerimaMemorandum;
    }

    public String getPangkatPenerimaMemorandum() {
        return pangkatPenerimaMemorandum;
    }

    public void setPangkatPenerimaMemorandum(String pangkatPenerimaMemorandum) {
        this.pangkatPenerimaMemorandum = pangkatPenerimaMemorandum;
    }

    public String getGolonganPenerimaMemorandum() {
        return golonganPenerimaMemorandum;
    }

    public void setGolonganPenerimaMemorandum(String golonganPenerimaMemorandum) {
        this.golonganPenerimaMemorandum = golonganPenerimaMemorandum;
    }

    public String getGelarDepanPemberiMemorandum() {
        return gelarDepanPemberiMemorandum;
    }

    public void setGelarDepanPemberiMemorandum(String gelarDepanPemberiMemorandum) {
        this.gelarDepanPemberiMemorandum = gelarDepanPemberiMemorandum;
    }

    public String getGelarBelakangPemberiMemorandum() {
        return gelarBelakangPemberiMemorandum;
    }

    public void setGelarBelakangPemberiMemorandum(String gelarBelakangPemberiMemorandum) {
        this.gelarBelakangPemberiMemorandum = gelarBelakangPemberiMemorandum;
    }

    public String getPangkatPemberiMemorandum() {
        return pangkatPemberiMemorandum;
    }

    public void setPangkatPemberiMemorandum(String pangkatPemberiMemorandum) {
        this.pangkatPemberiMemorandum = pangkatPemberiMemorandum;
    }

    public String getGolonganPemberiMemorandum() {
        return golonganPemberiMemorandum;
    }

    public void setGolonganPemberiMemorandum(String golonganPemberiMemorandum) {
        this.golonganPemberiMemorandum = golonganPemberiMemorandum;
    }

    public String getGelarDepanPembuatSurat() {
        return gelarDepanPembuatSurat;
    }

    public void setGelarDepanPembuatSurat(String gelarDepanPembuatSurat) {
        this.gelarDepanPembuatSurat = gelarDepanPembuatSurat;
    }

    public String getGelarBelakangPembuatSurat() {
        return gelarBelakangPembuatSurat;
    }

    public void setGelarBelakangPembuatSurat(String gelarBelakangPembuatSurat) {
        this.gelarBelakangPembuatSurat = gelarBelakangPembuatSurat;
    }

    public String getPangkatPembuatSurat() {
        return pangkatPembuatSurat;
    }

    public void setPangkatPembuatSurat(String pangkatPembuatSurat) {
        this.pangkatPembuatSurat = pangkatPembuatSurat;
    }

    public String getGolonganPembuatSurat() {
        return golonganPembuatSurat;
    }

    public void setGolonganPembuatSurat(String golonganPembuatSurat) {
        this.golonganPembuatSurat = golonganPembuatSurat;
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
}
