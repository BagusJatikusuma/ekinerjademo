package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.MemorandumService;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<?> createMemorandum() {
        LOGGER.info("create memorandum");

        return null;
    }

    @RequestMapping(value = "/sebar-memorandum/{kdMemorandum}", method = RequestMethod.PUT)
    ResponseEntity<?> sebarMemorandum(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("sebar memorandum");

        return null;
    }

    @RequestMapping(value = "/approve-memorandum/{kdMemorandum}", method = RequestMethod.PUT)
    ResponseEntity<?> approveMemorandum(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("approve memorandum");

        return null;
    }

    @RequestMapping(value = "/get-daftar-memorandum-history/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarMemorandumHistoryByPembuat(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get daftar memorandum history by pembuat : "+nipPembuat);

        return null;
    }

    @RequestMapping(value = "/get-daftar-memorandum-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarMemorandumUnread(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get daftar memorandum unread pegawai : "+ nipTarget);

        return null;
    }

    @RequestMapping(value = "/get-memorandum-by-kd-memorandum/{kdMemorandum}", method = RequestMethod.GET)
    ResponseEntity<?> getMemorandumBykdMemorandum(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("get memorandum by kd memorandum");

        return null;
    }

    @RequestMapping(value = "/open-memorandum-by-penilai/{kdMemorandum}", method = RequestMethod.PUT)
    ResponseEntity<?> openMemorandumByPenilai(@PathVariable("kdMemorandum") String kdMemorandum) {
        LOGGER.info("open memorandum by penilai");

        return null;
    }

}
