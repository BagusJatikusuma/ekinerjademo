package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.repository.ekinerjarepository.AkunPegawaiDao;
import com.pemda.ekinerjademo.service.AkunPegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bagus on 08/09/17.
 */
@Service("AkunPegawaiService")
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
}
