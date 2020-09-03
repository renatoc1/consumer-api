package com.api.consumer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.consumer.model.CampanhaSocioTorcedor;

@Repository
public interface CampanhaSocioTorcedorRepository extends JpaRepository<CampanhaSocioTorcedor, Long>{

	List<CampanhaSocioTorcedor> findByEmail(String email);

}
