package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.LembarDisposisi;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetLembarDisposisi;
import com.pemda.ekinerjademo.repository.ekinerjarepository.LembarDisposisiDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TargetLembarDisposisiDao;
import com.pemda.ekinerjademo.service.LembarDisposisiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 19/11/17.
 */
@Service("LembarDisposisiService")
@Transactional("ekinerjaTransactionManager")
public class LembarDisposisiServiceImpl implements LembarDisposisiService {
    @Autowired private LembarDisposisiDao lembarDisposisiDao;
    @Autowired private TargetLembarDisposisiDao targetLembarDisposisiDao;

    @Override
    public void create(LembarDisposisi lembarDisposisi) {
        lembarDisposisiDao.save(lembarDisposisi);
    }

    @Override
    public void createTargetLembarDisposisi(List<TargetLembarDisposisi> targetLembarDisposisiList) {
        for (TargetLembarDisposisi targetLembarDisposisi
                : targetLembarDisposisiList) {
            targetLembarDisposisiDao.save(targetLembarDisposisi);
        }

    }

    @Override
    public LembarDisposisi findByKdLembarDisposisi(String kdLembarDisposisi) {
        return lembarDisposisiDao.findOne(kdLembarDisposisi);
    }

    @Override
    public List<LembarDisposisi> findByUnitKerja(String kdUnitKerja) {
        return null;
    }

    @Override
    public List<LembarDisposisi> findByNipPegawai(String nipPegawai) {
        return null;
    }

    @Override
    public List<LembarDisposisi> findTree(String kdLembarDisposisi) {
        LembarDisposisi lembarDisposisi = lembarDisposisiDao.findOne(kdLembarDisposisi);

        return lembarDisposisiDao.findLembarDisposisiTree(lembarDisposisi.getPath());
    }


}
