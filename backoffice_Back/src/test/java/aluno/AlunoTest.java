package aluno;

import com.escola.backoffice.aluno.Aluno;
import com.escola.backoffice.aluno.AlunoDTO;
import com.escola.backoffice.aluno.AlunoService;
import com.escola.backoffice.aluno.IAlunoRepository;
import com.escola.backoffice.turma.ITurmaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = AlunoTest.class)
public class AlunoTest {
    @InjectMocks
    private AlunoService alunoService;

    public AlunoTest() {
    }


    @Test
    public void alunoDTONull() {
        assertThrows(NullPointerException.class, () -> {
            this.alunoService.save(null);
        });
    }
    @Test
    public void alunoDTONomeVazio() {
        assertThrows(NullPointerException.class, () -> {
            this.alunoService.save(new AlunoDTO("",1l));
        });
    }

    @Test
    public void verificarGeradorDeBoletins() {
        assertTrue(new Aluno().getBoletins().size() == 4);
    }

}
