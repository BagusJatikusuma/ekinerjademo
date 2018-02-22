package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratTugas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by bagus on 29/01/18.
 */
@Repository
public interface SuratTugasDao
        extends JpaRepository<SuratTugas, String> {
    @Query("select distinct st from SuratTugas st " +
            "left join fetch st.suratTugasPejabat " +
            "left join fetch st.suratTugasNonPejabat " +
            "left join fetch st.targetSuratTugasPegawaiSet " +
            "left join fetch st.targetSuratTugasPejabatSet " +
            "left join fetch st.tembusanSuratTugasSet " +
            "where st.kdUnitKerja = ?1")
    Set<SuratTugas> findByKdUnitKerja(String kdUnitKerja);
    @Query("select distinct st from SuratTugas st " +
            "left join fetch st.suratTugasPejabat " +
            "left join fetch st.suratTugasNonPejabat " +
            "left join fetch st.targetSuratTugasPegawaiSet " +
            "left join fetch st.targetSuratTugasPejabatSet " +
            "left join fetch st.tembusanSuratTugasSet " +
            "where st.nipPembuat = ?1")
    Set<SuratTugas> findByNipPembuat(String nipPembuat);
    @Query("select distinct st from SuratTugas st " +
            "left join fetch st.suratTugasPejabat " +
            "left join fetch st.suratTugasNonPejabat " +
            "left join fetch st.targetSuratTugasPegawaiSet " +
            "left join fetch st.targetSuratTugasPejabatSet " +
            "left join fetch st.tembusanSuratTugasSet " +
            "where st.nomorTahun = ?1")
    Set<SuratTugas> findByNomorTahun(Integer nomorTahun);

    @Query("select st from SuratTugas st " +
            "left join fetch st.suratTugasPejabat " +
            "left join fetch st.suratTugasNonPejabat " +
            "left join fetch st.targetSuratTugasPegawaiSet " +
            "left join fetch st.targetSuratTugasPejabatSet " +
            "left join fetch st.tembusanSuratTugasSet " +
            "where st.kdSuratTugas = ?1")
    SuratTugas findByKdSuratTugas(String kdSuratTugas);

    @Query("select st from SuratTugas st " +
            "where st.pathPenilaian like concat(?1,'%')")
    Set<SuratTugas> findByLastTree(String kdSuratTugasRoot);
}
