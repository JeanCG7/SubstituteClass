package com.substituicao.demo.dto;

public class CoordenadorCursoDTO extends PessoaDTO{
    private CursoDTO curso;

    public CoordenadorCursoDTO(int id, String nome, String email, CursoDTO curso)
    {
        super();
        this.curso = curso;

    }
}
