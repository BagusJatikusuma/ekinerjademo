package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.SopUraianTugasJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.SopUraianTugasJabatanId;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SopUraianTugasJabatanDao;
import com.pemda.ekinerjademo.service.SopUraianTugasJabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 08/10/17.
 */
@Service("SopUraianTugasJabatanService")
@Transactional("ekinerjaTransactionManager")
public class SopUraianTugasJabatanServiceImpl implements SopUraianTugasJabatanService {
    @Autowired private SopUraianTugasJabatanDao sopUrtugJabatanDao;

    @Override
    public List<SopUraianTugasJabatan> findByUraianTugasJabatan(UraianTugasJabatanId uraianTugasJabatanId) {
        return sopUrtugJabatanDao
                .findBySopUraianTugasJabatanId_KdUrtugAndSopUraianTugasJabatanId_KdJabatan(
                        uraianTugasJabatanId.getKdUrtug(),
                        uraianTugasJabatanId.getKdJabatan()
                );
    }

    @Override
    public void save(SopUraianTugasJabatan sopUraianTugasJabatan) {
        sopUrtugJabatanDao.save(sopUraianTugasJabatan);
    }

    @Override
    public void delete(SopUraianTugasJabatanId sopUraianTugasJabatanId) {
        sopUrtugJabatanDao.deleteBySopUraianTugasJabatanId(sopUraianTugasJabatanId);
    }
}
