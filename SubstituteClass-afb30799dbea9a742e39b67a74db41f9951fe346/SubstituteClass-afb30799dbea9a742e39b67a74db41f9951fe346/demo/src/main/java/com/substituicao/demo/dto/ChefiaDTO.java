package com.substituicao.demo.dto;

import lombok.Data;
/*
    - Criação de métodos no DTO com uso @DATA, o DTO é um um padrão que visa transferir dados entre redes e ter o tamanho reduzido de um método.
*/
@Data
public class ChefiaDTO extends PessoaDTO{
    private String documentoServidor;

    public ChefiaDTO(int id, String nome, String email, String documentoServidor){
        super();
        this.documentoServidor = documentoServidor;
    }
}
