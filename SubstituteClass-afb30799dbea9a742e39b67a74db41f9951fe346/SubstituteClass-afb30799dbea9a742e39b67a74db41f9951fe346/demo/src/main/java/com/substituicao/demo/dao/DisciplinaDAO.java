package com.substituicao.demo.dao;

import com.substituicao.demo.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaDAO extends JpaRepository<Disciplina, Long> {
}
