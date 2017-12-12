package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.controller.api.SuratInstruksiController;
import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.QutPegawaiClone;
import com.pemda.ekinerjademo.service.DataSynchronizer;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 09/12/17.
 */
@Component("DataSynchronizer")
public class DataSynchronizerImpl extends Thread{
    public static final Logger LOGGER
            = LoggerFactory.getLogger(DataSynchronizerImpl.class);

    @Autowired private QutPegawaiService qutPegawaiService;
    @Autowired private QutPegawaiCloneService qutPegawaiCloneService;

    public void run()
    {
        LOGGER.info("start check synchronize process");

        resetDataPegawai();

        try
        {
            //get start day
//            int tempDay = (int)(new Date().getTime()/(1000*60*60*24));
            Date tempDate = new Date();
            Date currentDate;
            //loop
            for (;;) {
//                int currentDay = (int)(new Date().getTime()/(1000*60*60*24));
                currentDate = new Date();

//                LOGGER.info(tempDay+" : "+currentDay);
                //if already tomorrow
                if (currentDate.after(tempDate)) {
                    resetDataPegawai();
                    //replace temp day
                    tempDate = new Date();
                }
                //wait for one hour
                Thread.sleep(1000 * 60 * 60);
            }

        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }
    }

    private void resetDataPegawai() {
        LOGGER.info("reset data pegawai");

        qutPegawaiCloneService.deleteAll();

        List<QutPegawai> qutPegawaiList
                = qutPegawaiService.getQutPegawai();
        List<QutPegawaiClone> qutPegawaiCloneList
                = new ArrayList<>();

        for (QutPegawai qutPegawai : qutPegawaiList) {
            QutPegawaiClone pegawaiClone = new QutPegawaiClone();

            pegawaiClone.setNip(qutPegawai.getNip());
            pegawaiClone.setNipLm(qutPegawai.getNipLm());
            pegawaiClone.setNama(qutPegawai.getNama());
            pegawaiClone.setGlrDpn(qutPegawai.getGlrDpn());
            pegawaiClone.setGlrBlk(qutPegawai.getGlrBlk());
            pegawaiClone.setTmtLahir(qutPegawai.getTmtLahir());
            pegawaiClone.setTglLahir(qutPegawai.getTglLahir());
            pegawaiClone.setJnsKlm(qutPegawai.getJnsKlm());
            pegawaiClone.setAgama(qutPegawai.getAgama());
            pegawaiClone.setStKwn(qutPegawai.getStKwn());
            pegawaiClone.setJmlAnak(qutPegawai.getJmlAnak());
            pegawaiClone.setAlRumah(qutPegawai.getAlRumah());
            pegawaiClone.setTlpRumah(qutPegawai.getTlpRumah());
            pegawaiClone.setTlpHP(qutPegawai.getTlpHP());
            pegawaiClone.setGolDarah(qutPegawai.getGolDarah());
            pegawaiClone.setTmtCpns(qutPegawai.getTmtCpns());
            pegawaiClone.setTmtPNS(qutPegawai.getTmtPNS());
            pegawaiClone.setKdJnPg(qutPegawai.getKdJnPg());
            pegawaiClone.setJnsKepeg(qutPegawai.getJnsKepeg());
            pegawaiClone.setPjbAngAwl(qutPegawai.getPjbAngAwl());
            pegawaiClone.setGol(qutPegawai.getGol());
            pegawaiClone.setPangkat(qutPegawai.getPangkat());
            pegawaiClone.setTmtGol(qutPegawai.getTmtGol());
            pegawaiClone.setNoSKgol(qutPegawai.getNoSKgol());
            pegawaiClone.setTglSKgol(qutPegawai.getTglSKgol());
            pegawaiClone.setPjTTDSKKP(qutPegawai.getPjTTDSKKP());
            pegawaiClone.setKdJabatan(qutPegawai.getKdJabatan());
            pegawaiClone.setJabatan(qutPegawai.getJabatan());
            pegawaiClone.setKdUntSbr(qutPegawai.getKdUntSbr());
            pegawaiClone.setUnkerFUng(qutPegawai.getUnkerFUng());
            pegawaiClone.setKdJjJab(qutPegawai.getKdJjJab());
            pegawaiClone.setJenJAb(qutPegawai.getJenJAb());
            pegawaiClone.setAKLm(qutPegawai.getAKLm());
            pegawaiClone.setTunJafung(qutPegawai.getTunJafung());
            pegawaiClone.setEselon(qutPegawai.getEselon());
            pegawaiClone.setJabatan1(qutPegawai.getJabatan1());
            pegawaiClone.setUnitSBr(qutPegawai.getUnitSBr());
            pegawaiClone.setTmtJAb(qutPegawai.getTmtJAb());
            pegawaiClone.setNoSKJab(qutPegawai.getNoSKJab());
            pegawaiClone.setTglSKJb(qutPegawai.getTglSKJb());
            pegawaiClone.setPjTTDSKJb(qutPegawai.getPjTTDSKJb());
            pegawaiClone.setPada(qutPegawai.getPada());
            pegawaiClone.setUnitKerja(qutPegawai.getUnitKerja());
            pegawaiClone.setTnjJabatan(qutPegawai.getTnjJabatan());
            pegawaiClone.setTunJab(qutPegawai.getTunJab());
            pegawaiClone.setMkTh(qutPegawai.getMkTh());
            pegawaiClone.setMkBl(qutPegawai.getMkBl());
            pegawaiClone.setGajiPokok(qutPegawai.getGajiPokok());
            pegawaiClone.setKdDikStruAkr(qutPegawai.getKdDikStruAkr());
            pegawaiClone.setKdDikStruAkr2(qutPegawai.getKdDikStruAkr2());
            pegawaiClone.setDikStruk(qutPegawai.getDikStruk());
            pegawaiClone.setPenyelenggara(qutPegawai.getPenyelenggara());
            pegawaiClone.setAngkatanSt(qutPegawai.getAngkatanSt());
            pegawaiClone.setJumlahJam(qutPegawai.getJumlahJam());
            pegawaiClone.setTglIjasah(qutPegawai.getTglIjasah());
            pegawaiClone.setNoIjasahDS(qutPegawai.getNoIjasahDS());
            pegawaiClone.setTlpUnitKrj(qutPegawai.getTlpUnitKrj());
            pegawaiClone.setKdPendFormal(qutPegawai.getKdPendFormal());
            pegawaiClone.setPendidikan(qutPegawai.getPendidikan());
            pegawaiClone.setFakultas(qutPegawai.getFakultas());
            pegawaiClone.setJurusan(qutPegawai.getJurusan());
            pegawaiClone.setTglLulus(qutPegawai.getTglLulus());
            pegawaiClone.setNoIjasahPF(qutPegawai.getNoIjasahPF());
            pegawaiClone.setAlumni(qutPegawai.getAlumni());
            pegawaiClone.setThLsPen(qutPegawai.getThLsPen());
            pegawaiClone.setNmKepSek(qutPegawai.getNmKepSek());
            pegawaiClone.setOperator1(qutPegawai.getOperator1());
            pegawaiClone.setsUpdate(qutPegawai.getsUpdate());
            pegawaiClone.setSts(qutPegawai.getSts());
            pegawaiClone.setNmLEN(qutPegawai.getNmLEN());
            pegawaiClone.setGab(qutPegawai.getGab());
            pegawaiClone.setKdUnitKerja(qutPegawai.getKdUnitKerja());
            pegawaiClone.setBUP(qutPegawai.getBUP());
            pegawaiClone.setNoKarpeg(qutPegawai.getNoKarpeg());
            pegawaiClone.setNoKaris_Karsu(qutPegawai.getNoKaris_Karsu());
            pegawaiClone.setNoTaspen(qutPegawai.getNoTaspen());
            pegawaiClone.setIDKdHukum(qutPegawai.getIDKdHukum());
            pegawaiClone.setKetHukum(qutPegawai.getKetHukum());
            pegawaiClone.setPensiun(qutPegawai.getPensiun());
            pegawaiClone.setJnsJab(qutPegawai.getJnsJab());
            pegawaiClone.setProyeksiKP(qutPegawai.getProyeksiKP());
            pegawaiClone.setJumlahJam(qutPegawai.getJumlahJam());

            qutPegawaiCloneList.add(pegawaiClone);
        }

        qutPegawaiCloneService.saveQutPegawaiList(qutPegawaiCloneList);
    }

}
