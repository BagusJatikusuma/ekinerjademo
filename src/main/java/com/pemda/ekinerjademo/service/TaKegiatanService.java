package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatanId;

import java.util.List;

/**
 * Created by bagus on 07/10/17.
 */
public interface TaKegiatanService {
    List<TaKegiatan> getAll();
    List<TaKegiatan> findByUnitKerja(UnitKerjaKegiatan unitKerjaKegiatan);
    TaKegiatan findByTaKegiatanId(TaKegiatanId taKegiatanId);
}
