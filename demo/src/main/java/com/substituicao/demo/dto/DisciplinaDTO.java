package com.substituicao.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DisciplinaDTO {
    private long id;
    private String nome;
    private CursoDTO curso;
}
