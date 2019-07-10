package com.substituicao.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Aula implements Serializable {
    @Id @GeneratedValue
    private Long id;

    private String titulo;

    private Date data;

    private int aulas;

    private boolean concluida;

    @ManyToOne
    private Turma turma;

    @OneToMany
    @JoinTable
    (
        name="aula_alunos",
        joinColumns = { @JoinColumn(name="Aula_ID", referencedColumnName = "Id")},
        inverseJoinColumns = { @JoinColumn(name="Aluno_ID", referencedColumnName = "Id")}
    )
    private List<Aluno> listaDePresenca;
}
