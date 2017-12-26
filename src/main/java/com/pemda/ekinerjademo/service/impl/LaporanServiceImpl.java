package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.Laporan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.LaporanDao;
import com.pemda.ekinerjademo.service.LaporanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */

@Service("LaporanService")
@Transactional("ekinerjaTransactionManager")
public class LaporanServiceImpl implements LaporanService {

    public static final Logger LOGGER = LoggerFactory.getLogger(LaporanServiceImpl.class);

    @Autowired private LaporanDao laporanDao;


    @Override
    public List<Laporan> getByNipPembuatSurat(String nipPembuatSurat) {
        return laporanDao.findByNipPembuatSurat(nipPembuatSurat);
    }

    @Override
    public void createLaporan(Laporan laporan) {
        laporanDao.save(laporan);
    }

    @Override
    public Laporan getLaporan(String kdLaporan) {
        return laporanDao.findOne(kdLaporan);
    }
}
