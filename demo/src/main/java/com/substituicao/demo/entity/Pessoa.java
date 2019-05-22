package com.substituicao.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@MappedSuperclass
public abstract class Pessoa implements Serializable {
    @Id @GeneratedValue
    protected Long id;
    protected String nome;
    protected String email;
}
