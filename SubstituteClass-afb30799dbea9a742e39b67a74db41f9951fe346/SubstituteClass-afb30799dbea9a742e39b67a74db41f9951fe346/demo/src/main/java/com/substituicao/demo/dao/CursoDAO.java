package com.substituicao.demo.dao;

import com.substituicao.demo.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoDAO extends JpaRepository<Curso, Long> {
}
