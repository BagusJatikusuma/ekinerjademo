package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.JenisUrtugUrtug;

import java.util.List;

/**
 * Created by bagus on 26/09/17.
 */
public interface JenisUrtugUrtugService {
    void save(JenisUrtugUrtug jenisUrtugUrtug);
    List<JenisUrtugUrtug> getJenisUrtugUrtug();
    List<JenisUrtugUrtug> getJenisUrtugUrtugByJabatan(String kdJabatan);
}
