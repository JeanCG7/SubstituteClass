package com.substituicao.demo.dao;

import com.substituicao.demo.entity.Chefia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefiaDAO extends JpaRepository<Chefia, Long> {
}
