package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatanId;
import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.KegiatanPenanggungJawabProjection;

import java.util.List;

/**
 * Created by bagus on 07/02/18.
 */
public interface PenanggungJawabKegiatanService {
    void create(PenanggungJawabKegiatan penanggungJawabKegiatan);
    List<PenanggungJawabKegiatan> getByUnitKerja(Integer kdUrusan, Integer kdBidang, Integer kdUnit);
    List<PenanggungJawabKegiatan> getByKegiatan(Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg, Integer kdKeg);
    void delete(PenanggungJawabKegiatanId id);

    List<KegiatanPenanggungJawabProjection> getKegiatanByUnitKerja(UnitKerjaKegiatan unitKerjaKegiatan);
    List<KegiatanPenanggungJawabProjection> getKegiatanProjectionByPegawai(String nipPegawai);
    List<PenanggungJawabKegiatan> getKegiatanUnitKerja(UnitKerjaKegiatan unitKerjaKegiatan);
    List<PenanggungJawabKegiatan> getByKegiatanPendingPegawai(String nipPegawai);
    List<PenanggungJawabKegiatan> getByPegawai(Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg, Integer kdKeg, String nipPegawai);
}
