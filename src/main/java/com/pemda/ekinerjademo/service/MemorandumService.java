package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.Memorandum;
import com.pemda.ekinerjademo.model.ekinerjamodel.MemorandumNonPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.MemorandumPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanMemorandum;

import java.util.List;

/**
 * Created by bayu on 05/01/18.
 */
public interface MemorandumService {
    List<Memorandum> getByNipPembuat(String nipPembuat);
    List<Memorandum> getByNipTarget(String nipTarget);
    List<TembusanMemorandum> getTembusanMemorandum(String kdJabatanTembusan);

    void createMemorandum(Memorandum memorandum);
    void createMemorandumNonPejabat(MemorandumNonPejabat memorandumNonPejabat);
    void createMemorandumPejabat(MemorandumPejabat memorandumPejabat);
    void createTembusanMemorandum(List<TembusanMemorandum> tembusanMemorandumList);

    void openMemorandum(String kdMemorandum);
    void openMemorandumByPenilai(String kdMemorandum);

    void update(Memorandum memorandum);

    Memorandum getByKdMemorandum(String kdMemorandum);
}
