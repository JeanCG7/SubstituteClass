package com.substituicao.demo.servicos;

import com.substituicao.demo.dto.*;
import com.substituicao.demo.exception.ParametroNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class AulaServico {
	private List<TurmaDTO> turmas;
	private List<DocenteDTO> docentes;
	private List<DisciplinaDTO> disciplinas;
	private List<CursoDTO> cursos;
	private List<AlunoDTO> alunos;
	private List<AulaDTO> aulas;

	public AulaServico() {
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
		aulas = new ArrayList<>();
	}

	@GetMapping("/servico/aulas")
	public ResponseEntity<List<AulaDTO>> listar() {
		return ResponseEntity.ok(aulas);
	}

	@GetMapping("/servico/aulas/{id}")
	public ResponseEntity<AulaDTO> listarPorId(@PathVariable int id) {
		Optional<AulaDTO> aula = aulas.stream().filter(a -> a.getId() == id).findAny();
		return ResponseEntity.of(aula);
	}

	@PostMapping("/servico/aulas")
	public ResponseEntity<AulaDTO> criar(@RequestBody AulaDTO aula) throws ParametroNaoEncontradoException {
		Optional<TurmaDTO> turmaExistente = turmas.stream().filter(t -> t.getId() == aula.getTurmaId()).findAny();

		aula.setTurma(Optional.ofNullable(turmaExistente.get())
				.orElseThrow(() -> new ParametroNaoEncontradoException(aula.getTurma().getId(), "Turma")));

		long maiorAulaId = Collections.max(aulas, Comparator.comparing(c -> c.getId())).getId();
		aula.setId(maiorAulaId + 1);
		aulas.add(aula);

		return ResponseEntity.status(201).body(aula);
	}

	@PutMapping("/servico/aulas/{id}")
	public ResponseEntity<AulaDTO> atualizar(@PathVariable int id, @RequestBody AulaDTO aulaReq)
			throws ParametroNaoEncontradoException {
		Optional<AulaDTO> aula = aulas.stream().filter(a -> a.getId() == id).findAny();

		if (aula.isPresent()) {
			Optional<TurmaDTO> turmaExistente = turmas.stream().filter(t -> t.getId() == aula.get().getTurma().getId())
					.findAny();
			aula.get().setTurma(Optional.ofNullable(turmaExistente.get())
					.orElseThrow(() -> new ParametroNaoEncontradoException(aula.get().getTurma().getId(), "Turma")));
			aula.get().setId(aulaReq.getId());
			aula.get().setTitulo(aulaReq.getTitulo());
			aula.get().setAulas(aulaReq.getAulas());
			aula.get().setData(aulaReq.getData());
			aula.get().setListaDePresenca(aulaReq.getListaDePresenca());
		}

		return ResponseEntity.of(aula);
	}

	@DeleteMapping("/servico/aulas/{id}")
	public ResponseEntity deletar(@PathVariable int id) {
		if (aulas.removeIf(aula -> aula.getId() == id))
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.notFound().build();
	}
}
