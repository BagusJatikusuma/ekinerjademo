package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatanId;
import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.KegiatanPenanggungJawabProjection;
import com.pemda.ekinerjademo.repository.ekinerjarepository.PenanggungJawabKegiatanDao;
import com.pemda.ekinerjademo.service.PenanggungJawabKegiatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 07/02/18.
 */
@Service("PenanggungJawabKegiatanService")
@Transactional("ekinerjaTransactionManager")
public class PenanggungJawabKegiatanServiceImpl implements PenanggungJawabKegiatanService {
    @Autowired private PenanggungJawabKegiatanDao penanggungJawabKegiatanDao;

    @Override
    public void create(PenanggungJawabKegiatan penanggungJawabKegiatan) {
        penanggungJawabKegiatanDao.save(penanggungJawabKegiatan);
    }

    @Override
    public List<PenanggungJawabKegiatan> getByUnitKerja(Integer kdUrusan, Integer kdBidang, Integer kdUnit) {
        return penanggungJawabKegiatanDao.findByUnitKerja(kdUrusan, kdBidang, kdUnit);
    }

    @Override
    public List<PenanggungJawabKegiatan> getByKegiatan(
            Integer kdUrusan,
            Integer kdBidang,
            Integer kdUnit,
            Integer kdSub,
            Integer tahun,
            Integer kdProg,
            Integer idProg,
            Integer kdKeg) {
        return penanggungJawabKegiatanDao.findByKegiatan(
                                            kdUrusan,
                                            kdBidang,
                                            kdUnit,
                                            kdSub,
                                            tahun,
                                            kdProg,
                                            idProg,
                                            kdKeg);
    }

    @Override
    public void delete(PenanggungJawabKegiatanId id) {
        penanggungJawabKegiatanDao.delete(id);
    }

    @Override
    public List<KegiatanPenanggungJawabProjection> getKegiatanByUnitKerja(UnitKerjaKegiatan unitKerjaKegiatan) {
        return penanggungJawabKegiatanDao
                    .findKegiatanUnitKerjaOnly(
                            unitKerjaKegiatan.getKdUrusan(),
                            unitKerjaKegiatan.getKdBidang(),
                            unitKerjaKegiatan.getKdUnit());
    }

    @Override
    public List<KegiatanPenanggungJawabProjection> getKegiatanProjectionByPegawai(String nipPegawai) {
        return penanggungJawabKegiatanDao.findProjectionByPegawaiOnly(nipPegawai);
    }

    @Override
    public List<PenanggungJawabKegiatan> getKegiatanUnitKerja(UnitKerjaKegiatan unitKerjaKegiatan) {
        return penanggungJawabKegiatanDao.findByUnitKerja(unitKerjaKegiatan.getKdUrusan(), unitKerjaKegiatan.getKdBidang(), unitKerjaKegiatan.getKdUnit());
    }

    @Override
    public List<PenanggungJawabKegiatan> getByKegiatanPendingPegawai(String nipPegawai) {
        return penanggungJawabKegiatanDao.findByPegawaiPending(nipPegawai);
    }

    @Override
    public List<PenanggungJawabKegiatan> getByPegawai(Integer kdUrusan,
                                                      Integer kdBidang,
                                                      Integer kdUnit,
                                                      Integer kdSub,
                                                      Integer tahun,
                                                      Integer kdProg,
                                                      Integer idProg,
                                                      Integer kdKeg,
                                                      String nipPegawai) {
        return penanggungJawabKegiatanDao.findByPegawai(kdUrusan,
                                                        kdBidang,
                                                        kdUnit,
                                                        kdSub,
                                                        tahun,
                                                        nipPegawai);
    }


}
