package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratUndangan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratUndanganId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface TembusanSuratUndanganDao
        extends JpaRepository<TembusanSuratUndangan, TembusanSuratUndanganId> {

}
