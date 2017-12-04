package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratDisposisi;

/**
 * Created by bagus on 19/11/17.
 */
public interface SuratDisposisiService {
    void create(SuratDisposisi suratDisposisi);
    void delete(String suratDisposisiId);
}
