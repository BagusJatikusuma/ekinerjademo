package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasTemplateLain;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UraianTugasTemplateLainDao;
import com.pemda.ekinerjademo.service.UraianTugasTemplateLainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UraianTugasTemplateLainService")
@Transactional("ekinerjaTransactionManager")
public class UraianTugasTemplateLainServiceImpl
        implements UraianTugasTemplateLainService {
    @Autowired private UraianTugasTemplateLainDao uraianTugasTemplateLainDao;

    @Override
    public void create(UraianTugasTemplateLain uraianTugasTemplateLain) {
        uraianTugasTemplateLainDao.save(uraianTugasTemplateLain);
    }

    @Override
    public List<UraianTugasTemplateLain> getAll() {
        return uraianTugasTemplateLainDao.findAll();
    }

    @Override
    public List<UraianTugasTemplateLain> get(String kdUrtug, String kdJabatan, Integer tahunUrtug, String kdJenisUrtug, Integer bulanUrtug, String nipPegawai) {
        return uraianTugasTemplateLainDao.findByUrtug(kdUrtug, kdJabatan, tahunUrtug, kdJenisUrtug, bulanUrtug, nipPegawai);
    }
}
