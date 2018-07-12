package com.pemda.ekinerjademo.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.QutPegawaiDao;
import com.pemda.ekinerjademo.repository.bismarepository.TkdJabatanDao;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.AkunPegawaiDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.wrapper.input.*;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by bagus on 06/09/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class AkunPegawaiController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AkunPegawaiController.class);

    private TkdJabatanDao tkdJabatanDao;
    private QutPegawaiDao qutPegawaiDao;
    private AkunPegawaiDao akunPegawaiDao;
    @Autowired private TkdUnkDao tkdUnkDao;

    private AkunPegawaiService akunPegawaiService;
    private RoleService roleService;
    private TkdJabatanService tkdJabatanService;
    private QutPegawaiCloneService qutPegawaiService;
//    private QutPegawaiCloneService qutPegawaiCloneService;
    private StatusPenanggungJawabKegiatanService statusPenanggungJawabKegiatanService;
    private UrtugKegiatanPegawaiService urtugKegiatanPegawaiService;
    private PejabatPenilaiDinilaiService pejabatPenilaiDinilaiService;

    //service yang digunakan untuk mengambil laporan
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
    @Autowired private LembarDisposisiService lembarDisposisiService;
    @Autowired private SuratInstruksiService suratInstruksiService;

    @Autowired
    public AkunPegawaiController(
            TkdJabatanDao tkdJabatanDao,
            QutPegawaiDao qutPegawaiDao,
            AkunPegawaiDao akunPegawaiDao,
            AkunPegawaiService akunPegawaiService,
            RoleService roleService,
            TkdJabatanService tkdJabatanService,
            QutPegawaiCloneService qutPegawaiService,
            StatusPenanggungJawabKegiatanService statusPenanggungJawabKegiatanService,
            UrtugKegiatanPegawaiService urtugKegiatanPegawaiService,
            PejabatPenilaiDinilaiService pejabatPenilaiDinilaiService) {
        this.tkdJabatanDao = tkdJabatanDao;
        this.qutPegawaiDao = qutPegawaiDao;
        this.akunPegawaiDao = akunPegawaiDao;
        this.akunPegawaiService = akunPegawaiService;
        this.roleService = roleService;
        this.tkdJabatanService = tkdJabatanService;
        this.qutPegawaiService = qutPegawaiService;
        this.statusPenanggungJawabKegiatanService = statusPenanggungJawabKegiatanService;
        this.urtugKegiatanPegawaiService = urtugKegiatanPegawaiService;
        this.pejabatPenilaiDinilaiService = pejabatPenilaiDinilaiService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @Transactional("bismaTransactionManager")
    ResponseEntity<?> test() {
        //test
        TkdJabatan tkdJabatan = tkdJabatanDao.findByKdJabatan("1000000A");
        LOGGER.info("get "+tkdJabatan.getJabatan());

        return new ResponseEntity<Object>("cek log", HttpStatus.OK);
    }

    @RequestMapping(value = "/test-local-database", method = RequestMethod.GET)
    @Transactional("ekinerjaTransactionManager")
    ResponseEntity<?> testLocalDatabase() {
        //test
        List<AkunPegawai> akunPegawai = akunPegawaiDao.findAll();
        LOGGER.info("get "+akunPegawai.get(0).getNipPegawai()+" - "+akunPegawai.get(0).getPassword());

        return new ResponseEntity<Object>("cek log", HttpStatus.OK);
    }

    @RequestMapping(value = "/test-qutpegawai-database", method = RequestMethod.GET)
    @Transactional("ekinerjaTransactionManager")
    ResponseEntity<?> testQutPegawaiDatabase() {
        //test
        QutPegawai qutPegawai = qutPegawaiDao.findByNip("195405011982032007");
        LOGGER.info("get "+qutPegawai.getNama()+" - "+qutPegawai.getJabatan()+" - "+qutPegawai.getUnitKerja());

        return new ResponseEntity<Object>("cek log", HttpStatus.OK);
    }

    @RequestMapping(value = "/get-pegawai-roles/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getRoles(@PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get roles");
        PegawaiRoleWrapper pegawaiRoleWrapper = new PegawaiRoleWrapper();

        List<Role> roles = roleService.getRoles();
        AkunPegawai akunPegawai = akunPegawaiService.getAkunPegawai(nipPegawai);

        RoleWrapper currentRoleWrapper =
                new RoleWrapper(akunPegawai.getRole().getId(), akunPegawai.getRole().getRole());
        List<RoleWrapper> roleWrapperList =
                new ArrayList<>();

        for (Role role : roles) {
            roleWrapperList
                    .add(new RoleWrapper(role.getId(), role.getRole()));
        }

        pegawaiRoleWrapper.setCurrentRole(currentRoleWrapper);
        pegawaiRoleWrapper.setRoles(roleWrapperList);

        return new ResponseEntity<Object>(pegawaiRoleWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/set-role", method = RequestMethod.POST)
    @Transactional("ekinerjaTransactionManager")
    ResponseEntity<?> setAkunPegawaiRole(
            @RequestBody AkunPegawaiRoleInputWrapper akunPegawaiRoleInputWrapper) {
        LOGGER.info("set role "+akunPegawaiRoleInputWrapper.getRoleId()+" for "+akunPegawaiRoleInputWrapper.getNipPegawai());

        akunPegawaiService
                .setPegawaiRole(
                        akunPegawaiRoleInputWrapper.getRoleId(),
                        akunPegawaiRoleInputWrapper.getNipPegawai());

        return new ResponseEntity<Object>(new CustomMessage("success set role"), HttpStatus.OK);

    }

    @RequestMapping(value = "/get-jabatan-by-unit-kerja/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getJabatanByUnitKerja(@PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get jabatan in "+kdUnitKerja);

        List<JabatanWrapper> jabatanWrapperList = new ArrayList<>();
        List<TkdJabatan> tkdJabatanList = tkdJabatanService.getJabatanByUnitKerja(kdUnitKerja);

        for (TkdJabatan tkdJabatan : tkdJabatanList) {
            jabatanWrapperList
                    .add(new JabatanWrapper(
                            tkdJabatan.getKdJabatan(),
                            tkdJabatan.getJabatan(),
                            tkdJabatan.getEselon()));
        }

        return new ResponseEntity<Object>(jabatanWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-all-jabatan", method = RequestMethod.GET)
    @Transactional("bismaTransactionManager")
    ResponseEntity<?> getAllJabatan() {
        LOGGER.info("get all jabatan");

        List<JabatanWrapper> jabatanWrapperList = new ArrayList<>();
        List<TkdJabatan> tkdJabatanList = tkdJabatanService.getAll();

        for (TkdJabatan tkdJabatan : tkdJabatanList) {
            jabatanWrapperList
                    .add(new JabatanWrapper(
                            tkdJabatan.getKdJabatan(),
                            tkdJabatan.getJabatan(),
                            tkdJabatan.getEselon(),
                            tkdJabatan.getKdUnitKerja().getKdUnK(),
                            tkdJabatan.getKdUnitKerja().getUnitKerja()));
        }

        return new ResponseEntity<Object>(jabatanWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-pegawai", method = RequestMethod.GET)
    @Transactional
    ResponseEntity<?> getPegawai() {
        LOGGER.info("get akun pegawai with role");

        List<QutPegawaiWrapper> qutPegawaiWrappers
                = new ArrayList<>();
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<AkunPegawai> akunPegawaiList
                = akunPegawaiService.getAkunPegawaiList();
        List<TkdUnk> tkdUnkList
                = tkdUnkDao.findAll();

//        for (AkunPegawai akunPegawai : akunPegawaiList) {
//            for (QutPegawai qutPegawai : qutPegawaiList) {
//                if (akunPegawai.getNipPegawai()
//                        .equals(qutPegawai.getNip())) {
//                    AkunPegawaiWrapper akunPegawaiWrapper = new AkunPegawaiWrapper();
//
//                    akunPegawaiWrapper.setNipPegawai(akunPegawai.getNipPegawai());
//                    akunPegawaiWrapper.setNama(qutPegawai.getNama());
//                    akunPegawaiWrapper.setRole(akunPegawai.getRole().getRole());
//
//                    akunPegawaiWrappers.add(akunPegawaiWrapper);
//                }
//
//            }
//
//        }
        LOGGER.info("finish get pegawai from database kepegawaian");
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            for (AkunPegawai akunPegawai : akunPegawaiList) {
                if (qutPegawai.getNip()
                        .equals(akunPegawai.getNipPegawai())) {
                    qutPegawaiWrappers
                            .add(new QutPegawaiWrapper(
                                    qutPegawai.getNip(),
                                    qutPegawai.getNama(),
                                    qutPegawai.getKdJabatan(),
                                    qutPegawai.getJabatan(),
                                    qutPegawai.getKdUnitKerja(),
                                    null,
                                    qutPegawai.getPangkat(),
                                    qutPegawai.getGol(),
                                    akunPegawai.getRole().getRole(),
                                    qutPegawai.getGlrDpn(),
                                    qutPegawai.getGlrBlk()));
                    break;
                }

            }

        }

        //get correct unit kerja
        for (QutPegawaiWrapper qutPegawaiWrapper
                : qutPegawaiWrappers) {
            for (TkdUnk tkdUnk
                    : tkdUnkList) {
                if (tkdUnk.getKdUnK().equals(qutPegawaiWrapper.getKdUnitKerja())) {
                    qutPegawaiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
                    break;
                }
            }
        }

        LOGGER.info("finish");

        return new ResponseEntity<Object>(qutPegawaiWrappers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/get-pegawai-custom",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<?> getPegawaiCustom() {
        LOGGER.info("get akun pegawai with role");

        List<QutPegawaiWrapper> qutPegawaiWrappers
                = new ArrayList<>();
        List<CustomPegawaiCredential> qutPegawaiList
                = qutPegawaiService.getCustomPegawaiCredentials();
        List<AkunPegawai> akunPegawaiList
                = akunPegawaiService.getAkunPegawaiList();
        List<TkdUnk> tkdUnkList
                = tkdUnkDao.findAll();

        LOGGER.info("finish get pegawai from database kepegawaian");
        for (CustomPegawaiCredential qutPegawai : qutPegawaiList) {
            for (AkunPegawai akunPegawai : akunPegawaiList) {
                if (qutPegawai.getNip()
                        .equals(akunPegawai.getNipPegawai())) {
                    qutPegawaiWrappers
                            .add(new QutPegawaiWrapper(
                                    qutPegawai.getNip(),
                                    qutPegawai.getNama(),
                                    qutPegawai.getKdJabatan(),
                                    qutPegawai.getJabatan(),
                                    qutPegawai.getKdUnitKerja(),
                                    qutPegawai.getUnitKerja(),
                                    qutPegawai.getPangkat(),
                                    qutPegawai.getGol(),
                                    akunPegawai.getRole().getRole()));
                    break;
                }

            }

        }

        //get correct unit kerja
        for (QutPegawaiWrapper qutPegawaiWrapper
                : qutPegawaiWrappers) {
            for (TkdUnk tkdUnk
                    : tkdUnkList) {
                if (tkdUnk.getKdUnK().equals(qutPegawaiWrapper.getKdUnitKerja())) {
                    qutPegawaiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
                    break;
                }
            }
        }

        LOGGER.info("finish");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new AfterburnerModule());
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String json = "";
        try {
            json = ow.writeValueAsString(qutPegawaiWrappers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(json, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-pegawai/{kdUnitKerja}", method = RequestMethod.GET)
    @Transactional
    ResponseEntity<?> getPegawaiByUnitKerja(@PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get pegawai in "+kdUnitKerja);

        List<QutPegawaiWrapper> qutPegawaiWrappers
                = new ArrayList<>();
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(kdUnitKerja);
        List<TkdUnk> tkdUnkList
                = tkdUnkDao.findAll();

        LOGGER.info("finish get pegawai from database kepegawaian");

        for (QutPegawai qutPegawai : qutPegawaiList) {
            qutPegawaiWrappers
                    .add(new QutPegawaiWrapper(
                            qutPegawai.getNip(),
                            qutPegawai.getNama(),
                            qutPegawai.getKdJabatan(),
                            qutPegawai.getJabatan(),
                            qutPegawai.getKdUnitKerja(),
                            qutPegawai.getUnitKerja(),
                            qutPegawai.getPangkat(),
                            qutPegawai.getGol()));
        }

        //get correct unit kerja
        for (QutPegawaiWrapper qutPegawaiWrapper
                : qutPegawaiWrappers) {
            for (TkdUnk tkdUnk
                    : tkdUnkList) {
                if (tkdUnk.getKdUnK().equals(qutPegawaiWrapper.getKdUnitKerja())) {
                    qutPegawaiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
                    break;
                }
            }
        }

        LOGGER.info("finish");

        return new ResponseEntity<Object>(qutPegawaiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-pegawai-by-jabatan/{kdJabatan}", method = RequestMethod.GET)
    ResponseEntity<?> getPegawaiByJabatan(@PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get pegawai by jabatan");

        List<QutPegawaiWrapper> qutPegawaiWrappers
                = new ArrayList<>();
        List<QutPegawaiClone> qutPegawaiCloneList
                = qutPegawaiService.getQutPegawaiByKdJabatan(kdJabatan);
        List<TkdUnk> tkdUnkList
                = tkdUnkDao.findAll();

        for (QutPegawaiClone pegawai : qutPegawaiCloneList) {
            qutPegawaiWrappers
                    .add(new QutPegawaiWrapper(
                            pegawai.getNip(),
                            pegawai.getNama(),
                            pegawai.getKdJabatan(),
                            pegawai.getJabatan(),
                            pegawai.getKdUnitKerja(),
                            pegawai.getUnitKerja(),
                            pegawai.getPangkat(),
                            pegawai.getGol()));
        }

        //get correct unit kerja
        for (QutPegawaiWrapper qutPegawaiWrapper
                : qutPegawaiWrappers) {
            for (TkdUnk tkdUnk
                    : tkdUnkList) {
                if (tkdUnk.getKdUnK().equals(qutPegawaiWrapper.getKdUnitKerja())) {
                    qutPegawaiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
                    break;
                }
            }
        }

        return new ResponseEntity<Object>(qutPegawaiWrappers, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-pegawai-dinilai/{nipPenilai}/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getPegawaiDinilai(
            @PathVariable("nipPenilai") String nipPenilai,
            @PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get pegawai dinilai");

        List<QutPegawaiWrapper> qutPegawaiWrappers
                = new ArrayList<>();
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(kdUnitKerja);
        List<PejabatPenilaiDinilai> pejabatPenilaiDinilaiList
                = pejabatPenilaiDinilaiService.findPegawaiDinilai(nipPenilai);
        List<TkdUnk> tkdUnkList
                = tkdUnkDao.findAll();

//        for (QutPegawai qutPegawai
//                : qutPegawaiList) {
//            for (PejabatPenilaiDinilai pejabatPenilaiDinilai :
//                    pejabatPenilaiDinilaiList) {
//                if (pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getKdJabatanDinilai()
//                        .equals(qutPegawai.getKdJabatan())) {
//                    qutPegawaiWrappers
//                            .add(new QutPegawaiWrapper(
//                                    qutPegawai.getNip(),
//                                    qutPegawai.getNama(),
//                                    qutPegawai.getKdJabatan(),
//                                    qutPegawai.getJabatan(),
//                                    qutPegawai.getKdUnitKerja(),
//                                    qutPegawai.getUnitKerja(),
//                                    qutPegawai.getPangkat(),
//                                    qutPegawai.getGol()));
//                    break;
//                }
//            }
//        }

        for (PejabatPenilaiDinilai pejabatPenilaiDinilai :
                pejabatPenilaiDinilaiList) {
            LOGGER.info(pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getKdJabatanDinilai());

            for (QutPegawai qutPegawai
                    : qutPegawaiList) {
                if (pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getKdJabatanDinilai()
                        .equals(qutPegawai.getKdJabatan())) {
                    qutPegawaiWrappers
                            .add(new QutPegawaiWrapper(
                                    qutPegawai.getNip(),
                                    qutPegawai.getNama(),
                                    qutPegawai.getKdJabatan(),
                                    qutPegawai.getJabatan(),
                                    qutPegawai.getKdUnitKerja(),
                                    qutPegawai.getUnitKerja(),
                                    qutPegawai.getPangkat(),
                                    qutPegawai.getGol()));
                }
            }
        }

        for (QutPegawaiWrapper qutPegawaiWrapper
                : qutPegawaiWrappers) {
            for (TkdUnk tkdUnk
                    : tkdUnkList) {
                if (tkdUnk.getKdUnK().equals(qutPegawaiWrapper.getKdUnitKerja())) {
                    qutPegawaiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
                    break;
                }
            }
        }

        return new ResponseEntity<Object>(qutPegawaiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-pegawai-penanggung-jawab", method = RequestMethod.POST)
    ResponseEntity<?> getPegawaiPenanggunhJawab(@RequestBody UrtugKegiatanPenanggungJawabWrapper inputWrapper) {
        LOGGER.info("get pegawai penanggung jawab");

        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList
                = urtugKegiatanPegawaiService.findByUrtugKegiatan(
                        new UrtugKegiatanId(
                                inputWrapper.getKdUrtug(),
                                inputWrapper.getKdJabatan(),
                                inputWrapper.getKdJenisUrtug(),
                                inputWrapper.getTahunUrtug(),
                                inputWrapper.getKdUrusan(),
                                inputWrapper.getKdBidang(),
                                inputWrapper.getKdUnit(),
                                inputWrapper.getKdSub(),
                                inputWrapper.getTahun(),
                                inputWrapper.getKdProg(),
                                inputWrapper.getIdProg(),
                                inputWrapper.getKdKeg()));

        List<QutPegawaiWrapper> qutPegawaiWrappers = new ArrayList<>();
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(inputWrapper.getKdUnitKerja());
        List<QutPegawaiClone> qutPegawaiCloneList
                = qutPegawaiService.getQutPegawaiByKdJabatan(inputWrapper.getKdJabatan());
        List<TkdUnk> tkdUnkList
                = tkdUnkDao.findAll();

        boolean found;
        for (QutPegawaiClone qutPegawai : qutPegawaiCloneList) {
            found = false;
            for (UrtugKegiatanPegawai urtugKegiatanPegawai : urtugKegiatanPegawaiList) {
                if (urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getNipPegawai()
                        .equals(qutPegawai.getNip())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                qutPegawaiWrappers
                        .add(new QutPegawaiWrapper(
                                qutPegawai.getNip(),
                                qutPegawai.getNama(),
                                qutPegawai.getKdJabatan(),
                                qutPegawai.getJabatan(),
                                qutPegawai.getKdUnitKerja(),
                                qutPegawai.getUnitKerja(),
                                qutPegawai.getPangkat(),
                                qutPegawai.getGol()));
            }

        }

        for (QutPegawaiWrapper qutPegawaiWrapper
                : qutPegawaiWrappers) {
            for (TkdUnk tkdUnk
                    : tkdUnkList) {
                if (tkdUnk.getKdUnK().equals(qutPegawaiWrapper.getKdUnitKerja())) {
                    qutPegawaiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
                    break;
                }
            }
        }

        return new ResponseEntity<Object>(qutPegawaiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-pegawai-penanggung-jawab-program", method = RequestMethod.POST)
    ResponseEntity<?> getPegawaiPenanggungJawabProgram(@RequestBody UrtugKegiatanPenanggungJawabWrapper inputWrapper) {
        LOGGER.info("get pegawai penanggung jawab");

        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList
                = urtugKegiatanPegawaiService.findByProgramAndUrtugJabatan(
                        inputWrapper.getKdUrtug(),
                        inputWrapper.getKdJabatan(),
                        inputWrapper.getKdJenisUrtug(),
                        inputWrapper.getTahunUrtug(),
                        inputWrapper.getKdUrusan(),
                        inputWrapper.getKdBidang(),
                        inputWrapper.getKdUnit(),
                        inputWrapper.getKdSub(),
                        inputWrapper.getTahun(),
                        inputWrapper.getKdProg(),
                        inputWrapper.getIdProg());

        List<QutPegawaiWrapper> qutPegawaiWrappers
                = new ArrayList<>();
        List<QutPegawaiClone> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByKdJabatan(inputWrapper.getKdJabatan());
        List<TkdUnk> tkdUnkList
                = tkdUnkDao.findAll();

        boolean found;
        for (QutPegawaiClone qutPegawai : qutPegawaiList) {
            found = false;
            for (UrtugKegiatanPegawai urtugKegiatanPegawai : urtugKegiatanPegawaiList) {
                if (urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getNipPegawai()
                        .equals(qutPegawai.getNip())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                qutPegawaiWrappers
                        .add(new QutPegawaiWrapper(
                                qutPegawai.getNip(),
                                qutPegawai.getNama(),
                                qutPegawai.getKdJabatan(),
                                qutPegawai.getJabatan(),
                                qutPegawai.getKdUnitKerja(),
                                qutPegawai.getUnitKerja(),
                                qutPegawai.getPangkat(),
                                qutPegawai.getGol()));
            }

        }

        for (QutPegawaiWrapper qutPegawaiWrapper
                : qutPegawaiWrappers) {
            for (TkdUnk tkdUnk
                    : tkdUnkList) {
                if (tkdUnk.getKdUnK().equals(qutPegawaiWrapper.getKdUnitKerja())) {
                    qutPegawaiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
                    break;
                }
            }
        }

        return new ResponseEntity<Object>(qutPegawaiWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-status-penanggung-jawab", method = RequestMethod.GET)
    ResponseEntity<?> getStatusPenanggungJawab() {
        LOGGER.info("get all status penanggung jawab");

        List<StatusPenanggungJawabWrapper> statusPenanggungJawabWrapperList
                = new ArrayList<>();
        List<StatusPenanggungJawabKegiatan> statusPenanggungJawabKegiatanList
                = statusPenanggungJawabKegiatanService.getAll();

        for (StatusPenanggungJawabKegiatan spj : statusPenanggungJawabKegiatanList) {
            statusPenanggungJawabWrapperList
                    .add(new StatusPenanggungJawabWrapper(spj.getKdStatus(), spj.getStatus()));
        }

        return new ResponseEntity<Object>(statusPenanggungJawabWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-status-penanggung-jawab-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> getStatusPenanggungJawabKegiatan(@RequestBody UrtugKegiatanInputWrapper inputWrapper) {
        LOGGER.info("get all status penanggung jawab kegiatan");

        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList
                = urtugKegiatanPegawaiService.findByUrtugKegiatan(
                    new UrtugKegiatanId(
                            inputWrapper.getKdUrtug(),
                            inputWrapper.getKdJabatan(),
                            inputWrapper.getKdJenisUrtug(),
                            inputWrapper.getTahunUrtug(),
                            inputWrapper.getKdUrusan(),
                            inputWrapper.getKdBidang(),
                            inputWrapper.getKdUnit(),
                            inputWrapper.getKdSub(),
                            inputWrapper.getTahun(),
                            inputWrapper.getKdProg(),
                            inputWrapper.getIdProg(),
                            inputWrapper.getKdKeg()));

        List<StatusPenanggungJawabWrapper> statusPenanggungJawabWrapperList
                = new ArrayList<>();
        List<StatusPenanggungJawabKegiatan> statusPenanggungJawabKegiatanList
                = statusPenanggungJawabKegiatanService.getAll();

        boolean inputConstraint;

        for (StatusPenanggungJawabKegiatan spj : statusPenanggungJawabKegiatanList) {
            inputConstraint = false;
            if (spj.getKdStatus().equals("ST001") ||
                    spj.getKdStatus().equals("ST002")||
                    spj.getKdStatus().equals("ST004")||
                    spj.getKdStatus().equals("ST005") ||
                    spj.getKdStatus().equals("1513324189794") ||
                    spj.getKdStatus().equals("1513484156490") ||
                    spj.getKdStatus().equals("1513485761316") ||
                    spj.getKdStatus().equals("1523326110773")) {
                LOGGER.info("masuk "+spj.getKdStatus());

                for (UrtugKegiatanPegawai urtugKegiatanPegawai : urtugKegiatanPegawaiList) {
                    if (urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdStatusPenanggungJawab()
                            .equals(spj.getKdStatus())) {
                        LOGGER.info("constraint "+urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdStatusPenanggungJawab());
                        inputConstraint = true;
                        break;
                    }

                }

            }

            if (!inputConstraint) {
                statusPenanggungJawabWrapperList
                        .add(new StatusPenanggungJawabWrapper(spj.getKdStatus(), spj.getStatus()));
            }

        }

        return new ResponseEntity<Object>(statusPenanggungJawabWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-status-penanggung-jawab-program", method = RequestMethod.POST)
    ResponseEntity<?> getStatusPenanggungJawabProgram(@RequestBody UrtugKegiatanInputWrapper inputWrapper) {
        LOGGER.info("get all status penanggung jawab program");

        List<UrtugKegiatanPegawai> urtugKegiatanPegawaiList
                = urtugKegiatanPegawaiService.findByProgramAndUrtugJabatan(
                        inputWrapper.getKdUrtug(),
                        inputWrapper.getKdJabatan(),
                        inputWrapper.getKdJenisUrtug(),
                        inputWrapper.getTahunUrtug(),
                        inputWrapper.getKdUrusan(),
                        inputWrapper.getKdBidang(),
                        inputWrapper.getKdUnit(),
                        inputWrapper.getKdSub(),
                        inputWrapper.getTahun(),
                        inputWrapper.getKdProg(),
                        inputWrapper.getIdProg());

        List<StatusPenanggungJawabWrapper> statusPenanggungJawabWrapperList
                = new ArrayList<>();
        List<StatusPenanggungJawabKegiatan> statusPenanggungJawabKegiatanList
                = statusPenanggungJawabKegiatanService.getAll();

        boolean inputConstraint;

        for (StatusPenanggungJawabKegiatan spj : statusPenanggungJawabKegiatanList) {
            inputConstraint = false;
            if (spj.getKdStatus().equals("ST001") ||
                    spj.getKdStatus().equals("ST002")||
                    spj.getKdStatus().equals("ST004")||
                    spj.getKdStatus().equals("ST005") ||
                    spj.getKdStatus().equals("1513324189794")) {

                for (UrtugKegiatanPegawai urtugKegiatanPegawai : urtugKegiatanPegawaiList) {
                    if (urtugKegiatanPegawai.getUrtugKegiatanPegawaiId().getKdStatusPenanggungJawab()
                            .equals(spj.getKdStatus())) {
                        inputConstraint = true;
                        break;
                    }

                }

            }

            if (!inputConstraint) {
                statusPenanggungJawabWrapperList
                        .add(new StatusPenanggungJawabWrapper(spj.getKdStatus(), spj.getStatus()));
            }

        }

        return new ResponseEntity<Object>(statusPenanggungJawabWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-penilai-urtug-by-jabatan/{kdJabatan}")
    ResponseEntity<?> getPenilaiUrtug(@PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get penilai urtug by jabatan");

        List<PejabatPenilaiDinilai> pejabatPenilaiDinilaiList
                = pejabatPenilaiDinilaiService.findByKdJabatanDinilai(kdJabatan);
        QutPegawai qutPegawai
                = qutPegawaiService.getQutPegawai(
                        pejabatPenilaiDinilaiList.get(0).getPejabatPenilaiDinilaiId().getNipPenilai());

        TkdUnk tkdUnk = tkdUnkDao.findOne(qutPegawai.getKdUnitKerja());

        QutPegawaiWrapper pegawaiWrapper
                = new QutPegawaiWrapper(
                        qutPegawai.getNip(),
                        qutPegawai.getNama(),
                        qutPegawai.getKdJabatan(),
                        qutPegawai.getJabatan(),
                        qutPegawai.getKdUnitKerja(),
                        tkdUnk.getUnitKerja(),
                        qutPegawai.getPangkat(),
                        qutPegawai.getGol());



        return new ResponseEntity<Object>(pegawaiWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/set-pegawai-penilai", method = RequestMethod.POST)
    ResponseEntity<?> setPegawaiPenilai(@RequestBody PegawaiPenilaiInputWrapper pegawaiPenilaiInputWrapper) {
        LOGGER.info("set pegawai penilai");

        PejabatPenilaiDinilai pejabatPenilaiDinilai = new PejabatPenilaiDinilai();
        pejabatPenilaiDinilai.setPejabatPenilaiDinilaiId(new PejabatPenilaiDinilaiId(
                pegawaiPenilaiInputWrapper.getNipPenilai(),
                pegawaiPenilaiInputWrapper.getKdJabatan()));
        pejabatPenilaiDinilaiService.create(pejabatPenilaiDinilai);


        return new ResponseEntity<Object>(pegawaiPenilaiInputWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/change-password-pegawai", method = RequestMethod.PUT)
    ResponseEntity<?> changePassword(
            @RequestBody AkunPasswordInputWrapper pegawaiPasswordWrapper) {
        LOGGER.info("change password pegawai");

        AkunPegawai akunPegawai
                = akunPegawaiService.getAkunPegawai(pegawaiPasswordWrapper.getNipPegawai());

        akunPegawai.setPassword(pegawaiPasswordWrapper.getNewPassword());

        akunPegawaiService.save(akunPegawai);

        return new ResponseEntity<Object>(new CustomMessage("password changed"), HttpStatus.OK);

    }

    @RequestMapping(value = "/get-laporan-bawahan/{nipPenilai}", method = RequestMethod.GET)
    ResponseEntity<?> getLaporanBawahan(@PathVariable("nipPenilai") String nipPenilai) {
        LOGGER.info("get laporan bawahan");

        List<LaporanBawahanWrapper> laporanBawahanWrapperList
                = new ArrayList<>();

        List<QutPegawaiClone> pegawaiBawahanList = new ArrayList<>();
        List<PejabatPenilaiDinilai> kdJabatanPegawaiBawahanList
                = pejabatPenilaiDinilaiService.findPegawaiDinilai(nipPenilai);

        QutPegawai penilai = qutPegawaiService.getQutPegawai(nipPenilai);

        //cek apakah kadin/camat atau sekdin/sekcam
        boolean isSekretaris = false, isKepala = false;
        if (penilai.getKdUnitKerja().substring(0,1)
                .equals("3")) {
            //eselon kadin II.b, eselon sekdin III.a
            if (penilai.getEselon().equals("II.b")) {
                isKepala = true;
            }
            else if (penilai.getEselon().equals("III.a")) {
                isSekretaris = true;
            }
        }
        else if (penilai.getKdUnitKerja().substring(0,1)
                    .equals("7")) {
            //eselon camat III.a, eselon sekdcam III.b
            if (penilai.getEselon().equals("III.a")) {
                isKepala = true;
            }
            else if (penilai.getEselon().equals("III.b")) {
                isSekretaris = true;
            }
        }

        if (isSekretaris) {
            List<QutPegawai> pegawaiUnitKerja
                    = qutPegawaiService.getQutPegawaiByUnitKerja(penilai.getKdUnitKerja());

            for (QutPegawai pegawai : pegawaiUnitKerja) {
                if (penilai.getKdUnitKerja().substring(0,1)
                        .equals("3")) {
                    if (pegawai.getEselon().equals("III.b")) {
                        pegawaiBawahanList.add(qutPegawaiService.convertQutPegawaiIntoQutPegawaiClone(pegawai));
                    }
                }
                else if (penilai.getKdUnitKerja().substring(0,1)
                        .equals("7")) {
                    if (pegawai.getEselon().equals("IV.a")) {
                        pegawaiBawahanList.add(qutPegawaiService.convertQutPegawaiIntoQutPegawaiClone(pegawai));
                    }
                }

            }
        }

        //ambil data pegawai bawahan terlebih dahulu
        //untuk kepala dinas atau camat tidak perlu
        if (isKepala) {
            pegawaiBawahanList.add(qutPegawaiService.convertQutPegawaiIntoQutPegawaiClone(penilai));
        }
        else {
            for (PejabatPenilaiDinilai jabatan : kdJabatanPegawaiBawahanList) {
                List<QutPegawaiClone> pegawaiBawahanJabatanList
                        = qutPegawaiService.getQutPegawaiByKdJabatan(jabatan.getPejabatPenilaiDinilaiId().getKdJabatanDinilai());
                for (QutPegawaiClone pegawaiBawahan : pegawaiBawahanJabatanList) {
                    pegawaiBawahanList.add(pegawaiBawahan);
                }
            }
        }

        if (isKepala) {
            LOGGER.info("penilai is kepala dinas/camat");
        }
        else if (isSekretaris) {
            LOGGER.info("penilai is sekretaris");
        }
        else {
            LOGGER.info("penilai is not kepala dinas/camat or sekretaris");
        }

        //ambil laporan dari seluruh history template untuk setiap pegawai bawahan
        Integer suratPejabat;
        boolean isPenandatangan = false;
        int statusPenilaian = 0;
        boolean dariKabid;

        QutPegawai pembuatLaporan = new QutPegawai();

        for (QutPegawaiClone pegawaiBawahan : pegawaiBawahanList) {
            //ambil data berita acara yang dilaporkan bawahan
            List<BeritaAcara> beritaAcaraList = new ArrayList<>();

            dariKabid = akunPegawaiService.isPegawaiKepalaBidang(pegawaiBawahan);

            if (isKepala) {
                beritaAcaraList
                        = beritaAcaraService.getBeritaAcaraApprovalSekretaris(penilai.getKdUnitKerja());
            }
            else {
                beritaAcaraList
                        = beritaAcaraService.getByNipPembuatSurat(pegawaiBawahan.getNip());
            }

            for (BeritaAcara beritaAcaraBawahan : beritaAcaraList) {

                if (beritaAcaraBawahan.getNipMengetahui().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (beritaAcaraBawahan.getStatusApprovalNipMengetahui() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (beritaAcaraBawahan.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = beritaAcaraBawahan.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(beritaAcaraBawahan.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                beritaAcaraBawahan.getKdBeritaAcara(),
                                "Berita Acara",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                0,
                                0,
                                beritaAcaraBawahan.getTanggalPembuatanMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }

            List<Memorandum> memorandums = new ArrayList<>();
            if (isKepala) {
                memorandums
                        = memorandumService.getMemorandumSekretarisApproval(penilai.getKdUnitKerja());
            }
            else {
                memorandums
                        = memorandumService.getByNipPembuat(pegawaiBawahan.getNip());
            }

            for (Memorandum memorandum : memorandums) {
                if (memorandum.getMemorandumPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                if (memorandum.getNipPenandatangan().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (memorandum.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (memorandum.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = memorandum.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(memorandum.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                memorandum.getKdMemorandum(),
                                "Memorandum",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                2,
                                suratPejabat,
                                memorandum.getTanggalPembuatanMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }

            List<NotaDinas> notaDinasList = new ArrayList<>();
            if (isKepala) {
                notaDinasList
                        = notaDinasService.getNotaDinasSekretarisApproval(penilai.getKdUnitKerja());
            }
            else {
                notaDinasList
                        = notaDinasService.getByNipPembuat(pegawaiBawahan.getNip());
            }
            for (NotaDinas notaDinas : notaDinasList) {
                if (notaDinas.getNipPenandatangan().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (notaDinas.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (notaDinas.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = notaDinas.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(notaDinas.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                notaDinas.getKdNotaDinas(),
                                "Nota Dinas",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                3,
                                0,
                                notaDinas.getTanggalPembuatanMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }

            List<Pengumuman> pengumumanList = new ArrayList<>();
            if (isKepala) {
                pengumumanList
                        = pengumumanService.getPengumumanBySekretarisApproval(penilai.getKdUnitKerja());
            }
            else {
                pengumumanList
                        = pengumumanService.getByPembuat(pegawaiBawahan.getNip());
            }

            for (Pengumuman pengumuman : pengumumanList) {
                if (pengumuman.getNipPenandatangan().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (pengumuman.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (pengumuman.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = pengumuman.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(pengumuman.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                pengumuman.getKdPengumuman(),
                                "Pengumuman",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                4,
                                0,
                                pengumuman.getTanggalPembuatanMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }

            List<SuratDinas> suratDinasList = new ArrayList<>();
                if (isKepala) {
                LOGGER.info("get surat dinas kadin");

                suratDinasList
                        = suratDinasService.getSuratDinasSekretarisApproval(penilai.getKdUnitKerja());

                LOGGER.info("size "+suratDinasList.size());
            }
            else {
                suratDinasList
                        = suratDinasService.getByNipPembuat(pegawaiBawahan.getNip());
            }

            for (SuratDinas suratDinas : suratDinasList) {
                if (suratDinas.getSuratDinasPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                if (suratDinas.getNipPenandatangan().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (suratDinas.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (suratDinas.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = suratDinas.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(suratDinas.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratDinas.getKdSuratDinas(),
                                "Surat Dinas",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                5,
                                suratPejabat,
                                suratDinas.getTanggalPembuatanMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }

            List<SuratEdaran> suratEdaranList = new ArrayList<>();
            if (isKepala) {
                suratEdaranList
                        = suratEdaranService.getSuratEdaranSekretarisApproval(penilai.getKdUnitKerja());
            }
            else {
                suratEdaranList
                        = suratEdaranService.getByNipPembuat(pegawaiBawahan.getNip());
            }
            for (SuratEdaran suratEdaran : suratEdaranList) {
                if (suratEdaran.getSuratEdaranPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                if (suratEdaran.getNipPenandatangan().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (suratEdaran.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (suratEdaran.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = suratEdaran.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(suratEdaran.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratEdaran.getKdSuratEdaran(),
                                "Surat Edaran",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                6,
                                suratPejabat,
                                suratEdaran.getTanggalPembuatanMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }

            List<SuratKeputusan> suratKeputusanList = new ArrayList<>();
            if (isKepala) {
                suratKeputusanList
                        = suratKeputusanService.getSuratKeputusanSekretarisApproval(penilai.getKdUnitKerja());
            }
            else {
                suratKeputusanList
                        = suratKeputusanService.getByNipPembuat(pegawaiBawahan.getNip());
            }
            for (SuratKeputusan suratKeputusan : suratKeputusanList) {

                if (suratKeputusan.getNipPenandatangan().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (suratKeputusan.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (suratKeputusan.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = suratKeputusan.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(suratKeputusan.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratKeputusan.getKdSuratKeputusan(),
                                "Surat Keputusan",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                7,
                                0,
                                suratKeputusan.getTanggalPembuatanMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }

            List<SuratKeterangan> suratKeterangans = new ArrayList<>();
            if (isKepala) {
                suratKeterangans
                        = suratKeteranganService.getSuratKeteranganSekretarisApproval(penilai.getKdUnitKerja());
            }
            else {
                suratKeterangans
                        = suratKeteranganService.getByNipPembuat(pegawaiBawahan.getNip());
            }
            for (SuratKeterangan suratKeterangan : suratKeterangans) {

                if (suratKeterangan.getNipPenandatangan().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (suratKeterangan.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (suratKeterangan.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = suratKeterangan.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(suratKeterangan.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratKeterangan.getKdSuratKeterangan(),
                                "Surat Keterangan",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                8,
                                0,
                                suratKeterangan.getTanggalPembuatanSuratMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }

            List<SuratPengantar> suratPengantarList = new ArrayList<>();
            if (isKepala) {
                suratPengantarList
                        = suratPengantarService.getSuratPengantarSekretarisApproval(penilai.getKdUnitKerja());
            }
            else {
                suratPengantarList
                        = suratPengantarService.getByNipPembuat(pegawaiBawahan.getNip());
            }
            for (SuratPengantar suratPengantar : suratPengantarList) {

                if (suratPengantar.getNipPemberiSuratPengantar().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (suratPengantar.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (suratPengantar.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = suratPengantar.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(suratPengantar.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratPengantar.getKdSuratPengantar(),
                                "Surat Pengantar",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                10,
                                0,
                                suratPengantar.getTanggalPembuatanMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }

            Set<SuratTugas> suratTugases = new HashSet<>();
            if (isKepala) {
                suratTugases
                        = suratTugasService.getSuratTugasSekretarisApproval(penilai.getKdUnitKerja());
            }
            else {
                suratTugases
                        = suratTugasService.getByNipPembuat(pegawaiBawahan.getNip());
            }
            for (SuratTugas suratTugas : suratTugases) {
                if (suratTugas.getSuratTugasPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                if (suratTugas.getNipPenandatangan().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (suratTugas.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (suratTugas.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = suratTugas.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(suratTugas.getNipPembuat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratTugas.getKdSuratTugas(),
                                "Surat Tugas",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                12,
                                suratPejabat,
                                suratTugas.getTanggalTugasMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }
            List<SuratUndangan> suratUndanganList = new ArrayList<>();
            if (isKepala) {
                suratUndanganList
                        = suratUndanganService.getSuratUndanganSekretarisApproval(penilai.getKdUnitKerja());
            }
            else {
                suratUndanganList
                        = suratUndanganService.getByNipPembuat(pegawaiBawahan.getNip());
            }
            for (SuratUndangan suratUndangan : suratUndanganList) {
                if (suratUndangan.getSuratUndanganPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                if (suratUndangan.getNipPenandatangan().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (suratUndangan.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (suratUndangan.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = suratUndangan.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(suratUndangan.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratUndangan.getKdSuratUndangan(),
                                "Surat Undangan",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                13,
                                suratPejabat,
                                suratUndangan.getTanggalPembuatanSurat(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }
            //ambil data surat perintah yang dilaporkan bawahan
            Set<SuratPerintah> suratPerintahList = new HashSet<>();
            if (isKepala) {
                suratPerintahList
                        = suratPerintahService.getSuratPerintahSekretarisApproval(penilai.getKdUnitKerja());
            }
            else {
                suratPerintahList
                        = suratPerintahService.getByNipPembuat(pegawaiBawahan.getNip());
            }

            for (SuratPerintah suratPerintahBawahan : suratPerintahList) {

                if (suratPerintahBawahan.getSuratPerintahPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                if (suratPerintahBawahan.getNipPenandatangan().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (suratPerintahBawahan.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (suratPerintahBawahan.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = suratPerintahBawahan.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(suratPerintahBawahan.getNipPembuat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratPerintahBawahan.getKdSuratPerintah(),
                                "Surat Perintah",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                11,
                                suratPejabat,
                                suratPerintahBawahan.getTanggalPerintahMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }
            //ambil data surat kuasa yang dilaporkan bawahan
            List<SuratKuasa> suratKuasaList
                    = suratKuasaService.getByNipPembuatSurat(pegawaiBawahan.getNip());
            for (SuratKuasa suratKuasaBawahan : suratKuasaList) {

                if (suratKuasaBawahan.getNipPemberiKuasa().equals(nipPenilai))
                    isPenandatangan = true;
                else isPenandatangan = false;

                if (suratKuasaBawahan.getApprovalPenandatangan() == 1) {
                    statusPenilaian = 3;
                } else {
                    if (suratKuasaBawahan.getStatusPenilaian() == 3) {
                        statusPenilaian = 4;
                    } else {
                        statusPenilaian = suratKuasaBawahan.getStatusPenilaian();
                    }
                }

                pembuatLaporan = qutPegawaiService.getQutPegawai(suratKuasaBawahan.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratKuasaBawahan.getKdSuratKuasa(),
                                "Surat Kuasa",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                statusPenilaian,
                                9,
                                0,
                                suratKuasaBawahan.getTanggalPembuatanMilis(),
                                isPenandatangan,
                                null,
                                null,
                                null,
                                dariKabid));
            }
            //ambil data laporan yang dilaporkan bawahan
            List<Laporan> laporanList
                    = laporanService.getByNipPembuatSurat(pegawaiBawahan.getNip());
            for (Laporan laporanBawahan : laporanList) {

                pembuatLaporan = qutPegawaiService.getQutPegawai(laporanBawahan.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                laporanBawahan.getKdLaporan(),
                                "Laporan Pegawai",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                laporanBawahan.getStatusPenilaian(),
                                1,
                                0,
                                laporanBawahan.getTanggalPembuatanMilis(),
                                true,
                                null,
                                null,
                                null,
                                dariKabid));
            }
            //ambil data telaahan staf yang dilaporkan bawahan
            List<TelaahanStaf> telaahanStafList
                    = telaahanStafService.getByNipPembuatSurat(pegawaiBawahan.getNip());
            for (TelaahanStaf telaahanStafBawahan : telaahanStafList) {

                pembuatLaporan = qutPegawaiService.getQutPegawai(telaahanStafBawahan.getNipPembuatSurat());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                telaahanStafBawahan.getKdTelaahanStaf(),
                                "Telaahan staf",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                telaahanStafBawahan.getStatusPenilaian(),
                                14,
                                0,
                                telaahanStafBawahan.getTanggalPembuatanMilis(),
                                true,
                                null,
                                null,
                                null,
                                dariKabid));
            }

            List<TemplateLain> templateLainList
                    = templateLainService.getByPembuat(pegawaiBawahan.getNip());
            for (TemplateLain templateLainBawahan : templateLainList) {

                pembuatLaporan = qutPegawaiService.getQutPegawai(templateLainBawahan.getNipPegawai());

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(templateLainBawahan.getKdTemplateLain(),
                                "template lain",
                                pembuatLaporan.getNip(),
                                pembuatLaporan.getNama(),
                                templateLainBawahan.getStatusPenilaian(),
                                15,
                                0,
                                templateLainBawahan.getTanggalPembuatanMilis(),
                                true,
                                FilenameUtils.removeExtension(templateLainBawahan.getPathFile()),
                                FilenameUtils.getExtension(templateLainBawahan.getPathFile()),
                                null,
                                dariKabid
                        ));
            }
        }

        return new ResponseEntity<Object>(laporanBawahanWrapperList, HttpStatus.OK);
    }

    //service ini digunakan beberapa menit sekali sebagai notifikasi jika ada laporan baru/yang belum dibaca
    @RequestMapping(value = "/get-laporan-bawahan-notif/{nipPenilai}", method = RequestMethod.GET)
    ResponseEntity<?> getLaporanBawahanNotif(@PathVariable("nipPenilai") String nipPenilai) {
        LOGGER.info("get laporan pegawai bawahan notif");

        List<LaporanBawahanWrapper> laporanBawahanWrapperList
                = new ArrayList<>();
        List<LaporanBawahanWrapper> laporanBawahanNotifList
                = new ArrayList<>();

        List<QutPegawaiClone> pegawaiBawahanList = new ArrayList<>();
        List<PejabatPenilaiDinilai> kdJabatanPegawaiBawahanList
                = pejabatPenilaiDinilaiService.findPegawaiDinilai(nipPenilai);

        //ambil data pegawai bawahan terlebih dahulu
        for (PejabatPenilaiDinilai jabatan : kdJabatanPegawaiBawahanList) {
            List<QutPegawaiClone> pegawaiBawahanJabatanList
                    = qutPegawaiService.getQutPegawaiByKdJabatan(jabatan.getPejabatPenilaiDinilaiId().getKdJabatanDinilai());
            for (QutPegawaiClone pegawaiBawahan : pegawaiBawahanJabatanList) {
                pegawaiBawahanList.add(pegawaiBawahan);
            }
        }
        //ambil laporan dari seluruh history template untuk setiap pegawai bawahan
        Integer suratPejabat;
        for (QutPegawaiClone pegawaiBawahan : pegawaiBawahanList) {
            //ambil data berita acara yang dilaporkan bawahan
            List<BeritaAcara> beritaAcaraList
                    = beritaAcaraService.getByNipPembuatSurat(pegawaiBawahan.getNip());
            for (BeritaAcara beritaAcaraBawahan : beritaAcaraList) {
                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                beritaAcaraBawahan.getKdBeritaAcara(),
                                "Berita Acara",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                beritaAcaraBawahan.getStatusPenilaian(),
                                0,
                                0,
                                beritaAcaraBawahan.getTanggalPembuatanMilis()));
            }
            List<Memorandum> memorandums
                    = memorandumService.getByNipPembuat(pegawaiBawahan.getNip());
            for (Memorandum memorandum : memorandums) {
                if (memorandum.getMemorandumPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                memorandum.getKdMemorandum(),
                                "Memorandum",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                memorandum.getStatusPenilaian(),
                                2,
                                suratPejabat,
                                memorandum.getTanggalPembuatanMilis()));
            }
            List<NotaDinas> notaDinasList
                    = notaDinasService.getByNipPembuat(pegawaiBawahan.getNip());
            for (NotaDinas notaDinas : notaDinasList) {
                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                notaDinas.getKdNotaDinas(),
                                "Nota Dinas",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                notaDinas.getStatusPenilaian(),
                                3,
                                0,
                                notaDinas.getTanggalPembuatanMilis()));
            }
            List<Pengumuman> pengumumanList
                    = pengumumanService.getByPembuat(pegawaiBawahan.getNip());
            for (Pengumuman pengumuman : pengumumanList) {
                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                pengumuman.getKdPengumuman(),
                                "Pengumuman",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                pengumuman.getStatusPenilaian(),
                                4,
                                0,
                                pengumuman.getTanggalPembuatanMilis()));
            }
            List<SuratDinas> suratDinasList
                    = suratDinasService.getByNipPembuat(pegawaiBawahan.getNip());
            for (SuratDinas suratDinas : suratDinasList) {
                if (suratDinas.getSuratDinasPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratDinas.getKdSuratDinas(),
                                "Surat Dinas",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                suratDinas.getStatusPenilaian(),
                                5,
                                suratPejabat,
                                suratDinas.getTanggalPembuatanMilis()));
            }
            List<SuratEdaran> suratEdaranList
                    = suratEdaranService.getByNipPembuat(pegawaiBawahan.getNip());
            for (SuratEdaran suratEdaran : suratEdaranList) {
                if (suratEdaran.getSuratEdaranPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratEdaran.getKdSuratEdaran(),
                                "Surat Edaran",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                suratEdaran.getStatusPenilaian(),
                                6,
                                suratPejabat,
                                suratEdaran.getTanggalPembuatanMilis()));
            }
            List<SuratKeputusan> suratKeputusanList
                    = suratKeputusanService.getByNipPembuat(pegawaiBawahan.getNip());
            for (SuratKeputusan suratKeputusan : suratKeputusanList) {

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratKeputusan.getKdSuratKeputusan(),
                                "Surat Keputusan",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                suratKeputusan.getStatusPenilaian(),
                                7,
                                0,
                                suratKeputusan.getTanggalPembuatanMilis()));
            }
            List<SuratKeterangan> suratKeterangans
                    = suratKeteranganService.getByNipPembuat(pegawaiBawahan.getNip());
            for (SuratKeterangan suratKeterangan : suratKeterangans) {

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratKeterangan.getKdSuratKeterangan(),
                                "Surat Keterangan",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                suratKeterangan.getStatusPenilaian(),
                                8,
                                0,
                                suratKeterangan.getTanggalPembuatanSuratMilis()));
            }
            List<SuratPengantar> suratPengantarList
                    = suratPengantarService.getByNipPembuat(pegawaiBawahan.getNip());
            for (SuratPengantar suratPengantar : suratPengantarList) {

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratPengantar.getKdSuratPengantar(),
                                "Surat Pengantar",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                suratPengantar.getStatusPenilaian(),
                                10,
                                0,
                                suratPengantar.getTanggalPembuatanMilis()));
            }
            Set<SuratTugas> suratTugases
                    = suratTugasService.getByNipPembuat(pegawaiBawahan.getNip());
            for (SuratTugas suratTugas : suratTugases) {
                if (suratTugas.getSuratTugasPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratTugas.getKdSuratTugas(),
                                "Surat Tugas",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                suratTugas.getStatusPenilaian(),
                                12,
                                suratPejabat,
                                suratTugas.getTanggalTugasMilis()));
            }
            List<SuratUndangan> suratUndanganList
                    = suratUndanganService.getByNipPembuat(pegawaiBawahan.getNip());
            for (SuratUndangan suratUndangan : suratUndanganList) {
                if (suratUndangan.getSuratUndanganPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratUndangan.getKdSuratUndangan(),
                                "Surat Undangan",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                suratUndangan.getStatusPenilaian(),
                                13,
                                suratPejabat,
                                suratUndangan.getTanggalPembuatanSurat()));
            }
            //ambil data surat perintah yang dilaporkan bawahan
            Set<SuratPerintah> suratPerintahList
                    = suratPerintahService.getByNipPembuat(pegawaiBawahan.getNip());
            for (SuratPerintah suratPerintahBawahan : suratPerintahList) {
                if (suratPerintahBawahan.getSuratPerintahPejabat() != null)
                    suratPejabat = 1;
                else
                    suratPejabat = 2;

                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratPerintahBawahan.getKdSuratPerintah(),
                                "Surat Perintah",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                suratPerintahBawahan.getStatusPenilaian(),
                                11,
                                suratPejabat,
                                suratPerintahBawahan.getTanggalPerintahMilis()));
            }
            //ambil data surat kuasa yang dilaporkan bawahan
            List<SuratKuasa> suratKuasaList
                    = suratKuasaService.getByNipPembuatSurat(pegawaiBawahan.getNip());
            for (SuratKuasa suratKuasaBawahan : suratKuasaList) {
                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                suratKuasaBawahan.getKdSuratKuasa(),
                                "Surat Kuasa",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                suratKuasaBawahan.getStatusPenilaian(),
                                9,
                                0,
                                suratKuasaBawahan.getTanggalPembuatanMilis()));
            }
            //ambil data laporan yang dilaporkan bawahan
            List<Laporan> laporanList
                    = laporanService.getByNipPembuatSurat(pegawaiBawahan.getNip());
            for (Laporan laporanBawahan : laporanList) {
                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                laporanBawahan.getKdLaporan(),
                                "Laporan Pegawai",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                laporanBawahan.getStatusPenilaian(),
                                1,
                                0,
                                laporanBawahan.getTanggalPembuatanMilis()));
            }
            //ambil data telaahan staf yang dilaporkan bawahan
            List<TelaahanStaf> telaahanStafList
                    = telaahanStafService.getByNipPembuatSurat(pegawaiBawahan.getNip());
            for (TelaahanStaf telaahanStafBawahan : telaahanStafList) {
                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(
                                telaahanStafBawahan.getKdTelaahanStaf(),
                                "Telaahan staf",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                telaahanStafBawahan.getStatusPenilaian(),
                                14,
                                0,
                                telaahanStafBawahan.getTanggalPembuatanMilis()));
            }

            List<TemplateLain> templateLainList
                    = templateLainService.getByPembuat(pegawaiBawahan.getNip());
            for (TemplateLain templateLainBawahan : templateLainList) {
                laporanBawahanWrapperList
                        .add(new LaporanBawahanWrapper(templateLainBawahan.getKdTemplateLain(),
                                "template lain",
                                pegawaiBawahan.getNip(),
                                pegawaiBawahan.getNama(),
                                templateLainBawahan.getStatusPenilaian(),
                                15,
                                0,
                                templateLainBawahan.getTanggalPembuatanMilis(),
                                FilenameUtils.removeExtension(templateLainBawahan.getPathFile()),
                                FilenameUtils.getExtension(templateLainBawahan.getPathFile())
                        ));
            }
        }

        //filter data yang belum di read saja
        for (LaporanBawahanWrapper laporanBawahanWrapper
                : laporanBawahanWrapperList) {
            if (laporanBawahanWrapper == null)
                LOGGER.error("laporan is null");

            if (laporanBawahanWrapper.getStatusPenilaian() == null) {
                LOGGER.error(
                        "status penilaian "+
                        laporanBawahanWrapper.getKdSurat()+
                        " in "+
                        laporanBawahanWrapper.getKdJenisSurat()+
                        " is null");
            } else if (laporanBawahanWrapper.getStatusPenilaian() == 0) {
                LOGGER.error(
                        "status penilaian "+
                        laporanBawahanWrapper.getKdSurat()+
                        " in "+
                        laporanBawahanWrapper.getKdJenisSurat()+
                        " is not null");
                laporanBawahanNotifList.add(laporanBawahanWrapper);
            }
        }

        return new ResponseEntity<Object>(laporanBawahanNotifList, HttpStatus.OK);
    }

    @RequestMapping(value = "/tolak-laporan", method = RequestMethod.PUT)
    ResponseEntity<?> tolakLaporan(@RequestBody LaporanBawahanInputWrapper inputWrapper) {
        LOGGER.info("tolak laporan");

        switch (inputWrapper.getKdJenisSurat()) {
            case 0 :
                BeritaAcara beritaAcara
                        = beritaAcaraService.getBeritaAcara(inputWrapper.getKdSurat());
                beritaAcara.setStatusPenilaian(3);
                beritaAcara.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                beritaAcaraService.createBeritaAcara(beritaAcara);
                break;
            case 1 :
                Laporan laporan
                        = laporanService.getLaporan(inputWrapper.getKdSurat());
                laporan.setStatusPenilaian(3);
                laporan.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                laporanService.createLaporan(laporan);
                break;
            case 2 :
                Memorandum memorandum
                        = memorandumService.getByKdMemorandum(inputWrapper.getKdSurat());
                memorandum.setStatusPenilaian(3);
                memorandum.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                memorandumService.createMemorandum(memorandum);
                break;
            case 3 :
                NotaDinas notaDinas
                        = notaDinasService.findBykdNotaDinas(inputWrapper.getKdSurat());
                notaDinas.setStatusPenilaian(3);
                notaDinas.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                notaDinasService.create(notaDinas);
                break;
            case 4 :
                Pengumuman pengumuman
                        = pengumumanService.getByKdPengumuman(inputWrapper.getKdSurat());
                pengumuman.setStatusPenilaian(3);
                pengumuman.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                pengumumanService.create(pengumuman);
                break;
            case 5 :
                SuratDinas suratDinas
                        = suratDinasService.getByKdSuratDinas(inputWrapper.getKdSurat());
                suratDinas.setStatusPenilaian(3);
                suratDinas.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                suratDinasService.create(suratDinas);
                break;
            case 6 :
                SuratEdaran suratEdaran
                        = suratEdaranService.getByKdSuratEdaran(inputWrapper.getKdSurat());
                suratEdaran.setStatusPenilaian(3);
                suratEdaran.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                suratEdaranService.create(suratEdaran);
                break;
            case 7 :
                SuratKeputusan suratKeputusan
                        = suratKeputusanService.getByKdSuratKeputusan(inputWrapper.getKdSurat());
                suratKeputusan.setStatusPenilaian(3);
                suratKeputusan.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                suratKeputusanService.create(suratKeputusan);
                break;
            case 8 :
                SuratKeterangan suratKeterangan
                        = suratKeteranganService.getByKdSuratKeterangan(inputWrapper.getKdSurat());
                suratKeterangan.setStatusPenilaian(3);
                suratKeterangan.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                suratKeteranganService.create(suratKeterangan);
                break;
            case 9 :
                SuratKuasa suratKuasa
                        = suratKuasaService.getSuratKuasa(inputWrapper.getKdSurat());
                suratKuasa.setStatusPenilaian(3);
                suratKuasa.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                suratKuasaService.createSuratKuasa(suratKuasa);
                break;
            case 10 :
                SuratPengantar suratPengantar
                        = suratPengantarService.getByKdSuratPengantar(inputWrapper.getKdSurat());
                suratPengantar.setStatusPenilaian(3);
                suratPengantar.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                suratPengantarService.create(suratPengantar);
                break;
            case 11 :
                SuratPerintah suratPerintah
                        = suratPerintahService.getSuratPerintahByKdSuratPerintah(inputWrapper.getKdSurat());
                suratPerintah.setStatusPenilaian(3);
                suratPerintah.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                suratPerintahService.creteSurat(suratPerintah);
                break;
            case 12 :
                SuratTugas suratTugas
                        = suratTugasService.getByKdSuratTugas(inputWrapper.getKdSurat());
                suratTugas.setStatusPenilaian(3);
                suratTugas.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                suratTugasService.create(suratTugas);
                break;
            case 13 :
                SuratUndangan suratUndangan
                        = suratUndanganService.getByKdSuratUndangan(inputWrapper.getKdSurat());
                suratUndangan.setStatusPenilaian(3);
                suratUndangan.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                suratUndanganService.create(suratUndangan);
                break;
            case 14 :
                TelaahanStaf telaahanStaf
                        = telaahanStafService.getTelaahanStaf(inputWrapper.getKdSurat());
                telaahanStaf.setStatusPenilaian(3);
                telaahanStaf.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                telaahanStafService.createTelaahanStaf(telaahanStaf);
                break;
            case 15 :
                TemplateLain templateLain
                        = templateLainService.getTemplateLain(inputWrapper.getKdSurat());
                templateLain.setStatusPenilaian(3);
                templateLain.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                templateLainService.create(templateLain);
                break;
        }

        return new ResponseEntity<Object>(new CustomMessage("laporan sudah ditolak"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-report-bulanan/{nipPegawai}/{bulan}/{tahun}")
    ResponseEntity<?> getReportBulanan(@PathVariable("nipPegawai") String nipPegawai,
                                       @PathVariable("bulan") Integer bulan,
                                       @PathVariable("tahun") Integer tahun) {
        LOGGER.info("get repot bulanan");

        KehadiranPegawaiBulananWrapper daftarhadirWrapper = new KehadiranPegawaiBulananWrapper();

        List<Integer> daftarTanggalKehadiran = new ArrayList<>();
        List<LoginPegawai> loginPegawaiList
                = loginPegawaiService.getByBulanAndTahun(bulan, tahun, nipPegawai);

        List<LaporanKinerjaPegawaiWrapper> laporanKinerjaPegawaiWrapperList
                = new ArrayList<>();

        boolean found;
        for (LoginPegawai loginPegawai : loginPegawaiList) {
//            LOGGER.info(loginPegawai.getTanggalLogin()+"-");
            found = false;

            for (Integer tanggal : daftarTanggalKehadiran) {
                if (tanggal == loginPegawai.getTanggalLogin()) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                daftarTanggalKehadiran.add(loginPegawai.getTanggalLogin());
            }
        }

        QutPegawai pegawai = qutPegawaiService.getQutPegawai(nipPegawai);

        List<TelaahanStaf> telaahanStafList
                = telaahanStafService.getByNipPembuatSurat(nipPegawai);
        List<BeritaAcara> beritaAcaraList
                = beritaAcaraService.getByNipPembuatSurat(nipPegawai);
        List<Laporan> laporanList
                = laporanService.getByNipPembuatSurat(nipPegawai);
        List<SuratInstruksi> suratInstruksiList
                = suratInstruksiService.getSuratInstruksiByNip(nipPegawai);
        List<SuratKuasa> suratKuasaList
                = suratKuasaService.getByNipPembuatSurat(nipPegawai);
        Set<SuratPerintah> suratPerintahList
                = suratPerintahService.getByNipPembuat(nipPegawai);
        List<TemplateLain> templateLainList
                = templateLainService.getByPembuat(nipPegawai);
        List<LembarDisposisi> lembarDisposisiList
                = lembarDisposisiService.findByNipPegawai(nipPegawai);

        Calendar calendar = Calendar.getInstance();
        Date date = null;

        Integer jumlahMenit = 0;

        for (Integer tanggal : daftarTanggalKehadiran) {

            LaporanKinerjaPegawaiWrapper laporanKinerja = new LaporanKinerjaPegawaiWrapper();

            laporanKinerja.setNipPegawai(pegawai.getNip());
            laporanKinerja.setNamaPegawai(pegawai.getNama());
            laporanKinerja.setTanggal(tanggal);
            laporanKinerja.setBulan(bulan);
            laporanKinerja.setTahun(tahun);

            List<KinerjaPegawaiWrapper> kinerjaPegawaiWrappers = new ArrayList<>();

            for (TelaahanStaf telaahanStaf : telaahanStafList) {
                date = new Date(telaahanStaf.getTanggalPembuatanMilis());
                calendar.setTime(date);

                int tanggalDibuat = calendar.get(Calendar.DAY_OF_MONTH);
                int bulanDibuat = calendar.get(Calendar.MONTH)+1;
                int tahunDibuat = calendar.get(Calendar.YEAR);

                if (bulanDibuat == bulan && tahunDibuat == tahun && tanggalDibuat == tanggal) {
                    kinerjaPegawaiWrappers
                            .add(new KinerjaPegawaiWrapper(
                                    telaahanStaf.getKdTelaahanStaf(),
                                    "telaahan staf",
                                    14,
                                    telaahanStaf.getDurasiPengerjaan()));
                }
            }
            for (BeritaAcara beritaAcara : beritaAcaraList) {
                date = new Date(beritaAcara.getTanggalPembuatanMilis());
                calendar.setTime(date);

                int tanggalDibuat = calendar.get(Calendar.DAY_OF_MONTH);
                int bulanDibuat = calendar.get(Calendar.MONTH)+1;
                int tahunDibuat = calendar.get(Calendar.YEAR);

                if (bulanDibuat == bulan && tahunDibuat == tahun && tanggalDibuat == tanggal) {
                    kinerjaPegawaiWrappers
                            .add(new KinerjaPegawaiWrapper(
                                    beritaAcara.getKdBeritaAcara(),
                                    "berita acara",
                                    0,
                                    beritaAcara.getDurasiPengerjaan()));
                }
            }
            for (Laporan laporan : laporanList) {
                date = new Date(laporan.getTanggalPembuatanMilis());
                calendar.setTime(date);

                int tanggalDibuat = calendar.get(Calendar.DAY_OF_MONTH);
                int bulanDibuat = calendar.get(Calendar.MONTH)+1;
                int tahunDibuat = calendar.get(Calendar.YEAR);

                if (bulanDibuat == bulan && tahunDibuat == tahun && tanggalDibuat == tanggal) {
                    kinerjaPegawaiWrappers
                            .add(new KinerjaPegawaiWrapper(
                                    laporan.getKdLaporan(),
                                    "laporan",
                                    1,
                                    laporan.getDurasiPengerjaan()));
                }
            }
            for (SuratInstruksi suratInstruksi : suratInstruksiList) {
                date = new Date(suratInstruksi.getCreateddateMilis());
                calendar.setTime(date);

                int tanggalDibuat = calendar.get(Calendar.DAY_OF_MONTH);
                int bulanDibuat = calendar.get(Calendar.MONTH)+1;
                int tahunDibuat = calendar.get(Calendar.YEAR);

                if (bulanDibuat == bulan && tahunDibuat == tahun && tanggalDibuat == tanggal) {
                    kinerjaPegawaiWrappers
                            .add(new KinerjaPegawaiWrapper(
                                    suratInstruksi.getKdInstruksi(),
                                    "surat instruksi",
                                    16,
                                    suratInstruksi.getDurasiPengerjaan()));
                }
            }
            for (SuratKuasa suratKuasa : suratKuasaList) {
                date = new Date(suratKuasa.getTanggalPembuatanMilis());
                calendar.setTime(date);

                int tanggalDibuat = calendar.get(Calendar.DAY_OF_MONTH);
                int bulanDibuat = calendar.get(Calendar.MONTH)+1;
                int tahunDibuat = calendar.get(Calendar.YEAR);

                if (bulanDibuat == bulan && tahunDibuat == tahun && tanggalDibuat == tanggal) {
                    kinerjaPegawaiWrappers
                            .add(new KinerjaPegawaiWrapper(
                                    suratKuasa.getKdSuratKuasa(),
                                    "surat kuasa",
                                    9,
                                    suratKuasa.getDurasiPengerjaan()));
                }
            }
            for (SuratPerintah suratPerintah : suratPerintahList) {
                date = new Date(suratPerintah.getTanggalPerintahMilis());
                calendar.setTime(date);

                int tanggalDibuat = calendar.get(Calendar.DAY_OF_MONTH);
                int bulanDibuat = calendar.get(Calendar.MONTH)+1;
                int tahunDibuat = calendar.get(Calendar.YEAR);

                if (bulanDibuat == bulan && tahunDibuat == tahun && tanggalDibuat == tanggal) {
                    kinerjaPegawaiWrappers
                            .add(new KinerjaPegawaiWrapper(
                                    suratPerintah.getKdSuratPerintah(),
                                    "surat perintah",
                                    11,
                                    suratPerintah.getDurasiPengerjaan()));
                }
            }
            for (LembarDisposisi lembarDisposisi : lembarDisposisiList) {
                date = new Date(lembarDisposisi.getTanggalPengirimanMilis());
                calendar.setTime(date);

                int tanggalDibuat = calendar.get(Calendar.DAY_OF_MONTH);
                int bulanDibuat = calendar.get(Calendar.MONTH)+1;
                int tahunDibuat = calendar.get(Calendar.YEAR);

                if (bulanDibuat == bulan && tahunDibuat == tahun && tanggalDibuat == tanggal) {
                    kinerjaPegawaiWrappers
                            .add(new KinerjaPegawaiWrapper(
                                    lembarDisposisi.getKdLembarDisposisi(),
                                    "lembar disposisi",
                                    17,
                                    lembarDisposisi.getDurasiPengerjaan()));
                }
            }
            for (TemplateLain templateLain : templateLainList) {
                date = new Date(templateLain.getTanggalPembuatanMilis());
                calendar.setTime(date);

                int tanggalDibuat = calendar.get(Calendar.DAY_OF_MONTH);
                int bulanDibuat = calendar.get(Calendar.MONTH)+1;
                int tahunDibuat = calendar.get(Calendar.YEAR);

                if (bulanDibuat == bulan && tahunDibuat == tahun && tanggalDibuat == tanggal) {
                    kinerjaPegawaiWrappers
                            .add(new KinerjaPegawaiWrapper(
                                    templateLain.getKdTemplateLain(),
                                    "template lain",
                                    15,
                                    templateLain.getDurasiPengerjaan()));
                }
            }

            laporanKinerja.setDaftarKinerjaPegawaiWrapper(kinerjaPegawaiWrappers);

            laporanKinerjaPegawaiWrapperList.add(laporanKinerja);
        }


        return new ResponseEntity<Object>(laporanKinerjaPegawaiWrapperList, HttpStatus.OK);
//        return new ResponseEntity<Object>(daftarTanggalKehadiran, HttpStatus.OK);

    }


    /**
     *
     * service yang digunakan untuk mengambil kontrak kerja yang diajukan oleh bawahan
     *
     * digunakan oleh pegawai penilai untuk melihat kontrak ajuan bawahan
     *
     * @param nipPenilai
     * @return
     */
    @RequestMapping(value = "/get-kontrak-ajuan-bawahan/{nipPenilai}", method = RequestMethod.GET)
    ResponseEntity<?> getKontrakAjuanBawahan(@PathVariable("nipPenilai") String nipPenilai) {
        LOGGER.info("get kontrak ajuan bawahan");

        List<QutPegawaiClone> pegawaiBawahanList = new ArrayList<>();
        List<PejabatPenilaiDinilai> kdJabatanPegawaiBawahanList
                = pejabatPenilaiDinilaiService.findPegawaiDinilai(nipPenilai);

        //ambil data pegawai bawahan terlebih dahulu
        for (PejabatPenilaiDinilai jabatan : kdJabatanPegawaiBawahanList) {
            List<QutPegawaiClone> pegawaiBawahanJabatanList
                    = qutPegawaiService.getQutPegawaiByKdJabatan(jabatan.getPejabatPenilaiDinilaiId().getKdJabatanDinilai());
            for (QutPegawaiClone pegawaiBawahan : pegawaiBawahanJabatanList) {
                pegawaiBawahanList.add(pegawaiBawahan);
            }
        }

        for (QutPegawaiClone pegawaiBawahan : pegawaiBawahanList) {

        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }



    //sampai disini

//    @Resource(name = "BasicAuthenticationProvider")
//    private AuthenticationProvider authenticationProvider;
//
//    //prototype. should return another object
//    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
//    ResponseEntity<?> validateAkunPegawai(@RequestBody AkunPegawai akunPegawai) {
//        LOGGER.info("receive "+akunPegawai.getUserName()+" authetication request");
//
//        AkunPegawai akunPegawaiAuthenticated = null;
//
//        try {
//            akunPegawaiAuthenticated = authenticationProvider.authenticate(akunPegawai);
//        } catch (AuthenticationCredentialsNotFoundExcecption authenticationCredentialsNotFoundExcecption) {
//            return new ResponseEntity<Object>(
//                    new CustomMessage(authenticationCredentialsNotFoundExcecption.getMessage()),
//                    HttpStatus.NOT_FOUND);
//
//        } catch (BadCredentialsException e) {
//            return new ResponseEntity<Object>(
//                    new CustomMessage(e.getMessage()),
//                    HttpStatus.FORBIDDEN);
//
//        }
//
//        return new ResponseEntity<Object>(akunPegawaiAuthenticated, HttpStatus.OK);
//
//    }

}



////////////////////////////////////////////////////////////////
//        daftarhadirWrapper.setDaftarTanggalHadir(daftarTanggalKehadiran);
//        daftarhadirWrapper.setNipPegawai(pegawai.getNip());
//        daftarhadirWrapper.setNamaPegawai(pegawai.getNama());
//        daftarhadirWrapper.setBulan(bulan);
//        daftarhadirWrapper.setTahun(tahun);
//
//        Integer jumlahMenit = 0;
//
//        List<TelaahanStaf> telaahanStafList
//                = telaahanStafService.getByNipPembuatSurat(nipPegawai);
//        List<BeritaAcara> beritaAcaraList
//                = beritaAcaraService.getByNipPembuatSurat(nipPegawai);
//        List<Laporan> laporanList
//                = laporanService.getByNipPembuatSurat(nipPegawai);
//        List<SuratInstruksi> suratInstruksiList
//                = suratInstruksiService.getSuratInstruksiByNip(nipPegawai);
//        List<SuratKuasa> suratKuasaList
//                = suratKuasaService.getByNipPembuatSurat(nipPegawai);
//        Set<SuratPerintah> suratPerintahList
//                = suratPerintahService.getByNipPembuat(nipPegawai);
//        List<TemplateLain> templateLainList
//                = templateLainService.getByPembuat(nipPegawai);
//        List<LembarDisposisi> lembarDisposisiList
//                = lembarDisposisiService.findByNipPegawai(nipPegawai);
//
//        Calendar calendar = Calendar.getInstance();
//        Date date = null;
//
//        for (TelaahanStaf telaahanStaf : telaahanStafList) {
//            date = new Date(telaahanStaf.getTanggalPembuatanMilis());
//            calendar.setTime(date);
//
//            int bulanDibuat = calendar.get(Calendar.MONTH)+1;
//            int tahunDibuat = calendar.get(Calendar.YEAR);
//
//            if (bulanDibuat == bulan && tahunDibuat == tahun) {
//                jumlahMenit = jumlahMenit + telaahanStaf.getDurasiPengerjaan();
//            }
//        }
//        for (BeritaAcara beritaAcara : beritaAcaraList) {
//            date = new Date(beritaAcara.getTanggalPembuatanMilis());
//            calendar.setTime(date);
//
//            int bulanDibuat = calendar.get(Calendar.MONTH)+1;
//            int tahunDibuat = calendar.get(Calendar.YEAR);
//
//            if (bulanDibuat == bulan && tahunDibuat == tahun) {
//                jumlahMenit = jumlahMenit + beritaAcara.getDurasiPengerjaan();
//            }
//        }
//        for (Laporan laporan : laporanList) {
//            date = new Date(laporan.getTanggalPembuatanMilis());
//            calendar.setTime(date);
//
//            int bulanDibuat = calendar.get(Calendar.MONTH)+1;
//            int tahunDibuat = calendar.get(Calendar.YEAR);
//
//            if (bulanDibuat == bulan && tahunDibuat == tahun) {
//                jumlahMenit = jumlahMenit + laporan.getDurasiPengerjaan();
//            }
//        }
//        for (SuratInstruksi suratInstruksi : suratInstruksiList) {
//            date = new Date(suratInstruksi.getCreateddateMilis());
//            calendar.setTime(date);
//
//            int bulanDibuat = calendar.get(Calendar.MONTH)+1;
//            int tahunDibuat = calendar.get(Calendar.YEAR);
//
//            if (bulanDibuat == bulan && tahunDibuat == tahun) {
//                jumlahMenit = jumlahMenit + suratInstruksi.getDurasiPengerjaan();
//            }
//        }
//        for (SuratKuasa suratKuasa : suratKuasaList) {
//            date = new Date(suratKuasa.getTanggalPembuatanMilis());
//            calendar.setTime(date);
//
//            int bulanDibuat = calendar.get(Calendar.MONTH)+1;
//            int tahunDibuat = calendar.get(Calendar.YEAR);
//
//            if (bulanDibuat == bulan && tahunDibuat == tahun) {
//                jumlahMenit = jumlahMenit + suratKuasa.getDurasiPengerjaan();
//            }
//        }
//        for (SuratPerintah suratPerintah : suratPerintahList) {
//            date = new Date(suratPerintah.getTanggalPerintahMilis());
//            calendar.setTime(date);
//
//            int bulanDibuat = calendar.get(Calendar.MONTH)+1;
//            int tahunDibuat = calendar.get(Calendar.YEAR);
//
//            if (bulanDibuat == bulan && tahunDibuat == tahun) {
//                jumlahMenit = jumlahMenit + suratPerintah.getDurasiPengerjaan();
//            }
//        }
//        for (LembarDisposisi lembarDisposisi : lembarDisposisiList) {
//            date = new Date(lembarDisposisi.getTanggalPengirimanMilis());
//            calendar.setTime(date);
//
//            int bulanDibuat = calendar.get(Calendar.MONTH)+1;
//            int tahunDibuat = calendar.get(Calendar.YEAR);
//
//            if (bulanDibuat == bulan && tahunDibuat == tahun) {
//                jumlahMenit = jumlahMenit + lembarDisposisi.getDurasiPengerjaan();
//            }
//        }
//
//        daftarhadirWrapper.setJumlahMenitKerja(jumlahMenit);
//
//        return new ResponseEntity<Object>(daftarhadirWrapper, HttpStatus.OK);