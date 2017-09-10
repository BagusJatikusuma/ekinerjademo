package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugas;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatan;

import com.pemda.ekinerjademo.service.*;
//import com.pemda.ekinerjademo.service.UraianTugasJabatanService;
//import com.pemda.ekinerjademo.service.UraianTugasService;

import com.pemda.ekinerjademo.wrapper.input.UraianTugasInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 10/09/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class UraianTugasController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UraianTugasController.class);

    @Autowired
    private QutPegawaiService qutPegawaiService;
    @Autowired
    private UraianTugasJabatanService uraianTugasJabatanService;
    @Autowired
    private UraianTugasService uraianTugasService;

    @CrossOrigin(allowCredentials = "false")
    @RequestMapping(value = "/get-urtug-by-nip/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getUraianTugasList(@PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get "+nipPegawai+" uraian tugas ");

        //set this logic in difference layer (databindirg/dataconvert layer)
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

    @CrossOrigin(allowCredentials = "false")
    @RequestMapping(value = "/create-urtug", method = RequestMethod.POST)
    @Transactional //only for development phase
    ResponseEntity<?> saveUraianTugas(@RequestBody UraianTugasInputWrapper uraianTugasInputWrapper) {
        LOGGER.info("create new uraian tugas to database");
        //set this logic in difference layer (databindirg/dataconvert layer)
        UraianTugas uraianTugas = new UraianTugas();

        String newKdUrtug = String.valueOf(new Date().getTime());
        uraianTugas
                .setKdUrtug(newKdUrtug);
        uraianTugas.setDeskripsi(uraianTugasInputWrapper.getDeskripsi());
        uraianTugas.setSatuan(uraianTugasInputWrapper.getSatuan());
        uraianTugas.setVolumeKerja(uraianTugasInputWrapper.getVolumeKerja());
        uraianTugas.setNormaWaktu(uraianTugasInputWrapper.getNormaWaktu());
        uraianTugas.setBebanKerja(uraianTugasInputWrapper.getBebanKerja());
        uraianTugas.setPeralatan(uraianTugasInputWrapper.getPeralatan());
        uraianTugas.setKeterangan(uraianTugasInputWrapper.getKeterangan());

        uraianTugasService.save(uraianTugas);

        return new ResponseEntity<Object>(new CustomMessage("urtug created"), HttpStatus.OK);
    }
    @RequestMapping(value = "/update-urtug", method = RequestMethod.PUT)
    @Transactional //only for development phase
    ResponseEntity<?> updateUraianTugas(@RequestBody UraianTugas uraianTugas) {
        LOGGER.info("update "+uraianTugas.getKdUrtug());
        return null;
    }


    @RequestMapping(value = "/delete-urtug/{kdUrtug}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteUraianTugas(@PathVariable("kdUrtug") String kdUrtug) {
        LOGGER.info("delete "+kdUrtug);
        uraianTugasService.delete(kdUrtug);
        return new ResponseEntity<Object>(new CustomMessage("urtug deleted"), HttpStatus.OK);
    }

    @CrossOrigin(allowCredentials = "false")
    @RequestMapping(value = "/get-all-urtug", method = RequestMethod.GET)
    ResponseEntity<?> getAllUraianTugas(){
        List<UraianTugasWrapper> uraianTugasWrappers = new ArrayList<>();
        List<UraianTugas> uraianTugases= uraianTugasService.getAllUraianTugas();

        for (UraianTugas uraianTugas : uraianTugases){
            uraianTugasWrappers.add(new UraianTugasWrapper(
                    uraianTugas.getKdUrtug(),
                    uraianTugas.getDeskripsi(),
                    uraianTugas.getSatuan(),
                    uraianTugas.getVolumeKerja(),
                    uraianTugas.getNormaWaktu(),
                    uraianTugas.getBebanKerja(),
                    uraianTugas.getPeralatan(),
                    uraianTugas.getKeterangan()
            ));
        }
        return new ResponseEntity<Object>(uraianTugasWrappers, HttpStatus.OK);
    }

}
