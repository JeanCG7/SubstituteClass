package com.substituicao.demo.viewmodels;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AulaListModel
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AulaListModel {
    private Long id;
    private String titulo;
    private Date data;
    private int aulas;
    private String turmaNome;
}