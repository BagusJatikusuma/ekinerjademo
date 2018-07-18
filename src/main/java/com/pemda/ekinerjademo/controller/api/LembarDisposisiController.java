package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.util.DateUtilities;
import com.pemda.ekinerjademo.util.FileUploader;
import com.pemda.ekinerjademo.wrapper.input.CompletedLembarDisposisiInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.DraftLembarDisposisiAdminSuratInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.LembarDisposisiInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.StatusApproveDraftlembarDisposisiInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import groovy.transform.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    @Autowired private QutPegawaiCloneService qutPegawaiService;

    @Autowired private TkdJabatanService tkdJabatanService;

    //service yang digunakan untuk mengambil surat disposisi
    @Autowired private BeritaAcaraService beritaAcaraService;
    @Autowired private SuratPerintahService suratPerintahService;
    @Autowired private LaporanService laporanService;
    @Autowired private MemorandumService memorandumService;
    @Autowired private NotaDinasService notaDinasService;
    @Autowired private PengumumanService pengumumanService;
    @Autowired private SuratDinasService suratDinasService;
    @Autowired private SuratEdaranService suratEdaranService;
    @Autowired private SuratKeputusanService suratKeputusanService;
    @Autowired private SuratKeteranganService suratKeteranganService;
    @Autowired private SuratKuasaService suratKuasaService;
    @Autowired private SuratPengantarService suratPengantarService;
    @Autowired private SuratTugasService suratTugasService;
    @Autowired private SuratUndanganService suratUndanganService;
    @Autowired private TelaahanStafService telaahanStafService;
    @Autowired private TemplateLainService templateLainService;
    @Autowired private LoginPegawaiService loginPegawaiService;
    @Autowired private SuratInstruksiService suratInstruksiService;

    @Autowired private BeritaAcaraController beritaAcaraController;
    @Autowired private SuratPerintahController suratPerintahController;
    @Autowired private LaporanController laporanController;
    @Autowired private MemorandumController memorandumController;
    @Autowired private NotaDinasController notaDinasController;
    @Autowired private PengumumanController pengumumanController;
    @Autowired private SuratDinasController suratDinasController;
    @Autowired private SuratEdaranController suratEdaranController;
    @Autowired private SuratKeputusanController suratKeputusanController;
    @Autowired private SuratKeteranganController suratKeteranganController;
    @Autowired private SuratKuasaController suratKuasaController;
    @Autowired private SuratPengantarController suratPengantarController;
    @Autowired private SuratTugasController suratTugasController;
    @Autowired private SuratUndanganController suratUndanganController;
    @Autowired private TelaahanStafController telaahanStafController;
    @Autowired private SuratInstruksiController suratInstruksiController;

    /**
     *
     * service yang digunakan untuk membuat lembar disposisi
     *
     * @param inputWrapper
     * @return custom notifikasi
     */
    @RequestMapping(value = "/create-lembar-disposisi", method = RequestMethod.POST)
    @Synchronized
    ResponseEntity<?> createLembarDisposisi(
            @RequestBody LembarDisposisiInputWrapper inputWrapper) {
        LOGGER.info("create lembar disposisi");

        String kdLembarDisposisi = String.valueOf(new Date().getTime());

        if (inputWrapper.getKdLembarDisposisiParent() == null ||
                inputWrapper.getKdLembarDisposisiParent().equals("")) {

            SuratDisposisi suratDisposisi = new SuratDisposisi();
            suratDisposisi.setNoSurat(inputWrapper.getNoSuratDisposisi());
            suratDisposisi.setTanggalSuratMilis(inputWrapper.getTanggalSuratDisposisiMilis());
            suratDisposisi.setDari(inputWrapper.getDariSuratDisposisi());
            suratDisposisi.setRingkasanIsi(inputWrapper.getRingkasanIsiSuratDisposisi());
            suratDisposisi.setLampiran(inputWrapper.getLampiran());

            //jika surat disposisi merupakan file upload
            if (inputWrapper.getJenisSuratPenugasan() == null) {
                suratDisposisi.setPathFile(kdLembarDisposisi + "." + FilenameUtils.getExtension(inputWrapper.getNamaFileSuratLain()));
            }
            //jika surat disposisi merupakan data surat yang ada pada sistem
            else {
                suratDisposisi.setJenisSuratPenugasan(inputWrapper.getJenisSuratPenugasan());
                suratDisposisi.setKdSuratPenugasan(inputWrapper.getKdSuratPenugasan());
            }

            suratDisposisiService.create(suratDisposisi);
        }

        LembarDisposisi lembarDisposisi = new LembarDisposisi();
        lembarDisposisi.setKdLembarDisposisi(kdLembarDisposisi);

        if (inputWrapper.getKdLembarDisposisiParent() == null) {
            lembarDisposisi.setPath(kdLembarDisposisi);
        } else {
            LembarDisposisi lembarDisposisiParent
                    = lembarDisposisiService.findByKdLembarDisposisi(inputWrapper.getKdLembarDisposisiParent());
            //ubah status baca parent menjadi sudah dilanjutkan
            lembarDisposisiParent.setStatusBaca(2);
            //ubah status baca target menjadi sudah dilanjutkan
            for (TargetLembarDisposisi targetLembarDisposisi
                    : lembarDisposisiParent.getTargetLembarDisposisiSet()) {
                if (targetLembarDisposisi.getTargetLembarDisposisiId().getNipPegawai()
                        .equals(inputWrapper.getNipPembuat())) {
                    targetLembarDisposisi.setStatusBaca(2);
                    lembarDisposisiService.createTargetLembarDisposisi(targetLembarDisposisi);

                    break;
                }

            }

            lembarDisposisi.setPath(lembarDisposisiParent.getPath()+"."+kdLembarDisposisi);
        }

        lembarDisposisi.setNipPembuat(inputWrapper.getNipPembuat());
        lembarDisposisi.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        lembarDisposisi.setTanggalPenerimaanMilis(inputWrapper.getTanggalPenerimaanMilis());
        lembarDisposisi.setTktKeamanan(inputWrapper.getTktKeamanan());
        lembarDisposisi.setTglPenyelesaianMilis(inputWrapper.getTglPenyelesaianMilis());
        lembarDisposisi.setNoSuratDisposisi(new SuratDisposisi(inputWrapper.getNoSuratDisposisi()));
        lembarDisposisi.setIsiDisposisi(inputWrapper.getIsiDisposisi());
        lembarDisposisi.setStatusBaca(0);
        lembarDisposisi.setStatusAktif(1);
        lembarDisposisi.setTanggalPengirimanMilis(new Date().getTime());
        lembarDisposisi.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());

        if (inputWrapper.getKdLembarDisposisiParent() == null) {
            lembarDisposisi.setKdLembarDisposisiParent(null);
        } else {
            lembarDisposisi.setKdLembarDisposisiParent(new LembarDisposisi(inputWrapper.getKdLembarDisposisiParent()));
        }
        lembarDisposisiService.create(lembarDisposisi);

        List<TargetLembarDisposisi> targetLembarDisposisiList = new ArrayList<>();
        for (String kdTarget : inputWrapper.getDaftarTargetLembarDisposisi()) {
            TargetLembarDisposisi targetLembarDisposisi = new TargetLembarDisposisi();

            if (inputWrapper.isTargetJabatan()) {
                targetLembarDisposisi
                        .setTargetLembarDisposisiId(
                                new TargetLembarDisposisiId(
                                        kdLembarDisposisi,
                                        qutPegawaiService.getQutPegawaiByKdJabatan(kdTarget).get(0).getNip()));
                targetLembarDisposisi.setKdJabatan(kdTarget);
            }
            else {
                targetLembarDisposisi.setTargetLembarDisposisiId(new TargetLembarDisposisiId(kdLembarDisposisi, kdTarget));
            }
            targetLembarDisposisi.setApproveStatus(0);
            targetLembarDisposisi.setStatusBaca(0);

            targetLembarDisposisiList.add(targetLembarDisposisi);
        }
        lembarDisposisiService.createTargetLembarDisposisi(targetLembarDisposisiList);

        if (inputWrapper.getKdLembarDisposisiParent() == null || inputWrapper.getJenisSuratPenugasan() == null){
            return new ResponseEntity<Object>(new CustomMessage(kdLembarDisposisi), HttpStatus.OK);
        }

        return new ResponseEntity<Object>(new CustomMessage("lembar disposisi created"), HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk membuat lembar disposisi dengan file
     *
     * @param inputWrapper
     * @param fileSuratDisposisi
     * @return
     */
    @RequestMapping(value = "/create-lembar-disposisi-ekstensi", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    ResponseEntity<?> createLembarDisposisiEkstensi(
            @RequestPart("metadata") LembarDisposisiInputWrapper inputWrapper,
            @RequestPart("file") MultipartFile fileSuratDisposisi) {
        LOGGER.info("create lembar disposisi");

        FileUploader uploader = new FileUploader();

        String kdLembarDisposisi = String.valueOf(new Date().getTime());

        if (inputWrapper.getKdLembarDisposisiParent() == null ||
                inputWrapper.getKdLembarDisposisiParent().equals("")) {
            String namaFileSuratDisposisi = String.valueOf(new Date().getTime());

            SuratDisposisi suratDisposisi = new SuratDisposisi();
            suratDisposisi.setNoSurat(kdLembarDisposisi);
            suratDisposisi.setNoSuratDisposisi(inputWrapper.getNoSuratDisposisi());
            suratDisposisi.setTanggalSuratMilis(inputWrapper.getTanggalSuratDisposisiMilis());
            suratDisposisi.setDari(inputWrapper.getDariSuratDisposisi());
            suratDisposisi.setRingkasanIsi(inputWrapper.getRingkasanIsiSuratDisposisi());
            suratDisposisi.setLampiran(inputWrapper.getLampiran());
            suratDisposisi.setPathFile(namaFileSuratDisposisi+"."+fileSuratDisposisi.getOriginalFilename().split("\\.")[1]);

            suratDisposisiService.create(suratDisposisi);
            uploader.uploadSuratLembarDisposisi(fileSuratDisposisi, namaFileSuratDisposisi);
        }

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
//        lembarDisposisi.setNoSuratDisposisi(new SuratDisposisi(inputWrapper.getNoSuratDisposisi()));
        lembarDisposisi.setNoSuratDisposisi(new SuratDisposisi(kdLembarDisposisi));
        lembarDisposisi.setIsiDisposisi(inputWrapper.getIsiDisposisi());
        lembarDisposisi.setStatusBaca(0);
        lembarDisposisi.setStatusAktif(1);
        lembarDisposisi.setTanggalPengirimanMilis(new Date().getTime());
        lembarDisposisi.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());

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
            targetLembarDisposisi.setStatusBaca(0);

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
                            lembarDisposisi.getTanggalPenerimaanMilis(),
                            lembarDisposisi.getTktKeamanan(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTglPenyelesaianMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTglPenyelesaianMilis(),
                            lembarDisposisi.getStatusBaca(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTanggalPengirimanMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTanggalPengirimanMilis(),
                            null,
                            null,
                            null,
                            null,
                            null,
                            lembarDisposisi.getStatusAktif()
                    ));
        }

        return new ResponseEntity<Object>(lembarDisposisiWrappers, HttpStatus.OK);
    }

    /**
     *
     * digunakan oleh target untuk mendapatkan lembar disposisinya
     * khusus untuk sekdin akan mendapatkan lembar disposisi dari kadin walaupun sekdin bbukan targetnya
     *
     * @param nipTarget
     * @return
     */
    @RequestMapping(value = "/get-lembar-disposisi-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getLembarDisposisiTarget(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get lembar disposisi target");

//        List<TargetLembarDisposisi> targetLembarDisposisiList
//                = lembarDisposisiService.findByTargetDisposisi(nipTarget);
        QutPegawai pegawaiTarget
                = qutPegawaiService.getQutPegawai(nipTarget);

        List<TargetLembarDisposisi> targetLembarDisposisiList = new ArrayList<>();

        //khusus sekdin dan sekcam
        boolean isPegawaiTargetSekretaris = false;
        if (pegawaiTarget.getKdUnitKerja().substring(0,1)
                .equals("3")) {
            if (pegawaiTarget.getEselon().contains("III.a")) {
                LOGGER.info("sekdin get all lembar disposisi from kadin");
                List<String> targetAlreadyExistList = new ArrayList<>();
                List<TargetLembarDisposisi> targetLembarDisposisisTemproraryList
                        = new ArrayList<>();

                targetLembarDisposisisTemproraryList
                        = lembarDisposisiService.getByApprovalKadinForTarget(pegawaiTarget.getKdUnitKerja());

                boolean isExist;
                for (TargetLembarDisposisi target : targetLembarDisposisisTemproraryList) {
                    isExist = false;
                    for (String kdLembarDisposisi : targetAlreadyExistList) {
                        if (target.getLembarDisposisi().getKdLembarDisposisi()
                                .equals(kdLembarDisposisi)) {
                            isExist = true;
                            break;
                        }
                    }

                    if (!isExist) {
                        targetAlreadyExistList.add(target.getLembarDisposisi().getKdLembarDisposisi());

                        targetLembarDisposisiList.add(target);
                    }

                }

                LOGGER.info("lembar disposisi for sekdin is "+targetLembarDisposisiList.size());

                isPegawaiTargetSekretaris = true;
            }
            else {
                targetLembarDisposisiList
                        = lembarDisposisiService.findByTargetDisposisiRev(nipTarget);
            }
        }
        else if (pegawaiTarget.getKdUnitKerja().substring(0,1)
                    .equals("7")) {
            if (pegawaiTarget.getEselon().contains("III.b")) {
                LOGGER.info("sekcam get all lembar disposisi from camat");

                targetLembarDisposisiList
                        = lembarDisposisiService.getByApprovalKadinForTarget(pegawaiTarget.getKdUnitKerja());

                LOGGER.info("lembar disposisi for sekcam is "+targetLembarDisposisiList.size());

                isPegawaiTargetSekretaris = true;
            }
            else {
                targetLembarDisposisiList
                        = lembarDisposisiService.findByTargetDisposisiRev(nipTarget);
            }
        }

        List<LembarDisposisiWrapper> lembarDisposisiWrappers
                = new ArrayList<>();

        Locale indoLocale = new Locale("id", "ID");
        for (TargetLembarDisposisi targetLembarDisposisi
                : targetLembarDisposisiList) {
//            LOGGER.info(lembarDisposisi.getPath());
            QutPegawai pegawaiPengirim = null, pegawaiPenerima = null;
            List<String> pegawaiPenerimaNames = new ArrayList<>();

            if (targetLembarDisposisi.getLembarDisposisi().getNipKepala() != null) {

                pegawaiPengirim
                        = qutPegawaiService.getQutPegawai(targetLembarDisposisi.getLembarDisposisi().getNipKepala());
            }
            else {

                pegawaiPengirim
                        = qutPegawaiService.getQutPegawai(targetLembarDisposisi.getLembarDisposisi().getNipPembuat());
            }

            if (isPegawaiTargetSekretaris) {
                pegawaiPenerima
                        = qutPegawaiService.getQutPegawai(targetLembarDisposisi.getTargetLembarDisposisiId().getNipPegawai());

            }

            lembarDisposisiWrappers
                    .add(new LembarDisposisiWrapper(
                            targetLembarDisposisi.getLembarDisposisi().getKdLembarDisposisi(),
                            targetLembarDisposisi.getLembarDisposisi().getPath(),
                            DateUtilities.createLocalDate(new Date(targetLembarDisposisi.getLembarDisposisi().getTanggalPenerimaanMilis()), "dd MMMM yyyy", indoLocale),
                            targetLembarDisposisi.getLembarDisposisi().getTanggalPenerimaanMilis(),
                            targetLembarDisposisi.getLembarDisposisi().getTktKeamanan(),
                            DateUtilities.createLocalDate(new Date(targetLembarDisposisi.getLembarDisposisi().getTglPenyelesaianMilis()), "dd MMMM yyyy", indoLocale),
                            targetLembarDisposisi.getLembarDisposisi().getTglPenyelesaianMilis(),
                            targetLembarDisposisi.getStatusBaca(),
                            DateUtilities.createLocalDate(new Date(targetLembarDisposisi.getLembarDisposisi().getTanggalPengirimanMilis()), "dd MMMM yyyy", indoLocale),
                            targetLembarDisposisi.getLembarDisposisi().getTanggalPengirimanMilis(),
                            targetLembarDisposisi.getLembarDisposisi().getNipPembuat(),
                            pegawaiPengirim.getNama(),
                            targetLembarDisposisi.getLembarDisposisi().getNoSuratDisposisi().getRingkasanIsi(),
                            "Lembar Disposisi",
                            1,
                            null,
                            null,
                            (pegawaiPenerima != null) ? null : null
                    ));
        }

        return new ResponseEntity<Object>(lembarDisposisiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-lembar-disposisi-target-unread/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getLembarDisposisiTargetUnread(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get lembar disposisi target unread");

//        List<TargetLembarDisposisi> targetLembarDisposisiList
//                = lembarDisposisiService.findByTargetDisposisi(nipTarget);

        List<TargetLembarDisposisi> targetLembarDisposisiList
                = lembarDisposisiService.findByTargetDisposisiRev(nipTarget);

        List<LembarDisposisiWrapper> lembarDisposisiWrappers
                = new ArrayList<>();

        Locale indoLocale = new Locale("id", "ID");
        for (TargetLembarDisposisi targetLembarDisposisi
                : targetLembarDisposisiList) {
//            LOGGER.info(lembarDisposisi.getPath());
            QutPegawai pegawaiPengirim
                    = qutPegawaiService.getQutPegawai(targetLembarDisposisi.getLembarDisposisi().getNipPembuat());

            if (targetLembarDisposisi.getStatusBaca() == 0) {
                lembarDisposisiWrappers
                        .add(new LembarDisposisiWrapper(
                                targetLembarDisposisi.getLembarDisposisi().getKdLembarDisposisi(),
                                targetLembarDisposisi.getLembarDisposisi().getPath(),
                                DateUtilities.createLocalDate(new Date(targetLembarDisposisi.getLembarDisposisi().getTanggalPenerimaanMilis()), "dd MMMM yyyy", indoLocale),
                                targetLembarDisposisi.getLembarDisposisi().getTanggalPenerimaanMilis(),
                                targetLembarDisposisi.getLembarDisposisi().getTktKeamanan(),
                                DateUtilities.createLocalDate(new Date(targetLembarDisposisi.getLembarDisposisi().getTglPenyelesaianMilis()), "dd MMMM yyyy", indoLocale),
                                targetLembarDisposisi.getLembarDisposisi().getTglPenyelesaianMilis(),
                                targetLembarDisposisi.getStatusBaca(),
                                DateUtilities.createLocalDate(new Date(targetLembarDisposisi.getLembarDisposisi().getTanggalPengirimanMilis()), "dd MMMM yyyy", indoLocale),
                                targetLembarDisposisi.getLembarDisposisi().getTanggalPengirimanMilis(),
                                targetLembarDisposisi.getLembarDisposisi().getNipPembuat(),
                                pegawaiPengirim.getNama(),
                                targetLembarDisposisi.getLembarDisposisi().getNoSuratDisposisi().getRingkasanIsi(),
                                "Lembar Disposisi",
                                1
                        ));
            }
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
            QutPegawai pegawaiPengirim
                    = qutPegawaiService.getQutPegawai(lembarDisposisi.getNipPembuat());

            lembarDisposisiWrappers
                    .add(new LembarDisposisiWrapper(
                            lembarDisposisi.getKdLembarDisposisi(),
                            lembarDisposisi.getPath(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTanggalPenerimaanMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTanggalPenerimaanMilis(),
                            lembarDisposisi.getTktKeamanan(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTglPenyelesaianMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTglPenyelesaianMilis(),
                            lembarDisposisi.getStatusBaca(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTanggalPengirimanMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTanggalPengirimanMilis(),
                            lembarDisposisi.getNipPembuat(),
                            pegawaiPengirim.getNama(),
                            lembarDisposisi.getNoSuratDisposisi().getRingkasanIsi()
                            ));
        }

        return new ResponseEntity<Object>(lembarDisposisiWrappers, HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk mengambil daftar lembar disposisi atasannya
     *
     * @param kdLembarDisposisiLeave
     * @return daftar lembar disposisi atasan
     */
    @RequestMapping(
            value = "/get-lembar-disposisi-tree-by-leave/{kdLembarDisposisiLeave}",
            method = RequestMethod.GET)
    ResponseEntity<?> getLembarDisposisiTreeByLeave(
            @PathVariable("kdLembarDisposisiLeave") String kdLembarDisposisiLeave) {
        LOGGER.info("get lembar disposisi tree by leave");

        List<LembarDisposisi> lembarDisposisiTree
                = lembarDisposisiService.findTreeByLeave(kdLembarDisposisiLeave);
        List<LembarDisposisiWrapper> lembarDisposisiWrappers
                = new ArrayList<>();

        Locale indoLocale = new Locale("id", "ID");

        for (LembarDisposisi lembarDisposisi
                : lembarDisposisiTree) {
//            LOGGER.info(lembarDisposisi.getPath());
            QutPegawai pegawaiPengirim
                    = qutPegawaiService.getQutPegawai(lembarDisposisi.getNipPembuat());

            lembarDisposisiWrappers
                    .add(new LembarDisposisiWrapper(
                            lembarDisposisi.getKdLembarDisposisi(),
                            lembarDisposisi.getPath(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTanggalPenerimaanMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTanggalPenerimaanMilis(),
                            lembarDisposisi.getTktKeamanan(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTglPenyelesaianMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTglPenyelesaianMilis(),
                            lembarDisposisi.getStatusBaca(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTanggalPengirimanMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTanggalPengirimanMilis(),
                            lembarDisposisi.getNipPembuat(),
                            pegawaiPengirim.getNama(),
                            lembarDisposisi.getNoSuratDisposisi().getRingkasanIsi()
                    ));
        }

        return new ResponseEntity<>(lembarDisposisiWrappers, HttpStatus.OK);
    }


    @RequestMapping(value = "/get-dokumen-lembar-disposisi/{kdLembarDisposisi}", method = RequestMethod.GET)
    ResponseEntity<?> getDokumenLembarDisposisi(@PathVariable("kdLembarDisposisi") String kdLembarDisposisi) {
        LOGGER.info("get dokumen lembar disposisi ");

        LembarDisposisi lembarDisposisi
                = lembarDisposisiService.findByKdLembarDisposisi(kdLembarDisposisi);
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<TkdJabatan> jabatanList
                = tkdJabatanService.getJabatanByUnitKerja(lembarDisposisi.getKdUnitKerja());

        List<QutPegawaiWrapper> targetPegawai = new ArrayList<>();
        List<JabatanWrapper> targetPejabat = new ArrayList<>();

        QutPegawai pembuatLembarDisposisi
                = qutPegawaiService.getQutPegawai(lembarDisposisi.getNipPembuat());

        boolean isTargetPejabat = false;
        if (pembuatLembarDisposisi.getEselon().equals("IV.a") ||
                pembuatLembarDisposisi.getEselon().contains("IV")) {

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

        }
        else {
            isTargetPejabat = true;

            for (TargetLembarDisposisi targetLembarDisposisi
                    : lembarDisposisi.getTargetLembarDisposisiSet()) {
                for (TkdJabatan jabatan : jabatanList) {
                    if (jabatan.getKdJabatan()
                            .equals(targetLembarDisposisi.getKdJabatan())) {
                        targetPejabat
                            .add(new JabatanWrapper(
                                    jabatan.getKdJabatan(),
                                    jabatan.getJabatan(),
                                    jabatan.getEselon(),
                                    jabatan.getKdUnitKerja().getKdUnK(),
                                    jabatan.getKdUnitKerja().getUnitKerja()));
                    }

                }

            }

        }

        Locale indoLocale = new Locale("id", "ID");
        DokumenLembarDisposisiWrapper dokumenLembarDisposisiWrapper
                = new DokumenLembarDisposisiWrapper(
                        lembarDisposisi.getKdLembarDisposisi(),
                        lembarDisposisi.getPath(),
                        DateUtilities.createLocalDate(new Date(lembarDisposisi.getTanggalPenerimaanMilis()), "dd MMMM yyyy", indoLocale),
                        lembarDisposisi.getTanggalPenerimaanMilis(),
                        lembarDisposisi.getTktKeamanan(),
                        DateUtilities.createLocalDate(new Date(lembarDisposisi.getTglPenyelesaianMilis()), "dd MMMM yyyy", indoLocale),
                        lembarDisposisi.getTglPenyelesaianMilis(),
                        lembarDisposisi.getTanggalPengirimanMilis(),
                        lembarDisposisi.getNoSuratDisposisi().getNoSurat(),
                        lembarDisposisi.getIsiDisposisi(),
                        DateUtilities.createLocalDate(new Date(lembarDisposisi.getNoSuratDisposisi().getTanggalSuratMilis()), "dd MMMM yyyy", indoLocale),
                        lembarDisposisi.getNoSuratDisposisi().getTanggalSuratMilis(),
                        lembarDisposisi.getNoSuratDisposisi().getDari(),
                        lembarDisposisi.getNoSuratDisposisi().getRingkasanIsi(),
                        lembarDisposisi.getNoSuratDisposisi().getLampiran(),
                        targetPegawai,
                        targetPejabat,
                        isTargetPejabat,
                        null
                        );

        return new ResponseEntity<Object>(dokumenLembarDisposisiWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/open-lembar-disposisi/{kdLembarDisposisi}/{nipPegawai}", method = RequestMethod.PUT)
    ResponseEntity<?> openLembarDisposisi(
            @PathVariable("kdLembarDisposisi") String kdLembarDisposisi,
            @PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("open lembar disposisi");

        lembarDisposisiService.openLembarDisposisi(kdLembarDisposisi);
        lembarDisposisiService.openLembarDisposisiTarget(new TargetLembarDisposisiId(kdLembarDisposisi, nipPegawai));

        return new ResponseEntity<Object>(new CustomMessage("lembar disposisi opened"), HttpStatus.OK);
    }

    /**
     *
     * Service yang digunakan untuk melihat surat yang didisposisikan
     *
     * @param kdLembarDisposisi
     * @return
     */
    @RequestMapping(
            value = "/get-surat-disposisi/{kdLembarDisposisi}",
            method = RequestMethod.GET)
    ResponseEntity<?> getSuratDisposisi(@PathVariable("kdLembarDisposisi") String kdLembarDisposisi) {
        LOGGER.info("get surat disposisi");

        SuratDisposisi suratDisposisi =
                lembarDisposisiService.findByKdLembarDisposisi(kdLembarDisposisi).getNoSuratDisposisi();

        SuratDisposisiWrapper suratDisposisiWrapper = new SuratDisposisiWrapper();

        if (suratDisposisi.getPathFile() != null) {
            byte[] fileSuratDisposisi = getSuratDisposisiFile(suratDisposisi);

            HttpHeaders headers = new HttpHeaders();

//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentType(MediaType.parseMediaType("application/pdf"));

            String filename = suratDisposisi.getPathFile();

            headers.add("content-disposition", "inline;filename=" + filename);
//            headers.setContentDispositionFormData(filename, filename);

            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<byte[]>(fileSuratDisposisi, headers, HttpStatus.OK);
        }

        switch (suratDisposisi.getJenisSuratPenugasan()) {
            case 0 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(beritaAcaraController.getBeritaAcaraWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 1 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(laporanController.getLaporanWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 2 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(memorandumController.getMemorandumWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 3 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(notaDinasController.getNotaDinasWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 4 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                break;
            case 5 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(suratDinasController.getSuratDinasWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 6 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(suratEdaranController.getSuratEdaranWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 7 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                break;
            case 8 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(suratKeteranganController.getSuratKeteranganWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 9 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(suratKuasaController.getSuratKuasaWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 10 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(suratPengantarController.getSuratPengantarWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 11 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(suratPerintahController.getSuratPerintahWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 12 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(suratTugasController.getSuratTugasWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 13 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(suratUndanganController.getSuratUndanganWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 14 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                suratDisposisiWrapper.setSuratDisposisi(telaahanStafController.getTelaahanStaffWrapper(suratDisposisi.getKdSuratPenugasan()));

                break;
            case 15 :
                suratDisposisiWrapper.setJenisSurat(suratDisposisi.getJenisSuratPenugasan());
                break;
        }

        return new ResponseEntity<>(suratDisposisiWrapper, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/create-surat-lain-file",
            method = RequestMethod.POST)
    ResponseEntity<?> createSuratLainFile(@RequestParam("file") MultipartFile fileTemplateLain) {
        LOGGER.info("create surat lain");

        FileUploader uploader = new FileUploader();
        uploader.uploadFileSuratLainDisposisi(fileTemplateLain, FilenameUtils.removeExtension(fileTemplateLain.getOriginalFilename()));

        return new ResponseEntity<Object>(
                new CustomMessage("surat lain created"), HttpStatus.CREATED);
    }

    /**
     *
     * service yang digunakan untuk membatalkan lembar disposisi yang pernah dibuat
     *
     * @param kdLembarDisposisi
     * @return
     */
    @RequestMapping(value = "/cancel-lembar-disposisi/{kdLembarDisposisi}", method = RequestMethod.PUT)
    ResponseEntity<?> batalkanLembarDisposisi(@PathVariable("kdLembarDisposisi") String kdLembarDisposisi) {
        LOGGER.info("cancel lembar disposisi");

        List<LembarDisposisi> lembarDisposisiList
                = lembarDisposisiService.findTree(kdLembarDisposisi);
        //batalkan lembar disposisi beserta turunannya
        for (LembarDisposisi lembarDisposisi : lembarDisposisiList) {
            lembarDisposisi.setStatusAktif(0);

            lembarDisposisiService.create(lembarDisposisi);
        }

        return new ResponseEntity<>(new CustomMessage("lembar disposisi berhasil dibatalkan"), HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk mengaktifkan lembar disposisi yang pernah dibuat
     *
     * @param kdLembarDisposisi
     * @return
     */
    @RequestMapping(value = "/enable-lembar-disposisi/{kdLembarDisposisi}", method = RequestMethod.PUT)
    ResponseEntity<?> aktifkanLembarDisposisi(@PathVariable("kdLembarDisposisi") String kdLembarDisposisi) {
        LOGGER.info("cancel lembar disposisi");

        List<LembarDisposisi> lembarDisposisiList
                = lembarDisposisiService.findTree(kdLembarDisposisi);
        //aktifkan lembar disposisi beserta turunannya
        for (LembarDisposisi lembarDisposisi : lembarDisposisiList) {
            lembarDisposisi.setStatusAktif(1);

            lembarDisposisiService.create(lembarDisposisi);
        }

        return new ResponseEntity<>(new CustomMessage("lembar disposisi berhasil diaktifkan"), HttpStatus.OK);
    }

    /**
     *
     * Service yang digunakan oleh admin persuratan untuk mendapatkan  data
     * lembar disposisi yang telah ditandatangani oleh kadin atau sekdin
     *
     * @return daftar lembar disposisi
     */
    @RequestMapping(value = "/get-draft-lembar-disposisi-approval/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getDrafttLembarDisposisiApproval(
            @PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get draft lembar disposisi approval");

        List<LembarDisposisi> draftLembarDisposisiApprovalList
                = lembarDisposisiService.getDraftlembarDisposisiApproval(kdUnitKerja);

        List<LembarDisposisiWrapper> lembarDisposisiWrappers
                = new ArrayList<>();

        Locale indoLocale = new Locale("id", "ID");
        for (LembarDisposisi lembarDisposisi
                : draftLembarDisposisiApprovalList) {
//            LOGGER.info(lembarDisposisi.getPath());
            lembarDisposisiWrappers
                    .add(new LembarDisposisiWrapper(
                            lembarDisposisi.getKdLembarDisposisi(),
                            lembarDisposisi.getPath(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTanggalPenerimaanMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTanggalPenerimaanMilis(),
                            lembarDisposisi.getTktKeamanan(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTglPenyelesaianMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTglPenyelesaianMilis(),
                            lembarDisposisi.getStatusBaca(),
                            DateUtilities.createLocalDate(new Date(lembarDisposisi.getTanggalPengirimanMilis()), "dd MMMM yyyy", indoLocale),
                            lembarDisposisi.getTanggalPengirimanMilis(),
                            null,
                            null,
                            null,
                            null,
                            null,
                            lembarDisposisi.getStatusAktif(),
                            lembarDisposisi.getStatusPenyebaran()
                    ));
        }


        return new ResponseEntity<>(lembarDisposisiWrappers, HttpStatus.OK);
    }

    /**
     *
     * digunakan oleh kadin/camat untuk membuat lembar disposisi awal berdasarkan draft lembar disposisi
     *
     * @param inputWrapper
     * @return
     */
    @RequestMapping(value = "/complete-lembar-disposisi", method = RequestMethod.POST)
    ResponseEntity<?> completeLembarDisposisi(
            @RequestBody CompletedLembarDisposisiInputWrapper inputWrapper) {
        LOGGER.info("complete lembar disposisi");

        LembarDisposisi draftlembarDisposisi
                = lembarDisposisiService.findByKdLembarDisposisi(inputWrapper.getKdDraftLembarDisposisi());
        draftlembarDisposisi.setTktKeamanan(inputWrapper.getTktKeamanan());
        draftlembarDisposisi.setTglPenyelesaianMilis(inputWrapper.getTglPenyelesaianMilis());
        draftlembarDisposisi.setIsiDisposisi(inputWrapper.getIsiDisposisi());
        draftlembarDisposisi.setApprovalPenandatangan(1);
        //untuk sekarang diisi nilai satu
        draftlembarDisposisi.setNipKepala(inputWrapper.getNipPelengkap());

        lembarDisposisiService.create(draftlembarDisposisi);

        List<TargetLembarDisposisi> targetLembarDisposisiList = new ArrayList<>();
        for (String kdTarget : inputWrapper.getDaftarTargetLembarDisposisi()) {
            TargetLembarDisposisi targetLembarDisposisi = new TargetLembarDisposisi();

            targetLembarDisposisi
                    .setTargetLembarDisposisiId(
                            new TargetLembarDisposisiId(
                                    draftlembarDisposisi.getKdLembarDisposisi(),
                                    qutPegawaiService.getQutPegawaiByKdJabatan(kdTarget).get(0).getNip()));

            targetLembarDisposisi.setApproveStatus(0);
            targetLembarDisposisi.setStatusBaca(0);
            targetLembarDisposisi.setKdJabatan(kdTarget);

            targetLembarDisposisiList.add(targetLembarDisposisi);
        }
        lembarDisposisiService.createTargetLembarDisposisi(targetLembarDisposisiList);

        return new ResponseEntity<>(new CustomMessage("lembar disposisi berhasil dibuat"), HttpStatus.OK);
    }

    @RequestMapping(value = "/teruskan-disposisi", method = RequestMethod.POST)
    ResponseEntity<?> teruskanDisposisi(@RequestBody CompletedLembarDisposisiInputWrapper inputWrapper) {
        LOGGER.info("teruskan disposisi");

        String kdLembarDisposisi = String.valueOf(new Date().getTime());

        LembarDisposisi lembarDisposisi = new LembarDisposisi();
        lembarDisposisi.setKdLembarDisposisi(kdLembarDisposisi);

        LembarDisposisi lembarDisposisiParent
                = lembarDisposisiService.findByKdLembarDisposisi(inputWrapper.getKdLembarDisposisiParent());
        //ubah status baca parent menjadi sudah dilanjutkan
        lembarDisposisiParent.setStatusBaca(2);
        //ubah status baca target menjadi sudah dilanjutkan
        for (TargetLembarDisposisi targetLembarDisposisi
                : lembarDisposisiParent.getTargetLembarDisposisiSet()) {
            if (targetLembarDisposisi.getTargetLembarDisposisiId().getNipPegawai()
                    .equals(inputWrapper.getNipPenerus())) {
                targetLembarDisposisi.setStatusBaca(2);
                lembarDisposisiService.createTargetLembarDisposisi(targetLembarDisposisi);

                break;
            }

        }

        lembarDisposisi.setPath(lembarDisposisiParent.getPath()+"."+kdLembarDisposisi);

        lembarDisposisi.setNipPembuat(inputWrapper.getNipPenerus());
        lembarDisposisi.setKdUnitKerja(lembarDisposisiParent.getKdUnitKerja());
        lembarDisposisi.setTanggalPenerimaanMilis(lembarDisposisiParent.getTanggalPenerimaanMilis());
        lembarDisposisi.setTktKeamanan(inputWrapper.getTktKeamanan());
        lembarDisposisi.setTglPenyelesaianMilis(inputWrapper.getTglPenyelesaianMilis());
        lembarDisposisi.setNoSuratDisposisi(new SuratDisposisi(lembarDisposisiParent.getNoSuratDisposisi().getNoSurat()));
        lembarDisposisi.setIsiDisposisi(inputWrapper.getIsiDisposisi());
        lembarDisposisi.setStatusBaca(0);
        lembarDisposisi.setStatusAktif(1);
        lembarDisposisi.setTanggalPengirimanMilis(new Date().getTime());
        lembarDisposisi.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());

        lembarDisposisi.setKdLembarDisposisiParent(new LembarDisposisi(inputWrapper.getKdLembarDisposisiParent()));

        lembarDisposisiService.create(lembarDisposisi);

        List<TargetLembarDisposisi> targetLembarDisposisiList = new ArrayList<>();
        for (String kdTarget : inputWrapper.getDaftarTargetLembarDisposisi()) {
            TargetLembarDisposisi targetLembarDisposisi = new TargetLembarDisposisi();

            if (inputWrapper.isTargetJabatan()) {
                targetLembarDisposisi
                        .setTargetLembarDisposisiId(
                                new TargetLembarDisposisiId(
                                        kdLembarDisposisi,
                                        qutPegawaiService.getQutPegawaiByKdJabatan(kdTarget).get(0).getNip()));
                targetLembarDisposisi.setKdJabatan(kdTarget);
            }
            else {
                targetLembarDisposisi.setTargetLembarDisposisiId(new TargetLembarDisposisiId(kdLembarDisposisi, kdTarget));
            }
            targetLembarDisposisi.setApproveStatus(0);
            targetLembarDisposisi.setStatusBaca(0);

            targetLembarDisposisiList.add(targetLembarDisposisi);
        }
        
        lembarDisposisiService.createTargetLembarDisposisi(targetLembarDisposisiList);

        return new ResponseEntity<>(new CustomMessage("lembar disposisi berhasil diteruskan"), HttpStatus.OK);
    }

    /**
     *
     * Service yang digunakan oleh admin persuratan untuk membuat draft lembar disposisi pertama kali
     *
     * @return
     */
    @RequestMapping(value = "/create-draft-lembar-disposisi", method = RequestMethod.POST)
    ResponseEntity<?> createDraftLembarDisposisi(
            @RequestBody DraftLembarDisposisiAdminSuratInputWrapper inputWrapper) {
        LOGGER.info("create draft lembar disposisi");

        String kdLembarDisposisi = String.valueOf(new Date().getTime());

        SuratDisposisi suratDisposisi = new SuratDisposisi();
        suratDisposisi.setNoSurat(inputWrapper.getNoSuratDisposisi());
        suratDisposisi.setTanggalSuratMilis(inputWrapper.getTanggalSuratDisposisiMilis());
        suratDisposisi.setDari(inputWrapper.getDariSuratDisposisi());
        suratDisposisi.setRingkasanIsi(inputWrapper.getRingkasanIsiSuratDisposisi());
        suratDisposisi.setLampiran(inputWrapper.getLampiran());

        //jika surat disposisi merupakan file upload
        if (inputWrapper.getJenisSuratPenugasan() == null) {
            suratDisposisi.setPathFile(kdLembarDisposisi + "." + FilenameUtils.getExtension(inputWrapper.getNamaFileSuratLain()));
        }
        //jika surat disposisi merupakan data surat yang ada pada sistem
        else {
            suratDisposisi.setJenisSuratPenugasan(inputWrapper.getJenisSuratPenugasan());
            suratDisposisi.setKdSuratPenugasan(inputWrapper.getKdSuratPenugasan());
        }

        suratDisposisiService.create(suratDisposisi);

        LembarDisposisi lembarDisposisi = new LembarDisposisi();
        lembarDisposisi.setKdLembarDisposisi(kdLembarDisposisi);

        lembarDisposisi.setPath(kdLembarDisposisi);

        lembarDisposisi.setNipPembuat(inputWrapper.getNipPembuat());
        lembarDisposisi.setKdUnitKerja(inputWrapper.getKdUnitKerja());
        lembarDisposisi.setTanggalPenerimaanMilis(inputWrapper.getTanggalSuratDisposisiDiterimaMilis());
        lembarDisposisi.setNoSuratDisposisi(new SuratDisposisi(inputWrapper.getNoSuratDisposisi()));
        lembarDisposisi.setStatusBaca(0);
        lembarDisposisi.setStatusAktif(1);
        lembarDisposisi.setTanggalPengirimanMilis(new Date().getTime());
        lembarDisposisi.setDurasiPengerjaan(inputWrapper.getDurasiPengerjaan());
        lembarDisposisi.setLevelDraft(1);
        lembarDisposisi.setStatusApprovalSekretaris(0);
        //
        lembarDisposisi.setTglPenyelesaianMilis(new Long(0));
        lembarDisposisi.setTktKeamanan(0);

        lembarDisposisi.setKdLembarDisposisiParent(null);

        lembarDisposisiService.create(lembarDisposisi);

        if (inputWrapper.getJenisSuratPenugasan() == null){
            return new ResponseEntity<Object>(new CustomMessage(kdLembarDisposisi), HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomMessage("draft lembar disposisi berhasil dibuat"), HttpStatus.OK);
    }

    /**
     *
     * digunakan oleh kepala dinas untuk melihat history lembar disposisi yang ditandatanganinya
     *
     * @return
     */
    @RequestMapping(value = "/get-lembar-disposisi-signed-history/{nipPenandatangan}", method = RequestMethod.GET)
    ResponseEntity<?> getLembarDisposisiSignedHistory(@PathVariable("nipPenandatangan") String nipPenandatangan) {
        LOGGER.info("get lembar disposisi signed history");

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /**
     *
     * digunakan oleh admin persuratan untuk melihat draft lembar disposisiyang pernah dibuat
     *
     * @param nipPembuat
     * @return
     */
    @RequestMapping(value = "/get-draft-lembar-disposisi-history/{nipPembuat}", method = RequestMethod.GET)
    ResponseEntity<?> getDraftLembarDisposisiHistory(@PathVariable("nipPembuat") String nipPembuat) {
        LOGGER.info("get draft lembar disposisi history");

        List<LembarDisposisi> lembarDisposisiList
                = lembarDisposisiService.findByNipPegawai(nipPembuat);
        List<DraftlembarDisposisiWrapper> draftlembarDisposisiWrappers
                = new ArrayList<>();

        boolean approvedBySekdin, signedBykadin;

        for (LembarDisposisi lembarDisposisi
                : lembarDisposisiList) {
            approvedBySekdin = false; signedBykadin = false;

            if (lembarDisposisi.getStatusApprovalSekretaris() == 1) {
                approvedBySekdin = true;
            }

            if (lembarDisposisi.getApprovalPenandatangan() == 1) {
                signedBykadin = true;
            }

            draftlembarDisposisiWrappers
                    .add(new DraftlembarDisposisiWrapper(
                            lembarDisposisi.getKdLembarDisposisi(),
                            lembarDisposisi.getNoSuratDisposisi().getDari(),
                            lembarDisposisi.getTanggalPenerimaanMilis(),
                            approvedBySekdin,
                            signedBykadin));
        }

        return new ResponseEntity<>(draftlembarDisposisiWrappers, HttpStatus.OK);
    }

    /**
     *
     * Service yang digunakan oleh sekretaris untuk mengapprove draft lembar disposisi dari admin persuratan
     *
     * @return
     */
    @RequestMapping(value = "/approve-draft-lembar-disposisi", method = RequestMethod.PUT)
    ResponseEntity<?> approveDraftLembarDisposisi(
            @RequestBody StatusApproveDraftlembarDisposisiInputWrapper inputWrapper) {
        LOGGER.info("approve draft lembar disposisi");

        LembarDisposisi draftLembarDisposisi
                = lembarDisposisiService.findByKdLembarDisposisi(inputWrapper.getKdDraftLembarDisposisi());

        if (draftLembarDisposisi == null)
            return new ResponseEntity<>(new CustomMessage("draft not found"), HttpStatus.NOT_FOUND);

        if (inputWrapper.isApproved()) {
            LOGGER.info("approved");

            draftLembarDisposisi.setLevelDraft(2);
            draftLembarDisposisi.setStatusApprovalSekretaris(1);
        } else {
            LOGGER.info("not approved");

            draftLembarDisposisi.setStatusApprovalSekretaris(2);
        }

        lembarDisposisiService.create(draftLembarDisposisi);

        return new ResponseEntity<>(new CustomMessage("draft lembar disposisi checked"), HttpStatus.OK);
    }

    /**
     *
     * digunakan oleh kadin dan sekdin untuk menerima draft lembar disposisi
     *
     * eselon kepala dinas II.b, ciri dinas adalah kode unit kerja diawali 3
     * eselon sekretaris dinas III.a
     * eselon camat III.a, ciri kecamatan adalah kode unit kerja diawali 7
     * eselon sekretaris kecamatan III.b
     *
     * @param kdUnitKerja
     * @return
     */
    @RequestMapping(value = "/get-draft-lembar-disposisi/{kdUnitKerja}/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getDraftLembarDisposisi(
            @PathVariable("kdUnitKerja") String kdUnitKerja,
            @PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get draft lembar disposisi");

        List<DraftlembarDisposisiWrapper> draftlembarDisposisiWrappers
                = new ArrayList<>();

        QutPegawai pegawaiTarget
                = qutPegawaiService.getQutPegawai(nipTarget);

        if (kdUnitKerja.substring(0,1).equals("3")) {
            switch (pegawaiTarget.getEselon()) {
                case "II.b" :
                    LOGGER.info("Kadin come here");
                    draftlembarDisposisiWrappers = getDraftlembarDisposisi(kdUnitKerja, 2);

                    break;
                case "III.a" :
                    LOGGER.info("Sekdin come here");
                    draftlembarDisposisiWrappers = getDraftlembarDisposisi(kdUnitKerja, 1);

                    LOGGER.info("size is "+draftlembarDisposisiWrappers.size());
                    break;
            }

        }
        else if (kdUnitKerja.substring(0,1).equals("7")) {
            switch (pegawaiTarget.getEselon()) {
                case "III.a" :
                    LOGGER.info("camat come here");
                    draftlembarDisposisiWrappers = getDraftlembarDisposisi(kdUnitKerja, 2);

                    break;
                case "III.b" :
                    LOGGER.info("Sekcam come here");
                    draftlembarDisposisiWrappers = getDraftlembarDisposisi(kdUnitKerja, 1);

                    break;
            }

        }

        return new ResponseEntity<>(draftlembarDisposisiWrappers, HttpStatus.OK);
    }

    private byte[] getSuratDisposisiFile(SuratDisposisi suratDisposisi) {
        byte[] file = null;

        File filePath = new File("/home/pemkab/project/documents/surat_disposisi/"+suratDisposisi.getPathFile());

        try {
            file = Files.readAllBytes(filePath.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("failed retreive file. error : "+e.getMessage());
        }

        return file;
    }

    private List<DraftlembarDisposisiWrapper> getDraftlembarDisposisi(String kdUnitKerja, Integer draftLevel) {
        LOGGER.info("kd unit kerja : "+kdUnitKerja+" ; level draft : "+draftLevel);

        List<DraftlembarDisposisiWrapper> draftlembarDisposisiWrappers = new ArrayList<>();

        List<LembarDisposisi> draftLembarDisposisiList
                = lembarDisposisiService.getDraftLembarDisposisiByLevel(kdUnitKerja, draftLevel);

        LOGGER.info("size from database "+draftLembarDisposisiList.size());

        for (LembarDisposisi lembarDisposisi : draftLembarDisposisiList) {
            draftlembarDisposisiWrappers
                    .add(new DraftlembarDisposisiWrapper(
                            lembarDisposisi.getKdLembarDisposisi(),
                            lembarDisposisi.getNoSuratDisposisi().getDari(),
                            lembarDisposisi.getTanggalPenerimaanMilis()));
        }

        return draftlembarDisposisiWrappers;
    }
}
