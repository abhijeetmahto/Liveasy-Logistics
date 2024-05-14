package com.example.load.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.load.dto.Loads;
import com.example.load.dto.ResponseStructure;
import com.example.load.service.LoadService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class LoadController {
	
	@Autowired
	private LoadService loadService;

	//Build Load Rest API
	@PostMapping("/loads")
	public ResponseEntity<ResponseStructure<Loads>> saveLoad(@RequestBody Loads load){
		
		return loadService.saveLoad(load);
	}
	
	//Build get Loads Rest API using loadId
	@GetMapping("/loads/{loadId}")
	public ResponseEntity<ResponseStructure<Loads>> getLoadByLoadId(@PathVariable int loadId){
		return loadService.findByLoadId(loadId); 
	}
	
	//Build PUT REST API
//	@PutMapping("/loads")
//	public ResponseEntity<ResponseStructure<Loads>> updateLoad(@RequestBody Loads load){
//		return loadService.updateLoad(load);
//	}
	
	@PutMapping("/loads/{loadId}")
	public ResponseEntity<ResponseStructure<Loads>> updateLoad(@PathVariable int loadId, @RequestBody Loads newLoad){
		return loadService.updateLoad(loadId, newLoad);
	}
	
	//Build DELETE API
	@DeleteMapping("/loads/{loadId}")
	public ResponseEntity<ResponseStructure<String>> deleteLoad(@PathVariable int loadId) {
		return loadService.deleteLoad(loadId);
	}
	
	//Build GET Loads REST API using ShipperID
	@GetMapping("/loads/shipid")
	public ResponseEntity<ResponseStructure<Loads>> getLoadByShipperId(@RequestParam int shipId){
		return loadService.findByShipId(shipId); 
	}
}
