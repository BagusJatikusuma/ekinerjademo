package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.LembarDisposisiDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TargetLembarDisposisiDao;
import com.pemda.ekinerjademo.service.LembarDisposisiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 19/11/17.
 */
@Service("LembarDisposisiService")
@Transactional("ekinerjaTransactionManager")
public class LembarDisposisiServiceImpl implements LembarDisposisiService {
    public static final Logger LOGGER = LoggerFactory.getLogger(LembarDisposisiServiceImpl.class);

    @Autowired private LembarDisposisiDao lembarDisposisiDao;
    @Autowired private TargetLembarDisposisiDao targetLembarDisposisiDao;

    @Override
    public void create(LembarDisposisi lembarDisposisi) {
        LOGGER.info("create lembar disposisi");

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
    public void createTargetLembarDisposisi(TargetLembarDisposisi targetLembarDisposisi) {
        targetLembarDisposisiDao.save(targetLembarDisposisi);
    }

    @Override
    public void openLembarDisposisi(String kdLembarDisposisi) {
        LembarDisposisi lembarDisposisi = lembarDisposisiDao.findOne(kdLembarDisposisi);

        lembarDisposisi.setStatusBaca(1);
    }

    @Override
    public void openLembarDisposisiTarget(TargetLembarDisposisiId targetLembarDisposisiId) {
        TargetLembarDisposisi targetLembarDisposisi
                = targetLembarDisposisiDao.findOne(targetLembarDisposisiId);
        if (targetLembarDisposisi != null)
            targetLembarDisposisi.setStatusBaca(1);
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
        return lembarDisposisiDao.findByNipPembuat(nipPegawai);
    }

    @Override
    public List<LembarDisposisi> findTree(String kdLembarDisposisi) {
        LembarDisposisi lembarDisposisi = lembarDisposisiDao.findOne(kdLembarDisposisi);

        return lembarDisposisiDao.findLembarDisposisiTree(lembarDisposisi.getPath());
    }

    @Override
    public List<TargetLembarDisposisi> findByTargetDisposisi(String nipTarget) {
        return targetLembarDisposisiDao.findByTargetLembarDisposisiId_NipPegawai(nipTarget);
    }

    @Override
    public List<TargetLembarDisposisi> findByTargetDisposisiRev(String nipTarget) {
        return targetLembarDisposisiDao.findByTargetDisposisi(nipTarget);
    }

    @Override
    public LembarDisposisi getDokumenLembarDisposisi(String kdLembarDisposisi) {
        return lembarDisposisiDao.findDokumenLembarDisposisi(kdLembarDisposisi);
    }

    @Override
    public List<LembarDisposisi> getDraftlembarDisposisiApproval(String kdUnitKerja) {
        return lembarDisposisiDao.findDraftLembarDisposisiApproval(kdUnitKerja);
    }

    @Override
    public List<LembarDisposisi> findTreeByLeave(String kdLembarDisposisiLeave) {
        LembarDisposisi lembarDisposisiLeave
                = lembarDisposisiDao.findOne(kdLembarDisposisiLeave);
        List<LembarDisposisi> lembarDisposisiTree
                = new ArrayList<>();

        lembarDisposisiTree.add(lembarDisposisiLeave);

        String kdPathCurr = lembarDisposisiLeave.getPath();
        while (kdPathCurr.contains(".")) {
            kdPathCurr
                    = kdPathCurr.substring(0,kdPathCurr.lastIndexOf("."));

            lembarDisposisiTree.add(lembarDisposisiDao.findByPath(kdPathCurr).get(0));
        }

        return lembarDisposisiTree;
    }

    @Override
    public List<LembarDisposisi> getDraftLembarDisposisiByLevel(String kdUnitKerja, Integer draftLevel) {
        LOGGER.info("log in service impl kdUnitKerja : "+kdUnitKerja+"; draftLevel : "+draftLevel);
        return lembarDisposisiDao.findDraftByLevel(kdUnitKerja, draftLevel);
    }


}
