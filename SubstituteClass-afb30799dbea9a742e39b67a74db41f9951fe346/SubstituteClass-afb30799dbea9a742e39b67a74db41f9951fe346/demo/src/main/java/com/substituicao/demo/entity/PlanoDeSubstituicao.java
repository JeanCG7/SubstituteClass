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

    @OneToOne //aqui deveria ter uma relação de um-para-muitos um professor para muitos planosDeSubstituicao
    private Docente requirinte;

    @OneToOne // aqui deveria ter uma relação de um-para-muitos, um planoDeSubstituicao para muitas aulas
    private Aula aula;

    private boolean aprovacao;
}
