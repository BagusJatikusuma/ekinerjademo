package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.service.UraianTugasJabatanService;
import com.pemda.ekinerjademo.service.UraianTugasService;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasJabatanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 26/09/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class UraianTugasJabatanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UraianTugasJabatanController.class);

    private TkdJabatanService tkdJabatanService;
    private UraianTugasJabatanService uraianTugasJabatanService;
    private UraianTugasService uraianTugasService;

    @Autowired
    public UraianTugasJabatanController(
            @Qualifier("TkdJabatanCloneService") TkdJabatanService tkdJabatanService,
            UraianTugasJabatanService uraianTugasJabatanService,
            UraianTugasService uraianTugasService) {
        this.tkdJabatanService = tkdJabatanService;
        this.uraianTugasJabatanService = uraianTugasJabatanService;
        this.uraianTugasService = uraianTugasService;
    }

    @RequestMapping(value = "/get-uraian-tugas-by-jabatan/{kdJabatan}", method = RequestMethod.GET)
    @Transactional
    ResponseEntity<?> getUraianTugasByJabatan(@PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get uraian tugas list by jabatan");

        LOGGER.info("fetch data jabatan by kd jabatan");
        TkdJabatan tkdJabatan = tkdJabatanService.getTkdJabatan(kdJabatan);
        LOGGER.info("finish fetch data jabatan by kd jabatan");

        List<UraianTugasJabatanWrapper> uraianTugasJabatanWrapperList =
                new ArrayList<>();
        UraianTugasJabatanWrapper uraianTugasJabatanWrapper
                = new UraianTugasJabatanWrapper();
        List<UraianTugasJabatan> uraianTugasJabatanList =
                uraianTugasJabatanService.getUraianTugasJabatanByJabatan(kdJabatan);
        List<UraianTugas> uraianTugasList =
                uraianTugasService.getAllUraianTugas();
        List<UraianTugasJenisWrapper> jabatanUraianTugasWrapperList =
                new ArrayList<>();
        List<UraianTugasWrapper> notJabatanUraianTugasWrapperList
                = new ArrayList<>();

        if (uraianTugasJabatanList.isEmpty()) {
            jabatanUraianTugasWrapperList = null;
            notJabatanUraianTugasWrapperList
                    = new ArrayList<>();

            for (UraianTugas uraianTugas : uraianTugasList) {
                notJabatanUraianTugasWrapperList
                        .add(new UraianTugasWrapper(
                                uraianTugas.getKdUrtug(),
                                uraianTugas.getDeskripsi(),
                                uraianTugas.getVolume(),
                                uraianTugas.getSatuanVolume(),
                                uraianTugas.getJabatan(),
                                uraianTugas.getUnitKerja()));
            }

        } else {

            for (UraianTugas uraianTugas : uraianTugasList) {
                boolean found = false;
                for (UraianTugasJabatan uraianTugasJabatan : uraianTugasJabatanList) {
                    if (uraianTugas.getKdUrtug()
                            .equals(uraianTugasJabatan.getUraianTugas().getKdUrtug())) {
                        LOGGER.info(uraianTugas.getKdUrtug()+" sama "+uraianTugasJabatan.getUraianTugas().getKdUrtug());

                        jabatanUraianTugasWrapperList
                                .add(new UraianTugasJenisWrapper(
                                        new UraianTugasWrapper(
                                                uraianTugas.getKdUrtug(),
                                                uraianTugas.getDeskripsi()),
                                        uraianTugas.getVolume(),
                                        uraianTugas.getSatuanVolume(),
                                        uraianTugasJabatan.getKualitas(),
                                        uraianTugasJabatan.getWaktu(),
                                        uraianTugasJabatan.getBiaya()
                                ));

                        found = true;
                        break;
                    }

                }

                if(!found) {
                    notJabatanUraianTugasWrapperList
                            .add(new UraianTugasWrapper(
                                    uraianTugas.getKdUrtug(),
                                    uraianTugas.getDeskripsi(),
                                    uraianTugas.getVolume(),
                                    uraianTugas.getSatuanVolume(),
                                    uraianTugas.getJabatan(),
                                    uraianTugas.getUnitKerja()));
                }

            }

        }

        uraianTugasJabatanWrapper.setKdJabatan(tkdJabatan.getKdJabatan());
        uraianTugasJabatanWrapper.setJabatan(tkdJabatan.getJabatan());
        uraianTugasJabatanWrapper.setJabatanUraianTugasList(jabatanUraianTugasWrapperList);
//        uraianTugasJabatanWrapper.setNotJabatanUraianTugasList(notJabatanUraianTugasWrapperList);

        LOGGER.info("finish");
        return new ResponseEntity<Object>(uraianTugasJabatanWrapper, HttpStatus.OK);
    }


    @RequestMapping(value = "/get-uraian-tugas-jabatan-by-jabatan/{kdJabatan}", method = RequestMethod.GET)
    @Transactional
    ResponseEntity<?> getUraianTugasJabatanByJabatan(@PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("Get uraian tugas jabatan by jabatan");

        List<UraianTugasJabatanOutputWrapper> urtugJabatanWrapperList
                = new ArrayList<>();
        List<UraianTugasJabatan> uraianTugasJabatanList =
                uraianTugasJabatanService.getUraianTugasJabatanByJabatan(kdJabatan);

        for (UraianTugasJabatan uraianTugasJabatan : uraianTugasJabatanList) {
            if(uraianTugasJabatan.getUraianTugasJabatanJenisUrtugList().isEmpty()){
                urtugJabatanWrapperList
                        .add(new UraianTugasJabatanOutputWrapper(
                                uraianTugasJabatan.getUraianTugas().getDeskripsi(),
                                uraianTugasJabatan.getUraianTugasJabatanId().getKdUrtug(),
                                uraianTugasJabatan.getUraianTugasJabatanId().getKdJabatan(),
                                uraianTugasJabatan.getKuantitas(),
                                uraianTugasJabatan.getSatuanKuantitas(),
                                uraianTugasJabatan.getKualitas(),
                                uraianTugasJabatan.getWaktu(),
                                uraianTugasJabatan.getBiaya()));
            }
        }

        return new ResponseEntity<Object>(urtugJabatanWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/add-uraian-tugas-jabatan", method = RequestMethod.POST)
    @Transactional
    ResponseEntity<?> addUraianTugasJabatan(@RequestBody UraianTugasJabatanInputWrapper urtugJabatanWrapper) {
        LOGGER.info("add urtug jabatan");

        uraianTugasJabatanService.createUrtugJabatan(urtugJabatanWrapper);

        return new ResponseEntity<Object>(new CustomMessage("urtug jabatan created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/add-uraian-tugas-jabatan-custom", method = RequestMethod.POST)
    ResponseEntity<?> addUraianTugasJabatanCustom(@RequestBody UraianTugasJabatanInputWrapper urtugJabatanWrapper) {
        LOGGER.info("add urtug jabatan custom");

        UraianTugas uraianTugas = new UraianTugas();

        TkdJabatan objJab = tkdJabatanService.getTkdJabatan(urtugJabatanWrapper.getKdJabatan());

        String newKdUrtug = String.valueOf(new Date().getTime());

        uraianTugas.setKdUrtug(newKdUrtug);
        uraianTugas.setDeskripsi(urtugJabatanWrapper.getDeskripsiUrtug());
        uraianTugas.setCreatedBy(new AkunPegawai(urtugJabatanWrapper.getCreatedBy()));
        uraianTugas.setVolume(urtugJabatanWrapper.getKuantitas());
        uraianTugas.setSatuanVolume(urtugJabatanWrapper.getSatuanKuantitas());
        uraianTugas.setJabatan(objJab.getJabatan());
        uraianTugas.setUnitKerja(objJab.getUnitKerja());

        uraianTugasService.save(uraianTugas);

        urtugJabatanWrapper.setKdUrtug(newKdUrtug);

        uraianTugasJabatanService.createUrtugJabatan(urtugJabatanWrapper);

        return new ResponseEntity<Object>(new CustomMessage("urtug jabatan created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/add-daftar-uraian-tugas-jabatan", method = RequestMethod.POST)
    ResponseEntity<?> addDaftarUraianTugasJabatan(@RequestBody List<UraianTugasJabatanInputWrapper> urtugJabatanWrappers) {
        LOGGER.info("add urtug jabatan");

        for (UraianTugasJabatanInputWrapper obj : urtugJabatanWrappers) {
            uraianTugasJabatanService.createUrtugJabatan(obj);
        }

        return new ResponseEntity<Object>(new CustomMessage("urtug jabatan created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/update-uraian-tugas-jabatan", method = RequestMethod.PUT)
    @Transactional
    ResponseEntity<?> updateUraianTugasJabatan(@RequestBody UraianTugasJabatanInputWrapper urtugJabatanWrapper) {
        LOGGER.info("add urtug jabatan");

        uraianTugasJabatanService.update(urtugJabatanWrapper);

        return new ResponseEntity<Object>(new CustomMessage("urtug jabatan created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-uraian-tugas-jabatan/{kdJabatan}/{kdUrtug}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteUraianTugasJabatan(
            @PathVariable("kdJabatan") String kdJabatan,
            @PathVariable("kdUrtug") String kdUrtug) {
        LOGGER.info("delete urtug jabatan");

        uraianTugasJabatanService.delete(kdUrtug, kdJabatan);

        return new ResponseEntity<Object>(new CustomMessage("uraian tugas jabatan deleted"), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/add-uraian-tugas-jabatan", method = RequestMethod.POST)
//    @Transactional
//    ResponseEntity<?> addUraianTugasJabatan(
//            @RequestBody UraianTugasJabatanInputWrapper uraianTugasJabatanInputWrapper) {
//        LOGGER.info("set uraian tugas into jabatan "+uraianTugasJabatanInputWrapper.getKdJabatan());
//
//        String kdJabatan = uraianTugasJabatanInputWrapper.getKdJabatan();
//        List<UraianTugasJabatanController> uraianTugasJabatanList =
//                uraianTugasJabatanService.getUraianTugasJabatanByJabatan(kdJabatan);
//        //first destroy jabatan uraian tugas if jabatan already has uraian tugas if not
//        if (!uraianTugasJabatanList.isEmpty()) {
//            LOGGER.info("destroy now");
//            uraianTugasJabatanService
//                    .deleteAllUraianTugasJabatanByJabatan(kdJabatan);
//        }
//        //loop as long as uraian tugas in uraianTugasJabatanInput wrapper and save Uraian tugas Jabatan
//        for (KdUraianTugasWrapper kdUraianTugasWrapper :
//                uraianTugasJabatanInputWrapper.getKdUraianTugasList()) {
////            LOGGER.info("masuk");
//            UraianTugasJabatanController uraianTugasJabatan = new UraianTugasJabatanController();
//            uraianTugasJabatan
//                    .setUraianTugasJabatanId(
//                            new UraianTugasJabatanId(
//                                    kdUraianTugasWrapper.getKdUrtug(),
//                                    uraianTugasJabatanInputWrapper.getKdJabatan()
//                            ));
//            uraianTugasJabatan
//                    .setCreatedBy(uraianTugasJabatanInputWrapper.getCreatedBy());
//
//            uraianTugasJabatanService.save(uraianTugasJabatan);
//        }
//
//        return new ResponseEntity<Object>(
//                new CustomMessage("uraian tugas added"),
//                HttpStatus.OK);
//
//    }

}
