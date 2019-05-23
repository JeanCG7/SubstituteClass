package com.substituicao.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
/*
    - Criação de métodos no DTO com uso @DATA, o DTO é um um padrão que visa transferir dados entre redes e ter o tamanho reduzido de um método.
    - 
*/


@Data
public class AlunoDTO extends PessoaDTO {
    private CursoDTO curso;
    private String RA;
    private Date dataMatricula;

    @Builder
    public AlunoDTO(int id, String nome, String email, CursoDTO curso, String RA, Date dataMatricula)
    {
        super();
        this.curso = curso;
        this.RA = RA;
        this.dataMatricula = dataMatricula;
    }
}
