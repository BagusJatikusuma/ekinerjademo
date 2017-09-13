package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
public interface QutPegawaiService {
    QutPegawai getQutPegawai(String nip);
    List<QutPegawai> getQutPegawai();
}
