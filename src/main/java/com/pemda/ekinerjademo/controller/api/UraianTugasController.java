package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;

import com.pemda.ekinerjademo.service.*;
//import com.pemda.ekinerjademo.service.UraianTugasJabatanService;
//import com.pemda.ekinerjademo.service.UraianTugasService;

import com.pemda.ekinerjademo.wrapper.input.*;
import com.pemda.ekinerjademo.wrapper.output.*;
import com.pemda.ekinerjademo.wrapper.output.UraianTugasJabatanWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 10/09/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class UraianTugasController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UraianTugasController.class);

    @Autowired
    private QutPegawaiService qutPegawaiService;
    @Autowired
    private UraianTugasJabatanService uraianTugasJabatanService;
    @Autowired
    private UraianTugasService uraianTugasService;
    @Autowired
    private TkdJabatanService tkdJabatanService;
    @Autowired
    private EKinerjaService eKinerjaService;
    @Autowired
    private AkunPegawaiService akunPegawaiService;
    @Autowired
    private RoleService roleService;

    @CrossOrigin(allowCredentials = "false")
    @RequestMapping(value = "/get-urtug-by-nip/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getUraianTugasList(@PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get "+nipPegawai+" uraian tugas ");

        //set this logic in difference layer (databindirg/dataconvert layer)
        QutPegawai qutPegawai = qutPegawaiService.getQutPegawai(nipPegawai);
        String namaPegawai = qutPegawai.getNama();
        JabatanWrapper jabatan =
                new JabatanWrapper(qutPegawai.getKdJabatan(),qutPegawai.getJabatan());
        UnitOrganisasiWrapper unitOrganisasi =
                new UnitOrganisasiWrapper("dummyId", "dummyOrganisasi");
        UnitKerjaWrapper unitKerja =
                new UnitKerjaWrapper(qutPegawai.getKdUnitKerja(), qutPegawai.getUnitKerja());
        List<UraianTugasJabatan> uraianTugasJabatanList =
                uraianTugasJabatanService.getUraianTugasJabatanByJabatan(qutPegawai.getKdJabatan());
        List<UraianTugasWrapper> uraianTugasWrapperList = new ArrayList<>();

        for (UraianTugasJabatan uraianTugasJabatan : uraianTugasJabatanList) {
            uraianTugasWrapperList
                    .add(new UraianTugasWrapper(
                            uraianTugasJabatan.getUraianTugas().getKdUrtug(),
                            uraianTugasJabatan.getUraianTugas().getDeskripsi(),
                            uraianTugasJabatan.getUraianTugas().getSatuan(),
                            uraianTugasJabatan.getUraianTugas().getVolumeKerja(),
                            uraianTugasJabatan.getUraianTugas().getNormaWaktu(),
                            uraianTugasJabatan.getUraianTugas().getBebanKerja(),
                            uraianTugasJabatan.getUraianTugas().getPeralatan(),
                            uraianTugasJabatan.getUraianTugas().getKeterangan()));
        }

        UraianTugasEKinerjaWrapper uraianTugasEKinerjaWrapper =
                new UraianTugasEKinerjaWrapper(
                        nipPegawai,
                        namaPegawai,
                        jabatan,
                        unitOrganisasi,
                        unitKerja,
                        uraianTugasWrapperList);

        return new ResponseEntity<Object>(uraianTugasEKinerjaWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/create-urtug", method = RequestMethod.POST)
    @Transactional //only for development phase
    ResponseEntity<?> saveUraianTugas(@RequestBody UraianTugasInputWrapper uraianTugasInputWrapper) {
        LOGGER.info("create new uraian tugas to database");
        //set this logic in difference layer (databindirg/dataconvert layer)
        UraianTugas uraianTugas = new UraianTugas();

        String newKdUrtug = String.valueOf(new Date().getTime());
        uraianTugas
                .setKdUrtug(newKdUrtug);
        uraianTugas.setDeskripsi(uraianTugasInputWrapper.getDeskripsi());
        uraianTugas.setSatuan(uraianTugasInputWrapper.getSatuan());
        uraianTugas.setVolumeKerja(uraianTugasInputWrapper.getVolumeKerja());
        uraianTugas.setNormaWaktu(uraianTugasInputWrapper.getNormaWaktu());
        uraianTugas.setBebanKerja(uraianTugasInputWrapper.getBebanKerja());
        uraianTugas.setPeralatan(uraianTugasInputWrapper.getPeralatan());
        uraianTugas.setKeterangan(uraianTugasInputWrapper.getKeterangan());
        uraianTugas.setCreatedBy(uraianTugasInputWrapper.getCreatedBy());

        uraianTugasService.save(uraianTugas);

        return new ResponseEntity<Object>(new CustomMessage("urtug created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/update-urtug", method = RequestMethod.PUT)
    @Transactional //only for development phase
    ResponseEntity<?> updateUraianTugas(@RequestBody UraianTugas uraianTugas) {
        LOGGER.info("update "+uraianTugas.getKdUrtug());
        UraianTugas newUraianTugas =
                uraianTugasService.getUraianTugas(uraianTugas.getKdUrtug());
        newUraianTugas.setBebanKerja(uraianTugas.getBebanKerja());
        newUraianTugas.setDeskripsi(uraianTugas.getDeskripsi());
        newUraianTugas.setKeterangan(uraianTugas.getKeterangan());
        newUraianTugas.setNormaWaktu(uraianTugas.getNormaWaktu());
        newUraianTugas.setPeralatan(uraianTugas.getPeralatan());
        newUraianTugas.setSatuan(uraianTugas.getSatuan());
        newUraianTugas.setVolumeKerja(uraianTugas.getVolumeKerja());

        uraianTugasService.update(newUraianTugas);
        return new ResponseEntity<Object>(
                new CustomMessage("urtug updated"), HttpStatus.OK);
    }


    @RequestMapping(value = "/delete-urtug/{kdUrtug}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteUraianTugas(@PathVariable("kdUrtug") String kdUrtug) {
        LOGGER.info("delete "+kdUrtug);
        uraianTugasService.delete(kdUrtug);
        return new ResponseEntity<Object>(new CustomMessage("urtug deleted"), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-all-urtug", method = RequestMethod.GET)
    ResponseEntity<?> getAllUraianTugas(){
        List<UraianTugasWrapper> uraianTugasWrappers = new ArrayList<>();
        List<UraianTugas> uraianTugases= uraianTugasService.getAllUraianTugas();

        for (UraianTugas uraianTugas : uraianTugases){
            uraianTugasWrappers.add(new UraianTugasWrapper(
                    uraianTugas.getKdUrtug(),
                    uraianTugas.getDeskripsi(),
                    uraianTugas.getSatuan(),
                    uraianTugas.getVolumeKerja(),
                    uraianTugas.getNormaWaktu(),
                    uraianTugas.getBebanKerja(),
                    uraianTugas.getPeralatan(),
                    uraianTugas.getKeterangan()
            ));
        }
        return new ResponseEntity<Object>(uraianTugasWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-all-urtug-by-jabatan/{kdJabatan}", method = RequestMethod.GET)
    ResponseEntity<?> getAllUraianTugasByJabatan(@PathVariable("kdJabatan") String kdJabatan){
        List<UraianTugasWrapper> uraianTugasWrappers = new ArrayList<>();
        List<UraianTugasJabatan> uraianTugasJabatanList =
                uraianTugasJabatanService.getUraianTugasJabatanByJabatan(kdJabatan);

        for (UraianTugasJabatan uraianTugasJabatan : uraianTugasJabatanList) {
            uraianTugasWrappers.add(new UraianTugasWrapper(
                    uraianTugasJabatan.getUraianTugas().getKdUrtug(),
                    uraianTugasJabatan.getUraianTugas().getDeskripsi(),
                    uraianTugasJabatan.getUraianTugas().getSatuan(),
                    uraianTugasJabatan.getUraianTugas().getVolumeKerja(),
                    uraianTugasJabatan.getUraianTugas().getNormaWaktu(),
                    uraianTugasJabatan.getUraianTugas().getBebanKerja(),
                    uraianTugasJabatan.getUraianTugas().getPeralatan(),
                    uraianTugasJabatan.getUraianTugas().getKeterangan()
            ));
        }

        return new ResponseEntity<Object>(uraianTugasWrappers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-jabatan-list", method = RequestMethod.GET)
    @Transactional
    ResponseEntity<?> getJabatanList() {
        LOGGER.info("get all jabatan");

        List<JabatanWrapper> jabatanWrapperList = new ArrayList<>();
        List<TkdJabatan> tkdJabatanList = tkdJabatanService.getAll();

        for (TkdJabatan tkdJabatan : tkdJabatanList) {
            jabatanWrapperList
                    .add(new JabatanWrapper(tkdJabatan.getKdJabatan(),tkdJabatan.getJabatan()));
        }

        return new ResponseEntity<Object>(jabatanWrapperList, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-uraian-tugas-by-jabatan/{kdJabatan}", method = RequestMethod.GET)
    @Transactional
    ResponseEntity<?> getUraianTugasByJabatan(@PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get uraian tugas list by jabatan");

        TkdJabatan tkdJabatan = tkdJabatanService.getTkdJabatan(kdJabatan);

        List<UraianTugasJabatanWrapper> uraianTugasJabatanWrapperList =
                new ArrayList<>();

        UraianTugasJabatanWrapper uraianTugasJabatanWrapper
                = new UraianTugasJabatanWrapper();

        List<UraianTugasJabatan> uraianTugasJabatanList =
                uraianTugasJabatanService.getUraianTugasJabatanByJabatan(kdJabatan);

        List<UraianTugas> uraianTugasList =
                uraianTugasService.getAllUraianTugas();

        List<UraianTugasWrapper> jabatanUraianTugasWrapperList =
                new ArrayList<>();
        List<UraianTugasWrapper> notJabatanUraianTugasWrapperList
                = new ArrayList<>();

        if (uraianTugasJabatanList.isEmpty()) {
            jabatanUraianTugasWrapperList = null;
            notJabatanUraianTugasWrapperList
                        = new ArrayList<>();

            for (UraianTugas uraianTugas : uraianTugasList) {
                notJabatanUraianTugasWrapperList
                        .add(new UraianTugasWrapper(
                                uraianTugas.getKdUrtug(),
                                uraianTugas.getDeskripsi(),
                                uraianTugas.getSatuan(),
                                uraianTugas.getVolumeKerja(),
                                uraianTugas.getNormaWaktu(),
                                uraianTugas.getBebanKerja(),
                                uraianTugas.getPeralatan(),
                                uraianTugas.getKeterangan()));
            }

        } else {
            jabatanUraianTugasWrapperList =
                    new ArrayList<>();
            notJabatanUraianTugasWrapperList
                    = new ArrayList<>();

            for (UraianTugas uraianTugas : uraianTugasList) {
                boolean found = false;
                for (UraianTugasJabatan uraianTugasJabatan : uraianTugasJabatanList) {
                    if (uraianTugas.getKdUrtug()
                            .equals(uraianTugasJabatan.getUraianTugas().getKdUrtug())) {
                        LOGGER.info(uraianTugas.getKdUrtug()+" sama "+uraianTugasJabatan.getUraianTugas().getKdUrtug());
                        jabatanUraianTugasWrapperList
                                .add(new UraianTugasWrapper(
                                        uraianTugas.getKdUrtug(),
                                        uraianTugas.getDeskripsi(),
                                        uraianTugas.getSatuan(),
                                        uraianTugas.getVolumeKerja(),
                                        uraianTugas.getNormaWaktu(),
                                        uraianTugas.getBebanKerja(),
                                        uraianTugas.getPeralatan(),
                                        uraianTugas.getKeterangan()));

                        found = true;
                        break;
                    }

                }

                if(!found) {
                    notJabatanUraianTugasWrapperList
                            .add(new UraianTugasWrapper(
                                    uraianTugas.getKdUrtug(),
                                    uraianTugas.getDeskripsi(),
                                    uraianTugas.getSatuan(),
                                    uraianTugas.getVolumeKerja(),
                                    uraianTugas.getNormaWaktu(),
                                    uraianTugas.getBebanKerja(),
                                    uraianTugas.getPeralatan(),
                                    uraianTugas.getKeterangan()));
                }

            }

        }

        uraianTugasJabatanWrapper.setKdJabatan(tkdJabatan.getKdJabatan());
        uraianTugasJabatanWrapper.setJabatan(tkdJabatan.getJabatan());
        uraianTugasJabatanWrapper.setJabatanUraianTugasList(jabatanUraianTugasWrapperList);
        uraianTugasJabatanWrapper.setNotJabatanUraianTugasList(notJabatanUraianTugasWrapperList);

        return new ResponseEntity<Object>(uraianTugasJabatanWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/add-uraian-tugas-jabatan", method = RequestMethod.POST)
    @Transactional
    ResponseEntity<?> addUraianTugasJabatan(
            @RequestBody UraianTugasJabatanInputWrapper uraianTugasJabatanInputWrapper) {
        LOGGER.info("set uraian tugas into jabatan "+uraianTugasJabatanInputWrapper.getKdJabatan());

        String kdJabatan = uraianTugasJabatanInputWrapper.getKdJabatan();
        List<UraianTugasJabatan> uraianTugasJabatanList =
                uraianTugasJabatanService.getUraianTugasJabatanByJabatan(kdJabatan);
        //first destroy jabatan uraian tugas if jabatan already has uraian tugas if not
        if (!uraianTugasJabatanList.isEmpty()) {
            LOGGER.info("destroy now");
            uraianTugasJabatanService
                    .deleteAllUraianTugasJabatanByJabatan(kdJabatan);
        }
        //loop as long as uraian tugas in uraianTugasJabatanInput wrapper and save Uraian tugas Jabatan
        for (KdUraianTugasWrapper kdUraianTugasWrapper :
                uraianTugasJabatanInputWrapper.getKdUraianTugasList()) {
//            LOGGER.info("masuk");
            UraianTugasJabatan uraianTugasJabatan = new UraianTugasJabatan();
            uraianTugasJabatan
                    .setUraianTugasJabatanId(
                            new UraianTugasJabatanId(
                                    kdUraianTugasWrapper.getKdUrtug(),
                                    uraianTugasJabatanInputWrapper.getKdJabatan()
                            ));
            uraianTugasJabatan
                    .setCreatedBy(uraianTugasJabatanInputWrapper.getCreatedBy());

            uraianTugasJabatanService.save(uraianTugasJabatan);
        }

        return new ResponseEntity<Object>(
                new CustomMessage("uraian tugas added"),
                HttpStatus.OK);

    }

    @RequestMapping(value = "/delete-uraian-tugas-jabatan/{kdJabatan}/{kdUrtug}", method = RequestMethod.DELETE)
    @Transactional
    ResponseEntity<?> deleteUraianTugasJabatan(
            @PathVariable("kdJabatan") String kdJabatan,
            @PathVariable("kdurtug") String kdUrtug) {
        LOGGER.info("delete urtug "+kdUrtug+" in "+kdJabatan);

        return null;
    }

    @RequestMapping(value = "/save-rincian-ekinerja", method = RequestMethod.POST)
    @Transactional
    ResponseEntity<?> saveRincianEKinerja(
            @RequestBody RincianEKinerjaInputWrapper rincianEKinerjaInputWrapper) {
        LOGGER.info("save rincian kinerja "
                + rincianEKinerjaInputWrapper.getNipPegawai()
                + " terhadap tugas "
                + rincianEKinerjaInputWrapper.getKdUrtug());

        RincianEKinerjaId rincianEKinerjaId =
                new RincianEKinerjaId(
                        rincianEKinerjaInputWrapper.getNipPegawai(),
                        rincianEKinerjaInputWrapper.getKdUrtug());


        RincianEKinerja rincianEKinerjaDatabase
                = eKinerjaService.getRincianEKinerjaById(rincianEKinerjaId);

        if (rincianEKinerjaDatabase != null) {
            rincianEKinerjaInputWrapper
                    .setCapaianMenit(
                            rincianEKinerjaInputWrapper.getCapaianMenit() + rincianEKinerjaDatabase.getCapaianMenit());
        }

        RincianEKinerja rincianEKinerja = new RincianEKinerja();
        rincianEKinerja.setRincianEKinerjaId(rincianEKinerjaId);
        rincianEKinerja.setCapaianMenit(rincianEKinerjaInputWrapper.getCapaianMenit());
        rincianEKinerja.setStatusEkinerja(rincianEKinerjaInputWrapper.getStatusEkinerja());

        eKinerjaService.save(rincianEKinerja);

        return new ResponseEntity<Object>(
                new CustomMessage("rincian kinerja submitted"),
                HttpStatus.OK);

    }

    @RequestMapping(value = "/get-rincian-ekinerja-by-nip-date/{nip}/{date}", method = RequestMethod.GET)
    @Transactional
    ResponseEntity<?> getRincianEKinerjaByDate(
            @PathVariable("nip") String nipPegawai,
            @PathVariable("date") Long dateInMiliSecond) {
        LOGGER.info("get rincian ekinerja "+nipPegawai+" in "+new Date(dateInMiliSecond));

        Date date = new Date(dateInMiliSecond);

        List<RincianEKinerja> rincianEKinerjaList =
                eKinerjaService.getRincianEKinerjaByNipAndDate(nipPegawai, date);
        List<RincianKinerjaWrapper> rincianKinerjaWrapperList =
                new ArrayList<>();

        for (RincianEKinerja rincianEKinerja : rincianEKinerjaList) {
            rincianKinerjaWrapperList
                    .add(new RincianKinerjaWrapper(
                            rincianEKinerja.getUraianTugas().getKdUrtug(),
                            rincianEKinerja.getUraianTugas().getDeskripsi(),
                            rincianEKinerja.getStatusEkinerja(),
                            rincianEKinerja.getCapaianMenit()));
        }

        return new ResponseEntity<Object>(rincianKinerjaWrapperList, HttpStatus.OK);

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

//        AkunPegawai akunPegawai = akunPegawaiService.getAkunPegawai(akunPegawaiRoleInputWrapper.getNipPegawai());
//        Role role = roleService.getRole(akunPegawaiRoleInputWrapper.getRoleId());
//
//        if (role == null) {
//            LOGGER.info("role not found");
//        }
//
//        if (akunPegawai == null) {
//            LOGGER.info("akun pegawai is null");
//        }
//
//        akunPegawai.setRole(role);
//        akunPegawaiService.save(akunPegawai);

        return new ResponseEntity<Object>(new CustomMessage("success set role"), HttpStatus.OK);
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

//    @RequestMapping(value = "/create-uraian-tugas-jabatan", method = RequestMethod.POST)
//    @Transactional("ekinerjaTransactionManager")
//    ResponseEntity<?> createUraianTugasJabatan(@RequestBody UraianTugasJabatanInputWrapper uraianTugasJabatanInputWrapper) {
//        for (KdUraianTugasWrapper kdUraianTugasWrapper :
//                uraianTugasJabatanInputWrapper.getKdUraianTugasList()) {
//            LOGGER.info(
//                    "masuk "
//                    +uraianTugasJabatanInputWrapper.getKdJabatan()+ " : "
//                    +kdUraianTugasWrapper.getKdUrtug()
//            );
//
//            UraianTugasJabatan uraianTugasJabatan = new UraianTugasJabatan();
//            uraianTugasJabatan
//                    .setUraianTugasJabatanId(
//                            new UraianTugasJabatanId(
//                                    kdUraianTugasWrapper.getKdUrtug(),
//                                    uraianTugasJabatanInputWrapper.getKdJabatan()
//                                    ));
//
//            uraianTugasJabatanService.save(uraianTugasJabatan);
//        }
//        return null;
//    }

}
