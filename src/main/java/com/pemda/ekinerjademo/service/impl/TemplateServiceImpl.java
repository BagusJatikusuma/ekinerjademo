package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.Template;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TemplateDao;
import com.pemda.ekinerjademo.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
@Service("TemplateService")
@Transactional("ekinerjaTransactionManager")
public class TemplateServiceImpl implements TemplateService {
    @Autowired private TemplateDao templateDao;

    @Override
    public Template get(String templateId) {
        return templateDao.findByKdTemplate(templateId);
    }

    @Override
    public List<Template> getAll() {
        return templateDao.findAll();
    }

    @Override
    public void save(Template template) {
        templateDao.save(template);
    }

    @Override
    public void update(Template template) {
        Template currentTemplate = templateDao.findByKdTemplate(template.getKdTemplate());

        currentTemplate.setNmTemplate(template.getNmTemplate());
        currentTemplate.setUrl(template.getUrl());
    }

    @Override
    public void delete(String templateId) {
        templateDao.deleteByKdTemplate(templateId);
    }
}
