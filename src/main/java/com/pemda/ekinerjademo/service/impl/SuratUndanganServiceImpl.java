package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratUndanganDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratUndanganNonPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratUndanganPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TembusanSuratUndanganDao;
import com.pemda.ekinerjademo.service.SuratUndanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@Service("SuratUndanganService")
@Transactional("ekinerjaTransactionManager")
public class SuratUndanganServiceImpl implements SuratUndanganService {
    @Autowired private SuratUndanganDao suratUndanganDao;
    @Autowired private SuratUndanganNonPejabatDao suratUndanganNonPejabatDao;
    @Autowired private SuratUndanganPejabatDao suratUndanganPejabatDao;
    @Autowired private TembusanSuratUndanganDao tembusanSuratUndanganDao;

    @Override
    public List<SuratUndangan> getByKdUnitKerja(String kdUnitKerja) {
        return suratUndanganDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<SuratUndangan> getByNipPembuat(String nipPembuat) {
        return suratUndanganDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<SuratUndangan> getByNomorTahun(Integer nomorTahun) {
        return suratUndanganDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public List<SuratUndangan> getByNipPenerima(String nipPenerima) {
        return null;
    }

    @Override
    public List<TembusanSuratUndangan> getTembusanSuratUndangan(String kdJabatan) {
        return null;
    }

    @Override
    public SuratUndangan getByKdSuratUndangan(String kdSuratUndangan) {
        return suratUndanganDao.findByKdSuratUndangan(kdSuratUndangan);
    }

    @Override
    public void create(SuratUndangan suratUndangan) {
        suratUndanganDao.save(suratUndangan);
    }

    @Override
    public void createSuratUndanganNonPejabat(SuratUndanganNonPejabat suratUndanganNonPejabat) {
        suratUndanganNonPejabatDao.save(suratUndanganNonPejabat);
    }

    @Override
    public void createSuratUndanganPejabat(SuratUndanganPejabat suratUndanganPejabat) {
        suratUndanganPejabatDao.save(suratUndanganPejabat);
    }

    @Override
    public void tembusanSuratUndangan(TembusanSuratUndangan tembusanSuratUndangan) {
        tembusanSuratUndanganDao.save(tembusanSuratUndangan);
    }

    @Override
    public void openSuratUndangan(String kdSuratUndangan) {
        SuratUndangan suratUndangan
                = suratUndanganDao.findByKdSuratUndangan(kdSuratUndangan);
        suratUndangan.setStatusBaca(1);
    }

    @Override
    public void openTembusanSuratUndangan(TembusanSuratUndanganId tembusanSuratUndanganId) {
        TembusanSuratUndangan tembusanSuratUndangan
                = tembusanSuratUndanganDao.findOne(tembusanSuratUndanganId);
        tembusanSuratUndangan.setStatusBaca(1);
    }

    @Override
    public void openSuratUndanganPenilai(String kdSuratUndangan) {
        SuratUndangan suratUndangan
                = suratUndanganDao.findByKdSuratUndangan(kdSuratUndangan);
        suratUndangan.setStatusPenilaian(1);
    }
}
