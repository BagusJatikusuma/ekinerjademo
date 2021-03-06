package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TkdUnkCloneDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.util.BarcodeGenerator;
import com.pemda.ekinerjademo.wrapper.input.SuratDinasInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bayu on 18/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratDinasController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratDinasController.class);

    @Autowired private SuratDinasService suratDinasService;

//    @Autowired private QutPegawaiService qutPegawaiService; //test clone
    @Autowired private QutPegawaiCloneService qutPegawaiService;

    @Autowired
    @Qualifier("TkdJabatanCloneService")
    private TkdJabatanService tkdJabatanService;

//    @Autowired private TkdUnkDao tkdUnkDao; //test clone
    @Autowired private TkdUnkCloneDao tkdUnkDao;

    @Autowired
    private AkunPegawaiService akunPegawaiService;
    @Autowired
    private SuratDisposisiService suratDisposisiService;

    @RequestMapping(value = "/create-surat-dinas", method = RequestMethod.POST)
    ResponseEntity<?> createSuratDinas(@RequestBody SuratDinasInputWrapper inputWrapper) {
        LOGGER.info("create surat dinas");

        String kdSuratDinas = String.valueOf(new Date().getTime());

        List<TembusanSuratDinas> tembusanSuratDinasList
                = new ArrayList<>();
        //build tembusan surat dinas list
        for (String jabatanTembusan
                : inputWrapper.getKdTembusanList()) {
            TembusanSuratDinasId tembusanSuratDinasId
                    = new TembusanSuratDinasId(kdSuratDinas, jabatanTembusan);
            TembusanSuratDinas tembusanSuratDinas
                    = new TembusanSuratDinas();
            tembusanSuratDinas.setTembusanSuratDinasId(tembusanSuratDinasId);

            tembusanSuratDinas.setKdUnitKerja(tkdJabatanService.getTkdJabatan(jabatanTembusan).getKdUnitKerja().getKdUnK());
            tembusanSuratDinas.setStatusBaca(0);
            tembusanSuratDinas.setStatusDiterima(0);

            tembusanSuratDinasList.add(tembusanSuratDinas);
        }
        //build surat dinas
        SuratDinas suratDinas = new SuratDinas();

        suratDinas.setKdSuratDinas(kdSuratDinas);
        suratDinas.setNomorUrusan(inputWrapper.getNomorUrusan());
        suratDinas.setNomorUrut(0);
        suratDinas.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        suratDinas.setNomorUnit(inputWrapper.getNomorUnit());
        suratDinas.setNomorTahun(Year.now().getValue());

        suratDinas.setSifat(inputWrapper.getSifat());
        suratDinas.setLampiran(inputWrapper.getLampiran());
        suratDinas.setHal(inputWrapper.getHal());
        suratDinas.setKdJabatanPenerimaSuratDinas(inputWrapper.getKdJabatanPenerimaSuratDinas());
        suratDinas.setTanggalPembuatanMilis(new Date().getTime());
        suratDinas.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSuratDinas());
        suratDinas.setIsiSuratDinas(inputWrapper.getIsiSuratDinas());
        suratDinas.setNipPenandatangan(inputWrapper.getNipPenandatangan());
        suratDinas.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        suratDinas.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        suratDinas.setKdUnitKerjaTarget(
                tkdJabatanService.getTkdJabatan(
                        inputWrapper.getKdJabatanPenerimaSuratDinas()).getKdUnitKerja().getKdUnK());

        suratDinas.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        suratDinas.setStatusBaca(0);

        if (inputWrapper.getKdSuratDinasBawahan() == null) {
            suratDinas.setPathPenilaian(kdSuratDinas);
            suratDinas.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
            suratDinas.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());
        } else {
            SuratDinas suratDinasBawahan
                    = suratDinasService.getByKdSuratDinas(inputWrapper.getKdSuratDinasBawahan());
            suratDinas.setPathPenilaian(suratDinasBawahan.getPathPenilaian()+"."+kdSuratDinas);
            suratDinas.setKdNaskahPenugasan(suratDinasBawahan.getKdNaskahPenugasan());
            suratDinas.setJenisNaskahPenugasan(suratDinasBawahan.getJenisNaskahPenugasan());

            suratDinasBawahan.setStatusPenilaian(2);
            suratDinasService.create(suratDinasBawahan);
        }

        suratDinas.setNipPenilai("");
        suratDinas.setStatusPenilaian(0);
        suratDinas.setAlasanPenolakan("");
        suratDinas.setStatusPenyebaran(0);

        suratDinas.setKdUrtug(inputWrapper.getKdUrtug());
        suratDinas.setTahunUrtug(inputWrapper.getTahunUrtug());
        suratDinas.setApprovalPenandatangan(0);

        QutPegawai pegawaiPembuat = qutPegawaiService.getQutPegawai(inputWrapper.getNipPembuatSurat());
        if (akunPegawaiService.isPegawaiSekretaris(pegawaiPembuat)) {
            suratDinas.setApprovalSekretaris(1);
        }

        suratDinasService.create(suratDinas);
        for (TembusanSuratDinas tembusanSuratDinas
                : tembusanSuratDinasList) {
            suratDinasService.createTembusanSuratDinas(tembusanSuratDinas);
        }

        if (inputWrapper.isSuratPejabat()) {
            SuratDinasPejabat suratDinasPejabat
                    = new SuratDinasPejabat();
            suratDinasPejabat.setKdJabatan(inputWrapper.getKdJabatanSuratPejabat());
            suratDinasPejabat.setKdSuratDinas(kdSuratDinas);

            suratDinasService.createSuratDinasPejabat(suratDinasPejabat);
        } else {
            SuratDinasNonPejabat suratDinasNonPejabat
                    = new SuratDinasNonPejabat();
            suratDinasNonPejabat.setKdSuratDinas(kdSuratDinas);
            suratDinasNonPejabat.setKdUnitKerja(inputWrapper.getKdUnitKerja());

            suratDinasService.createSuratDinasNonPejabat(suratDinasNonPejabat);
        }

        return new ResponseEntity<Object>(new CustomMessage("surat dinas created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/sebar-surat-dinas/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("sebar surat dinas");

        SuratDinas suratDinas = suratDinasService.getByKdSuratDinas(kdSuratDinas);

        suratDinas.setStatusPenyebaran(1);

        suratDinasService.create(suratDinas);

        return new ResponseEntity<Object>(new CustomMessage("surat dinas telah disebar"), HttpStatus.OK);

    }

    @RequestMapping(value = "/approve-surat-dinas/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("sebar surat dinas");

        suratDinasService.approveSuratDinas(kdSuratDinas);

        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/terima-surat-dinas/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> terimaSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("terima surat dinas");


        return new ResponseEntity<Object>(null, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-dinas-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratDinasHistoryByPegawai(
            @PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get surat dinas history");

        List<SuratDinas> suratDinasList
                = suratDinasService.getByNipPembuat(nipPembuat);
        List<SuratPerintahHistoryWrapper> suratDinasHistoryList
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        boolean isSuratPejabat = false;

        for (SuratDinas suratDinas
                : suratDinasList) {

            if (suratDinas.getSuratDinasPejabat() != null) {
                isSuratPejabat = true;
            } else {
                isSuratPejabat = false;
            }

            suratDinasHistoryList
                    .add(new SuratPerintahHistoryWrapper(
                            suratDinas.getKdSuratDinas(),
                            null,
                            isSuratPejabat,
                            suratDinas.getStatusBaca(),
                            "surat dinas",
                            5,
                            suratDinas.getTanggalPembuatanMilis(),
                            suratDinas.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(suratDinasHistoryList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-dinas-by-target/{nipTarget}/{isPersuratan}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratDinasTarget(
            @PathVariable("nipTarget") String nipTarget,
            @PathVariable("isPersuratan") boolean isPersuratan) {
        LOGGER.info("get surat dinas target");

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<SuratPerintahTargetWrapper> targetSuratDinasListWrapper
                = new ArrayList<>();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        boolean isPegawaiTargetAdminSurat = false;
        if (akunPegawaiService.getAkunPegawai(nipTarget).getRole().getId().equals("AD004")) {
            isPegawaiTargetAdminSurat = true;

            LOGGER.info("pegawai is admin surat");
        }


        List<SuratDinas> suratDinasTargetList
                = new ArrayList<>();
        List<TembusanSuratDinas> tembusanSuratDinasTargetList
                = new ArrayList<>();

        if (!isPegawaiTargetAdminSurat) {
            suratDinasTargetList
                    = suratDinasService.getByJabatanPenerima(pegawaiTarget.getKdJabatan());
            tembusanSuratDinasTargetList
                    = suratDinasService.getTembusanSuratDinas(pegawaiTarget.getKdJabatan());
        }
        else {
            suratDinasTargetList
                    = suratDinasService.getByKdUnitKerjaTarget(pegawaiTarget.getKdUnitKerja());
            tembusanSuratDinasTargetList
                    = suratDinasService.getTembusanSuratDinasUnitKerja(pegawaiTarget.getKdUnitKerja());
        }

        //get by target
        boolean isSuratPejabat = false;
        for (SuratDinas suratDinas
                : suratDinasTargetList) {
            if (suratDinas.getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(suratDinas.getNipPenandatangan())) {

                        if (!isPegawaiTargetAdminSurat) {
                            if (pegawaiPemberi.getKdUnitKerja().equals(pegawaiTarget.getKdUnitKerja())) {

                                if (suratDinas.getSuratDinasPejabat() != null) {
                                    isSuratPejabat = true;
                                } else {
                                    isSuratPejabat = false;
                                }

                                targetSuratDinasListWrapper
                                        .add(new SuratPerintahTargetWrapper(
                                                suratDinas.getKdSuratDinas(),
                                                "",
                                                suratDinas.getTanggalPembuatanMilis(),
                                                isSuratPejabat,
                                                pegawaiPemberi.getNip(),
                                                pegawaiPemberi.getNama(),
                                                pegawaiPemberi.getJabatan(),
                                                suratDinas.getStatusBaca(),
                                                "Surat Dinas",
                                                5,
                                                tkdUnkDao.findOne(pegawaiPemberi.getKdUnitKerja()).getUnitKerja(),
                                                false));
                            }

                        }
                        else {
                            boolean isTargetValid = false;
                            if (pegawaiPemberi.getKdUnitKerja()
                                    .equals(pegawaiTarget.getKdUnitKerja())) {
                                if (suratDinas.getKdJabatanPenerimaSuratDinas()
                                        .equals(pegawaiTarget.getKdJabatan())) {
                                    if (!isPersuratan) {
                                        isTargetValid = true;
                                    }
                                }

                            }
                            else {
                                if (isPersuratan) isTargetValid = true;
                            }

                            if (isTargetValid) {

                                if (suratDinas.getSuratDinasPejabat() != null) {
                                    isSuratPejabat = true;
                                } else {
                                    isSuratPejabat = false;
                                }

                                targetSuratDinasListWrapper
                                        .add(new SuratPerintahTargetWrapper(
                                                suratDinas.getKdSuratDinas(),
                                                "",
                                                suratDinas.getTanggalPembuatanMilis(),
                                                isSuratPejabat,
                                                pegawaiPemberi.getNip(),
                                                pegawaiPemberi.getNama(),
                                                pegawaiPemberi.getJabatan(),
                                                suratDinas.getStatusBaca(),
                                                "Surat Dinas",
                                                5,
                                                tkdUnkDao.findOne(pegawaiPemberi.getKdUnitKerja()).getUnitKerja(),
                                                suratDisposisiService.isSuratDisposisiExist(suratDinas.getKdSuratDinas(),5)));
                            }

                        }
                        break;

                    }
                }
            }
        }
        //get by tembusan
        for (TembusanSuratDinas tembusanSuratDinas
                : tembusanSuratDinasTargetList) {
            if (tembusanSuratDinas.getSuratDinas().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getKdJabatan()
                            .equals(tembusanSuratDinas.getSuratDinas().getKdJabatanPenerimaSuratDinas())) {

                        if (!isPegawaiTargetAdminSurat) {
                            if (pegawaiPemberi.getKdUnitKerja().equals(pegawaiTarget.getKdUnitKerja())) {

                                if (tembusanSuratDinas.getSuratDinas().getSuratDinasPejabat() != null) {
                                    isSuratPejabat = true;
                                } else {
                                    isSuratPejabat = false;
                                }

                                targetSuratDinasListWrapper
                                        .add(new SuratPerintahTargetWrapper(
                                                tembusanSuratDinas.getSuratDinas().getKdSuratDinas(),
                                                "",
                                                tembusanSuratDinas.getSuratDinas().getTanggalPembuatanMilis(),
                                                isSuratPejabat,
                                                pegawaiPemberi.getNip(),
                                                pegawaiPemberi.getNama(),
                                                pegawaiPemberi.getJabatan(),
                                                tembusanSuratDinas.getStatusBaca(),
                                                "Surat Dinas",
                                                5,
                                                tkdUnkDao.findOne(pegawaiPemberi.getKdUnitKerja()).getUnitKerja(),
                                                false));
                            }

                        }
                        else {
                            boolean isTargetValid = false;
                            if (pegawaiPemberi.getKdUnitKerja()
                                    .equals(pegawaiTarget.getKdUnitKerja())) {
                                if (tembusanSuratDinas.getTembusanSuratDinasId().getKdJabatan()
                                        .equals(pegawaiTarget.getKdJabatan())) {
                                    if (!isPersuratan) {
                                        isTargetValid = true;
                                    }
                                }

                            }
                            else {
                                if (isPersuratan) isTargetValid = true;
                            }

                            if (isTargetValid) {
                                if (tembusanSuratDinas.getSuratDinas().getSuratDinasPejabat() != null) {
                                    isSuratPejabat = true;
                                } else {
                                    isSuratPejabat = false;
                                }

                                targetSuratDinasListWrapper
                                        .add(new SuratPerintahTargetWrapper(
                                                tembusanSuratDinas.getSuratDinas().getKdSuratDinas(),
                                                "",
                                                tembusanSuratDinas.getSuratDinas().getTanggalPembuatanMilis(),
                                                isSuratPejabat,
                                                pegawaiPemberi.getNip(),
                                                pegawaiPemberi.getNama(),
                                                pegawaiPemberi.getJabatan(),
                                                tembusanSuratDinas.getStatusBaca(),
                                                "Surat Dinas",
                                                5,
                                                tkdUnkDao.findOne(pegawaiPemberi.getKdUnitKerja()).getUnitKerja(),
                                                suratDisposisiService
                                                        .isSuratDisposisiExist(tembusanSuratDinas.getSuratDinas().getKdSuratDinas(),5)));
                            }

                        }
                        break;

                    }
                }
            }
        }

        return new ResponseEntity<Object>(targetSuratDinasListWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-daftar-surat-dinas-by-target-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratDinasTargetUnread(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get surat dinas target unread");

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<SuratPerintahTargetWrapper> targetSuratDinasListWrapper
                = new ArrayList<>();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<SuratDinas> suratDinasTargetList
                = suratDinasService.getByJabatanPenerima(pegawaiTarget.getKdJabatan());
        List<TembusanSuratDinas> tembusanSuratDinasTargetList
                = suratDinasService.getTembusanSuratDinas(pegawaiTarget.getKdJabatan());

        //get by target
        boolean isSuratPejabat = false;
        for (SuratDinas suratDinas
                : suratDinasTargetList) {
            if (suratDinas.getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(suratDinas.getNipPenandatangan())) {

                        if (suratDinas.getSuratDinasPejabat() != null) {
                            isSuratPejabat = true;
                        } else {
                            isSuratPejabat = false;
                        }

                        if (suratDinas.getStatusBaca() == 0) {
                            targetSuratDinasListWrapper
                                    .add(new SuratPerintahTargetWrapper(
                                            suratDinas.getKdSuratDinas(),
                                            "",
                                            suratDinas.getTanggalPembuatanMilis(),
                                            isSuratPejabat,
                                            pegawaiPemberi.getNip(),
                                            pegawaiPemberi.getNama(),
                                            pegawaiPemberi.getJabatan(),
                                            suratDinas.getStatusBaca(),
                                            "Surat Dinas",
                                            5));
                        }
                        break;
                    }
                }
            }
        }
        //get by tembusan
        for (TembusanSuratDinas tembusanSuratDinas
                : tembusanSuratDinasTargetList) {
            if (tembusanSuratDinas.getSuratDinas().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getKdJabatan()
                            .equals(tembusanSuratDinas.getSuratDinas().getKdJabatanPenerimaSuratDinas())) {

                        if (tembusanSuratDinas.getSuratDinas().getSuratDinasPejabat() != null) {
                            isSuratPejabat = true;
                        } else {
                            isSuratPejabat = false;
                        }

                        if (tembusanSuratDinas.getStatusBaca() == 0) {
                            targetSuratDinasListWrapper
                                    .add(new SuratPerintahTargetWrapper(
                                            tembusanSuratDinas.getSuratDinas().getKdSuratDinas(),
                                            "",
                                            tembusanSuratDinas.getSuratDinas().getTanggalPembuatanMilis(),
                                            isSuratPejabat,
                                            pegawaiPemberi.getNip(),
                                            pegawaiPemberi.getNama(),
                                            pegawaiPemberi.getJabatan(),
                                            tembusanSuratDinas.getStatusBaca(),
                                            "Surat Dinas",
                                            5));
                        }
                        break;
                    }
                }
            }
        }

        return new ResponseEntity<Object>(targetSuratDinasListWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-surat-dinas-by-kd-surat-dinas/{kdSuratDinas}", method = RequestMethod.GET)
    ResponseEntity<?> getSuratDinasByKdSuratDinas(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("get surat dinas kd surat dinas");

        SuratDinas suratDinas
                = suratDinasService.getByKdSuratDinas(kdSuratDinas);
        SuratDinasWrapper suratDinasWrapper
                = new SuratDinasWrapper();

        String base64BarcodeImage = null;

        if (suratDinas.getKdBarcode() != null) {
            BarcodeGenerator generator = new BarcodeGenerator();

            base64BarcodeImage
                    = generator.convertBarcodeImageIntoBase64String(
                    generator.generateBarcode(suratDinas.getKdBarcode()));
        }

        suratDinasWrapper.setNomorUrusan(suratDinas.getNomorUrusan());
        suratDinasWrapper.setNomorUrut(suratDinas.getNomorUrut());
        suratDinasWrapper.setNomorPasanganUrut(suratDinas.getNomorPasanganUrut());
        suratDinasWrapper.setNomorUnit(suratDinas.getNomorUnit());
        suratDinasWrapper.setNomorTahun(suratDinas.getNomorTahun());

        suratDinasWrapper.setSifat(suratDinas.getSifat());
        suratDinasWrapper.setHal(suratDinas.getHal());
        suratDinasWrapper.setLampiran(suratDinas.getLampiran());

        List<TkdJabatan> tkdJabatanList
                = tkdJabatanService.getAll();
        TkdJabatan jabatanPenerima = new TkdJabatan();
        List<JabatanWrapper> tembusanJabatanWrapperList
                = new ArrayList<>();

        for (TkdJabatan tkdJabatan
                : tkdJabatanList) {
            if (tkdJabatan.getKdJabatan()
                    .equals(suratDinas.getKdJabatanPenerimaSuratDinas())) {
                jabatanPenerima = tkdJabatan;
                break;
            }
        }
        for (TembusanSuratDinas tembusanSuratDinas
                : suratDinas.getTembusanSuratDinasList()) {
            for (TkdJabatan tkdJabatan
                    : tkdJabatanList) {
                if (tkdJabatan.getKdJabatan()
                        .equals(tembusanSuratDinas.getTembusanSuratDinasId().getKdJabatan())) {
                    tembusanJabatanWrapperList
                            .add(new JabatanWrapper(
                                    tkdJabatan.getKdJabatan(),
                                    tkdJabatan.getJabatan(),
                                    tkdJabatan.getEselon()));
                    break;
                }
            }
        }

        suratDinasWrapper.setKdJabatanPenerimaSuratDinas(jabatanPenerima.getKdJabatan());
        suratDinasWrapper.setJabatanPenerimaSuratDinas(jabatanPenerima.getJabatan());
        suratDinasWrapper.setTanggalPembuatanMilis(suratDinas.getTanggalPembuatanMilis());
        suratDinasWrapper.setKotaPembuatanSurat(suratDinas.getKotaPembuatanSurat());
        suratDinasWrapper.setIsiSuratDinas(suratDinas.getIsiSuratDinas());

        suratDinasWrapper.setTembusanSuratDinasWrapper(tembusanJabatanWrapperList);

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential penandatangan = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (suratDinas.getNipPenandatangan()
                    .equals(pegawai.getNip())) {
                penandatangan = pegawai;

                break;
            }
        }
        suratDinasWrapper.setNipPenandatangan(penandatangan.getNip());
        suratDinasWrapper.setNamaPenandatangan(penandatangan.getNama());
        suratDinasWrapper.setJabatanPenandatangan(penandatangan.getJabatan());
        suratDinasWrapper.setUnitKerjaPenandatangan(tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja());
        suratDinasWrapper.setPangkatPenandatangan(penandatangan.getPangkat());
        suratDinasWrapper.setGelarDepanPenandatangan(penandatangan.getGlrDpn());
        suratDinasWrapper.setGelarBelakangPenandatangan(penandatangan.getGlrBlk());
        suratDinasWrapper.setBarcodeImage(null);
        boolean isSuratPejabat = false;

        if (suratDinas.getSuratDinasPejabat() != null) isSuratPejabat = true;

        suratDinasWrapper.setSuratPejabat(isSuratPejabat);
        suratDinasWrapper.setBarcodeImage(base64BarcodeImage);

        return new ResponseEntity<Object>(suratDinasWrapper, HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-dinas/{kdSuratDinas}/{nipTarget}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratDinas(
            @PathVariable("kdSuratDinas") String kdSuratDinas,
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("open surat dinas");

        QutPegawai pegawaiTarget = qutPegawaiService.getQutPegawai(nipTarget);
        SuratDinas suratDinas = suratDinasService.getByKdSuratDinas(kdSuratDinas);

        if (pegawaiTarget.getKdJabatan()
                .equals(suratDinas.getKdJabatanPenerimaSuratDinas())) {
            suratDinasService.openSuratDinas(kdSuratDinas);
        }

        boolean exist = false;
        for (TembusanSuratDinas tembusanSuratDinas
                : suratDinas.getTembusanSuratDinasList()) {
            if (tembusanSuratDinas.getTembusanSuratDinasId().getKdJabatan()
                    .equals(pegawaiTarget.getKdJabatan())) {
                exist = true;
                break;
            }
        }

        if (exist) {
            suratDinasService
                    .openTembusanSuratDinas(
                            new TembusanSuratDinasId(kdSuratDinas, pegawaiTarget.getKdJabatan()));
        }

        return new ResponseEntity<Object>(new CustomMessage("surat dinas opened"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-dinas-penilai/{kdSuratDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratDinasPenilai(@PathVariable("kdSuratDinas") String kdSuratDinas) {
        LOGGER.info("open surat dinas penilai");

        SuratDinas suratDinas
                = suratDinasService.getByKdSuratDinas(kdSuratDinas);
        suratDinas.setStatusPenilaian(1);

        suratDinasService.create(suratDinas);
        return new ResponseEntity<Object>(new CustomMessage("surat dinas opened by penilai"), HttpStatus.OK);

    }

    @RequestMapping(value = "/get-draft-surat-dinas-approval/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getDraftSuratDinasApproval(
            @PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get draft memorandum approval");

        List<SuratDinas> draftSuratDinasApprovalList
                = suratDinasService.getDraftApproval(kdUnitKerja);
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();


        List<DraftSuratApprovalWrapper> draftSuratApprovalWrappers
                = new ArrayList<>();

        for (SuratDinas suratDinas : draftSuratDinasApprovalList) {
            boolean isSuratPejabat = false;

            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratDinas.getNipPenandatangan())) {
                    if (suratDinas.getSuratDinasPejabat() != null) {
                        isSuratPejabat = true;
                    }

                    draftSuratApprovalWrappers
                            .add(new DraftSuratApprovalWrapper(
                                    suratDinas.getKdSuratDinas(),
                                    null,
                                    suratDinas.getTanggalPembuatanMilis(),
                                    isSuratPejabat,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan(),
                                    suratDinas.getStatusPenyebaran(),
                                    "surat dinas",
                                    5
                            ));
                    break;
                }
            }

        }

        return new ResponseEntity<>(draftSuratApprovalWrappers, HttpStatus.OK);
    }

    /**
     *
     *
     *
     * @param kdSuratDinas
     * @return
     */
    public SuratDinasWrapper getSuratDinasWrapper(String kdSuratDinas) {
        SuratDinas suratDinas
                = suratDinasService.getByKdSuratDinas(kdSuratDinas);
        SuratDinasWrapper suratDinasWrapper
                = new SuratDinasWrapper();

        String base64BarcodeImage = null;

        if (suratDinas.getKdBarcode() != null) {
            BarcodeGenerator generator = new BarcodeGenerator();

            base64BarcodeImage
                    = generator.convertBarcodeImageIntoBase64String(
                    generator.generateBarcode(suratDinas.getKdBarcode()));
        }

        suratDinasWrapper.setNomorUrusan(suratDinas.getNomorUrusan());
        suratDinasWrapper.setNomorUrut(suratDinas.getNomorUrut());
        suratDinasWrapper.setNomorPasanganUrut(suratDinas.getNomorPasanganUrut());
        suratDinasWrapper.setNomorUnit(suratDinas.getNomorUnit());
        suratDinasWrapper.setNomorTahun(suratDinas.getNomorTahun());

        suratDinasWrapper.setSifat(suratDinas.getSifat());
        suratDinasWrapper.setHal(suratDinas.getHal());
        suratDinasWrapper.setLampiran(suratDinas.getLampiran());

        List<TkdJabatan> tkdJabatanList
                = tkdJabatanService.getAll();
        TkdJabatan jabatanPenerima = new TkdJabatan();
        List<JabatanWrapper> tembusanJabatanWrapperList
                = new ArrayList<>();

        for (TkdJabatan tkdJabatan
                : tkdJabatanList) {
            if (tkdJabatan.getKdJabatan()
                    .equals(suratDinas.getKdJabatanPenerimaSuratDinas())) {
                jabatanPenerima = tkdJabatan;
                break;
            }
        }
        for (TembusanSuratDinas tembusanSuratDinas
                : suratDinas.getTembusanSuratDinasList()) {
            for (TkdJabatan tkdJabatan
                    : tkdJabatanList) {
                if (tkdJabatan.getKdJabatan()
                        .equals(tembusanSuratDinas.getTembusanSuratDinasId().getKdJabatan())) {
                    tembusanJabatanWrapperList
                            .add(new JabatanWrapper(
                                    tkdJabatan.getKdJabatan(),
                                    tkdJabatan.getJabatan(),
                                    tkdJabatan.getEselon()));
                    break;
                }
            }
        }

        suratDinasWrapper.setKdJabatanPenerimaSuratDinas(jabatanPenerima.getKdJabatan());
        suratDinasWrapper.setJabatanPenerimaSuratDinas(jabatanPenerima.getJabatan());
        suratDinasWrapper.setTanggalPembuatanMilis(suratDinas.getTanggalPembuatanMilis());
        suratDinasWrapper.setKotaPembuatanSurat(suratDinas.getKotaPembuatanSurat());
        suratDinasWrapper.setIsiSuratDinas(suratDinas.getIsiSuratDinas());

        suratDinasWrapper.setTembusanSuratDinasWrapper(tembusanJabatanWrapperList);

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential penandatangan = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (suratDinas.getNipPenandatangan()
                    .equals(pegawai.getNip())) {
                penandatangan = pegawai;

                break;
            }
        }
        suratDinasWrapper.setNipPenandatangan(penandatangan.getNip());
        suratDinasWrapper.setNamaPenandatangan(penandatangan.getNama());
        suratDinasWrapper.setJabatanPenandatangan(penandatangan.getJabatan());
        suratDinasWrapper.setUnitKerjaPenandatangan(tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja());
        suratDinasWrapper.setPangkatPenandatangan(penandatangan.getPangkat());
        suratDinasWrapper.setGelarDepanPenandatangan(penandatangan.getGlrDpn());
        suratDinasWrapper.setGelarBelakangPenandatangan(penandatangan.getGlrBlk());
        suratDinasWrapper.setBarcodeImage(null);
        boolean isSuratPejabat = false;

        if (suratDinas.getSuratDinasPejabat() != null) isSuratPejabat = true;

        suratDinasWrapper.setSuratPejabat(isSuratPejabat);
        suratDinasWrapper.setBarcodeImage(base64BarcodeImage);

        return suratDinasWrapper;
    }

}
