package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.Sop;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SopDao;
import com.pemda.ekinerjademo.service.SopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 26/09/17.
 */
@Service("SopService")
@Transactional("ekinerjaTransactionManager")
public class SopServiceImpl implements SopService {
    @Autowired
    private SopDao sopDao;

    @Override
    @Transactional(readOnly = true)
    public List<Sop> getSop() {
        return sopDao.findAll();
    }

}
