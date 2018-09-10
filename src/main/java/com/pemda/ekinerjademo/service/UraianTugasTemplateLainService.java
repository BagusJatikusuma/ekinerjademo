package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasTemplateLain;

import java.util.List;

public interface UraianTugasTemplateLainService {
    void create(UraianTugasTemplateLain uraianTugasTemplateLain);
    List<UraianTugasTemplateLain> getAll();
    List<UraianTugasTemplateLain> get(String kdUrtug,
                                      String kdJabatan,
                                      Integer tahunUrtug,
                                      String kdJenisUrtug,
                                      Integer bulanUrtug,
                                      String nipPegawai);
}
