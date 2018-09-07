package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulananAjuan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulananAjuanId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UraianTugasPegawaiBulananAjuanDao;
import com.pemda.ekinerjademo.service.UraianTugasPegawaiBulananAjuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UraianTugasPegawaiBulananAjuanService")
@Transactional("ekinerjaTransactionManager")
public class UraianTugasPegawaiBulananAjuanServiceImpl
        implements UraianTugasPegawaiBulananAjuanService {
    @Autowired
    private UraianTugasPegawaiBulananAjuanDao uraianTugasPegawaiBulananAjuanDao;

    @Override
    public void create(UraianTugasPegawaiBulananAjuan uraianTugasPegawaiBulananAjuan) {
        uraianTugasPegawaiBulananAjuanDao.save(uraianTugasPegawaiBulananAjuan);
    }

    @Override
    public void delete(UraianTugasPegawaiBulananAjuanId id) {
        uraianTugasPegawaiBulananAjuanDao.delete(id);
    }
}
