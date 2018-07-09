package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratDisposisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 19/11/17.
 */
@Repository
public interface SuratDisposisiDao extends JpaRepository<SuratDisposisi, String> {
    void deleteByNoSurat(String noSurat);
    List<SuratDisposisi> findByKdSuratPenugasanAndJenisSuratPenugasan(String kdSuratPenugasan, Integer jenisSuratPenugasan);
}
