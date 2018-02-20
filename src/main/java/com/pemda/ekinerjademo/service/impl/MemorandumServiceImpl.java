package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.MemorandumDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.MemorandumNonPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.MemorandumPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TembusanMemorandumDao;
import com.pemda.ekinerjademo.service.MemorandumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 05/01/18.
 */
@Service("MemorandumService")
@Transactional("ekinerjaTransactionManager")
public class MemorandumServiceImpl implements MemorandumService {
    @Autowired private MemorandumDao memorandumDao;
    @Autowired private MemorandumNonPejabatDao memorandumNonPejabatDao;
    @Autowired private MemorandumPejabatDao memorandumPejabatDao;
    @Autowired private TembusanMemorandumDao tembusanMemorandumDao;

    @Override
    public List<Memorandum> getByNipPembuat(String nipPembuat) {
        return memorandumDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<Memorandum> getByNipTarget(String nipTarget) {
        return memorandumDao.findByNipPenerimaMemorandum(nipTarget);
    }

    @Override
    public List<TembusanMemorandum> getTembusanMemorandum(String kdJabatanTembusan) {
        return tembusanMemorandumDao.findByTembusanMemorandumId_KdJabatan(kdJabatanTembusan);
    }

    @Override
    public void createMemorandum(Memorandum memorandum) {
        memorandumDao.save(memorandum);
    }

    @Override
    public void createMemorandumNonPejabat(MemorandumNonPejabat memorandumNonPejabat) {
        memorandumNonPejabatDao.save(memorandumNonPejabat);
    }

    @Override
    public void createMemorandumPejabat(MemorandumPejabat memorandumPejabat) {
        memorandumPejabatDao.save(memorandumPejabat);
    }

    @Override
    public void createTembusanMemorandum(List<TembusanMemorandum> tembusanMemorandumList) {
        for (TembusanMemorandum tembusanMemorandum
                : tembusanMemorandumList) {
            tembusanMemorandumDao.save(tembusanMemorandum);
        }
    }

    @Override
    public void openMemorandum(String kdMemorandum) {
        Memorandum memorandum
                = memorandumDao.findByKdMemorandum(kdMemorandum);
        memorandum.setStatusBaca(1);
    }

    @Override
    public void openTembusanMemorandum(TembusanMemorandumId id) {
        TembusanMemorandum tembusanMemorandum
                = tembusanMemorandumDao.findOne(id);
        tembusanMemorandum.setStatusBaca(1);
    }

    @Override
    public void openMemorandumByPenilai(String kdMemorandum) {
        Memorandum memorandum
                = memorandumDao.findByKdMemorandum(kdMemorandum);
        memorandum.setStatusPenilaian(1);
    }

    @Override
    public void update(Memorandum memorandum) {
        memorandumDao.save(memorandum);
    }

    @Override
    public Memorandum getByKdMemorandum(String kdMemorandum) {
        return memorandumDao.findByKdMemorandum(kdMemorandum);
    }

    @Override
    public void approveMemorandum(String kdMemorandum) {
        List<Memorandum> memorandumList
                = memorandumDao.findByLastTree(kdMemorandum);
        for (Memorandum memorandum
                : memorandumList) {
            memorandum.setApprovalPenandatangan(1);
        }
    }

}
