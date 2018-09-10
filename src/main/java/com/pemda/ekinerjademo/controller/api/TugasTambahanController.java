package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.TugasTambahan;
import com.pemda.ekinerjademo.service.TugasTambahanService;
import com.pemda.ekinerjademo.wrapper.input.TugasTambahanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.TugasTambahanWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class TugasTambahanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TugasTambahan.class);

    @Autowired private TugasTambahanService tugasTambahanService;

    @RequestMapping(value = "/create-tugas-tambahan", method = RequestMethod.POST)
    ResponseEntity<?> createTugasTambahan(@RequestBody TugasTambahanInputWrapper inputWrapper) {
        LOGGER.info("create tugas tambahan");

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.of("Asia/Jakarta")).toLocalDate();
        int month = localDate.getMonthValue() - 1;
        int year = localDate.getYear();

        Short monthInShort = (short) month;
        Short yearInShort = (short) year;

        TugasTambahan tugasTambahan = new TugasTambahan();

        tugasTambahan.setId(String.valueOf(date.getTime()));
        tugasTambahan.setBulan(monthInShort);
        tugasTambahan.setTahun(yearInShort);
        tugasTambahan.setNipPegawai(inputWrapper.getNipPegawai());
        tugasTambahan.setDeskripsi(inputWrapper.getDeskripsi());

        tugasTambahanService.createTugasTambahan(tugasTambahan);

        return new ResponseEntity<>(new CustomMessage("tugas tambahan telah dibuat"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-tugas-tambahan/{nipPegawai}/{bulan}/{tahun}", method = RequestMethod.GET)
    ResponseEntity<?> getTugasTambahan(
            @PathVariable("nipPegawai") String nipPegawai,
            @PathVariable("bulan") Short bulan,
            @PathVariable("tahun") Short tahun) {
        LOGGER.info("get tugas tambahan");

        List<TugasTambahan> tugasTambahans
                = tugasTambahanService.findByPegawaiBulanTahun(nipPegawai,bulan, tahun);
        List<TugasTambahanWrapper> tugasTambahanWrappers
                = new ArrayList<>();

        for (TugasTambahan obj: tugasTambahans) {
            tugasTambahanWrappers.add(new TugasTambahanWrapper(obj.getId(),
                    obj.getBulan(),
                    obj.getTahun(),
                    obj.getDeskripsi()));
        }

        return new ResponseEntity<>(tugasTambahanWrappers, HttpStatus.OK);
    }

}
