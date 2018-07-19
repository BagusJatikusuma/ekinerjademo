package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKeterangan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratKeterangan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratKeteranganId;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TkdUnkCloneDao;
import com.pemda.ekinerjademo.service.AkunPegawaiService;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratKeteranganService;
import com.pemda.ekinerjademo.util.BarcodeGenerator;
import com.pemda.ekinerjademo.util.DateUtilities;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.util.EkinerjaXMLParser;
import com.pemda.ekinerjademo.wrapper.input.SuratKeteranganInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.SuratKeteranganWrapper;
import com.pemda.ekinerjademo.wrapper.output.SuratPerintahHistoryWrapper;
import com.pemda.ekinerjademo.wrapper.output.SuratPerintahTargetWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by bayu on 18/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratKeteranganController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratKeteranganController.class);

    @Autowired private SuratKeteranganService suratKeteranganService;

    //    @Autowired
//    private QutPegawaiService qutPegawaiService; //test clone

    @Autowired
    private QutPegawaiCloneService qutPegawaiService;

    //    @Autowired private TkdUnkDao tkdUnkDao; //test clone
    @Autowired private TkdUnkCloneDao tkdUnkDao;

    @Autowired private AkunPegawaiService akunPegawaiService;

    @RequestMapping(value = "/create-surat-keterangan", method = RequestMethod.POST)
    ResponseEntity<?> createSuratKeterangan(@RequestBody SuratKeteranganInputWrapper inputWrapper) {
        LOGGER.info("create surat keterangan");

        EkinerjaXMLBuilder ekinerjaXMLBuilder = new EkinerjaXMLBuilder();

        String kdSuratKeterangan = String.valueOf(new Date().getTime());

        //build target surat keterangan
        List<TargetSuratKeterangan> targetSuratKeteranganList = new ArrayList<>();

        for (String nipTargetSuratKeterangan
//                : inputWrapper.getTargetSuratKeteranganList()
                : inputWrapper.getNipPegawaiKeterangan()) {
            TargetSuratKeteranganId targetSuratKeteranganId
                    = new TargetSuratKeteranganId(kdSuratKeterangan, nipTargetSuratKeterangan);

            TargetSuratKeterangan targetSuratKeterangan
                    = new TargetSuratKeterangan();
            targetSuratKeterangan.setTargetSuratKeteranganId(targetSuratKeteranganId);
            targetSuratKeterangan.setApproveStatus(0);
            targetSuratKeterangan.setStatusDiterima(0);
            targetSuratKeterangan.setStatusBaca(0);

            targetSuratKeteranganList.add(targetSuratKeterangan);

        }
        //build surat keterangan
        SuratKeterangan suratKeterangan = new SuratKeterangan();

        suratKeterangan.setKdSuratKeterangan(kdSuratKeterangan);

        suratKeterangan.setNomorUrusan(inputWrapper.getNomorUrusan());
        suratKeterangan.setNomorUrut(0);
        suratKeterangan.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        suratKeterangan.setNomorUnit(inputWrapper.getNomorUnit());
        suratKeterangan.setNomorTahun(Year.now().getValue());

        suratKeterangan.setNipPenandatangan(inputWrapper.getNipPenandatangan());
        suratKeterangan.setIsiSuratKeterangan(inputWrapper.getIsiSuratKeterangan());
        suratKeterangan.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSurat());
        suratKeterangan.setTanggalPembuatanSuratMilis(new Date().getTime());
        suratKeterangan.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        suratKeterangan.setKdUnitKerja(inputWrapper.getKdUnitKerja());

        suratKeterangan.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());

        if (inputWrapper.getKdSuratKeteranganBawahan() == null) {
            suratKeterangan.setPathPenilaian(kdSuratKeterangan);
            suratKeterangan.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
            suratKeterangan.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());
        } else {
            SuratKeterangan suratKeteranganBawahan
                    = suratKeteranganService.getByKdSuratKeterangan(inputWrapper.getKdSuratKeteranganBawahan());
            suratKeterangan.setPathPenilaian(suratKeteranganBawahan.getPathPenilaian()+"."+kdSuratKeterangan);
            suratKeterangan.setKdNaskahPenugasan(suratKeteranganBawahan.getKdNaskahPenugasan());
            suratKeterangan.setJenisNaskahPenugasan(suratKeteranganBawahan.getJenisNaskahPenugasan());

            suratKeteranganBawahan.setStatusPenilaian(2);
            suratKeteranganService.create(suratKeteranganBawahan);
        }

        suratKeterangan.setNipPenilai("");
        suratKeterangan.setStatusPenilaian(0);
        suratKeterangan.setAlasanPenolakan(null);
        suratKeterangan.setStatusBaca(0);
        suratKeterangan.setStatusPenyebaran(0);
        suratKeterangan.setNipPegawaiKeterangan(
                ekinerjaXMLBuilder.convertListSuratPerintahIntoXml(
                        inputWrapper.getNipPegawaiKeterangan(),
                        "nip"));

        suratKeterangan.setKdUrtug(inputWrapper.getKdUrtug());
        suratKeterangan.setTahunUrtug(inputWrapper.getTahunUrtug());

        QutPegawai pegawaiPembuat = qutPegawaiService.getQutPegawai(inputWrapper.getNipPembuatSurat());
        if (akunPegawaiService.isPegawaiSekretaris(pegawaiPembuat)) {
            suratKeterangan.setApprovalSekretaris(1);
        }

        suratKeteranganService.create(suratKeterangan);

        for (TargetSuratKeterangan targetSuratKeterangan
                : targetSuratKeteranganList) {
            suratKeteranganService.createTargetSuratKeterangan(targetSuratKeterangan);
        }

        return new ResponseEntity<Object>(new CustomMessage("surat keterangan created"), HttpStatus.OK);

    }

    @RequestMapping(value = "/sebar-surat-keterangan/{kdSuratKeterangan}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarSuratKeterangan(@PathVariable("kdSuratKeterangan") String kdSuratKeterangan) {
        LOGGER.info("sebar surat keterangan");

        SuratKeterangan suratKeterangan = suratKeteranganService.getByKdSuratKeterangan(kdSuratKeterangan);


        return new ResponseEntity<Object>(new CustomMessage("surat keterangan telah disebar"), HttpStatus.OK);

    }

    @RequestMapping(value = "/approve-surat-keterangan/{kdSuratKeterangan}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratKeterangan(@PathVariable("kdSuratKeterangan") String kdSuratKeterangan) {
        LOGGER.info("approve surat keterangan");

        suratKeteranganService.approveSuratKeterangan(kdSuratKeterangan);

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/terima-surat-keterangan/{kdSuratKeterangan}", method = RequestMethod.PUT)
    ResponseEntity<?> terimaSuratKeterangan(@PathVariable("kdSuratKeterangan") String kdSuratKeterangan) {
        LOGGER.info("terima surat keterangan");

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keterangan-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeteranganHistoryByPegawai(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get surat keterangan history");

        List<SuratKeterangan> suratKeteranganList
                = suratKeteranganService.getByNipPembuat(nipPembuat);
        List<SuratPerintahHistoryWrapper> suratKeteranganHistoryList
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (SuratKeterangan suratKeterangan : suratKeteranganList) {
            suratKeteranganHistoryList
                    .add(new SuratPerintahHistoryWrapper(
                            suratKeterangan.getKdSuratKeterangan(),
                            df.format(new Date(suratKeterangan.getTanggalPembuatanSuratMilis())),
                            false,
                            suratKeterangan.getStatusBaca(),//how if surat keterangan doesnot have target?
                            "surat keterangan",
                            8,
                            suratKeterangan.getTanggalPembuatanSuratMilis(),
                            suratKeterangan.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(suratKeteranganHistoryList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keterangan-by-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeteranganTarget(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat keterangan target");

        List<TargetSuratKeterangan> targetSuratKeteranganList
                = suratKeteranganService.getTargetSuratKeteranganByNip(nipTarget);
        List<SuratPerintahTargetWrapper> suratTargetKeteranganList
                = new ArrayList<>();

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        Locale indoLocale = new Locale("id", "ID");

        for (TargetSuratKeterangan targetSuratKeterangan
                : targetSuratKeteranganList) {
            if (targetSuratKeterangan.getSuratKeterangan().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(targetSuratKeterangan.getSuratKeterangan().getNipPenandatangan())) {
                        suratTargetKeteranganList
                                .add(new SuratPerintahTargetWrapper(
                                        targetSuratKeterangan.getSuratKeterangan().getKdSuratKeterangan(),
                                        DateUtilities.createLocalDate(
                                                new Date(targetSuratKeterangan.getSuratKeterangan().getTanggalPembuatanSuratMilis()), "dd MMMM yyyy", indoLocale),
                                        targetSuratKeterangan.getSuratKeterangan().getTanggalPembuatanSuratMilis(),
                                        false,
                                        pegawaiPemberi.getNip(),
                                        pegawaiPemberi.getNama(),
                                        pegawaiPemberi.getJabatan(),
                                        targetSuratKeterangan.getStatusBaca(),
                                        "Surat Keterangan",
                                        8));

                        break;

                    }
                }
            }
        }

        return new ResponseEntity<Object>(suratTargetKeteranganList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-keterangan-by-target-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKeteranganTargetUnread(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat keterangan target unread");
        List<TargetSuratKeterangan> targetSuratKeteranganList
                = suratKeteranganService.getTargetSuratKeteranganByNip(nipTarget);
        List<SuratPerintahTargetWrapper> suratTargetKeteranganList
                = new ArrayList<>();

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        Locale indoLocale = new Locale("id", "ID");

        if (targetSuratKeteranganList.isEmpty()) {
            return new ResponseEntity<Object>(new CustomMessage("target is empty"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (targetSuratKeteranganList.get(0).getSuratKeterangan() == null) {
            return new ResponseEntity<Object>(new CustomMessage("there is error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for (TargetSuratKeterangan targetSuratKeterangan
                : targetSuratKeteranganList) {
            if (targetSuratKeterangan.getSuratKeterangan().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(targetSuratKeterangan.getSuratKeterangan().getNipPenandatangan())) {
                        if (targetSuratKeterangan.getStatusBaca() == 0) {
                            suratTargetKeteranganList
                                    .add(new SuratPerintahTargetWrapper(
                                            targetSuratKeterangan.getSuratKeterangan().getKdSuratKeterangan(),
                                            DateUtilities.createLocalDate(
                                                    new Date(targetSuratKeterangan.getSuratKeterangan().getTanggalPembuatanSuratMilis()), "dd MMMM yyyy", indoLocale),
                                            targetSuratKeterangan.getSuratKeterangan().getTanggalPembuatanSuratMilis(),
                                            false,
                                            pegawaiPemberi.getNip(),
                                            pegawaiPemberi.getNama(),
                                            pegawaiPemberi.getJabatan(),
                                            targetSuratKeterangan.getStatusBaca(),
                                            "Surat Keterangan",
                                            8));
                        }

                        break;

                    }
                }
            }
        }

        return new ResponseEntity<Object>(suratTargetKeteranganList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-keterangan-by-kd-surat-keterangan/{kdSuratKeterangan}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratKeteranganByKdSuratKeterangan(@PathVariable("kdSuratKeterangan") String kdSuratKeterangan) {
        LOGGER.info("get surat keterangan kd surat keterangan");

        SuratKeterangan suratKeterangan = suratKeteranganService.getByKdSuratKeterangan(kdSuratKeterangan);

        EkinerjaXMLParser ekinerjaXMLParser = new EkinerjaXMLParser();

        CustomPegawaiCredential penandatangan = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        List<String> nipPegawaiKeteranganList
                = ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                        suratKeterangan.getNipPegawaiKeterangan(), "nip");

        List<CustomPegawaiCredential> pegawaiKeteranganList
                = new ArrayList<>();
        List<CustomPegawaiCredential> targetPegawaiKeteranganList
                = new ArrayList<>();

        for (CustomPegawaiCredential customPegawaiCredential : qutPegawaiList) {
            if (customPegawaiCredential.getNip()
                    .equals(suratKeterangan.getNipPenandatangan())) {
                penandatangan = customPegawaiCredential;
                break;
            }
        }

        for (String nip : nipPegawaiKeteranganList) {
            for (CustomPegawaiCredential customPegawaiCredential : qutPegawaiList) {
                if (customPegawaiCredential.getNip()
                        .equals(nip)) {
                    customPegawaiCredential
                            .setUnitKerja(tkdUnkDao.findOne(customPegawaiCredential.getKdUnitKerja()).getUnitKerja());
                    pegawaiKeteranganList.add(customPegawaiCredential);
                    break;
                }
            }
        }

        for (TargetSuratKeterangan targetSuratKeterangan
                : suratKeterangan.getTargetSuratKeteranganList()) {
            for (CustomPegawaiCredential customPegawaiCredential
                    : qutPegawaiList) {
                if (customPegawaiCredential.getNip()
                        .equals(targetSuratKeterangan.getTargetSuratKeteranganId().getNipPegawai())) {
                    customPegawaiCredential
                            .setUnitKerja(tkdUnkDao.findOne(customPegawaiCredential.getKdUnitKerja()).getUnitKerja());

                    pegawaiKeteranganList.add(customPegawaiCredential);
                    break;
                }
            }
        }

        String base64BarcodeImage = null;
        if (suratKeterangan.getKdBarcode() != null) {
            BarcodeGenerator generator = new BarcodeGenerator();

            base64BarcodeImage
                    = generator.convertBarcodeImageIntoBase64String(
                    generator.generateBarcode(suratKeterangan.getKdBarcode()));
        }
        SuratKeteranganWrapper suratKeteranganWrapper
                = new SuratKeteranganWrapper(
                        suratKeterangan.getKdSuratKeterangan(),
                        suratKeterangan.getNomorUrusan(),
                        suratKeterangan.getNomorUrut(),
                        suratKeterangan.getNomorPasanganUrut(),
                        suratKeterangan.getNomorUnit(),
                        suratKeterangan.getNomorTahun(),
                        penandatangan.getNip(),
                        penandatangan.getNama(),
                        penandatangan.getJabatan(),
                        tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja(),
                penandatangan.getPangkat(), penandatangan.getGol(), penandatangan.getGlrDpn(),
                        penandatangan.getGlrBlk(),
                        suratKeterangan.getIsiSuratKeterangan(),
                        suratKeterangan.getKotaPembuatanSurat(),
                        suratKeterangan.getTanggalPembuatanSuratMilis(),
                        pegawaiKeteranganList,
                        targetPegawaiKeteranganList,
                base64BarcodeImage);

        return new ResponseEntity<Object>(suratKeteranganWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-keterangan/{kdSuratKeterangan}/{nipTarget}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratKeterangan(
            @PathVariable("kdSuratKeterangan") String kdSuratKeterangan,
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("open surat keterangan");
        SuratKeterangan suratKeterangan
                = suratKeteranganService.getByKdSuratKeterangan(kdSuratKeterangan);

        suratKeteranganService.openSuratKeterangan(kdSuratKeterangan);
        
        suratKeteranganService
                .openTargetSuratKeterangan(new TargetSuratKeteranganId(kdSuratKeterangan, nipTarget));

        return new ResponseEntity<Object>(new CustomMessage("surat keterangan opened"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-keterangan-penilai/{kdSuratKeterangan}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratKeteranganPenilai(@PathVariable("kdSuratKeterangan") String kdSuratKeterangan) {
        LOGGER.info("open surat keterangan penilai");

        suratKeteranganService.openSuratKeteranganPenilai(kdSuratKeterangan);

        return new ResponseEntity<Object>(new CustomMessage("surat keterangan opened by penilai"), HttpStatus.OK);

    }

    /**
     *
     *
     *
     * @param kdSuratKeterangan
     * @return
     */
    public SuratKeteranganWrapper getSuratKeteranganWrapper(String kdSuratKeterangan) {
        SuratKeterangan suratKeterangan = suratKeteranganService.getByKdSuratKeterangan(kdSuratKeterangan);

        EkinerjaXMLParser ekinerjaXMLParser = new EkinerjaXMLParser();

        CustomPegawaiCredential penandatangan = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        List<String> nipPegawaiKeteranganList
                = ekinerjaXMLParser.convertXmlSuratPerintahIntoListofString(
                suratKeterangan.getNipPegawaiKeterangan(), "nip");

        List<CustomPegawaiCredential> pegawaiKeteranganList
                = new ArrayList<>();
        List<CustomPegawaiCredential> targetPegawaiKeteranganList
                = new ArrayList<>();

        for (CustomPegawaiCredential customPegawaiCredential : qutPegawaiList) {
            if (customPegawaiCredential.getNip()
                    .equals(suratKeterangan.getNipPenandatangan())) {
                penandatangan = customPegawaiCredential;
                break;
            }
        }

        for (String nip : nipPegawaiKeteranganList) {
            for (CustomPegawaiCredential customPegawaiCredential : qutPegawaiList) {
                if (customPegawaiCredential.getNip()
                        .equals(nip)) {
                    customPegawaiCredential
                            .setUnitKerja(tkdUnkDao.findOne(customPegawaiCredential.getKdUnitKerja()).getUnitKerja());
                    pegawaiKeteranganList.add(customPegawaiCredential);
                    break;
                }
            }
        }

        for (TargetSuratKeterangan targetSuratKeterangan
                : suratKeterangan.getTargetSuratKeteranganList()) {
            for (CustomPegawaiCredential customPegawaiCredential
                    : qutPegawaiList) {
                if (customPegawaiCredential.getNip()
                        .equals(targetSuratKeterangan.getTargetSuratKeteranganId().getNipPegawai())) {
                    customPegawaiCredential
                            .setUnitKerja(tkdUnkDao.findOne(customPegawaiCredential.getKdUnitKerja()).getUnitKerja());

                    pegawaiKeteranganList.add(customPegawaiCredential);
                    break;
                }
            }
        }

        String base64BarcodeImage = null;
        if (suratKeterangan.getKdBarcode() != null) {
            BarcodeGenerator generator = new BarcodeGenerator();

            base64BarcodeImage
                    = generator.convertBarcodeImageIntoBase64String(
                    generator.generateBarcode(suratKeterangan.getKdBarcode()));
        }
        SuratKeteranganWrapper suratKeteranganWrapper
                = new SuratKeteranganWrapper(
                suratKeterangan.getKdSuratKeterangan(),
                suratKeterangan.getNomorUrusan(),
                suratKeterangan.getNomorUrut(),
                suratKeterangan.getNomorPasanganUrut(),
                suratKeterangan.getNomorUnit(),
                suratKeterangan.getNomorTahun(),
                penandatangan.getNip(),
                penandatangan.getNama(),
                penandatangan.getJabatan(),
                tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja(),
                penandatangan.getPangkat(), penandatangan.getGol(), penandatangan.getGlrDpn(),
                penandatangan.getGlrBlk(),
                suratKeterangan.getIsiSuratKeterangan(),
                suratKeterangan.getKotaPembuatanSurat(),
                suratKeterangan.getTanggalPembuatanSuratMilis(),
                pegawaiKeteranganList,
                targetPegawaiKeteranganList,
                base64BarcodeImage);

        return suratKeteranganWrapper;

    }
    
}
