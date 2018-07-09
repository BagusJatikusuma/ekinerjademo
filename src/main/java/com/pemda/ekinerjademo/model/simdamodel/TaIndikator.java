package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.*;

/**
 * Created by bayu on 04/12/17.
 */

@Entity
@Table(name = "Ta_Indikator")
public class TaIndikator {
    @EmbeddedId
    private TaIndikatorId taIndikatorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(
                    name = "Tahun",
                    referencedColumnName = "Tahun",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Urusan",
                    referencedColumnName = "Kd_Urusan",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Bidang",
                    referencedColumnName = "Kd_Bidang",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Unit",
                    referencedColumnName = "Kd_Unit",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Sub",
                    referencedColumnName = "Kd_Sub",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Prog",
                    referencedColumnName = "Kd_Prog",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "ID_Prog",
                    referencedColumnName = "ID_Prog",
                    insertable = false,
                    updatable = false),
            @JoinColumn(
                    name = "Kd_Keg",
                    referencedColumnName = "Kd_Keg",
                    insertable = false,
                    updatable = false)
    })
    private TaKegiatan taKegiatan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "Kd_Indikator",
            referencedColumnName = "Kd_Indikator",
            insertable = false,
            updatable = false)
    private RefIndikator refIndikator;

    @Column(name = "Tolak_Ukur")
    private String tolakUkur;
    @Column(name = "Target_Angka")
    private Double targetAngka;
    @Column(name = "Target_Uraian")
    private String targetUraian;

    public TaIndikatorId getTaIndikatorId() {
        return taIndikatorId;
    }

    public void setTaIndikatorId(TaIndikatorId taIndikatorId) {
        this.taIndikatorId = taIndikatorId;
    }

    public TaKegiatan getTaKegiatan() {
        return taKegiatan;
    }

    public void setTaKegiatan(TaKegiatan taKegiatan) {
        this.taKegiatan = taKegiatan;
    }

    public RefIndikator getRefIndikator() {
        return refIndikator;
    }

    public void setRefIndikator(RefIndikator refIndikator) {
        this.refIndikator = refIndikator;
    }

    public String getTolakUkur() {
        return tolakUkur;
    }

    public void setTolakUkur(String tolakUkur) {
        this.tolakUkur = tolakUkur;
    }

    public Double getTargetAngka() {
        return targetAngka;
    }

    public void setTargetAngka(Double targetAngka) {
        this.targetAngka = targetAngka;
    }

    public String getTargetUraian() {
        return targetUraian;
    }

    public void setTargetUraian(String targetUraian) {
        this.targetUraian = targetUraian;
    }
}
