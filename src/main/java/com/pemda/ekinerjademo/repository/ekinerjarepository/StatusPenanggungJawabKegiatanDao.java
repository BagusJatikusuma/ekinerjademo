package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.StatusPenanggungJawabKegiatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 05/10/17.
 */
@Repository
public interface StatusPenanggungJawabKegiatanDao extends JpaRepository<StatusPenanggungJawabKegiatan, String> {
    StatusPenanggungJawabKegiatan findByKdStatus(String kdStatus);
    void deleteByKdStatus(String kdStatus);
}
