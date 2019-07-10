package com.substituicao.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoDTO {
    private long id;
    private String nome;
}
