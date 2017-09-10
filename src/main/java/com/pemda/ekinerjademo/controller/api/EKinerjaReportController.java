package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatan;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.UraianTugasJabatanService;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 10/09/17.
 */
@RestController
@RequestMapping(value = "/api")
public class EKinerjaReportController {
    public static final Logger LOGGER = LoggerFactory.getLogger(EKinerjaReportController.class);

    @Autowired
    private QutPegawaiService qutPegawaiService;

    @Autowired
    private UraianTugasJabatanService uraianTugasJabatanService;

    @RequestMapping(value = "/get-urtug-by-nip/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getUraianTugasList(@PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get "+nipPegawai+" uraian tugas ");

        //set this logic in difference layer
        QutPegawai qutPegawai = qutPegawaiService.getQutPegawai(nipPegawai);
        String namaPegawai = qutPegawai.getNama();
        JabatanWrapper jabatan =
                new JabatanWrapper(qutPegawai.getKdJabatan(),qutPegawai.getJabatan());
        UnitOrganisasiWrapper unitOrganisasi =
                new UnitOrganisasiWrapper("dummyId", "dummyOrganisasi");
        UnitKerjaWrapper unitKerja =
                new UnitKerjaWrapper(qutPegawai.getKdUnitKerja(), qutPegawai.getUnitKerja());
        List<UraianTugasJabatan> uraianTugasJabatanList =
                uraianTugasJabatanService.getUraianTugasJabatanByJabatan(qutPegawai.getKdJabatan());
        List<UraianTugasWrapper> uraianTugasWrapperList = new ArrayList<>();

        for (UraianTugasJabatan uraianTugasJabatan : uraianTugasJabatanList) {
            uraianTugasWrapperList
                    .add(new UraianTugasWrapper(
                            uraianTugasJabatan.getUraianTugas().getKdUrtug(),
                            uraianTugasJabatan.getUraianTugas().getDeskripsi(),
                            uraianTugasJabatan.getUraianTugas().getSatuan(),
                            uraianTugasJabatan.getUraianTugas().getVolumeKerja(),
                            uraianTugasJabatan.getUraianTugas().getNormaWaktu(),
                            uraianTugasJabatan.getUraianTugas().getBebanKerja(),
                            uraianTugasJabatan.getUraianTugas().getPeralatan(),
                            uraianTugasJabatan.getUraianTugas().getKeterangan()));
        }

        UraianTugasEKinerjaWrapper uraianTugasEKinerjaWrapper =
                new UraianTugasEKinerjaWrapper(
                        nipPegawai,
                        namaPegawai,
                        jabatan,
                        unitOrganisasi,
                        unitKerja,
                        uraianTugasWrapperList);

        return new ResponseEntity<Object>(uraianTugasEKinerjaWrapper, HttpStatus.OK);
    }
}
