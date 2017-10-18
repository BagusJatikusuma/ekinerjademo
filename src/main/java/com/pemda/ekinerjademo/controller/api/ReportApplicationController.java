package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.report.NodinBean;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bagus on 17/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api/report")
public class ReportApplicationController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ReportApplicationController.class);

    @RequestMapping(value = "/create-nodin", method = RequestMethod.POST)
    ResponseEntity<?> createNodinReport() {
        LOGGER.info("create surat nodin");
        List<NodinBean> nodinBeans
                = new ArrayList<>();

        NodinBean nodinBean
                = new NodinBean(
                "Prof. Bagus",
                "Dr. Bagus",
                "141511036",
                "08/01/2017",
                "rahasia",
                "1 lampiran",
                "penting",
                "ini isi surat",
                "init penutup surat");
        nodinBeans.add(nodinBean);

        InputStream nodinReportStream
                = getClass().getClassLoader().getResourceAsStream("design_report_xml/Nodin.jrxml");

        if (nodinReportStream == null)
            return new ResponseEntity<Object>(new CustomMessage("nodin design not found"), HttpStatus.OK);

        try {
            JasperReport jasperReport
                    = JasperCompileManager.compileReport(nodinReportStream);

            JRSaver.saveObject(jasperReport, "nodinReport.jasper");
            JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(nodinBeans);

            JasperPrint jasperPrint = JasperFillManager.
                    fillReport(
                            jasperReport,
                            null,
                            beanCollectionDataSource);

            JRPdfExporter exporter = new JRPdfExporter();

            File employeeReportPdf = File.createTempFile(
                    "nodin_report.",
                    ".pdf",
                    new File("/home/pemkab"));

            JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(employeeReportPdf));

        } catch (JRException e) {
            return new ResponseEntity<Object>(new CustomMessage("error when compile design xml"), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<Object>(new CustomMessage("error create pdf file"), HttpStatus.OK);
        }


        return new ResponseEntity<Object>(new CustomMessage("nodin report created"), HttpStatus.OK);
    }

}
