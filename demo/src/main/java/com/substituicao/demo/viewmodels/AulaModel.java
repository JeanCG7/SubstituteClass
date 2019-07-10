package com.substituicao.demo.viewmodels;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AulaModel
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AulaModel {
    private Long id;
    private String titulo;
    private Date data;
    private int aulas;
    private int turmaId;
}