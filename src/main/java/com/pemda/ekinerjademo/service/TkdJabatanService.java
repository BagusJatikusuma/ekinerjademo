package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;

import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
public interface TkdJabatanService {
    TkdJabatan getTkdJabatan(String kdJabatan);
    List<TkdJabatan> getAll();
}
