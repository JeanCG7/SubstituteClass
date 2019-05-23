package com.substituicao.demo.dto;

import lombok.Builder;

@Builder
public class DisciplinaDTO {
    private long id;
    private String nome;
    private CursoDTO curso;
}
