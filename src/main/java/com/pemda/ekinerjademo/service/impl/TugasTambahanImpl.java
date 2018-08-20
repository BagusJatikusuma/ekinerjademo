package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.TugasTambahan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TugasTambahanDao;
import com.pemda.ekinerjademo.service.TugasTambahanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("TugasTambahanService")
@Transactional("ekinerjaTransactionManager")
public class TugasTambahanImpl implements TugasTambahanService{
    @Autowired
    private TugasTambahanDao tugasTambahanDao;

    @Override
    public void createTugasTambahan(TugasTambahan tugasTambahan) {
        tugasTambahanDao.save(tugasTambahan);
    }

    @Override
    public List<TugasTambahan> findByPegawaiBulanTahun(String nipPegawai, Short bulan, Short tahun) {
        return tugasTambahanDao.findByNipPegawaiAndBulanAndTahun(nipPegawai, bulan, tahun);
    }

}
