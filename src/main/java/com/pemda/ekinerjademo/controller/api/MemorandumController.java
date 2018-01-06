package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.Memorandum;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanMemorandum;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanMemorandumId;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.MemorandumService;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.wrapper.input.MemorandumInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.MemorandumHistoryWrapper;
import com.pemda.ekinerjademo.wrapper.output.MemorandumTargetWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

        // attach list tembusan into memorandum

        //save memorandum
        //save tembusan memorandum

        //save memorandum pejabat or non pejabat

        return null;
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

    @RequestMapping(value = "/get-memorandum-by-kd-memorandum/{kdMemorandum}", method = RequestMethod.GET)
    ResponseEntity<?> getMemorandumBykdMemorandum(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("get memorandum by kd memorandum");



        return null;
    }

    @RequestMapping(value = "/open-memorandum-by-penilai/{kdMemorandum}", method = RequestMethod.PUT)
    ResponseEntity<?> openMemorandumByPenilai(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("open memorandum by penilai");

        memorandumService.openMemorandumByPenilai(kdMemorandum);

        return new ResponseEntity<Object>(new CustomMessage("laporan opened by penilai"), HttpStatus.OK);
    }

}
