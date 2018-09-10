package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.RekapitulasiPerilakuKerjaPegawai;

import java.util.List;

/**
 * Created by bayu on 18/07/18.
 */
public interface RekapitulasiPerilakuKerjaPegawaiService {

    void createRekaptulasiPerilakuKerjaPegawai(RekapitulasiPerilakuKerjaPegawai rekapitulasiPerilakuKerjaPegawai);

    List<RekapitulasiPerilakuKerjaPegawai> getRekaptulasiPerilakuKerjaPegawai(String kdUnitKerja, long bulanTahunRekapitulasi);
}
