package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatan;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasJabatanInputWrapper;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
public interface UraianTugasJabatanService {
    void createUrtugJabatan(UraianTugasJabatanInputWrapper urtugJabatanWrapper);
    void save(UraianTugasJabatan uraianTugasJabatan);
    UraianTugasJabatan getUraianTugasJabatan(String kdUrtug, String kdJabatan, String kdJenisUrtug);
    List<UraianTugasJabatan> getUraianTugasJabatanByJabatan(String kdJabatan);
    List<UraianTugasJabatan> getUraianTugasJabatan();
    void update(UraianTugasJabatanInputWrapper urtugWrapper);
    void delete(String kdUrtug, String kdJabatan);
}

//    void save(String kdUrtug, String kdJabatan);
//    void deleteAllUraianTugasJabatanByJabatan(String kdJabatan);
//    List<UraianTugasJabatanController> getUraianTugasJabatanByUrtug(String kdUrtug);