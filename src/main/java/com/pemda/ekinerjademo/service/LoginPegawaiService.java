package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.LoginPegawai;

import java.util.List;

/**
 * Created by bagus on 26/12/17.
 */
public interface LoginPegawaiService {
    void createLog(LoginPegawai loginPegawai);
    LoginPegawai get(Long loginId);
    List<LoginPegawai> getByBulanAndTahun(Integer bulan, Integer tahun);
}
