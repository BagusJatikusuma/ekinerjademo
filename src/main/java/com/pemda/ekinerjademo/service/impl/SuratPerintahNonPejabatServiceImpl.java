package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPerintahNonPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratPerintahNonPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratPerintahNonPejabat;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratPerintahNonPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TargetSuratPerintahNonPejabatDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TembusanSuratPerintahNonPejabatDao;
import com.pemda.ekinerjademo.service.SuratPerintahNonPejabatService;
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
public class SuratPerintahNonPejabatServiceImpl implements SuratPerintahNonPejabatService {
    @Autowired
    private SuratPerintahNonPejabatDao suratPerintahNonPejabatDao;
    @Autowired
    private TargetSuratPerintahNonPejabatDao targetSuratPerintahNonPejabatDao;
    @Autowired
    private TembusanSuratPerintahNonPejabatDao tembusanSuratPerintahNonPejabatDao;

    @Override
    public Set<SuratPerintahNonPejabat> getByKdUnitKerja(String kdUnitKerja) {
        return suratPerintahNonPejabatDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public Set<SuratPerintahNonPejabat> getByNomorTahun(String nomorTahun) {
        return suratPerintahNonPejabatDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public Set<SuratPerintahNonPejabat> getByNipPembuat(String nipPembuat) {
        return suratPerintahNonPejabatDao.findByNipPembuat(nipPembuat);
    }

    @Override
    public List<TargetSuratPerintahNonPejabat> getSuratPerintahTarget(String nipTargetSurat) {
        return targetSuratPerintahNonPejabatDao.findByTargetSuratPerintahNonPejabatId_NipPegawai(nipTargetSurat);
    }

    @Override
    public Integer getLatestNomorSuratByUnitKerja(String kdUnitKerja) {
        return suratPerintahNonPejabatDao.findLatestNomorSuratByUnitKerja(kdUnitKerja);
    }

    @Override
    public void creteSurat(SuratPerintahNonPejabat suratPerintahNonPejabat) {
        suratPerintahNonPejabatDao.save(suratPerintahNonPejabat);
    }

    @Override
    public void createTargetSurat(Set<TargetSuratPerintahNonPejabat> targetSuratPerintahNonPejabatSet) {
        if (!targetSuratPerintahNonPejabatSet.isEmpty()) {
            for (TargetSuratPerintahNonPejabat target
                    : targetSuratPerintahNonPejabatSet) {
                targetSuratPerintahNonPejabatDao.save(target);
            }
        }
    }

    @Override
    public void createTembusanSurat(Set<TembusanSuratPerintahNonPejabat> tembusanSuratPerintahNonPejabatSet) {
        if (!tembusanSuratPerintahNonPejabatSet.isEmpty()) {
            for (TembusanSuratPerintahNonPejabat tembusan
                    : tembusanSuratPerintahNonPejabatSet) {
                tembusanSuratPerintahNonPejabatDao.save(tembusan);
            }
        }
    }

    @Override
    public SuratPerintahNonPejabat getSuratPerintahNonPejabatByKdSuratPerintah(String kdSuratperintah) {
        return suratPerintahNonPejabatDao.findSuratPerintahNonPejabatByKdSuratPerintah(kdSuratperintah);
    }
}
