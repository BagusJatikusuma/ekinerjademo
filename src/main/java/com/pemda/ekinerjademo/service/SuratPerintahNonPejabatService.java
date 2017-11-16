package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPerintahNonPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratPerintahNonPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratPerintahNonPejabat;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 14/11/17.
 */
public interface SuratPerintahNonPejabatService {
    Set<SuratPerintahNonPejabat> getByKdUnitKerja(String kdUnitKerja);
    Set<SuratPerintahNonPejabat> getByNomorTahun(String nomorTahun);
    Set<SuratPerintahNonPejabat> getByNipPembuat(String nipPembuat);
    List<TargetSuratPerintahNonPejabat> getSuratPerintahTarget(String nipTargetSurat);
    Integer getLatestNomorSuratByUnitKerja(String kdUnitKerja);
    void creteSurat(SuratPerintahNonPejabat suratPerintahNonPejabat);
    void createTargetSurat(Set<TargetSuratPerintahNonPejabat> targetSuratPerintahNonPejabatSet);
    void createTembusanSurat(Set<TembusanSuratPerintahNonPejabat> tembusanSuratPerintahNonPejabatSet);
    SuratPerintahNonPejabat getSuratPerintahNonPejabatByKdSuratPerintah(String kdSuratperintah);
}
