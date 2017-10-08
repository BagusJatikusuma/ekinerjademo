package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;

/**
 * Created by bagus on 08/10/17.
 */
public interface UnitKerjaKegiatanService {
    UnitKerjaKegiatan findByKdUnitKerja(String kdUnitKerja);
}
