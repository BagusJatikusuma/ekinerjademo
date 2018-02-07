package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.repository.ekinerjarepository.PenanggungJawabKegiatanDao;
import com.pemda.ekinerjademo.service.PenanggungJawabKegiatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bagus on 07/02/18.
 */
@Service("PenanggungJawabKegiatanService")
@Transactional("ekinerjaTransactionManager")
public class PenanggungJawabKegiatanServiceImpl implements PenanggungJawabKegiatanService {
    @Autowired private PenanggungJawabKegiatanDao penanggungJawabKegiatanDao;

}
