package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.util.FileUploader;
import com.pemda.ekinerjademo.wrapper.input.TemplateLainInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.UrtugBulananIdInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.TemplateLainHistoryWrapper;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 26/12/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class TemplateLainController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TemplateLainController.class);

    @Autowired
    private TemplateLainService templateLainService;
    @Autowired
    private QutPegawaiCloneService qutPegawaiService;
    @Autowired
    private AkunPegawaiService akunPegawaiService;
    @Autowired
    private UraianTugasPegawaiBulananService uraianTugasPegawaiBulananService;
    @Autowired
    private KegiatanPegawaiBulananService kegiatanPegawaiBulananService;
    @Autowired
    private PenanggungJawabKegiatanService penanggungJawabKegiatanService;

    @RequestMapping(value = "/create-template-lain",
            method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    ResponseEntity<?> createTemplateLain(
            @RequestPart("metadata") TemplateLainInputWrapper templateLainInputWrapper,
            @RequestPart("file") MultipartFile fileTemplateLain) {
        LOGGER.info("create template lain");

        FileUploader uploader = new FileUploader();

        String kdTemplateLain = String.valueOf(new Date().getTime());

        TemplateLain templateLain = new TemplateLain();

        templateLain.setKdTemplateLain(kdTemplateLain);
        templateLain.setKdUnitKerja(templateLainInputWrapper.getKdUnitKerja());
        templateLain.setNipPegawai(templateLainInputWrapper.getNipPegawai());
        templateLain.setKeterangan(templateLainInputWrapper.getKeterangan());
        templateLain.setKdNaskahPenugasan(templateLainInputWrapper.getKdNaskahPenugasan());
        templateLain.setJenisNaskahPenugasan(templateLainInputWrapper.getJenisNaskahPenugasan());

        templateLain.setKdUrtug(templateLainInputWrapper.getKdUrtug());
        templateLain.setKdJabatan(templateLainInputWrapper.getKdJabatan());
        templateLain.setTahunUrtug(templateLainInputWrapper.getTahunUrtug());
        templateLain.setKdJenisUrtug(templateLainInputWrapper.getKdJenisUrtug());
        templateLain.setBulanUrtug(templateLainInputWrapper.getBulanUrtug());

        templateLain.setStatusPenilaian(0);
        templateLain.setTanggalPembuatanMilis(new Date().getTime());


        if (templateLainInputWrapper.getKdTemplateLainBawahan() == null) {
//            templateLain.setPathFile(kdTemplateLain+"."+fileTemplateLain.getOriginalFilename().split("\\.")[1]);
            templateLain.setPathFile(kdTemplateLain+"."+ FilenameUtils.getExtension(fileTemplateLain.getOriginalFilename()));
            templateLain.setPathPenilaian(kdTemplateLain);

            uploader.uploadFileTemplateLain(fileTemplateLain, kdTemplateLain);
        } else {
            TemplateLain templateLainBawahan
                    = templateLainService.getTemplateLain(templateLainInputWrapper.getKdTemplateLainBawahan());

            templateLain.setPathFile(templateLainInputWrapper.getNamaFileLaporanBawahan());
            templateLain.setPathPenilaian(templateLainBawahan.getPathPenilaian()+"."+kdTemplateLain);

            templateLainBawahan.setStatusPenilaian(2);
            templateLainService.create(templateLainBawahan);
        }

        QutPegawai pegawaiPembuat = qutPegawaiService.getQutPegawai(templateLainInputWrapper.getNipPegawai());
        if (akunPegawaiService.isPegawaiSekretaris(pegawaiPembuat)) {
            templateLain.setApprovalSekretaris(1);
        }

        templateLainService.create(templateLain);

        return new ResponseEntity<Object>(
                new CustomMessage("template lain created"), HttpStatus.CREATED);
    }

    /**
     *
     * rules saat ini :
     * yang dapat membuat/mengerjakan laporan hanya pelaksana atau kasie
     * jika yang membuat adalah kasie maka sekaligus dengan proses approval
     * jika dalam pembuatan laporan hanya kasie tanpa bantuan pelaksana maka pelaksana tidak memperoleh poin realisasi
     *
     * @param templateLainInputWrapper
     * @return
     */
    @RequestMapping(value = "/create-template-lain-data", method = RequestMethod.POST)
    ResponseEntity<?> createTemplateLainData(@RequestBody TemplateLainInputWrapper templateLainInputWrapper) {
        LOGGER.info("create template lain data");

        boolean isLaporanDPA = false;
        if (templateLainInputWrapper.getKdUrtug() == null)
            isLaporanDPA = true;

        String kdTemplateLain = String.valueOf(new Date().getTime());

        TemplateLain templateLain = new TemplateLain();

        templateLain.setKdTemplateLain(kdTemplateLain);
        templateLain.setKdUnitKerja(templateLainInputWrapper.getKdUnitKerja());
        templateLain.setNipPegawai(templateLainInputWrapper.getNipPegawai());
        templateLain.setKeterangan(templateLainInputWrapper.getKeterangan());
        if (templateLainInputWrapper.getDurasiPengerjaan() == null) {
            templateLain.setDurasiPengerjaan(0);
        } else {
            templateLain.setDurasiPengerjaan(templateLainInputWrapper.getDurasiPengerjaan());
        }
        templateLain.setKdNaskahPenugasan(templateLainInputWrapper.getKdNaskahPenugasan());
        templateLain.setJenisNaskahPenugasan(templateLainInputWrapper.getJenisNaskahPenugasan());

        if (!isLaporanDPA) {
            templateLain.setKdUrtug(templateLainInputWrapper.getKdUrtug());
            templateLain.setKdJabatan(templateLainInputWrapper.getKdJabatan());
            templateLain.setTahunUrtug(templateLainInputWrapper.getTahunUrtug());
            templateLain.setKdJenisUrtug(templateLainInputWrapper.getKdJenisUrtug());
            templateLain.setBulanUrtug(templateLainInputWrapper.getBulanUrtug());
        }
        else {
            templateLain.setKdUrusan(templateLainInputWrapper.getKdUrusan());
            templateLain.setKdBidang(templateLainInputWrapper.getKdBidang());
            templateLain.setKdUnit(templateLainInputWrapper.getKdUnit());
            templateLain.setKdSub(templateLainInputWrapper.getKdSub());
            templateLain.setTahun(templateLainInputWrapper.getTahun());
            templateLain.setKdProg(templateLainInputWrapper.getKdProg());
            templateLain.setIdProg(templateLainInputWrapper.getIdProg());
            templateLain.setKdKeg(templateLainInputWrapper.getKdKeg());
            templateLain.setKdStatusPenanggungJawab(templateLainInputWrapper.getKdStatusPenanggungJawab());
        }

        templateLain.setStatusPenilaian(0);
        templateLain.setTanggalPembuatanMilis(new Date().getTime());
        templateLain.setStatusBaca(0);

        /**kondisi(laporan urtug non dpa) ini dapat di akses oleh kasie dan pelaksana yang membuat laporan baru; 31 juli 2018**/
        if (templateLainInputWrapper.getKdTemplateLainBawahan() == null) {
//            templateLain.setPathFile(kdTemplateLain+"."+fileTemplateLain.getOriginalFilename().split("\\.")[1]);
            templateLain.setPathFile(kdTemplateLain+"."+ FilenameUtils.getExtension(templateLainInputWrapper.getNamaFile()));
            templateLain.setPathPenilaian(kdTemplateLain);
            /** khusus untuk laporan dpa**/
            if (isLaporanDPA) templateLain.setStatusPenilaian(2);
        }
        /**kondisi ini hanya dapat di akses oleh kasie yang melanjutkan laporan bawahannya; 31 juli 2018
        ubah realisasi pelaksana**/
        /** kondisi ini tidak akan bisa diakses pada proses laporan dpa; 16 agustus 2018**/
        else {
            TemplateLain templateLainBawahan
                    = templateLainService.getTemplateLain(templateLainInputWrapper.getKdTemplateLainBawahan());

            templateLain.setKeterangan(templateLainBawahan.getKeterangan());
            templateLain.setPathFile(templateLainInputWrapper.getNamaFileLaporanBawahan());
            templateLain.setPathPenilaian(templateLainBawahan.getPathPenilaian()+"."+kdTemplateLain);
            //status penilaian 2 diterima;
            templateLainBawahan.setStatusPenilaian(2);
            templateLainService.create(templateLainBawahan);

            //langsung approve untuk bawahan
            templateLainService.approve(templateLainBawahan.getKdTemplateLain());
        }

//        QutPegawai pegawaiPembuat = qutPegawaiService.getQutPegawai(templateLainInputWrapper.getNipPegawai());
//        if (akunPegawaiService.isPegawaiSekretaris(pegawaiPembuat)) {
//            templateLain.setApprovalSekretaris(1);
//        }

        /** proses penambahan realisasi dibedakan berdasarkan jenis tugas yang dikerjakan
         * seperti urtug non dpa atau dpa**/
        if (!isLaporanDPA) {
            /**jika yang membuat atau melanjutkan adalah kasie maka ubah realisasi dirinya dengan atasan**/
            QutPegawai pegawaiPembuat = qutPegawaiService.getQutPegawai(templateLainInputWrapper.getNipPegawai());
            if (akunPegawaiService.isPegawaiKasie(pegawaiPembuat)) {
                LOGGER.info("pegawai melanjutkan is kasie");
                templateLain.setStatusPenilaian(2);

                List<UraianTugasPegawaiBulanan> uraianTugasPegawaiBulananSKPDList
                        = uraianTugasPegawaiBulananService.getByUnitKerja(
                        templateLain.getKdUnitKerja(),
                        templateLain.getBulanUrtug(),
                        templateLain.getTahunUrtug());

                for (UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan : uraianTugasPegawaiBulananSKPDList) {
                    if (uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdUrtug()
                            .equals(templateLainInputWrapper.getKdUrtug())
                            && uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJabatan()
                            .equals(templateLainInputWrapper.getKdJabatan())
                            && uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJenisUrtug()
                            .equals(templateLainInputWrapper.getKdJenisUrtug())
                            && uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getBulanUrtug()
                            .equals(templateLainInputWrapper.getBulanUrtug())
                            && uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getTahunUrtug()
                            .equals(templateLainInputWrapper.getTahunUrtug())
                            && uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getNipPegawai()
                            .equals(templateLainInputWrapper.getNipPegawai())) {
                        LOGGER.info("same urtug kasie");
                        uraianTugasPegawaiBulanan.setRealisasiKuantitas(uraianTugasPegawaiBulanan.getRealisasiKuantitas() + 1);
                        uraianTugasPegawaiBulananService.create(uraianTugasPegawaiBulanan);

                        break;

                    }
                }

                LOGGER.info("jumlah uraian tugas unit kerja "
                        + templateLain.getKdUnitKerja()
                        + " : "
                        + uraianTugasPegawaiBulananSKPDList.size());

                for (UrtugBulananIdInputWrapper urtugBulananId : templateLainInputWrapper.getDaftarUrtugAtasan()) {
                    for (UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan : uraianTugasPegawaiBulananSKPDList) {
                        LOGGER.error(
                                urtugBulananId.getKdUrtug() + ";"
                                        + urtugBulananId.getKdJabatan() + ";"
                                        + urtugBulananId.getKdJenisUrtug() + ";"
                                        + urtugBulananId.getBulanUrtug() + ";"
                                        + urtugBulananId.getTahunUrtug() + ";"
                                        + urtugBulananId.getNipPegawai() + ";");
                        LOGGER.info(
                                uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdUrtug() + ";"
                                        + uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJabatan() + ";"
                                        + uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJenisUrtug() + ";"
                                        + uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getBulanUrtug() + ";"
                                        + uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getTahunUrtug() + ";"
                                        + uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getNipPegawai() + ";");

                        if (uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdUrtug()
                                .equals(urtugBulananId.getKdUrtug())
                                && uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJabatan()
                                .equals(urtugBulananId.getKdJabatan())
                                && uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJenisUrtug()
                                .equals(urtugBulananId.getKdJenisUrtug())
                                && uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getBulanUrtug()
                                .equals(urtugBulananId.getBulanUrtug())
                                && uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getTahunUrtug()
                                .equals(urtugBulananId.getTahunUrtug())
                                && uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getNipPegawai()
                                .equals(urtugBulananId.getNipPegawai())) {
                            LOGGER.info("same success update reailisasi kuantitas");
                            uraianTugasPegawaiBulanan.setRealisasiKuantitas(uraianTugasPegawaiBulanan.getRealisasiKuantitas() + 1);
                            uraianTugasPegawaiBulananService.create(uraianTugasPegawaiBulanan);

                            break;

                        }
                    }
                }

            }
        }
        /** jika laporan dibuat untuk dpa**/
        else {
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.of("Asia/Jakarta")).toLocalDate();
            int month = localDate.getMonthValue() - 1; //value getMonthValue LocalDate start from 1 to 12

            List<KegiatanPegawaiBulanan> kegiatanPegawaiBulanans
                    = kegiatanPegawaiBulananService.getKegiatanBulanan(templateLainInputWrapper.getKdUrusan(),
                                                                        templateLainInputWrapper.getKdBidang(),
                                                                        templateLainInputWrapper.getKdUnit(),
                                                                        templateLainInputWrapper.getKdSub(),
                                                                        templateLainInputWrapper.getTahun(),
                                                                        templateLainInputWrapper.getKdProg(),
                                                                        templateLainInputWrapper.getIdProg(),
                                                                        templateLainInputWrapper.getKdKeg(),
                                                                        month);
            /**jika sebelumnya sudah ada realisasi tinggal tambah realisasi kuantitas +1 **/
            if (!kegiatanPegawaiBulanans.isEmpty()) {
                for (KegiatanPegawaiBulanan obj : kegiatanPegawaiBulanans) {
                    obj.setTargetKuantitas(obj.getTargetKuantitas() + 1);
                    obj.setRealisasiKuantitas(obj.getRealisasiKuantitas() + 1);

                    kegiatanPegawaiBulananService.create(obj);
                }
            }
            /**jika sebelumnya belum ada realisasi maka buat baru pada kegiatan pegawai bulanan **/
            else {
                List<PenanggungJawabKegiatan> penanggungJawabKegiatans
                        = penanggungJawabKegiatanService.getByKegiatan(templateLainInputWrapper.getKdUrusan(),
                                                                        templateLainInputWrapper.getKdBidang(),
                                                                        templateLainInputWrapper.getKdUnit(),
                                                                        templateLainInputWrapper.getKdSub(),
                                                                        templateLainInputWrapper.getTahun(),
                                                                        templateLainInputWrapper.getKdProg(),
                                                                        templateLainInputWrapper.getIdProg(),
                                                                        templateLainInputWrapper.getKdKeg());

                for (PenanggungJawabKegiatan obj : penanggungJawabKegiatans) {
                    KegiatanPegawaiBulananId id
                            = new KegiatanPegawaiBulananId(obj.getPenanggungJawabKegiatanId().getKdUrusan(),
                                                            obj.getPenanggungJawabKegiatanId().getKdBidang(),
                                                            obj.getPenanggungJawabKegiatanId().getKdUnit(),
                                                            obj.getPenanggungJawabKegiatanId().getKdSub(),
                                                            obj.getPenanggungJawabKegiatanId().getTahun(),
                                                            obj.getPenanggungJawabKegiatanId().getKdProg(),
                                                            obj.getPenanggungJawabKegiatanId().getIdProg(),
                                                            obj.getPenanggungJawabKegiatanId().getKdKeg(),
                                                            obj.getPenanggungJawabKegiatanId().getNipPegawai(),
                                                            obj.getPenanggungJawabKegiatanId().getKdStatusPenanggungJawab(),
                                                            month);

                    KegiatanPegawaiBulanan kegiatanPegawaiBulanan = new KegiatanPegawaiBulanan();
                    kegiatanPegawaiBulanan.setKegiatanPegawaiBulananId(id);
                    kegiatanPegawaiBulanan.setTargetKuantitas(1);
                    kegiatanPegawaiBulanan.setTargetKualitas(100);
                    kegiatanPegawaiBulanan.setTargetSatuanKuantitas("");
                    kegiatanPegawaiBulanan.setRealisasiKuantitas(1);
                    kegiatanPegawaiBulanan.setRealisasiKualitas(100);

                    kegiatanPegawaiBulananService.create(kegiatanPegawaiBulanan);
                }

            }
        }
        /** proses penambahan realisasi dibedakan berdasarkan jenis tugas yang dikerjakan
         * seperti urtug non dpa atau dpa selesai**/

        templateLainService.create(templateLain);

        return new ResponseEntity<Object>(
                new CustomMessage(kdTemplateLain), HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/create-template-lain-file",
            method = RequestMethod.POST)
    ResponseEntity<?> createTemplateLainFile(@RequestParam("file") MultipartFile fileTemplateLain) {
        LOGGER.info("create template lain");

        FileUploader uploader = new FileUploader();
//            templateLain.setPathFile(kdTemplateLain+"."+fileTemplateLain.getOriginalFilename().split("\\.")[1]);
//            templateLain.setPathFile(kdTemplateLain+"."+ FilenameUtils.getExtension(fileTemplateLain.getOriginalFilename()));
        uploader.uploadFileTemplateLain(fileTemplateLain, FilenameUtils.removeExtension(fileTemplateLain.getOriginalFilename()));

        return new ResponseEntity<Object>(
                new CustomMessage("template lain created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/approve-template-lain/{kdLaporan}", method = RequestMethod.PUT)
    ResponseEntity<?> approveTemplateLain(@PathVariable("kdLaporan") String kdLaporan){
        LOGGER.info("approve laporan bawahan template lain");

        templateLainService.approve(kdLaporan);

        return new ResponseEntity<>(new CustomMessage("laporan sudah diapprove"), HttpStatus.OK);
    }


    @RequestMapping(value = "/get-template-lain-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getTemplateLainByPembuat(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get template lain by pembuat");

        List<TemplateLainHistoryWrapper> templateLainHistoryWrapperList
                = new ArrayList<>();
        List<TemplateLain> templateLainList
                = templateLainService.getByPembuat(nipPembuat);

        for (TemplateLain templateLain : templateLainList) {
            templateLainHistoryWrapperList
                    .add(new TemplateLainHistoryWrapper(
                            templateLain.getKdTemplateLain(),
                            templateLain.getKeterangan(),
                            FilenameUtils.removeExtension(templateLain.getPathFile()),
                            FilenameUtils.getExtension(templateLain.getPathFile()),
                            templateLain.getStatusPenilaian(),
                            templateLain.getTanggalPembuatanMilis(),
                            templateLain.getAlasanPenolakan(),
                            "template lain",
                            15,
                            templateLain.getApprovalPenandatangan()));
        }

        return new ResponseEntity<Object>(templateLainHistoryWrapperList, HttpStatus.OK);
    }

}
