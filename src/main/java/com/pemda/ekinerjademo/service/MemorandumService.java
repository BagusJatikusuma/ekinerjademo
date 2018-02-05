package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;

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
    void openTembusanMemorandum(TembusanMemorandumId id);
    void openMemorandumByPenilai(String kdMemorandum);

    void update(Memorandum memorandum);

    Memorandum getByKdMemorandum(String kdMemorandum);
}
