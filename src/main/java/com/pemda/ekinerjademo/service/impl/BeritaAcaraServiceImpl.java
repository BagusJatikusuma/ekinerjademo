package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;
import com.pemda.ekinerjademo.repository.ekinerjarepository.BeritaAcaraDao;
import com.pemda.ekinerjademo.service.BeritaAcaraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */
@Service("BeritaAcaraService")
@Transactional("ekinerjaTransactionManager")
public class BeritaAcaraServiceImpl implements BeritaAcaraService{
    public static final Logger LOGGER = LoggerFactory.getLogger(BeritaAcaraServiceImpl.class);

    @Autowired private BeritaAcaraDao beritaAcaraDao;

    @Override
    public List<BeritaAcara> getByNipPembuatSurat(String nipPembuatSurat) {
        return beritaAcaraDao.findByNipPembuatSurat(nipPembuatSurat);
    }

    @Override
    public void createBeritaAcara(BeritaAcara beritaAcara) {
        beritaAcaraDao.save(beritaAcara);

    }

    @Override
    public BeritaAcara getBeritaAcara(String kdBeritaAcara) {
        return beritaAcaraDao.findOne(kdBeritaAcara);
    }
}
