package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.Role;
import com.pemda.ekinerjademo.repository.bismarepository.QutPegawaiDao;
import com.pemda.ekinerjademo.repository.bismarepository.TkdJabatanDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.AkunPegawaiDao;
import com.pemda.ekinerjademo.service.AkunPegawaiService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import com.pemda.ekinerjademo.service.RoleService;
import com.pemda.ekinerjademo.service.TkdJabatanService;
import com.pemda.ekinerjademo.wrapper.input.AkunPegawaiRoleInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private QutPegawaiService qutPegawaiService;

    @Autowired
    public AkunPegawaiController(
            TkdJabatanDao tkdJabatanDao,
            QutPegawaiDao qutPegawaiDao,
            AkunPegawaiDao akunPegawaiDao,
            AkunPegawaiService akunPegawaiService,
            RoleService roleService,
            TkdJabatanService tkdJabatanService,
            QutPegawaiService qutPegawaiService) {
        this.tkdJabatanDao = tkdJabatanDao;
        this.qutPegawaiDao = qutPegawaiDao;
        this.akunPegawaiDao = akunPegawaiDao;
        this.akunPegawaiService = akunPegawaiService;
        this.roleService = roleService;
        this.tkdJabatanService = tkdJabatanService;
        this.qutPegawaiService = qutPegawaiService;
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
                    .add(new JabatanWrapper(tkdJabatan.getKdJabatan(),tkdJabatan.getJabatan()));
        }

        return new ResponseEntity<Object>(jabatanWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-pegawai", method = RequestMethod.GET)
    @Transactional
    ResponseEntity<?> getPegawai() {
        LOGGER.info("get akun pegawai with role");

        List<QutPegawaiWrapper> qutPegawaiWrappers
                = new ArrayList<>();
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawai();

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

        for (QutPegawai qutPegawai : qutPegawaiList) {
            qutPegawaiWrappers
                    .add(new QutPegawaiWrapper(
                            qutPegawai.getNip(),
                            qutPegawai.getNama(),
                            qutPegawai.getJabatan(),
                            qutPegawai.getUnitKerja()));
        }

        LOGGER.info("finish");

        return new ResponseEntity<Object>(qutPegawaiWrappers, HttpStatus.OK);
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
                            qutPegawai.getJabatan(),
                            qutPegawai.getUnitKerja()));
        }

        LOGGER.info("finish");

        return new ResponseEntity<Object>(qutPegawaiWrappers, HttpStatus.OK);
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
