package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.QutPegawaiClone;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

import java.util.List;

/**
 * Created by bagus on 10/12/17.
 */
public interface QutPegawaiCloneService {
    QutPegawai getQutPegawai(String nip);
    List<QutPegawai> getQutPegawai();
    List<CustomPegawaiCredential> getCustomPegawaiCredentials();
    List<QutPegawai> getQutPegawaiByUnitKerja(String kdUnitKerja);
    List<QutPegawaiClone> getQutPegawaiByKdJabatan(String kdJabatan);
    List<QutPegawaiClone> convertQutPegawaiIntoQutPegawaiClone(List<QutPegawai> qutPegawaiList);
    QutPegawaiClone convertQutPegawaiIntoQutPegawaiClone(QutPegawai qutPegawai);
    void deleteAll();
    void saveQutPegawaiList(List<QutPegawaiClone> qutPegawaiList);
}
