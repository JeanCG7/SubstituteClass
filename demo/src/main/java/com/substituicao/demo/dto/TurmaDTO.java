package com.substituicao.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class TurmaDTO {
    private long id;
    private String codigo;
    private CursoDTO curso;
    private List<AlunoDTO> alunos;
}
