package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.AkunPegawaiService;
import com.pemda.ekinerjademo.service.MemorandumService;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.util.BarcodeGenerator;
import com.pemda.ekinerjademo.wrapper.input.MemorandumInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
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

/**
 * Created by bayu on 05/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class MemorandumController {
    public static final Logger LOGGER = LoggerFactory.getLogger(MemorandumController.class);

    @Autowired
    private MemorandumService memorandumService;
    @Autowired
    private QutPegawaiCloneService qutPegawaiService;
    @Autowired
    private TkdJabatanService tkdJabatanService;
    @Autowired
    private TkdUnkDao tkdUnkDao;
    @Autowired
    private AkunPegawaiService akunPegawaiService;

    @RequestMapping(value = "/create-memorandum/", method = RequestMethod.POST)
    ResponseEntity<?> createMemorandum(@RequestBody MemorandumInputWrapper inputWrapper) {
        LOGGER.info("create memorandum");

        Integer nomorUrut = 0;

        String kdMemorandum = String.valueOf(new Date().getTime());

        List<TembusanMemorandum> tembusanMemorandumList
                = new ArrayList<>();

        //build tembusan memorandum list
        for (String kdJabatanTembusan : inputWrapper.getKdJabatanTembusanList()) {
            TembusanMemorandumId tembusanId
                    = new TembusanMemorandumId(kdMemorandum, kdJabatanTembusan);
            TembusanMemorandum tembusanMemorandum = new TembusanMemorandum();

            tembusanMemorandum.setTembusanMemorandumId(tembusanId);
            tembusanMemorandum.setKdUnitKerja(tkdJabatanService.getTkdJabatan(kdJabatanTembusan).getKdUnitKerja().getKdUnK());
            tembusanMemorandum.setStatusBaca(0);
            tembusanMemorandum.setStatusDiterima(0);

            tembusanMemorandumList.add(tembusanMemorandum);
        }

        //build memorandum
        Memorandum memorandum = new Memorandum();

        memorandum.setKdMemorandum(kdMemorandum);
        memorandum.setNomorUrusan(inputWrapper.getNomorUrusan());
        memorandum.setNomorUnit(inputWrapper.getNomorUnit());
        memorandum.setNomorUrut(0);
        memorandum.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        memorandum.setNomorTahun(Year.now().getValue());

        memorandum.setNipPenerimaMemorandum(inputWrapper.getNipPenerimaMemorandum());
        memorandum.setNipPemberiMemorandum(inputWrapper.getNipPemberiMemorandum());
        memorandum.setHal(inputWrapper.getHal());
        memorandum.setTanggalPembuatanMilis(new Date().getTime());
        memorandum.setIsiMemorandum(inputWrapper.getIsiMemorandum());
        memorandum.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        memorandum.setNipPenandatangan(inputWrapper.getNipPenandatangan());
        memorandum.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        memorandum.setKdUnitKerjaTarget(
                qutPegawaiService.getQutPegawai(inputWrapper.getNipPenerimaMemorandum()).getKdUnitKerja());
        memorandum.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        memorandum.setNipPenilai("");

        memorandum.setStatusBaca(0);
        memorandum.setStatusPenyebaran(0);
        memorandum.setStatusPenilaian(0);

        memorandum.setKdUrtug(inputWrapper.getKdUrtug());
        memorandum.setTahunUrtug(inputWrapper.getTahunUrtug());

        if (inputWrapper.getKdMemorandumBawahan() == null) {
            memorandum.setPathPenilaian(kdMemorandum);
            memorandum.setStatusPenilaian(0);
            memorandum.setKdNaskahPenugasan(inputWrapper.getKdNaskahPenugasan());
            memorandum.setJenisNaskahPenugasan(inputWrapper.getJenisNaskahPenugasan());
        } else {
            Memorandum memorandumBawahan
                    = memorandumService.getByKdMemorandum(inputWrapper.getKdMemorandumBawahan());
            memorandum.setPathPenilaian(memorandumBawahan.getPathPenilaian()+"."+kdMemorandum);
            memorandum.setKdNaskahPenugasan(memorandumBawahan.getKdNaskahPenugasan());
            memorandum.setJenisNaskahPenugasan(memorandumBawahan.getJenisNaskahPenugasan());

            memorandumBawahan.setStatusPenilaian(2);
            memorandumService.createMemorandum(memorandumBawahan);
        }
        //save memorandum
        memorandumService.createMemorandum(memorandum);
        //save tembusan memorandum
        memorandumService.createTembusanMemorandum(tembusanMemorandumList);
        //save memorandum pejabat or non pejabat
        if (inputWrapper.isSuratPejabat()) {
            MemorandumPejabat memorandumPejabat
                    = new MemorandumPejabat();
            memorandumPejabat.setKdJabatan(inputWrapper.getKdJabatanSuratPejabat());
            memorandumPejabat.setKdMemorandum(kdMemorandum);

            memorandumService.createMemorandumPejabat(memorandumPejabat);
        } else {
            MemorandumNonPejabat memorandumNonPejabat
                    = new MemorandumNonPejabat();
            memorandumNonPejabat.setKdUnitKerja(inputWrapper.getKdUnitKerja());
            memorandumNonPejabat.setKdMemorandum(kdMemorandum);

            memorandumService.createMemorandumNonPejabat(memorandumNonPejabat);
        }

        return new ResponseEntity<Object>(new CustomMessage("memorandum created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/sebar-memorandum/{kdMemorandum}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarMemorandum(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("sebar memorandum");

        Memorandum memorandum
                = memorandumService.getByKdMemorandum(kdMemorandum);
        memorandum.setStatusPenyebaran(1);

        memorandumService.update(memorandum);

        return new ResponseEntity<Object>(new CustomMessage("memorandum sudah disebar"), HttpStatus.OK);
    }

    @RequestMapping(value = "/approve-memorandum/{kdMemorandum}", method = RequestMethod.PUT)
    ResponseEntity<?> approveMemorandum(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("approve memorandum");

        memorandumService.approveMemorandum(kdMemorandum);

        return new ResponseEntity<Object>(new CustomMessage("memorandum sudah di approve"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-daftar-memorandum-history/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarMemorandumHistoryByPembuat(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get daftar memorandum history by pembuat : "+nipPembuat);

        List<Memorandum> memorandumList
                = memorandumService.getByNipPembuat(nipPembuat);
        List<MemorandumHistoryWrapper> memorandumHistoryWrappers
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        boolean isSuratPejabat = false;

        for (Memorandum memorandum
                : memorandumList) {
            if (memorandum.getMemorandumPejabat() != null)
                isSuratPejabat = true;
            else
                isSuratPejabat = false;

            memorandumHistoryWrappers
                    .add(new MemorandumHistoryWrapper(
                            memorandum.getKdMemorandum(),
                            df.format(new Date(memorandum.getTanggalPembuatanMilis())),
                            isSuratPejabat,
                            memorandum.getStatusBaca(),
                            "memorandum",
                            2,
                            memorandum.getTanggalPembuatanMilis(),
                            memorandum.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(memorandumHistoryWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-daftar-memorandum-target/{nipTarget}/{isPersuratan}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarMemorandumTarget(
            @PathVariable("nipTarget") String nipTarget,
            @PathVariable("isPersuratan") boolean isPersuratan) {
        LOGGER.info("get daftar memorandum unread pegawai : "+ nipTarget);

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

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

        List<Memorandum> memorandumTargetList
                = new ArrayList<>();
        List<TembusanMemorandum> tembusanMemorandumList
                = new ArrayList<>();

        if (!isPegawaiTargetAdminSurat) {
            memorandumTargetList
                    = memorandumService.getByNipTarget(nipTarget);
            tembusanMemorandumList
                    = memorandumService.getTembusanMemorandum(pegawaiTarget.getKdJabatan());
        }
        else {
            memorandumTargetList
                    = memorandumService.getMemorandumByUnitKerjaTarget(pegawaiTarget.getKdUnitKerja());
            tembusanMemorandumList
                    = memorandumService.getTembusanMemorandumUnitKerja(pegawaiTarget.getKdUnitKerja());
        }


        List<MemorandumTargetWrapper> memorandumTargetWrappers
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        boolean isSuratPejabat = false;

        //get surat perintah berdasarkan target
        for (Memorandum memorandum
                : memorandumTargetList) {
            if (memorandum.getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(memorandum.getNipPenandatangan())) {

                        if (!isPegawaiTargetAdminSurat) {
                            if (pegawaiPemberi.getKdUnitKerja().equals(pegawaiTarget.getKdUnitKerja())) {

                                if (memorandum.getMemorandumPejabat() != null)
                                    isSuratPejabat = true;
                                else
                                    isSuratPejabat = false;

                                memorandumTargetWrappers
                                        .add(new MemorandumTargetWrapper(
                                                memorandum.getKdMemorandum(),
                                                df.format(new Date(memorandum.getTanggalPembuatanMilis())),
                                                memorandum.getTanggalPembuatanMilis(),
                                                isSuratPejabat,
                                                pegawaiPemberi.getNip(),
                                                pegawaiPemberi.getNama(),
                                                pegawaiPemberi.getJabatan(),
                                                memorandum.getStatusBaca(),
                                                "memorandum",
                                                2,
                                                tkdUnkDao.findOne(pegawaiPemberi.getKdUnitKerja()).getUnitKerja()));
                            }

                        }
                        else {
                            boolean isTargetValid = false;
                            if (pegawaiPemberi.getKdUnitKerja()
                                    .equals(pegawaiTarget.getKdUnitKerja())) {
                                if (memorandum.getNipPenerimaMemorandum()
                                        .equals(pegawaiTarget.getNip())) {
                                    if (!isPersuratan) {
                                        isTargetValid = true;
                                    }
                                }

                            }
                            else {
                                if (isPersuratan) isTargetValid = true;
                            }

                            if (isTargetValid) {

                                if (memorandum.getMemorandumPejabat() != null)
                                    isSuratPejabat = true;
                                else
                                    isSuratPejabat = false;

                                memorandumTargetWrappers
                                        .add(new MemorandumTargetWrapper(memorandum.getKdMemorandum(),
                                                df.format(new Date(memorandum.getTanggalPembuatanMilis())),
                                                memorandum.getTanggalPembuatanMilis(),
                                                isSuratPejabat,
                                                pegawaiPemberi.getNip(),
                                                pegawaiPemberi.getNama(),
                                                pegawaiPemberi.getJabatan(),
                                                memorandum.getStatusBaca(),
                                                "memorandum",
                                                2,
                                                tkdUnkDao.findOne(pegawaiPemberi.getKdUnitKerja()).getUnitKerja()));
                            }

                        }

                        break;

                    }

                }
            }

        }
        //get surat perintah berdasarkan tembusan
        for (TembusanMemorandum tembusanMemorandum
                : tembusanMemorandumList) {
            if (tembusanMemorandum.getMemorandum().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(tembusanMemorandum.getMemorandum().getNipPenandatangan())) {

                        if (!isPegawaiTargetAdminSurat) {
                            if (pegawaiPemberi.getKdUnitKerja().equals(pegawaiTarget.getKdUnitKerja())) {

                                if (tembusanMemorandum.getMemorandum().getMemorandumPejabat() != null)
                                    isSuratPejabat = true;
                                else
                                    isSuratPejabat = false;

                                memorandumTargetWrappers
                                        .add(new MemorandumTargetWrapper(tembusanMemorandum.getMemorandum().getKdMemorandum(),
                                                df.format(new Date(tembusanMemorandum.getMemorandum().getTanggalPembuatanMilis())),
                                                tembusanMemorandum.getMemorandum().getTanggalPembuatanMilis(),
                                                isSuratPejabat,
                                                pegawaiPemberi.getNip(),
                                                pegawaiPemberi.getNama(),
                                                pegawaiPemberi.getJabatan(),
                                                tembusanMemorandum.getStatusBaca(),
                                                "memorandum",
                                                2,
                                                tkdUnkDao.findOne(pegawaiPemberi.getKdUnitKerja()).getUnitKerja()));
                            }

                        }
                        else {
                            boolean isTargetValid = false;
                            if (pegawaiPemberi.getKdUnitKerja()
                                    .equals(pegawaiTarget.getKdUnitKerja())) {
                                if (tembusanMemorandum.getTembusanMemorandumId().getKdJabatan()
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

                                if (tembusanMemorandum.getMemorandum().getMemorandumPejabat() != null)
                                    isSuratPejabat = true;
                                else
                                    isSuratPejabat = false;

                                memorandumTargetWrappers
                                        .add(new MemorandumTargetWrapper(tembusanMemorandum.getMemorandum().getKdMemorandum(),
                                                df.format(new Date(tembusanMemorandum.getMemorandum().getTanggalPembuatanMilis())),
                                                tembusanMemorandum.getMemorandum().getTanggalPembuatanMilis(),
                                                isSuratPejabat,
                                                pegawaiPemberi.getNip(),
                                                pegawaiPemberi.getNama(),
                                                pegawaiPemberi.getJabatan(),
                                                tembusanMemorandum.getStatusBaca(),
                                                "memorandum",
                                                2,
                                                tkdUnkDao.findOne(pegawaiPemberi.getKdUnitKerja()).getUnitKerja()));
                            }

                        }
                        break;

                    }

                }

            }

        }

        return new ResponseEntity<Object>(memorandumTargetWrappers, HttpStatus.OK);
    }


    @RequestMapping(value = "/get-daftar-memorandum-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarMemorandumUnread(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get daftar memorandum unread pegawai : "+ nipTarget);

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential pegawaiTarget = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (nipTarget.equals(pegawai.getNip())) {
                pegawaiTarget = pegawai;

                break;
            }
        }

        List<Memorandum> memorandumTargetList
                = memorandumService.getByNipTarget(nipTarget);
        List<TembusanMemorandum> tembusanMemorandumList
                = memorandumService.getTembusanMemorandum(pegawaiTarget.getKdJabatan());
        List<MemorandumTargetWrapper> memorandumTargetWrappers
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        boolean isSuratPejabat = false;

        //get surat perintah berdasarkan target
        for (Memorandum memorandum
                : memorandumTargetList) {
            if (memorandum.getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(memorandum.getNipPenandatangan())) {
                        if (memorandum.getMemorandumPejabat() != null)
                            isSuratPejabat = true;
                        else
                            isSuratPejabat = false;

                        if (memorandum.getStatusBaca() == 0) {
                            memorandumTargetWrappers
                                    .add(new MemorandumTargetWrapper(
                                            memorandum.getKdMemorandum(),
                                            df.format(new Date(memorandum.getTanggalPembuatanMilis())),
                                            memorandum.getTanggalPembuatanMilis(),
                                            isSuratPejabat,
                                            pegawaiPemberi.getNip(),
                                            pegawaiPemberi.getNama(),
                                            pegawaiPemberi.getJabatan(),
                                            memorandum.getStatusBaca(),
                                            "memorandum",
                                            2));
                        }

                        break;

                    }

                }

            }

        }
        //get surat perintah berdasarkan tembusan
        for (TembusanMemorandum tembusanMemorandum
                : tembusanMemorandumList) {
            if (tembusanMemorandum.getMemorandum().getStatusPenyebaran() == 1) {
                for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                    if (pegawaiPemberi.getNip()
                            .equals(tembusanMemorandum.getMemorandum().getNipPenandatangan())) {
                        if (tembusanMemorandum.getMemorandum().getMemorandumPejabat() != null)
                            isSuratPejabat = true;
                        else
                            isSuratPejabat = false;

                        if (tembusanMemorandum.getStatusBaca() == 0) {
                            memorandumTargetWrappers
                                    .add(new MemorandumTargetWrapper(tembusanMemorandum.getMemorandum().getKdMemorandum(),
                                            df.format(new Date(tembusanMemorandum.getMemorandum().getTanggalPembuatanMilis())),
                                            tembusanMemorandum.getMemorandum().getTanggalPembuatanMilis(),
                                            isSuratPejabat,
                                            pegawaiPemberi.getNip(),
                                            pegawaiPemberi.getNama(),
                                            pegawaiPemberi.getJabatan(),
                                            tembusanMemorandum.getStatusBaca(),
                                            "memorandum",
                                            2));
                        }

                        break;

                    }

                }

            }

        }

        return new ResponseEntity<Object>(memorandumTargetWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-memorandum-by-kd-memorandum/{kdMemorandum}", method = RequestMethod.GET)
    ResponseEntity<?> getMemorandumBykdMemorandum(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("get memorandum by kd memorandum");

        List<JabatanWrapper> tembusanMemorandumList = new ArrayList<>();
        Memorandum memorandum = memorandumService.getByKdMemorandum(kdMemorandum);

        List<TkdJabatan> tkdJabatanList = tkdJabatanService.getAll();
        for (TembusanMemorandum tembusanMemorandum
                : memorandum.getTembusanMemorandumList()) {
            for (TkdJabatan tkdJabatan : tkdJabatanList){
                if (tkdJabatan.getKdJabatan()
                        .equals(tembusanMemorandum.getTembusanMemorandumId().getKdJabatan())) {
                    JabatanWrapper jabatanWrapper = new JabatanWrapper();

                    jabatanWrapper.setKdJabatan(tkdJabatan.getKdJabatan());
                    jabatanWrapper.setJabatan(tkdJabatan.getJabatan());
                    jabatanWrapper.setEselon(tkdJabatan.getEselon());

                    tembusanMemorandumList.add(jabatanWrapper);

                    break;

                }

            }

        }

        CustomPegawaiCredential
                penerima = null,
                pemberi = null,
                pembuat = null,
                penandatangan = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        // get penerima
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(memorandum.getNipPenerimaMemorandum())) {
                penerima = qutPegawai;
                break;
            }
        }
        // get pemberi
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(memorandum.getNipPemberiMemorandum())) {
                pemberi = qutPegawai;
                break;
            }
        }
        // get pembuat
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(memorandum.getNipPembuatSurat())) {
                pembuat = qutPegawai;
                break;
            }
        }
        // get penandatangan
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(memorandum.getNipPenandatangan())) {
                penandatangan = qutPegawai;
                break;
            }
        }

        boolean isSuratPejabat = false;

        if (memorandum.getMemorandumPejabat() != null) {
            isSuratPejabat = true;
        }

        String base64BarcodeImage = null;
//        String kdBarcode
//                = memorandum.getKdBarcode()+memorandum.getNomorUrut()+memorandum.getKdUnitKerja()+"2";
        if (memorandum.getKdBarcode() != null) {
            BarcodeGenerator generator = new BarcodeGenerator();

            base64BarcodeImage
                    = generator.convertBarcodeImageIntoBase64String(
                        generator.generateBarcode(memorandum.getKdBarcode()));
        }

        MemorandumWrapper memorandumWrapper
                = new MemorandumWrapper(
                        memorandum.getKdMemorandum(),
                        memorandum.getNomorUrusan(),
                        memorandum.getNomorUrut(),
                        memorandum.getNomorPasanganUrut(),
                        memorandum.getNomorUnit(),
                        memorandum.getNomorTahun(),
                        penerima.getNip(),
                        penerima.getNama(),
                        penerima.getJabatan(),
                        tkdUnkDao.findOne(penerima.getKdUnitKerja()).getUnitKerja(),
                penerima.getGlrDpn(), penerima.getGlrBlk(), penerima.getPangkat(), penerima.getGol(), pemberi.getNip(),
                        pemberi.getNama(),
                        pemberi.getJabatan(),
                        tkdUnkDao.findOne(pemberi.getKdUnitKerja()).getUnitKerja(),
                pemberi.getGlrDpn(), pemberi.getGlrBlk(), pemberi.getPangkat(), pemberi.getGol(), memorandum.getHal(),
                        memorandum.getTanggalPembuatanMilis(),
                        memorandum.getIsiMemorandum(),
                        pembuat.getNip(),
                        pembuat.getNama(),
                        pembuat.getJabatan(),
                        tkdUnkDao.findOne(pembuat.getKdUnitKerja()).getUnitKerja(),
                pembuat.getGlrDpn(), pembuat.getGlrBlk(), pembuat.getPangkat(), pembuat.getGol(), penandatangan.getNip(),
                        penandatangan.getNama(),
                        penandatangan.getJabatan(),
                        tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja(),
                penandatangan.getGlrDpn(), penandatangan.getGlrBlk(), penandatangan.getPangkat(), penandatangan.getGol(),
                tembusanMemorandumList, isSuratPejabat,
                base64BarcodeImage);


        return new ResponseEntity<Object>(memorandumWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-memorandum-by-target/{kdMemorandum}/{nipTarget}", method = RequestMethod.PUT)
    ResponseEntity<?> openMemorandumByTarget(
            @PathVariable("kdMemorandum") String kdMemorandum,
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("open memorandum by penilai");

        QutPegawai pegawaiTarget = qutPegawaiService.getQutPegawai(nipTarget);

        Memorandum memorandum = memorandumService.getByKdMemorandum(kdMemorandum);

        if (pegawaiTarget.getNip()
                .equals(memorandum.getNipPenerimaMemorandum())) {
            memorandumService.openMemorandum(kdMemorandum);
        }

        boolean exist = false;
        for (TembusanMemorandum tembusanMemorandum
                : memorandum.getTembusanMemorandumList()) {
            if (tembusanMemorandum.getTembusanMemorandumId().getKdJabatan()
                    .equals(pegawaiTarget.getKdJabatan())) {
                exist = true;
                break;
            }
        }

        if (exist) {
            memorandumService
                    .openTembusanMemorandum(
                            new TembusanMemorandumId(kdMemorandum, pegawaiTarget.getKdJabatan()));
        }

        return new ResponseEntity<Object>(new CustomMessage("laporan opened by penilai"), HttpStatus.OK);
    }

    @RequestMapping(value = "/open-memorandum-by-penilai/{kdMemorandum}", method = RequestMethod.PUT)
    ResponseEntity<?> openMemorandumByPenilai(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("open memorandum by penilai");

        memorandumService.openMemorandumByPenilai(kdMemorandum);

        return new ResponseEntity<Object>(new CustomMessage("laporan opened by penilai"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-draft-memorandum-approval/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getDraftMemorandumApproval(
            @PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get draft memorandum approval");

        List<Memorandum> draftMemorandumApprovalList
                = memorandumService.getDraftMemorandumApproval(kdUnitKerja);
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();


        List<DraftSuratApprovalWrapper> draftSuratApprovalWrappers
                = new ArrayList<>();

        for (Memorandum memorandum : draftMemorandumApprovalList) {
            boolean isSuratPejabat = false;

            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(memorandum.getNipPenandatangan())) {
                    if (memorandum.getMemorandumNonPejabat() != null) {
                        isSuratPejabat = true;
                    }

                    draftSuratApprovalWrappers
                            .add(new DraftSuratApprovalWrapper(
                                    memorandum.getKdMemorandum(),
                                    null,
                                    memorandum.getTanggalPembuatanMilis(),
                                    isSuratPejabat,
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan(),
                                    memorandum.getStatusPenyebaran(),
                                    "memorandum",
                                    2
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
     * @return
     */
    public MemorandumWrapper getMemorandumWrapper(String kdMemorandum) {
        List<JabatanWrapper> tembusanMemorandumList = new ArrayList<>();
        Memorandum memorandum = memorandumService.getByKdMemorandum(kdMemorandum);

        List<TkdJabatan> tkdJabatanList = tkdJabatanService.getAll();
        for (TembusanMemorandum tembusanMemorandum
                : memorandum.getTembusanMemorandumList()) {
            for (TkdJabatan tkdJabatan : tkdJabatanList){
                if (tkdJabatan.getKdJabatan()
                        .equals(tembusanMemorandum.getTembusanMemorandumId().getKdJabatan())) {
                    JabatanWrapper jabatanWrapper = new JabatanWrapper();

                    jabatanWrapper.setKdJabatan(tkdJabatan.getKdJabatan());
                    jabatanWrapper.setJabatan(tkdJabatan.getJabatan());
                    jabatanWrapper.setEselon(tkdJabatan.getEselon());

                    tembusanMemorandumList.add(jabatanWrapper);

                    break;

                }

            }

        }

        CustomPegawaiCredential
                penerima = null,
                pemberi = null,
                pembuat = null,
                penandatangan = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        // get penerima
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(memorandum.getNipPenerimaMemorandum())) {
                penerima = qutPegawai;
                break;
            }
        }
        // get pemberi
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(memorandum.getNipPemberiMemorandum())) {
                pemberi = qutPegawai;
                break;
            }
        }
        // get pembuat
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(memorandum.getNipPembuatSurat())) {
                pembuat = qutPegawai;
                break;
            }
        }
        // get penandatangan
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(memorandum.getNipPenandatangan())) {
                penandatangan = qutPegawai;
                break;
            }
        }

        boolean isSuratPejabat = false;

        if (memorandum.getMemorandumPejabat() != null) {
            isSuratPejabat = true;
        }

        String base64BarcodeImage = null;
//        String kdBarcode
//                = memorandum.getKdBarcode()+memorandum.getNomorUrut()+memorandum.getKdUnitKerja()+"2";
        if (memorandum.getKdBarcode() != null) {
            BarcodeGenerator generator = new BarcodeGenerator();

            base64BarcodeImage
                    = generator.convertBarcodeImageIntoBase64String(
                    generator.generateBarcode(memorandum.getKdBarcode()));
        }

        MemorandumWrapper memorandumWrapper
                = new MemorandumWrapper(
                memorandum.getKdMemorandum(),
                memorandum.getNomorUrusan(),
                memorandum.getNomorUrut(),
                memorandum.getNomorPasanganUrut(),
                memorandum.getNomorUnit(),
                memorandum.getNomorTahun(),
                penerima.getNip(),
                penerima.getNama(),
                penerima.getJabatan(),
                tkdUnkDao.findOne(penerima.getKdUnitKerja()).getUnitKerja(),
                penerima.getGlrDpn(), penerima.getGlrBlk(), penerima.getPangkat(), penerima.getGol(), pemberi.getNip(),
                pemberi.getNama(),
                pemberi.getJabatan(),
                tkdUnkDao.findOne(pemberi.getKdUnitKerja()).getUnitKerja(),
                pemberi.getGlrDpn(), pemberi.getGlrBlk(), pemberi.getPangkat(), pemberi.getGol(), memorandum.getHal(),
                memorandum.getTanggalPembuatanMilis(),
                memorandum.getIsiMemorandum(),
                pembuat.getNip(),
                pembuat.getNama(),
                pembuat.getJabatan(),
                tkdUnkDao.findOne(pembuat.getKdUnitKerja()).getUnitKerja(),
                pembuat.getGlrDpn(), pembuat.getGlrBlk(), pembuat.getPangkat(), pembuat.getGol(), penandatangan.getNip(),
                penandatangan.getNama(),
                penandatangan.getJabatan(),
                tkdUnkDao.findOne(penandatangan.getKdUnitKerja()).getUnitKerja(),
                penandatangan.getGlrDpn(), penandatangan.getGlrBlk(), penandatangan.getPangkat(), penandatangan.getGol(),
                tembusanMemorandumList, isSuratPejabat,
                base64BarcodeImage);

        return memorandumWrapper;
    }

}
