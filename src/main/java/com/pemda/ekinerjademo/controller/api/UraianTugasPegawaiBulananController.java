package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TkdUnkCloneDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.util.exception.EkinerjaObjConverter;
import com.pemda.ekinerjademo.wrapper.input.AjuanSKPKasieWrapper;
import com.pemda.ekinerjademo.wrapper.input.TargetSKPbulanan;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasPegawaiBulananInputWrapper;
import com.pemda.ekinerjademo.wrapper.input.UrtugBulananIdInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class UraianTugasPegawaiBulananController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UraianTugasPegawaiBulananController.class);

    @Autowired
    private UraianTugasPegawaiBulananService urtugBulananService;
    @Autowired
    private QutPegawaiCloneService qutPegawaiService;
    @Autowired private UraianTugasJabatanJenisUrtugService uraianTugasJabatanJenisUrtugService;
    @Autowired private PejabatPenilaiDinilaiService pejabatPenilaiDinilaiService;

    //    @Autowired private TkdUnkDao tkdUnkDao;
    @Autowired private TkdUnkCloneDao tkdUnkDao;
    @Autowired private TemplateLainService templateLainService;

    /**
     *
     * service yang digunakan untuk membuat daftar ajuan kontrak bulanan pegawai
     * dibuat oleh kasie/eselon 4
     *
     * @return
     */
    @RequestMapping(value = "/create-daftar-urtug-pegawai-bulanan-segitiga", method = RequestMethod.POST)
    public ResponseEntity<?> createSegitiga(
            @RequestBody AjuanSKPKasieWrapper ajuanSKPKasieWrapper) {
        LOGGER.info("create urtug pegawai bulanan");

//        urtugBulananService.create(inputWrapperList, 0);
        /**
         * membuat kontrak kerjanya sekaligus menambahkan uraian tugas untuk skp atasannya hingga kadis
         */
        QutPegawai pegawai
                = qutPegawaiService.getQutPegawai(ajuanSKPKasieWrapper.getDaftarUrtugSKPkasie().get(0).getNipPegawai());
        createUraianTugasRecursive(pegawai, ajuanSKPKasieWrapper.getDaftarUrtugSKPkasie());
        /**
         * membuat skp untuk pelaksana dari kasie
         */
        urtugBulananService.create(ajuanSKPKasieWrapper.getDaftarUrtugSKPPelaksanakasie(), 1);

        return new ResponseEntity<>(new CustomMessage("ajuan berhasil dibuat"), HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk membuat daftar ajuan kontrak bulanan pegawai
     * dibuat oleh kasie/eselon 4
     *
     * @return
     */
    @RequestMapping(value = "/create-daftar-urtug-pegawai-bulanan", method = RequestMethod.POST)
    public ResponseEntity<?> create(
            @RequestBody List<UraianTugasPegawaiBulananInputWrapper> inputWrapperList) {
        LOGGER.info("create urtug pegawai bulanan");

        urtugBulananService.create(inputWrapperList, 1);

        return new ResponseEntity<>(new CustomMessage("ajuan berhasil dibuat"), HttpStatus.OK);
    }

    @RequestMapping(value = "/update-target-skp-bulanan-pegawai", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTargetSKPBulananPegawai(@RequestBody TargetSKPbulanan targetSKPbulananBaru) {
        LOGGER.info("update target skp bulanan pegawai");

        urtugBulananService.updateTargetSKPBulananPegawai(
                new UraianTugasPegawaiBulananId(targetSKPbulananBaru.getKdUrtug(),
                        targetSKPbulananBaru.getKdJabatan(),
                        targetSKPbulananBaru.getKdJenisUrtug(),
                        targetSKPbulananBaru.getTahunUrtug(),
                        targetSKPbulananBaru.getBulanUrtug(),
                        targetSKPbulananBaru.getNipPegawai()),
                targetSKPbulananBaru.getTargetKuantitasBaru());

        return new ResponseEntity<>(new CustomMessage("target uraian tugas berhasi diubah"), HttpStatus.OK);
    }

    /**
     *
     * service yang digunakan untuk mengapprove ajuan kontrak bawahan
     *
     * @return
     */
    @RequestMapping(value = "/approval-urtug-bulanan-non-dpa-pegawai", method = RequestMethod.PUT)
    public ResponseEntity<?> approveAjuanKontrakBulananBawahan(
            @RequestBody List<UraianTugasPegawaiBulananInputWrapper> uraianTugasPegawaiBulananInputWrappers) {
        LOGGER.info("approve ajuan kontrak bulanan bawahan");

        if (uraianTugasPegawaiBulananInputWrappers.isEmpty())
            return new ResponseEntity<Object>(new CustomMessage("Uraian tugas yang dipilih telah disetujui"), HttpStatus.OK);

        //get current uraian tugas non dpa pegawai in database
        List<UraianTugasPegawaiBulanan> uraianTugasPegawaiBulanans
                = urtugBulananService.getByNip(
                    uraianTugasPegawaiBulananInputWrappers
                        .get(0)
                        .getNipPegawai());

        for (UraianTugasPegawaiBulananInputWrapper urtugPegawaiAtasan : uraianTugasPegawaiBulananInputWrappers){
            boolean found = false;
            for (UraianTugasPegawaiBulanan urtugPegawai : uraianTugasPegawaiBulanans){
                if (urtugPegawaiAtasan.getKdUrtug()
                        .equals(urtugPegawai.getUraianTugasPegawaiBulananId().getKdUrtug())) {
                    urtugBulananService.approveAjuanKontrakBulanan(urtugPegawaiAtasan);

                    found = true;
                    break;
                }
            }
            //jika misalkan asalnya ditolak bawahan tapi ditambahkan oleh atasan
            if (!found) {
                if (urtugPegawaiAtasan.getStatusApproval() == 1) {
                    urtugBulananService.create(
                            urtugPegawaiAtasan,
                            1);
                }
            }
        }

        return new ResponseEntity<>(new CustomMessage("Uraian tugas yang dipilih telah disetujui"), HttpStatus.OK);
    }

    /**
     *
     * gak pakai parameter tahun??
     *
     * @return
     */
    @RequestMapping(value = "/get-uraian-tugas-pegawai-bulanan-by-nip/{nipPegawai}/{bulanUrtug}", method = RequestMethod.GET)
    public ResponseEntity<?> getUraianTugasPegawaiBulananByNip(
            @PathVariable("nipPegawai") String nipPegawai,
            @PathVariable("bulanUrtug") Integer bulanUrtug) {
        LOGGER.info("get uraian tugas pegawai bulanan by nip "+nipPegawai);

        List<UraianTugasPegawaiBulanan> uraianTugasPegawaiBulanans
                = urtugBulananService.getByNip(nipPegawai, bulanUrtug);
        List<UraianTugasPegawaiBulananWrapper> uraianTugasPegawaiBulananWrapperList
                = new ArrayList<>();

        for (UraianTugasPegawaiBulanan urtug
                : uraianTugasPegawaiBulanans) {
            if (urtug.getStatusApproval() == 1) {
                uraianTugasPegawaiBulananWrapperList
                        .add(new UraianTugasPegawaiBulananWrapper(
                                urtug.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                urtug.getUraianTugasPegawaiBulananId().getKdUrtug(),
                                urtug.getUraianTugasPegawaiBulananId().getKdJabatan(),
                                urtug.getUraianTugasPegawaiBulananId().getKdJenisUrtug(),
                                urtug.getUraianTugasPegawaiBulananId().getTahunUrtug(),
                                bulanUrtug,
                                urtug.getUraianTugasPegawaiBulananId().getNipPegawai(),
                                urtug.getTargetKuantitas(),
                                urtug.getTargetSatuanKuantitas(),
                                urtug.getTargetKualitas(),
                                urtug.getWaktu(),
                                Long.valueOf(urtug.getTargetKuantitas() * urtug.getWaktu()),
                                urtug.getBiaya(),
                                "",
                                urtug.getStatusApproval(),
                                urtug.getRealisasiKuantitas(),
                                urtug.getRealisasiKualitas(),
                                urtug.getRealisasiWaktu()));
            }

        }

        return new ResponseEntity<>(uraianTugasPegawaiBulananWrapperList, HttpStatus.OK);
    }

    /**
     *
     * service yang dibuat untuk melihat ajuan uraian tugas bulanan bawahan oleh penilai
     *
     * @return
     */
    @RequestMapping(value = "/get-uraian-tugas-bulanan-by-penilai/{kdUnitKerja}/{nipPenilai}/{bulanPengajuan}", method = RequestMethod.GET)
    public ResponseEntity<?> getUraianTugasBulananByPenilai(@PathVariable("kdUnitKerja") String kdUnitKerja,
                                                            @PathVariable("nipPenilai") String nipPenilai,
                                                            @PathVariable("bulanPengajuan") Integer bulanPengajuan) {
        LOGGER.info("get ajuan uraian tugas bulanan by penilai");

        List<UraianTugasPegawaiBulanan> uraianTugasPegawaiBulanans
                = urtugBulananService.getByUnitKerja(kdUnitKerja, bulanPengajuan);
        List<PegawaiCredential> pegawaiCredentialList
                = new ArrayList<>();
        List<AjuanUraianTugasPegawaiBulananWrapper> ajuanUraianTugasPegawaiBulananWrappers
                = new ArrayList<>();
        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawaiByUnitKerja(kdUnitKerja);
        List<PejabatPenilaiDinilai> nipPegawaiBawahanPenilaiList
                = pejabatPenilaiDinilaiService.findPegawaiDinilai(nipPenilai);
        List<QutPegawai> pegawaiBawahanPenilaiList
                = new ArrayList<>();
        List<UraianTugasJabatanJenisUrtug> uraianTugasJabatanJenisUrtugList
                = uraianTugasJabatanJenisUrtugService.getUrtugNonDpaByUnitKerja(kdUnitKerja);
//        List<TkdUnk> tkdUnkList
//                = tkdUnkDao.findAll(); //test clone
        List<TkdUnk> tkdUnkList
                = EkinerjaObjConverter.convertFromUnkClonetoOriginal(tkdUnkDao.findAll());

        //filter pegawai bawahan penilai
        for (PejabatPenilaiDinilai pejabatPenilaiDinilai :
                nipPegawaiBawahanPenilaiList) {
            LOGGER.info(pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getKdJabatanDinilai());

            for (QutPegawai qutPegawai
                    : qutPegawaiList) {
                if (pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getKdJabatanDinilai()
                        .equals(qutPegawai.getKdJabatan())) {
                    pegawaiBawahanPenilaiList
                            .add(qutPegawai);
                }
            }
        }

        for (UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan : uraianTugasPegawaiBulanans) {
            Boolean constraint = false;

            for (PegawaiCredential pegawaiCredential : pegawaiCredentialList) {
                if (pegawaiCredential.getNipPegawai()
                        .equals(uraianTugasPegawaiBulanan
                                .getUraianTugasPegawaiBulananId()
                                .getNipPegawai())){
                    constraint = true;
                    break;
                }
            }

            if (!constraint){
                pegawaiCredentialList.add(
                        new PegawaiCredential(
                            uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getNipPegawai(),
                            null,
                            null,
                            null,
                            null,
                            null,
                            null));
            }

        }

        for (QutPegawai qutPegawai : pegawaiBawahanPenilaiList){
            AjuanUraianTugasPegawaiBulananWrapper ajuanUraianTugasNonDpaPegawaiWrapper = new AjuanUraianTugasPegawaiBulananWrapper();
            ajuanUraianTugasNonDpaPegawaiWrapper.setNipPegawai(qutPegawai.getNip());
            ajuanUraianTugasNonDpaPegawaiWrapper.setNamaPegawai(qutPegawai.getNama());
            ajuanUraianTugasNonDpaPegawaiWrapper.setKdJabatan(qutPegawai.getKdJabatan());
            ajuanUraianTugasNonDpaPegawaiWrapper.setJabatan(qutPegawai.getJabatan());
            ajuanUraianTugasNonDpaPegawaiWrapper.setEselon(qutPegawai.getEselon());
            ajuanUraianTugasNonDpaPegawaiWrapper.setKdUnitKerja(qutPegawai.getKdUnitKerja());

            ajuanUraianTugasPegawaiBulananWrappers.add(ajuanUraianTugasNonDpaPegawaiWrapper);
        }

        for (AjuanUraianTugasPegawaiBulananWrapper qutPegawaiWrapper
                : ajuanUraianTugasPegawaiBulananWrappers) {
            for (TkdUnk tkdUnk
                    : tkdUnkList) {
                if (tkdUnk.getKdUnK().equals(qutPegawaiWrapper.getKdUnitKerja())) {
                    qutPegawaiWrapper.setUnitKerja(tkdUnk.getUnitKerja());
                    break;
                }
            }
        }

        //insert urtug ke setiap elemen tugas
        int penanganan;
        for (AjuanUraianTugasPegawaiBulananWrapper ajuanUraianTugasNonDpaPegawaiWrapper
                : ajuanUraianTugasPegawaiBulananWrappers){
            penanganan = 0;

            List<UraianTugasPegawaiTahunanWrapper> uraianTugasWrapperList = new ArrayList<>();
            List<UraianTugasPegawaiTahunanWrapper> uraianTugasTidakDipilihWrapperList = new ArrayList<>();

            for (UraianTugasJabatanJenisUrtug uraianTugasJabatanJenisUrtug : uraianTugasJabatanJenisUrtugList) {
                if (ajuanUraianTugasNonDpaPegawaiWrapper.getKdJabatan()
                        .equals(uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJabatan())) {

                    boolean found = false;
                    for (UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan : uraianTugasPegawaiBulanans) {
                        if (ajuanUraianTugasNonDpaPegawaiWrapper.getNipPegawai()
                                .equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getNipPegawai())) {

                            if (uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug()
                                    .equals(uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdUrtug())) {
                                if (uraianTugasPegawaiBulanan.getStatusApproval() == 0) {
                                    found = true;
                                    uraianTugasWrapperList.add(
                                            new UraianTugasPegawaiTahunanWrapper(
                                                    uraianTugasPegawaiBulanan.getUraianTugasJabatanJenisUrtug().getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                                    uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdUrtug(),
                                                    uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJabatan(),
                                                    uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getKdJenisUrtug(),
                                                    uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getTahunUrtug(),
                                                    uraianTugasPegawaiBulanan.getUraianTugasPegawaiBulananId().getNipPegawai(),
                                                    uraianTugasPegawaiBulanan.getTargetKuantitas(),
                                                    uraianTugasPegawaiBulanan.getTargetSatuanKuantitas(),
                                                    uraianTugasPegawaiBulanan.getTargetKualitas(),
                                                    uraianTugasPegawaiBulanan.getWaktu(),
                                                    uraianTugasPegawaiBulanan.getBiaya(),
                                                    "",
                                                    uraianTugasPegawaiBulanan.getStatusApproval()
                                            ));
                                }
                                else if (uraianTugasPegawaiBulanan.getStatusApproval() == 2
                                        || uraianTugasPegawaiBulanan.getStatusApproval() == 1) {
                                    found = true;
                                    penanganan = 1;

                                }
                                //ambil uraian tugas yang tidak dipilih oleh bawahan
                                else if (uraianTugasPegawaiBulanan.getStatusApproval() == 3) {
                                    uraianTugasTidakDipilihWrapperList.add(
                                            new UraianTugasPegawaiTahunanWrapper(
                                                    uraianTugasJabatanJenisUrtug.getUraianTugasJabatan().getUraianTugas().getDeskripsi(),
                                                    uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdUrtug(),
                                                    uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJabatan(),
                                                    uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getKdJenisUrtug(),
                                                    uraianTugasJabatanJenisUrtug.getUraianTugasJabatanJenisUrtugId().getTahunUrtug(),
                                                    "",
                                                    uraianTugasJabatanJenisUrtug.getKuantitas(),
                                                    uraianTugasJabatanJenisUrtug.getSatuanKuantitas(),
                                                    uraianTugasJabatanJenisUrtug.getKualitas(),
                                                    uraianTugasJabatanJenisUrtug.getWaktu(),
                                                    uraianTugasJabatanJenisUrtug.getBiaya(),
                                                    "",
                                                    0
                                            ));
                                }

                                break;

                            }

                        }


                    }

                }
            }

            ajuanUraianTugasNonDpaPegawaiWrapper.setUraianTugasDiajukan(uraianTugasWrapperList);
            ajuanUraianTugasNonDpaPegawaiWrapper.setUraianTugasTidakDipilih(uraianTugasTidakDipilihWrapperList);

            // jika pegawai bawahan belum sama sekali mengirimkan ajuan kontrak kerja
            if (uraianTugasWrapperList.isEmpty() && penanganan == 0) {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(0);
            }
            //jika pegawai bawahan baru pertama kali mengirimkan ajuan kontrak kerja
            else if (!uraianTugasWrapperList.isEmpty() && penanganan == 0) {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(1);
            }
            //jika ajuan kontrak kerja bawahan sudah ditangani
            else if (uraianTugasWrapperList.isEmpty() && penanganan == 1) {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(2);
            }
            //jika pegawai bawahan mengajukan lagi kontrak kerja yang lain
            else if (!uraianTugasWrapperList.isEmpty() && penanganan == 1) {
                ajuanUraianTugasNonDpaPegawaiWrapper.setStatusPenangangan(3);
            }

        }

        return new ResponseEntity<>(ajuanUraianTugasPegawaiBulananWrappers, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    public ResponseEntity<?> getUraianTugasBulananByUnitKerja() {
        return null;
    }

    /**
     *
     *
     *
     * @return
     */
    @RequestMapping(value = "/get-progress-urtug-bulanan", method = RequestMethod.POST)
    public ResponseEntity<?> getProgressUrtugBulanan(@RequestBody UrtugBulananIdInputWrapper inputWrapper) {
        LOGGER.info("get progress urtug bulanan");

        List<TemplateLain> progressByTemplateList
                = new ArrayList<>();
        List<TemplateLainHistoryWrapper> progressByTemplateLainWrappers
                = new ArrayList<>();

        //lakukan pencarian berdasarkan jenis urtug
        switch (inputWrapper.getKdJenisUrtug()) {
            case "KJU002" :
                progressByTemplateList
                        = templateLainService.getByUrtugNonDpa(
                                new UraianTugasPegawaiBulananId(inputWrapper.getKdUrtug(),
                                                                inputWrapper.getKdJabatan(),
                                                                inputWrapper.getKdJenisUrtug(),
                                                                inputWrapper.getTahunUrtug(),
                                                                inputWrapper.getBulanUrtug(),
                                                                inputWrapper.getNipPegawai()));
                break;
            case "KJU001" :
                break;
        }

        for (TemplateLain obj : progressByTemplateList) {
            progressByTemplateLainWrappers
                    .add(new TemplateLainHistoryWrapper(obj.getKdTemplateLain(),
                            obj.getKeterangan(),
                            FilenameUtils.removeExtension(obj.getPathFile()),
                            FilenameUtils.getExtension(obj.getPathFile()),
                            obj.getStatusPenilaian(),
                            obj.getTanggalPembuatanMilis(),
                            obj.getAlasanPenolakan(),
                            "template lain",
                            15,
                            obj.getApprovalPenandatangan()));
        }

        return new ResponseEntity<>(progressByTemplateLainWrappers, HttpStatus.OK);
    }

    /**
     *
     *
     *
     * @param pegawai
     * @param daftarUrtugBulanan
     */
    private void createUraianTugasRecursive(QutPegawai pegawai,
                                            List<UraianTugasPegawaiBulananInputWrapper> daftarUrtugBulanan) {
        PejabatPenilaiDinilai pejabatPenilaiDinilai
                = pejabatPenilaiDinilaiService.findByKdJabatanDinilai(pegawai.getKdJabatan()).get(0);

        urtugBulananService.create(daftarUrtugBulanan, 1);

        //lakukan recursive hanya jika data penilai berbeda dengan bawahan
        //karena rule saat ini penilai bagi jabatan tertinggi adalah dirinya sendiri
        if (!pegawai.getNip()
                .equals(pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getNipPenilai())) {
            QutPegawai pegawaiPenilai
                    = qutPegawaiService.getQutPegawai(pejabatPenilaiDinilai.getPejabatPenilaiDinilaiId().getNipPenilai());

            //ubah kembali pembuat uraian tugas inputwrapper menjadi pegawai penilai
            for (UraianTugasPegawaiBulananInputWrapper urtugBulanan : daftarUrtugBulanan) {
                urtugBulanan.setNipPegawai(pegawaiPenilai.getNip());
            }

            createUraianTugasRecursive(pegawaiPenilai, daftarUrtugBulanan);
        }
    }

}
