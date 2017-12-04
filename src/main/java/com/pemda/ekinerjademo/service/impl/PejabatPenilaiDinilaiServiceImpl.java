package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilai;
import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilaiId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.PejabatPenilaiDinilaiDao;
import com.pemda.ekinerjademo.service.PejabatPenilaiDinilaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 25/10/17.
 */
@Service("PejabatPenilaiDinilaiService")
@Transactional("ekinerjaTransactionManager")
public class PejabatPenilaiDinilaiServiceImpl implements PejabatPenilaiDinilaiService {
    @Autowired private PejabatPenilaiDinilaiDao pejabatPenilaiDinilaiDao;

    @Override
    public void create(PejabatPenilaiDinilai pejabatPenilaiDinilai) {
        pejabatPenilaiDinilaiDao.save(pejabatPenilaiDinilai);
    }

    @Override
    public void updatePejabatPenilai(PejabatPenilaiDinilai pejabatPenilaiDinilai) {
    }

    @Override
    public void updatePejabatPenilaiByKdJabatanDinilai(String nipPenilai, String kdJabatanDinilai) {
        PejabatPenilaiDinilai pejabatPenilaiDinilai
                = pejabatPenilaiDinilaiDao.findByPejabatPenilaiDinilaiId(new PejabatPenilaiDinilaiId(nipPenilai, kdJabatanDinilai));

        pejabatPenilaiDinilai
                .setPejabatPenilaiDinilaiId(
                        new PejabatPenilaiDinilaiId(
                                nipPenilai,
                                kdJabatanDinilai));
    }

    @Override
    public List<PejabatPenilaiDinilai> findByKdJabatanDinilai(String kdJabatanDinilai) {
        return pejabatPenilaiDinilaiDao.findByPejabatPenilaiDinilaiId_KdJabatanDinilai(kdJabatanDinilai);
    }

    @Override
    public PejabatPenilaiDinilai find(PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId) {
        return pejabatPenilaiDinilaiDao.findByPejabatPenilaiDinilaiId(pejabatPenilaiDinilaiId);
    }

    @Override
    public List<PejabatPenilaiDinilai> findPegawaiDinilai(String nipPenilai) {
        return pejabatPenilaiDinilaiDao
                .findByPejabatPenilaiDinilaiId_NipPenilai(nipPenilai);
    }

    @Override
    public void delete(PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId) {
        pejabatPenilaiDinilaiDao
                .deleteByPejabatPenilaiDinilaiId(pejabatPenilaiDinilaiId);
    }
}
