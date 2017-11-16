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

    public String convertListDasarSuratPerintahIntoXml(List<String> dasarList) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");

        //loop sejumlah list dasar
        for (String dasar : dasarList) {
            root.addElement("dasar")
                    .addText(dasar);
        }

        return document.asXML();
    }

    public String convertListSuratPerintahIntoXml(List<String> objectList, String objectType) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");

        //loop sejumlah list dasar
        for (String object : objectList) {
            Element el = root.addElement(objectType);
            el.addElement("text").addText(object);
        }

        return document.asXML();
    }

    public String convertListUntukSuratPerintahIntoXml(List<String> untukList) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");

        //loop sejumlah list dasar
        for (String untuk : untukList) {
            root.addElement("untuk")
                    .addText(untuk);
        }

        return document.asXML();
    }

}
