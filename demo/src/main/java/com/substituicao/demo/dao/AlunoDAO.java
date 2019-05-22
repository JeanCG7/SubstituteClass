package com.substituicao.demo.dao;

import com.substituicao.demo.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoDAO extends JpaRepository<Aluno, Long> {
}
