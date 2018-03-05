package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.Laporan;
import com.pemda.ekinerjademo.model.ekinerjamodel.NomorUrutSuratUnitKerja;
import com.pemda.ekinerjademo.model.ekinerjamodel.NomorUrutSuratUnitKerjaId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.LaporanDao;
import com.pemda.ekinerjademo.service.LaporanService;
import com.pemda.ekinerjademo.service.NomorUrutSuratUnitKerjaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */

@Service("LaporanService")
@Transactional("ekinerjaTransactionManager")
public class LaporanServiceImpl implements LaporanService {

    public static final Logger LOGGER = LoggerFactory.getLogger(LaporanServiceImpl.class);

    @Autowired private LaporanDao laporanDao;
    @Autowired
    private NomorUrutSuratUnitKerjaService nomorUrutSuratUnitKerjaService;


    @Override
    public List<Laporan> getByNipPembuatSurat(String nipPembuatSurat) {
        return laporanDao.findByNipPembuatSurat(nipPembuatSurat);
    }

    @Override
    public void createLaporan(Laporan laporan) {
        String kdBarcode
                = laporan.getKdLaporan()
                    +laporan.getKdUnitKerja()
                    +"1";

        laporanDao.save(laporan);
    }

    @Override
    public Laporan getLaporan(String kdLaporan) {
        return laporanDao.findOne(kdLaporan);
    }

    @Override
    public void openLaporanByPenilai(String kdLaporan) {
        Laporan laporan = laporanDao.findOne(kdLaporan);

        laporan.setStatusPenilaian(1);
    }

    @Override
    public void approveLaporanService(String kdLaporan) {

    }
}
