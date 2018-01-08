package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.Memorandum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 05/01/18.
 */
@Repository
public interface MemorandumDao extends JpaRepository<Memorandum, String>{
    List<Memorandum> findByKdUnitKerja(String kdUnitKerja);
    List<Memorandum> findByNomorTahun(Integer nomorTahun);
    @Query("select m from Memorandum m " +
            "left join fetch m.memorandumNonPejabat " +
            "left join fetch m.memorandumPejabat " +
            "left join fetch m.tembusanMemorandumList " +
            "where m.nipPembuatSurat = ?1")
    List<Memorandum> findByNipPembuatSurat(String nipPembuatSurat);
    @Query("select m from Memorandum m " +
            "left join fetch m.memorandumNonPejabat " +
            "left join fetch m.memorandumPejabat " +
            "left join fetch m.tembusanMemorandumList " +
            "where m.nipPenerimaMemorandum = ?1")
    List<Memorandum> findByNipPenerimaMemorandum(String nipPenerima);
    @Query("select m from Memorandum m " +
            "left join fetch m.memorandumNonPejabat " +
            "left join fetch m.memorandumPejabat " +
            "left join fetch m.tembusanMemorandumList " +
            "where m.kdMemorandum = ?1")
    Memorandum findByKdMemorandum(String kdMemorandum);
}
