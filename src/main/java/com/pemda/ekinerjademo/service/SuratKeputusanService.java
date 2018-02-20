package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKeputusan;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
public interface SuratKeputusanService {
    List<SuratKeputusan> getByKdUnitKerja(String kdUnitKerja);
    List<SuratKeputusan> getByNipPembuat(String nipPembuat);
    List<SuratKeputusan> getByNomorTahun(Integer nomorTahun);

    SuratKeputusan getByKdSuratKeputusan(String kdSuratKeputusan);

    void create(SuratKeputusan suratKeputusan);

    void approveSuratKeputusan(String kdSuratKeputusan);
    
}
