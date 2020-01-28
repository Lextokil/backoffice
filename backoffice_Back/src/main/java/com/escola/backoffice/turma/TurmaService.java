package com.escola.backoffice.turma;

import com.escola.backoffice.aluno.IAlunoRepository;
import com.escola.backoffice.professor.IProfessoreRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TurmaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurmaService.class);
    private static final String ID_INEXISTENTE = "ID NÃO EXISTE: ";
    private final ITurmaRepository iTurmaRepository;
    private final IProfessoreRepository iProfessoreRepository;
    private final IAlunoRepository iAlunoRepository;

    public TurmaDTO save(TurmaDTO turmaDTO) {
        LOGGER.info("Salvando Turma");
        LOGGER.debug("Turma: {}", turmaDTO);
        Turma turmaSave = new Turma(
                turmaDTO.getId(),
                turmaDTO.getTurno(),
                this.iAlunoRepository.findAllAlunosByIds(turmaDTO.getAlunos()),
                this.iProfessoreRepository.findAllProfessorByIds(turmaDTO.getProfessores())
        );

        turmaSave = this.iTurmaRepository.save(turmaSave);
        return TurmaDTO.of(turmaSave);
    }


    public Turma findById(Long id) {
        LOGGER.info("Buscando turma de id: {}", id);
        return this.iTurmaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ID_INEXISTENTE + id));
    }

    public List<Turma> findAll(){
        LOGGER.info("Buscando todas as turmas");
        return iTurmaRepository.findAll();
    }

    public TurmaDTO update(TurmaDTO turmaDTO, Long id){
        LOGGER.info("Executando update para turma de ID: [{}]", id);
        Turma turmaUpdate = this.findById(id);
        turmaUpdate.setTurno(turmaDTO.getTurno());
        turmaUpdate.setProfessores(this.iProfessoreRepository.findAllProfessorByIds(turmaDTO.getProfessores()));
        turmaUpdate.setAlunos(this.iAlunoRepository.findAllAlunosByIds(turmaDTO.getAlunos()));
        turmaUpdate = iTurmaRepository.save(turmaUpdate);
        return TurmaDTO.of(turmaUpdate);
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para turma de ID: [{}]", id);
        this.iTurmaRepository.deleteById(id);
    }

}
