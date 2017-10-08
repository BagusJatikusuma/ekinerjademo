package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.SopUraianTugasJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.SopUraianTugasJabatanId;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanId;

import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
public interface SopUraianTugasJabatanService {
    List<SopUraianTugasJabatan> findByUraianTugasJabatan(UraianTugasJabatanId uraianTugasJabatanId);
    void save(SopUraianTugasJabatan sopUraianTugasJabatan);
    void delete(SopUraianTugasJabatanId sopUraianTugasJabatanId);
}
