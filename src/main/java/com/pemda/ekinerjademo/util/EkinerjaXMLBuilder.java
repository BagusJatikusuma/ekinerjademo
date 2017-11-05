package com.pemda.ekinerjademo.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by bagus on 05/11/17.
 */
public class EkinerjaXMLBuilder {

    public String convertListTembusanSuratNodinIntoXml(List<String> tembusanSuratList) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");

        //loop sejumlah list tembusan
        for (String tembusanSurat : tembusanSuratList) {
            root.addElement("tembusanSurat")
                    .addText(tembusanSurat);
        }

        return document.asXML();
    }

}
