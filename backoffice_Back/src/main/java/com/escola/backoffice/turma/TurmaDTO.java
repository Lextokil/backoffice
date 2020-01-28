package com.escola.backoffice.turma;

import com.escola.backoffice.aluno.Aluno;
import com.escola.backoffice.professor.Professor;
import com.escola.backoffice.util.Turno;
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
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDTO {
    
    private Long id;
    
    @Size(max = 15, message = "O Turno não pode conter mais de 15 caracteres.")
    @NotNull(message = "O turno não pode ser nulo.")
    @NotBlank(message = "O turno pode estar em branco.")
    @Enumerated(EnumType.STRING)
    private Turno turno;

    private List<Long> alunos;

    private List<Long> professores;
    
    public static TurmaDTO of(Turma turma){
        List<Long> professores = turma.getProfessores().stream().map(Professor::getId).collect(Collectors.toList());
        List<Long> alunos = turma.getAlunos().stream().map(Aluno::getId).collect(Collectors.toList());
        return new TurmaDTO(
                turma.getId(),
                turma.getTurno(),
                alunos,
                professores);
    }
    
}
