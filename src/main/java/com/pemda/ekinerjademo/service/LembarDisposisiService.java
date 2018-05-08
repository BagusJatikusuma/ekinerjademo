package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.LembarDisposisi;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetLembarDisposisi;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetLembarDisposisiId;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratPerintahPegawaiId;

import java.util.List;

/**
 * Created by bagus on 19/11/17.
 */
public interface LembarDisposisiService {
    // buat root lembar disposisi, disposisi pertama
    void create(LembarDisposisi lembarDisposisi);
    void createTargetLembarDisposisi(List<TargetLembarDisposisi> targetLembarDisposisiList);
    void createTargetLembarDisposisi(TargetLembarDisposisi targetLembarDisposisi);
    void openLembarDisposisi(String kdLembarDisposisi);
    void openLembarDisposisiTarget(TargetLembarDisposisiId targetLembarDisposisiId);

    LembarDisposisi findByKdLembarDisposisi(String kdLembarDisposisi);
    List<LembarDisposisi> findByUnitKerja(String kdUnitKerja);
    List<LembarDisposisi> findByNipPegawai(String nipPegawai);
    List<LembarDisposisi> findTree(String kdLembarDisposisi);

    List<TargetLembarDisposisi> findByTargetDisposisi(String nipTarget);
    List<TargetLembarDisposisi> findByTargetDisposisiRev(String nipTarget);

    LembarDisposisi getDokumenLembarDisposisi(String kdLembarDisposisi);

    List<LembarDisposisi> getDraftlembarDisposisiApproval(String kdUnitKerja);

    List<LembarDisposisi> findTreeByLeave(String kdLembarDisposisiLeave);

    List<LembarDisposisi> getDraftLembarDisposisiByLevel(String kdUnitKerja, Integer draftLevel);
}
