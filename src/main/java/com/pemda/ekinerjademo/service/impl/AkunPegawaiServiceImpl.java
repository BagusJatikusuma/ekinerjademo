package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.QutPegawaiClone;
import com.pemda.ekinerjademo.repository.ekinerjarepository.AkunPegawaiDao;
import com.pemda.ekinerjademo.service.AkunPegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
@Service("AkunPegawaiService")
@Transactional("ekinerjaTransactionManager")
public class AkunPegawaiServiceImpl implements AkunPegawaiService {
    @Autowired private AkunPegawaiDao akunPegawaiDao;

    @Override
    public List<AkunPegawai> getAkunPegawaiList() {
        return akunPegawaiDao.findAll();
    }

    @Override
    public AkunPegawai getAkunPegawai(String nipPegawai) {
        return akunPegawaiDao.findByNipPegawai(nipPegawai);
    }

    @Override
    public void setPegawaiRole(String role, String nipPegawai) {
        akunPegawaiDao.updatePegawaiRole(role, nipPegawai);
    }

    @Override
    public void save(AkunPegawai akunPegawai) {
        akunPegawaiDao.save(akunPegawai);
    }

    @Override
    public boolean isPegawaiSekretaris(QutPegawai qutPegawai) {
        boolean isSekretaris = false;
        if (qutPegawai.getKdUnitKerja().substring(0,1)
                .equals("3")) {
            //eselon kadin II.b, eselon sekdin III.a
            if (qutPegawai.getEselon().contains("III.a")) {
                isSekretaris = true;
            }
        }
        else if (qutPegawai.getKdUnitKerja().substring(0,1)
                .equals("7")) {
            //eselon camat III.a, eselon sekdcam III.b
            if (qutPegawai.getEselon().contains("III.b")) {
                isSekretaris = true;
            }
        }

        return isSekretaris;
    }

    @Override
    public boolean isPegawaiSekretaris(QutPegawaiClone qutPegawai) {
        boolean isSekretaris = false;
        if (qutPegawai.getKdUnitKerja().substring(0,1)
                .equals("3")) {
            //eselon kadin II.b, eselon sekdin III.a
            if (qutPegawai.getEselon().contains("III.a")) {
                isSekretaris = true;
            }
        }
        else if (qutPegawai.getKdUnitKerja().substring(0,1)
                .equals("7")) {
            //eselon camat III.a, eselon sekdcam III.b
            if (qutPegawai.getEselon().contains("III.b")) {
                isSekretaris = true;
            }
        }

        return isSekretaris;
    }

    @Override
    public boolean isPegawaiKepalaBidang(QutPegawai qutPegawai) {
        boolean isKabid = false;
        if (qutPegawai.getKdUnitKerja().substring(0,1)
                .equals("3")) {
            //eselon kadin II.b, eselon sekdin III.a
            if (qutPegawai.getEselon().contains("III.b")) {
                isKabid = true;
            }
        }
        else if (qutPegawai.getKdUnitKerja().substring(0,1)
                .equals("7")) {
            //eselon camat III.a, eselon sekdcam III.b
            if (qutPegawai.getEselon().contains("IV.a")) {
                isKabid = true;
            }
        }

        return isKabid;
    }

    @Override
    public boolean isPegawaiKepalaBidang(QutPegawaiClone qutPegawai) {
        boolean isKabid = false;
        if (qutPegawai.getKdUnitKerja().substring(0,1)
                .equals("3")) {
            //eselon kadin II.b, eselon sekdin III.a
            if (qutPegawai.getEselon().contains("III.b")) {
                isKabid = true;
            }
        }
        else if (qutPegawai.getKdUnitKerja().substring(0,1)
                .equals("7")) {
            //eselon camat III.a, eselon sekdcam III.b
            if (qutPegawai.getEselon().contains("IV.a")) {
                isKabid = true;
            }
        }

        return isKabid;
    }

//    @Override
//    public PasswordAkunPegawaiProjection getPasswordAkunPegawai(String nipPegawai) {
//        return akunPegawaiDao.findPasswordByNipPegawai(nipPegawai);
//    }
}
