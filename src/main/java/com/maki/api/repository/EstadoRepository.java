package com.maki.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maki.api.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
