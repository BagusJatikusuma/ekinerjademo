package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKuasa;
import com.pemda.ekinerjademo.model.ekinerjamodel.TelaahanStaf;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.SuratKuasaService;
import com.pemda.ekinerjademo.service.TelaahanStafService;
import com.pemda.ekinerjademo.wrapper.input.SuratKuasaInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.TelaahanStafInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.SuratPerintahHistoryWrapper;
import com.pemda.ekinerjademo.wrapper.output.TelaahanStaffHistoryWrapper;
import com.pemda.ekinerjademo.wrapper.output.TelaahanStaffWrapper;
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
 * Created by bayu on 15/12/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class TelaahanStafController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TelaahanStafController.class);

    @Autowired
    private TelaahanStafService telaahanStafService;
    @Autowired
    private QutPegawaiCloneService qutPegawaiService;
    @Autowired
    private TkdUnkDao tkdUnkDao;

    @RequestMapping(value = "/create-telaahan-staf", method = RequestMethod.POST)
    @Synchronized
    ResponseEntity<?> createTelaahanStaf(
            @RequestBody TelaahanStafInputWrapper inputWrapper) {
        LOGGER.info("create telaahan staf");

        String kdTelahaanStaf = String.valueOf(new Date().getTime());

        TelaahanStaf telaahanStaf = new TelaahanStaf();

        telaahanStaf.setKdTelaahanStaf(kdTelahaanStaf);
        telaahanStaf.setTentang(inputWrapper.getTentang());
        telaahanStaf.setPersoalan(inputWrapper.getPersoalan());
        telaahanStaf.setPraanggapan(inputWrapper.getPraanggapan());
        telaahanStaf.setFaktaYangMempengaruhi(inputWrapper.getFaktaYangMempengaruhi());
        telaahanStaf.setAnalisis(inputWrapper.getAnalisis());
        telaahanStaf.setSimpulan(inputWrapper.getSimpulan());
        telaahanStaf.setSaran(inputWrapper.getSaran());
        telaahanStaf.setNipPenandatangan(inputWrapper.getNipPenandatangan());

        telaahanStaf.setTanggalPembuatanMilis(new Date().getTime());
        telaahanStaf.setNipPembuatSurat(inputWrapper.getNipPembuatSurat());
        telaahanStaf.setKdUnitKerja(inputWrapper.getKdUnitKerja());

        telaahanStaf.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        telaahanStaf.setNipPenilai("");
        telaahanStaf.setStatusPenilaian(0);
        telaahanStaf.setAlasanPenolakan("");

        if (inputWrapper.getKdTelaahanStafBawahan() == null) {
            telaahanStaf.setPathPenilaian(kdTelahaanStaf);
        } else {
            TelaahanStaf telaahanStafBawahan
                    = telaahanStafService.getTelaahanStaf(inputWrapper.getKdTelaahanStafBawahan());
            telaahanStaf.setPathPenilaian(telaahanStafBawahan.getPathPenilaian()+"."+kdTelahaanStaf);

            telaahanStafBawahan.setStatusPenilaian(2);
            telaahanStafService.createTelaahanStaf(telaahanStafBawahan);
        }

        telaahanStafService.createTelaahanStaf(telaahanStaf);

        return new ResponseEntity<Object>(
                new CustomMessage("telaahan staf created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get-daftar-telaahan-staff-history-by-pegawai/{nipPembuatSurat}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarTelaahanStaffHistoryByPegawai(
            @PathVariable("nipPembuatSurat") String nipPembuatSurat) {
        LOGGER.info("get telaahan staff history by nip "+nipPembuatSurat);

        List<TelaahanStaf> telaahanStafList
                = telaahanStafService.getByNipPembuatSurat(nipPembuatSurat);
        List<SuratPerintahHistoryWrapper> telaahanStaffHistoryList
                = new ArrayList<>();

        for (TelaahanStaf telaahanStaf
                : telaahanStafList) {

            telaahanStaffHistoryList
                    .add(new SuratPerintahHistoryWrapper(telaahanStaf.getKdTelaahanStaf(),
                            "",
                            false,
                            telaahanStaf.getStatusBaca(),
                            "telaahan staff",
                            14,
                            telaahanStaf.getTanggalPembuatanMilis(),
                            telaahanStaf.getStatusPenilaian()));
        }

        return new ResponseEntity<Object>(telaahanStaffHistoryList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-telaahan-staff-by-kd-telaahan-staff/{kdTelaahanStaff}", method = RequestMethod.GET)
    ResponseEntity<?> getTelaahanStaffByKdTelaahanStaff(
            @PathVariable("kdTelaahanStaff") String kdTelaahanStaff) {
        LOGGER.info("get telaahan staff by kd telaahan staf");

        TelaahanStaf telaahanStaf = telaahanStafService.getTelaahanStaf(kdTelaahanStaff);

        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();

        CustomPegawaiCredential penandatangan = null,
                                pembuatSurat  = null;

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (telaahanStaf.getNipPenandatangan()
                    .equals(pegawai.getNip())) {
                pegawai.setUnitKerja(tkdUnkDao.findOne(pegawai.getKdUnitKerja()).getUnitKerja());

                penandatangan = pegawai;

                break;
            }
        }

        for (CustomPegawaiCredential pegawai : qutPegawaiList) {
            if (telaahanStaf.getNipPembuatSurat()
                    .equals(pegawai.getNip())) {
                pegawai.setUnitKerja(tkdUnkDao.findOne(pegawai.getKdUnitKerja()).getUnitKerja());

                pembuatSurat = pegawai;

                break;
            }
        }

        TelaahanStaffWrapper telaahanStaffWrapper
                = new TelaahanStaffWrapper(
                        telaahanStaf.getKdTelaahanStaf(),
                        telaahanStaf.getTentang(),
                        telaahanStaf.getPersoalan(),
                        telaahanStaf.getPraanggapan(),
                        telaahanStaf.getFaktaYangMempengaruhi(),
                        telaahanStaf.getAnalisis(),
                        telaahanStaf.getSimpulan(),
                        telaahanStaf.getSaran(),
                        penandatangan,
                        telaahanStaf.getTanggalPembuatanMilis(),
                        pembuatSurat,
                        tkdUnkDao.findOne(pembuatSurat.getKdUnitKerja()).getUnitKerja());

        return new ResponseEntity<Object>(telaahanStaffWrapper, HttpStatus.OK);
    }
}
