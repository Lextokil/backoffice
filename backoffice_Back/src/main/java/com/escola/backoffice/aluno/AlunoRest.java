package com.escola.backoffice.aluno;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = {"http://localhost:5000"})
@RestController
@RequestMapping("alunos")
public class AlunoRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlunoRest.class);

    private final AlunoService alunoService;

    @Autowired
    public AlunoRest(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public AlunoDTO save(@RequestBody AlunoDTO alunoDTO) {
        LOGGER.info("Recebendo solicitação de persistência para aluno...");
        LOGGER.debug("Payload: {}", alunoDTO);
        return this.alunoService.save(alunoDTO);
    }

    @GetMapping("/{id}")
    public AlunoDTO findAlunoDTOById(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return AlunoDTO.of(this.alunoService.findById(id));
    }

    @GetMapping("/allByTurma/{id}")
    public Set<AlunoDTO> findAllTurmas(@PathVariable("id") Long id) {
        LOGGER.info("Buscando alunos pelo id da turma:[{}]", id);
        return alunoService.findAllByTurma(id);
    }
    @GetMapping("/all")
    public List<AlunoDTO> findAll() {
        LOGGER.info("Buscando todos os alunos");
        return alunoService.findAll();
    }

    @PutMapping("/{id}")
    public AlunoDTO udpate(@PathVariable("id") Long id, @RequestBody AlunoDTO alunoDTO) {
        LOGGER.info("Recebendo Update para aluno de ID: {}", id);
        LOGGER.debug("Payload: {}", alunoDTO);

        return this.alunoService.update(alunoDTO, id);
    }
    @PutMapping("/updateAll")
    public List<AlunoDTO> udpateAll( @RequestBody List<AlunoDTO> alunoDTOs) {
        LOGGER.info("Recebendo Update para alunos");

        return this.alunoService.updateAll(alunoDTOs);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para aluno de ID: {}", id);
        this.alunoService.delete(id);
    }


}
