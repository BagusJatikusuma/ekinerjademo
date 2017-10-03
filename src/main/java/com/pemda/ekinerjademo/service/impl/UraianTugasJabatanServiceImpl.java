package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UraianTugasJabatanDao;
import com.pemda.ekinerjademo.service.JenisUrtugUrtugService;
import com.pemda.ekinerjademo.service.UraianTugasJabatanService;
import com.pemda.ekinerjademo.wrapper.input.JenisUrtugUrtugInputWrapper;
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
    private JenisUrtugUrtugService jenisUrtugUrtugService;

    @Override
    public List<UraianTugasJabatan> getUraianTugasJabatan() {
        return uraianTugasJabatanDao.findAll();
    }

    @Override
    public void update(UraianTugasJabatanInputWrapper urtugWrapper) {
        UraianTugasJabatan urtugJabatan =
                getUraianTugasJabatan(urtugWrapper.getKdUrtug(), urtugWrapper.getKdJabatan());
        urtugJabatan.setCreatedBy(new AkunPegawai(urtugWrapper.getCreatedBy()));
    }

    @Override
    public void delete(String kdUrtug, String kdJabatan) {
        uraianTugasJabatanDao.deleteByUraianTugasJabatanId(new UraianTugasJabatanId(kdUrtug, kdJabatan));
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
        urtugJabatan.setCreatedBy(new AkunPegawai(urtugJabatanWrapper.getCreatedBy()));

        save(urtugJabatan);

        List<JenisUrtugUrtugInputWrapper> jenisUrtugWrapperList =
                urtugJabatanWrapper.getJenisUrtugList();

        for (JenisUrtugUrtugInputWrapper jenisUrtugWrapper : jenisUrtugWrapperList) {
            JenisUrtugUrtug jenisUrtugUrtug = new JenisUrtugUrtug();

            jenisUrtugUrtug.setJenisUrtugUrtugId(
                        new JenisUrtugUrtugId(
                                jenisUrtugWrapper.getKdJenisUrtug(),
                                urtugJabatanWrapper.getKdUrtug(),
                                urtugJabatanWrapper.getKdJabatan()));
            jenisUrtugUrtug.setSatuan(jenisUrtugWrapper.getSatuan());
            jenisUrtugUrtug.setNormaWaktu(jenisUrtugWrapper.getNormaWaktu());
            jenisUrtugUrtug.setVolumeKerja(jenisUrtugWrapper.getVolumeKerja());
            jenisUrtugUrtug.setBebanKerja(jenisUrtugWrapper.getBebanKerja());
            jenisUrtugUrtug.setPeralatan(jenisUrtugWrapper.getPeralatan());
            jenisUrtugUrtug.setKeterangan(jenisUrtugWrapper.getKeterangan());

            jenisUrtugUrtugService.save(jenisUrtugUrtug);
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(UraianTugasJabatan uraianTugasJabatan) {
        uraianTugasJabatanDao.save(uraianTugasJabatan);
    }

    @Override
    public UraianTugasJabatan getUraianTugasJabatan(String kdUrtug, String kdJabatan) {
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
