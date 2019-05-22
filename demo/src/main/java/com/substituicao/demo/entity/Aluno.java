package com.substituicao.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Aluno extends Pessoa implements Serializable {
    @ManyToOne
    private Curso curso;
    private String RA;
    private Date dataMatricula;

}
