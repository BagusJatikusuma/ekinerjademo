package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UraianTugasJabatanDao;
import com.pemda.ekinerjademo.service.UraianTugasJabatanService;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasJabatanInputWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 10/09/17.
 */
@Service("UraianTugasJabatanService")
@Transactional("ekinerjaTransactionManager")
public class UraianTugasJabatanServiceImpl implements UraianTugasJabatanService {
    @Autowired
    private UraianTugasJabatanDao uraianTugasJabatanDao;
    @Autowired
    private UraianTugasJabatanTransactionService urtugJabatanTransactionalService;

    @Override
    public List<UraianTugasJabatan> getUraianTugasJabatan() {
        return uraianTugasJabatanDao.findAll();
    }

    @Override
    public void update(UraianTugasJabatanInputWrapper urtugWrapper) {
        UraianTugasJabatan urtugJabatan =
                uraianTugasJabatanDao
                        .findByUraianTugasJabatanId(new UraianTugasJabatanId(
                                urtugWrapper.getKdUrtug(),
                                urtugWrapper.getKdJabatan()));

        urtugJabatan.setKuantitas(urtugWrapper.getKuantitas());
        urtugJabatan.setSatuanKuantitas(urtugWrapper.getSatuanKuantitas());
        urtugJabatan.setKualitas(urtugWrapper.getKualitas());
        urtugJabatan.setWaktu(urtugWrapper.getWaktu());
        urtugJabatan.setBiaya(urtugWrapper.getBiaya());
        urtugJabatan.setCreatedBy(new AkunPegawai(urtugWrapper.getCreatedBy()));
    }

    @Override
    public void delete(String kdUrtug, String kdJabatan) {
        uraianTugasJabatanDao
                .deleteByUraianTugasJabatanId(new UraianTugasJabatanId(kdUrtug, kdJabatan));
    }

    @Override
    public List<UraianTugasJabatan> getUraianTugasJabatanByJabatan(String kdJabatan) {
        return uraianTugasJabatanDao.findByUraianTugasJabatanIdKdJabatan(kdJabatan);
    }

    @Override
    public void createUrtugJabatan(UraianTugasJabatanInputWrapper urtugJabatanWrapper) {
        UraianTugasJabatan urtugJabatan = new UraianTugasJabatan();

        urtugJabatan.setUraianTugasJabatanId(
                new UraianTugasJabatanId(
                        urtugJabatanWrapper.getKdUrtug(),
                        urtugJabatanWrapper.getKdJabatan()));
        urtugJabatan.setKuantitas(urtugJabatanWrapper.getKuantitas());
        urtugJabatan.setSatuanKuantitas(urtugJabatanWrapper.getSatuanKuantitas());
        urtugJabatan.setKualitas(urtugJabatanWrapper.getKualitas());
        urtugJabatan.setWaktu(urtugJabatanWrapper.getWaktu());
        urtugJabatan.setBiaya(urtugJabatanWrapper.getBiaya());
        urtugJabatan.setCreatedBy(new AkunPegawai(urtugJabatanWrapper.getCreatedBy()));

        urtugJabatanTransactionalService.save(urtugJabatan);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(UraianTugasJabatan uraianTugasJabatan) {
        uraianTugasJabatanDao.save(uraianTugasJabatan);
    }

    @Override
    public UraianTugasJabatan getUraianTugasJabatan(String kdUrtug, String kdJabatan, String kdJenisUrtug) {
        return uraianTugasJabatanDao
                .findByUraianTugasJabatanId(new UraianTugasJabatanId(kdUrtug, kdJabatan));
    }

//    @Override
//    public void save(String kdUrtug, String kdJabatan) {
//        uraianTugasJabatanDao.saveNativeQuery(kdUrtug, kdJabatan);
//    }
//
//    @Override
//    public void deleteAllUraianTugasJabatanByJabatan(String kdJabatan) {
//        uraianTugasJabatanDao
//                .deleteAllByUraianTugasJabatanIdKdJabatan(kdJabatan);
//    }
//
//    @Override
//    public List<UraianTugasJabatanController> getUraianTugasJabatanByUrtug(String kdUrtug) {
//        return null;
//    }
}
