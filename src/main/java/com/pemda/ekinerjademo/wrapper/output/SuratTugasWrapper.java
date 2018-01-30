package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

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

    private Set<String> dasar;
    private Set<String> untuk;
    private Set<String> tempat;

    private Long tanggalTugasMilis;
    private String kdUnitKerja;

    private Set<CustomPegawaiCredential> targetSuratTugasPegawaiSet;

    private Set<JabatanWrapper> targetSuratTugasPejabatSet;

    private Set<JabatanWrapper> tembusanSuratTugasSet;

    public SuratTugasWrapper() {
    }

    public SuratTugasWrapper(
            String kdSuratTugas,
            CustomPegawaiCredential nipPenandatangan,
            Integer nomorUrut,
            String nomorUrusan,
            String nomorUnit,
            Integer nomorTahun,
            String nomorPasanganUrut,
            Set<String> dasar,
            Set<String> untuk,
            Set<String> tempat,
            Long tanggalTugasMilis,
            String kdUnitKerja,
            Set<CustomPegawaiCredential> targetSuratTugasPegawaiSet,
            Set<JabatanWrapper> targetSuratTugasPejabatSet,
            Set<JabatanWrapper> tembusanSuratTugasSet) {
        this.kdSuratTugas = kdSuratTugas;
        this.nipPenandatangan = nipPenandatangan;
        this.nomorUrut = nomorUrut;
        this.nomorUrusan = nomorUrusan;
        this.nomorUnit = nomorUnit;
        this.nomorTahun = nomorTahun;
        this.nomorPasanganUrut = nomorPasanganUrut;
        this.dasar = dasar;
        this.untuk = untuk;
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

    public Set<String> getDasar() {
        return dasar;
    }

    public void setDasar(Set<String> dasar) {
        this.dasar = dasar;
    }

    public Set<String> getUntuk() {
        return untuk;
    }

    public void setUntuk(Set<String> untuk) {
        this.untuk = untuk;
    }

    public Set<String> getTempat() {
        return tempat;
    }

    public void setTempat(Set<String> tempat) {
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
}
