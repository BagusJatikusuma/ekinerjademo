package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.StatusPenanggungJawabKegiatan;

import java.util.List;

/**
 * Created by bagus on 05/10/17.
 */
public interface StatusPenanggungJawabKegiatanService {
    List<StatusPenanggungJawabKegiatan> getAll();
    StatusPenanggungJawabKegiatan get(String kdStatus);
    void save(StatusPenanggungJawabKegiatan statusPenanggungJawabKegiatan);
    void update(StatusPenanggungJawabKegiatan statusPenanggungJawabKegiatan);
    void delete(String kdStatus);
}
