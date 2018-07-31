package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.QutPegawaiClone;

import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
public interface AkunPegawaiService {
    List<AkunPegawai> getAkunPegawaiList();
    AkunPegawai getAkunPegawai(String nipPegawai);
    void setPegawaiRole(String role, String nipPegawai);
    void save(AkunPegawai akunPegawai);
    boolean isPegawaiSekretaris(QutPegawai qutPegawai);
    boolean isPegawaiSekretaris(QutPegawaiClone qutPegawai);
    boolean isPegawaiKasie(QutPegawai qutPegawai);
    boolean isPegawaiKasie(QutPegawaiClone qutPegawai);
    boolean isPegawaiKepalaBidang(QutPegawai qutPegawai);
    boolean isPegawaiKepalaBidang(QutPegawaiClone qutPegawai);
//    PasswordAkunPegawaiProjection getPasswordAkunPegawai(String nipPegawai);
}
