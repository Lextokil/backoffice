package com.escola.backoffice.aluno;

import com.escola.backoffice.boletin.Boletim;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlunoDTO {

    private Long id;

    @Size(max = 50, message = "O nome não pode conter mais de 50 caracteres.")
    @NotNull(message = "O nome não pode ser nulo.")
    @NotBlank(message = "O nome não pode estar em branco.")
    @Enumerated(EnumType.STRING)
    private String nome;

    @NotNull(message = "A turma não pode ser nula.")
    @NotBlank(message = "A turma não pode estar em branco.")
    @Enumerated(EnumType.STRING)
    private Long turma;


    private  List<Long> boletins;

    public AlunoDTO( String nome, Long turma) {
        this.nome = nome;
        this.turma = turma;
    }


    public static AlunoDTO of(Aluno aluno){
        List<Long> boletins = aluno.getBoletins().stream()
                .map(Boletim::getId).collect(Collectors.toList());

        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getTurma().getId(),
                boletins
                );
    }
}
