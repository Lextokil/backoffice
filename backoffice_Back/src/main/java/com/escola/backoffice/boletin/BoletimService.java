package com.escola.backoffice.boletin;

import com.escola.backoffice.aluno.Aluno;
import com.escola.backoffice.aluno.AlunoService;
import com.escola.backoffice.util.Materia;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoletimService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoletimService.class);
    private static final String ID_INEXISTENTE = "ID NÃO EXISTE: ";
    private final IBoletimRepository iBoletimRepository;
    private final AlunoService alunoService;

    public BoletimDTO save(BoletimDTO boletimDTO) {
        LOGGER.info("Salvando boletim");
        LOGGER.debug("Boletim: {}", boletimDTO);

        Boletim boletimSave = new Boletim(
                boletimDTO.getId(),
                alunoService.findById(boletimDTO.getAluno())
        );
        boletimSave = iBoletimRepository.save(boletimSave);
        return BoletimDTO.of(boletimSave);
    }

    public Boletim findById(Long id) {
        LOGGER.info("Buscando boletim de id: {}", id);
        return this.iBoletimRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ID_INEXISTENTE + id));
    }

    public List<Boletim> findAll() {
        LOGGER.info("Buscando todos os boletins");
        return iBoletimRepository.findAll();
    }

    public BoletimDTO update(BoletimDTO boletimDTO, Long id) {
        LOGGER.info("Executando update para boletim de ID: [{}]", id);
        Boletim boletimUpdate = this.findById(id);
        for (int i = 0; i < boletimUpdate.getMateriaNotas().size(); i++) {
            boletimUpdate.getMateriaNotas().get(i).setNota(boletimDTO.getMateriaNotas().get(i));
        }
        boletimUpdate = iBoletimRepository.save(boletimUpdate);
        return BoletimDTO.of(boletimUpdate);
    }

    public List<BoletimDTO> findAllByAluno(Long id) {
        Aluno alunoRef = this.alunoService.findById(id);
        List<BoletimDTO> boletinsDoAluno = iBoletimRepository.findAllByAluno(alunoRef);
        return boletinsDoAluno;
    }

    public List<BoletimCompleto> getBoletimCompletoAluno(Long id) {
        List<BoletimDTO> boletins = this.findAllByAluno(id);
        List<BoletimCompleto> boletinsCompletos = new ArrayList<>();
        for (int i = 0; i < Materia.values().length; i++) {
            BoletimCompleto bc = new BoletimCompleto();
            bc.setMaterias(boletins.get(0).getMaterias().get(i));
            bc.setBim1(boletins.get(0).getMateriaNotas().get(i));
            bc.setBim2(boletins.get(1).getMateriaNotas().get(i));
            bc.setBim3(boletins.get(2).getMateriaNotas().get(i));
            bc.setBim4(boletins.get(3).getMateriaNotas().get(i));
            Double media = (bc.getBim1() + bc.getBim2() + bc.getBim3() + bc.getBim4()) / 4;
            bc.setMedia(media);
            boletinsCompletos.add(bc);
        }
        return boletinsCompletos;
    }

    public void delete(Long id) {
    }
}
