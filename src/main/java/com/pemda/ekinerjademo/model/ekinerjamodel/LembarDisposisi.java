package com.pemda.ekinerjademo.model.ekinerjamodel;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 18/11/17.
 */
@Entity
@Table(name = "lembar_disposisi")
public class LembarDisposisi {
    @Id
    @Column(name = "kd_lembar_disposisi")
    private String kdLembarDisposisi;
    @Column(name = "path")
    private String path;
    @Column(name = "nip_pembuat")
    private String nipPembuat;
    @Column(name = "kd_unit_kerja")
    private String kdUnitKerja;
    @Column(name = "tanggal_penerimaan_surat")
    private Long tanggalPenerimaanMilis;
    @Column(name = "tkt_keamanan")
    private Integer tktKeamanan;
    @Column(name = "tgl_penyelesaian_milis")
    private Long tglPenyelesaianMilis;
    @Column(name = "isi_disposisi")
    private String isiDisposisi;
    @Column(name = "status_penyebaran")
    private Integer statusPenyebaran;
    @Column(name = "approval_penandatangan")
    private Integer approvalPenandatangan;
    @Column(name = "status_baca")
    private Integer statusBaca;
    @Column(name = "tanggal_pengiriman_surat")
    private Long tanggalPengirimanMilis;
    @Column(name = "durasi_pengerjaan")
    private Integer durasiPengerjaan;
    @Column(name = "kd_barcode")
    private String kdBarcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "kd_lembar_dispoisisi_parent",
            referencedColumnName = "kd_lembar_disposisi")
    private LembarDisposisi kdLembarDisposisiParent;
    @OneToMany(mappedBy = "kdLembarDisposisiParent")
    private Set<LembarDisposisi> kdLembarDisposisiChild;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "no_surat_disposisi",
            referencedColumnName = "no_surat")
    private SuratDisposisi noSuratDisposisi;
    @OneToMany(mappedBy = "lembarDisposisi", fetch = FetchType.LAZY)
    private Set<TargetLembarDisposisi> targetLembarDisposisiSet;

    public LembarDisposisi() {
    }
    public LembarDisposisi(String kdLembarDisposisi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
    }

    public String getKdLembarDisposisi() {
        return kdLembarDisposisi;
    }

    public void setKdLembarDisposisi(String kdLembarDisposisi) {
        this.kdLembarDisposisi = kdLembarDisposisi;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNipPembuat() {
        return nipPembuat;
    }

    public void setNipPembuat(String nipPembuat) {
        this.nipPembuat = nipPembuat;
    }

    public String getKdUnitKerja() {
        return kdUnitKerja;
    }

    public void setKdUnitKerja(String kdUnitKerja) {
        this.kdUnitKerja = kdUnitKerja;
    }

    public Long getTanggalPenerimaanMilis() {
        return tanggalPenerimaanMilis;
    }

    public void setTanggalPenerimaanMilis(Long tanggalPenerimaanMilis) {
        this.tanggalPenerimaanMilis = tanggalPenerimaanMilis;
    }

    public Integer getTktKeamanan() {
        return tktKeamanan;
    }

    public void setTktKeamanan(Integer tktKeamanan) {
        this.tktKeamanan = tktKeamanan;
    }

    public Long getTglPenyelesaianMilis() {
        return tglPenyelesaianMilis;
    }

    public void setTglPenyelesaianMilis(Long tglPenyelesaianMilis) {
        this.tglPenyelesaianMilis = tglPenyelesaianMilis;
    }

    public String getIsiDisposisi() {
        return isiDisposisi;
    }

    public void setIsiDisposisi(String isiDisposisi) {
        this.isiDisposisi = isiDisposisi;
    }

    public Integer getStatusPenyebaran() {
        return statusPenyebaran;
    }

    public void setStatusPenyebaran(Integer statusPenyebaran) {
        this.statusPenyebaran = statusPenyebaran;
    }

    public Integer getApprovalPenandatangan() {
        return approvalPenandatangan;
    }

    public void setApprovalPenandatangan(Integer approvalPenandatangan) {
        this.approvalPenandatangan = approvalPenandatangan;
    }

    public Integer getStatusBaca() {
        return statusBaca;
    }

    public void setStatusBaca(Integer statusBaca) {
        this.statusBaca = statusBaca;
    }

    public Long getTanggalPengirimanMilis() {
        return tanggalPengirimanMilis;
    }

    public void setTanggalPengirimanMilis(Long tanggalPengirimanMilis) {
        this.tanggalPengirimanMilis = tanggalPengirimanMilis;
    }

    public Integer getDurasiPengerjaan() {
        return durasiPengerjaan;
    }

    public void setDurasiPengerjaan(Integer durasiPengerjaan) {
        this.durasiPengerjaan = durasiPengerjaan;
    }

    public String getKdBarcode() {
        return kdBarcode;
    }

    public void setKdBarcode(String kdBarcode) {
        this.kdBarcode = kdBarcode;
    }

    public LembarDisposisi getKdLembarDisposisiParent() {
        return kdLembarDisposisiParent;
    }

    public void setKdLembarDisposisiParent(LembarDisposisi kdLembarDisposisiParent) {
        this.kdLembarDisposisiParent = kdLembarDisposisiParent;
    }

    public Set<LembarDisposisi> getKdLembarDisposisiChild() {
        return kdLembarDisposisiChild;
    }

    public void setKdLembarDisposisiChild(Set<LembarDisposisi> kdLembarDisposisiChild) {
        this.kdLembarDisposisiChild = kdLembarDisposisiChild;
    }

    public SuratDisposisi getNoSuratDisposisi() {
        return noSuratDisposisi;
    }

    public void setNoSuratDisposisi(SuratDisposisi noSuratDisposisi) {
        this.noSuratDisposisi = noSuratDisposisi;
    }

    public Set<TargetLembarDisposisi> getTargetLembarDisposisiSet() {
        return targetLembarDisposisiSet;
    }

    public void setTargetLembarDisposisiSet(Set<TargetLembarDisposisi> targetLembarDisposisiSet) {
        this.targetLembarDisposisiSet = targetLembarDisposisiSet;
    }
}
