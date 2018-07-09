package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.NomorUrutSuratUnitKerja;
import com.pemda.ekinerjademo.model.ekinerjamodel.NomorUrutSuratUnitKerjaId;
import com.pemda.ekinerjademo.model.ekinerjamodel.TelaahanStaf;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TelaahanStafDao;
import com.pemda.ekinerjademo.service.NomorUrutSuratUnitKerjaService;
import com.pemda.ekinerjademo.service.TelaahanStafService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

/**
 * Created by bayu on 15/12/17.
 */
@Service("TelaahanStafService")
@Transactional("ekinerjaTransactionManager")
public class TelaahanStafServiceImpl implements TelaahanStafService {
    public static final Logger LOGGER = LoggerFactory.getLogger(TelaahanStafServiceImpl.class);
    @Autowired
    private NomorUrutSuratUnitKerjaService nomorUrutSuratUnitKerjaService;

    @Autowired
    private TelaahanStafDao telaahanStafDao;

    @Override
    public List<TelaahanStaf> getByNipPembuatSurat(String nipPembuatSurat) {
        return telaahanStafDao.findByNipPembuatSurat(nipPembuatSurat);
    }

    @Override
    public void createTelaahanStaf(TelaahanStaf telaahanStaf) {
        String kdBarcode
                = telaahanStaf.getKdTelaahanStaf()
                    + telaahanStaf.getKdUnitKerja()
                    + "14";
        telaahanStaf.setKdBarcode(kdBarcode);

        telaahanStafDao.save(telaahanStaf);
    }

    @Override
    public TelaahanStaf getTelaahanStaf(String kdTelaahanStaf) {
        return telaahanStafDao.findOne(kdTelaahanStaf);
    }
}
