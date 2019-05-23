package com.substituicao.demo.dao;

import com.substituicao.demo.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteDAO extends JpaRepository<Docente, Long> {
}
