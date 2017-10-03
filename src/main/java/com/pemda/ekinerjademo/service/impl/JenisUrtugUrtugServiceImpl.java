package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.JenisUrtugUrtug;
import com.pemda.ekinerjademo.repository.ekinerjarepository.JenisUrtugUrtugDao;
import com.pemda.ekinerjademo.service.JenisUrtugUrtugService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 26/09/17.
 */
@Service("JenisUrtugUrtugService")
@Transactional("ekinerjaTransactionManager")
public class JenisUrtugUrtugServiceImpl implements JenisUrtugUrtugService {
    public static final Logger LOGGER = LoggerFactory.getLogger(JenisUrtugUrtugServiceImpl.class);

    @Autowired
    private JenisUrtugUrtugDao jenisUrtugUrtugDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(JenisUrtugUrtug jenisUrtugUrtug) {
        jenisUrtugUrtugDao.save(jenisUrtugUrtug);
    }

    @Override
//    @Transactional(readOnly = true)
    public List<JenisUrtugUrtug> getJenisUrtugUrtug() {
        return jenisUrtugUrtugDao.findAll();
    }

    @Override
    public List<JenisUrtugUrtug> getJenisUrtugUrtugByJabatan(String kdJabatan) {
        return jenisUrtugUrtugDao.findAllByKdJabatan(kdJabatan);
    }

}
