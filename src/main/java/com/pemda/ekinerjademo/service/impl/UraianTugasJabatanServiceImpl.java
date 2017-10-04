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
                                urtugWrapper.getKdJabatan(),
                                urtugWrapper.getKdJenisUrtug()));

        urtugJabatan.setSatuan(urtugWrapper.getSatuan());
        urtugJabatan.setNormaWaktu(urtugWrapper.getNormaWaktu());
        urtugJabatan.setVolumeKerja(urtugWrapper.getVolumeKerja());
        urtugJabatan.setBebanKerja(urtugWrapper.getBebanKerja());
        urtugJabatan.setPeralatan(urtugWrapper.getPeralatan());
        urtugJabatan.setKeterangan(urtugWrapper.getKeterangan());
        urtugJabatan.setCreatedBy(new AkunPegawai(urtugWrapper.getCreatedBy()));
    }

    @Override
    public void delete(String kdUrtug, String kdJabatan, String kdJenisUrtug) {
        uraianTugasJabatanDao
                .deleteByUraianTugasJabatanId(new UraianTugasJabatanId(kdUrtug, kdJabatan, kdJenisUrtug));
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
                        urtugJabatanWrapper.getKdJabatan(),
                        urtugJabatanWrapper.getKdJenisUrtug()));
        urtugJabatan.setSatuan(urtugJabatanWrapper.getSatuan());
        urtugJabatan.setNormaWaktu(urtugJabatanWrapper.getNormaWaktu());
        urtugJabatan.setVolumeKerja(urtugJabatanWrapper.getVolumeKerja());
        urtugJabatan.setBebanKerja(urtugJabatanWrapper.getBebanKerja());
        urtugJabatan.setPeralatan(urtugJabatanWrapper.getPeralatan());
        urtugJabatan.setKeterangan(urtugJabatanWrapper.getKeterangan());
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
                .findByUraianTugasJabatanId(new UraianTugasJabatanId(kdUrtug, kdJabatan, kdJenisUrtug));
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
