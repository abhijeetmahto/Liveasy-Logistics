package com.example.load.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.load.dto.Loads;
import com.example.load.repository.LoadRepository;

@Repository
public class LoadDao {

	@Autowired
	private LoadRepository repository;
	
	public Loads saveLoad(Loads load) {
		return repository.save(load);
	}
	
	public Optional<Loads> findLoad(int loadId){
		return repository.findById(loadId);
	}
	
	public Loads updateLoad(Loads load) {
		return repository.save(load);
	}
	
	public void deleteLoad(int loadId) {
		repository.deleteById(loadId);
	}
	
	public Optional<Loads> findLoadByShipId(int shipId){
		return repository.findByShipId(shipId);
	}
}
