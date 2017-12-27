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
    @Autowired private SuratKuasaService suratKuasaService;
    @Autowired private TelaahanStafService telaahanStafService;
    @Autowired private TemplateLainService templateLainService;
    @Autowired private LoginPegawaiService loginPegawaiService;
    @Autowired private LembarDisposisiService lembarDisposisiService;
    @Autowired private SuratInstruksiService suratInstruksiService;

    @Autowired private TkdUnkDao tkdUnkDao;

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
    ResponseEntity<?> getAllJabatan() {
        LOGGER.info("get all jabatan");

        List<JabatanWrapper> jabatanWrapperList = new ArrayList<>();
        List<TkdJabatan> tkdJabatanList = tkdJabatanService.getAll();

        for (TkdJabatan tkdJabatan : tkdJabatanList) {
            jabatanWrapperList
                    .add(new JabatanWrapper(
                            tkdJabatan.getKdJabatan(),
                            tkdJabatan.getJabatan(),
                            tkdJabatan.getEselon()));
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
                    spj.getKdStatus().equals("ST005")) {
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
                                templateLainBawahan.getPathFile()
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
        }

        //filter data yang belum di read saja
        for (LaporanBawahanWrapper laporanBawahanWrapper
                : laporanBawahanWrapperList) {
            if (laporanBawahanWrapper.getStatusPenilaian() == 0) {
                laporanBawahanNotifList.add(laporanBawahanWrapper);
            }
        }

        return new ResponseEntity<Object>(laporanBawahanNotifList, HttpStatus.OK);
    }

    @RequestMapping(value = "/tolak-laporan", method = RequestMethod.PUT)
    ResponseEntity<?> tolakLaporan(@RequestBody LaporanBawahanInputWrapper inputWrapper) {
        LOGGER.info("tolak laporan");

        switch (inputWrapper.getKdJenisSurat()) {
            case 1 :
                BeritaAcara beritaAcara
                        = beritaAcaraService.getBeritaAcara(inputWrapper.getKdSurat());
                beritaAcara.setStatusPenilaian(3);
                beritaAcara.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                beritaAcaraService.createBeritaAcara(beritaAcara);
                break;
            case 2 :
                Laporan laporan
                        = laporanService.getLaporan(inputWrapper.getKdSurat());
                laporan.setStatusPenilaian(3);
                laporan.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                laporanService.createLaporan(laporan);
                break;
            case 3 : break;
            case 4 : break;
            case 5 : break;
            case 6 : break;
            case 7 : break;
            case 8 : break;
            case 9 :
                SuratKuasa suratKuasa
                        = suratKuasaService.getSuratKuasa(inputWrapper.getKdSurat());
                suratKuasa.setStatusPenilaian(3);
                suratKuasa.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                suratKuasaService.createSuratKuasa(suratKuasa);
                break;
            case 10 : break;
            case 11 :
                SuratPerintah suratPerintah
                        = suratPerintahService.getSuratPerintahByKdSuratPerintah(inputWrapper.getKdSurat());
                suratPerintah.setStatusPenilaian(3);
                suratPerintah.setAlasanPenolakan(inputWrapper.getAlasanPenolakan());

                suratPerintahService.creteSurat(suratPerintah);
                break;
            case 12 : break;
            case 13 : break;
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
                = loginPegawaiService.getByBulanAndTahun(bulan, tahun);

        List<LaporanKinerjaPegawaiWrapper> laporanKinerjaPegawaiWrapperList
                = new ArrayList<>();

        boolean found;
        for (LoginPegawai loginPegawai : loginPegawaiList) {
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

            laporanKinerja.setDaftarKinerjaPegawaiWrapper(kinerjaPegawaiWrappers);

            laporanKinerjaPegawaiWrapperList.add(laporanKinerja);
        }


        return new ResponseEntity<Object>(laporanKinerjaPegawaiWrapperList, HttpStatus.OK);

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