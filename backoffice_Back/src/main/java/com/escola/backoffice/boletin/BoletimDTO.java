package com.escola.backoffice.boletin;

import com.escola.backoffice.materianota.MateriaNota;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoletimDTO {

    private Long id;

    @NotEmpty(message = "Boletim deve conter um aluno")
    @NotBlank(message = "Boletim deve conter um aluno")
    private Long aluno;

    private List<String> materias = new ArrayList<>();

    private List<Double> materiaNotas = new ArrayList<>();


    public static BoletimDTO of(Boletim boletim){
        List<Double> materiaNotas = boletim.getMateriaNotas().stream().map(MateriaNota::getNota).collect(Collectors.toList());
        List<String> materias = boletim.getMateriaNotas().stream().map(MateriaNota::getMateria).collect(Collectors.toList());
        return new BoletimDTO(
                boletim.getId(),
                boletim.getAluno().getId(),
                materias,
                materiaNotas
        );
    }

}
