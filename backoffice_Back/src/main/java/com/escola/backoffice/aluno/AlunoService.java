package com.escola.backoffice.aluno;

import com.escola.backoffice.boletin.IBoletimRepository;
import com.escola.backoffice.turma.TurmaService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AlunoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlunoService.class);
    private static final String ID_INEXISTENTE = "ID NÃO EXISTE: ";
    private final  IAlunoRepository iAlunoRepository;
    private final IBoletimRepository iBoletimRepository;
    private final TurmaService turmaService;

    public AlunoDTO save(AlunoDTO alunoDTO) {
        LOGGER.info("Salvando Aluno");
        LOGGER.debug("Aluno: {}", alunoDTO);
        Aluno alunoSave = new Aluno(
                alunoDTO.getId(),
                alunoDTO.getNome(),
                turmaService.findById(alunoDTO.getTurma())
        );

        alunoSave = this.iAlunoRepository.save(alunoSave);
        return AlunoDTO.of(alunoSave);
    }

    public Aluno findById(Long id) {
        LOGGER.info("Buscando aluno de id: {}", id);
        return this.iAlunoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ID_INEXISTENTE + id));
    }


    public List<AlunoDTO> findAll(){
        LOGGER.info("Buscando todos os alunos");
        List<AlunoDTO> alunos = new ArrayList<>();
        iAlunoRepository.findAll().forEach(aluno -> alunos.add(AlunoDTO.of(aluno)));
        return alunos;
    }

    public AlunoDTO update(AlunoDTO alunoDTO, Long id){
        LOGGER.info("Executando update para aluno de ID: [{}]", id);
        Aluno alunoUpdate = this.findById(id);
        alunoUpdate.setNome(alunoDTO.getNome());
        alunoUpdate.setBoletins(this.iBoletimRepository.findAllBoletimByIds(alunoDTO.getBoletins()));
        alunoUpdate.setTurma(this.turmaService.findById(alunoDTO.getTurma()));
        alunoUpdate = iAlunoRepository.save(alunoUpdate);

        return AlunoDTO.of(alunoUpdate);
    }
    public void delete(Long id) {
        LOGGER.info("Executando delete para aluno de ID: [{}]", id);
        this.iAlunoRepository.deleteById(id);
    }

}
