package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.TemplateLain;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TemplateLainDao;
import com.pemda.ekinerjademo.service.TemplateLainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 26/12/17.
 */
@Service("TemplateLainService")
@Transactional("ekinerjaTransactionManager")
public class TemplateLainServiceImpl implements TemplateLainService {
    @Autowired private TemplateLainDao templateLainDao;

    @Override
    public void create(TemplateLain templateLain) {
        templateLainDao.save(templateLain);
    }

    @Override
    public TemplateLain getTemplateLain(String kdTemplateLain) {
        return templateLainDao.findOne(kdTemplateLain);
    }

    @Override
    public List<TemplateLain> getByPembuat(String nipPembuat) {
        return templateLainDao.findByNipPegawai(nipPembuat);
    }
}
