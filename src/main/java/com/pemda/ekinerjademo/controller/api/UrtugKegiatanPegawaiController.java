package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanId;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanPegawaiId;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.service.TaKegiatanService;
import com.pemda.ekinerjademo.service.UnitKerjaKegiatanService;
import com.pemda.ekinerjademo.service.UrtugKegiatanPegawaiService;
import com.pemda.ekinerjademo.wrapper.input.UrtugKegiatanInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.UrtugKegiatanPegawaiInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.PenanggungJawabKegiatanWrapper;
import com.pemda.ekinerjademo.wrapper.output.UrtugKegiatanPegawaiWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 06/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class UrtugKegiatanPegawaiController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UrtugKegiatanPegawaiController.class);

    private UrtugKegiatanPegawaiService urtugKegiatanPegawaiService;
    @Autowired private TaKegiatanService taKegiatanService;
    @Autowired private UnitKerjaKegiatanService unitKerjaKegiatanService;

    @Autowired
    public UrtugKegiatanPegawaiController(UrtugKegiatanPegawaiService urtugKegiatanPegawaiService) {
        this.urtugKegiatanPegawaiService = urtugKegiatanPegawaiService;
    }

    @RequestMapping(value = "/get-urtug-kegiatan-pegawai/{nipPegawai}/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getUrtugKegiatanPegawaiByNip(
            @PathVariable("nipPegawai") String nipPegawai,
            @PathVariable("kdUnitKerja") String kdUnitKerja) {
        List<UrtugKegiatanPegawaiWrapper> urtugKegiatanPegawaiWrappers
                = new ArrayList<>();

        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList
                = urtugKegiatanPegawaiService.findByNipPegawai(nipPegawai);

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(kdUnitKerja);

        List<TaKegiatan> taKegiatanList = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);

        for (UrtugKegiatanPegawai urtugKegiatanPegawai : urtugKegiatanPegawaiList) {
            for (TaKegiatan taKegiatan : taKegiatanList) {
                if (urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg()) &&
                        urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {
                    urtugKegiatanPegawaiWrappers
                            .add(new UrtugKegiatanPegawaiWrapper(
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdUrtug(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdJabatan(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdJenisUrtug(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdUrusan(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdBidang(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdUnit(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdSub(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getTahun(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdProg(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getIdProg(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdKeg(),
                                    taKegiatan.getKetKegiatan(),
                                    taKegiatan.getPaguAnggaran(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getNipPegawai(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdStatusPenanggungJawab(),
                                    urtugKegiatanPegawai.getStatusPenanggungJawabKegiatan().getStatus()));
                }
            }
        }

        return new ResponseEntity<Object>(urtugKegiatanPegawaiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-urtug-kegiatan-pegawai-by-urtug-kegiatan/", method = RequestMethod.POST)
    ResponseEntity<?> getUrtugKegiatanPegawaiByUrtugKegiatan(
            @RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
        List<PenanggungJawabKegiatanWrapper> penanggungJawabKegiatanList
                = new ArrayList<>();

        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList
                = urtugKegiatanPegawaiService.findByUrtugKegiatan(
                        new UrtugKegiatanId(
                                urtugKegiatanInputWrapper.getKdUrtug(),
                                urtugKegiatanInputWrapper.getKdJabatan(),
                                urtugKegiatanInputWrapper.getKdJenisUrtug(),
                                urtugKegiatanInputWrapper.getKdUrusan(),
                                urtugKegiatanInputWrapper.getKdBidang(),
                                urtugKegiatanInputWrapper.getKdUnit(),
                                urtugKegiatanInputWrapper.getKdSub(),
                                urtugKegiatanInputWrapper.getTahun(),
                                urtugKegiatanInputWrapper.getKdProg(),
                                urtugKegiatanInputWrapper.getIdProg(),
                                urtugKegiatanInputWrapper.getKdKeg()));

        for (UrtugKegiatanPegawai urtugKegiatanPegawai : urtugKegiatanPegawaiList) {
            penanggungJawabKegiatanList
                    .add(new PenanggungJawabKegiatanWrapper(
                            urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getNipPegawai(),
                            urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdStatusPenanggungJawab(),
                            urtugKegiatanPegawai.getStatusPenanggungJawabKegiatan().getStatus()));
        }

        return new ResponseEntity<Object>(penanggungJawabKegiatanList, HttpStatus.OK);
    }


    @RequestMapping(value = "/create-urtug-kegiatan-pegawai", method = RequestMethod.POST)
    ResponseEntity<?> createUrtugKegiatanPegawai(
            @RequestBody UrtugKegiatanPegawaiInputWrapper urtugKegiatanPegawaiInputWrapper) {
        LOGGER.info("create urtug kegiatan pegawai");

        UrtugKegiatanPegawai urtugKegiatanPegawai = new UrtugKegiatanPegawai();

        urtugKegiatanPegawai
                .setUrtugKegiatanPegawaiId(
                        new UrtugKegiatanPegawaiId(
                                urtugKegiatanPegawaiInputWrapper.getKdUrtug(),
                                urtugKegiatanPegawaiInputWrapper.getKdJabatan(),
                                urtugKegiatanPegawaiInputWrapper.getKdJenisUrtug(),
                                urtugKegiatanPegawaiInputWrapper.getKdUrusan(),
                                urtugKegiatanPegawaiInputWrapper.getKdBidang(),
                                urtugKegiatanPegawaiInputWrapper.getKdUnit(),
                                urtugKegiatanPegawaiInputWrapper.getKdSub(),
                                urtugKegiatanPegawaiInputWrapper.getTahun(),
                                urtugKegiatanPegawaiInputWrapper.getKdProg(),
                                urtugKegiatanPegawaiInputWrapper.getIdProg(),
                                urtugKegiatanPegawaiInputWrapper.getKdKeg(),
                                urtugKegiatanPegawaiInputWrapper.getNipPegawai(),
                                urtugKegiatanPegawaiInputWrapper.getKdStatusPenanggungJawab()));

        urtugKegiatanPegawaiService.save(urtugKegiatanPegawai);

        return new ResponseEntity<Object>(
                new CustomMessage("urtug kegiatan pegawai created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update-urtug-kegiatan-pegawai", method = RequestMethod.PUT)
    ResponseEntity<?> updateUrtugKegiatanPegawai(
            @RequestBody UrtugKegiatanPegawaiInputWrapper urtugKegiatanPegawaiInputWrapper) {
        LOGGER.info("update urtug kegiatan pegawai");

        UrtugKegiatanPegawai urtugKegiatanPegawai = new UrtugKegiatanPegawai();

        urtugKegiatanPegawai
                .setUrtugKegiatanPegawaiId(
                        new UrtugKegiatanPegawaiId(
                                urtugKegiatanPegawaiInputWrapper.getKdUrtug(),
                                urtugKegiatanPegawaiInputWrapper.getKdJabatan(),
                                urtugKegiatanPegawaiInputWrapper.getKdJenisUrtug(),
                                urtugKegiatanPegawaiInputWrapper.getKdUrusan(),
                                urtugKegiatanPegawaiInputWrapper.getKdBidang(),
                                urtugKegiatanPegawaiInputWrapper.getKdUnit(),
                                urtugKegiatanPegawaiInputWrapper.getKdSub(),
                                urtugKegiatanPegawaiInputWrapper.getTahun(),
                                urtugKegiatanPegawaiInputWrapper.getKdProg(),
                                urtugKegiatanPegawaiInputWrapper.getIdProg(),
                                urtugKegiatanPegawaiInputWrapper.getKdKeg(),
                                urtugKegiatanPegawaiInputWrapper.getNipPegawai(),
                                urtugKegiatanPegawaiInputWrapper.getKdStatusPenanggungJawab()));

        urtugKegiatanPegawaiService.update(urtugKegiatanPegawai);

        return new ResponseEntity<Object>(
                new CustomMessage("urtug kegiatan pegawai updated"), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-urtug-kegiatan-pegawai", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteUrtugKegiatanPegawai(
            @RequestBody UrtugKegiatanPegawaiInputWrapper urtugKegiatanPegawaiInputWrapper) {
        LOGGER.info("delete urtug kegiatan pegawai");

        UrtugKegiatanPegawaiId urtugKegiatanPegawaiId
                = new UrtugKegiatanPegawaiId(
                urtugKegiatanPegawaiInputWrapper.getKdUrtug(),
                urtugKegiatanPegawaiInputWrapper.getKdJabatan(),
                urtugKegiatanPegawaiInputWrapper.getKdJenisUrtug(),
                urtugKegiatanPegawaiInputWrapper.getKdUrusan(),
                urtugKegiatanPegawaiInputWrapper.getKdBidang(),
                urtugKegiatanPegawaiInputWrapper.getKdUnit(),
                urtugKegiatanPegawaiInputWrapper.getKdSub(),
                urtugKegiatanPegawaiInputWrapper.getTahun(),
                urtugKegiatanPegawaiInputWrapper.getKdProg(),
                urtugKegiatanPegawaiInputWrapper.getIdProg(),
                urtugKegiatanPegawaiInputWrapper.getKdKeg(),
                urtugKegiatanPegawaiInputWrapper.getNipPegawai(),
                urtugKegiatanPegawaiInputWrapper.getKdStatusPenanggungJawab());

        urtugKegiatanPegawaiService.delete(urtugKegiatanPegawaiId);

        return new ResponseEntity<Object>(
                new CustomMessage("urtug kegiatan pegawai deleted"), HttpStatus.OK);
    }

}
