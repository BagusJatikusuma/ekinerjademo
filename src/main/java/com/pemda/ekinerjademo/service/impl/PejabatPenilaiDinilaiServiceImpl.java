package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilai;
import com.pemda.ekinerjademo.model.ekinerjamodel.PejabatPenilaiDinilaiId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.PejabatPenilaiDinilaiDao;
import com.pemda.ekinerjademo.service.PejabatPenilaiDinilaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                = pejabatPenilaiDinilaiDao.findByPejabatPenilaiDinilaiId_KdJabatanDinilai(kdJabatanDinilai);

        pejabatPenilaiDinilai
                .setPejabatPenilaiDinilaiId(
                        new PejabatPenilaiDinilaiId(
                                nipPenilai,
                                kdJabatanDinilai));
    }

    @Override
    public PejabatPenilaiDinilai findByKdJabatanDinilai(String kdJabatanDinilai) {
        return pejabatPenilaiDinilaiDao
                .findByPejabatPenilaiDinilaiId_KdJabatanDinilai(kdJabatanDinilai);
    }

    @Override
    public void delete(PejabatPenilaiDinilaiId pejabatPenilaiDinilaiId) {
        pejabatPenilaiDinilaiDao
                .deleteByPejabatPenilaiDinilaiId(pejabatPenilaiDinilaiId);
    }
}
