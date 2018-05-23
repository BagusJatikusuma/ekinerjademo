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
    @Query("select distinct m from Memorandum m " +
            "left join fetch m.memorandumNonPejabat " +
            "left join fetch m.memorandumPejabat " +
            "left join fetch m.tembusanMemorandumList " +
            "where m.nipPembuatSurat = ?1")
    List<Memorandum> findByNipPembuatSurat(String nipPembuatSurat);
    @Query("select distinct m from Memorandum m " +
            "left join fetch m.memorandumNonPejabat " +
            "left join fetch m.memorandumPejabat " +
            "left join fetch m.tembusanMemorandumList " +
            "where m.nipPenerimaMemorandum = ?1")
    List<Memorandum> findByNipPenerimaMemorandum(String nipPenerima);

    @Query("select mm from Memorandum mm " +
            "where mm.pathPenilaian like concat(?1,'%')")
    List<Memorandum> findByLastTree(String kdMemorandumRoot);
    @Query("select distinct m from Memorandum m " +
            "left join fetch m.memorandumNonPejabat " +
            "left join fetch m.memorandumPejabat " +
            "left join fetch m.tembusanMemorandumList " +
            "where m.kdMemorandum = ?1")
    Memorandum findByKdMemorandum(String kdMemorandum);

    @Query("select distinct m from Memorandum m " +
            "left join fetch m.memorandumNonPejabat " +
            "left join fetch m.memorandumPejabat " +
            "left join fetch m.tembusanMemorandumList " +
            "where m.kdUnitKerjaTarget = ?1")
    List<Memorandum> findByUnitKerjaTarget(String kdUnitKerjaTarget);

    @Query("select distinct m from Memorandum m " +
            "left join fetch m.memorandumNonPejabat " +
            "left join fetch m.memorandumPejabat " +
            "left join fetch m.tembusanMemorandumList " +
            "where m.kdUnitKerja = ?1 " +
            "and m.approvalPenandatangan = 1")
    List<Memorandum> findByApproval(String kdUnitKerjaTarget);

    @Query("select distinct m from Memorandum m " +
            "left join fetch m.memorandumNonPejabat " +
            "left join fetch m.memorandumPejabat " +
            "left join fetch m.tembusanMemorandumList " +
            "where m.kdUnitKerja = ?1 " +
            "and m.approvalSekretaris = 1")
    List<Memorandum> findBySekretarisApproval(String kdUnitKerja);
}
