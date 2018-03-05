package com.pemda.ekinerjademo.util;

import com.lowagie.text.Image;
import com.lowagie.text.pdf.BarcodeEAN;

/**
 * Created by bagus on 26/02/18.
 */
public class BarcodeGenerator {
    private BarcodeEAN barcodeEAN;

    public BarcodeGenerator() {
        this.barcodeEAN = new BarcodeEAN();
    }

    public Image generateBarcode(String kode) {
        barcodeEAN.setCodeType(barcodeEAN.EAN13);
        barcodeEAN.setCode(kode);

        Image barcodeImage = barcodeEAN.createImageWithBarcode(null,null,null);

        return barcodeEAN.createImageWithBarcode(null,null,null);
    }

}
