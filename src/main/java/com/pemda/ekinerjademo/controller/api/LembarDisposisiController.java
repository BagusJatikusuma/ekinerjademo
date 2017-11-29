package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratDisposisiDao;
import com.pemda.ekinerjademo.service.LembarDisposisiService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.SuratDisposisiService;
import com.pemda.ekinerjademo.util.DateUtilities;
import com.pemda.ekinerjademo.wrapper.input.LembarDisposisiInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.DokumenLembarDisposisiWrapper;
import com.pemda.ekinerjademo.wrapper.output.LembarDisposisiWrapper;
import com.pemda.ekinerjademo.wrapper.output.QutPegawaiWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by bagus on 19/11/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class LembarDisposisiController {
    public static final Logger LOGGER = LoggerFactory.getLogger(LembarDisposisiController.class);

    @Autowired private LembarDisposisiService lembarDisposisiService;
    @Autowired private SuratDisposisiService suratDisposisiService;
    @Autowired private QutPegawaiService qutPegawaiService;

    @RequestMapping(value = "/create-lembar-disposisi", method = RequestMethod.POST)
    ResponseEntity<?> createLembarDisposisi(
            @RequestBody LembarDisposisiInputWrapper inputWrapper) {
        LOGGER.info("create lembar disposisi");

        String kdLembarDisposisi = String.valueOf(new Date().getTime());

        SuratDisposisi suratDisposisi = new SuratDisposisi();
        suratDisposisi.setNoSurat(inputWrapper.getNoSuratDisposisi());
        suratDisposisi.setTanggalSuratMilis(inputWrapper.getTanggalSuratDisposisiMilis());
        suratDisposisi.setDari(inputWrapper.getDariSuratDisposisi());
        suratDisposisi.setRingkasanIsi(inputWrapper.getRingkasanIsiSuratDisposisi());
        suratDisposisi.setLampiran(inputWrapper.getLampiran());

        suratDisposisiService.create(suratDisposisi);

        LembarDisposisi lembarDisposisi = new LembarDisposisi();
        lembarDisposisi.setKdLembarDisposisi(kdLembarDisposisi);

        if (inputWrapper.getKdLembarDisposisiParent() == null) {
            lembarDisposisi.setPath(kdLembarDisposisi);
        } else {
            LembarDisposisi lembarDisposisiParent
                    = lembarDisposisiService.findByKdLembarDisposisi(inputWrapper.getKdLembarDisposisiParent());
            lembarDisposisi.setPath(lembarDisposisiParent.getPath()+"."+kdLembarDisposisi);
        }

        lembarDisposisi.setNipPembuat(inputWrapper.getNipPembuat());
        lembarDisposisi.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        lembarDisposisi.setTanggalPenerimaanMilis(inputWrapper.getTanggalPenerimaanMilis());
        lembarDisposisi.setTktKeamanan(inputWrapper.getTktKeamanan());
        lembarDisposisi.setTglPenyelesaianMilis(inputWrapper.getTglPenyelesaianMilis());
        lembarDisposisi.setNoSuratDisposisi(new SuratDisposisi(inputWrapper.getNoSuratDisposisi()));
        lembarDisposisi.setIsiDisposisi(inputWrapper.getIsiDisposisi());

        if (inputWrapper.getKdLembarDisposisiParent() == null) {
            lembarDisposisi.setKdLembarDisposisiParent(null);
        } else {
            lembarDisposisi.setKdLembarDisposisiParent(new LembarDisposisi(inputWrapper.getKdLembarDisposisiParent()));
        }
        lembarDisposisiService.create(lembarDisposisi);

        List<TargetLembarDisposisi> targetLembarDisposisiList = new ArrayList<>();
        for (String kdTarget : inputWrapper.getDaftarTargetLembarDisposisi()) {
            TargetLembarDisposisi targetLembarDisposisi = new TargetLembarDisposisi();
            targetLembarDisposisi.setTargetLembarDisposisiId(new TargetLembarDisposisiId(kdLembarDisposisi, kdTarget));
            targetLembarDisposisi.setApproveStatus(0);

            targetLembarDisposisiList.add(targetLembarDisposisi);
        }
        lembarDisposisiService.createTargetLembarDisposisi(targetLembarDisposisiList);

        return new ResponseEntity<Object>(new CustomMessage("lembar disposisi created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-lembar-disposisi-by-pembuat/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getLembarDisposisi(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get lembar disposisi by pembuat");

        List<LembarDisposisi> lembarDisposisiList
                = lembarDisposisiService.findByNipPegawai(nipPembuat);
        List<LembarDisposisiWrapper> lembarDisposisiWrappers
                = new ArrayList<>();

        Locale indoLocale = new Locale("id", "ID");
        for (LembarDisposisi lembarDisposisi
                : lembarDisposisiList) {
//            LOGGER.info(lembarDisposisi.getPath());
            lembarDisposisiWrappers
                    .add(new LembarDisposisiWrapper(
                            lembarDisposisi.getKdLembarDisposisi(),
                            lembarDisposisi.getPath(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTanggalPenerimaanMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTktKeamanan(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTglPenyelesaianMilis()), "dd MMMM yyyy", indoLocale)
                    ));
        }

        return new ResponseEntity<Object>(lembarDisposisiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-lembar-disposisi-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getLembarDisposisiTarget(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get lembar disposisi target");

        List<TargetLembarDisposisi> targetLembarDisposisiList
                = lembarDisposisiService.findByTargetDisposisi(nipTarget);

        List<LembarDisposisiWrapper> lembarDisposisiWrappers
                = new ArrayList<>();

        Locale indoLocale = new Locale("id", "ID");
        for (TargetLembarDisposisi targetLembarDisposisi
                : targetLembarDisposisiList) {
//            LOGGER.info(lembarDisposisi.getPath());
            lembarDisposisiWrappers
                    .add(new LembarDisposisiWrapper(
                            targetLembarDisposisi.getLembarDisposisi().getKdLembarDisposisi(),
                            targetLembarDisposisi.getLembarDisposisi().getPath(),
                            DateUtilities.createLocalDate(new Date(targetLembarDisposisi.getLembarDisposisi().getTanggalPenerimaanMilis()), "dd MMMM yyyy", indoLocale),
                            targetLembarDisposisi.getLembarDisposisi().getTktKeamanan(),
                            DateUtilities.createLocalDate(new Date(targetLembarDisposisi.getLembarDisposisi().getTglPenyelesaianMilis()), "dd MMMM yyyy", indoLocale)
                    ));
        }

        return new ResponseEntity<Object>(lembarDisposisiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-lembar-disposisi-tree/{kdLembarDisposisi}", method = RequestMethod.GET)
    ResponseEntity<?> getLembarDisposisiTree(
            @PathVariable("kdLembarDisposisi") String kdLembarDisposisi) {
        LOGGER.info("get lembar disposisi tree");

        List<LembarDisposisi> lembarDisposisiList
                = lembarDisposisiService.findTree(kdLembarDisposisi);
        List<LembarDisposisiWrapper> lembarDisposisiWrappers
                = new ArrayList<>();

        Locale indoLocale = new Locale("id", "ID");

        for (LembarDisposisi lembarDisposisi
                : lembarDisposisiList) {
//            LOGGER.info(lembarDisposisi.getPath());
            lembarDisposisiWrappers
                    .add(new LembarDisposisiWrapper(
                            lembarDisposisi.getKdLembarDisposisi(),
                            lembarDisposisi.getPath(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTanggalPenerimaanMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTktKeamanan(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTglPenyelesaianMilis()), "dd MMMM yyyy", indoLocale)
                            ));
        }

        return new ResponseEntity<Object>(lembarDisposisiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-dokumen-lembar-disposisi/{kdLembarDisposisi}", method = RequestMethod.GET)
    ResponseEntity<?> getDokumenLembarDisposisi(@PathVariable("kdLembarDisposisi") String kdLembarDisposisi) {
        LOGGER.info("get dokumen lembar disposisi ");

        LembarDisposisi lembarDisposisi
                = lembarDisposisiService.findByKdLembarDisposisi(kdLembarDisposisi);
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<QutPegawaiWrapper> targetPegawai = new ArrayList<>();

        for (TargetLembarDisposisi targetLembarDisposisi
                : lembarDisposisi.getTargetLembarDisposisiSet()) {
            for (CustomPegawaiCredential pegawai : qutPegawaiList) {
                if (pegawai.getNip()
                        .equals(targetLembarDisposisi.getTargetLembarDisposisiId().getNipPegawai())) {
                    targetPegawai
                            .add(new QutPegawaiWrapper(
                                    pegawai.getNip(),
                                    pegawai.getNama(), pegawai.getKdJabatan(),
                                    pegawai.getJabatan(),
                                    pegawai.getKdUnitKerja(),
                                    pegawai.getUnitKerja(),
                                    pegawai.getPangkat(),
                                    pegawai.getGol()));
                    break;
                }

            }

        }


        Locale indoLocale = new Locale("id", "ID");
        DokumenLembarDisposisiWrapper dokumenLembarDisposisiWrapper
                = new DokumenLembarDisposisiWrapper(
                        lembarDisposisi.getKdLembarDisposisi(),
                lembarDisposisi.getPath(),
                DateUtilities.createLocalDate(new Date(lembarDisposisi.getTanggalPenerimaanMilis()), "dd MMMM yyyy", indoLocale),
                lembarDisposisi.getTktKeamanan(),
                DateUtilities.createLocalDate(new Date(lembarDisposisi.getTglPenyelesaianMilis()), "dd MMMM yyyy", indoLocale),
                lembarDisposisi.getNoSuratDisposisi().getNoSurat(),
                lembarDisposisi.getIsiDisposisi(),
                DateUtilities.createLocalDate(new Date(lembarDisposisi.getNoSuratDisposisi().getTanggalSuratMilis()), "dd MMMM yyyy", indoLocale),
                lembarDisposisi.getNoSuratDisposisi().getDari(),
                lembarDisposisi.getNoSuratDisposisi().getRingkasanIsi(),
                lembarDisposisi.getNoSuratDisposisi().getLampiran(),
                targetPegawai
                );

        return new ResponseEntity<Object>(dokumenLembarDisposisiWrapper, HttpStatus.OK);
    }

}
