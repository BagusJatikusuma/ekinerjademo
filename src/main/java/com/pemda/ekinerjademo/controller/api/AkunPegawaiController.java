package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.AkunPegawai;
import com.pemda.ekinerjademo.repository.bismarepository.QutPegawaiDao;
import com.pemda.ekinerjademo.repository.bismarepository.TkdJabatanDao;
import com.pemda.ekinerjademo.repository.ekinerjarepository.AkunPegawaiDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bagus on 06/09/17.
 */
@RestController
@RequestMapping(value = "/api/pegawai")
public class AkunPegawaiController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AkunPegawaiController.class);

    @Autowired private TkdJabatanDao tkdJabatanDao;
    @Autowired private QutPegawaiDao qutPegawaiDao;
    @Autowired private AkunPegawaiDao akunPegawaiDao;

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
