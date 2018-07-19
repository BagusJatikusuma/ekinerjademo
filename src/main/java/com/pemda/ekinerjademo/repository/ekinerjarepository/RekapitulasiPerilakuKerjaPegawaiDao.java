package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.RekapitulasiPerilakuKerjaPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.RekapitulasiPerilakuKerjaPegawaiId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bayu on 18/07/18.
 */
public interface RekapitulasiPerilakuKerjaPegawaiDao extends JpaRepository<RekapitulasiPerilakuKerjaPegawai, RekapitulasiPerilakuKerjaPegawaiId> {
    List<RekapitulasiPerilakuKerjaPegawai> findByRekapitulasiPerilakuKerjaPegawaiId_BulanTahunRekapulasiAndKdUnitKerja(Long bulanTahunRekapitulasi, String kdUnitKerja);
}
