package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.TelaahanStaf;

import java.util.List;

/**
 * Created by bayu on 15/12/17.
 */
public interface TelaahanStafService {
    List<TelaahanStaf> getByNipPembuatSurat(String nipPembuatSurat);

    void createTelaahanStaf(TelaahanStaf telaahanStaf);

    TelaahanStaf getTelaahanStaf(String kdTelaahanStaf);
}
