package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.RincianEKinerja;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
public interface EKinerjaService {
    RincianEKinerja getRincianEKinerja(String kdUrtug, String nipPegawai);
    List<RincianEKinerja> getRincianEKinerjaByUrtug(String kdUrtug);
    List<RincianEKinerja> getRincianEKinerjaByNip(String nipPegawai);
}
