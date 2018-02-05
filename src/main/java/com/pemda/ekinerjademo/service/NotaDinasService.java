package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.NotaDinas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanNotaDinas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanNotaDinasId;

import java.util.List;

/**
 * Created by bagus on 30/01/18.
 */
public interface NotaDinasService {
    List<NotaDinas> getByKdUnitKerja(String kdUnitKerja) ;
    List<NotaDinas> getByNipPembuat(String nipPembuat);
    List<NotaDinas> getByByNomorTahun(Integer nomorTahun);
    List<TembusanNotaDinas> getTembusanNotaDinasByJabatan(String kdJabatan);

    NotaDinas findBykdNotaDinas(String kdNotaDinas);

    void create(NotaDinas notaDinas);
    void createTembusanNotaDinas(TembusanNotaDinas tembusanNotaDinas);

    void openNotaDinas(String kdNotaDinas);
    void openTembusanNotaDinas(TembusanNotaDinasId id);
    void openNotaDinasByPenilai(String kdNotaDinas);

}