package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.JabatanUrtugDTO;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugas;
import com.pemda.ekinerjademo.wrapper.input.UpdateUraianTugasInputWrapper;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
public interface UraianTugasService {
    List<UraianTugas> getAllUraianTugas();
    void save(UraianTugas uraianTugas);
    UraianTugas getUraianTugas(String kdUrtug);
    void update(UpdateUraianTugasInputWrapper urtugWrapper);
    void delete(String kdUrtug);
    List<String> getJabatanUrtug();
    List<UraianTugas> getAllUraianTugas(String jabatan);
}
