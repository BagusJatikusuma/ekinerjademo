package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.repository.bismarepository.TkdUnkDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.TkdUnkCloneDao;
import com.pemda.ekinerjademo.service.*;
import com.pemda.ekinerjademo.util.exception.AuthenticationCredentialsNotFoundExcecption;
import com.pemda.ekinerjademo.util.exception.BadCredentialsException;
import com.pemda.ekinerjademo.wrapper.output.*;
import groovy.transform.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by bagus on 09/09/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api")
public class AuthenticationController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Resource(name = "BasicAuthenticationProvider")
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private QutPegawaiCloneService qutPegawaiService;
    @Autowired
    private UraianTugasPegawaiTahunanService uraianTugasPegawaiTahunanService;
    @Autowired
    private UraianTugasPegawaiBulananService uraianTugasPegawaiBulananService;
    @Autowired
    private AkunPegawaiService akunPegawaiService;
    @Autowired
    private LoginPegawaiService loginPegawaiService;

//    @Autowired private TkdUnkDao tkdUnkDao; //test clone
    @Autowired private TkdUnkCloneDao tkdUnkDao;

    /**
     * this method used for receive pegawai authentication request
     * @param akunPegawai in JSON Format
     * @return Role JSON Format
     */
    // tambah status sudah pernah mengajukan kontrak kerja atau belum
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @Transactional
    @Synchronized
    ResponseEntity<?> validateAkunPegawai(@RequestBody AkunPegawai akunPegawai) {
        LOGGER.info("receive "+akunPegawai.getNipPegawai()+" authetication request");

        AkunPegawai akunPegawaiAuthenticated = null;

        try {
            akunPegawaiAuthenticated = authenticationProvider.authenticate(akunPegawai);
        } catch (AuthenticationCredentialsNotFoundExcecption authenticationCredentialsNotFoundExcecption) {
            return new ResponseEntity<Object>(
                    new CustomMessage(authenticationCredentialsNotFoundExcecption.getMessage()),
                    HttpStatus.UNAUTHORIZED);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<Object>(
                    new CustomMessage(e.getMessage()),
                    HttpStatus.UNAUTHORIZED);

        }

        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date();

        calendar.setTime(currentDate);

        Long kdLoginPegawai = currentDate.getTime();

        LoginPegawai loginPegawai = new LoginPegawai();

        loginPegawai.setIdLoginPegawai(kdLoginPegawai);
        loginPegawai.setNipPegawai(akunPegawai.getNipPegawai());
        loginPegawai.setLoginMilis(currentDate.getTime());

        loginPegawai.setTanggalLogin(calendar.get(Calendar.DAY_OF_MONTH));
        loginPegawai.setBulanLogin(calendar.get(Calendar.MONTH)+1);
        loginPegawai.setTahunLogin(calendar.get(Calendar.YEAR));

        loginPegawaiService.createLog(loginPegawai);

        QutPegawai qutPegawai =
                qutPegawaiService.getQutPegawai(akunPegawaiAuthenticated.getNipPegawai());

        if (qutPegawai == null) {
            return new ResponseEntity<Object>(
                    new CustomMessage("pegawai not found in database kepegawaian"),
                    HttpStatus.UNAUTHORIZED);
        }

        List<UraianTugasPegawaiTahunan> kontrakKerja
                = uraianTugasPegawaiTahunanService.getByNipPegawai(akunPegawaiAuthenticated.getNipPegawai());

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.of("Asia/Jakarta")).toLocalDate();
        int bulan = localDate.getMonthValue();
        int tahun = localDate.getYear();

        List<UraianTugasPegawaiBulanan> kontrakKerjaBulanan
                = uraianTugasPegawaiBulananService.getAjuanKontrakBulanByNipPegawai(
                        akunPegawaiAuthenticated.getNipPegawai(),
                        bulan-1,
                        tahun);

        boolean sudahMembuatKontrakKerja = false;

        if (!kontrakKerjaBulanan.isEmpty())
            sudahMembuatKontrakKerja = true;

        PegawaiCredential pegawaiCredential =
                new PegawaiCredential(
                        akunPegawaiAuthenticated.getNipPegawai(),
                        qutPegawai.getNama(),
                        akunPegawaiAuthenticated.getRole(),
                        "IniTokenDUmmy",
                        qutPegawai.getJabatan(),
                        tkdUnkDao.findOne(qutPegawai.getKdUnitKerja()).getUnitKerja(),
                        qutPegawai.getKdUnitKerja(),
                        qutPegawai.getKdJabatan(),
                        qutPegawai.getPangkat(),
                        qutPegawai.getGol(),
                        qutPegawai.getEselon(),
                        kdLoginPegawai,
                        sudahMembuatKontrakKerja
                );

        return new ResponseEntity<Object>(pegawaiCredential, HttpStatus.OK);

    }

    @RequestMapping(value = "/logout-pegawai/{loginId}", method = RequestMethod.PUT)
    ResponseEntity<?> logOutPegawai(@PathVariable("loginId") Long loginId) {
        LOGGER.info("log out");
        LoginPegawai loginPegawai
                = loginPegawaiService.get(loginId);

        Calendar calendar = Calendar.getInstance();

        Date currentDate = new Date();

        calendar.setTime(currentDate);

        loginPegawai.setLogoutMilis(currentDate.getTime());
        loginPegawai.setTanggalLogout(calendar.get(Calendar.DAY_OF_MONTH));
        loginPegawai.setBulanLogout(calendar.get(Calendar.MONTH)+1);
        loginPegawai.setTahunLogout(calendar.get(Calendar.YEAR));

        loginPegawaiService.createLog(loginPegawai);

        return new ResponseEntity<Object>(new CustomMessage("logout success"), HttpStatus.OK);
    }

}
