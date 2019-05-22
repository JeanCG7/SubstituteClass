package com.substituicao.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanoDeSubstituicaoDTO {

    private Long id;

    private String justificativa;

    private DocenteDTO requirinte;

    private AulaDTO aula;

    private boolean aprovado;
}
