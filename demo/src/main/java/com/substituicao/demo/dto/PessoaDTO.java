package com.substituicao.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PessoaDTO {
    private Long id;
    private String nome;
    private String email;

}
