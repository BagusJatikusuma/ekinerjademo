package com.pemda.ekinerjademo.util;

import com.lowagie.text.pdf.BarcodeEAN;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by bagus on 26/02/18.
 */
public class BarcodeGenerator {
    public static final Logger LOGGER = LoggerFactory.getLogger(BarcodeGenerator.class);

    private BarcodeEAN barcodeEAN;

    public BarcodeGenerator() {
        this.barcodeEAN = new BarcodeEAN();
    }

    public Image generateBarcode(String kode) {
        barcodeEAN.setCodeType(barcodeEAN.EAN13);
        barcodeEAN.setCode(kode);

        Barcode barcode = null;
        try {
            barcode = BarcodeFactory.createCode128(kode);
        } catch (BarcodeException e) {
            e.printStackTrace();
            LOGGER.error("error when generate barcode barbecue");
        }


//        Image barcodeImage = barcodeEAN.createImageWithBarcode(null,null,null);

//        Image awtImage = barcodeEAN.createAwtImage(Color.BLACK, Color.WHITE);
        Image awtImage = null;
        try {
            awtImage = BarcodeImageHandler.getImage(barcode);
        } catch (OutputException e) {
            e.printStackTrace();
            LOGGER.info("error get awt image from barbecue Barcode");
        }

        return awtImage;
    }

    public String convertBarcodeImageIntoBase64String(Image barcodeImage) {
        String imageStringBase64 = null;

        BufferedImage bImage = convertImageIntoBufferedImage(barcodeImage);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(bImage, "png", bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageStringBase64 = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            LOGGER.info("error write image from buffered image");
        }

        return imageStringBase64;
    }

    public BufferedImage convertImageIntoBufferedImage(Image img) {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        return new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

    }

}
