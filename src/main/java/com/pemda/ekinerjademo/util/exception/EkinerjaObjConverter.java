package com.pemda.ekinerjademo.util.exception;

import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.bismamodel.TkdUnk;
import com.pemda.ekinerjademo.model.ekinerjamodel.TkdJabatanClone;
import com.pemda.ekinerjademo.model.ekinerjamodel.TkdUnkClone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class EkinerjaObjConverter {
    public static TkdJabatan convertFromCLonetoOriginal(TkdJabatanClone obj) {
        TkdJabatan tkdJabatan = new TkdJabatan();

        tkdJabatan.setKdJabatan(obj.getKdJabatan());
        tkdJabatan.setJabatan(obj.getJabatan());
        tkdJabatan.setUnitKerja(obj.getUnitKerja());
        tkdJabatan.setEselon(obj.getEselon());
        tkdJabatan.setPada(obj.getPada());
        tkdJabatan.setUnitKerja(obj.getUnitKerja());
        tkdJabatan.setTnjJabatan(obj.getTnjJabatan());
        tkdJabatan.setTnjJabatanLm(obj.getTnjJabatanLm());
        tkdJabatan.setKdUnitKerja(convertFromUnkClonetoOriginal(obj.getKdUnitKerja()));
        tkdJabatan.setKet(obj.getKet());

        return tkdJabatan;
    }

    public static List<TkdJabatan> convertFromCLonetoOriginal(List<TkdJabatanClone> objList) {
        List<TkdJabatan> tkdJabatans = new ArrayList<>();

        for (TkdJabatanClone obj : objList) {
            tkdJabatans.add(convertFromCLonetoOriginal(obj));
        }

        return tkdJabatans;
    }

    public static TkdUnk convertFromUnkClonetoOriginal(TkdUnkClone obj) {
        TkdUnk tkdUnk = new TkdUnk();

        tkdUnk.setKdUnK(obj.getKdUnK());
        tkdUnk.setUnitKerja(obj.getUnitKerja());
        tkdUnk.setAlamatUnitKerja(obj.getAlamatUnitKerja());
        tkdUnk.setTeleponUnK(obj.getTeleponUnK());
        tkdUnk.setFaxUnk(obj.getFaxUnk());
        tkdUnk.setTypeSKPD(obj.getTypeSKPD());

        return tkdUnk;
    }

    public static List<TkdUnk> convertFromUnkClonetoOriginal(List<TkdUnkClone> objList) {
        List<TkdUnk> tkdUnkList = new ArrayList<>();

        for (TkdUnkClone obj : objList) {
            tkdUnkList.add(convertFromUnkClonetoOriginal(obj));
        }

        return tkdUnkList;
    }
}
