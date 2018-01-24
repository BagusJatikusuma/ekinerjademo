package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.MemorandumService;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.wrapper.input.MemorandumInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.MemorandumHistoryWrapper;
import com.pemda.ekinerjademo.wrapper.output.MemorandumTargetWrapper;
import com.pemda.ekinerjademo.wrapper.output.MemorandumWrapper;
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
        memorandum.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        memorandum.setNipPenilai("");

        memorandum.setStatusBaca(0);
        memorandum.setStatusPenyebaran(0);
        memorandum.setStatusPenilaian(0);

        if (inputWrapper.getKdMemorandumBawahan() == null) {
            memorandum.setPathPenilaian(kdMemorandum);
            memorandum.setStatusPenilaian(0);
        } else {
            Memorandum memorandumBawahan
                    = memorandumService.getByKdMemorandum(inputWrapper.getKdMemorandumBawahan());
            memorandum.setPathPenilaian(memorandumBawahan.getPathPenilaian()+"."+kdMemorandum);

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


        return null;
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
                            -1,
                            memorandum.getTanggalPembuatanMilis(),
                            memorandum.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(memorandumHistoryWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-daftar-memorandum-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarMemorandumTarget(@PathVariable("nipTarget") String nipTarget) {
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
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(memorandum.getNipPenandatangan())) {
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
                                    -1));

                    break;

                }

            }

        }
        //get surat perintah berdasarkan tembusan
        for (TembusanMemorandum tembusanMemorandum
                : tembusanMemorandumList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(tembusanMemorandum.getMemorandum().getNipPenandatangan())) {
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
                                    -1));

                    break;

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
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(memorandum.getNipPenandatangan())) {
                    if (memorandum.getMemorandumPejabat() != null)
                        isSuratPejabat = true;
                    else
                        isSuratPejabat = false;

                    if (memorandum.getStatusBaca() == 0) {
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
                                        -1));
                    }

                    break;

                }

            }

        }
        //get surat perintah berdasarkan tembusan
        for (TembusanMemorandum tembusanMemorandum
                : tembusanMemorandumList) {
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
                                        -1));
                    }

                    break;

                }

            }

        }

        return new ResponseEntity<Object>(memorandumTargetWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-memorandum-by-kd-memorandum/{kdMemorandum}", method = RequestMethod.GET)
    ResponseEntity<?> getMemorandumBykdMemorandum(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("get memorandum by kd memorandum");

        Memorandum memorandum = memorandumService.getByKdMemorandum(kdMemorandum);

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
                        penerima.getUnitKerja(),
                        pemberi.getNip(),
                        pemberi.getNama(),
                        pemberi.getJabatan(),
                        pemberi.getUnitKerja(),
                        memorandum.getHal(),
                        memorandum.getTanggalPembuatanMilis(),
                        memorandum.getIsiMemorandum(),
                        pembuat.getNip(),
                        pembuat.getNama(),
                        pembuat.getJabatan(),
                        pembuat.getUnitKerja(),
                        penandatangan.getNip(),
                        penandatangan.getNama(),
                        penandatangan.getJabatan(),
                        penandatangan.getUnitKerja());


        return new ResponseEntity<Object>(memorandumWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-memorandum-by-target/{kdMemorandum}", method = RequestMethod.PUT)
    ResponseEntity<?> openMemorandumByTarget(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("open memorandum by penilai");

        memorandumService.openMemorandum(kdMemorandum);

        return new ResponseEntity<Object>(new CustomMessage("laporan opened by penilai"), HttpStatus.OK);
    }

    @RequestMapping(value = "/open-memorandum-by-penilai/{kdMemorandum}", method = RequestMethod.PUT)
    ResponseEntity<?> openMemorandumByPenilai(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("open memorandum by penilai");

        memorandumService.openMemorandumByPenilai(kdMemorandum);

        return new ResponseEntity<Object>(new CustomMessage("laporan opened by penilai"), HttpStatus.OK);
    }

}
