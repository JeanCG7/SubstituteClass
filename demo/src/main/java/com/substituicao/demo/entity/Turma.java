package com.substituicao.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Turma implements Serializable {
    @Id @GeneratedValue
    private long id;
    private String codigo;

    @ManyToOne
    private Disciplina disciplina;

    @ManyToOne
    private Docente docente;

    @OneToMany
    @JoinTable
    (
         name="turma_alunos",
         joinColumns = { @JoinColumn(name="Turma_ID", referencedColumnName = "Id")},
         inverseJoinColumns = { @JoinColumn(name="Aluno_ID", referencedColumnName = "Id")}
    )
    private List<Aluno> alunos;
}
