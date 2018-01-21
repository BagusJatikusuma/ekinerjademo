package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratDinas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratDinasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface TembusanSuratDinasDao
        extends JpaRepository<TembusanSuratDinas, TembusanSuratDinasId> {

}
