package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratTugas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratTugasPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratTugasPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratTugas;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 29/01/18.
 */
public interface SuratTugasService {
    Set<SuratTugas> getByKdUnitKerja(String kdUnitKerja);
    Set<SuratTugas> getByNomorTahun(String nomorTahun);
    Set<SuratTugas> getByNipPembuat(String nipPembuat);

    List<TargetSuratTugasPegawai> getTargetSuratTugasPegawai(String nipTargetSurat);
    List<TargetSuratTugasPejabat> getTargetSuratTugasPejabat(String kdJabatanTarget);
    List<TembusanSuratTugas> getTembusanSuratTugas(String kdJabatanTembusan);


}
