package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;

import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */
public interface BeritaAcaraService {

    List<BeritaAcara> getByNipPembuatSurat(String nipPembuatSurat);

    void createBeritaAcara(BeritaAcara beritaAcara);

    BeritaAcara getBeritaAcara(String kdBeritaAcara);

    void openBeritaAcaraByPenilai(String kdBeritaAcara);
}
