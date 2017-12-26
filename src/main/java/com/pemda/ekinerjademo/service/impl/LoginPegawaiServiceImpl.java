package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.LoginPegawai;
import com.pemda.ekinerjademo.repository.ekinerjarepository.LoginPegawaiDao;
import com.pemda.ekinerjademo.service.LoginPegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bagus on 26/12/17.
 */
@Service("LoginPegawaiService")
@Transactional("ekinerjaTransactionManager")
public class LoginPegawaiServiceImpl implements LoginPegawaiService {
    @Autowired private LoginPegawaiDao loginPegawaiDao;

    @Override
    public void createLog(LoginPegawai loginPegawai) {
        loginPegawaiDao.save(loginPegawai);
    }

}
