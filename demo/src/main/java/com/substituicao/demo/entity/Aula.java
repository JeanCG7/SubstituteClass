package com.substituicao.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Aula implements Serializable {
    @Id @GeneratedValue
    private Long id;

    //private Date data;

    @OneToOne
    private Docente professor;

    @ManyToOne
    private Turma turma;

    @ManyToMany
    private List<Aluno> listaDePresenca;

    @OneToOne
    private PlanoDeSubstituicao planoDeSubstituicao;
}
