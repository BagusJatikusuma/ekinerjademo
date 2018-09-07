package com.pemda.ekinerjademo.util;

import com.pemda.ekinerjademo.controller.api.TestServiceController;
import com.pemda.ekinerjademo.model.bismamodel.TkdJabatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TkdJabatanClone;
import com.pemda.ekinerjademo.model.ekinerjamodel.TkdUnkClone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(CSVUtil.class);

    public List<TkdJabatanClone> getTkdJabatanCLoneFromCSV(String csvFilePath, List<TkdUnkClone> tkdUnkClones) {
//        String csvFile = csvFilePath;
//        String line = "";
//        String cvsSplitBy = ",";
//
//        LOGGER.info("path of file "+csvFile);
//
//        List<TkdJabatanClone> tkdJabatanClones = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//
//            while ((line = br.readLine()) != null) {
//                // use comma as separator
//                String[] country = line.split(cvsSplitBy);
//                TkdJabatanClone objJatan = new TkdJabatanClone();
//                LOGGER.error("size of line "+country.length + "with example first element "+country[0]);
//
//                for (TkdUnkClone objUnk : tkdUnkClones) {
//                    if (objUnk.getUnitKerja().equals(country[3])) {
//                        objJatan.setKdJabatan(country[0]);
//                        objJatan.setJabatan(country[1]);
//                        objJatan.setEselon(country[2]);
//                        objJatan.setUnitKerja(country[3]);
//                        objJatan.setKdUnitKerja(objUnk);
//
//                        tkdJabatanClones.add(objJatan);
//
//                        break;
//                    }
//                }
//
////                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return null;

    }

}
