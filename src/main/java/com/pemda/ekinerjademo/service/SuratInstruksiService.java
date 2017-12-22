package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;

import java.util.List;

/**
 * Created by bagus on 22/11/17.
 */
public interface SuratInstruksiService {
    void createSuratInstruksi(SuratInstruksi suratInstruksi);
    void createInstruksiPegawai(InstruksiPegawai instruksiPegawai);
    void createInstruksiPejabat(InstruksiPejabat instruksiPejabat);
    void createSuratInstruksiPejabat(SuratInstruksiPejabat suratInstruksiPejabat);
    void createSuratInstruksiNonPejabat(SuratInstruksiNonPejabat suratInstruksiNonPejabat);
    void openSuratInstruksi(String kdSuratInstruksi);
    void openSuratInstruksiTarget(InstruksiPegawaiId instruksiPegawaiId);

    SuratInstruksi getSuratInstruksi(String kdSuratInstruksi);
    SuratInstruksi getDokumenSuratInstruksi(String kdSuratInstruksi);
    List<InstruksiPegawai> getInstruksiPegawai(String nipPegawai);
    List<InstruksiPejabat> getInstruksiPejabat(String kdJabatan);
    List<SuratInstruksiPejabat> getSuratInstruksiPejabat(String kdJabatan);
    List<SuratInstruksiNonPejabat> getSuratInstruksiUnitKerja(String kdUnitKerja);
    List<SuratInstruksi> getSuratInstruksiByNip(String nipPegawai);

    List<SuratInstruksi> findTree(String kdSuratInstruksi);

}
