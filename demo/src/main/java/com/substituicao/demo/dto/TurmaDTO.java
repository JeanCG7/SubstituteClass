package com.substituicao.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TurmaDTO {
    private long id;
    private String codigo;
    private DisciplinaDTO disciplina;
    private DocenteDTO docente;
    private List<AlunoDTO> alunos;
}
