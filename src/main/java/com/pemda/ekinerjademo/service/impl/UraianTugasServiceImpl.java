package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.JabatanUrtugDTO;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugas;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UraianTugasDao;
import com.pemda.ekinerjademo.service.UraianTugasService;
import com.pemda.ekinerjademo.wrapper.input.UpdateUraianTugasInputWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 10/09/17.
 */
@Service("UraianTugasService")
@Transactional("ekinerjaTransactionManager")
public class UraianTugasServiceImpl implements UraianTugasService {
    @Autowired private UraianTugasDao uraianTugasDao;
//
//    @Override
    public UraianTugas getUraianTugas(String kdUrtug) {
        return uraianTugasDao.findByKdUrtug(kdUrtug);
    }

    @Override
    public void update(UpdateUraianTugasInputWrapper urtugWrapper) {
        UraianTugas uraianTugas = getUraianTugas(urtugWrapper.getKdUrtug());
        uraianTugas.setDeskripsi(urtugWrapper.getDeskripsi());
    }

    @Override
    public void delete(String kdUrtug) {
        uraianTugasDao.deleteByKdUrtug(kdUrtug);
    }

    @Override
    public List<String> getJabatanUrtug() {
        return uraianTugasDao.findAllByDistinctJabatan();
    }

    @Override
    public List<String> getUnitKerjaUrtug(String jabatan) {
        return uraianTugasDao.findAllByDistinctUnitKerja(jabatan);
    }

    @Override
    public List<UraianTugas> getAllUraianTugas(String jabatan) {
        return uraianTugasDao.findAllByJabatan(jabatan);
    }

    @Override
    public List<UraianTugas> getAllUraianTugas(String jabatan, String unitKerja) {
        return uraianTugasDao.findAllByJabatan(jabatan, unitKerja);
    }

    @Override
    public List<UraianTugas> getAllUraianTugas(){
        return uraianTugasDao.findAll();
    }

    @Override
    public void save(UraianTugas uraianTugas) {
        uraianTugasDao.save(uraianTugas);
    }

}
