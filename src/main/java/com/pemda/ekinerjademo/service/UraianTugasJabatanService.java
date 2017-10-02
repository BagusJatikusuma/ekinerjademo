package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatan;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
public interface UraianTugasJabatanService {
//    void save(UraianTugasJabatanController uraianTugasJabatan);
//    void save(String kdUrtug, String kdJabatan);
//    void deleteAllUraianTugasJabatanByJabatan(String kdJabatan);
//    UraianTugasJabatanController getUraianTugasJabatan(String kdUrtug, String kdJabatan);
    List<UraianTugasJabatan> getUraianTugasJabatanByJabatan(String kdJabatan);
//    List<UraianTugasJabatanController> getUraianTugasJabatanByUrtug(String kdUrtug);
    List<UraianTugasJabatan> getUraianTugasJabatan();

}
