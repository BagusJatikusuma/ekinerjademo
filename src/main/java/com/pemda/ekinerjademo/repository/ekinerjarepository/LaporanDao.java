package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.Laporan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bayu on 07/12/17.
 */
public interface LaporanDao extends JpaRepository<Laporan, String> {
}
