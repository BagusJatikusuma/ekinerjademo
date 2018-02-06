package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKeterangan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratKeterangan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratKeteranganId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratKeteranganDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TargetSuratKeteranganDao;
import com.pemda.ekinerjademo.service.SuratKeteranganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@Service("SuratKeteranganService")
@Transactional("ekinerjaTransactionManager")
public class SuratKeteranganServiceImpl implements SuratKeteranganService {
    @Autowired private SuratKeteranganDao suratKeteranganDao;
    @Autowired private TargetSuratKeteranganDao targetSuratKeteranganDao;

    @Override
    public List<SuratKeterangan> getByKdUnitKerja(String kdUnitKerja) {
        return suratKeteranganDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<SuratKeterangan> getByNipPembuat(String nipPembuat) {
        return suratKeteranganDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<SuratKeterangan> getByNomorTahun(Integer nomorTahun) {
        return suratKeteranganDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public List<TargetSuratKeterangan> getTargetSuratKeteranganByNip(String nipTarget) {
        return targetSuratKeteranganDao.findByTargetSuratKeteranganId_NipPegawai(nipTarget);
    }

    @Override
    public SuratKeterangan getByKdSuratKeterangan(String kdSuratKeterangan) {
        return suratKeteranganDao.findByKdSuratKeterangan(kdSuratKeterangan);
    }

    @Override
    public void create(SuratKeterangan suratKeterangan) {
        suratKeteranganDao.save(suratKeterangan);
    }

    @Override
    public void createTargetSuratKeterangan(TargetSuratKeterangan targetSuratKeterangan) {
        targetSuratKeteranganDao.save(targetSuratKeterangan);
    }

    @Override
    public void openSuratKeterangan(String kdSuratKeterangan) {
        SuratKeterangan suratKeterangan
                = suratKeteranganDao.findByKdSuratKeterangan(kdSuratKeterangan);
        suratKeterangan.setStatusBaca(1);
    }

    @Override
    public void openTargetSuratKeterangan(TargetSuratKeteranganId targetSuratKeteranganId) {
        TargetSuratKeterangan targetSuratKeterangan
                = targetSuratKeteranganDao.findOne(targetSuratKeteranganId);
        targetSuratKeterangan.setStatusBaca(1);
    }

    @Override
    public void openSuratKeteranganPenilai(String kdSuratKeterangan) {
        SuratKeterangan suratKeterangan
                = suratKeteranganDao.findByKdSuratKeterangan(kdSuratKeterangan);
        suratKeterangan.setStatusPenilaian(1);
    }

}
