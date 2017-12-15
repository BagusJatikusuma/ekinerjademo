package com.pemda.ekinerjademo.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.bismarepository.QutPegawaiDao;
import com.pemda.ekinerjademo.repository.bismarepository.TkdJabatanDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.AkunPegawaiDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.wrapper.input.AkunPegawaiRoleInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.PegawaiPenilaiInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.UrtugKegiatanInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.UrtugKegiatanPenanggungJawabWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    private QutPegawaiCloneService qutPegawaiCloneService;
    private StatusPenanggungJawabKegiatanService statusPenanggungJawabKegiatanService;
    private UrtugKegiatanPegawaiService urtugKegiatanPegawaiService;
    private PejabatPenilaiDinilaiService pejabatPenilaiDinilaiService;

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
                                    qutPegawai.getUnitKerja(),
                                    qutPegawai.getPangkat(),
                                    qutPegawai.getGol(),
                                    akunPegawai.getRole().getRole()));
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

        List<QutPegawaiWrapper> qutPegawaiWrappers
                = new ArrayList<>();
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(inputWrapper.getKdUnitKerja());

        boolean found;
        for (QutPegawai qutPegawai : qutPegawaiList) {
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
                = qutPegawaiCloneService.getQutPegawaiByKdJabatan(inputWrapper.getKdJabatan());

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
        QutPegawaiWrapper pegawaiWrapper
                = new QutPegawaiWrapper(
                        qutPegawai.getNip(),
                qutPegawai.getNama(),
                qutPegawai.getKdJabatan(),
                qutPegawai.getJabatan(),
                qutPegawai.getKdUnitKerja(),
                qutPegawai.getUnitKerja(),
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
