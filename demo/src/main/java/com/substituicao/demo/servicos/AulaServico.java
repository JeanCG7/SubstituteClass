package com.substituicao.demo.servicos;

import com.substituicao.demo.dto.*;
import com.substituicao.demo.exception.TurmaNaoEncontradaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class AulaServico {
    private List<TurmaDTO> turmas;
    private List<DocenteDTO> docentes;
    private List<CursoDTO> cursos;
    private List<AlunoDTO> alunos;
    private List<AulaDTO> aulas;

    public AulaServico() {
        cursos = Stream.of(
                CursoDTO.builder().id(1).nome("Eng. de Software").build(),
                CursoDTO.builder().id(2).nome("Eng. de Computação").build(),
                CursoDTO.builder().id(3).nome("Análise e Desenv. de Sistemas").build()
                ).collect(Collectors.toList());

        docentes = Stream.of(
                DocenteDTO.builder().id(1).nome("Gabriel Costa").email("gabriel@utf.com").documentoDocente("DOC111").ativo(true).build(),
                DocenteDTO.builder().id(2).nome("Willian Watanabe").email("william@utf.com").documentoDocente("DOC222").ativo(true).build()
                ).collect(Collectors.toList());

        alunos = Stream.of(
                AlunoDTO.builder().id(1).RA("RA111").nome("Jean Carlos").email("jean@utf.com").dataMatricula(new Date()).curso(cursos.get(0)).build(),
                AlunoDTO.builder().id(2).RA("RA222").nome("Vitória Leite").email("vitoria@utf.com").dataMatricula(new Date()).curso(cursos.get(0)).build(),
                AlunoDTO.builder().id(3).RA("RA333").nome("Breno Angelotti").email("breno@utf.com").dataMatricula(new Date()).curso(cursos.get(1)).build(),
                AlunoDTO.builder().id(4).RA("RA444").nome("Kassia Catarine").email("kassia@utf.com").dataMatricula(new Date()).curso(cursos.get(1)).build(),
                AlunoDTO.builder().id(5).RA("RA555").nome("Javao Goulart").email("javao@utf.com").dataMatricula(new Date()).curso(cursos.get(2)).build(),
                AlunoDTO.builder().id(6).RA("RA666").nome("Mateus Merxo").email("merxo@utf.com").dataMatricula(new Date()).curso(cursos.get(2)).build()
                ).collect(Collectors.toList());

        turmas = Stream.of(
                TurmaDTO.builder().id(1).codigo("ES57").alunos(alunos).curso(cursos.get(0)).build(),
                TurmaDTO.builder().id(2).codigo("ES77").alunos(alunos).curso(cursos.get(1)).build(),
                TurmaDTO.builder().id(3).codigo("ES97").alunos(alunos).curso(cursos.get(2)).build()
                ).collect(Collectors.toList());

    }

    @GetMapping("/servico/aula")
    public ResponseEntity<List<AulaDTO>> listar() {
        return ResponseEntity.ok(aulas);
    }

    @GetMapping("/servico/aula/{id}")
    public ResponseEntity<AulaDTO> listarPorId(@PathVariable int id) {
        Optional<AulaDTO> aula = aulas.stream().filter(a -> a.getId() == id).findAny();
        return ResponseEntity.of(aula);
    }
    @PostMapping("/servico/aula")
    public ResponseEntity<AulaDTO> criar (@RequestBody AulaDTO aula) throws TurmaNaoEncontradaException {
        Optional<TurmaDTO> turmaExistente = turmas.stream().filter(t -> t.getId() == aula.getTurma().getId()).findAny();

        aula.setTurma(
                Optional.ofNullable(turmaExistente.get()).orElseThrow(() ->
                    new TurmaNaoEncontradaException("Turma com id: " + aula.getTurma().getId() + " não encontrada")));

        long maiorAulaId = Collections.max(aulas, Comparator.comparing(c -> c.getId())).getId();
        aula.setId(maiorAulaId + 1);
        aulas.add(aula);

        return ResponseEntity.status(201).body(aula);
    }

    @PutMapping("/servico/aula/{id}")
    public ResponseEntity<AulaDTO> atualizar (@PathVariable int id, @RequestBody AulaDTO aulaReq) throws TurmaNaoEncontradaException {
        Optional<AulaDTO> aula = aulas.stream().filter(a -> a.getId() == id).findAny();

        if(aula.isPresent())
        {
            Optional<TurmaDTO> turmaExistente = turmas.stream().filter(t -> t.getId() == aula.get().getTurma().getId()).findAny();
            aula.get().setTurma(
                    Optional.ofNullable(turmaExistente.get()).orElseThrow(() ->
                            new TurmaNaoEncontradaException("Turma com id: " + aula.get().getTurma().getId() + " não encontrada")));
            aula.get().setId(aulaReq.getId());
            aula.get().setAulas(aulaReq.getAulas());
            aula.get().setData(aulaReq.getData());
            aula.get().setListaDePresenca(aulaReq.getListaDePresenca());
            aula.get().setProfessor(aulaReq.getProfessor());
        }

        return ResponseEntity.of(aula);
    }

    @DeleteMapping("/servico/aula/{id}")
    public ResponseEntity deletar (@PathVariable int id) {
        if (aulas.removeIf(aulas -> aulas.getId() == id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
