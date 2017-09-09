package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatan;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
public interface UraianTugasJabatanService {
    UraianTugasJabatan getUraianTugasJabatan(String kdUrtug, String kdJabatan);
    List<UraianTugasJabatan> getUraianTugasJabatanByJabatan(String kdJabatan);
    List<UraianTugasJabatan> getUraianTugasJabatanByUrtug(String kdUrtug);

}
