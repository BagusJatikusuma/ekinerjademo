package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratDinasDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratDinasNonPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratDinasPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TembusanSuratDinasDao;
import com.pemda.ekinerjademo.service.SuratDinasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@Service("SuratDinasService")
@Transactional("ekinerjaTransactionManager")
public class SuratDinasServiceImpl implements SuratDinasService {
    @Autowired private SuratDinasDao suratDinasDao;
    @Autowired private SuratDinasPejabatDao suratDinasPejabatDao;
    @Autowired private SuratDinasNonPejabatDao suratDinasNonPejabatDao;
    @Autowired private TembusanSuratDinasDao tembusanSuratDinasDao;

    @Override
    public List<SuratDinas> getByKdUnitKerja(String kdUnitKerja) {
        return suratDinasDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<SuratDinas> getByNipPembuat(String nipPembuat) {
        return suratDinasDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<SuratDinas> getByNomorTahun(Integer nomorTahun) {
        return suratDinasDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public List<SuratDinas> getByJabatanPenerima(String kdJabatan) {
        return suratDinasDao.findByKdJabatanPenerimaSuratDinas(kdJabatan);
    }

    @Override
    public List<TembusanSuratDinas> getTembusanSuratDinas(String kdJabatan) {
        return tembusanSuratDinasDao.findByTembusanSuratDinasId_KdJabatan(kdJabatan);
    }

    @Override
    public SuratDinas getByKdSuratDinas(String kdSuratDinas) {
        return suratDinasDao.findByKdSuratDinas(kdSuratDinas);
    }

    @Override
    public void create(SuratDinas suratDinas) {
        suratDinasDao.save(suratDinas);
    }

    @Override
    public void createSuratDinasPejabat(SuratDinasPejabat suratDinasPejabat) {
        suratDinasPejabatDao.save(suratDinasPejabat);
    }

    @Override
    public void createSuratDinasNonPejabat(SuratDinasNonPejabat suratDinasNonPejabat) {
        suratDinasNonPejabatDao.save(suratDinasNonPejabat);
    }

    @Override
    public void createTembusanSuratDinas(TembusanSuratDinas tembusanSuratDinas) {
        tembusanSuratDinasDao.save(tembusanSuratDinas);
    }

    @Override
    public void openSuratDinas(String kdSuratDinas) {
        SuratDinas suratDinas = suratDinasDao.findByKdSuratDinas(kdSuratDinas);

    }

    @Override
    public void openTembusanSuratDinas(TembusanSuratDinasId tembusanSuratDinasId) {
        TembusanSuratDinas tembusanSuratDinas
                = tembusanSuratDinasDao.findOne(tembusanSuratDinasId);
        tembusanSuratDinas.setStatusDiterima(1);
    }

    @Override
    public void openSuratDinasPenilai(String kdSuratDinas) {
        SuratDinas suratDinas
                = suratDinasDao.findByKdSuratDinas(kdSuratDinas);
        suratDinas.setStatusPenilaian(1);
    }

    @Override
    public void approveSuratDinas(String kdSuratDinas) {
        List<SuratDinas> suratDinasList
                = suratDinasDao.findByLastTree(kdSuratDinas);
        for (SuratDinas suratDinas
                : suratDinasList) {
            suratDinas.setApprovalPenandatangan(1);
        }
    }
}
