package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.repository.bismarepository.TkdJabatanDao;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 10/09/17.
 */
@Service("TkdJabatanService")
@Transactional
public class TkdJabatanServiceImpl implements TkdJabatanService {
    @Autowired
    private TkdJabatanDao tkdJabatanDao;

    @Override
    public TkdJabatan getTkdJabatan(String kdJabatan) {
        return tkdJabatanDao.findByKdJabatan(kdJabatan);
    }

    @Override
    public List<TkdJabatan> getAll() {
        return tkdJabatanDao.findAll();
    }
}
