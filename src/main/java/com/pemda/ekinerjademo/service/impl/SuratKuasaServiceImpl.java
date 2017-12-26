package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKuasa;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratKuasaDao;
import com.pemda.ekinerjademo.service.SuratKuasaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */
@Service("SuratKuasaService")
@Transactional("ekinerjaTransactionManager")
public class SuratKuasaServiceImpl implements SuratKuasaService{
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratKuasaServiceImpl.class);

    @Autowired private SuratKuasaDao suratKuasaDao;

    @Override
    public List<SuratKuasa> getByNipPembuatSurat(String nipPembuatSurat) {
        return suratKuasaDao.findByNipPembuatSurat(nipPembuatSurat);
    }

    @Override
    public void createSuratKuasa(SuratKuasa suratKuasa) {
        suratKuasaDao.save(suratKuasa);
    }

    @Override
    public SuratKuasa getSuratKuasa(String kdSuratKuasa) {
        return suratKuasaDao.findOne(kdSuratKuasa);
    }
}
