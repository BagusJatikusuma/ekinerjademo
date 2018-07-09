package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
public interface QutPegawaiService {
    QutPegawai getQutPegawai(String nip);
    List<QutPegawai> getQutPegawai();
    List<CustomPegawaiCredential> getCustomPegawaiCredentials();
    List<QutPegawai> getQutPegawaiByUnitKerja(String kdUnitKerja);
}
