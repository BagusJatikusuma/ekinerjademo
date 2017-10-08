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
    public List<Sop> getSop() {
        return sopDao.findAll();
    }

    @Override
    public Sop get(String kdSop) {
        return sopDao.findByKdSop(kdSop);
    }

    @Override
    public void save(Sop sop) {
        sopDao.save(sop);
    }

    @Override
    public void update(Sop sop) {
        Sop currentSop = sopDao.findByKdSop(sop.getKdSop());
        currentSop.setSop(sop.getSop());
    }

    @Override
    public void delete(String kdSop) {
        sopDao.deleteByKdSop(kdSop);
    }

}
