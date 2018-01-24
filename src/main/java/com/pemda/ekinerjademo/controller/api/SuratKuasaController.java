package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKuasa;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratKuasaService;
import com.pemda.ekinerjademo.wrapper.input.BeritaAcaraInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.SuratKuasaInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.SuratKuasaHistoryWrapper;
import com.pemda.ekinerjademo.wrapper.output.SuratKuasaPenerimaKuasaWrapper;
import com.pemda.ekinerjademo.wrapper.output.SuratKuasaWrapper;
import groovy.transform.Synchronized;
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
 * Created by bayu on 07/12/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class SuratKuasaController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SuratKuasaController.class);

    @Autowired private SuratKuasaService suratKuasaService;
    @Autowired private QutPegawaiService qutPegawaiService;

    @RequestMapping(value = "/create-surat-kuasa", method = RequestMethod.POST)
    @Synchronized
    ResponseEntity<?> createSuratKuasa(
            @RequestBody SuratKuasaInputWrapper inputWrapper) {
        LOGGER.info("create surat kuasa");

        String kdSuratKuasa = String.valueOf(new Date().getTime());

        SuratKuasa suratKuasa = new SuratKuasa();

        suratKuasa.setKdSuratKuasa(kdSuratKuasa);
        suratKuasa.setNomorUrusan(inputWrapper.getNomorUrusan());
        suratKuasa.setNomorUrut(null);
        suratKuasa.setNomorPasanganUrut(inputWrapper.getNomorPasanganUrut());
        suratKuasa.setNomorUnit(inputWrapper.getNomorUnit());
        suratKuasa.setNomorTahun(Year.now().getValue());

        suratKuasa.setNipPemberiKuasa(inputWrapper.getNipPemberiKuasa());
        suratKuasa.setNipPenerimaKuasa(inputWrapper.getNipPenerimaKuasa());
        suratKuasa.setIsiKuasa(inputWrapper.getIsiKuasa());

        suratKuasa.setKotaPembuatanSurat(inputWrapper.getKotaPembuatanSurat());
        suratKuasa.setTanggalPembuatanMilis(new Date().getTime());
        suratKuasa.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        suratKuasa.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        suratKuasa.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        suratKuasa.setNipPenilai("");
        suratKuasa.setStatusPenilaian(0);
        suratKuasa.setAlasanPenolakan("");

        if (inputWrapper.getKdSuratKuasaBawahan() == null) {
            suratKuasa.setPathPenilaian(kdSuratKuasa);
        } else {
            SuratKuasa suratKuasaBawahan
                    = suratKuasaService.getSuratKuasa(inputWrapper.getKdSuratKuasaBawahan());
            suratKuasa.setPathPenilaian(suratKuasaBawahan.getPathPenilaian()+"."+kdSuratKuasa);

            suratKuasaBawahan.setStatusPenilaian(2);
            suratKuasaService.createSuratKuasa(suratKuasaBawahan);
        }

        suratKuasaService.createSuratKuasa(suratKuasa);

        return new ResponseEntity<Object>(
                new CustomMessage("surat kuasa created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-daftar-surat-kuasa-history-by-pegawai/{nipPembuatSurat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarSuratKuasaHistoryByPegawai(
            @PathVariable("nipPembuatSurat") String nipPembuatSurat) {
        LOGGER.info("get surat kuasa history by nip "+nipPembuatSurat);

        List<SuratKuasa> suratKuasaList
                = suratKuasaService.getByNipPembuatSurat(nipPembuatSurat);

        List<SuratKuasaHistoryWrapper> suratKuasaHistoryWrapperList
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (SuratKuasa suratKuasa
                : suratKuasaList) {

            suratKuasaHistoryWrapperList
                    .add(new SuratKuasaHistoryWrapper(
                            suratKuasa.getKdSuratKuasa(),
                            df.format(new Date(suratKuasa.getTanggalPembuatanMilis())),
                            suratKuasa.getStatusBaca()
                    ));
        }

        return new ResponseEntity<Object>(suratKuasaHistoryWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-surat-kuasa/{kdSuratKuasa}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratKuasa(
            @PathVariable("kdSuratKuasa") String kdSuratKuasa) {
        LOGGER.info("open surat kuasa");

        SuratKuasa suratKuasa = suratKuasaService.getSuratKuasa(kdSuratKuasa);

        suratKuasa.setStatusBaca(1);
        suratKuasaService.createSuratKuasa(suratKuasa);

        return new ResponseEntity<Object>(new CustomMessage("laporan opened by penilai"), HttpStatus.OK);

    }

    @RequestMapping(value = "/open-surat-kuasa-penilai/{kdSuratKuasa}/{nipPegawai}", method = RequestMethod.PUT)
    ResponseEntity<?> openSuratKuasaPenilai(
            @PathVariable("kdSuratKuasa") String kdSuratKuasa,
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("open surat kuasa");

        suratKuasaService.openSuratKuasaByPenilai(kdSuratKuasa);

        return new ResponseEntity<Object>(new CustomMessage("laporan opened by penilai"), HttpStatus.OK);

    }

    @RequestMapping(
            value = "/get-surat-kuasa-by-kd-surat-kuasa/{kdSuratKuasa}",
            method = RequestMethod.GET)
    ResponseEntity<?> getSuratKuasaByKdSuratKuasa(
            @PathVariable("kdSuratKuasa") String kdSuratKuasa) {
        LOGGER.info("get surat kuasa by kd surat kuasa");

        SuratKuasa suratKuasa = suratKuasaService.getSuratKuasa(kdSuratKuasa);

        CustomPegawaiCredential
                pemberiKuasa = null,
                penerimaKuasa = null;

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        // get pemberi kuasa
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(suratKuasa.getNipPemberiKuasa())) {
                pemberiKuasa = qutPegawai;
                break;
            }
        }
        // get pemberi kuasa
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            if (qutPegawai.getNip()
                    .equals(suratKuasa.getNipPenerimaKuasa())) {
                penerimaKuasa = qutPegawai;
                break;
            }
        }

        SuratKuasaWrapper suratKuasaWrapper
                = new SuratKuasaWrapper(
                        suratKuasa.getNomorUrusan(),
                        suratKuasa.getNomorUrut(),
                        suratKuasa.getNomorPasanganUrut(),
                        suratKuasa.getNomorUnit(),
                        suratKuasa.getNomorTahun(),
                        pemberiKuasa.getNip(),
                        pemberiKuasa.getNama(),
                        pemberiKuasa.getJabatan(),
                        pemberiKuasa.getUnitKerja(),
                        penerimaKuasa.getNip(),
                        penerimaKuasa.getNama(),
                        penerimaKuasa.getJabatan(),
                        penerimaKuasa.getUnitKerja(),
                        suratKuasa.getIsiKuasa(),
                        suratKuasa.getKotaPembuatanSurat(),
                        suratKuasa.getTanggalPembuatanMilis());

        return new ResponseEntity<Object>(suratKuasaWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/approve-surat-kuasa/{kdSuratKuasa}", method = RequestMethod.PUT)
    ResponseEntity<?> approveSuratKuasa(@PathVariable("kdSuratKuasa") String kdSuratKuasa) {
        LOGGER.info("approve surat kuasa");

        return new ResponseEntity<Object>(new CustomMessage("surat kuasa approved"), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/get-surat-kuasa-by-penerima-kuasa/{nipPenerimaKuasa}",
            method = RequestMethod.GET)
    ResponseEntity<?> getSuratKuasaByPenerimaKuasa(
            @PathVariable("nipPenerimaKuasa") String nipPenerimaKuasa) {
        LOGGER.info("get surat kuasa by penerima kuasa");

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();


        List<SuratKuasa> suratKuasaList
                = suratKuasaService.getByNipPenerimaKuasa(nipPenerimaKuasa);

        List<SuratKuasaPenerimaKuasaWrapper> suratKuasaPenerimaKuasaList
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (SuratKuasa suratKuasa
                : suratKuasaList) {
            for (CustomPegawaiCredential pegawaiPemberi : qutPegawaiList) {
                if (pegawaiPemberi.getNip()
                        .equals(suratKuasa.getNipPemberiKuasa())) {
                    suratKuasaPenerimaKuasaList
                            .add(new SuratKuasaPenerimaKuasaWrapper(
                                    suratKuasa.getKdSuratKuasa(),
                                    df.format(new Date(suratKuasa.getTanggalPembuatanMilis())),
                                    suratKuasa.getTanggalPembuatanMilis(),
                                    pegawaiPemberi.getNip(),
                                    pegawaiPemberi.getNama(),
                                    pegawaiPemberi.getJabatan()
                                    ));
                    break;
                }

            }

        }

        return new ResponseEntity<Object>(suratKuasaPenerimaKuasaList, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/get-surat-kuasa-by-penerima-kuasa-unread/{nipPenerimaKuasa}",
            method = RequestMethod.GET)
    ResponseEntity<?> getSuratKuasaByPenerimaKuasaUnread(
            @PathVariable("nipPenerimaKuasa") String nipPenerimaKuasa) {
        LOGGER.info("get surat kuasa by penerima kuasa");

        List<SuratKuasa> suratKuasaList
                = suratKuasaService.getByNipPenerimaKuasa(nipPenerimaKuasa);

        List<SuratKuasaHistoryWrapper> suratKuasaPenerimaKuasaList
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (SuratKuasa suratKuasa
                : suratKuasaList) {
            suratKuasaPenerimaKuasaList
                    .add(new SuratKuasaHistoryWrapper(
                            suratKuasa.getKdSuratKuasa(),
                            df.format(new Date(suratKuasa.getTanggalPembuatanMilis())),
                            suratKuasa.getStatusBaca()
                    ));
        }

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

}
