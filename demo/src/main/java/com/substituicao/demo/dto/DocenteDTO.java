package com.substituicao.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class DocenteDTO extends PessoaDTO{
    private String documentoDocente;
    private boolean ativo;

    @Builder
    public DocenteDTO(int id, String nome, String email, String documentoDocente, boolean ativo)
    {
        super();
        this.documentoDocente = documentoDocente;
        this.ativo = ativo;
    }
}
