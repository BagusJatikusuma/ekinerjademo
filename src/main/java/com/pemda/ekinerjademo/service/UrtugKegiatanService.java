package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanId;

import java.util.List;

/**
 * Created by bagus on 05/10/17.
 */
public interface UrtugKegiatanService {
    List<UrtugKegiatan> findAll();
    List<UrtugKegiatan> findAllByUraianTugasJabatan(String kdUrtug, String kdJabatan, String kdJenisUrtug);
    UrtugKegiatan find(UrtugKegiatanId urtugKegiatanId);
    void save(UrtugKegiatan urtugKegiatan);
    void update(UrtugKegiatan urtugKegiatan);
    void delete(UrtugKegiatanId urtugKegiatanId);
}
