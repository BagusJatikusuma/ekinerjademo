package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.SopJenisUrtugUrtug;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SopJenisUrtugUrtugDao;
import com.pemda.ekinerjademo.service.SopJenisUrtugUrtugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 26/09/17.
 */
@Service("SopJenisUrtugUrtugService")
@Transactional("ekinerjaTransactionManager")
public class SopJenisUrtugUrtugServiceImpl implements SopJenisUrtugUrtugService {
    @Autowired
    private SopJenisUrtugUrtugDao sopJenisUrtugUrtugDao;

    @Override
    @Transactional(readOnly = true)
    public List<SopJenisUrtugUrtug> getSopJenisUrtugUrtug() {
        return sopJenisUrtugUrtugDao.findAll();
    }

}
