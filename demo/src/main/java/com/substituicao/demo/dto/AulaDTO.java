package com.substituicao.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AulaDTO {
    private long id;
    private String titulo;
    private Date data;
    private int aulas;
    private boolean concluida;
    private TurmaDTO turma;

    private List<AlunoDTO> listaDePresenca;
    private PlanoDeSubstituicaoDTO planoDeSubstituicaoDTO;

}
