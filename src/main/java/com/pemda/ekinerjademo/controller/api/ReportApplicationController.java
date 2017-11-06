package com.pemda.ekinerjademo.controller.api;

import com.pemda.ekinerjademo.model.ekinerjamodel.NodinTemplateHistory;
import com.pemda.ekinerjademo.service.NodinTemplateHistoryService;
import com.pemda.ekinerjademo.util.EkinerjaXMLBuilder;
import com.pemda.ekinerjademo.wrapper.input.NodinTemplateInputWrapper;
import com.pemda.ekinerjademo.wrapper.output.CustomMessage;
import com.pemda.ekinerjademo.wrapper.output.NodinHistoryWrapper;
import com.pemda.ekinerjademo.wrapper.report.NodinBean;
import com.pemda.ekinerjademo.wrapper.report.NotaDinasBean;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bagus on 17/10/17.
 */
@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping(value = "/api/report")
public class ReportApplicationController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ReportApplicationController.class);

    @Autowired private NodinTemplateHistoryService nodinTemplateHistoryService;

    @RequestMapping(value = "/create-nodin", method = RequestMethod.POST)
    ResponseEntity<?> createNodin() {
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

    @RequestMapping(value = "/create-nodin-report", method = RequestMethod.POST)
    ResponseEntity<?> createNodinReport(
            @RequestBody NodinTemplateInputWrapper nodinTemplateWrapper) {
        LOGGER.info("create surat nodin");

        EkinerjaXMLBuilder ekinerjaXMLBuilder = new EkinerjaXMLBuilder();
        String tembusanListinXml
                = ekinerjaXMLBuilder.convertListTembusanSuratNodinIntoXml(nodinTemplateWrapper.getTembusanSurat());

        NodinTemplateHistory nodinTemplateHistory
                = new NodinTemplateHistory(
                        String.valueOf(new Date().getTime()),
                        nodinTemplateWrapper.getNipPegawai(),
                        nodinTemplateWrapper.getKdUnitKerja(),
                        nodinTemplateWrapper.getNmInstansi(),
                        nodinTemplateWrapper.getNomorSurat1(),
                        nodinTemplateWrapper.getNomorSurat2(),
                        nodinTemplateWrapper.getNomorSurat(),
                        Year.now().getValue(),
                        nodinTemplateWrapper.getNmTujuan(),
                        nodinTemplateWrapper.getNmPemberi(),
                        nodinTemplateWrapper.getHal(),
                        new Date(nodinTemplateWrapper.getTanggal()),
                        nodinTemplateWrapper.getPembukaSurat(),
                        nodinTemplateWrapper.getIsiSurat(),
                        nodinTemplateWrapper.getPenutupSurat(),
                        nodinTemplateWrapper.getNmLengkap(),
                        tembusanListinXml,
                        new Date().getTime(),
                        "");

        List<NotaDinasBean> beans = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        beans.add(new NotaDinasBean(
                nodinTemplateHistory.getNmInstansi(),
                nodinTemplateHistory.getNomorSurat1()+"/"

                        +nodinTemplateHistory.getNomorSurat2()+"/"
                        +nodinTemplateHistory.getNomorSurat3()+"/"+nodinTemplateHistory.getNomorSuratTahun(),
                nodinTemplateHistory.getNmTujuan(),
                nodinTemplateHistory.getNmPemberi(),
                nodinTemplateHistory.getHal(),
                df.format(nodinTemplateHistory.getTanggal()),
                nodinTemplateHistory.getPembukaSurat(),
                nodinTemplateHistory.getIsiSurat(),
                nodinTemplateHistory.getPenutupSurat(),
                nodinTemplateHistory.getNmLengkap(),
                nodinTemplateWrapper.getTembusanSurat().get(0)));

        InputStream nodinReportStream
                = getClass().getClassLoader().getResourceAsStream("design_report_xml/NotaDinas.jrxml");

        if (nodinReportStream == null)
            return new ResponseEntity<Object>(new CustomMessage("Nota Dinas design not found"), HttpStatus.OK);

        JasperReport jasperReport
                = null;
        try {
            jasperReport = JasperCompileManager.compileReport(nodinReportStream);

            JRSaver.saveObject(jasperReport, "nodinReport.jasper");
            JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(beans);

            JasperPrint jasperPrint = JasperFillManager.
                    fillReport(
                            jasperReport,
                            null,
                            beanCollectionDataSource);

            JRPdfExporter exporter = new JRPdfExporter();


            File employeeReportPdf
                    = File.createTempFile(
                        "nodin_report.",
                        ".pdf",
                        new File("/home/pemkab/project/documents/nodin"));

            JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(employeeReportPdf));
            nodinTemplateHistory.setDocumentFileName(employeeReportPdf.getName());
//            LOGGER.info(employeeReportPdf.getAbsolutePath());

        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nodinTemplateHistoryService.save(nodinTemplateHistory);
//        LOGGER.info(tembusanListinXml);

        return new ResponseEntity<Object>(
                new CustomMessage("nodin report created"),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/get-nodin-document-by-history/{kdHistory}", method = RequestMethod.GET)
    ResponseEntity<?> getNodinDocumentByKdHistory(@PathVariable("kdHistory") String kdHistory) {
        LOGGER.info("get nodin document by kd history : "+kdHistory);

        NodinTemplateHistory nodinTemplateHistory
                = nodinTemplateHistoryService.findByKdHistory(kdHistory);

        if (nodinTemplateHistory == null)
            return new ResponseEntity<Object>(new CustomMessage("dokumen tidak ditemukan"), HttpStatus.OK);

        byte[] nodinDocumentBytes;
        File nodinDocument
                = new File("/home/pemkab/project/documents/nodin/"+nodinTemplateHistory.getDocumentFileName());

        try {
            nodinDocumentBytes = Files.readAllBytes(nodinDocument.toPath());
        } catch (IOException e) {
            return new ResponseEntity<Object>(new CustomMessage("berkas dokumen gagal diproses"), HttpStatus.OK);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData(nodinTemplateHistory.getDocumentFileName(), nodinTemplateHistory.getDocumentFileName());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<Object>(nodinDocumentBytes, headers, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-report-nodin-history-by-nip/{nipPegawai}", method = RequestMethod.GET)
    ResponseEntity<?> getReportNodinHistoryByNipPegawai(@PathVariable("nipPegawai") String nipPegawai) {
        LOGGER.info("get report history by nip "+nipPegawai);

        List<NodinTemplateHistory> nodinTemplateHistories
                = nodinTemplateHistoryService.findByNipPegawai(nipPegawai);
        List<NodinHistoryWrapper> nodinHistoryWrapperList
                = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (NodinTemplateHistory nodinTemplateHistory : nodinTemplateHistories) {
            nodinHistoryWrapperList
                    .add(new NodinHistoryWrapper(
                            nodinTemplateHistory.getKdHistory(),
                            df.format(
                                    new Date(nodinTemplateHistory.getCreatedDateTimeMilis()))
                    ));
        }

        return new ResponseEntity<Object>(nodinHistoryWrapperList, HttpStatus.OK);
    }

}
