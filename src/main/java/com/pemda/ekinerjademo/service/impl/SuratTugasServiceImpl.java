package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.*;
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
    @Autowired private SuratTugasPejabatDao suratTugasPejabatDao;
    @Autowired private SuratTugasNonPejabatDao suratTugasNonPejabatDao;

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
    public TargetSuratTugasPegawai getTargetSuratTugasPegawaiById(TargetSuratTugasPegawaiId id) {
        return targetSuratTugasPegawaiDao.findOne(id);
    }

    @Override
    public TargetSuratTugasPejabat getTargetSuratTugasPejabatById(TargetSuratTugasPejabatId id) {
        return targetSuratTugasPejabatDao.findOne(id);
    }

    @Override
    public TembusanSuratTugas getTembusanSuratTugasById(TembusanSuratTugasId id) {
        return tembusanSuratTugasDao.findOne(id);
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
    public void createSuratTugasPejabat(SuratTugasPejabat suratTugasPejabat) {
        suratTugasPejabatDao.save(suratTugasPejabat);
    }

    @Override
    public void createSuratTugasNonPejabat(SuratTugasNonPejabat suratTugasNonPejabat) {
        suratTugasNonPejabatDao.save(suratTugasNonPejabat);
    }

    @Override
    public void openSuratTugas(String kdSuratTugas) {
        SuratTugas suratTugas
                = getByKdSuratTugas(kdSuratTugas);

        suratTugas.setStatusBaca(1);
    }

    @Override
    public void openTargetSuratTugasPegawai(TargetSuratTugasPegawaiId id) {
        TargetSuratTugasPegawai targetSuratTugasPegawai
                = targetSuratTugasPegawaiDao.findOne(id);
        targetSuratTugasPegawai.setStatusBaca(1);
    }

    @Override
    public void openTargetSuratTugasPejabat(TargetSuratTugasPejabatId id) {
        TargetSuratTugasPejabat targetSuratTugasPejabat
                = targetSuratTugasPejabatDao.findOne(id);
        targetSuratTugasPejabat.setStatusBaca(1);
    }

    @Override
    public void openTembusanSuratTugas(TembusanSuratTugasId id) {
        TembusanSuratTugas tembusanSuratTugas
                = tembusanSuratTugasDao.findOne(id);
        tembusanSuratTugas.setStatusBaca(1);
    }

    @Override
    public void openSuratTugasPenilai(String kdSuratTugas) {
        SuratTugas suratTugas
                = getByKdSuratTugas(kdSuratTugas);

        suratTugas.setStatusPenilaian(1);
    }

    @Override
    public void approveSuratTugas(String kdSuratTugas) {
        SuratTugas suratTugasLast = suratTugasDao.findOne(kdSuratTugas);
        String penilaianTree = suratTugasLast.getPathPenilaian();

        Set<SuratTugas> suratTugasSet
                = suratTugasDao.findByLastTree(penilaianTree.substring(0, penilaianTree.indexOf(".")));
        for (SuratTugas suratTugas
                : suratTugasSet) {
            suratTugas.setApprovalPenandatangan(1);
        }
    }
}
