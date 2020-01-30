package com.escola.backoffice.turma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","*"})
@RestController
@RequestMapping("turmas")
public class TurmaRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurmaRest.class);

    private final TurmaService turmaService;

    @Autowired
    public TurmaRest(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public TurmaDTO save(@RequestBody TurmaDTO turmaDTO){
        LOGGER.info("Recebendo solicitação de persistência para turma...");
        LOGGER.debug("Payload: {}", turmaDTO);
        return this.turmaService.save(turmaDTO);
    }

    @GetMapping("/{id}")
    public TurmaDTO findTurmaById(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return TurmaDTO.of(this.turmaService.findById(id));
    }

    @GetMapping("/all")
    public List<TurmaDTO> findAllTurmas(){
        return turmaService.findAll();
    }

    @PutMapping("/{id}")
    public TurmaDTO udpate(@PathVariable("id") Long id, @RequestBody TurmaDTO turmaDTO) {
        LOGGER.info("Recebendo Update para turma de ID: {}", id);
        LOGGER.debug("Payload: {}", turmaDTO);

        return this.turmaService.update(turmaDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para turma de ID: {}", id);

        this.turmaService.delete(id);
    }


}
