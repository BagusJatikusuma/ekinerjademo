package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulananAjuan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulananAjuanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UraianTugasPegawaiBulananAjuanDao
        extends JpaRepository<UraianTugasPegawaiBulananAjuan, UraianTugasPegawaiBulananAjuanId> {

}
