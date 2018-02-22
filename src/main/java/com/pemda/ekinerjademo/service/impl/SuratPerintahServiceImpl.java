package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.*;
import com.pemda.ekinerjademo.service.SuratPerintahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 14/11/17.
 */
@Service("SuratPerintahNonPejabatService")
@Transactional("ekinerjaTransactionManager")
public class SuratPerintahServiceImpl implements SuratPerintahService {
    @Autowired
    private SuratPerintahDao suratPerintahDao;
    @Autowired
    private TargetSuratPerintahNonPejabatDao targetSuratPerintahNonPejabatDao;
    @Autowired
    private TargetSuratPerintahPegawaiDao targetSuratPerintahPegawaiDao;
    @Autowired
    private TargetSuratPerintahPejabatDao targetSuratPerintahPejabatDao;
    @Autowired
    private SuratPerintahPejabatDao suratPerintahPejabatDao;
    @Autowired
    private SuratPerintahNonPejabatDao suratPerintahNonPejabatDao;
    @Autowired
    private TembusanSuratPerintahDao tembusanSuratPerintahDao;

    @Override
    public Set<SuratPerintah> getByKdUnitKerja(String kdUnitKerja) {
        return suratPerintahDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public Set<SuratPerintah> getByNomorTahun(String nomorTahun) {
        return suratPerintahDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public Set<SuratPerintah> getByNipPembuat(String nipPembuat) {
        return suratPerintahDao.findByNipPembuat(nipPembuat);
    }

    @Override
    public List<TargetSuratPerintahPegawai> getTargetSuratPerintahPegawai(String nipTargetSurat) {
        return targetSuratPerintahPegawaiDao.findByTargetSuratPerintahPegawaiId_NipPegawai(nipTargetSurat);
    }

    @Override
    public List<TargetSuratPerintahPejabat> getTargetSuratPerintahPejabat(String kdJabatanTarget) {
        return targetSuratPerintahPejabatDao.findByTargetSuratPerintahPejabatId_KdJabatan(kdJabatanTarget);
    }

    @Override
    public List<TembusanSuratPerintah> getTembusanSuratPerintah(String kdJabatanTembusan) {
        return tembusanSuratPerintahDao.findByTembusanSuratPerintahId_KdJabatan(kdJabatanTembusan);
    }

    @Override
    public Integer getLatestNomorSuratByUnitKerja(String kdUnitKerja) {
        return suratPerintahDao.findLatestNomorSuratByUnitKerja(kdUnitKerja);
    }

    @Override
    public void creteSurat(SuratPerintah suratPerintah) {
        suratPerintahDao.save(suratPerintah);
    }

    @Override
    public void createTargetSuratPegawai(Set<TargetSuratPerintahPegawai> targetSuratPerintahPegawaiSet) {
        if (!targetSuratPerintahPegawaiSet.isEmpty()) {
            for (TargetSuratPerintahPegawai target
                    : targetSuratPerintahPegawaiSet) {
                targetSuratPerintahNonPejabatDao.save(target);
            }
        }
    }

    @Override
    public void updateTargetSuratPegawai(TargetSuratPerintahPegawai targetSuratPerintahPegawai) {
        targetSuratPerintahPegawaiDao.save(targetSuratPerintahPegawai);
    }

    @Override
    public void createTargetSuratPejabat(Set<TargetSuratPerintahPejabat> targetSuratPerintahPejabatSet) {
        for (TargetSuratPerintahPejabat targetSuratPerintahPejabat
                : targetSuratPerintahPejabatSet) {
            targetSuratPerintahPejabatDao.save(targetSuratPerintahPejabat);
        }
    }

    @Override
    public void updateTargetSuratPejabat(TargetSuratPerintahPejabat targetSuratPerintahPejabat) {
        targetSuratPerintahPejabatDao.save(targetSuratPerintahPejabat);
    }

    @Override
    public void createSuratPerintahPejabat(SuratPerintahPejabat suratPerintahPejabat) {
        suratPerintahPejabatDao.save(suratPerintahPejabat);
    }

    @Override
    public void createSuratPerintahNonPejabat(SuratPerintahNonPejabat suratPerintahNonPejabat) {
        suratPerintahNonPejabatDao.save(suratPerintahNonPejabat);
    }

    @Override
    public void createTembusanSurat(Set<TembusanSuratPerintah> tembusanSuratPerintahSet) {
        if (!tembusanSuratPerintahSet.isEmpty()) {
            for (TembusanSuratPerintah tembusan
                    : tembusanSuratPerintahSet) {
                tembusanSuratPerintahDao.save(tembusan);
            }
        }
    }

    @Override
    public void updateTembusanSurat(TembusanSuratPerintah tembusanSuratPerintah) {
        tembusanSuratPerintahDao.save(tembusanSuratPerintah);
    }

    @Override
    public void openSuratPerintah(String kdSuratPerintah) {
        SuratPerintah suratPerintah
                = suratPerintahDao.findOne(kdSuratPerintah);

        suratPerintah.setStatusBaca(1);
    }

    @Override
    public void openSuratPerintahPegawai(TargetSuratPerintahPegawaiId targetSuratPerintahPegawaiId) {
        TargetSuratPerintahPegawai targetSuratPerintahPegawai
                = targetSuratPerintahPegawaiDao.findOne(targetSuratPerintahPegawaiId);
        if (targetSuratPerintahPegawai != null)
            targetSuratPerintahPegawai.setStatusBaca(1);
    }

    @Override
    public void openSuratTembusan(TembusanSuratPerintahId tembusanSuratPerintahId) {
        TembusanSuratPerintah tembusanSuratPerintah
                = tembusanSuratPerintahDao.findOne(tembusanSuratPerintahId);
        if (tembusanSuratPerintah != null)
            tembusanSuratPerintah.setStatusBaca(1);
    }

    @Override
    public void openSuratPeintahByPenilai(String kdSuratPerintah) {
        SuratPerintah suratPerintah = suratPerintahDao.findOne(kdSuratPerintah);

        suratPerintah.setStatusPenilaian(1);
    }

    @Override
    public void update(SuratPerintah suratPerintah) {
        suratPerintahDao.save(suratPerintah);
    }

    @Override
    public SuratPerintah getSuratPerintahByKdSuratPerintah(String kdSuratperintah) {
        return suratPerintahDao.findByKdSuratPerintah(kdSuratperintah);
    }

    @Override
    public void approveSuratPerintah(String kdSuratPerintah) {
        SuratPerintah suratPerintahLast = suratPerintahDao.findOne(kdSuratPerintah);
        suratPerintahLast.setStatusPenyebaran(1);
        suratPerintahLast.setApprovalPenandatangan(1);

        String penilaianTree = suratPerintahLast.getPathPenilaian();

        if (penilaianTree.contains(".")) {
            Set<SuratPerintah> suratPerintahList
                    = suratPerintahDao.findByLastTree(penilaianTree.substring(0, penilaianTree.indexOf(".")));
            for (SuratPerintah suratPerintah
                    : suratPerintahList) {
                suratPerintah.setApprovalPenandatangan(1);
            }
        }
    }
}
