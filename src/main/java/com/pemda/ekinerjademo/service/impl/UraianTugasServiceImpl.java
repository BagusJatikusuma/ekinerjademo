package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugas;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UraianTugasDao;
import com.pemda.ekinerjademo.service.UraianTugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bagus on 10/09/17.
 */
@Service("UraianTugasService")
@Transactional("ekinerjaTransactionManager")
public class UraianTugasServiceImpl implements UraianTugasService {
    @Autowired private UraianTugasDao uraianTugasDao;
    @Override
    public UraianTugas getUraianTugas(String kdUrtug) {
        return uraianTugasDao.findByKdUrtug(kdUrtug);
    }
}
