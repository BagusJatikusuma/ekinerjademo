package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiTahunan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiTahunanId;
import com.pemda.ekinerjademo.service.UraianTugasPegawaiTahunanService;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasPegawaiTahunanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.UraianTugasPegawaiTahunanWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 14/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class UraianTugasPegawaiTahunanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UraianTugasPegawaiTahunanController.class);

    @Autowired private UraianTugasPegawaiTahunanService urtugPegawaiTahunanService;

    @RequestMapping(value = "/get-uraian-tugas-pegawai-tahunan-by-nip/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getUraianTugasPegawaiTahunanByNip(@PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get uraian tugas pegawai tahunan by nip");

        List<UraianTugasPegawaiTahunan> urtugPegawaiTahunanList
                = urtugPegawaiTahunanService.getByNipPegawai(nipPegawai);
        List<UraianTugasPegawaiTahunanWrapper> urtugPegawaiTahunanWrapperList
                = new ArrayList<>();

        for (UraianTugasPegawaiTahunan urtugPegawaiTahunan : urtugPegawaiTahunanList) {
            urtugPegawaiTahunanWrapperList
                    .add(new UraianTugasPegawaiTahunanWrapper(
                            urtugPegawaiTahunan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                            urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug(),
                            urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJabatan(),
                            urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJenisUrtug(),
                            urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getTahunUrtug(),
                            urtugPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai()
                    ));
        }

        return new ResponseEntity<Object>(urtugPegawaiTahunanWrapperList, HttpStatus.OK);

    }

    @RequestMapping(value = "/create-daftar-uraian-tugas-pegawai-tahunan", method = RequestMethod.POST)
    ResponseEntity<?> createUraianTugasPegawaiTahunanList(
            @RequestBody List<UraianTugasPegawaiTahunanInputWrapper> urtugPegawaiList) {
        LOGGER.info("create daftar uraian tugas pegawai tahunan");

        urtugPegawaiTahunanService.createUraianTugasPegawaiTahunanList(urtugPegawaiList);

        return new ResponseEntity<Object>(
                new CustomMessage("uraian tugas pegawai tahunan created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-uraian-tugas-pegawai-tahunan", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteUraianTugasPegawaiTahunan(@RequestBody UraianTugasPegawaiTahunanInputWrapper uraianTugasPegawaiTahunanInputWrapper) {
        LOGGER.info("delete uraian tugas pegawai tahunan");

        urtugPegawaiTahunanService
                .deleteUraianTugasPegawaiTahunan(
                        new UraianTugasPegawaiTahunanId(
                                uraianTugasPegawaiTahunanInputWrapper.getKdUrtug(),
                                uraianTugasPegawaiTahunanInputWrapper.getKdJabatan(),
                                uraianTugasPegawaiTahunanInputWrapper.getKdJenisUrtug(),
                                uraianTugasPegawaiTahunanInputWrapper.getTahunUrtug(),
                                uraianTugasPegawaiTahunanInputWrapper.getNipPegawai()
                        ));

        return new ResponseEntity<Object>(
                new CustomMessage("uraian tugas pegawai tahunan created"), HttpStatus.OK);
    }

}
