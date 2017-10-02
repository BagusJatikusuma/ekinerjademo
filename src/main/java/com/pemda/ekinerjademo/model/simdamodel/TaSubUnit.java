package com.pemda.ekinerjademo.model.simdamodel;

import javax.persistence.*;

/**
 * Created by bagus on 29/09/17.
 */
@Entity
@Table(name = "Ta_Sub_Unit")
public class TaSubUnit {
    @EmbeddedId
    private TaSubUnitId taSubUnitId;
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
                    updatable = false)
    })
    private RefSubUnit refSubUnit;
    @Column(name = "Nm_Pimpinan")
    private String nmPimpinan;
    @Column(name = "Nip_Pimpinan")
    private String nipPimpinan;
    @Column(name = "Jbt_Pimpinan")
    private String jbtPimpinan;
    @Column(name = "Alamat")
    private String alamat;
    @Column(name = "Ur_Visi")
    private String urVisi;

    public TaSubUnitId getTaSubUnitId() {
        return taSubUnitId;
    }

    public void setTaSubUnitId(TaSubUnitId taSubUnitId) {
        this.taSubUnitId = taSubUnitId;
    }

    public RefSubUnit getRefSubUnit() {
        return refSubUnit;
    }

    public void setRefSubUnit(RefSubUnit refSubUnit) {
        this.refSubUnit = refSubUnit;
    }

    public String getNmPimpinan() {
        return nmPimpinan;
    }

    public void setNmPimpinan(String nmPimpinan) {
        this.nmPimpinan = nmPimpinan;
    }

    public String getNipPimpinan() {
        return nipPimpinan;
    }

    public void setNipPimpinan(String nipPimpinan) {
        this.nipPimpinan = nipPimpinan;
    }

    public String getJbtPimpinan() {
        return jbtPimpinan;
    }

    public void setJbtPimpinan(String jbtPimpinan) {
        this.jbtPimpinan = jbtPimpinan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUrVisi() {
        return urVisi;
    }

    public void setUrVisi(String urVisi) {
        this.urVisi = urVisi;
    }
}
