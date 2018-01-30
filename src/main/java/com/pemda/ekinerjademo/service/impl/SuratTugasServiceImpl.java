package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratTugas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratTugasPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratTugasPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratTugas;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratTugasDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TargetSuratTugasPegawaiDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TargetSuratTugasPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TembusanSuratTugasDao;
import com.pemda.ekinerjademo.service.SuratTugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 30/01/18.
 */
@Service("SuratTugasService")
@Transactional("ekinerjaTransactionManager")
public class SuratTugasServiceImpl implements SuratTugasService {
    @Autowired private SuratTugasDao suratTugasDao;
    @Autowired private TargetSuratTugasPejabatDao targetSuratTugasPejabatDao;
    @Autowired private TargetSuratTugasPegawaiDao targetSuratTugasPegawaiDao;
    @Autowired private TembusanSuratTugasDao tembusanSuratTugasDao;

    @Override
    public SuratTugas getByKdSuratTugas(String kdSuratTugas) {
        return suratTugasDao.findByKdSuratTugas(kdSuratTugas);
    }

    @Override
    public Set<SuratTugas> getByKdUnitKerja(String kdUnitKerja) {
        return suratTugasDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public Set<SuratTugas> getByNomorTahun(Integer nomorTahun) {
        return suratTugasDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public Set<SuratTugas> getByNipPembuat(String nipPembuat) {
        return suratTugasDao.findByNipPembuat(nipPembuat);
    }

    @Override
    public List<TargetSuratTugasPegawai> getTargetSuratTugasPegawai(String nipTargetSurat) {
        return targetSuratTugasPegawaiDao.findByTargetSuratTugasPegawaiId_NipPegawai(nipTargetSurat);
    }

    @Override
    public List<TargetSuratTugasPejabat> getTargetSuratTugasPejabat(String kdJabatanTarget) {
        return targetSuratTugasPejabatDao.findByTargetSuratTugasPejabatId_KdJabatan(kdJabatanTarget);
    }

    @Override
    public List<TembusanSuratTugas> getTembusanSuratTugas(String kdJabatanTembusan) {
        return tembusanSuratTugasDao.findByTembusanSuratTugasId_KdJabatan(kdJabatanTembusan);
    }

    @Override
    public void create(SuratTugas suratTugas) {
        suratTugasDao.save(suratTugas);
    }

    @Override
    public void createTargetSuratTugasPegawai(TargetSuratTugasPegawai targetSuratTugasPegawai) {
        targetSuratTugasPegawaiDao.save(targetSuratTugasPegawai);
    }

    @Override
    public void createTargetSuratTugasPejabat(TargetSuratTugasPejabat targetSuratTugasPejabat) {
        targetSuratTugasPejabatDao.save(targetSuratTugasPejabat);
    }

    @Override
    public void createTembusanSuratTugas(TembusanSuratTugas tembusanSuratTugas) {
        tembusanSuratTugasDao.save(tembusanSuratTugas);
    }

    @Override
    public void openSuratTugas(String kdSuratTugas) {
        SuratTugas suratTugas
                = getByKdSuratTugas(kdSuratTugas);

        suratTugas.setStatusBaca(1);
    }

    @Override
    public void openSuratTugasPenilai(String kdSuratTugas) {
        SuratTugas suratTugas
                = getByKdSuratTugas(kdSuratTugas);

        suratTugas.setStatusPenilaian(2);
    }
}
