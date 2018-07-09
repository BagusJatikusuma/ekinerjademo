package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.NomorUrutSuratUnitKerja;
import com.pemda.ekinerjademo.model.ekinerjamodel.NomorUrutSuratUnitKerjaId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.NomorUrutSuratUnitKerjaDao;
import com.pemda.ekinerjademo.service.NomorUrutSuratUnitKerjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bagus on 05/12/17.
 */
@Service("NomorUrutSuratUnitKerjaService")
@Transactional("ekinerjaTransactionManager")
public class NomorUrutSuratUnitKerjaServiceImpl implements NomorUrutSuratUnitKerjaService {
    @Autowired
    private NomorUrutSuratUnitKerjaDao nomorUrutSuratUnitKerjaDao;

    @Override
    public NomorUrutSuratUnitKerja getNomorSuratByUnitKerjaAndTahun(String kdUnitKerja, Integer tahun) {
        return nomorUrutSuratUnitKerjaDao
                .findByNomorUrutSuratUnitKerjaId(new NomorUrutSuratUnitKerjaId(kdUnitKerja, tahun));
    }

    @Override
    public void updateNomorUrutSurat(NomorUrutSuratUnitKerja nomorUrutSuratUnitKerja) {
        nomorUrutSuratUnitKerjaDao.save(nomorUrutSuratUnitKerja);
    }

    @Override
    public void createNomorUrutSurat(NomorUrutSuratUnitKerja nomorUrutSuratUnitKerja) {
        nomorUrutSuratUnitKerjaDao.save(nomorUrutSuratUnitKerja);
    }
}
