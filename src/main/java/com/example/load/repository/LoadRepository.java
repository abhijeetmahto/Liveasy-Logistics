package com.example.load.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.load.dto.Loads;

public interface LoadRepository extends JpaRepository<Loads, Integer>{

	@Query("select l from Loads l where l.shipperId=?1")
	public Optional<Loads> findByShipId(int shipId);
}
