package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 30/01/18.
 */
public class SuratTugasWrapper {
    private String kdSuratTugas;

    private CustomPegawaiCredential nipPenandatangan;

    private Integer nomorUrut;
    private String nomorUrusan;
    private String nomorUnit;
    private Integer nomorTahun;
    private String nomorPasanganUrut;

    private List<String> menimbangList;
    private List<String> dasarList;
    private List<String> untukList;
    private String tempat;

    private Long tanggalTugasMilis;
    private String kdUnitKerja;

    private boolean isSuratPejabat;

    private String kdUnitKerjaPenandatangan;

    private String unitKerjaPenandatangan;

    private String kdJabatanPenandatangan;

    private String jabatanPenandatangan;

    private Set<CustomPegawaiCredential> targetSuratTugasPegawaiSet;

    private Set<JabatanWrapper> targetSuratTugasPejabatSet;

    private Set<JabatanWrapper> tembusanSuratTugasSet;

    public SuratTugasWrapper() {
    }

    public SuratTugasWrapper(String kdSuratTugas, CustomPegawaiCredential nipPenandatangan, Integer nomorUrut, String nomorUrusan, String nomorUnit, Integer nomorTahun, String nomorPasanganUrut, List<String> menimbangList, List<String> dasarList, List<String> untukList, String tempat, Long tanggalTugasMilis, String kdUnitKerja, Set<CustomPegawaiCredential> targetSuratTugasPegawaiSet, Set<JabatanWrapper> targetSuratTugasPejabatSet, Set<JabatanWrapper> tembusanSuratTugasSet) {
        this.kdSuratTugas = kdSuratTugas;
        this.nipPenandatangan = nipPenandatangan;
        this.nomorUrut = nomorUrut;
        this.nomorUrusan = nomorUrusan;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.menimbangList = menimbangList;
        this.dasarList = dasarList;
        this.untukList = untukList;
        this.tempat = tempat;
        this.tanggalTugasMilis = tanggalTugasMilis;
        this.kdUnitKerja = kdUnitKerja;
        this.targetSuratTugasPegawaiSet = targetSuratTugasPegawaiSet;
        this.targetSuratTugasPejabatSet = targetSuratTugasPejabatSet;
        this.tembusanSuratTugasSet = tembusanSuratTugasSet;
    }

    public String getKdSuratTugas() {
        return kdSuratTugas;
    }

    public void setKdSuratTugas(String kdSuratTugas) {
        this.kdSuratTugas = kdSuratTugas;
    }

    public CustomPegawaiCredential getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(CustomPegawaiCredential nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public Integer getNomorUrut() {
        return nomorUrut;
    }

    public void setNomorUrut(Integer nomorUrut) {
        this.nomorUrut = nomorUrut;
    }

    public String getNomorUrusan() {
        return nomorUrusan;
    }

    public void setNomorUrusan(String nomorUrusan) {
        this.nomorUrusan = nomorUrusan;
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

    public String getNomorPasanganUrut() {
        return nomorPasanganUrut;
    }

    public void setNomorPasanganUrut(String nomorPasanganUrut) {
        this.nomorPasanganUrut = nomorPasanganUrut;
    }

    public List<String> getMenimbangList() {
        return menimbangList;
    }

    public void setMenimbangList(List<String> menimbangList) {
        this.menimbangList = menimbangList;
    }

    public List<String> getDasarList() {
        return dasarList;
    }

    public void setDasarList(List<String> dasarList) {
        this.dasarList = dasarList;
    }

    public List<String> getUntukList() {
        return untukList;
    }

    public void setUntukList(List<String> untukList) {
        this.untukList = untukList;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Long getTanggalTugasMilis() {
        return tanggalTugasMilis;
    }

    public void setTanggalTugasMilis(Long tanggalTugasMilis) {
        this.tanggalTugasMilis = tanggalTugasMilis;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public Set<CustomPegawaiCredential> getTargetSuratTugasPegawaiSet() {
        return targetSuratTugasPegawaiSet;
    }

    public void setTargetSuratTugasPegawaiSet(Set<CustomPegawaiCredential> targetSuratTugasPegawaiSet) {
        this.targetSuratTugasPegawaiSet = targetSuratTugasPegawaiSet;
    }

    public Set<JabatanWrapper> getTargetSuratTugasPejabatSet() {
        return targetSuratTugasPejabatSet;
    }

    public void setTargetSuratTugasPejabatSet(Set<JabatanWrapper> targetSuratTugasPejabatSet) {
        this.targetSuratTugasPejabatSet = targetSuratTugasPejabatSet;
    }

    public Set<JabatanWrapper> getTembusanSuratTugasSet() {
        return tembusanSuratTugasSet;
    }

    public void setTembusanSuratTugasSet(Set<JabatanWrapper> tembusanSuratTugasSet) {
        this.tembusanSuratTugasSet = tembusanSuratTugasSet;
    }

    public boolean isSuratPejabat() {
        return isSuratPejabat;
    }

    public void setSuratPejabat(boolean suratPejabat) {
        isSuratPejabat = suratPejabat;
    }

    public String getKdUnitKerjaPenandatangan() {
        return kdUnitKerjaPenandatangan;
    }

    public void setKdUnitKerjaPenandatangan(String kdUnitKerjaPenandatangan) {
        this.kdUnitKerjaPenandatangan = kdUnitKerjaPenandatangan;
    }

    public String getUnitKerjaPenandatangan() {
        return unitKerjaPenandatangan;
    }

    public void setUnitKerjaPenandatangan(String unitKerjaPenandatangan) {
        this.unitKerjaPenandatangan = unitKerjaPenandatangan;
    }

    public String getKdJabatanPenandatangan() {
        return kdJabatanPenandatangan;
    }

    public void setKdJabatanPenandatangan(String kdJabatanPenandatangan) {
        this.kdJabatanPenandatangan = kdJabatanPenandatangan;
    }

    public String getJabatanPenandatangan() {
        return jabatanPenandatangan;
    }

    public void setJabatanPenandatangan(String jabatanPenandatangan) {
        this.jabatanPenandatangan = jabatanPenandatangan;
    }
}
