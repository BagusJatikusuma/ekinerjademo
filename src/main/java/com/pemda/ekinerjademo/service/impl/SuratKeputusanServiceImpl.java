package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKeputusan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratKeputusanDao;
import com.pemda.ekinerjademo.service.SuratKeputusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@Service("SuratKeputusanService")
@Transactional("ekinerjaTransactionManager")
public class SuratKeputusanServiceImpl implements SuratKeputusanService {
    @Autowired private SuratKeputusanDao suratKeputusanDao;

    @Override
    public List<SuratKeputusan> getByKdUnitKerja(String kdUnitKerja) {
        return suratKeputusanDao.findByKdUnitKerja(kdUnitKerja);
    }

    @Override
    public List<SuratKeputusan> getByNipPembuat(String nipPembuat) {
        return suratKeputusanDao.findByNipPembuatSurat(nipPembuat);
    }

    @Override
    public List<SuratKeputusan> getByNomorTahun(Integer nomorTahun) {
        return suratKeputusanDao.findByNomorTahun(nomorTahun);
    }

    @Override
    public SuratKeputusan getByKdSuratKeputusan(String kdSuratKeputusan) {
        return suratKeputusanDao.findByKdSuratKeputusan(kdSuratKeputusan);
    }

    @Override
    public void create(SuratKeputusan suratKeputusan) {
        suratKeputusanDao.save(suratKeputusan);
    }
}
