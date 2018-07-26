package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatanId;
import com.pemda.ekinerjademo.repository.simdarepository.TaIndikatorDao;
import com.pemda.ekinerjademo.repository.simdarepository.TaKegiatanDao;
import com.pemda.ekinerjademo.service.TaKegiatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 07/10/17.
 */
@Service("TaKegiatanService")
@Transactional("simdaTransactionManager")
public class TaKegiatanServiceImpl implements TaKegiatanService {
    @Autowired private TaKegiatanDao taKegiatanDao;
    @Autowired private TaIndikatorDao taIndikatorDao;

    @Override
    public List<TaKegiatan> getAll() {
        return taKegiatanDao.findAll();
    }

    @Override
    public List<TaKegiatan> findByUnitKerja(UnitKerjaKegiatan unitKerjaKegiatan) {
        return taKegiatanDao
                .findAllByKdUnitKerja(
                        unitKerjaKegiatan.getKdUrusan(),
                        unitKerjaKegiatan.getKdBidang(),
                        unitKerjaKegiatan.getKdUnit());
    }

    @Override
    public TaKegiatan findByTaKegiatanId(TaKegiatanId taKegiatanId) {
        return taKegiatanDao.findByTaKegiatanId(taKegiatanId);
    }
}
