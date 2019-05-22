package com.substituicao.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

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
