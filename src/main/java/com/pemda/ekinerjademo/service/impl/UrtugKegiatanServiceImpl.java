package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UrtugKegiatanDao;
import com.pemda.ekinerjademo.service.UrtugKegiatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bagus on 05/10/17.
 */
@Service("UrtugKegiatanService")
@Transactional("ekinerjaTransactionManager")
public class UrtugKegiatanServiceImpl implements UrtugKegiatanService {
    @Autowired
    private UrtugKegiatanDao urtugKegiatanDao;

    @Override
    public List<UrtugKegiatan> findAll() {
        return urtugKegiatanDao.findAll();
    }

    @Override
    public List<UrtugKegiatan> findAllByUraianTugasJabatan(String kdUrtug, String kdJabatan, String kdJenisUrtug, Integer tahunUrtug) {
        return urtugKegiatanDao
                .findByUrtugKegiatanId_KdUrtugAndUrtugKegiatanId_KdJabatanAndUrtugKegiatanId_KdJenisUrtugAndUrtugKegiatanId_TahunUrtug(
                        kdUrtug,
                        kdJabatan,
                        kdJenisUrtug,
                        tahunUrtug);
    }

    @Override
    public List<UrtugKegiatan> findByProgramAndUrtug(String kdUrtug,
                                                    String kdJabatan,
                                                    String kdJenisUrtug,
                                                    Integer tahunUrtug,
                                                    Integer kdUrusan,
                                                    Integer kdBidang,
                                                    Integer kdUnit,
                                                    Integer kdSub,
                                                    Integer tahun,
                                                    Integer kdProg,
                                                    Integer idProg) {
        return urtugKegiatanDao
                .findByProgramAndUrtug(kdUrtug, kdJabatan, kdJenisUrtug, tahunUrtug, kdUrusan, kdBidang, kdUnit, kdSub, tahun, kdProg, idProg);
    }

    @Override
    public UrtugKegiatan find(UrtugKegiatanId urtugKegiatanId) {
        return urtugKegiatanDao.findByUrtugKegiatanId(urtugKegiatanId);
    }

    @Override
    public void save(UrtugKegiatan urtugKegiatan) {
        urtugKegiatanDao.save(urtugKegiatan);
    }

    @Override
    public void update(UrtugKegiatan urtugKegiatan) {
        urtugKegiatanDao.save(urtugKegiatan);
    }

    @Override
    public void delete(UrtugKegiatanId urtugKegiatanId) {
        urtugKegiatanDao.deleteByUrtugKegiatanId(urtugKegiatanId);
    }

    @Override
    public List<UrtugKegiatan> findByPegawaiAndUnitKerja(String nipPegawai, UnitKerjaKegiatan unitKerjaKegiatan) {

        return urtugKegiatanDao.findByPegawawiAndUnitKerja(nipPegawai,
                                                            unitKerjaKegiatan.getKdUrusan(),
                                                            unitKerjaKegiatan.getKdBidang(),
                                                            unitKerjaKegiatan.getKdUnit());
    }

    @Override
    public List<UrtugKegiatan> findByPegawaiApproval(String nipPegawai, Integer tahunUrtug) {
        return urtugKegiatanDao.findByPegawaiApproval(nipPegawai, tahunUrtug);
    }

    @Override
    public List<UrtugKegiatan> findByUraianTugasJabatanAndNip(String kdUrtug, String kdJabatan, String kdJenisUrtug, Integer tahunUrtug, String nipPegawai) {
        return urtugKegiatanDao.findByUrtugJabatanJenisAndNipPegawai(kdUrtug, kdJabatan, kdJenisUrtug, tahunUrtug, nipPegawai);
    }
}
