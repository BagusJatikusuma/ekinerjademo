package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanId;
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

    @Override
    public List<UraianTugasJabatan> getUraianTugasJabatan() {
        return uraianTugasJabatanDao.findAll();
    }

    @Override
    public void update(UraianTugasJabatanInputWrapper urtugWrapper) {
        UraianTugasJabatan urtugJabatan =
                getUraianTugasJabatan(urtugWrapper.getKdUrtug(), urtugWrapper.getKdJabatan());
        urtugJabatan.setSatuan(urtugWrapper.getSatuan());
        urtugJabatan.setVolumeKerja(urtugWrapper.getVolumeKerja());
        urtugJabatan.setNormaWaktu(urtugWrapper.getNormaWaktu());
        urtugJabatan.setBebanKerja(urtugWrapper.getBebanKerja());
        urtugJabatan.setPeralatan(urtugWrapper.getPeralatan());
        urtugJabatan.setKeterangan(urtugWrapper.getKeterangan());

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
