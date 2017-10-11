package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanJenisUrtug;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanJenisUrtugId;

import java.util.List;

/**
 * Created by bagus on 10/10/17.
 */
public interface UraianTugasJabatanJenisUrtugService {
    List<UraianTugasJabatanJenisUrtug> get();
    List<UraianTugasJabatanJenisUrtug> getByUrtugJabatanAndTahun(String kdUrtug, String kdJabatan, Integer tahun);
    List<UraianTugasJabatanJenisUrtug> getByUrtugJabatan(String kdUrtug, String kdJabatan);
    List<UraianTugasJabatanJenisUrtug> getByJabatan(String kdJabatan);
    UraianTugasJabatanJenisUrtug get(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId);
    void save(UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug);
    void delete(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId);
}
