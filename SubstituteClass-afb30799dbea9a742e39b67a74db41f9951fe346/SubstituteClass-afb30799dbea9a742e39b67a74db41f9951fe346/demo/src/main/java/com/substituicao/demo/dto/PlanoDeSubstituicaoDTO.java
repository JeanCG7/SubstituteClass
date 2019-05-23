package com.substituicao.demo.dto;

import lombok.Builder;
import lombok.Data;
/*
    - Criação de métodos no DTO com uso @DATA, o DTO é um um padrão que visa transferir dados entre redes e ter o tamanho reduzido de um método.
*/
@Data
@Builder
public class PlanoDeSubstituicaoDTO {

    private Long id;

    private String justificativa;

    private DocenteDTO requirinte;

    private AulaDTO aula;

    private boolean aprovado;
}
