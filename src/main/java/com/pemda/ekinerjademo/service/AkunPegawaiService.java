package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.PasswordAkunPegawaiProjection;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;

import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
public interface AkunPegawaiService {
    List<AkunPegawai> getAkunPegawaiList();
    AkunPegawai getAkunPegawai(String nipPegawai);
    void setPegawaiRole(String role, String nipPegawai);
    void save(AkunPegawai akunPegawai);
//    PasswordAkunPegawaiProjection getPasswordAkunPegawai(String nipPegawai);
}
