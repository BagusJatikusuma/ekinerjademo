package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKuasa;

import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */
public interface SuratKuasaService {
    List<SuratKuasa> getByNipPembuatSurat(String nipPembuatSurat);

    List<SuratKuasa> getByNipPenerimaKuasa(String nipPenerimaKuasa);

    void createSuratKuasa(SuratKuasa suratKuasa);

    SuratKuasa getSuratKuasa(String kdSuratKuasa);

    void openSuratKuasaByPenilai(String kdSuratKuasa);
}
