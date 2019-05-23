package com.substituicao.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
    - Criação de métodos no DTO com uso @DATA, o DTO é um um padrão que visa transferir dados entre redes e ter o tamanho reduzido de um método.
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PessoaDTO {
    private Long id;
    private String nome;
    private String email;

}
