package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.SopTemplate;
import com.pemda.ekinerjademo.model.ekinerjamodel.SopTemplateId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SopTemplateDao;
import com.pemda.ekinerjademo.service.SopTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
@Service("SopTemplateService")
@Transactional("ekinerjaTransactionManager")
public class SopTemplateServiceImpl implements SopTemplateService {
    @Autowired private SopTemplateDao sopTemplateDao;

    @Override
    public List<SopTemplate> getSopTemplateBySop(String kdSop) {
        return sopTemplateDao.findBySopTemplateId_KdSop(kdSop);
    }

    @Override
    public void save(SopTemplate sopTemplate) {
        sopTemplateDao.save(sopTemplate);
    }

    @Override
    public void delete(SopTemplateId sopTemplateId) {
        sopTemplateDao.deleteBySopTemplateId(sopTemplateId);
    }
}
