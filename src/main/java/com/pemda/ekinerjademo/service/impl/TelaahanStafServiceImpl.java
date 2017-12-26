package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.TelaahanStaf;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TelaahanStafDao;
import com.pemda.ekinerjademo.service.TelaahanStafService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 15/12/17.
 */
@Service("TelaahanStafService")
@Transactional("ekinerjaTransactionManager")
public class TelaahanStafServiceImpl implements TelaahanStafService {
    public static final Logger LOGGER = LoggerFactory.getLogger(TelaahanStafServiceImpl.class);

    @Autowired
    private TelaahanStafDao telaahanStafDao;

    @Override
    public List<TelaahanStaf> getByNipPembuatSurat(String nipPembuatSurat) {
        return telaahanStafDao.findByNipPembuatSurat(nipPembuatSurat);
    }

    @Override
    public void createTelaahanStaf(TelaahanStaf telaahanStaf) {
        telaahanStafDao.save(telaahanStaf);
    }

    @Override
    public TelaahanStaf getTelaahanStaf(String kdTelaahanStaf) {
        return telaahanStafDao.findOne(kdTelaahanStaf);
    }
}
