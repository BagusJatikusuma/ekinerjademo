package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.JenisUrtug;
import com.pemda.ekinerjademo.repository.ekinerjarepository.JenisUrtugDao;
import com.pemda.ekinerjademo.service.JenisUrtugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 26/09/17.
 */
@Service("JenisUrtugService")
@Transactional("ekinerjaTransactionManager")
public class JenisUrtugServiceImpl implements JenisUrtugService {
    @Autowired
    private JenisUrtugDao jenisUrtugDao;

    @Override
    @Transactional(readOnly = true)
    public List<JenisUrtug> getJenisUrtug() {
        return jenisUrtugDao.findAll();
    }
}
