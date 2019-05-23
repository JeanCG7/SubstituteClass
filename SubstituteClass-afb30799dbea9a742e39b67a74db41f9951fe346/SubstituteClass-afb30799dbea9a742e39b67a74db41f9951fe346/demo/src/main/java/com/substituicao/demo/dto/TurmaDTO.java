package com.substituicao.demo.dto;

import lombok.Data;

import java.util.List;
/*
    - Criação de métodos no DTO com uso @DATA, o DTO é um um padrão que visa transferir dados entre redes e ter o tamanho reduzido de um método.
*/
@Data
public class TurmaDTO {
    private long id;
    private String codigo;
    private CursoDTO curso;
    private List<AlunoDTO> alunos;
}
