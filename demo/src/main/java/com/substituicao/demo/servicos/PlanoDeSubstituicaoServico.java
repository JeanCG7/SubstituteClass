package com.substituicao.demo.servicos;

import com.substituicao.demo.dto.*;
import com.substituicao.demo.entity.PlanoDeSubstituicao;
import com.substituicao.demo.exception.LimiteAulaDiariaSubstituicaoException;
import com.substituicao.demo.exception.ParametroNaoEncontradoException;
import com.substituicao.demo.exception.RequerenteNaoAutorizadoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class PlanoDeSubstituicaoServico {

    private List<TurmaDTO> turmas;
    private List<DocenteDTO> requerentes;
    private List<DisciplinaDTO> disciplinas;
    private List<CursoDTO> cursos;
    private List<AlunoDTO> alunos;
    private List<AulaDTO> aulas;
    private List<PlanoDeSubstituicaoDTO> planos;

    public PlanoDeSubstituicaoServico() {
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

        requerentes = Stream.of(
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
                        .docente(requerentes.get(1)).build(),
                TurmaDTO.builder().id(1).codigo("ES57").alunos(alunos).disciplina(disciplinas.get(1))
                        .docente(requerentes.get(0)).build(),
                TurmaDTO.builder().id(2).codigo("ES77").alunos(alunos).disciplina(disciplinas.get(2))
                        .docente(requerentes.get(1)).build(),
                TurmaDTO.builder().id(3).codigo("ES97").alunos(alunos).disciplina(disciplinas.get(3))
                        .docente(requerentes.get(1)).build())
                .collect(Collectors.toList());

        aulas = Stream.of(
                AulaDTO.builder().id(gerarIdUnico()).titulo("Aula planejamento").data(new Date()).aulas(5).listaDePresenca(alunos).turma(turmas.get(0))
                        .concluida(false).build(),
                AulaDTO.builder().id(gerarIdUnico()).titulo("Repasse do trabalho").data(new Date()).aulas(5).listaDePresenca(alunos).turma(turmas.get(1))
                        .concluida(false).build(),
                AulaDTO.builder().id(gerarIdUnico()).titulo("Correção de prova").data(new Date()).aulas(6).listaDePresenca(alunos).turma(turmas.get(2))
                        .concluida(false).build()).
                collect(Collectors.toList());

        planos = Stream.of(
                PlanoDeSubstituicaoDTO.builder().id(gerarIdUnico()).justificativa("Porque é top").requerente(requerentes.get(0)).aprovado(false)
                        .aula(aulas.get(1)).build(),
                PlanoDeSubstituicaoDTO.builder().id(gerarIdUnico()).justificativa("Porque é bom").requerente(requerentes.get(0)).aprovado(false)
                        .aula(aulas.get(2)).build(),
                PlanoDeSubstituicaoDTO.builder().id(gerarIdUnico()).justificativa("Observacao de aula").requerente(requerentes.get(1)).aprovado(true)
                        .aula(aulas.get(3)).build())
                .collect(Collectors.toList());
    }
    @GetMapping("/servico/plano")
    public ResponseEntity<List<PlanoDeSubstituicaoDTO>> listar() {
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/servico/plano/{id}")
    public ResponseEntity<PlanoDeSubstituicaoDTO> listarPorId(@PathVariable int id) {
        Optional<PlanoDeSubstituicaoDTO> plano = planos.stream().filter(p -> p.getId() == id).findAny();
        return ResponseEntity.of(plano);
    }

    @PostMapping("/servico/plano")
    public ResponseEntity<PlanoDeSubstituicaoDTO> criar(PlanoDeSubstituicaoDTO plano) throws ParametroNaoEncontradoException, LimiteAulaDiariaSubstituicaoException {
        if (plano.getAula().getAulas() > 6)
            throw new LimiteAulaDiariaSubstituicaoException();

        Optional<AulaDTO> aulaExistente = aulas.stream().filter(a -> a.getId() == plano.getAula().getId()).findAny();
        Optional<DocenteDTO> requerenteExistente = requerentes.stream().filter(r -> r.getId() == plano.getRequerente().getId()).findAny();

        plano.setAula(
                Optional.ofNullable(aulaExistente.get()).orElseThrow(() ->
                        new ParametroNaoEncontradoException(plano.getAula().getId(), "Aula")));
        plano.setRequerente(
                Optional.ofNullable(requerenteExistente .get()).orElseThrow(() ->
                        new ParametroNaoEncontradoException(plano.getRequerente().getId(), "Requirinte")));
        plano.setAprovado(false);

        long maiorPlanoId = Collections.max(planos, Comparator.comparing(c -> c.getId())).getId();
        plano.setId(maiorPlanoId+1);
        planos.add(plano);

        return ResponseEntity.ok(plano);
    }

    @PutMapping("/servico/plano/{id}")
    public ResponseEntity<PlanoDeSubstituicaoDTO> atualizar(@PathVariable int id, @RequestBody PlanoDeSubstituicaoDTO planoReq) throws ParametroNaoEncontradoException{
        Optional<PlanoDeSubstituicaoDTO> plano = planos.stream().filter(p -> p.getId() == id).findAny();

        if (plano.isPresent()) {
            Optional<AulaDTO> aulaExistente = aulas.stream().filter(a -> a.getId() == plano.get().getAula().getId()).findAny();
            Optional<DocenteDTO> requerenteExistente = requerentes.stream().filter(r -> r.getId() == plano.get().getRequerente().getId()).findAny();
            plano.get().setAula(
                    Optional.ofNullable(aulaExistente.get()).orElseThrow(() ->
                            new ParametroNaoEncontradoException(plano.get().getAula().getId(), "Aula")));
            plano.get().setRequerente(
                    Optional.ofNullable(requerenteExistente.get()).orElseThrow(() ->
                            new ParametroNaoEncontradoException(plano.get().getRequerente().getId(), "Requirinte")));
            plano.get().setJustificativa(planoReq.getJustificativa());
        }
        return ResponseEntity.of(plano);
    }

    @PutMapping("/servico/plano/aprovar/{id}")
    public ResponseEntity mudarStatus(@PathVariable int id, @RequestBody DocenteDTO requerente) throws RequerenteNaoAutorizadoException {
        Optional<PlanoDeSubstituicaoDTO> plano = planos.stream().filter(p -> p.getId() == id).findAny();

        if (plano.get().getRequerente().getId() != requerente.getId())
            throw new RequerenteNaoAutorizadoException();

        plano.ifPresent(p -> {
            p.setAprovado(true);
        });

        return ResponseEntity.of(plano);
    }

    @DeleteMapping("/servico/plano/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        if (planos.removeIf(plano-> plano.getId() == id))
            return ResponseEntity.noContent().build();

        else
            return ResponseEntity.notFound().build();
    }

    private Long gerarIdUnico() {
        long val = -1;
        do {
            final UUID uid = UUID.randomUUID();
            final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
            buffer.putLong(uid.getLeastSignificantBits());
            buffer.putLong(uid.getMostSignificantBits());
            final BigInteger bi = new BigInteger(buffer.array());
            val = bi.longValue();
        }
        // We also make sure that the ID is in positive space, if its not we simply
        // repeat the process
        while (val < 0);
        return val;
    }
}
