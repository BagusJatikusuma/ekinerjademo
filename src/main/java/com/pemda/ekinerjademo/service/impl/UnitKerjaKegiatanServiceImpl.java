package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UnitKerjaKegiatanDao;
import com.pemda.ekinerjademo.service.UnitKerjaKegiatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bagus on 08/10/17.
 */
@Service("UnitKerjaKegiatanService")
@Transactional("ekinerjaTransactionManager")
public class UnitKerjaKegiatanServiceImpl implements UnitKerjaKegiatanService {
    @Autowired private UnitKerjaKegiatanDao unitKerjaKegiatanDao;

    @Override
    public UnitKerjaKegiatan findByKdUnitKerja(String kdUnitKerja) {
        return unitKerjaKegiatanDao.findByKdUnitKerja(kdUnitKerja);
    }
}
