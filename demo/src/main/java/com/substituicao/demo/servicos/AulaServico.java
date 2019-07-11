package com.substituicao.demo.servicos;

import com.substituicao.demo.dto.*;
import com.substituicao.demo.exception.ParametroNaoEncontradoException;
import com.substituicao.demo.viewmodels.AulaListModel;
import com.substituicao.demo.viewmodels.AulaModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.nio.ByteBuffer;
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
				TurmaDTO.builder().id(2).codigo("ES77").alunos(alunos).disciplina(disciplinas.get(2))
						.docente(docentes.get(1)).build(),
				TurmaDTO.builder().id(3).codigo("ES97").alunos(alunos).disciplina(disciplinas.get(3))
						.docente(docentes.get(1)).build())
				.collect(Collectors.toList());

		aulas = Stream.of(
				AulaDTO.builder().id(gerarIdUnico()).titulo("Aula planejamento").data(new Date()).aulas(5).listaDePresenca(alunos).turma(turmas.get(0))
						.concluida(false).build(),
				AulaDTO.builder().id(gerarIdUnico()).titulo("Repasse do trabalho").data(new Date()).aulas(5).listaDePresenca(alunos).turma(turmas.get(1))
						.concluida(false).build(),
				AulaDTO.builder().id(gerarIdUnico()).titulo("Correção de prova").data(new Date()).aulas(6).listaDePresenca(alunos).turma(turmas.get(2))
						.concluida(false).build()).
				collect(Collectors.toList());
	}

	@GetMapping("/servico/aulas")
	public ResponseEntity<List<AulaListModel>> listar() {
		List<AulaListModel> aulaListModels = new ArrayList<>();
		for (AulaDTO aula : aulas) {
			String turmaNome = new String(aula.getTurma().getCodigo().toString() + " - "
					+ aula.getTurma().getDisciplina().getNome().toString());
			aulaListModels.add(AulaListModel.builder().id(aula.getId()).titulo(aula.getTitulo()).data(aula.getData())
					.aulas(aula.getAulas()).turmaNome(turmaNome).build());
		}
		return ResponseEntity.ok(aulaListModels);
	}

	@GetMapping("/servico/aulas/{id}")
	public ResponseEntity<AulaDTO> listarPorId(@PathVariable Long id) {
		Optional<AulaDTO> aula = aulas.stream().filter(a -> a.getId() == id).findAny();
		return ResponseEntity.of(aula);
	}

	@PostMapping("/servico/aulas")
	public ResponseEntity<Long> criar(@RequestBody AulaModel aulaModel) throws ParametroNaoEncontradoException {
		Optional<TurmaDTO> turmaExistente = turmas.stream().filter(t -> t.getId() == aulaModel.getTurmaId()).findAny();
		AulaDTO aula = AulaDTO.builder().id(gerarIdUnico()).titulo(aulaModel.getTitulo()).data(aulaModel.getData())
				.aulas(aulaModel.getAulas())
				.turma(Optional.ofNullable(turmaExistente.get())
						.orElseThrow(() -> new ParametroNaoEncontradoException(aulaModel.getTurmaId(), "Turma")))
				.build();
		this.aulas.add(aula);
		return ResponseEntity.status(201).body(new Long(1));
	}

	@PutMapping("/servico/aulas/{id}")
	public ResponseEntity<AulaDTO> atualizar(@PathVariable Long id, @RequestBody AulaModel aulaReq)
			throws ParametroNaoEncontradoException {
		Optional<AulaDTO> aula = aulas.stream().filter(a -> a.getId() == id).findAny();

		if (aula.isPresent()) {
			Optional<TurmaDTO> turmaExistente = turmas.stream().filter(t -> t.getId() == aulaReq.getTurmaId())
					.findAny();
			aula.get().setTurma(Optional.ofNullable(turmaExistente.get())
					.orElseThrow(() -> new ParametroNaoEncontradoException(aula.get().aulaReq.getTurmaId(), "Turma")));
			aula.get().setTitulo(aulaReq.getTitulo());
			aula.get().setAulas(aulaReq.getAulas());
			aula.get().setData(aulaReq.getData());
		}

		return ResponseEntity.of(aula);
	}

	@DeleteMapping("/servico/aulas/{id}")
	public ResponseEntity deletar(@PathVariable long id) {
		if (aulas.removeIf(aula -> aula.getId() == id))
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
