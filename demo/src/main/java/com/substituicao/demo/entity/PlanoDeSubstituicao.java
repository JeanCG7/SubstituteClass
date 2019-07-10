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
public class PlanoDeSubstituicao implements Serializable {
    @Id @GeneratedValue
    private Long id;

    private String justificativa;

    @OneToOne
    private Docente requerente;

    @OneToOne
    private Aula aula;

    private boolean aprovacao;
}
