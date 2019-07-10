package com.substituicao.demo.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TurmaModel
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaModel {
    private Long id;
    private String codigo;
    private String disciplinaNome;
}