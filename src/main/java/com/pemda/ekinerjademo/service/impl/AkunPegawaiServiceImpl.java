package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.repository.ekinerjarepository.AkunPegawaiDao;
import com.pemda.ekinerjademo.service.AkunPegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional("ekinerjaTransactionManager")
    public void setPegawaiRole(String role, String nipPegawai) {
        akunPegawaiDao.updatePegawaiRole(role, nipPegawai);
    }

    @Override
    public void save(AkunPegawai akunPegawai) {
        akunPegawaiDao.save(akunPegawai);
    }
}
