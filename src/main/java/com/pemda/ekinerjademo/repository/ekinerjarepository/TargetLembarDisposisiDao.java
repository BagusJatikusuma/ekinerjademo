package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TargetLembarDisposisi;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetLembarDisposisiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 19/11/17.
 */
@Repository
public interface TargetLembarDisposisiDao extends JpaRepository<TargetLembarDisposisi, TargetLembarDisposisiId> {

}
