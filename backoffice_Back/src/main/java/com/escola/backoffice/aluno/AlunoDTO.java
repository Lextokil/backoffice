package com.escola.backoffice.aluno;

import com.escola.backoffice.boletin.Boletim;
import com.escola.backoffice.professor.Professor;
import com.escola.backoffice.util.Turno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {

    private Long id;

    @Size(max = 50, message = "O nome não pode conter mais de 50 caracteres.")
    @NotNull(message = "O nome não pode ser nulo.")
    @NotBlank(message = "O nome pode estar em branco.")
    @Enumerated(EnumType.STRING)
    private String nome;

    @Size(max = 15, message = "O Turno não pode conter mais de 15 caracteres.")
    @NotNull(message = "O turno não pode ser nulo.")
    @NotBlank(message = "O turno pode estar em branco.")
    @Enumerated(EnumType.STRING)
    private Turno turno;

    @NotEmpty(message = "O aluno deve ter professores")
    private List<Long> professores;

    @NotNull(message = "A turma não pode ser nula.")
    @NotBlank(message = "A turma pode estar em branco.")
    @Enumerated(EnumType.STRING)
    private Long turma;


    private  List<Long> boletins;



    public static AlunoDTO of(Aluno aluno){
        List<Long> professores = aluno.getProfessores().stream()
                .map(Professor::getId).collect(Collectors.toList());
        List<Long> boletins = aluno.getBoletins().stream()
                .map(Boletim::getId).collect(Collectors.toList());
        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getTurno(),
                professores,
                aluno.getTurma().getId(),
                boletins
                );
    }
}
