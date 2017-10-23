package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanId;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UnitKerjaKegiatanDao;
import com.pemda.ekinerjademo.repository.simdarepository.TaKegiatanDao;
import com.pemda.ekinerjademo.service.TaKegiatanService;
import com.pemda.ekinerjademo.service.UnitKerjaKegiatanService;
import com.pemda.ekinerjademo.service.UrtugKegiatanService;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasJabatanInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.UrtugJabatanIdInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.UrtugJabatanJenisIdInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.UrtugKegiatanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.UrtugKegiatanWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 05/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class UrtugKegiatanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UrtugKegiatanController.class);

    private UrtugKegiatanService urtugKegiatanService;
    @Autowired
    private UnitKerjaKegiatanDao unitKerjaKegiatanDao;
    @Autowired
    private TaKegiatanDao taKegiatanDao;
    @Autowired
    private TaKegiatanService taKegiatanService;
    @Autowired
    private UnitKerjaKegiatanService unitKerjaKegiatanService;

    @Autowired
    public UrtugKegiatanController(UrtugKegiatanService urtugKegiatanService) {
        this.urtugKegiatanService = urtugKegiatanService;
    }

    ResponseEntity<?> getUrtugKegiatan() {
        return null;
    }

    @RequestMapping(value = "/get-urtug-kegiatan-by-jabatan", method = RequestMethod.POST)
    ResponseEntity<?> getUrtugKegiatanByUrtugJabatan(
            @RequestBody UrtugJabatanJenisIdInputWrapper urtugJabatanWrapper) {
        List<UrtugKegiatanWrapper> urtugKegiatanWrapperList = new ArrayList<>();

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(urtugJabatanWrapper.getKdUnitKerja());

        List<TaKegiatan> taKegiatanList = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);

        List<UrtugKegiatan> urtugKegiatanList
                = urtugKegiatanService.findAllByUraianTugasJabatan(
                        urtugJabatanWrapper.getKdUrtug(),
                        urtugJabatanWrapper.getKdJabatan(),
                        urtugJabatanWrapper.getKdJenisUrtug(),
                        urtugJabatanWrapper.getTahunUrtug());

        for (UrtugKegiatan urtugKegiatan : urtugKegiatanList) {
            for (TaKegiatan taKegiatan : taKegiatanList) {
                if (urtugKegiatan.getUrtugKegiatanId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg()) &&
                        urtugKegiatan.getUrtugKegiatanId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {
                    urtugKegiatanWrapperList
                            .add(new UrtugKegiatanWrapper(
                                    urtugKegiatan.getUrtugKegiatanId().getKdUrtug(),
                                    urtugKegiatan.getUrtugKegiatanId().getKdJabatan(),
                                    urtugKegiatan.getUrtugKegiatanId().getKdJenisUrtug(),
                                    urtugKegiatan.getUrtugKegiatanId().getTahunUrtug(),
                                    urtugKegiatan.getUrtugKegiatanId().getKdUrusan(),
                                    urtugKegiatan.getUrtugKegiatanId().getKdBidang(),
                                    urtugKegiatan.getUrtugKegiatanId().getKdUnit(),
                                    urtugKegiatan.getUrtugKegiatanId().getKdSub(),
                                    urtugKegiatan.getUrtugKegiatanId().getTahun(),
                                    urtugKegiatan.getUrtugKegiatanId().getKdProg(),
                                    urtugKegiatan.getUrtugKegiatanId().getIdProg(),
                                    urtugKegiatan.getUrtugKegiatanId().getKdKeg(),
                                    taKegiatan.getKetKegiatan(),
                                    taKegiatan.getPaguAnggaran()));
                }
            }
        }

        return new ResponseEntity<Object>(urtugKegiatanWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/create-urtug-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> createUrtugKegiatan(@RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
        LOGGER.info("create urtug kegiatan");

        UrtugKegiatan urtugKegiatan = new UrtugKegiatan();
        urtugKegiatan.setUrtugKegiatanId(
                new UrtugKegiatanId(
                        urtugKegiatanInputWrapper.getKdUrtug(),
                        urtugKegiatanInputWrapper.getKdJabatan(),
                        urtugKegiatanInputWrapper.getKdJenisUrtug(),
                        urtugKegiatanInputWrapper.getTahunUrtug(),
                        urtugKegiatanInputWrapper.getKdUrusan(),
                        urtugKegiatanInputWrapper.getKdBidang(),
                        urtugKegiatanInputWrapper.getKdUnit(),
                        urtugKegiatanInputWrapper.getKdSub(),
                        urtugKegiatanInputWrapper.getTahun(),
                        urtugKegiatanInputWrapper.getKdProg(),
                        urtugKegiatanInputWrapper.getIdProg(),
                        urtugKegiatanInputWrapper.getKdKeg()
                ));

        urtugKegiatanService.save(urtugKegiatan);

        return new ResponseEntity<Object>(new CustomMessage("urtug kegiatan created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update-urtug-kegiatan", method = RequestMethod.PUT)
    ResponseEntity<?> updateUrtugKegiatan(@RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
        LOGGER.info("update urtug kegiatan");

        UrtugKegiatan urtugKegiatan = new UrtugKegiatan();
        urtugKegiatan.setUrtugKegiatanId(
                new UrtugKegiatanId(
                        urtugKegiatanInputWrapper.getKdUrtug(),
                        urtugKegiatanInputWrapper.getKdJabatan(),
                        urtugKegiatanInputWrapper.getKdJenisUrtug(),
                        urtugKegiatanInputWrapper.getTahunUrtug(),
                        urtugKegiatanInputWrapper.getKdUrusan(),
                        urtugKegiatanInputWrapper.getKdBidang(),
                        urtugKegiatanInputWrapper.getKdUnit(),
                        urtugKegiatanInputWrapper.getKdSub(),
                        urtugKegiatanInputWrapper.getTahun(),
                        urtugKegiatanInputWrapper.getKdProg(),
                        urtugKegiatanInputWrapper.getIdProg(),
                        urtugKegiatanInputWrapper.getKdKeg()
                ));

        urtugKegiatanService.update(urtugKegiatan);

        return new ResponseEntity<Object>(new CustomMessage("urtug kegiatan updated"), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-urtug-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> deleteUrtugKegiatan(@RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
        LOGGER.info("delete urtug kegiatan");

        urtugKegiatanService.delete(
                new UrtugKegiatanId(
                        urtugKegiatanInputWrapper.getKdUrtug(),
                        urtugKegiatanInputWrapper.getKdJabatan(),
                        urtugKegiatanInputWrapper.getKdJenisUrtug(),
                        urtugKegiatanInputWrapper.getTahunUrtug(),
                        urtugKegiatanInputWrapper.getKdUrusan(),
                        urtugKegiatanInputWrapper.getKdBidang(),
                        urtugKegiatanInputWrapper.getKdUnit(),
                        urtugKegiatanInputWrapper.getKdSub(),
                        urtugKegiatanInputWrapper.getTahun(),
                        urtugKegiatanInputWrapper.getKdProg(),
                        urtugKegiatanInputWrapper.getIdProg(),
                        urtugKegiatanInputWrapper.getKdKeg()
                ));

        return new ResponseEntity<Object>(new CustomMessage("urtug kegiatan deleted"), HttpStatus.OK);
    }

}
