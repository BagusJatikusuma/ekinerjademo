package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.RincianEKinerja;

import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
public interface EKinerjaService {
    void save(RincianEKinerja rincianEKinerja);
    RincianEKinerja getRincianEKinerja(String kdUrtug, String nipPegawai);
    List<RincianEKinerja> getRincianEKinerjaByUrtug(String kdUrtug);
    List<RincianEKinerja> getRincianEKinerjaByNip(String nipPegawai);
    List<RincianEKinerja> getRincianEKinerjaByNipAndDate(String nipPegawai, Date date);
}
