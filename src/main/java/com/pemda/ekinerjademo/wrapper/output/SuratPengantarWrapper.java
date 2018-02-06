package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

import java.util.List;

/**
 * Created by bagus on 22/01/18.
 */
public class SuratPengantarWrapper {
    private String nomorUrusan;
    private Integer nomorUrut;
    private String nomorPasanganUrut;
    private String nomorUnit;
    private Integer nomorTahun;

    private Long tanggalPembuatanMilis;
    private Long tanggalDiterimaSuratPengantar;

    private String kdJabatanPenerimaSuratPengantar;
    private String jabatanPenerimaSuratPengantar;

    private CustomPegawaiCredential penerimaSuratPengantar;

    private String nipPemberiSuratPengantar;
    private String namaPemberiSuratPengantar;
    private String jabatanPemberiSuratPengantar;
    private String unitKerjaPemberiSuratPengantar;
    private String gelarDepanPemberiSuratPengantar;
    private String gelarBelakangPemberiSuratPengantar;
    private String pangkatPemberiSuratPengantar;
    private String golonganPemberiSuratPengantar;

    private String nomorTeleponPemberi;
    private List<SuratPengantarIsiWrapper> suratPengantarIsiWrapperList;

    public SuratPengantarWrapper() {
    }

    public SuratPengantarWrapper(
            String nomorUrusan,
            Integer nomorUrut,
            String nomorPasanganUrut,
            String nomorUnit,
            Integer nomorTahun,
            Long tanggalPembuatanMilis,
            Long tanggalDiterimaSuratPengantar,
            String kdJabatanPenerimaSuratPengantar,
            String jabatanPenerimaSuratPengantar,
            CustomPegawaiCredential penerimaSuratPengantar, String nipPemberiSuratPengantar,
            String namaPemberiSuratPengantar,
            String jabatanPemberiSuratPengantar,
            String unitKerjaPemberiSuratPengantar,
            String gelarDepanPemberiSuratPengantar, String gelarBelakangPemberiSuratPengantar, String pangkatPemberiSuratPengantar, String golonganPemberiSuratPengantar, String nomorTeleponPemberi, List<SuratPengantarIsiWrapper> suratPengantarIsiWrapperList) {
        this.nomorUrusan = nomorUrusan;
        this.nomorUrut = nomorUrut;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
        this.tanggalDiterimaSuratPengantar = tanggalDiterimaSuratPengantar;
        this.kdJabatanPenerimaSuratPengantar = kdJabatanPenerimaSuratPengantar;
        this.jabatanPenerimaSuratPengantar = jabatanPenerimaSuratPengantar;
        this.penerimaSuratPengantar = penerimaSuratPengantar;
        this.nipPemberiSuratPengantar = nipPemberiSuratPengantar;
        this.namaPemberiSuratPengantar = namaPemberiSuratPengantar;
        this.jabatanPemberiSuratPengantar = jabatanPemberiSuratPengantar;
        this.unitKerjaPemberiSuratPengantar = unitKerjaPemberiSuratPengantar;
        this.gelarDepanPemberiSuratPengantar = gelarDepanPemberiSuratPengantar;
        this.gelarBelakangPemberiSuratPengantar = gelarBelakangPemberiSuratPengantar;
        this.pangkatPemberiSuratPengantar = pangkatPemberiSuratPengantar;
        this.golonganPemberiSuratPengantar = golonganPemberiSuratPengantar;
        this.nomorTeleponPemberi = nomorTeleponPemberi;
        this.suratPengantarIsiWrapperList = suratPengantarIsiWrapperList;
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

    public Long getTanggalPembuatanMilis() {
        return tanggalPembuatanMilis;
    }

    public void setTanggalPembuatanMilis(Long tanggalPembuatanMilis) {
        this.tanggalPembuatanMilis = tanggalPembuatanMilis;
    }

    public Long getTanggalDiterimaSuratPengantar() {
        return tanggalDiterimaSuratPengantar;
    }

    public void setTanggalDiterimaSuratPengantar(Long tanggalDiterimaSuratPengantar) {
        this.tanggalDiterimaSuratPengantar = tanggalDiterimaSuratPengantar;
    }

    public String getKdJabatanPenerimaSuratPengantar() {
        return kdJabatanPenerimaSuratPengantar;
    }

    public void setKdJabatanPenerimaSuratPengantar(String kdJabatanPenerimaSuratPengantar) {
        this.kdJabatanPenerimaSuratPengantar = kdJabatanPenerimaSuratPengantar;
    }

    public String getJabatanPenerimaSuratPengantar() {
        return jabatanPenerimaSuratPengantar;
    }

    public void setJabatanPenerimaSuratPengantar(String jabatanPenerimaSuratPengantar) {
        this.jabatanPenerimaSuratPengantar = jabatanPenerimaSuratPengantar;
    }

    public String getNipPemberiSuratPengantar() {
        return nipPemberiSuratPengantar;
    }

    public void setNipPemberiSuratPengantar(String nipPemberiSuratPengantar) {
        this.nipPemberiSuratPengantar = nipPemberiSuratPengantar;
    }

    public String getNamaPemberiSuratPengantar() {
        return namaPemberiSuratPengantar;
    }

    public void setNamaPemberiSuratPengantar(String namaPemberiSuratPengantar) {
        this.namaPemberiSuratPengantar = namaPemberiSuratPengantar;
    }

    public String getJabatanPemberiSuratPengantar() {
        return jabatanPemberiSuratPengantar;
    }

    public void setJabatanPemberiSuratPengantar(String jabatanPemberiSuratPengantar) {
        this.jabatanPemberiSuratPengantar = jabatanPemberiSuratPengantar;
    }

    public String getUnitKerjaPemberiSuratPengantar() {
        return unitKerjaPemberiSuratPengantar;
    }

    public void setUnitKerjaPemberiSuratPengantar(String unitKerjaPemberiSuratPengantar) {
        this.unitKerjaPemberiSuratPengantar = unitKerjaPemberiSuratPengantar;
    }

    public String getNomorTeleponPemberi() {
        return nomorTeleponPemberi;
    }

    public void setNomorTeleponPemberi(String nomorTeleponPemberi) {
        this.nomorTeleponPemberi = nomorTeleponPemberi;
    }

    public List<SuratPengantarIsiWrapper> getSuratPengantarIsiWrapperList() {
        return suratPengantarIsiWrapperList;
    }

    public void setSuratPengantarIsiWrapperList(List<SuratPengantarIsiWrapper> suratPengantarIsiWrapperList) {
        this.suratPengantarIsiWrapperList = suratPengantarIsiWrapperList;
    }

    public CustomPegawaiCredential getPenerimaSuratPengantar() {
        return penerimaSuratPengantar;
    }

    public void setPenerimaSuratPengantar(CustomPegawaiCredential penerimaSuratPengantar) {
        this.penerimaSuratPengantar = penerimaSuratPengantar;
    }

    public String getGelarDepanPemberiSuratPengantar() {
        return gelarDepanPemberiSuratPengantar;
    }

    public void setGelarDepanPemberiSuratPengantar(String gelarDepanPemberiSuratPengantar) {
        this.gelarDepanPemberiSuratPengantar = gelarDepanPemberiSuratPengantar;
    }

    public String getGelarBelakangPemberiSuratPengantar() {
        return gelarBelakangPemberiSuratPengantar;
    }

    public void setGelarBelakangPemberiSuratPengantar(String gelarBelakangPemberiSuratPengantar) {
        this.gelarBelakangPemberiSuratPengantar = gelarBelakangPemberiSuratPengantar;
    }

    public String getPangkatPemberiSuratPengantar() {
        return pangkatPemberiSuratPengantar;
    }

    public void setPangkatPemberiSuratPengantar(String pangkatPemberiSuratPengantar) {
        this.pangkatPemberiSuratPengantar = pangkatPemberiSuratPengantar;
    }

    public String getGolonganPemberiSuratPengantar() {
        return golonganPemberiSuratPengantar;
    }

    public void setGolonganPemberiSuratPengantar(String golonganPemberiSuratPengantar) {
        this.golonganPemberiSuratPengantar = golonganPemberiSuratPengantar;
    }
}
