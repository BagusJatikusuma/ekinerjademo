package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.model.simdamodel.TaProgramId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UrtugKegiatanPegawaiDao;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.service.UrtugKegiatanPegawaiService;
import com.pemda.ekinerjademo.wrapper.input.UrtugKegiatanPegawaiApprovalInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.UrtugKegiatanPegawaiByUrtugJabatanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 06/10/17.
 */
@Service("UrtugKegiatanPegawaiService")
@Transactional("ekinerjaTransactionManager")
public class UrtugKegiatanPegawaiServiceImpl implements UrtugKegiatanPegawaiService {
    @Autowired
    private UrtugKegiatanPegawaiDao urtugKegiatanPegawaiDao;

    @Autowired
    @Qualifier("TkdJabatanCloneService")
    private TkdJabatanService tkdJabatanService;

    @Override
    public List<UrtugKegiatanPegawai> findByUrtugJabatan(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId) {
        return urtugKegiatanPegawaiDao
                .findUrtugKegiatanPegawaiByUrtugJabatan(uraianTugasJabatanJenisUrtugId);
    }

    @Override
    public List<UrtugKegiatanPegawai> findByNipPegawai(String nipPegawai) {
        return urtugKegiatanPegawaiDao
                .findUrtugKegiatanPegawaiByNipPegawai(nipPegawai);
    }

    @Override
    public List<UrtugKegiatanPegawai> findByUrtugKegiatan(UrtugKegiatanId urtugKegiatanId) {
        return urtugKegiatanPegawaiDao
                .findUrtugKegiatanPegawaiByUrtugKegiatan(urtugKegiatanId);
    }

    @Override
    public List<UrtugKegiatanPegawai> findByUrtugJabatanAndNipPegawai(UraianTugasJabatanId uraianTugasJabatanId, String nipPegawai) {
        return urtugKegiatanPegawaiDao
                .findUrtugKegiatanPegawaiByUrtugJabatanAndNip(uraianTugasJabatanId, nipPegawai);
    }

    @Override
    public List<UrtugKegiatanPegawai> findByUrtugJabatanTahunAndNipePegawai(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId, String nipPegawai) {
        return urtugKegiatanPegawaiDao
                .findUrtugKegiatanPegawaiByUrtugJabatanTahunAndNip(uraianTugasJabatanJenisUrtugId, nipPegawai);
    }

    //terdapat revisi 14 januari 2018
    //algoritma hanya berlaku hingga refactor database (penambahan field kdUnitKerja) selesai diimplement
    @Override
    public List<UrtugKegiatanPegawai> findByUnitKerja(String unitKerja) {
        //mulai algoritma revisi
        List<TkdJabatan> tkdJabatanList
                = tkdJabatanService.getJabatanByUnitKerja(unitKerja);

        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList = urtugKegiatanPegawaiDao.findAll();
        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiFilterList = new ArrayList<>();

        for (UrtugKegiatanPegawai urtugKegiatanPegawai
                : urtugKegiatanPegawaiList) {
            for (TkdJabatan tkdJabatan : tkdJabatanList) {
                if (urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdJabatan()
                        .equals(tkdJabatan.getKdJabatan())) {
                    urtugKegiatanPegawaiFilterList.add(urtugKegiatanPegawai);
                    break;

                }

            }

        }

        return urtugKegiatanPegawaiFilterList;
        //selesai algoritma revisi

//        return urtugKegiatanPegawaiDao
//                .findByUnitKerja(unitKerja);
    }

    @Override
    public List<UrtugKegiatanPegawai> findByProgram(
            Integer kdUrusan,
            Integer kdBidang,
            Integer kdUnit,
            Integer kdSub,
            Integer tahun,
            Integer kdProg,
            Integer idProg) {
        return urtugKegiatanPegawaiDao.findByProgram(kdUrusan, kdBidang, kdUnit, kdSub, tahun, kdProg, idProg);
    }

    @Override
    public List<UrtugKegiatanPegawai> findByProgramAndUrtugJabatan(String kdUrtug, String kdJabatan, String kdJenisUrtug, Integer tahunUrtug, Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg) {
        return urtugKegiatanPegawaiDao.findByProgramAndUrtugJabatan(kdUrtug,kdJabatan, kdJenisUrtug, tahunUrtug, kdUrusan, kdBidang, kdUnit, kdSub, tahun, kdProg, idProg);
    }

    @Override
    public List<UrtugKegiatanPegawai> findByProgramAndPegawai(
            Integer kdUrusan,
            Integer kdBidang,
            Integer kdUnit,
            Integer kdSub,
            Integer tahun,
            Integer kdProg,
            Integer idProg,
            String nip) {
        return urtugKegiatanPegawaiDao.findByProgramAndPegawai(kdUrusan, kdBidang, kdUnit, kdSub, tahun, kdProg, idProg, nip);
    }

    @Override
    public void save(UrtugKegiatanPegawai urtugKegiatanPegawai) {
        urtugKegiatanPegawaiDao.save(urtugKegiatanPegawai);
    }

    @Override
    public void update(UrtugKegiatanPegawai urtugKegiatanPegawai) {
        urtugKegiatanPegawaiDao.save(urtugKegiatanPegawai);
    }

    @Override
    public void delete(UrtugKegiatanPegawaiId urtugKegiatanPegawaiId) {
        urtugKegiatanPegawaiDao.deleteByUrtugKegiatanPegawaiId(urtugKegiatanPegawaiId);
    }

    @Override
    public void changeStatusApprovalUrtugKegiatan(List<UrtugKegiatanPegawaiApprovalInputWrapper> inputWrapperList) {
        for (UrtugKegiatanPegawaiApprovalInputWrapper inputWrapper : inputWrapperList) {
            List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList
                    = urtugKegiatanPegawaiDao
                        .findUrtugKegiatanPegawaiByUrtugJabatanTahunAndNip(
                            new UraianTugasJabatanJenisUrtugId(
                                    inputWrapper.getKdUrtug(),
                                    inputWrapper.getKdJabatan(),
                                    inputWrapper.getKdJenisUrtug(),
                                    inputWrapper.getTahunUrtug()),
                            inputWrapper.getNipPegawai());

            for (UrtugKegiatanPegawai urtugKegiatanPegawai : urtugKegiatanPegawaiList) {
                urtugKegiatanPegawai.setStatusApproval(inputWrapper.getStatusApproval());
                update(urtugKegiatanPegawai);
            }

        }

    }

}
