package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPengantar;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPengantarIsi;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
public interface SuratPengantarService {
    List<SuratPengantar> getByKdUnitKerja(String kdUnitKerja);
    List<SuratPengantar> getByNipPembuat(String nipPembuat);
    List<SuratPengantar> getByNomorTahun(Integer nomorTahun);

    SuratPengantar getByKdSuratPengantar(String kdSuratPengantar);

    void create(SuratPengantar suratPengantar);
    void createSuratPengantarIsi(SuratPengantarIsi suratPengantarIsi);

    void openSuratPengantarPenilai(String kdSuratPengantar);
}
