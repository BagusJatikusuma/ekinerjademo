package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.NotaDinas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanNotaDinas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanNotaDinasId;
import com.pemda.ekinerjademo.wrapper.input.NotaDinasInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 30/01/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class NotaDinasController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PejabatPenilaiDinilaiController.class);

    @RequestMapping(value = "/create-nota-dinas", method = RequestMethod.POST)
    ResponseEntity<?> createNotaDinas(@RequestBody NotaDinasInputWrapper inputWrapper) {
        LOGGER.info("create nota dinas");

        String kdNotaDinas = String.valueOf(new Date().getTime());

        List<TembusanNotaDinas> tembusanNotaDinasList
                = new ArrayList<>();
        //build termbusan nota dinas
        for (String kdJabatan
                : inputWrapper.getKdTembusanList()) {
            TembusanNotaDinasId id
                    = new TembusanNotaDinasId(kdNotaDinas, kdJabatan);
            TembusanNotaDinas tembusanNotaDinas = new TembusanNotaDinas();

            tembusanNotaDinas.setTembusanNotaDinasId(id);
            tembusanNotaDinas.setStatusBaca(0);
            tembusanNotaDinas.setStatusDiterima(0);

            tembusanNotaDinasList.add(tembusanNotaDinas);
        }

        NotaDinas notaDinas = new NotaDinas();

        notaDinas.setKdNotaDinas(kdNotaDinas);
        notaDinas.setNomorUrusan(inputWrapper.getNomorUrusan());
        notaDinas.setNomorUnit(inputWrapper.getNomorUnit());
        notaDinas.setNomorUrut(0);
        notaDinas.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        notaDinas.setNomorTahun(Year.now().getValue());

        notaDinas.setKdJabatanPenerimaNotaDinas(inputWrapper.getKdJabatanPenerimaNotaDinas());
        notaDinas.setNipPemberiNotaDinas(inputWrapper.getNipPemberiNotaDinas());
        notaDinas.setHal(inputWrapper.getHal());
        notaDinas.setTanggalPembuatanMilis(new Date().getTime());
        notaDinas.setIsiNotaDinas(inputWrapper.getIsiNotaDinas());


        return new ResponseEntity<Object>(new CustomMessage("nota dinas created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-nota-dinas-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getNotaDinasByPembuat(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get nota dinas by pembuat");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-nota-dinas-by-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getNotaDinasByTarget(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get nota dinas by target");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-nota-dinas-by-target-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getNotaDinasByTargetUnread() {
        LOGGER.info("get nota dinas by target unread");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-nota-dinas-by-kd-nota-dinas/{kdNotaDinas}", method = RequestMethod.GET)
    ResponseEntity<?> getNotaDinasByKdNotaDinas(@PathVariable("kdNotaDinas") String kdNotaDinas) {
        LOGGER.info("get nota dinas by kode nota dinas");

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-nota-dinas/{kdNotaDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> openNotaDinas(@PathVariable("kdNotaDinas") String kdNotaDinas) {
        LOGGER.info("open nota dinas");

        return new ResponseEntity<Object>(
                new CustomMessage("nota dinas opened"), HttpStatus.OK);
    }

    @RequestMapping(value = "/open-nota-dinas-by-penilai/{kdNotaDinas}", method = RequestMethod.PUT)
    ResponseEntity<?> openNotaDinasByPenilai(@PathVariable("kdNotaDinas") String kdNotaDinas) {
        LOGGER.info("open nota dinas by penilai");

        return new ResponseEntity<Object>(
                new CustomMessage("nota dinas opened"), HttpStatus.OK);
    }

}
