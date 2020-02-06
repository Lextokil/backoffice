package com.escola.backoffice.professor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = {"http://localhost:5000"})
@RestController
@RequestMapping("professores")
public class ProfessoreRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessoreRest.class);

    private final ProfessorService professorService;

    @Autowired
    public ProfessoreRest(ProfessorService professorService) {
        this.professorService = professorService;
    }


    @PostMapping
    public ProfessorDTO save(@RequestBody ProfessorDTO professorDTO){
        LOGGER.info("Recebendo solicitação de persistência para professor...");
        LOGGER.debug("Payload: {}", professorDTO);
        return this.professorService.save(professorDTO);
    }

    @GetMapping("/{id}")
    public ProfessorDTO findProfessorById(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return ProfessorDTO.of(this.professorService.findById(id));
    }

    @GetMapping("/all")
    public List<ProfessorDTO> findAllProfessores(){
        return professorService.findAll();
    }

    @GetMapping("/allByTurma/{idturma}")
    public Set<ProfessorDTO> findAllProfessoresByTurma(@PathVariable("idturma") Long idturma) {
        return professorService.findAllByTurma(idturma);
    }

    @PutMapping("/{id}")
    public ProfessorDTO udpate(@PathVariable("id") Long id, @RequestBody ProfessorDTO professorDTO) {
        LOGGER.info("Recebendo Update para professor de ID: {}", id);
        LOGGER.debug("Payload: {}", professorDTO);

        return this.professorService.update(professorDTO, id);
    }
    @PutMapping("/updateAll")
    public List<ProfessorDTO> udpateAll( @RequestBody List<ProfessorDTO> professorDTOS) {
        LOGGER.info("Recebendo Update All");
        LOGGER.debug("Payload: {}", professorDTOS);

        return this.professorService.updateAll(professorDTOS);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para professor de ID: {}", id);

        this.professorService.delete(id);
    }

}
