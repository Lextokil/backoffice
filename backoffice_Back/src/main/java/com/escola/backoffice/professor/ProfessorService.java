package com.escola.backoffice.professor;

import com.escola.backoffice.turma.ITurmaRepository;
import com.escola.backoffice.turma.Turma;
import com.escola.backoffice.turma.TurmaService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProfessorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorService.class);
    private static final String ID_INEXISTENTE = "ID NÃO EXISTE: ";
    private final IProfessoreRepository iProfessoreRepository;
    private final ITurmaRepository iTurmaRepository;
    private final TurmaService turmaService;

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        LOGGER.info("Salvando professor");
        LOGGER.debug("Professor: {}", professorDTO);

        Professor professorSave = new Professor(
                professorDTO.getId(),
                professorDTO.getNome(),
                this.iTurmaRepository.findAllTurmasByIds(professorDTO.getTurmas()),
                professorDTO.getMateria()
        );

        professorSave = this.iProfessoreRepository.save(professorSave);
        return ProfessorDTO.of(professorSave);
    }

    public Professor findById(Long id) {
        LOGGER.info("Buscando professor de id: {}", id);
        return this.iProfessoreRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ID_INEXISTENTE + id));
    }

    public List<ProfessorDTO> findAll() {
        LOGGER.info("Buscando todas as professores");
        List<ProfessorDTO> professorDTOS = new ArrayList<>();
        iProfessoreRepository.findAll().forEach(professor -> professorDTOS.add(ProfessorDTO.of(professor)));
        return professorDTOS;
    }

    public Set<ProfessorDTO> findAllByTurma(Long idturma) {
        LOGGER.info("Buscando todas as professores");
        Turma turma = this.turmaService.findById(idturma);
        Set<ProfessorDTO> professorDTOS = iProfessoreRepository.findAllByTurmas(turma);
        return professorDTOS;
    }

    public ProfessorDTO update(ProfessorDTO professorDTO, Long id) {
        LOGGER.info("Executando update para professor de ID: [{}]", id);
        Professor professorUpdate = this.findById(id);
        if (!(ProfessorDTO.of(professorUpdate).equals(professorDTO))) {
            professorUpdate.setMateria(professorDTO.getMateria());
            professorUpdate.setNome(professorDTO.getNome());
            professorUpdate.setTurmas(this.iTurmaRepository.findAllTurmasByIds(professorDTO.getTurmas()));
            professorUpdate = iProfessoreRepository.save(professorUpdate);
        }

        return ProfessorDTO.of(professorUpdate);

    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para professor de ID: [{}]", id);
        this.iTurmaRepository.deleteById(id);
    }

    public List<ProfessorDTO> updateAll(List<ProfessorDTO> professorDTOS) {
        List<ProfessorDTO> professorDTOS1 = new ArrayList<>();
        professorDTOS.forEach(professorDTO -> professorDTOS1.add(this.update(professorDTO, professorDTO.getId())));

        return professorDTOS1;
    }
}
