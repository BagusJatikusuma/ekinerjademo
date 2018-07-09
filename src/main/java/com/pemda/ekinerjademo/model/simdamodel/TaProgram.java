package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by bagus on 29/09/17.
 */
@Entity
@Table(name = "Ta_Program")
public class TaProgram {
    @EmbeddedId
    private TaProgramId taProgramId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
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
                    name = "Tahun",
                    referencedColumnName = "Tahun",
                    insertable = false,
                    updatable = false)
    })
    private TaSubUnit taSubUnit;
    @Column(name = "Ket_Program")
    private String ketProgram;
    @Column(name = "Tolak_Ukur")
    private String tolakUkur;
    @Column(name = "Target_Angka")
    private BigDecimal targetAngka;
    @Column(name = "Target_Uraian")
    private String targetUraian;
    @Column(name = "Kd_Urusan1")
    private String kdUrusan1;
    @Column(name = "Kd_Bidang1")
    private String kdBidang1;

    @OneToMany(mappedBy = "taProgram")
    private List<TaKegiatan> taKegiatanList;

    public TaProgramId getTaProgramId() {
        return taProgramId;
    }

    public void setTaProgramId(TaProgramId taProgramId) {
        this.taProgramId = taProgramId;
    }

    public TaSubUnit getTaSubUnit() {
        return taSubUnit;
    }

    public void setTaSubUnit(TaSubUnit taSubUnit) {
        this.taSubUnit = taSubUnit;
    }

    public String getKetProgram() {
        return ketProgram;
    }

    public void setKetProgram(String ketProgram) {
        this.ketProgram = ketProgram;
    }

    public String getTolakUkur() {
        return tolakUkur;
    }

    public void setTolakUkur(String tolakUkur) {
        this.tolakUkur = tolakUkur;
    }

    public BigDecimal getTargetAngka() {
        return targetAngka;
    }

    public void setTargetAngka(BigDecimal targetAngka) {
        this.targetAngka = targetAngka;
    }

    public String getTargetUraian() {
        return targetUraian;
    }

    public void setTargetUraian(String targetUraian) {
        this.targetUraian = targetUraian;
    }

    public String getKdUrusan1() {
        return kdUrusan1;
    }

    public void setKdUrusan1(String kdUrusan1) {
        this.kdUrusan1 = kdUrusan1;
    }

    public String getKdBidang1() {
        return kdBidang1;
    }

    public void setKdBidang1(String kdBidang1) {
        this.kdBidang1 = kdBidang1;
    }

    public List<TaKegiatan> getTaKegiatanList() {
        return taKegiatanList;
    }

    public void setTaKegiatanList(List<TaKegiatan> taKegiatanList) {
        this.taKegiatanList = taKegiatanList;
    }
}
