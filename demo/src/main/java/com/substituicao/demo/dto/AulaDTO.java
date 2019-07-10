package com.substituicao.demo.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class AulaDTO {
    private Long id;
    private Date data;
    private int aulas;
    private boolean concluida;
    private TurmaDTO turma;

    private List<AlunoDTO> listaDePresenca;
    private PlanoDeSubstituicaoDTO planoDeSubstituicaoDTO ;

}
