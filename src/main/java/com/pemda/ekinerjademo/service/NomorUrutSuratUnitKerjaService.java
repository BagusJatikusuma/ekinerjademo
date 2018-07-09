package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.NomorUrutSuratUnitKerja;

/**
 * Created by bagus on 05/12/17.
 */
public interface NomorUrutSuratUnitKerjaService {
    NomorUrutSuratUnitKerja getNomorSuratByUnitKerjaAndTahun(String kdUnitKerja, Integer tahun);
    void updateNomorUrutSurat(NomorUrutSuratUnitKerja nomorUrutSuratUnitKerja);
    void createNomorUrutSurat(NomorUrutSuratUnitKerja nomorUrutSuratUnitKerja);
}
