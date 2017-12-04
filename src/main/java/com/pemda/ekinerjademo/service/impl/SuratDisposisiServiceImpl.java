package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratDisposisi;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratDisposisiDao;
import com.pemda.ekinerjademo.service.SuratDisposisiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bagus on 19/11/17.
 */
@Service("SuratDisposisiService")
@Transactional("ekinerjaTransactionManager")
public class SuratDisposisiServiceImpl implements SuratDisposisiService {
    @Autowired private SuratDisposisiDao suratDisposisiDao;

    @Override
    public void create(SuratDisposisi suratDisposisi) {
        suratDisposisiDao.save(suratDisposisi);
    }

    @Override
    public void delete(String suratDisposisiId) {
        suratDisposisiDao.deleteByNoSurat(suratDisposisiId);
    }
}
