package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.QutPegawaiClone;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import com.pemda.ekinerjademo.repository.ekinerjarepository.QutPegawaiCloneDao;
import com.pemda.ekinerjademo.service.QutPegawaiCloneService;
import com.pemda.ekinerjademo.service.QutPegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 10/12/17.
 */
@Service("QutPegawaiCloneService")
@Transactional("ekinerjaTransactionManager")
public class QutPegawaiCloneImpl implements QutPegawaiCloneService {
    @Autowired private QutPegawaiCloneDao qutPegawaiCloneDao;


    @Override
    public QutPegawai getQutPegawai(String nip) {
        QutPegawaiClone qutPegawaiClone
                = qutPegawaiCloneDao.findByNip(nip);

        QutPegawai qutPegawai = new QutPegawai();
        qutPegawai.setNip(qutPegawaiClone.getNip());
        qutPegawai.setNipLm(qutPegawaiClone.getNipLm());
        qutPegawai.setNama(qutPegawaiClone.getNama());
        qutPegawai.setGlrDpn(qutPegawaiClone.getGlrDpn());
        qutPegawai.setGlrBlk(qutPegawaiClone.getGlrBlk());
        qutPegawai.setTmtLahir(qutPegawaiClone.getTmtLahir());
        qutPegawai.setTglLahir(qutPegawaiClone.getTglLahir());
        qutPegawai.setJnsKlm(qutPegawaiClone.getJnsKlm());
        qutPegawai.setAgama(qutPegawaiClone.getAgama());
        qutPegawai.setStKwn(qutPegawaiClone.getStKwn());
        qutPegawai.setJmlAnak(qutPegawaiClone.getJmlAnak());
        qutPegawai.setAlRumah(qutPegawaiClone.getAlRumah());
        qutPegawai.setTlpRumah(qutPegawaiClone.getTlpRumah());
        qutPegawai.setTlpHP(qutPegawaiClone.getTlpHP());
        qutPegawai.setGolDarah(qutPegawaiClone.getGolDarah());
        qutPegawai.setTmtCpns(qutPegawaiClone.getTmtCpns());
        qutPegawai.setTmtPNS(qutPegawaiClone.getTmtPNS());
        qutPegawai.setKdJnPg(qutPegawaiClone.getKdJnPg());
        qutPegawai.setJnsKepeg(qutPegawaiClone.getJnsKepeg());
        qutPegawai.setPjbAngAwl(qutPegawaiClone.getPjbAngAwl());
        qutPegawai.setGol(qutPegawaiClone.getGol());
        qutPegawai.setPangkat(qutPegawaiClone.getPangkat());
        qutPegawai.setTmtGol(qutPegawaiClone.getTmtGol());
        qutPegawai.setNoSKgol(qutPegawaiClone.getNoSKgol());
        qutPegawai.setTglSKgol(qutPegawaiClone.getTglSKgol());
        qutPegawai.setPjTTDSKKP(qutPegawaiClone.getPjTTDSKKP());
        qutPegawai.setKdJabatan(qutPegawaiClone.getKdJabatan());
        qutPegawai.setJabatan(qutPegawaiClone.getJabatan());
        qutPegawai.setKdUntSbr(qutPegawaiClone.getKdUntSbr());
        qutPegawai.setUnkerFUng(qutPegawaiClone.getUnkerFUng());
        qutPegawai.setKdJjJab(qutPegawaiClone.getKdJjJab());
        qutPegawai.setJenJAb(qutPegawaiClone.getJenJAb());
        qutPegawai.setAKLm(qutPegawaiClone.getAKLm());
        qutPegawai.setTunJafung(qutPegawaiClone.getTunJafung());
        qutPegawai.setEselon(qutPegawaiClone.getEselon());
        qutPegawai.setJabatan1(qutPegawaiClone.getJabatan1());
        qutPegawai.setUnitSBr(qutPegawaiClone.getUnitSBr());
        qutPegawai.setTmtJAb(qutPegawaiClone.getTmtJAb());
        qutPegawai.setNoSKJab(qutPegawaiClone.getNoSKJab());
        qutPegawai.setTglSKJb(qutPegawaiClone.getTglSKJb());
        qutPegawai.setPjTTDSKJb(qutPegawaiClone.getPjTTDSKJb());
        qutPegawai.setPada(qutPegawaiClone.getPada());
        qutPegawai.setUnitKerja(qutPegawaiClone.getUnitKerja());
        qutPegawai.setTnjJabatan(qutPegawaiClone.getTnjJabatan());
        qutPegawai.setTunJab(qutPegawaiClone.getTunJab());
        qutPegawai.setMkTh(qutPegawaiClone.getMkTh());
        qutPegawai.setMkBl(qutPegawaiClone.getMkBl());
        qutPegawai.setGajiPokok(qutPegawaiClone.getGajiPokok());
        qutPegawai.setKdDikStruAkr(qutPegawaiClone.getKdDikStruAkr());
        qutPegawai.setKdDikStruAkr2(qutPegawaiClone.getKdDikStruAkr2());
        qutPegawai.setDikStruk(qutPegawaiClone.getDikStruk());
        qutPegawai.setPenyelenggara(qutPegawaiClone.getPenyelenggara());
        qutPegawai.setAngkatanSt(qutPegawaiClone.getAngkatanSt());
        qutPegawai.setJumlahJam(qutPegawaiClone.getJumlahJam());
        qutPegawai.setTglIjasah(qutPegawaiClone.getTglIjasah());
        qutPegawai.setNoIjasahDS(qutPegawaiClone.getNoIjasahDS());
        qutPegawai.setTlpUnitKrj(qutPegawaiClone.getTlpUnitKrj());
        qutPegawai.setKdPendFormal(qutPegawaiClone.getKdPendFormal());
        qutPegawai.setPendidikan(qutPegawaiClone.getPendidikan());
        qutPegawai.setFakultas(qutPegawaiClone.getFakultas());
        qutPegawai.setJurusan(qutPegawaiClone.getJurusan());
        qutPegawai.setTglLulus(qutPegawaiClone.getTglLulus());
        qutPegawai.setNoIjasahPF(qutPegawaiClone.getNoIjasahPF());
        qutPegawai.setAlumni(qutPegawaiClone.getAlumni());
        qutPegawai.setThLsPen(qutPegawaiClone.getThLsPen());
        qutPegawai.setNmKepSek(qutPegawaiClone.getNmKepSek());
        qutPegawai.setOperator1(qutPegawaiClone.getOperator1());
        qutPegawai.setsUpdate(qutPegawaiClone.getsUpdate());
        qutPegawai.setSts(qutPegawaiClone.getSts());
        qutPegawai.setNmLEN(qutPegawaiClone.getNmLEN());
        qutPegawai.setGab(qutPegawaiClone.getGab());
        qutPegawai.setKdUnitKerja(qutPegawaiClone.getKdUnitKerja());
        qutPegawai.setBUP(qutPegawaiClone.getBUP());
        qutPegawai.setNoKarpeg(qutPegawaiClone.getNoKarpeg());
        qutPegawai.setNoKaris_Karsu(qutPegawaiClone.getNoKaris_Karsu());
        qutPegawai.setNoTaspen(qutPegawaiClone.getNoTaspen());
        qutPegawai.setIDKdHukum(qutPegawaiClone.getIDKdHukum());
        qutPegawai.setKetHukum(qutPegawaiClone.getKetHukum());
        qutPegawai.setPensiun(qutPegawaiClone.getPensiun());
        qutPegawai.setJnsJab(qutPegawaiClone.getJnsJab());
        qutPegawai.setProyeksiKP(qutPegawaiClone.getProyeksiKP());

        return qutPegawai;
    }

    @Override
    public List<QutPegawai> getQutPegawai() {
        List<QutPegawai> qutPegawaiList
                = new ArrayList<>();
        List<QutPegawaiClone> qutPegawaiCloneList
                = qutPegawaiCloneDao.findAll();

        for (QutPegawaiClone qutPegawaiClone : qutPegawaiCloneList) {
            QutPegawai qutPegawai = new QutPegawai();

            qutPegawai.setNip(qutPegawaiClone.getNip());
            qutPegawai.setNipLm(qutPegawaiClone.getNipLm());
            qutPegawai.setNama(qutPegawaiClone.getNama());
            qutPegawai.setGlrDpn(qutPegawaiClone.getGlrDpn());
            qutPegawai.setGlrBlk(qutPegawaiClone.getGlrBlk());
            qutPegawai.setTmtLahir(qutPegawaiClone.getTmtLahir());
            qutPegawai.setTglLahir(qutPegawaiClone.getTglLahir());
            qutPegawai.setJnsKlm(qutPegawaiClone.getJnsKlm());
            qutPegawai.setAgama(qutPegawaiClone.getAgama());
            qutPegawai.setStKwn(qutPegawaiClone.getStKwn());
            qutPegawai.setJmlAnak(qutPegawaiClone.getJmlAnak());
            qutPegawai.setAlRumah(qutPegawaiClone.getAlRumah());
            qutPegawai.setTlpRumah(qutPegawaiClone.getTlpRumah());
            qutPegawai.setTlpHP(qutPegawaiClone.getTlpHP());
            qutPegawai.setGolDarah(qutPegawaiClone.getGolDarah());
            qutPegawai.setTmtCpns(qutPegawaiClone.getTmtCpns());
            qutPegawai.setTmtPNS(qutPegawaiClone.getTmtPNS());
            qutPegawai.setKdJnPg(qutPegawaiClone.getKdJnPg());
            qutPegawai.setJnsKepeg(qutPegawaiClone.getJnsKepeg());
            qutPegawai.setPjbAngAwl(qutPegawaiClone.getPjbAngAwl());
            qutPegawai.setGol(qutPegawaiClone.getGol());
            qutPegawai.setPangkat(qutPegawaiClone.getPangkat());
            qutPegawai.setTmtGol(qutPegawaiClone.getTmtGol());
            qutPegawai.setNoSKgol(qutPegawaiClone.getNoSKgol());
            qutPegawai.setTglSKgol(qutPegawaiClone.getTglSKgol());
            qutPegawai.setPjTTDSKKP(qutPegawaiClone.getPjTTDSKKP());
            qutPegawai.setKdJabatan(qutPegawaiClone.getKdJabatan());
            qutPegawai.setJabatan(qutPegawaiClone.getJabatan());
            qutPegawai.setKdUntSbr(qutPegawaiClone.getKdUntSbr());
            qutPegawai.setUnkerFUng(qutPegawaiClone.getUnkerFUng());
            qutPegawai.setKdJjJab(qutPegawaiClone.getKdJjJab());
            qutPegawai.setJenJAb(qutPegawaiClone.getJenJAb());
            qutPegawai.setAKLm(qutPegawaiClone.getAKLm());
            qutPegawai.setTunJafung(qutPegawaiClone.getTunJafung());
            qutPegawai.setEselon(qutPegawaiClone.getEselon());
            qutPegawai.setJabatan1(qutPegawaiClone.getJabatan1());
            qutPegawai.setUnitSBr(qutPegawaiClone.getUnitSBr());
            qutPegawai.setTmtJAb(qutPegawaiClone.getTmtJAb());
            qutPegawai.setNoSKJab(qutPegawaiClone.getNoSKJab());
            qutPegawai.setTglSKJb(qutPegawaiClone.getTglSKJb());
            qutPegawai.setPjTTDSKJb(qutPegawaiClone.getPjTTDSKJb());
            qutPegawai.setPada(qutPegawaiClone.getPada());
            qutPegawai.setUnitKerja(qutPegawaiClone.getUnitKerja());
            qutPegawai.setTnjJabatan(qutPegawaiClone.getTnjJabatan());
            qutPegawai.setTunJab(qutPegawaiClone.getTunJab());
            qutPegawai.setMkTh(qutPegawaiClone.getMkTh());
            qutPegawai.setMkBl(qutPegawaiClone.getMkBl());
            qutPegawai.setGajiPokok(qutPegawaiClone.getGajiPokok());
            qutPegawai.setKdDikStruAkr(qutPegawaiClone.getKdDikStruAkr());
            qutPegawai.setKdDikStruAkr2(qutPegawaiClone.getKdDikStruAkr2());
            qutPegawai.setDikStruk(qutPegawaiClone.getDikStruk());
            qutPegawai.setPenyelenggara(qutPegawaiClone.getPenyelenggara());
            qutPegawai.setAngkatanSt(qutPegawaiClone.getAngkatanSt());
            qutPegawai.setJumlahJam(qutPegawaiClone.getJumlahJam());
            qutPegawai.setTglIjasah(qutPegawaiClone.getTglIjasah());
            qutPegawai.setNoIjasahDS(qutPegawaiClone.getNoIjasahDS());
            qutPegawai.setTlpUnitKrj(qutPegawaiClone.getTlpUnitKrj());
            qutPegawai.setKdPendFormal(qutPegawaiClone.getKdPendFormal());
            qutPegawai.setPendidikan(qutPegawaiClone.getPendidikan());
            qutPegawai.setFakultas(qutPegawaiClone.getFakultas());
            qutPegawai.setJurusan(qutPegawaiClone.getJurusan());
            qutPegawai.setTglLulus(qutPegawaiClone.getTglLulus());
            qutPegawai.setNoIjasahPF(qutPegawaiClone.getNoIjasahPF());
            qutPegawai.setAlumni(qutPegawaiClone.getAlumni());
            qutPegawai.setThLsPen(qutPegawaiClone.getThLsPen());
            qutPegawai.setNmKepSek(qutPegawaiClone.getNmKepSek());
            qutPegawai.setOperator1(qutPegawaiClone.getOperator1());
            qutPegawai.setsUpdate(qutPegawaiClone.getsUpdate());
            qutPegawai.setSts(qutPegawaiClone.getSts());
            qutPegawai.setNmLEN(qutPegawaiClone.getNmLEN());
            qutPegawai.setGab(qutPegawaiClone.getGab());
            qutPegawai.setKdUnitKerja(qutPegawaiClone.getKdUnitKerja());
            qutPegawai.setBUP(qutPegawaiClone.getBUP());
            qutPegawai.setNoKarpeg(qutPegawaiClone.getNoKarpeg());
            qutPegawai.setNoKaris_Karsu(qutPegawaiClone.getNoKaris_Karsu());
            qutPegawai.setNoTaspen(qutPegawaiClone.getNoTaspen());
            qutPegawai.setIDKdHukum(qutPegawaiClone.getIDKdHukum());
            qutPegawai.setKetHukum(qutPegawaiClone.getKetHukum());
            qutPegawai.setPensiun(qutPegawaiClone.getPensiun());
            qutPegawai.setJnsJab(qutPegawaiClone.getJnsJab());
            qutPegawai.setProyeksiKP(qutPegawaiClone.getProyeksiKP());

            qutPegawaiList.add(qutPegawai);
        }

        return qutPegawaiList;
    }

    @Override
    public List<CustomPegawaiCredential> getCustomPegawaiCredentials() {
        return qutPegawaiCloneDao.findByCustomPegawaiCredential();
    }

    @Override
    public List<QutPegawai> getQutPegawaiByUnitKerja(String kdUnitKerja) {
        List<QutPegawai> qutPegawaiList
                = new ArrayList<>();
        List<QutPegawaiClone> qutPegawaiCloneList
                = qutPegawaiCloneDao.findByKdUnitKerja(kdUnitKerja);

        for (QutPegawaiClone qutPegawaiClone : qutPegawaiCloneList) {
            QutPegawai qutPegawai = new QutPegawai();

            qutPegawai.setNip(qutPegawaiClone.getNip());
            qutPegawai.setNipLm(qutPegawaiClone.getNipLm());
            qutPegawai.setNama(qutPegawaiClone.getNama());
            qutPegawai.setGlrDpn(qutPegawaiClone.getGlrDpn());
            qutPegawai.setGlrBlk(qutPegawaiClone.getGlrBlk());
            qutPegawai.setTmtLahir(qutPegawaiClone.getTmtLahir());
            qutPegawai.setTglLahir(qutPegawaiClone.getTglLahir());
            qutPegawai.setJnsKlm(qutPegawaiClone.getJnsKlm());
            qutPegawai.setAgama(qutPegawaiClone.getAgama());
            qutPegawai.setStKwn(qutPegawaiClone.getStKwn());
            qutPegawai.setJmlAnak(qutPegawaiClone.getJmlAnak());
            qutPegawai.setAlRumah(qutPegawaiClone.getAlRumah());
            qutPegawai.setTlpRumah(qutPegawaiClone.getTlpRumah());
            qutPegawai.setTlpHP(qutPegawaiClone.getTlpHP());
            qutPegawai.setGolDarah(qutPegawaiClone.getGolDarah());
            qutPegawai.setTmtCpns(qutPegawaiClone.getTmtCpns());
            qutPegawai.setTmtPNS(qutPegawaiClone.getTmtPNS());
            qutPegawai.setKdJnPg(qutPegawaiClone.getKdJnPg());
            qutPegawai.setJnsKepeg(qutPegawaiClone.getJnsKepeg());
            qutPegawai.setPjbAngAwl(qutPegawaiClone.getPjbAngAwl());
            qutPegawai.setGol(qutPegawaiClone.getGol());
            qutPegawai.setPangkat(qutPegawaiClone.getPangkat());
            qutPegawai.setTmtGol(qutPegawaiClone.getTmtGol());
            qutPegawai.setNoSKgol(qutPegawaiClone.getNoSKgol());
            qutPegawai.setTglSKgol(qutPegawaiClone.getTglSKgol());
            qutPegawai.setPjTTDSKKP(qutPegawaiClone.getPjTTDSKKP());
            qutPegawai.setKdJabatan(qutPegawaiClone.getKdJabatan());
            qutPegawai.setJabatan(qutPegawaiClone.getJabatan());
            qutPegawai.setKdUntSbr(qutPegawaiClone.getKdUntSbr());
            qutPegawai.setUnkerFUng(qutPegawaiClone.getUnkerFUng());
            qutPegawai.setKdJjJab(qutPegawaiClone.getKdJjJab());
            qutPegawai.setJenJAb(qutPegawaiClone.getJenJAb());
            qutPegawai.setAKLm(qutPegawaiClone.getAKLm());
            qutPegawai.setTunJafung(qutPegawaiClone.getTunJafung());
            qutPegawai.setEselon(qutPegawaiClone.getEselon());
            qutPegawai.setJabatan1(qutPegawaiClone.getJabatan1());
            qutPegawai.setUnitSBr(qutPegawaiClone.getUnitSBr());
            qutPegawai.setTmtJAb(qutPegawaiClone.getTmtJAb());
            qutPegawai.setNoSKJab(qutPegawaiClone.getNoSKJab());
            qutPegawai.setTglSKJb(qutPegawaiClone.getTglSKJb());
            qutPegawai.setPjTTDSKJb(qutPegawaiClone.getPjTTDSKJb());
            qutPegawai.setPada(qutPegawaiClone.getPada());
            qutPegawai.setUnitKerja(qutPegawaiClone.getUnitKerja());
            qutPegawai.setTnjJabatan(qutPegawaiClone.getTnjJabatan());
            qutPegawai.setTunJab(qutPegawaiClone.getTunJab());
            qutPegawai.setMkTh(qutPegawaiClone.getMkTh());
            qutPegawai.setMkBl(qutPegawaiClone.getMkBl());
            qutPegawai.setGajiPokok(qutPegawaiClone.getGajiPokok());
            qutPegawai.setKdDikStruAkr(qutPegawaiClone.getKdDikStruAkr());
            qutPegawai.setKdDikStruAkr2(qutPegawaiClone.getKdDikStruAkr2());
            qutPegawai.setDikStruk(qutPegawaiClone.getDikStruk());
            qutPegawai.setPenyelenggara(qutPegawaiClone.getPenyelenggara());
            qutPegawai.setAngkatanSt(qutPegawaiClone.getAngkatanSt());
            qutPegawai.setJumlahJam(qutPegawaiClone.getJumlahJam());
            qutPegawai.setTglIjasah(qutPegawaiClone.getTglIjasah());
            qutPegawai.setNoIjasahDS(qutPegawaiClone.getNoIjasahDS());
            qutPegawai.setTlpUnitKrj(qutPegawaiClone.getTlpUnitKrj());
            qutPegawai.setKdPendFormal(qutPegawaiClone.getKdPendFormal());
            qutPegawai.setPendidikan(qutPegawaiClone.getPendidikan());
            qutPegawai.setFakultas(qutPegawaiClone.getFakultas());
            qutPegawai.setJurusan(qutPegawaiClone.getJurusan());
            qutPegawai.setTglLulus(qutPegawaiClone.getTglLulus());
            qutPegawai.setNoIjasahPF(qutPegawaiClone.getNoIjasahPF());
            qutPegawai.setAlumni(qutPegawaiClone.getAlumni());
            qutPegawai.setThLsPen(qutPegawaiClone.getThLsPen());
            qutPegawai.setNmKepSek(qutPegawaiClone.getNmKepSek());
            qutPegawai.setOperator1(qutPegawaiClone.getOperator1());
            qutPegawai.setsUpdate(qutPegawaiClone.getsUpdate());
            qutPegawai.setSts(qutPegawaiClone.getSts());
            qutPegawai.setNmLEN(qutPegawaiClone.getNmLEN());
            qutPegawai.setGab(qutPegawaiClone.getGab());
            qutPegawai.setKdUnitKerja(qutPegawaiClone.getKdUnitKerja());
            qutPegawai.setBUP(qutPegawaiClone.getBUP());
            qutPegawai.setNoKarpeg(qutPegawaiClone.getNoKarpeg());
            qutPegawai.setNoKaris_Karsu(qutPegawaiClone.getNoKaris_Karsu());
            qutPegawai.setNoTaspen(qutPegawaiClone.getNoTaspen());
            qutPegawai.setIDKdHukum(qutPegawaiClone.getIDKdHukum());
            qutPegawai.setKetHukum(qutPegawaiClone.getKetHukum());
            qutPegawai.setPensiun(qutPegawaiClone.getPensiun());
            qutPegawai.setJnsJab(qutPegawaiClone.getJnsJab());
            qutPegawai.setProyeksiKP(qutPegawaiClone.getProyeksiKP());

            qutPegawaiList.add(qutPegawai);
        }

        return qutPegawaiList;
    }

    @Override
    public List<QutPegawaiClone> getQutPegawaiByKdJabatan(String kdJabatan) {
        return qutPegawaiCloneDao.findByKdJabatan(kdJabatan);
    }

    @Override
    public List<QutPegawaiClone> convertQutPegawaiIntoQutPegawaiClone(List<QutPegawai> qutPegawaiList) {
        List<QutPegawaiClone> qutPegawaiCloneList
                = new ArrayList<>();

        for (QutPegawai qutPegawai : qutPegawaiList) {
            qutPegawaiCloneList.add(convertQutPegawaiIntoQutPegawaiClone(qutPegawai));
        }

        return qutPegawaiCloneList;
    }

    @Override
    public QutPegawaiClone convertQutPegawaiIntoQutPegawaiClone(QutPegawai qutPegawai) {
        QutPegawaiClone qutPegawaiClone = new QutPegawaiClone();

        qutPegawaiClone.setNip(qutPegawai.getNip());
        qutPegawaiClone.setNipLm(qutPegawai.getNipLm());
        qutPegawaiClone.setNama(qutPegawai.getNama());
        qutPegawaiClone.setGlrDpn(qutPegawai.getGlrDpn());
        qutPegawaiClone.setGlrBlk(qutPegawai.getGlrBlk());
        qutPegawaiClone.setTmtLahir(qutPegawai.getTmtLahir());
        qutPegawaiClone.setTglLahir(qutPegawai.getTglLahir());
        qutPegawaiClone.setJnsKlm(qutPegawai.getJnsKlm());
        qutPegawaiClone.setAgama(qutPegawai.getAgama());
        qutPegawaiClone.setStKwn(qutPegawai.getStKwn());
        qutPegawaiClone.setJmlAnak(qutPegawai.getJmlAnak());
        qutPegawaiClone.setAlRumah(qutPegawai.getAlRumah());
        qutPegawaiClone.setTlpRumah(qutPegawai.getTlpRumah());
        qutPegawaiClone.setTlpHP(qutPegawai.getTlpHP());
        qutPegawaiClone.setGolDarah(qutPegawai.getGolDarah());
        qutPegawaiClone.setTmtCpns(qutPegawai.getTmtCpns());
        qutPegawaiClone.setTmtPNS(qutPegawai.getTmtPNS());
        qutPegawaiClone.setKdJnPg(qutPegawai.getKdJnPg());
        qutPegawaiClone.setJnsKepeg(qutPegawai.getJnsKepeg());
        qutPegawaiClone.setPjbAngAwl(qutPegawai.getPjbAngAwl());
        qutPegawaiClone.setGol(qutPegawai.getGol());
        qutPegawaiClone.setPangkat(qutPegawai.getPangkat());
        qutPegawaiClone.setTmtGol(qutPegawai.getTmtGol());
        qutPegawaiClone.setNoSKgol(qutPegawai.getNoSKgol());

        qutPegawaiClone.setKdJabatan(qutPegawai.getKdJabatan());
        qutPegawaiClone.setJabatan(qutPegawai.getJabatan());

        qutPegawaiClone.setKdUnitKerja(qutPegawai.getKdUnitKerja());
        qutPegawaiClone.setUnitKerja(qutPegawai.getUnitKerja());

        qutPegawaiClone.setEselon(qutPegawai.getEselon());

        return qutPegawaiClone;
    }

    @Override
    public void deleteAll() {
        qutPegawaiCloneDao.deleteAll();
    }

    @Override
    public void saveQutPegawaiList(List<QutPegawaiClone> qutPegawaiList) {
        for (QutPegawaiClone qutPegawaiClone : qutPegawaiList) {
            qutPegawaiCloneDao.save(qutPegawaiClone);
        }
    }
}
