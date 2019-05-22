package com.substituicao.demo.dto;

import lombok.Data;

@Data
public class ChefiaDTO extends PessoaDTO{
    private String documentoServidor;

    public ChefiaDTO(int id, String nome, String email, String documentoServidor){
        super();
        this.documentoServidor = documentoServidor;
    }
}
