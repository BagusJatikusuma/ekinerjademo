package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.ekinerjarepository.SuratDisposisiDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.util.DateUtilities;
import com.pemda.ekinerjademo.util.FileUploader;
import com.pemda.ekinerjademo.wrapper.input.LembarDisposisiInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import groovy.transform.Synchronized;
import org.apache.commons.io.FilenameUtils;
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
                            lembarDisposisi.getTanggalPengirimanMilis()
                    ));
        }

        return new ResponseEntity<Object>(lembarDisposisiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-lembar-disposisi-target/{nipTarget}", method = RequestMethod.GET)
    ResponseEntity<?> getLembarDisposisiTarget(@PathVariable("nipTarget") String nipTarget) {
        LOGGER.info("get lembar disposisi target");

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
                        targetPegawai
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
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String filename = suratDisposisi.getPathFile();
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(fileSuratDisposisi, headers, HttpStatus.OK);
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
}
