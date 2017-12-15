package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.model.simdamodel.TaProgramId;
import com.pemda.ekinerjademo.wrapper.input.UrtugKegiatanPegawaiApprovalInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.UrtugKegiatanPegawaiByUrtugJabatanWrapper;

import java.util.List;

/**
 * Created by bagus on 06/10/17.
 */
public interface UrtugKegiatanPegawaiService {
    List<UrtugKegiatanPegawai> findByUrtugJabatan(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId);
    List<UrtugKegiatanPegawai> findByNipPegawai(String nipPegawai);
    List<UrtugKegiatanPegawai> findByUrtugKegiatan(UrtugKegiatanId urtugKegiatanId);
    List<UrtugKegiatanPegawai> findByUrtugJabatanAndNipPegawai(
            UraianTugasJabatanId uraianTugasJabatanId, String nipPegawai);
    List<UrtugKegiatanPegawai> findByUrtugJabatanTahunAndNipePegawai(
            UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId, String nipPegawai);
    List<UrtugKegiatanPegawai> findByUnitKerja(String unitKerja);

    List<UrtugKegiatanPegawai> findByProgram(Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg);
    List<UrtugKegiatanPegawai> findByProgramAndUrtugJabatan(String kdUrtug, String kdJabatan, String kdJenisUrtug, Integer tahunUrtug, Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg);
    List<UrtugKegiatanPegawai> findByProgramAndPegawai(Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg, String nip);


    void save(UrtugKegiatanPegawai urtugKegiatanPegawai);
    void update(UrtugKegiatanPegawai urtugKegiatanPegawai);
    void delete(UrtugKegiatanPegawaiId urtugKegiatanPegawaiId);
    void changeStatusApprovalUrtugKegiatan(List<UrtugKegiatanPegawaiApprovalInputWrapper> inputWrapperList);

}
