package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanId;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatanId;
import com.pemda.ekinerjademo.model.simdamodel.TaProgram;
import com.pemda.ekinerjademo.model.simdamodel.TaProgramId;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UnitKerjaKegiatanDao;
import com.pemda.ekinerjademo.repository.simdarepository.TaKegiatanDao;
import com.pemda.ekinerjademo.repository.simdarepository.TaProgramDao;
import com.pemda.ekinerjademo.service.PenanggungJawabKegiatanService;
import com.pemda.ekinerjademo.service.TaKegiatanService;
import com.pemda.ekinerjademo.service.UnitKerjaKegiatanService;
import com.pemda.ekinerjademo.service.UrtugKegiatanService;
import com.pemda.ekinerjademo.wrapper.input.*;
import com.pemda.ekinerjademo.wrapper.output.*;
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
    private TaProgramDao taProgramDao;
    @Autowired
    private TaKegiatanService taKegiatanService;
    @Autowired
    private UnitKerjaKegiatanService unitKerjaKegiatanService;
    @Autowired
    private PenanggungJawabKegiatanService penanggungJawabKegiatanService;

    @Autowired
    public UrtugKegiatanController(UrtugKegiatanService urtugKegiatanService) {
        this.urtugKegiatanService = urtugKegiatanService;
    }


    /**
     *
     * memasangkan uraian tugas DPA dengan kegiatan yang sudah dipasang dengan penanggung jawabnya
     * create urtug kegiatan with master kegiatan
     *
     * @param inputWrapper
     * @return custom Message object
     */
    @RequestMapping(value = "/create-urtug-kegiatan-revisi", method = RequestMethod.POST)
    ResponseEntity<?> createUrtugKegiatanRevisi(@RequestBody KegiatanUrtugPenanggungJawabInputWrapper inputWrapper) {
        LOGGER.info("create urtug kegiatan penanggung jawab");

        for (KegiatanWrapper kegiatanWrapper
                : inputWrapper.getKegiatanList()) {
            List<PenanggungJawabKegiatan> penanggungJawabKegiatanList
                    = penanggungJawabKegiatanService.getByKegiatan(kegiatanWrapper.getKdUrusan(),
                                                                    kegiatanWrapper.getKdBidang(),
                                                                    kegiatanWrapper.getKdUnit(),
                                                                    kegiatanWrapper.getKdSub(),
                                                                    kegiatanWrapper.getTahun(),
                                                                    kegiatanWrapper.getKdProg(),
                                                                    kegiatanWrapper.getIdProg(),
                                                                    kegiatanWrapper.getKdKeg());
            for (PenanggungJawabKegiatan penanggungJawabKegiatan
                    : penanggungJawabKegiatanList) {
                UrtugKegiatan urtugKegiatan = new UrtugKegiatan();
                UrtugKegiatanId id
                        = new UrtugKegiatanId(inputWrapper.getKdUrtug(),
                                                inputWrapper.getKdJabatan(),
                                                inputWrapper.getKdJenisUrtug(),
                                                inputWrapper.getTahunUrtug(),
                                                penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdUrusan(),
                                                penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdBidang(),
                                                penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdUnit(),
                                                penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdSub(),
                                                penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getTahun(),
                                                penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdProg(),
                                                penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getIdProg(),
                                                penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdKeg(),
                                                penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getNipPegawai(),
                                                penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdStatusPenanggungJawab());

                urtugKegiatan.setUrtugKegiatanId(id);
                urtugKegiatan.setKuantitas(kegiatanWrapper.getKuantitas());
                urtugKegiatan.setSatuanKuantitas(kegiatanWrapper.getSatuanKuantitas());
                urtugKegiatan.setKualitas(kegiatanWrapper.getKualitas());
                urtugKegiatan.setWaktu(kegiatanWrapper.getWaktu());

                urtugKegiatanService.save(urtugKegiatan);
            }
        }

        return new ResponseEntity<Object>(new CustomMessage("pemasangan uraian tugas dengan daftar kegiatan berhasil"), HttpStatus.OK);
    }


    /**
     *
     * ambil daftar bahan   ajuan kontrak kerja, jika sudah pernah diajukan tidak akan diambil lagi
     * diambil berdasarkan nip pegawai dan unit kerja saat melakukan request
     *
     * service ini hanya untuk mendapatkan urtug dpa saja
     *
     * digunakan oleh pegawai yang akan membuat kontrak kerja
     *
     * @param nipPegawai
     * @param kdUnitKerja
     * @return list bahan ajuan kontrak kerja DPA
     */
    //sebagai parameter tambahkan juga kdjabatan, agar urtug pada jabatan lain tidak ikut terbawa
    @RequestMapping(value = "/get-urtug-dpa-ajuan-by-pegawai/{nipPegawai}/{kdUnitKerja}/{kdJabatan}", method = RequestMethod.GET)
    ResponseEntity<?> getUrtugDpaAjuangByPegawai(@PathVariable("nipPegawai") String nipPegawai,
                                                 @PathVariable("kdUnitKerja") String kdUnitKerja,
                                                 @PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get urtug dpa ajuan by pegawai "+nipPegawai+" in "+kdUnitKerja);

        List<UraianTugasJabatanJenisWrapper> outputWrappers
                = new ArrayList<>();

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(kdUnitKerja);

        List<TaKegiatan> taKegiatanList = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);

        List<UrtugKegiatan> urtugKegiatans
                = urtugKegiatanService.findByPegawaiAndUnitKerja(nipPegawai, unitKerjaKegiatan, kdJabatan);
        List<UrtugKegiatan> urtugKegiatansBelumDiajukanList
                = new ArrayList<>();

        for (UrtugKegiatan urtugKegiatan
                : urtugKegiatans) {
            if (urtugKegiatan.getPenanggungJawabKegiatan().getStatusApproval() == 0) {
                urtugKegiatansBelumDiajukanList.add(urtugKegiatan);
            }
        }

        for (UrtugKegiatan urtugKegiatan : urtugKegiatansBelumDiajukanList) {
            boolean found = false;
            for (UraianTugasJabatanJenisWrapper uraianTugasJabatanOutputWrapper : outputWrappers) {
                if (urtugKegiatan.getUrtugKegiatanId().getKdUrtug()
                        .equals(uraianTugasJabatanOutputWrapper.getKdUrtug())) {
                    //tambah jumlah kuantitas kegiatan pada wrapper
                    uraianTugasJabatanOutputWrapper.setKuantitas(uraianTugasJabatanOutputWrapper.getKuantitas()+1);

                    for (TaKegiatan taKegiatan : taKegiatanList) {
                        if (urtugKegiatan.getUrtugKegiatanId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg()) &&
                                urtugKegiatan.getUrtugKegiatanId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {
                            uraianTugasJabatanOutputWrapper.setBiaya(uraianTugasJabatanOutputWrapper.getBiaya() + taKegiatan.getPaguAnggaran().intValue());

                            break;
                        }
                    }

                    found =true;
                    break;
                }

            }

            if (!found) {
                //ambil biaya dari kegiatan
                for (TaKegiatan taKegiatan : taKegiatanList) {
                    if (urtugKegiatan.getUrtugKegiatanId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg()) &&
                            urtugKegiatan.getUrtugKegiatanId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {

                        outputWrappers
                                .add(new UraianTugasJabatanJenisWrapper(
                                        urtugKegiatan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                        urtugKegiatan.getUrtugKegiatanId().getKdUrtug(),
                                        urtugKegiatan.getUrtugKegiatanId().getKdJabatan(),
                                        urtugKegiatan.getUrtugKegiatanId().getKdJenisUrtug(),
                                        urtugKegiatan.getUrtugKegiatanId().getTahunUrtug(),
                                        1,
                                        "kegiatan",
                                        urtugKegiatan.getUraianTugasJabatanJenisUrtug().getKualitas(),
                                        urtugKegiatan.getUraianTugasJabatanJenisUrtug().getWaktu(),
                                        taKegiatan.getPaguAnggaran().intValue()));

                        break;
                    }

                }

            }

        }

        return new ResponseEntity<Object>(outputWrappers, HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk mendapatkan data urtug beserta dpanya yang telah di approve oleh pegawai yang dibebani
     * digunakan oleh pegawai untuk melihat kontrak kerjanya
     *
     * @param nipPegawai, kdUnitKerja, kdJabatan bawahan yang ingin dilihat
     * @return daftar urtug dan kegiatan dpanya
     */
    @RequestMapping(value = "/get-urtug-dpa-pegawai-approval/{nipPegawai}/{kdUnitKerja}/{kdJabatan}", method = RequestMethod.GET)
    ResponseEntity<?> getUrtugDpaPegawaiAproval(@PathVariable("nipPegawai") String nipPegawai,
                                                @PathVariable("kdUnitKerja") String kdUnitKerja,
                                                @PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get urtug dpa pegawai approval");

        List<UrtugKegiatanPegawaiWrapper> urtugKegiatanPegawaiWrappers
                = new ArrayList<>();

        List<UrtugKegiatan> urtugKegiatanPegawaiApprovalList
                = urtugKegiatanService.findByPegawaiApproval(nipPegawai, Year.now().getValue(), kdJabatan);

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(kdUnitKerja);

        List<TaKegiatan> taKegiatanList = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);

        for (UrtugKegiatan urtugKegiatan : urtugKegiatanPegawaiApprovalList) {
            for (TaKegiatan taKegiatan : taKegiatanList) {
                if (urtugKegiatan.getUrtugKegiatanId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg()) &&
                        urtugKegiatan.getUrtugKegiatanId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {
                    urtugKegiatanPegawaiWrappers
                            .add(new UrtugKegiatanPegawaiWrapper(
                                    urtugKegiatan.getUrtugKegiatanId().getKdUrtug(),
                                    urtugKegiatan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
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
                                    taKegiatan.getPaguAnggaran(),
                                    urtugKegiatan.getUrtugKegiatanId().getNipPegawai(),
                                    urtugKegiatan.getUrtugKegiatanId().getKdStatusPenanggungJawab(),
                                    urtugKegiatan.getPenanggungJawabKegiatan().getStatusPenanggungJawabKegiatan().getStatus()));

                    break;
                }
            }
        }

        return new ResponseEntity<>(urtugKegiatanPegawaiWrappers, HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk mengambil daftar kegiatan berdasarkan urtug dpa
     * digunakan oleh pegawai pada proses pengajuan kontrak kerja
     *
     * @return daftar kegiatan
     */
    @RequestMapping(value = "/get-kegiatan-by-urtug", method = RequestMethod.POST)
    ResponseEntity<?> getKegiatanByUrtug(@RequestBody UrtugJabatanJenisIdInputWrapper urtugJabatanWrapper) {
        LOGGER.info("get kegiatan by urtug");

        List<UrtugKegiatanWrapper> urtugKegiatanWrapperList = new ArrayList<>();

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(urtugJabatanWrapper.getKdUnitKerja());

        List<TaKegiatan> taKegiatanList = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);

        List<UrtugKegiatan> urtugKegiatanList
                = urtugKegiatanService.findAllByUraianTugasJabatan(
                                        urtugJabatanWrapper.getKdUrtug(),
                                        urtugJabatanWrapper.getKdJabatan(),
                                        urtugJabatanWrapper.getKdJenisUrtug(),
                                        urtugJabatanWrapper.getTahunUrtug(),
                                        urtugJabatanWrapper.getNipPegawai());

        boolean exist;
        for (UrtugKegiatan urtugKegiatan : urtugKegiatanList) {
            for (TaKegiatan taKegiatan : taKegiatanList) {
                if (urtugKegiatan.getUrtugKegiatanId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg()) &&
                        urtugKegiatan.getUrtugKegiatanId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {

                    //check if kegiatan already exist in wrapper list
                    exist = false;
                    for (UrtugKegiatanWrapper urtugKegiatanWrapper
                            : urtugKegiatanWrapperList) {
                        if (urtugKegiatan.getUrtugKegiatanId().getKdProg().equals(urtugKegiatanWrapper.getKdProg()) &&
                                urtugKegiatan.getUrtugKegiatanId().getKdKeg().equals(urtugKegiatanWrapper.getKdKeg())) {
                            exist = true;
                            break;
                        }

                    }


                    if (!exist) {
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
        }

        return new ResponseEntity<Object>(urtugKegiatanWrapperList, HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk mengapprove/memilih urtug dpa yang akan dijadikan kontrak kerja
     * digunakan oleh pegawai
     *
     * @param urtugJabatanWrapperList
     * @return notif uraian tugas sudah diapprove
     */
    @RequestMapping(value = "/approve-urtug-kegiatan", method = RequestMethod.PUT)
    ResponseEntity<?> approveUrtugKegiatan(@RequestBody List<UrtugKegiatanApprovalInputWrapper> urtugJabatanWrapperList) {
        LOGGER.info("approve urtug kegiatan");

        List<UrtugKegiatan> urtugKegiatanYangDiterimaList = new ArrayList<>();

        for (UrtugKegiatanApprovalInputWrapper inputWrapper
                : urtugJabatanWrapperList) {
                urtugKegiatanYangDiterimaList
                        .addAll(urtugKegiatanService.findByUraianTugasJabatanAndNip(inputWrapper.getKdUrtug(),
                                                                                    inputWrapper.getKdJabatan(),
                                                                                    inputWrapper.getKdJenisUrtug(),
                                                                                    inputWrapper.getTahunUrtug(),
                                                                                    inputWrapper.getNipPegawai()));
        }

        List<PenanggungJawabKegiatan> penanggungJawabKegiatanPendingList
                = penanggungJawabKegiatanService.getByKegiatanPendingPegawai(urtugJabatanWrapperList.get(0).getNipPegawai());


        boolean isMatch;
        for (PenanggungJawabKegiatan penanggungJawabKegiatan
                : penanggungJawabKegiatanPendingList) {
            isMatch = false;

            for (UrtugKegiatan urtugKegiatanYangDiterima
                    : urtugKegiatanYangDiterimaList) {
                if (penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdProg().equals(urtugKegiatanYangDiterima.getUrtugKegiatanId().getKdProg())
                        && penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getIdProg().equals(urtugKegiatanYangDiterima.getUrtugKegiatanId().getIdProg())
                        && penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdKeg().equals(urtugKegiatanYangDiterima.getUrtugKegiatanId().getKdKeg())) {

                    penanggungJawabKegiatan.setStatusApproval(1);

                    isMatch = true;
                    break;
                }
            }

            if (!isMatch) {
                penanggungJawabKegiatan.setStatusApproval(2);
            }

            penanggungJawabKegiatanService.create(penanggungJawabKegiatan);

        }

        return new ResponseEntity<>(new CustomMessage("uraian tugas dpa sudah diapprove"), HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk menghapus data urtug kegiatan
     * digunakan oleh admin unit kerja
     *
     * @return notifikasi proses
     */
    @RequestMapping(value = "/delete-urtug-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> deleteUrtugKegiatan(@RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
        LOGGER.info("delete urtug kegiatan");

        List<UrtugKegiatan> urtugKegiatans
                = urtugKegiatanService.findByKegiatanAndUrtug(urtugKegiatanInputWrapper.getKdUrtug(),
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
                                                                urtugKegiatanInputWrapper.getKdKeg());

        for (UrtugKegiatan urtugKegiatan
                : urtugKegiatans) {
            urtugKegiatanService.delete(
                    new UrtugKegiatanId(
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
                            urtugKegiatan.getUrtugKegiatanId().getNipPegawai(),
                            urtugKegiatan.getUrtugKegiatanId().getKdStatusPenanggungJawab()
                    ));
        }

        return new ResponseEntity<Object>(new CustomMessage("urtug kegiatan deleted"), HttpStatus.OK);
    }

//====================================================================================================================//
    ResponseEntity<?> getUrtugKegiatan() {
        return null;
    }

//    @RequestMapping(value = "/get-urtug-kegiatan-by-jabatan", method = RequestMethod.POST)
//    ResponseEntity<?> getUrtugKegiatanByUrtugJabatan(
//            @RequestBody UrtugJabatanJenisIdInputWrapper urtugJabatanWrapper) {
//        List<UrtugKegiatanWrapper> urtugKegiatanWrapperList = new ArrayList<>();
//
//        UnitKerjaKegiatan unitKerjaKegiatan
//                = unitKerjaKegiatanService.findByKdUnitKerja(urtugJabatanWrapper.getKdUnitKerja());
//
//        List<TaKegiatan> taKegiatanList = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);
//
//        List<UrtugKegiatan> urtugKegiatanList
//                = urtugKegiatanService.findAllByUraianTugasJabatan(
//                        urtugJabatanWrapper.getKdUrtug(),
//                        urtugJabatanWrapper.getKdJabatan(),
//                        urtugJabatanWrapper.getKdJenisUrtug(),
//                        urtugJabatanWrapper.getTahunUrtug());
//
//        for (UrtugKegiatan urtugKegiatan : urtugKegiatanList) {
//            for (TaKegiatan taKegiatan : taKegiatanList) {
//                if (urtugKegiatan.getUrtugKegiatanId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg()) &&
//                        urtugKegiatan.getUrtugKegiatanId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {
//                    urtugKegiatanWrapperList
//                            .add(new UrtugKegiatanWrapper(
//                                    urtugKegiatan.getUrtugKegiatanId().getKdUrtug(),
//                                    urtugKegiatan.getUrtugKegiatanId().getKdJabatan(),
//                                    urtugKegiatan.getUrtugKegiatanId().getKdJenisUrtug(),
//                                    urtugKegiatan.getUrtugKegiatanId().getTahunUrtug(),
//                                    urtugKegiatan.getUrtugKegiatanId().getKdUrusan(),
//                                    urtugKegiatan.getUrtugKegiatanId().getKdBidang(),
//                                    urtugKegiatan.getUrtugKegiatanId().getKdUnit(),
//                                    urtugKegiatan.getUrtugKegiatanId().getKdSub(),
//                                    urtugKegiatan.getUrtugKegiatanId().getTahun(),
//                                    urtugKegiatan.getUrtugKegiatanId().getKdProg(),
//                                    urtugKegiatan.getUrtugKegiatanId().getIdProg(),
//                                    urtugKegiatan.getUrtugKegiatanId().getKdKeg(),
//                                    taKegiatan.getKetKegiatan(),
//                                    taKegiatan.getPaguAnggaran()));
//                }
//            }
//        }
//
//        return new ResponseEntity<Object>(urtugKegiatanWrapperList, HttpStatus.OK);
//    }

    @RequestMapping(value = "/get-urtug-program-by-jabatan", method = RequestMethod.POST)
    ResponseEntity<?> getUrtugProgramByUrtugJabatan(
            @RequestBody UrtugJabatanJenisIdInputWrapper urtugJabatanWrapper) {
        LOGGER.info("get urtug program by urtug jabatan");

        List<UrtugProgramWrapper> urtugProgramWrapperList = new ArrayList<>();

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(urtugJabatanWrapper.getKdUnitKerja());

        List<TaProgram> taProgramList
                = taProgramDao.findAllByKdUnitKerja(
                    unitKerjaKegiatan.getKdUrusan(),
                    unitKerjaKegiatan.getKdBidang(),
                    unitKerjaKegiatan.getKdUnit());

        List<UrtugKegiatan> urtugKegiatanList
                = urtugKegiatanService.findAllByUraianTugasJabatan(
                    urtugJabatanWrapper.getKdUrtug(),
                    urtugJabatanWrapper.getKdJabatan(),
                    urtugJabatanWrapper.getKdJenisUrtug(),
                    urtugJabatanWrapper.getTahunUrtug(),
                urtugJabatanWrapper.getNipPegawai());

        boolean notFound;
        for (UrtugKegiatan urtugKegiatan : urtugKegiatanList) {
            notFound = true;

            for (UrtugProgramWrapper urtugProgramWrapper
                    : urtugProgramWrapperList) {
                if (urtugProgramWrapper.getKdUrusan().equals(urtugKegiatan.getUrtugKegiatanId().getKdUrusan()) &&
                        urtugProgramWrapper.getKdBidang().equals(urtugKegiatan.getUrtugKegiatanId().getKdBidang()) &&
                        urtugProgramWrapper.getKdUnit().equals(urtugKegiatan.getUrtugKegiatanId().getKdUnit()) &&
                        urtugProgramWrapper.getKdSub().equals(urtugKegiatan.getUrtugKegiatanId().getKdSub()) &&
                        urtugProgramWrapper.getTahun().equals(urtugKegiatan.getUrtugKegiatanId().getTahun()) &&
                        urtugProgramWrapper.getKdProg().equals(urtugKegiatan.getUrtugKegiatanId().getKdProg()) &&
                        urtugProgramWrapper.getIdProg().equals(urtugKegiatan.getUrtugKegiatanId().getIdProg())) {
                    notFound = false;

                    break;
                }
            }

            if (notFound) {
                urtugProgramWrapperList
                        .add(new UrtugProgramWrapper(
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
                                ""
                        ));
            }
        }

        //set keterangan from simda
        for (UrtugProgramWrapper program
                : urtugProgramWrapperList) {
            for (TaProgram taProgram
                    : taProgramList) {
                if (program.getKdUrusan().equals(taProgram.getTaProgramId().getKdUrusan()) &&
                        program.getKdBidang().equals(taProgram.getTaProgramId().getKdBIdang()) &&
                        program.getKdUnit().equals(taProgram.getTaProgramId().getKdUnit()) &&
                        program.getKdSub().equals(taProgram.getTaProgramId().getKdSub()) &&
                        program.getTahun().equals(taProgram.getTaProgramId().getTahun()) &&
                        program.getKdProg().equals(taProgram.getTaProgramId().getKdProg()) &&
                        program.getIdProg().equals(taProgram.getTaProgramId().getIdProg())) {

                    program.setKetProgram(taProgram.getKetProgram());
                }
            }
        }

        return new ResponseEntity<Object>(urtugProgramWrapperList, HttpStatus.OK);
    }



    //tambahkan algoritma akumulasi jumlah waktu, biaya berdasarkan jumlah kegiatan yang ditambahkan
    @RequestMapping(value = "/create-urtug-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> createUrtugKegiatan(@RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
        LOGGER.info("create urtug kegiatan");

        TaKegiatan taKegiatan
                = taKegiatanDao.findByTaKegiatanId(
                        new TaKegiatanId(
                                urtugKegiatanInputWrapper.getKdUrusan(),
                                urtugKegiatanInputWrapper.getKdBidang(),
                                urtugKegiatanInputWrapper.getKdUnit(),
                                urtugKegiatanInputWrapper.getKdSub(),
                                urtugKegiatanInputWrapper.getTahun(),
                                urtugKegiatanInputWrapper.getKdProg(),
                                urtugKegiatanInputWrapper.getIdProg(),
                                urtugKegiatanInputWrapper.getKdKeg()));

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
        urtugKegiatan.setKuantitas(urtugKegiatanInputWrapper.getKuantitas());
        urtugKegiatan.setSatuanKuantitas(urtugKegiatanInputWrapper.getSatuanKuantitas());
        urtugKegiatan.setKualitas(100);
        urtugKegiatan.setWaktu(urtugKegiatanInputWrapper.getWaktu());
        urtugKegiatan.setBiaya(taKegiatan.getPaguAnggaran());

        urtugKegiatanService.save(urtugKegiatan);

        return new ResponseEntity<Object>(new CustomMessage("urtug kegiatan created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-urtug-kegiatan-by-id/", method = RequestMethod.POST)
    ResponseEntity<?> getUrtugKegiatanById(
            @RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
        LOGGER.info("get urtug kegiatan by id");

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(urtugKegiatanInputWrapper.getKdUnitKerja());

        List<TaKegiatan> taKegiatanList = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);

        UrtugKegiatan urtugKegiatan
                = urtugKegiatanService.find(new UrtugKegiatanId(
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

        UrtugKegiatanWrapper urtugKegiatanWrapper = null;
        for (TaKegiatan taKegiatan
                : taKegiatanList) {
            if (urtugKegiatan.getUrtugKegiatanId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg()) &&
                    urtugKegiatan.getUrtugKegiatanId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {
                urtugKegiatanWrapper
                        = new UrtugKegiatanWrapper(
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
                                taKegiatan.getPaguAnggaran(),
                                urtugKegiatan.getKuantitas(),
                                urtugKegiatan.getSatuanKuantitas(),
                                urtugKegiatan.getKualitas(),
                                urtugKegiatan.getWaktu());
            }
        }

        return new ResponseEntity<Object>(urtugKegiatanWrapper, HttpStatus.OK);
    }

//    @RequestMapping(value = "/get-urtug-program-by-id/", method = RequestMethod.POST)
//    ResponseEntity<?> getUrtugProgramById(
//            @RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
//        LOGGER.info("get urtug kegiatan by id");
//
//        UnitKerjaKegiatan unitKerjaKegiatan
//                = unitKerjaKegiatanService.findByKdUnitKerja(urtugKegiatanInputWrapper.getKdUnitKerja());
//
//        List<TaKegiatan> taKegiatanList = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);
//
//        UrtugKegiatan urtugKegiatan
//                = urtugKegiatanService.find
//        ));
//
//        UrtugKegiatanWrapper urtugKegiatanWrapper = null;
//        for (TaKegiatan taKegiatan
//                : taKegiatanList) {
//            if (urtugKegiatan.getUrtugKegiatanId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg()) &&
//                    urtugKegiatan.getUrtugKegiatanId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {
//                urtugKegiatanWrapper
//                        = new UrtugKegiatanWrapper(
//                        urtugKegiatan.getUrtugKegiatanId().getKdUrtug(),
//                        urtugKegiatan.getUrtugKegiatanId().getKdJabatan(),
//                        urtugKegiatan.getUrtugKegiatanId().getKdJenisUrtug(),
//                        urtugKegiatan.getUrtugKegiatanId().getTahunUrtug(),
//                        urtugKegiatan.getUrtugKegiatanId().getKdUrusan(),
//                        urtugKegiatan.getUrtugKegiatanId().getKdBidang(),
//                        urtugKegiatan.getUrtugKegiatanId().getKdUnit(),
//                        urtugKegiatan.getUrtugKegiatanId().getKdSub(),
//                        urtugKegiatan.getUrtugKegiatanId().getTahun(),
//                        urtugKegiatan.getUrtugKegiatanId().getKdProg(),
//                        urtugKegiatan.getUrtugKegiatanId().getIdProg(),
//                        urtugKegiatan.getUrtugKegiatanId().getKdKeg(),
//                        taKegiatan.getKetKegiatan(),
//                        taKegiatan.getPaguAnggaran(),
//                        urtugKegiatan.getKuantitas(),
//                        urtugKegiatan.getSatuanKuantitas(),
//                        urtugKegiatan.getKualitas(),
//                        urtugKegiatan.getWaktu());
//            }
//        }
//
//        return new ResponseEntity<Object>(urtugKegiatanWrapper, HttpStatus.OK);
//    }

    @RequestMapping(value = "/create-urtug-program", method = RequestMethod.POST)
    ResponseEntity<?> createUrtugProgram(
            @RequestBody UrtugProgramInputWrapper inputWrapper) {
        LOGGER.info("create urtug program");

        TaProgram taProgram
                = taProgramDao.findByTaProgramId(
                        new TaProgramId(
                                inputWrapper.getKdUrusan(),
                                inputWrapper.getKdBidang(),
                                inputWrapper.getKdUnit(),
                                inputWrapper.getKdSub(),
                                inputWrapper.getTahun(),
                                inputWrapper.getKdProg(),
                                inputWrapper.getIdProg()));

        for (TaKegiatan kegiatan
                : taProgram.getTaKegiatanList()) {
            UrtugKegiatan urtugKegiatan = new UrtugKegiatan();

            urtugKegiatan.setUrtugKegiatanId(
                    new UrtugKegiatanId(
                            inputWrapper.getKdUrtug(),
                            inputWrapper.getKdJabatan(),
                            inputWrapper.getKdJenisUrtug(),
                            inputWrapper.getTahunUrtug(),
                            inputWrapper.getKdUrusan(),
                            inputWrapper.getKdBidang(),
                            inputWrapper.getKdUnit(),
                            inputWrapper.getKdSub(),
                            inputWrapper.getTahun(),
                            inputWrapper.getKdProg(),
                            inputWrapper.getIdProg(),
                            kegiatan.getTaKegiatanId().getKdKegiatan()
                    ));
            urtugKegiatan.setKuantitas(taProgram.getTaKegiatanList().size());
            urtugKegiatan.setSatuanKuantitas("kegiatan");
            urtugKegiatan.setKualitas(100);
            urtugKegiatan.setWaktu(12);
            urtugKegiatan.setBiaya(kegiatan.getPaguAnggaran());

            urtugKegiatanService.save(urtugKegiatan);

        }

        return new ResponseEntity<Object>(new CustomMessage("urtug program created"), HttpStatus.CREATED);
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

    @RequestMapping(value = "/delete-urtug-program", method = RequestMethod.POST)
    ResponseEntity<?> deleteUrtugProgram(@RequestBody UrtugKegiatanInputWrapper urtugKegiatanInputWrapper) {
        List<UrtugKegiatan> urtugKegiatanList
                = urtugKegiatanService.findByProgramAndUrtug(
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
                        urtugKegiatanInputWrapper.getIdProg());

        for (UrtugKegiatan urtugKegiatan
                : urtugKegiatanList) {
            urtugKegiatanService.delete(
                    new UrtugKegiatanId(
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
                            urtugKegiatan.getUrtugKegiatanId().getKdKeg()
                    ));
        }

        return new ResponseEntity<Object>(new CustomMessage("urtug program deleted"), HttpStatus.OK);

    }

}
