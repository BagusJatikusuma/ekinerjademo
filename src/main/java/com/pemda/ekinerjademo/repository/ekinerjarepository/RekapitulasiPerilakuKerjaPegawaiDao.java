package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.RekapitulasiPerilakuKerjaPegawai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bayu on 18/07/18.
 */
public interface RekapitulasiPerilakuKerjaPegawaiDao extends JpaRepository<RekapitulasiPerilakuKerjaPegawai, String> {
    List<RekapitulasiPerilakuKerjaPegawai> findByBulanTahunRekapulasiAndKdUnitKerja(Long bulanTahunRekapitulasi, String kdUnitKerja);
}
