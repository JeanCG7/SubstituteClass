package com.substituicao.demo.dao;

import com.substituicao.demo.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaDAO extends JpaRepository<Turma, Long> {
}
