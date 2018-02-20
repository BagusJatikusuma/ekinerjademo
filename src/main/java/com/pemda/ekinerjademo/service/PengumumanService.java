package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.Pengumuman;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
public interface PengumumanService {
    List<Pengumuman> getByKdUnitKerja(String kdUnitKerja);
    List<Pengumuman> getByPembuat(String nipPembuat);
    List<Pengumuman> getByNomorTahun(Integer nomorTahun);

    Pengumuman getByKdPengumuman(String kdPengumuman);

    void create(Pengumuman pengumuman);

    void approvePengumuman(String kdPengumuman);
}
