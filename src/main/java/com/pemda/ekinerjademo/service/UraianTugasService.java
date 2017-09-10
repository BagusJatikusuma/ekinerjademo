package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugas;

/**
 * Created by bagus on 09/09/17.
 */
public interface UraianTugasService {
    UraianTugas getUraianTugas(String kdUrtug);
    void save(UraianTugas uraianTugas);
    void update(UraianTugas uraianTugas);
    void delete(String kdUrtug);
}
