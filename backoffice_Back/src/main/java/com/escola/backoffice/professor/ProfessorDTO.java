package com.escola.backoffice.professor;

import com.escola.backoffice.turma.Turma;
import com.escola.backoffice.util.Materia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {

    private Long id;

    @Size(max = 50, message = "O nome não pode conter mais de 50 caracteres.")
    @NotNull(message = "O nome não pode ser nulo.")
    @NotBlank(message = "O nome não pode estar em branco.")
    private String nome;

    private List<Long> turmas;

    @Size(max = 50, message = "O materia não pode conter mais de 50 caracteres.")
    @NotNull(message = "O materia não pode ser nulo.")
    @NotBlank(message = "A materia não pode estar em branco.")
    @Enumerated(EnumType.STRING)
    private Materia materia;

    public static ProfessorDTO of(Professor professor){
        List<Long> turmas = professor.getTurmas().stream().map(Turma::getId).collect(Collectors.toList());
        return new ProfessorDTO(
                professor.getId(),
                professor.getNome(),
                turmas,
                professor.getMateria()
        );
    }
}
