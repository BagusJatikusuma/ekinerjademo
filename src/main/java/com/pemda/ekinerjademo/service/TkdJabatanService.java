package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TkdJabatanClone;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
public interface TkdJabatanService {
    TkdJabatan getTkdJabatan(String kdJabatan);
    List<TkdJabatan> getAll();
    List<TkdJabatanClone> getAllClone();
    List<TkdJabatan> getJabatanByUnitKerja(String kdUnitKerja);
    List<TkdJabatan> getJabatanByEselonAndUnitKerja(String eselon, String kdUnitKerja);
    TkdJabatan getCamatUnitKerja(String kdUnitKerja);

    void createClone(TkdJabatanClone tkdJabatanClone);
}
