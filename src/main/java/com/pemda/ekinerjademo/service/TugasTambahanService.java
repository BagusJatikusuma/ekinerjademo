package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.TugasTambahan;

import java.util.List;

public interface TugasTambahanService {
    void createTugasTambahan(TugasTambahan tugasTambahan);
    List<TugasTambahan> findByPegawaiBulanTahun(String nipPegawai, Short bulan, Short tahun);
}
