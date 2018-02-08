package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatanId;

import java.util.List;

/**
 * Created by bagus on 07/02/18.
 */
public interface PenanggungJawabKegiatanService {
    void create(PenanggungJawabKegiatan penanggungJawabKegiatan);
    List<PenanggungJawabKegiatan> getByUnitKerja(Integer kdUrusan, Integer kdBidang, Integer kdUnit);
    void delete(PenanggungJawabKegiatanId id);
}
