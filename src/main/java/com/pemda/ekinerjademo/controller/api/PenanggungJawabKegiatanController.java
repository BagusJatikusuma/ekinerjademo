package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.model.simdamodel.TaKegiatan;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.KegiatanPenanggungJawabProjection;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.UnitKerjaKegiatanDao;
import com.pemda.ekinerjademo.repository.simdarepository.TaKegiatanDao;
import com.pemda.ekinerjademo.repository.simdarepository.TaProgramDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.wrapper.input.KegiatanWrapper;
import com.pemda.ekinerjademo.wrapper.input.PenanggungJawabKegiatanInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 07/02/18.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class PenanggungJawabKegiatanController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PenanggungJawabKegiatanController.class);

    @Autowired private PenanggungJawabKegiatanService penanggungJawabKegiatanService;
    @Autowired private QutPegawaiCloneService qutPegawaiService;
    @Autowired private StatusPenanggungJawabKegiatanService statusPenanggungJawabKegiatanService;
    @Autowired private UnitKerjaKegiatanService unitKerjaKegiatanService;
    @Autowired private TaKegiatanService taKegiatanService;
    @Autowired private TaProgramDao taProgramDao;
    @Autowired private UrtugKegiatanService urtugKegiatanService;
    @Autowired private TkdJabatanService tkdJabatanService;

    @Autowired private TkdUnkDao tkdUnkDao;
    @Autowired
    private TaKegiatanDao taKegiatanDao;
    @Autowired
    private UnitKerjaKegiatanDao unitKerjaKegiatanDao;

    /**
     *
     * service yang digunakan untuk memasangkan kegiatan dengan penanggung jawabnya
     * dilakukan oleh admin unit kerja
     *
     * @param inputWrapper
     * @return custom message
     */
    @RequestMapping(value = "/create-penanggung-jawab-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> createPenanggungJawabKegiatan(@RequestBody PenanggungJawabKegiatanInputWrapper inputWrapper) {
        LOGGER.info("create penanggung jawab kegiatan");

        PenanggungJawabKegiatanId id
                = new PenanggungJawabKegiatanId(
                        inputWrapper.getKdUrusan(),
                        inputWrapper.getKdBidang(),
                        inputWrapper.getKdUnit(),
                        inputWrapper.getKdSub(),
                        inputWrapper.getTahun(),
                        inputWrapper.getKdProg(),
                        inputWrapper.getIdProg(),
                        inputWrapper.getKdKeg(),
                        inputWrapper.getNipPegawai(),
                        inputWrapper.getKdStatusPenanggungJawab());

        PenanggungJawabKegiatan penanggungJawabKegiatan
                = new PenanggungJawabKegiatan();
        penanggungJawabKegiatan.setPenanggungJawabKegiatanId(id);
        penanggungJawabKegiatan.setStatusApproval(0);

        penanggungJawabKegiatanService.create(penanggungJawabKegiatan);

        return new ResponseEntity<Object>(new CustomMessage("penanggung jawab berhasil dipasang"), HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk memasangkan kegiatan dengan penanggung jawabnya
     * dilakukan oleh admin unit kerja
     *
     * @param inputWrappers
     * @return custom message
     */
    @RequestMapping(value = "/create-daftar-penanggung-jawab-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> createDaftarPenanggungJawabKegiatan(@RequestBody List<PenanggungJawabKegiatanInputWrapper> inputWrappers) {
        LOGGER.info("create daftar penanggung jawab kegiatan");

        for (PenanggungJawabKegiatanInputWrapper inputWrapper : inputWrappers) {
            PenanggungJawabKegiatanId id
                    = new PenanggungJawabKegiatanId(
                    inputWrapper.getKdUrusan(),
                    inputWrapper.getKdBidang(),
                    inputWrapper.getKdUnit(),
                    inputWrapper.getKdSub(),
                    inputWrapper.getTahun(),
                    inputWrapper.getKdProg(),
                    inputWrapper.getIdProg(),
                    inputWrapper.getKdKeg(),
                    inputWrapper.getNipPegawai(),
                    inputWrapper.getKdStatusPenanggungJawab());

            PenanggungJawabKegiatan penanggungJawabKegiatan
                    = new PenanggungJawabKegiatan();
            penanggungJawabKegiatan.setPenanggungJawabKegiatanId(id);
            penanggungJawabKegiatan.setStatusApproval(0);

            penanggungJawabKegiatanService.create(penanggungJawabKegiatan);
        }

        return new ResponseEntity<>(new CustomMessage("daftar penanggung jawab berhasil dibuat"), HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk mengambil data daftar penanggung jawab berdasarkan suatu kegiatan
     *
     * @param inputWrapper
     * @return daftar penanggung jawab
     */
    @RequestMapping(value = "/get-penanggung-jawab-kegiatan-by-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> getPenanggungJawabKegiatanByKegiatan(@RequestBody KegiatanWrapper inputWrapper) {
        LOGGER.info("get penanggung jawab kegiatan by kegiatan");

        List<PenanggungJawabWrapper> penanggungJawabWrappers
                = new ArrayList<>();

        List<QutPegawai> pegawaiUnitKerja
                = qutPegawaiService.getQutPegawaiByUnitKerja(inputWrapper.getKdUnitKerja());

        List<PenanggungJawabKegiatan> penanggungJawabKegiatanList
                = penanggungJawabKegiatanService.getByKegiatan(
                        inputWrapper.getKdUrusan(),
                        inputWrapper.getKdBidang(),
                        inputWrapper.getKdUnit(),
                        inputWrapper.getKdSub(),
                        inputWrapper.getTahun(),
                        inputWrapper.getKdProg(),
                        inputWrapper.getIdProg(),
                        inputWrapper.getKdKeg());

        CustomPegawaiCredential penanggungJawab;
        for (PenanggungJawabKegiatan penanggungJawabKegiatan
                : penanggungJawabKegiatanList) {
            penanggungJawab = null;

            for (QutPegawai qutPegawai
                    : pegawaiUnitKerja) {
                if (penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getNipPegawai()
                        .equals(qutPegawai.getNip())) {
                    penanggungJawab
                            = new CustomPegawaiCredential(
                                    qutPegawai.getNip(),
                                    qutPegawai.getNama(),
                                    qutPegawai.getGol(),
                                    qutPegawai.getPangkat(),
                                    qutPegawai.getKdJabatan(),
                                    qutPegawai.getJabatan(),
                                    qutPegawai.getKdUnitKerja(),
                                    qutPegawai.getUnitKerja(),
                                    qutPegawai.getGlrDpn(),
                                    qutPegawai.getGlrBlk());
                    break;
                }
            }

            penanggungJawabWrappers
                    .add(new PenanggungJawabWrapper(
                            penanggungJawab,
                            penanggungJawabKegiatan.getStatusPenanggungJawabKegiatan().getKdStatus(),
                            penanggungJawabKegiatan.getStatusPenanggungJawabKegiatan().getStatus(),
                            penanggungJawabKegiatan.getStatusApproval()
                    ));
        }


        return new ResponseEntity<Object>(penanggungJawabWrappers,HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk mengambil data daftar pegawai calon penanggung jawab
     *
     * @param inputWrapper
     * @return daftar pegawai
     */
    @RequestMapping(value = "/get-pegawai-penanggung-jawab-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> getPegawaiPenanggungJawabKegiatan(@RequestBody KegiatanWrapper inputWrapper) {
        LOGGER.info("get pegawai penanggung jawab kegiatan");

        List<PenanggungJawabKegiatan> penanggungJawabKegiatanList
                = penanggungJawabKegiatanService.getByKegiatan(
                                                inputWrapper.getKdUrusan(),
                                                inputWrapper.getKdBidang(),
                                                inputWrapper.getKdUnit(),
                                                inputWrapper.getKdSub(),
                                                inputWrapper.getTahun(),
                                                inputWrapper.getKdProg(),
                                                inputWrapper.getIdProg(),
                                                inputWrapper.getKdKeg());
        List<QutPegawaiWrapper> qutPegawaiWrappers = new ArrayList<>();
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(inputWrapper.getKdUnitKerja());
        List<TkdUnk> tkdUnkList
                = tkdUnkDao.findAll();

        boolean found;
        for (QutPegawai qutPegawai : qutPegawaiList) {
            found = false;
            for (PenanggungJawabKegiatan penanggungJawabKegiatan
                    : penanggungJawabKegiatanList) {
                if (penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getNipPegawai()
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

    /**
     *
     * service yang digunakan untuk mengambil daftar status penanggung jawab yang masih tersedia/belum diisi
     *
     * @param inputWrapper
     * @return daftar status penanggung jawab
     */
    @RequestMapping(value = "/get-status-penanggung-jawab-kegiatan-revisi", method = RequestMethod.POST)
    ResponseEntity<?> getStatusPenanggungJawabKegiatanRevisi(@RequestBody KegiatanWrapper inputWrapper) {
        LOGGER.info("get status penanggung jawab kegiatan revisi");

        List<PenanggungJawabKegiatan> penanggungJawabKegiatanList
                = penanggungJawabKegiatanService.getByKegiatan(
                                                inputWrapper.getKdUrusan(),
                                                inputWrapper.getKdBidang(),
                                                inputWrapper.getKdUnit(),
                                                inputWrapper.getKdSub(),
                                                inputWrapper.getTahun(),
                                                inputWrapper.getKdProg(),
                                                inputWrapper.getIdProg(),
                                                inputWrapper.getKdKeg());

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

                for (PenanggungJawabKegiatan penanggungJawabKegiatan
                        : penanggungJawabKegiatanList) {
                    if (penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdStatusPenanggungJawab()
                            .equals(spj.getKdStatus())) {
                        LOGGER.info("constraint "+penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdStatusPenanggungJawab());
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

    /**
     *
     * service yang digunakan untuk menghapus data penanggung jawab dalam suatu kegiatan
     *
     * @param inputWrapper
     * @return custom message
     */
    @RequestMapping(value = "/delete-penanggung-jawab-kegiatan", method = RequestMethod.POST)
    ResponseEntity<?> deletePenanggungJawabKegiatan(@RequestBody PenanggungJawabKegiatanInputWrapper inputWrapper) {
        LOGGER.info("delete penanggung jawab kegiatan");

        penanggungJawabKegiatanService
                .delete(new PenanggungJawabKegiatanId(
                            inputWrapper.getKdUrusan(),
                            inputWrapper.getKdBidang(),
                            inputWrapper.getKdUnit(),
                            inputWrapper.getKdSub(),
                            inputWrapper.getTahun(),
                            inputWrapper.getKdProg(),
                            inputWrapper.getIdProg(),
                            inputWrapper.getKdKeg(),
                            inputWrapper.getNipPegawai(),
                            inputWrapper.getKdStatusPenanggungJawab()));

        return new ResponseEntity<Object>(new CustomMessage("penanggung jawab berhasil dihapus"), HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk mengambil data kegiatan dari simda berdasarkan unit kerja
     *
     * constraint :
     * kd Unit Kerja
     *
     * @param kdUnitKerja
     * @return daftar kegiatan
     */
    @RequestMapping(value = "/get-organisasi-barjas-unit-kerja/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getPenanggungJawabKegiatanByUnitKerja(@PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get penanggung jawab kegiatan by unit kerja "+kdUnitKerja);

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(kdUnitKerja);

        List<KegiatanPenanggungJawabProjection> kegiatanList
                = penanggungJawabKegiatanService.getKegiatanByUnitKerja(unitKerjaKegiatan);
        List<TaKegiatan> taKegiatanList
                = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);

        List<TaKegiatanWrapper> kegiatanWrappers
                = new ArrayList<>();

        for (KegiatanPenanggungJawabProjection kegiatan
                : kegiatanList) {
            for (TaKegiatan taKegiatan
                    : taKegiatanList) {
                if (compareKegiatan(kegiatan, taKegiatan)) {
                    kegiatanWrappers
                            .add(new TaKegiatanWrapper(kegiatan.getKdUrusan(),
                                    kegiatan.getKdBidang(),
                                    kegiatan.getKdUnit(),
                                    kegiatan.getKdSub(),
                                    kegiatan.getTahun(),
                                    kegiatan.getKdProg(),
                                    kegiatan.getIdProg(),
                                    kegiatan.getKdKeg(),
                                    taKegiatan.getKetKegiatan(),
                                    taKegiatan.getLokasi(),
                                    taKegiatan.getKelompokSasaran(),
                                    taKegiatan.getStatusKegiatan(),
                                    taKegiatan.getPaguAnggaran(),
                                    taKegiatan.getWaktuPelaksanaan(),
                                    taKegiatan.getKdSumber()));

                    break;
                }
            }
        }

        return new ResponseEntity<Object>(kegiatanWrappers, HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk mengambil data kegiatan dari simda berdasarkan nip
     *
     * constraint :
     * kd Unit Kerja
     *
     * @param kdJabatan
     * @return daftar kegiatan
     */
    @RequestMapping(value = "/get-organisasi-barjas-jabatan/{kdJabatan}", method = RequestMethod.GET)
    ResponseEntity<?> getPenanggungJawabKegiatanByJabatan(@PathVariable("kdJabatan") String kdJabatan) {
        LOGGER.info("get penanggung jawab kegiatan by pegawai");

        String kdUnitKerja = kdJabatan.substring(0,3);

        LOGGER.warn("result kdunitkerja "+kdUnitKerja+" from kdJabatan "+kdJabatan);

        UnitKerjaKegiatan unitKerjaKegiatan
                = unitKerjaKegiatanService.findByKdUnitKerja(kdUnitKerja);

        List<QutPegawaiClone> qutPegawaiCloneList
                = qutPegawaiService.getQutPegawaiByKdJabatan(kdJabatan);

        List<KegiatanPenanggungJawabProjection> kegiatanList
                = new ArrayList<>();

        for (QutPegawaiClone qutPegawaiClone : qutPegawaiCloneList) {
            kegiatanList.addAll(penanggungJawabKegiatanService.getKegiatanProjectionByPegawai(qutPegawaiClone.getNip()));
        }
//        kegiatanList
//                = penanggungJawabKegiatanService.getKegiatanProjectionByPegawai(qutPegawaiCloneList.get(0).getNip());

        List<TaKegiatan> taKegiatanList
                = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);
        List<UrtugKegiatan> urtugKegiatanList
                = urtugKegiatanService.findByJabatan(kdJabatan);

        List<TaKegiatanWrapper> kegiatanWrappers
                = new ArrayList<>();

        boolean found;
        for (KegiatanPenanggungJawabProjection kegiatan
                : kegiatanList) {
            found = false;

            for (UrtugKegiatan urtugKegiatan
                    : urtugKegiatanList) {
                if (kegiatan.getKdProg().equals(urtugKegiatan.getUrtugKegiatanId().getKdProg())
                        && kegiatan.getIdProg().equals(urtugKegiatan.getUrtugKegiatanId().getIdProg())
                        && kegiatan.getKdKeg().equals(urtugKegiatan.getUrtugKegiatanId().getKdKeg())) {
                    found = true;
                    break;
                }

            }

            // jika kegiatan pernah dipasang dengan salah satu urtug dalam suatu jabatan
            // jangan diberikan lagi
            if (!found) {
                for (TaKegiatan taKegiatan
                        : taKegiatanList) {
                    if (compareKegiatan(kegiatan, taKegiatan)) {
                        kegiatanWrappers
                                .add(new TaKegiatanWrapper(kegiatan.getKdUrusan(),
                                        kegiatan.getKdBidang(),
                                        kegiatan.getKdUnit(),
                                        kegiatan.getKdSub(),
                                        kegiatan.getTahun(),
                                        kegiatan.getKdProg(),
                                        kegiatan.getIdProg(),
                                        kegiatan.getKdKeg(),
                                        taKegiatan.getKetKegiatan(),
                                        taKegiatan.getLokasi(),
                                        taKegiatan.getKelompokSasaran(),
                                        taKegiatan.getStatusKegiatan(),
                                        taKegiatan.getPaguAnggaran(),
                                        taKegiatan.getWaktuPelaksanaan(),
                                        taKegiatan.getKdSumber()));

                        break;
                    }
                }
            }

        }

        return new ResponseEntity<>(kegiatanWrappers, HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan melakukan proses approval terhadap calon ajuan kontrak kerja dari pegawai
     * digunakan oleh pegawai yang akan membuat kontrak kerja
     *
     * @param inputWrappers, merupakan daftar dpa yang disetujui oleh pegawai.
     * @return custom message
     */
    @RequestMapping(value = "/approve-penanggung-jawab-kegiatan", method = RequestMethod.PUT)
    ResponseEntity<?> approvePenanggungJawabKegiatan(@RequestBody List<PenanggungJawabKegiatanInputWrapper> inputWrappers) {
        LOGGER.info("approve penanggung jawab kegiatan");

        List<PenanggungJawabKegiatan> penanggungJawabKegiatanList
                = penanggungJawabKegiatanService.getByPegawai(inputWrappers.get(0).getKdUrusan(),
                                                                inputWrappers.get(0).getKdBidang(),
                                                                inputWrappers.get(0).getKdUnit(),
                                                                inputWrappers.get(0).getKdSub(),
                                                                inputWrappers.get(0).getTahun(),
                                                                inputWrappers.get(0).getKdProg(),
                                                                inputWrappers.get(0).getIdProg(),
                                                                inputWrappers.get(0).getKdKeg(),
                                                                inputWrappers.get(0).getNipPegawai());

        boolean isMatch;
        for (PenanggungJawabKegiatan penanggungJawabKegiatan
                : penanggungJawabKegiatanList) {
            isMatch = false;

            for (PenanggungJawabKegiatanInputWrapper obj
                    : inputWrappers) {
                if (penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdProg().equals(obj.getKdProg())
                        && penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getIdProg().equals(obj.getIdProg())
                        && penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdKeg().equals(obj.getKdKeg())) {

                    penanggungJawabKegiatan.setStatusApproval(1);

                    isMatch = true;
                    break;
                }
            }

            //set status ditolak untuk setiap data yang belum di approve
            if (!isMatch && penanggungJawabKegiatan.getStatusApproval() == 0) {
                penanggungJawabKegiatan.setStatusApproval(2);
            }

            penanggungJawabKegiatanService.create(penanggungJawabKegiatan);
        }

        return new ResponseEntity<Object>(new CustomMessage("dpa sudah disetujui"), HttpStatus.OK);
    }

    /**
     *
     *
     *
     * @return
     */
    @RequestMapping(value = "/get-daftar-kegiatan-penanggung-jawab/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getDaftarKegiatanPenanggungJawab(@PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get daftar kegiatan penanggung jawab");

        UnitKerjaKegiatan unitKerjaKegiatan = unitKerjaKegiatanDao.findByKdUnitKerja(kdUnitKerja);

        LOGGER.info(
                "kdUrusan : "+unitKerjaKegiatan.getKdUrusan()+
                        ", kdBidang : "+unitKerjaKegiatan.getKdBidang()+
                        ", kdUnit : "+unitKerjaKegiatan.getKdUnit());

        List<KegiatanPenanggungJawabWrapper> taKegiatanWrapperList
                = new ArrayList<>();
        List<TaKegiatan> taKegiatanList
                = taKegiatanDao.findAllByKdUnitKerja(unitKerjaKegiatan.getKdUrusan(),
                                                    unitKerjaKegiatan.getKdBidang(),
                                                    unitKerjaKegiatan.getKdUnit());
        List<PenanggungJawabKegiatan> kegiatanPenanggungJawabProjectionList
                = penanggungJawabKegiatanService.getKegiatanUnitKerja(unitKerjaKegiatan);

        boolean bendaharaExist = false,
                ppbjExist = false,
                penggunaAnggaranExist = false,
                pelaksanaAdministrasiExist = false,
                pphpExist = false,
                ppkExist = false,
                pptkExist = false,
                timTeknisExist = false;

        for (TaKegiatan taKegiatan : taKegiatanList) {
            if (!taKegiatan.getKetKegiatan().equals("Non Kegiatan")) {
                bendaharaExist = false;
                ppbjExist = false;
                penggunaAnggaranExist = false;
                pelaksanaAdministrasiExist = false;
                pphpExist = false;
                ppkExist = false;
                pptkExist = false;
                timTeknisExist = false;

                //perlu improvisasi algoritma ini
                for (PenanggungJawabKegiatan kegiatanProjection
                        : kegiatanPenanggungJawabProjectionList) {
                    if (compareKegiatan(kegiatanProjection, taKegiatan)) {
                        switch (kegiatanProjection.getStatusPenanggungJawabKegiatan().getKdStatus()) {
                            case "1513324189794" : bendaharaExist = true; break;
                            case "1513484156490" : ppbjExist = true; break;
                            case "1513485761316" : penggunaAnggaranExist = true; break;
                            case "1516936818788" : pelaksanaAdministrasiExist = true; break;
                            case "1523326110773" : pphpExist = true; break;
                            case "ST001" : ppkExist = true; break;
                            case "ST002" : pptkExist = true; break;
                            case "ST003" : timTeknisExist = true; break;
                            default: LOGGER.error("kode status penanggung jawab not found");
                        }

                    }
                }

                taKegiatanWrapperList
                        .add(new KegiatanPenanggungJawabWrapper(
                                taKegiatan.getTaKegiatanId().getKdUrusan(),
                                taKegiatan.getTaKegiatanId().getKdBIdang(),
                                taKegiatan.getTaKegiatanId().getKdUnit(),
                                taKegiatan.getTaKegiatanId().getKdSub(),
                                taKegiatan.getTaKegiatanId().getTahun(),
                                taKegiatan.getTaKegiatanId().getKdProg(),
                                taKegiatan.getTaKegiatanId().getIdProg(),
                                taKegiatan.getTaKegiatanId().getKdKegiatan(),
                                taKegiatan.getKetKegiatan(),
                                taKegiatan.getLokasi(),
                                taKegiatan.getKelompokSasaran(),
                                taKegiatan.getStatusKegiatan(),
                                taKegiatan.getPaguAnggaran(),
                                taKegiatan.getWaktuPelaksanaan(),
                                taKegiatan.getKdSumber(),
                                bendaharaExist,
                                ppbjExist,
                                penggunaAnggaranExist,
                                pelaksanaAdministrasiExist,
                                pphpExist,
                                ppkExist,
                                pptkExist,
                                timTeknisExist));

            }

        }

        return new ResponseEntity<>(taKegiatanWrapperList, HttpStatus.OK);
    }

    /**
     *
     *
     *
     * @param kdUnitKerja
     * @return
     */
    @RequestMapping(value = "/get-sk-barjas/{kdUnitKerja}", method = RequestMethod.GET)
    ResponseEntity<?> getSKBarjas(
            @PathVariable("kdUnitKerja") String kdUnitKerja) {
        LOGGER.info("get sk barjas");

        UnitKerjaKegiatan unitKerjaKegiatan = unitKerjaKegiatanDao.findByKdUnitKerja(kdUnitKerja);

        LOGGER.info(
                "kdUrusan : "+unitKerjaKegiatan.getKdUrusan()+
                        ", kdBidang : "+unitKerjaKegiatan.getKdBidang()+
                        ", kdUnit : "+unitKerjaKegiatan.getKdUnit());
        List<TaKegiatan> taKegiatanList
                = taKegiatanDao.findAllByKdUnitKerja(
                        unitKerjaKegiatan.getKdUrusan(),
                        unitKerjaKegiatan.getKdBidang(),
                        unitKerjaKegiatan.getKdUnit());

        PejabatBarjasPPUKWrapper pejabatBarjasPPUKWrapper = new PejabatBarjasPPUKWrapper();
        List<PejabatBarjasPPKWrapper> pejabatBarjasPPKWrappers = new ArrayList<>();
        List<PejabatBarjasPPTKWrapper> pejabatBarjasPPTKWrappers = new ArrayList<>();

        List<PenanggungJawabKegiatan> kegiatanUnitKerjaList
                = penanggungJawabKegiatanService.getKegiatanUnitKerja(unitKerjaKegiatan);

        //ambil pejabat PPUK
        for (PenanggungJawabKegiatan kegiatan : kegiatanUnitKerjaList) {
            if (kegiatan.getPenanggungJawabKegiatanId().getKdStatusPenanggungJawab()
                    .equals("1513324189794")) {
                QutPegawai penanggungJawab
                        = qutPegawaiService.getQutPegawai(kegiatan.getPenanggungJawabKegiatanId().getNipPegawai());

                pejabatBarjasPPUKWrapper.setNip(penanggungJawab.getNip());
                pejabatBarjasPPUKWrapper.setNama(penanggungJawab.getNama());
                pejabatBarjasPPUKWrapper.setGelarDepan(penanggungJawab.getGlrDpn());
                pejabatBarjasPPUKWrapper.setGelarBelakang(penanggungJawab.getGlrBlk());
                pejabatBarjasPPUKWrapper.setJabatan(penanggungJawab.getJabatan());
                pejabatBarjasPPUKWrapper.setJabatanBarjas("Pejabat Penata Usahaan Keuangan (PPUK)");

                break;
            }
        }
        //ambil daftar pejabat PPK
        List<String> isPejabatAlreadyExist = new ArrayList<>();
        boolean found = false;
        for (PenanggungJawabKegiatan kegiatan : kegiatanUnitKerjaList) {
            if (kegiatan.getPenanggungJawabKegiatanId().getKdStatusPenanggungJawab()
                    .equals("ST001")) {
                found = false;
                for (String obj : isPejabatAlreadyExist) {
                    if (obj.equals(kegiatan.getPenanggungJawabKegiatanId().getNipPegawai())) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    QutPegawai penanggungJawab
                            = qutPegawaiService.getQutPegawai(kegiatan.getPenanggungJawabKegiatanId().getNipPegawai());

                    PejabatBarjasPPKWrapper pejabatBarjasPPKWrapper = new PejabatBarjasPPKWrapper();

                    pejabatBarjasPPKWrapper.setNip(penanggungJawab.getNip());
                    pejabatBarjasPPKWrapper.setNama(penanggungJawab.getNama());
                    pejabatBarjasPPKWrapper.setGelarDepan(penanggungJawab.getGlrDpn());
                    pejabatBarjasPPKWrapper.setGelarBelakang(penanggungJawab.getGlrBlk());
                    pejabatBarjasPPKWrapper.setJabatan(penanggungJawab.getJabatan());
                    pejabatBarjasPPKWrapper.setJabatanBarjas("Pejabat Pembuat Komitmen (PPK)");

                    pejabatBarjasPPKWrappers.add(pejabatBarjasPPKWrapper);

                    isPejabatAlreadyExist.add(penanggungJawab.getNip());
                }

            }
        }
        //ambil pejabat PPTK beserta kegiatannya
        isPejabatAlreadyExist.clear();
        for (PenanggungJawabKegiatan kegiatan : kegiatanUnitKerjaList) {
            if (kegiatan.getPenanggungJawabKegiatanId().getKdStatusPenanggungJawab()
                    .equals("ST002")) {
                found = false;

                for (String obj : isPejabatAlreadyExist) {
                    if (obj.equals(kegiatan.getPenanggungJawabKegiatanId().getNipPegawai())) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    QutPegawai penanggungJawab
                            = qutPegawaiService.getQutPegawai(kegiatan.getPenanggungJawabKegiatanId().getNipPegawai());

                    PejabatBarjasPPTKWrapper pejabatBarjasPPTKWrapper = new PejabatBarjasPPTKWrapper();

                    pejabatBarjasPPTKWrapper.setNip(penanggungJawab.getNip());
                    pejabatBarjasPPTKWrapper.setNama(penanggungJawab.getNama());
                    pejabatBarjasPPTKWrapper.setGelarDepan(penanggungJawab.getGlrDpn());
                    pejabatBarjasPPTKWrapper.setGelarBelakang(penanggungJawab.getGlrBlk());
                    pejabatBarjasPPTKWrapper.setJabatan(penanggungJawab.getJabatan());
                    pejabatBarjasPPTKWrapper.setJabatanBarjas("Pejabat Pelaksana Teknis Kegiatan (PPTK)");

                    List<TaKegiatanWrapper> taKegiatanWrappers = new ArrayList<>();
                    for (PenanggungJawabKegiatan kegiatanPPTK : kegiatanUnitKerjaList) {
                        if (kegiatanPPTK.getPenanggungJawabKegiatanId().getNipPegawai()
                                .equals(penanggungJawab.getNip())) {

                            for (TaKegiatan taKegiatan
                                    : taKegiatanList) {
                                if (compareKegiatan(kegiatanPPTK, taKegiatan)) {
                                    taKegiatanWrappers
                                            .add(new TaKegiatanWrapper(
                                                    kegiatanPPTK.getPenanggungJawabKegiatanId().getKdUrusan(),
                                                    kegiatanPPTK.getPenanggungJawabKegiatanId().getKdBidang(),
                                                    kegiatanPPTK.getPenanggungJawabKegiatanId().getKdUnit(),
                                                    kegiatanPPTK.getPenanggungJawabKegiatanId().getKdSub(),
                                                    kegiatanPPTK.getPenanggungJawabKegiatanId().getTahun(),
                                                    kegiatanPPTK.getPenanggungJawabKegiatanId().getKdProg(),
                                                    kegiatanPPTK.getPenanggungJawabKegiatanId().getIdProg(),
                                                    kegiatanPPTK.getPenanggungJawabKegiatanId().getKdKeg(),
                                                    taKegiatan.getKetKegiatan(),
                                                    taKegiatan.getLokasi(),
                                                    taKegiatan.getKelompokSasaran(),
                                                    taKegiatan.getStatusKegiatan(),
                                                    taKegiatan.getPaguAnggaran(),
                                                    taKegiatan.getWaktuPelaksanaan(),
                                                    taKegiatan.getKdSumber()));

                                    break;
                                }
                            }

                        }
                    }
                    pejabatBarjasPPTKWrapper.setKegiatanWrapperList(taKegiatanWrappers);

                    pejabatBarjasPPTKWrappers.add(pejabatBarjasPPTKWrapper);

                    isPejabatAlreadyExist.add(penanggungJawab.getNip());
                }
            }
        }
        //pasang PPK dengan PPTK
        for (PejabatBarjasPPKWrapper pejabatPPK : pejabatBarjasPPKWrappers) {
            List<PejabatBarjasPPTKWrapper> pejabatBarjasPPTKWrappersTemp = new ArrayList<>();

            for (PenanggungJawabKegiatan kegiatan : kegiatanUnitKerjaList) {
                if (kegiatan.getPenanggungJawabKegiatanId().getNipPegawai()
                        .equals(pejabatPPK.getNip())) {

                    boolean pptkAlreadyExist;
                    for (PejabatBarjasPPTKWrapper pejabatPPTK : pejabatBarjasPPTKWrappers) {
                        pptkAlreadyExist = false;

                        for (PejabatBarjasPPTKWrapper pejabatPPTKTemp : pejabatBarjasPPTKWrappersTemp) {
                            if (pejabatPPTK.getNip().equals(pejabatPPTKTemp.getNip())) {
                                pptkAlreadyExist = true;
                                break;
                            }
                        }

                        if (!pptkAlreadyExist) {
                            for (TaKegiatanWrapper kegiatanPejabatPPTK : pejabatPPTK.getKegiatanWrapperList()) {
                                if (kegiatan.getPenanggungJawabKegiatanId().getKdProg().equals(kegiatanPejabatPPTK.getKdProg())
                                        && kegiatan.getPenanggungJawabKegiatanId().getIdProg().equals(kegiatanPejabatPPTK.getIdProg())
                                        && kegiatan.getPenanggungJawabKegiatanId().getKdKeg().equals(kegiatanPejabatPPTK.getKdKegiatan())) {
                                    pejabatBarjasPPTKWrappersTemp.add(pejabatPPTK);

                                }
                            }
                        }

                    }

                }
            }

            pejabatPPK.setPejabatBarjasPPTKList(pejabatBarjasPPTKWrappersTemp);
        }

        //pasang PPUK dengan PPK
        pejabatBarjasPPUKWrapper.setPejabatBarjasPPKList(pejabatBarjasPPKWrappers);

        return new ResponseEntity<>(pejabatBarjasPPUKWrapper, HttpStatus.OK);

    }

    /**
     *
     * method ini digunakan untuk mengenerate pengguna anggaran pada setiap kegiatan dalam suatu unit kerja
     * yang diperbolehkan menjadi pengguna anggaran hanyalah kepala dinas/kepala unit kerja
     *
     * @param kdUnitKerja
     */
    private void generatePenggunaAnggaranKegiatan(String kdUnitKerja, List<TaKegiatanWrapper> kegiatanWrappers) {
        LOGGER.info("generate pengguna anggaran kegiatan unit kerja "+kdUnitKerja);

        String kdJabatanKepalaUnitKerja;

        //cek jika kdunitkerja merupakan unit kerja kecamatan
        if (kdUnitKerja.substring(0,2).equals("71")) {
            kdJabatanKepalaUnitKerja = generateKdJabatanKepalaCamat(kdUnitKerja);
        } else {
            kdJabatanKepalaUnitKerja = kdUnitKerja.concat("0000A");
        }

        List<QutPegawaiClone> kepalaUnitKerja = qutPegawaiService.getQutPegawaiByKdJabatan(kdJabatanKepalaUnitKerja);

        for (TaKegiatanWrapper inputWrapper : kegiatanWrappers) {
            PenanggungJawabKegiatanId id
                    = new PenanggungJawabKegiatanId(
                        inputWrapper.getKdUrusan(),
                        inputWrapper.getKdBIdang(),
                        inputWrapper.getKdUnit(),
                        inputWrapper.getKdSub(),
                        inputWrapper.getTahun(),
                        inputWrapper.getKdProg(),
                        inputWrapper.getIdProg(),
                        inputWrapper.getKdKegiatan(),
                        kepalaUnitKerja.get(0).getNip(),
                        "1513485761316");

            PenanggungJawabKegiatan penanggungJawabKegiatan
                    = new PenanggungJawabKegiatan();
            penanggungJawabKegiatan.setPenanggungJawabKegiatanId(id);
            penanggungJawabKegiatan.setStatusApproval(0);

            penanggungJawabKegiatanService.create(penanggungJawabKegiatan);

        }

    }

    private String generateKdJabatanKepalaCamat(String kdUnitKerjaKecamatan) {
        return tkdJabatanService.getCamatUnitKerja(kdUnitKerjaKecamatan).getJabatan();
    }

    /**
     *
     * service yang digunakan untuk membandingkan kegiatan pada simda dengan kegiatan pada ekinerja
     *
     * @param kegiatan
     * @param taKegiatan
     * @return boolean false atau true
     */
    private boolean compareKegiatan(@NotNull KegiatanPenanggungJawabProjection kegiatan,
                                    @NotNull TaKegiatan taKegiatan) {
        if (kegiatan.getKdUrusan().equals(taKegiatan.getTaKegiatanId().getKdUrusan())
                && kegiatan.getKdBidang().equals(taKegiatan.getTaKegiatanId().getKdBIdang())
                && kegiatan.getKdUnit().equals(taKegiatan.getTaKegiatanId().getKdUnit())
                && kegiatan.getKdSub().equals(taKegiatan.getTaKegiatanId().getKdSub())
                && kegiatan.getTahun().equals(taKegiatan.getTaKegiatanId().getTahun())
                && kegiatan.getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg())
                && kegiatan.getIdProg().equals(taKegiatan.getTaKegiatanId().getIdProg())
                && kegiatan.getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {
            return true;
        }

        return false;
    }

    private boolean compareKegiatan(@NotNull PenanggungJawabKegiatan kegiatan,
                                    @NotNull TaKegiatan taKegiatan) {
        if (kegiatan.getPenanggungJawabKegiatanId().getKdUrusan().equals(taKegiatan.getTaKegiatanId().getKdUrusan())
                && kegiatan.getPenanggungJawabKegiatanId().getKdBidang().equals(taKegiatan.getTaKegiatanId().getKdBIdang())
                && kegiatan.getPenanggungJawabKegiatanId().getKdUnit().equals(taKegiatan.getTaKegiatanId().getKdUnit())
                && kegiatan.getPenanggungJawabKegiatanId().getKdSub().equals(taKegiatan.getTaKegiatanId().getKdSub())
                && kegiatan.getPenanggungJawabKegiatanId().getTahun().equals(taKegiatan.getTaKegiatanId().getTahun())
                && kegiatan.getPenanggungJawabKegiatanId().getKdProg().equals(taKegiatan.getTaKegiatanId().getKdProg())
                && kegiatan.getPenanggungJawabKegiatanId().getIdProg().equals(taKegiatan.getTaKegiatanId().getIdProg())
                && kegiatan.getPenanggungJawabKegiatanId().getKdKeg().equals(taKegiatan.getTaKegiatanId().getKdKegiatan())) {
            return true;
        }

        return false;
    }


}

//@RequestMapping(value = "/get-penanggung-jawab-kegiatan/{kdUnitKerja}", method = RequestMethod.GET)
//    ResponseEntity<?> getPenanggungJawabKegiatan(@PathVariable("kdUnitKerja") String kdUnitKerja) {
//        LOGGER.info("get penanggung jawab kegiatan "+kdUnitKerja);
//
//        List<PenanggungJawabWrapper> penanggungJawabKegiatanWrapperList
//                = new ArrayList<>();
//
//        List<QutPegawai> pegawaiUnitKerja
//                = qutPegawaiService.getQutPegawaiByUnitKerja(kdUnitKerja);
//        List<StatusPenanggungJawabKegiatan> statusPenanggungJawabKegiatanList
//                = statusPenanggungJawabKegiatanService.getAll();
//
//        UnitKerjaKegiatan unitKerjaKegiatan
//                = unitKerjaKegiatanService.findByKdUnitKerja(kdUnitKerja);
//        List<TaKegiatan> taKegiatanList
//                = taKegiatanService.findByUnitKerja(unitKerjaKegiatan);
//        List<PenanggungJawabKegiatan> penanggungJawabKegiatanList
//                = penanggungJawabKegiatanService.getByUnitKerja(
//                        unitKerjaKegiatan.getKdUrusan(),
//                        unitKerjaKegiatan.getKdBidang(),
//                        unitKerjaKegiatan.getKdUnit());
//
//        CustomPegawaiCredential penanggungJawab;
//        String statusPenanggungJawab = "";
//        String keteranganKegiatan = "";
//        for (PenanggungJawabKegiatan penanggungJawabKegiatan
//                : penanggungJawabKegiatanList) {
//
//            penanggungJawab = null;
//
//            for (QutPegawai qutPegawai
//                    : pegawaiUnitKerja) {
//                if (penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getNipPegawai()
//                        .equals(qutPegawai.getNip())) {
//                    penanggungJawab
//                            = new CustomPegawaiCredential(
//                                    qutPegawai.getNip(),
//                                    qutPegawai.getNama(),
//                                    qutPegawai.getGol(),
//                                    qutPegawai.getPangkat(),
//                                    qutPegawai.getKdJabatan(),
//                                    qutPegawai.getJabatan(),
//                                    qutPegawai.getKdUnitKerja(),
//                                    qutPegawai.getUnitKerja(),
//                                    qutPegawai.getGlrDpn(),
//                                    qutPegawai.getGlrBlk());
//                    break;
//                }
//            }
//
//            for (StatusPenanggungJawabKegiatan statusPenanggungJawabKegiatan
//                    : statusPenanggungJawabKegiatanList) {
//                if (penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdStatusPenanggungJawab()
//                        .equals(statusPenanggungJawabKegiatan.getKdStatus())) {
//                    statusPenanggungJawab = statusPenanggungJawabKegiatan.getStatus();
//                    break;
//                }
//            }
//
//            for (TaKegiatan taKegiatan
//                    : taKegiatanList) {
//                if (taKegiatan.getTaKegiatanId().getKdUrusan().equals(penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdUrusan())
//                        && taKegiatan.getTaKegiatanId().getKdBIdang().equals(penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdBidang())
//                        && taKegiatan.getTaKegiatanId().getKdUnit().equals(penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdUnit())) {
//                    keteranganKegiatan = taKegiatan.getKetKegiatan();
//                    break;
//                }
//            }
//
//            penanggungJawabKegiatanWrapperList
//                    .add(new PenanggungJawabWrapper(
//                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdUrusan(),
//                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdBidang(),
//                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdUnit(),
//                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdSub(),
//                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getTahun(),
//                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdProg(),
//                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getIdProg(),
//                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdKeg(),
//                            keteranganKegiatan,
//                            penanggungJawab,
//                            penanggungJawabKegiatan.getPenanggungJawabKegiatanId().getKdStatusPenanggungJawab(),
//                            statusPenanggungJawab,
//                            penanggungJawabKegiatan.getStatusApproval()
//                    ));
//        }
//
//        return new ResponseEntity<Object>(penanggungJawabKegiatanWrapperList, HttpStatus.OK);
//    }
