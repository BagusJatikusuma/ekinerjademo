package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.LembarDisposisi;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetLembarDisposisi;

import java.util.List;

/**
 * Created by bagus on 19/11/17.
 */
public interface LembarDisposisiService {
    // buat root lembar disposisi, disposisi pertama
    void create(LembarDisposisi lembarDisposisi);
    void createTargetLembarDisposisi(List<TargetLembarDisposisi> targetLembarDisposisiList);
    void openLembarDisposisi(String kdLembarDisposisi);

    LembarDisposisi findByKdLembarDisposisi(String kdLembarDisposisi);
    List<LembarDisposisi> findByUnitKerja(String kdUnitKerja);
    List<LembarDisposisi> findByNipPegawai(String nipPegawai);
    List<LembarDisposisi> findTree(String kdLembarDisposisi);
    List<TargetLembarDisposisi> findByTargetDisposisi(String nipTarget);
    LembarDisposisi getDokumenLembarDisposisi(String kdLembarDisposisi);
}
