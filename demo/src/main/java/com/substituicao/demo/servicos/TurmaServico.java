package com.substituicao.demo.servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.substituicao.demo.dto.AlunoDTO;
import com.substituicao.demo.dto.CursoDTO;
import com.substituicao.demo.dto.DisciplinaDTO;
import com.substituicao.demo.dto.DocenteDTO;
import com.substituicao.demo.dto.TurmaDTO;
import com.substituicao.demo.viewmodels.TurmaModel;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TurmaServico
 */
@Controller
public class TurmaServico {
    private List<TurmaDTO> turmas;
    private List<DocenteDTO> docentes;
    private List<DisciplinaDTO> disciplinas;
    private List<CursoDTO> cursos;
    private List<AlunoDTO> alunos;

    public TurmaServico() {
        cursos = Stream
                .of(CursoDTO.builder().id(1).nome("Eng. de Software").build(),
                        CursoDTO.builder().id(2).nome("Eng. de Computação").build(),
                        CursoDTO.builder().id(3).nome("Análise e Desenv. de Sistemas").build())
                .collect(Collectors.toList());

        disciplinas = Stream
                .of(DisciplinaDTO.builder().id(1).nome("Arquitetura de software").curso(cursos.get(0)).build(),
                        DisciplinaDTO.builder().id(2).nome("Programação web 2").curso(cursos.get(0)).build(),
                        DisciplinaDTO.builder().id(3).nome("Banco de dados").curso(cursos.get(1)).build(),
                        DisciplinaDTO.builder().id(4).nome("Comunicação oral e escrita").curso(cursos.get(2)).build())
                .collect(Collectors.toList());

        docentes = Stream.of(
                DocenteDTO.builder().id(1).nome("Gabriel Costa").email("gabriel@utf.com").documentoDocente("DOC111")
                        .ativo(true).build(),
                DocenteDTO.builder().id(2).nome("Willian Watanabe").email("william@utf.com").documentoDocente("DOC222")
                        .ativo(true).build())
                .collect(Collectors.toList());

        alunos = Stream.of(
                AlunoDTO.builder().id(1).RA("RA111").nome("Jean Carlos").email("jean@utf.com").dataMatricula(new Date())
                        .curso(cursos.get(0)).build(),
                AlunoDTO.builder().id(2).RA("RA222").nome("Vitória Leite").email("vitoria@utf.com")
                        .dataMatricula(new Date()).curso(cursos.get(0)).build(),
                AlunoDTO.builder().id(3).RA("RA333").nome("Breno Angelotti").email("breno@utf.com")
                        .dataMatricula(new Date()).curso(cursos.get(1)).build(),
                AlunoDTO.builder().id(4).RA("RA444").nome("Kassia Catarine").email("kassia@utf.com")
                        .dataMatricula(new Date()).curso(cursos.get(1)).build(),
                AlunoDTO.builder().id(5).RA("RA555").nome("Javao Goulart").email("javao@utf.com")
                        .dataMatricula(new Date()).curso(cursos.get(2)).build(),
                AlunoDTO.builder().id(6).RA("RA666").nome("Mateus Merxo").email("merxo@utf.com")
                        .dataMatricula(new Date()).curso(cursos.get(2)).build())
                .collect(Collectors.toList());

        turmas = Stream.of(
                TurmaDTO.builder().id(1).codigo("ES57").alunos(alunos).disciplina(disciplinas.get(0))
                        .docente(docentes.get(1)).build(),
                TurmaDTO.builder().id(1).codigo("ES57").alunos(alunos).disciplina(disciplinas.get(1))
                        .docente(docentes.get(0)).build(),
                TurmaDTO.builder().id(2).codigo("ES77").alunos(alunos).disciplina(disciplinas.get(2))
                        .docente(docentes.get(1)).build(),
                TurmaDTO.builder().id(3).codigo("ES97").alunos(alunos).disciplina(disciplinas.get(3))
                        .docente(docentes.get(1)).build())
                .collect(Collectors.toList());
    }

    @GetMapping("/servico/turmas")
    public ResponseEntity<List<TurmaModel>> listar() {
        List<TurmaModel> turmaModels = new ArrayList<>();
        for (TurmaDTO turma : turmas) {
            turmaModels.add(TurmaModel.builder().id(turma.getId()).codigo(turma.getCodigo())
                    .disciplinaNome(turma.getDisciplina().getNome()).build());
        }
        return ResponseEntity.ok(turmaModels);
    }
}