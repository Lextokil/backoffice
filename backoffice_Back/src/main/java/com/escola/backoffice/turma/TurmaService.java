package com.escola.backoffice.turma;

import com.escola.backoffice.aluno.Aluno;
import com.escola.backoffice.aluno.IAlunoRepository;
import com.escola.backoffice.professor.IProfessoreRepository;
import com.escola.backoffice.professor.Professor;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public List<TurmaDTO> findAll() {
        LOGGER.info("Buscando todas as turmas");
        List<TurmaDTO> turmas = new ArrayList<>();
        iTurmaRepository.findAll().forEach(turma -> turmas.add(TurmaDTO.of(turma)));
        return turmas;
    }

    @Transactional
    public TurmaDTO update(TurmaDTO turmaDTO, Long id) {
        LOGGER.info("Executando update para turma de ID: [{}]", id);
        Turma turmaUpdate = this.findById(id);
        Turma finalTurmaUpdate = turmaUpdate;
        turmaUpdate.getProfessores().forEach(professor -> {
            if (!turmaDTO.getProfessores().contains(professor.getId())) {
                professor.getTurmas().remove(finalTurmaUpdate);
            }
        });
        if (!(TurmaDTO.of(turmaUpdate).equals(turmaDTO))) {
            turmaUpdate.setTurno(turmaDTO.getTurno());
            turmaUpdate.getProfessores().clear();
            Set<Professor> professores = this.iProfessoreRepository.findAllProfessorByIds(turmaDTO.getProfessores());
            professores.forEach(professor -> professor.getTurmas().add(finalTurmaUpdate));
            turmaUpdate.getProfessores().addAll(professores);
            turmaUpdate.getAlunos().clear();
            Set<Aluno> alunos = this.iAlunoRepository.findAllAlunosByIds(turmaDTO.getAlunos());
            alunos.forEach(aluno -> aluno.setTurma(finalTurmaUpdate));
            turmaUpdate.getAlunos().addAll(alunos);
            turmaUpdate = iTurmaRepository.save(turmaUpdate);
        } else {
            LOGGER.info("Não há nenhuma alteração para turma de ID: [{}]", id);
        }

        return TurmaDTO.of(turmaUpdate);
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para turma de ID: [{}]", id);
        this.iTurmaRepository.deleteById(id);
    }

    public List<TurmaDTO> updateAll(List<TurmaDTO> turmaDTO) {
        List<TurmaDTO> turmaDTOList = new ArrayList<>();
        turmaDTO.forEach(turmaDTO1 -> turmaDTOList.add(this.update(turmaDTO1, turmaDTO1.getId())));
        return turmaDTOList;
    }
}
