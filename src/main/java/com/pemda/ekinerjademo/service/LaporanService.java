package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.Laporan;

import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */
public interface LaporanService {
    List<Laporan> getByNipPembuatSurat(String nipPembuatSurat);

    void createLaporan(Laporan laporan);

    Laporan getLaporan(String kdLaporan);
}
