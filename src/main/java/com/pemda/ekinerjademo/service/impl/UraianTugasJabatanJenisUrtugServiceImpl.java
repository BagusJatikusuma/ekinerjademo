package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanJenisUrtug;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanJenisUrtugId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UraianTugasJabatanJenisUrtugDao;
import com.pemda.ekinerjademo.service.UraianTugasJabatanJenisUrtugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 10/10/17.
 */
@Service("UraianTugasJabatanJenisUrtugService")
@Transactional("ekinerjaTransactionManager")
public class UraianTugasJabatanJenisUrtugServiceImpl implements UraianTugasJabatanJenisUrtugService {
    @Autowired
    private UraianTugasJabatanJenisUrtugDao uraianTugasJabatanJenisUrtugDao;

    @Override
    public List<UraianTugasJabatanJenisUrtug> get() {
        return uraianTugasJabatanJenisUrtugDao.findAll();
    }

    @Override
    public List<UraianTugasJabatanJenisUrtug> getUrtugNonDpaByJabatan(String kdJabatan) {
        return uraianTugasJabatanJenisUrtugDao.findUrtugNonDpaByJabatan(kdJabatan, "KJU002");
    }

    @Override
    public List<UraianTugasJabatanJenisUrtug> getUrtugNonDpaByUnitKerja(String kdUnitKerja) {
        return uraianTugasJabatanJenisUrtugDao.findUrtugNonDpaByUnitKerja(kdUnitKerja, "KJU002");
    }

    @Override
    public List<UraianTugasJabatanJenisUrtug> getByUrtugJabatanAndTahun(String kdUrtug, String kdJabatan, Integer tahun) {
        return null;
    }

    @Override
    public List<UraianTugasJabatanJenisUrtug> getByUrtugJabatan(String kdUrtug, String kdJabatan) {
        return uraianTugasJabatanJenisUrtugDao.findByUrtugJabatan(kdUrtug, kdJabatan);
    }

    @Override
    public List<UraianTugasJabatanJenisUrtug> getByJabatan(String kdJabatan) {
        return uraianTugasJabatanJenisUrtugDao.findByJabatan(kdJabatan);
    }

    @Override
    public UraianTugasJabatanJenisUrtug get(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId) {
        return uraianTugasJabatanJenisUrtugDao
                .findByUraianTugasJabatanJenisUrtugId(uraianTugasJabatanJenisUrtugId);
    }

    @Override
    public void save(UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug) {
        uraianTugasJabatanJenisUrtugDao
                .save(uraianTugasJabatanJenisUrtug);
    }

    @Override
    public void delete(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId) {
        uraianTugasJabatanJenisUrtugDao
                .deleteByUraianTugasJabatanJenisUrtugId(uraianTugasJabatanJenisUrtugId);
//        uraianTugasJabatanJenisUrtugDao
//                .deleteData(
//                        uraianTugasJabatanJenisUrtugId.getKdUrtug(),
//                        uraianTugasJabatanJenisUrtugId.getKdJabatan(),
//                        uraianTugasJabatanJenisUrtugId.getKdJenisUrtug(),
//                        uraianTugasJabatanJenisUrtugId.getTahunUrtug());
    }

}
