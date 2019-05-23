package com.substituicao.demo.dto;

import com.substituicao.demo.dao.PlanoDeSubstituicaoDAO;
import com.substituicao.demo.entity.PlanoDeSubstituicao;

import java.util.Date;
import java.util.List;

public class AulaDTO {
    private Long id;
    private Date data;
    private int aulas;
    private boolean concluida;
    private DocenteDTO professor;
    private TurmaDTO turma;

    private List<AlunoDTO> listaDePresenca;
    private PlanoDeSubstituicaoDTO planoDeSubstituicaoDTO ;

}
