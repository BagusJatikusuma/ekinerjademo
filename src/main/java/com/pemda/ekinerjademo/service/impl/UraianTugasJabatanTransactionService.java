package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UraianTugasJabatanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bagus on 03/10/17.
 */
@Service("UraianTugasJabatanServiceTransactional")
@Transactional("ekinerjaTransactionManager")
public class UraianTugasJabatanTransactionService {
    @Autowired
    private UraianTugasJabatanDao uraianTugasJabatanDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(UraianTugasJabatan uraianTugasJabatan) {
        uraianTugasJabatanDao.save(uraianTugasJabatan);
    }
}
