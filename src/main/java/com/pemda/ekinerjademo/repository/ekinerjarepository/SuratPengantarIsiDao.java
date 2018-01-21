package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPengantarIsi;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPengantarIsiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface SuratPengantarIsiDao
        extends JpaRepository<SuratPengantarIsi, SuratPengantarIsiId> {
}
