package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.UraianTugasJabatanJenisUrtugService;
import com.pemda.ekinerjademo.service.UraianTugasPegawaiBulananService;
import com.pemda.ekinerjademo.service.UraianTugasPegawaiTahunanService;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasJabatanJenisUrtugInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasPegawaiTahunanInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.UrtugJabatanIdInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.UraianTugasJabatanJenisUrtugWrapper;
import com.pemda.ekinerjademo.wrapper.output.UraianTugasPegawaiTahunanWrapper;
import com.pemda.ekinerjademo.wrapper.output.UrtugNonDpaWrapper;
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
 * Created by bagus on 10/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class UraianTugasJabatanJenisUrtugController {
    public static final Logger LOGGER
            = LoggerFactory.getLogger(UraianTugasJabatanJenisUrtugController.class);

    @Autowired
    private UraianTugasJabatanJenisUrtugService uraianTugasJabatanJenisUrtugService;
    @Autowired
    private UraianTugasPegawaiTahunanService urtugPegawaiTahunanService;
    @Autowired
    private UraianTugasPegawaiBulananService uraianTugasPegawaiBulananService;
    @Autowired
    private QutPegawaiCloneService qutPegawaiService;

    @RequestMapping(value = "/get-urtug-jabatan-jenis-by-urtug-jabatan", method = RequestMethod.POST)
    ResponseEntity<?> getByUrtugJabatan(@RequestBody UrtugJabatanIdInputWrapper inputWrapper) {
        LOGGER.info("get urtug jabatan jenis by urtug jabatan");

        List<UraianTugasJabatanJenisUrtugWrapper> urtugWrapperList
                = new ArrayList<>();
        List<UraianTugasJabatanJenisUrtug> urtugJabatanJenisList
                = uraianTugasJabatanJenisUrtugService.getByUrtugJabatan(inputWrapper.getKdUrtug(), inputWrapper.getKdJabatan());

        for (UraianTugasJabatanJenisUrtug urtugJabatanJenis : urtugJabatanJenisList) {
            urtugWrapperList
                    .add(new UraianTugasJabatanJenisUrtugWrapper(
                            urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdUrtug(),
                            urtugJabatanJenis.getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                            urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJabatan(),
                            urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJenisUrtug(),
                            urtugJabatanJenis.getJenisUrtug().getJenisUrtug(),
                            urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getTahunUrtug(),
                            urtugJabatanJenis.getKuantitas(),
                            urtugJabatanJenis.getSatuanKuantitas(),
                            urtugJabatanJenis.getKualitas(),
                            urtugJabatanJenis.getWaktu(),
                            urtugJabatanJenis.getBiaya()
                    ));
        }

        return new ResponseEntity<Object>(urtugWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-urtug-jabatan-jenis-by-jabatan/{kdJabatan}", method = RequestMethod.GET)
    ResponseEntity<?> getByJabatan(@PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get urtug jabatan jenis byjabatan");

        List<UraianTugasJabatanJenisUrtugWrapper> urtugWrapperList
                = new ArrayList<>();
        List<UraianTugasJabatanJenisUrtug> urtugJabatanJenisList
                = uraianTugasJabatanJenisUrtugService.getByJabatan(kdJabatan);

        for (UraianTugasJabatanJenisUrtug urtugJabatanJenis : urtugJabatanJenisList) {
            urtugWrapperList
                    .add(new UraianTugasJabatanJenisUrtugWrapper(
                            urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdUrtug(),
                            urtugJabatanJenis.getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                            urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJabatan(),
                            urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJenisUrtug(),
                            urtugJabatanJenis.getJenisUrtug().getJenisUrtug(),
                            urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getTahunUrtug(),
                            urtugJabatanJenis.getKuantitas(),
                            urtugJabatanJenis.getSatuanKuantitas(),
                            urtugJabatanJenis.getKualitas(),
                            urtugJabatanJenis.getWaktu(),
                            urtugJabatanJenis.getBiaya()
                    ));
        }

        return new ResponseEntity<Object>(urtugWrapperList, HttpStatus.OK);
    }

    /**
     *
     * aturan baru pada tanggal 17 juli 2019 :
     * 1. kontrak tahunan tidak ada pengajuan. jadi setelah pemasangan uraian tugas non dpa langsung menjadi kontrak
     *
     * @param inputWrapper
     * @return
     */
    @RequestMapping(value = "/create-urtug-jabatan-jenis", method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody UraianTugasJabatanJenisUrtugInputWrapper inputWrapper) {
        LOGGER.info("crete urtug jabatan jenis");

        UraianTugasJabatanJenisUrtug urtugJabatanJenis
                = new UraianTugasJabatanJenisUrtug();
        urtugJabatanJenis
                .setUraianTugasJabatanJenisUrtugId(
                        new UraianTugasJabatanJenisUrtugId(
                                inputWrapper.getKdUrtug(),
                                inputWrapper.getKdJabatan(),
                                inputWrapper.getKdJenisUrtug(),
                                Year.now().getValue()));
        urtugJabatanJenis.setKuantitas(inputWrapper.getKuantitas());
        urtugJabatanJenis.setSatuanKuantitas(inputWrapper.getSatuanKuantitas());
        urtugJabatanJenis.setKualitas(inputWrapper.getKualitas());
        urtugJabatanJenis.setWaktu(inputWrapper.getWaktu());
        urtugJabatanJenis.setBiaya(inputWrapper.getBiaya());

        uraianTugasJabatanJenisUrtugService.save(urtugJabatanJenis);

        /** logic ini dibuat berdasarkan aturan bahwa kontrak tahunan tidak memerlukan ajuan dari pegawai **/
        if (inputWrapper.getKdJenisUrtug().equals("KJU002")) {
            List<QutPegawaiClone> pegawaiListInJabatan
                    = qutPegawaiService.getQutPegawaiByKdJabatan(inputWrapper.getKdJabatan());
            for (QutPegawaiClone pegawai : pegawaiListInJabatan) {
                UraianTugasPegawaiTahunanInputWrapper urtugTahunanPegawai = new UraianTugasPegawaiTahunanInputWrapper();

                urtugTahunanPegawai.setKdUrtug(inputWrapper.getKdUrtug());
                urtugTahunanPegawai.setKdJabatan(inputWrapper.getKdJabatan());
                urtugTahunanPegawai.setKdJenisUrtug(inputWrapper.getKdJenisUrtug());
                urtugTahunanPegawai.setTahunUrtug(Year.now().getValue());
                urtugTahunanPegawai.setNipPegawai(pegawai.getNip());

                urtugTahunanPegawai.setBiaya(inputWrapper.getBiaya());
                urtugTahunanPegawai.setKualitas(inputWrapper.getKualitas());
                urtugTahunanPegawai.setKuantitas(inputWrapper.getKuantitas());
                urtugTahunanPegawai.setSatuanKuantitas(inputWrapper.getSatuanKuantitas());
                urtugTahunanPegawai.setWaktu(inputWrapper.getWaktu());

                urtugPegawaiTahunanService.createUraianTugasPegawaiTahunan(urtugTahunanPegawai, 1);

            }

        }

        return new ResponseEntity<Object>(new CustomMessage("urtug jabatan jenis created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update-urtug-jabatan-jenis", method = RequestMethod.PUT)
    ResponseEntity<?> update() {
        LOGGER.info("update urtug jabatan jenis");

        return null;
    }

    @RequestMapping(value = "/delete-urtug-jabatan-jenis", method = RequestMethod.POST)
    ResponseEntity<?> delete(@RequestBody UraianTugasJabatanJenisUrtugInputWrapper inputWrapper) {
        LOGGER.info("delete urtug jabatan jenis");

        uraianTugasJabatanJenisUrtugService
                .delete(new UraianTugasJabatanJenisUrtugId(
                        inputWrapper.getKdUrtug(),
                        inputWrapper.getKdJabatan(),
                        inputWrapper.getKdJenisUrtug(),
                        inputWrapper.getTahunUrtug()));

        return new ResponseEntity<Object>(new CustomMessage("urtug jabatan jenis deleted"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-urtug-non-dpa-by-jabatan/{kdJabatan}/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getUrtugNonDpaByJabatan(
            @PathVariable("kdJabatan") String kdJabatan,
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get urtug Non-DPA by jabatan");

        List<UraianTugasJabatanJenisUrtugWrapper> urtugWrapperList
                = new ArrayList<>();
        List<UraianTugasPegawaiTahunanWrapper> uraianTugasPegawaiTahunanWrapperList
                = new ArrayList<>();
        List<UraianTugasJabatanJenisUrtug> urtugJabatanJenisList
                = uraianTugasJabatanJenisUrtugService.getUrtugNonDpaByJabatan(kdJabatan);
        List<UraianTugasPegawaiBulanan> uraianTugasPegawaiBulananList
                = uraianTugasPegawaiBulananService.getByNip(nipPegawai);
        List<UraianTugasPegawaiTahunan> urtugPegawaiTahunanList
                = urtugPegawaiTahunanService.getByNipPegawai(nipPegawai);

        boolean foundInDB;
        for (UraianTugasJabatanJenisUrtug urtugJabatanJenis : urtugJabatanJenisList) {
            foundInDB = false;
            for (UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan : uraianTugasPegawaiBulananList) {
                if (urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdUrtug()
                        .equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdUrtug())
                        && urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJabatan()
                        .equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJabatan())
                        && urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getTahunUrtug()
                        .equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getTahunUrtug())
                        && urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJenisUrtug()
                        .equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJenisUrtug())) {

                    boolean found = false;
                    for (UraianTugasJabatanJenisUrtugWrapper objWrapper : urtugWrapperList) {
                        if (objWrapper.getKdUrtug().equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdUrtug())
                                && objWrapper.getKdJabatan().equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJabatan())
                                && objWrapper.getTahunUrtug().equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getTahunUrtug())
                                && objWrapper.getKdJenisUrtug().equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJenisUrtug())) {
                            found = true;

                            objWrapper.setTotalRealisasi(objWrapper.getTotalRealisasi() + uraianTugasPegawaiBulanan.getRealisasiKuantitas());

                            break;
                        }
                    }

                    if (!found) {
                        urtugWrapperList
                                .add(new UraianTugasJabatanJenisUrtugWrapper(
                                        urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdUrtug(),
                                        urtugJabatanJenis.getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                        urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJabatan(),
                                        urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJenisUrtug(),
                                        urtugJabatanJenis.getJenisUrtug().getJenisUrtug(),
                                        urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getTahunUrtug(),
                                        urtugJabatanJenis.getKuantitas(),
                                        urtugJabatanJenis.getSatuanKuantitas(),
                                        urtugJabatanJenis.getKualitas(),
                                        urtugJabatanJenis.getWaktu(),
                                        urtugJabatanJenis.getBiaya(),
                                        uraianTugasPegawaiBulanan.getRealisasiKuantitas()
                                ));
                    }
                    foundInDB = true;
                }
            }

            if (!foundInDB) {
                urtugWrapperList
                        .add(new UraianTugasJabatanJenisUrtugWrapper(
                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdUrtug(),
                                urtugJabatanJenis.getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJabatan(),
                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJenisUrtug(),
                                urtugJabatanJenis.getJenisUrtug().getJenisUrtug(),
                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getTahunUrtug(),
                                urtugJabatanJenis.getKuantitas(),
                                urtugJabatanJenis.getSatuanKuantitas(),
                                urtugJabatanJenis.getKualitas(),
                                urtugJabatanJenis.getWaktu(),
                                urtugJabatanJenis.getBiaya(),
                                0
                        ));
            }

        }

//        for (UraianTugasJabatanJenisUrtug urtugJabatanJenis : urtugJabatanJenisList) {
//            boolean found = false;
//            for (UraianTugasPegawaiTahunan uraianTugasPegawaiTahunan : urtugPegawaiTahunanList) {
//                if (urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdUrtug()
//                        .equals(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug())) {
//                    found = true;
//                    uraianTugasPegawaiTahunanWrapperList
//                            .add(new UraianTugasPegawaiTahunanWrapper(
//                                    uraianTugasPegawaiTahunan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
//                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug(),
//                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJabatan(),
//                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJenisUrtug(),
//                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getTahunUrtug(),
//                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai(),
//                                    uraianTugasPegawaiTahunan.getKuantitas(),
//                                    uraianTugasPegawaiTahunan.getSatuanKuantitas(),
//                                    uraianTugasPegawaiTahunan.getKualitas(),
//                                    uraianTugasPegawaiTahunan.getWaktu(),
//                                    uraianTugasPegawaiTahunan.getBiaya(),
//                                    uraianTugasPegawaiTahunan.getAlasan(),
//                                    uraianTugasPegawaiTahunan.getStatusApproval()
//                            ));
//                    break;
//                }
//            }
//
//            if (!found) {
//                urtugWrapperList
//                        .add(new UraianTugasJabatanJenisUrtugWrapper(
//                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdUrtug(),
//                                urtugJabatanJenis.getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
//                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJabatan(),
//                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJenisUrtug(),
//                                urtugJabatanJenis.getJenisUrtug().getJenisUrtug(),
//                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getTahunUrtug(),
//                                urtugJabatanJenis.getKuantitas(),
//                                urtugJabatanJenis.getSatuanKuantitas(),
//                                urtugJabatanJenis.getKualitas(),
//                                urtugJabatanJenis.getWaktu(),
//                                urtugJabatanJenis.getBiaya()
//                        ));
//            }
//
//        }

        UrtugNonDpaWrapper urtugNonDpaWrapper
                = new UrtugNonDpaWrapper(urtugWrapperList, uraianTugasPegawaiTahunanWrapperList);

        return new ResponseEntity<Object>(urtugNonDpaWrapper, HttpStatus.OK);
    }
}


//logic sebelum ada kontrak kerja bulanan
//    @RequestMapping(value = "/get-urtug-non-dpa-by-jabatan/{kdJabatan}/{nipPegawai}", method = RequestMethod.GET)
//    ResponseEntity<?> getUrtugNonDpaByJabatan(
//            @PathVariable("kdJabatan") String kdJabatan,
//            @PathVariable("nipPegawai") String nipPegawai) {
//        LOGGER.info("get urtug Non-DPA by jabatan");
//
//        List<UraianTugasJabatanJenisUrtugWrapper> urtugWrapperList
//                = new ArrayList<>();
//        List<UraianTugasPegawaiTahunanWrapper> uraianTugasPegawaiTahunanWrapperList
//                = new ArrayList<>();
//        List<UraianTugasJabatanJenisUrtug> urtugJabatanJenisList
//                = uraianTugasJabatanJenisUrtugService.getUrtugNonDpaByJabatan(kdJabatan);
//        List<UraianTugasPegawaiTahunan> urtugPegawaiTahunanList
//                = urtugPegawaiTahunanService.getByNipPegawai(nipPegawai);
//
//        for (UraianTugasJabatanJenisUrtug urtugJabatanJenis : urtugJabatanJenisList) {
//            boolean found = false;
//            for (UraianTugasPegawaiTahunan uraianTugasPegawaiTahunan : urtugPegawaiTahunanList) {
//                if (urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdUrtug()
//                        .equals(uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug())) {
//                    found = true;
//                    uraianTugasPegawaiTahunanWrapperList
//                            .add(new UraianTugasPegawaiTahunanWrapper(
//                                    uraianTugasPegawaiTahunan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
//                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdUrtug(),
//                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJabatan(),
//                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getKdJenisUrtug(),
//                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getTahunUrtug(),
//                                    uraianTugasPegawaiTahunan.getUraianTugasPegawaiTahunanId().getNipPegawai(),
//                                    uraianTugasPegawaiTahunan.getKuantitas(),
//                                    uraianTugasPegawaiTahunan.getSatuanKuantitas(),
//                                    uraianTugasPegawaiTahunan.getKualitas(),
//                                    uraianTugasPegawaiTahunan.getWaktu(),
//                                    uraianTugasPegawaiTahunan.getBiaya(),
//                                    uraianTugasPegawaiTahunan.getAlasan(),
//                                    uraianTugasPegawaiTahunan.getStatusApproval()
//                            ));
//                    break;
//                }
//            }
//
//            if (!found) {
//                urtugWrapperList
//                        .add(new UraianTugasJabatanJenisUrtugWrapper(
//                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdUrtug(),
//                                urtugJabatanJenis.getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
//                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJabatan(),
//                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getKdJenisUrtug(),
//                                urtugJabatanJenis.getJenisUrtug().getJenisUrtug(),
//                                urtugJabatanJenis.getUraianTugasJabatanJenisUrtugId().getTahunUrtug(),
//                                urtugJabatanJenis.getKuantitas(),
//                                urtugJabatanJenis.getSatuanKuantitas(),
//                                urtugJabatanJenis.getKualitas(),
//                                urtugJabatanJenis.getWaktu(),
//                                urtugJabatanJenis.getBiaya()
//                        ));
//            }
//
//        }
//
//        UrtugNonDpaWrapper urtugNonDpaWrapper
//                = new UrtugNonDpaWrapper(urtugWrapperList, uraianTugasPegawaiTahunanWrapperList);
//
//        return new ResponseEntity<Object>(urtugNonDpaWrapper, HttpStatus.OK);
//    }
