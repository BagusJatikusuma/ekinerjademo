package com.pemda.ekinerjademo.util;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 05/11/17.
 */
public class EkinerjaXMLParser {
    public List<String> convertXmlTembusanNodinIntoListOfString(String tembusanXml) {
        List<String> tembusanNodinList = new ArrayList<>();
        Document document;
        SAXReader saxReader = new SAXReader();

        try {
            document = saxReader.read(new StringReader(tembusanXml));
            Element rootElement = document.getRootElement();

            List<Node> nodes = document.selectNodes("/root/tembusanSurat");

            for (Node node : nodes) {
                tembusanNodinList
                        .add(node.valueOf("tembusanSurat"));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return tembusanNodinList;
    }
}
