package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.TaKegiatanService;
import com.pemda.ekinerjademo.service.UnitKerjaKegiatanService;
import com.pemda.ekinerjademo.service.UrtugKegiatanPegawaiService;
import com.pemda.ekinerjademo.wrapper.input.*;
import com.pemda.ekinerjademo.wrapper.output.*;
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
    @Autowired private QutPegawaiService qutPegawaiService;

    @Autowired
    public UrtugKegiatanPegawaiController(UrtugKegiatanPegawaiService urtugKegiatanPegawaiService) {
        this.urtugKegiatanPegawaiService = urtugKegiatanPegawaiService;
    }

    @RequestMapping(value = "/get-urtug-kegiatan-pegawai-by-urtug-jabatan", method = RequestMethod.POST)
    ResponseEntity<?> getUrtugKegiatanPegawaiByUrtugJabatan(
            @RequestBody UraianTugasKegiatanByJabatanInputWrapper urtugJabatanInputWrapper) {
        LOGGER.info("get urtug kegiatan pegawai by urtug jabatan");

        List<UrtugKegiatanPegawaiByUrtugJabatanWrapper> urtugKegiatanPegawaiWrappers
                = new ArrayList<>();

        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList
                = urtugKegiatanPegawaiService.findByUrtugJabatan(
                        new UraianTugasJabatanJenisUrtugId(
                                urtugJabatanInputWrapper.getKdUrtug(),
                                urtugJabatanInputWrapper.getKdJabatan(),
                                urtugJabatanInputWrapper.getKdJenisUrtug(),
                                urtugJabatanInputWrapper.getTahunUrtug()));

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(urtugJabatanInputWrapper.getKdUnitKerja());

        List<TaKegiatan> taKegiatanList = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(urtugJabatanInputWrapper.getKdUnitKerja());

        for (UrtugKegiatanPegawai urtugKegiatanPegawai : urtugKegiatanPegawaiList) {
            for (TaKegiatan taKegiatan : taKegiatanList) {
                if (urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg()) &&
                        urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {
                    for (QutPegawai qutPegawai : qutPegawaiList) {
                        if (urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getNipPegawai()
                                .equals(qutPegawai.getNip())) {
                            urtugKegiatanPegawaiWrappers
                                    .add(new UrtugKegiatanPegawaiByUrtugJabatanWrapper(
                                            urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdUrtug(),
                                            urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdJabatan(),
                                            urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdJenisUrtug(),
                                            urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getTahunUrtug(),
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
                                            qutPegawai.getNama(),
                                            urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdStatusPenanggungJawab(),
                                            urtugKegiatanPegawai.getStatusPenanggungJawabKegiatan().getStatus()));
                            break;

                        }

                    }

                    break;

                }

            }

        }

        return new ResponseEntity<Object>(urtugKegiatanPegawaiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-urtug-kegiatan-pegawai/{nipPegawai}/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getUrtugKegiatanPegawaiByNip(
            @PathVariable("nipPegawai") String nipPegawai,
            @PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("geet urtug kegiatan pegawai by pegawai");

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
                                    urtugKegiatanPegawai.getUrtugKegiatan().getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdJabatan(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdJenisUrtug(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getTahunUrtug(),
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

                    break;
                }
            }
        }

        return new ResponseEntity<Object>(urtugKegiatanPegawaiWrappers, HttpStatus.OK);
    }

    //get urtug dpa by nip
    @RequestMapping(value = "/get-urtug-dpa-pegawai/{nipPegawai}/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getUrtugDpaPegawaiByNipPegawai(
            @PathVariable("nipPegawai") String nipPegawai,
            @PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get urtug dpa pegawai");

        List<UraianTugasJabatanOutputWrapper> outputWrappers
                = new ArrayList<>();

        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList
                = urtugKegiatanPegawaiService.findByNipPegawai(nipPegawai);

        for (UrtugKegiatanPegawai urtugKegiatanPegawai : urtugKegiatanPegawaiList) {
            boolean found = false;
            for (UraianTugasJabatanOutputWrapper uraianTugasJabatanOutputWrapper : outputWrappers) {
                if (urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdUrtug()
                        .equals(uraianTugasJabatanOutputWrapper.getKdUrtug())) {
                    found =true;
                    break;
                }

            }

            if (!found) {
                outputWrappers
                        .add(new UraianTugasJabatanOutputWrapper(
                                urtugKegiatanPegawai.getUrtugKegiatan().getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdUrtug(),
                                urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdJabatan(),
                                urtugKegiatanPegawai.getUrtugKegiatan().getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getKuantitas(),
                                urtugKegiatanPegawai.getUrtugKegiatan().getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getSatuanKuantitas(),
                                urtugKegiatanPegawai.getUrtugKegiatan().getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getKualitas(),
                                urtugKegiatanPegawai.getUrtugKegiatan().getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getWaktu(),
                                urtugKegiatanPegawai.getUrtugKegiatan().getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getBiaya()));
            }

        }

        return new ResponseEntity<Object>(outputWrappers, HttpStatus.OK);
    }

    //get kegiatan dpa and status by urtug jabatan and nip
    @RequestMapping(value = "/get-kegiatan-pegawai-by-urtug", method = RequestMethod.POST)
    ResponseEntity<?> getKegiatanPegawaibyUraianTugasJabatan(
            @RequestBody UrtugJabatanIdNipInputWrapper inputWrapper) {
        LOGGER.info("get kegiatan pegawai by urtug");

        List<UrtugKegiatanPegawaiWrapper> urtugKegiatanPegawaiWrappers
                = new ArrayList<>();

        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList
                = urtugKegiatanPegawaiService
                .findByUrtugJabatanAndNipPegawai(
                        new UraianTugasJabatanId(inputWrapper.getKdUrtug(), inputWrapper.getKdJabatan()),
                        inputWrapper.getNipPegawai());

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(inputWrapper.getKdUnitKerja());

        List<TaKegiatan> taKegiatanList = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);

        for (UrtugKegiatanPegawai urtugKegiatanPegawai : urtugKegiatanPegawaiList) {
            for (TaKegiatan taKegiatan : taKegiatanList) {
                if (urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg()) &&
                        urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {
                    urtugKegiatanPegawaiWrappers
                            .add(new UrtugKegiatanPegawaiWrapper(
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdUrtug(),
                                    urtugKegiatanPegawai.getUrtugKegiatan().getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdJabatan(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdJenisUrtug(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getTahunUrtug(),
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

                    break;
                }
            }
        }

        return new ResponseEntity<Object>(urtugKegiatanPegawaiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-urtug-kegiatan-pegawai-by-urtug-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> getUrtugKegiatanPegawaiByUrtugKegiatan(
            @RequestBody UrtugKegiatanUnitKerjaInputWrapper urtugKegiatanInputWrapper) {
        LOGGER.info("get urtug kegiatan pegawi by urtug kegiatan");

        List<PenanggungJawabKegiatanWrapper> penanggungJawabKegiatanList
                = new ArrayList<>();

        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(urtugKegiatanInputWrapper.getKdUnitKerja());

        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList
                = urtugKegiatanPegawaiService.findByUrtugKegiatan(
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
                                urtugKegiatanInputWrapper.getKdKeg()));

        for (UrtugKegiatanPegawai urtugKegiatanPegawai : urtugKegiatanPegawaiList) {
            for (QutPegawai qutPegawai : qutPegawaiList) {
                if (qutPegawai.getNip()
                        .equals(urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getNipPegawai())) {
                    penanggungJawabKegiatanList
                            .add(new PenanggungJawabKegiatanWrapper(
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getNipPegawai(),
                                    qutPegawai.getNama(),
                                    urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdStatusPenanggungJawab(),
                                    urtugKegiatanPegawai.getStatusPenanggungJawabKegiatan().getStatus()));
                    break;

                }

            }

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
                                urtugKegiatanPegawaiInputWrapper.getTahunUrtug(),
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
        urtugKegiatanPegawai.setStatusApproval(0);

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
                                urtugKegiatanPegawaiInputWrapper.getTahunUrtug(),
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
        urtugKegiatanPegawai.setStatusApproval(urtugKegiatanPegawaiInputWrapper.getStatusApproval());

        urtugKegiatanPegawaiService.update(urtugKegiatanPegawai);

        return new ResponseEntity<Object>(
                new CustomMessage("urtug kegiatan pegawai updated"), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-urtug-kegiatan-pegawai", method = RequestMethod.POST)
    ResponseEntity<?> deleteUrtugKegiatanPegawai(
            @RequestBody UrtugKegiatanPegawaiInputWrapper urtugKegiatanPegawaiInputWrapper) {
        LOGGER.info("delete urtug kegiatan pegawai");

        UrtugKegiatanPegawaiId urtugKegiatanPegawaiId
                = new UrtugKegiatanPegawaiId(
                urtugKegiatanPegawaiInputWrapper.getKdUrtug(),
                urtugKegiatanPegawaiInputWrapper.getKdJabatan(),
                urtugKegiatanPegawaiInputWrapper.getKdJenisUrtug(),
                urtugKegiatanPegawaiInputWrapper.getTahunUrtug(),
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

    //change status urtug dpa pegawai
    @RequestMapping(value = "/change-status-urtug-dpa-pegawai", method = RequestMethod.PUT)
    ResponseEntity<?> changeStatusUrtugDpaPegawai(
            @RequestBody UrtugKegiatanPegawaiInputWrapper urtugKegiatanPegawaiInputWrapper) {
        LOGGER.info("change status urtug dpa pegawai");

        return new ResponseEntity<Object>(
                new CustomMessage("urtug kegiatan pegawai updated"), HttpStatus.OK);
    }

}
