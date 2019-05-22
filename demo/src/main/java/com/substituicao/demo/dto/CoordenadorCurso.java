package com.substituicao.demo.dto;

public class CoordenadorCurso extends PessoaDTO{
    private CursoDTO curso;

    public CoordenadorCurso(int id, String nome, String email, CursoDTO curso)
    {
        super();
        this.curso = curso;

    }
}
