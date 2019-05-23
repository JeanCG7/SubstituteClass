package com.substituicao.demo.dao;

import com.substituicao.demo.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaDAO extends JpaRepository<Aula, Long> {
}
