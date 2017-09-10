package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UraianTugasJabatanDao;
import com.pemda.ekinerjademo.service.UraianTugasJabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 10/09/17.
 */
@Service("UraianTugasJabatanService")
@Transactional("ekinerjaTransactionManager")
public class UraianTugasJabatanServiceImpl implements UraianTugasJabatanService {
    @Autowired
    private UraianTugasJabatanDao uraianTugasJabatanDao;

    @Override
    public UraianTugasJabatan getUraianTugasJabatan(String kdUrtug, String kdJabatan) {
        return null;
    }

    @Override
    public List<UraianTugasJabatan> getUraianTugasJabatanByJabatan(String kdJabatan) {
        return uraianTugasJabatanDao.findByUraianTugasJabatanIdKdJabatan(kdJabatan);
    }

    @Override
    public List<UraianTugasJabatan> getUraianTugasJabatanByUrtug(String kdUrtug) {
        return null;
    }
}
