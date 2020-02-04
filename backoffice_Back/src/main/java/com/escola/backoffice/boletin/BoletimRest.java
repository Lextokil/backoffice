package com.escola.backoffice.boletin;

import com.escola.backoffice.reportservice.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("boletins")
@CrossOrigin(origins = {"http://localhost:5000"})
public class BoletimRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoletimRest.class);

    private final BoletimService boletimService;
    private final ReportService reportService;

    @Autowired
    public BoletimRest(BoletimService boletimService, ReportService reportService) {
        this.boletimService = boletimService;
        this.reportService = reportService;
    }


    @GetMapping("/{id}")
    public BoletimDTO findBoletimById(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return BoletimDTO.of(this.boletimService.findById(id));
    }

    @GetMapping("/all")
    public List<Boletim> findAllBoletins() {
        return boletimService.findAll();
    }

    @GetMapping("/all/{id}")
    public List<BoletimCompleto> findAllBoletinsByAluno(@PathVariable("id") Long id) {

        return boletimService.getBoletimCompletoAluno(id);
    }

    @GetMapping("/export/{id}")
    public void exportBoletim(HttpServletResponse response, @PathVariable("id") Long id) throws IOException, JRException {
        LOGGER.info("Recebendo exportação para boletim do aluno de ID: {}", id);
        reportService.exportReport(response, id);
    }

    @PutMapping("/{id}")
    public BoletimDTO udpate(@PathVariable("id") Long id, @RequestBody BoletimDTO boletimDTO) {
        LOGGER.info("Recebendo Update para boletim de ID: {}", id);
        LOGGER.debug("Payload: {}", boletimDTO);

        return this.boletimService.update(boletimDTO, id);
    }
    @PutMapping("/updateAll/{idAluno}")
    public List<BoletimDTO> udpateAllByAluno(@PathVariable("idAluno") Long idAluno, @RequestBody List<BoletimCompleto> boletimDTOS) {
        LOGGER.info("Recebendo Update all by aluno");
        LOGGER.debug("Payload: {}", boletimDTOS);

        return this.boletimService.convertAndUpdateAll(boletimDTOS, idAluno);
    }


}
