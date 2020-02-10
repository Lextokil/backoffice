package com.escola.backoffice.reportservice;

import com.escola.backoffice.boletin.BoletimCompleto;
import com.escola.backoffice.boletin.BoletimService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {


    @Autowired
    private BoletimService boletimService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

    public void exportReport(HttpServletResponse response, Long id) throws JRException, IOException {
        try {
            String fileName = "Boletim" + id + ".pdf";
            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
            OutputStream out = response.getOutputStream();
            List<BoletimCompleto> boletinsDoAluno = boletimService.getBoletimCompletoAluno(id);
            File file = ResourceUtils.getFile("classpath:boletim1.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(boletinsDoAluno);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdby", "BackOffice");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            LOGGER.info("Download de boletim concluido!");
        } catch (Exception e) {
            LOGGER.info("Erro ao exportar boletim");
            LOGGER.error(e.toString());
        }


    }
}
