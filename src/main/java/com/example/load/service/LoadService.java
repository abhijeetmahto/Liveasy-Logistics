package com.example.load.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.load.dao.LoadDao;
import com.example.load.dto.Loads;
import com.example.load.dto.ResponseStructure;

@Service
public class LoadService {
	
	@Autowired
	private LoadDao dao;
	
	public ResponseEntity<ResponseStructure<Loads>> saveLoad(Loads load){
		ResponseStructure<Loads> structure = new ResponseStructure<Loads>();
		structure.setMessage("User Registered Successfully");
		structure.setBody(dao.saveLoad(load));
		structure.setCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Loads>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Loads>> findByLoadId(int loadId){
		ResponseStructure<Loads> structure = new ResponseStructure<Loads>();
		Optional<Loads> load = dao.findLoad(loadId);
		if(load.isPresent()) {
			structure.setBody(load.get());
			structure.setMessage("Load Found");
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Loads>>(structure,HttpStatus.OK);
		}else {
//			structure.setBody(load.get());
			structure.setMessage("Load Not Found");
			structure.setCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Loads>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
//	public ResponseEntity<ResponseStructure<Loads>>  updateLoad(Loads load) {
//		ResponseStructure<Loads> structure = new ResponseStructure<Loads>();
//		structure.setMessage("User Updated Successfully");
//		structure.setBody(dao.updateLoad(load));
//		structure.setCode(HttpStatus.ACCEPTED.value());
//		return new ResponseEntity<ResponseStructure<Loads>>(structure,HttpStatus.ACCEPTED);
//	} 
	
	
	public ResponseEntity<ResponseStructure<Loads>> updateLoad(int loadId, Loads newLoad){
	    return dao.findLoad(loadId)
	        .map(load -> {
	            load.setLoadingPoint(newLoad.getLoadingPoint());
	            load.setUnloadingPoint(newLoad.getUnloadingPoint());
	            load.setProductType(newLoad.getProductType());
	            load.setTruckType(newLoad.getTruckType());
	            load.setNoOfTrucks(newLoad.getNoOfTrucks());
	            load.setWeight(newLoad.getWeight());
	            load.setShipperId(newLoad.getShipperId());
	            load.setDate(newLoad.getDate());
	            Loads updatedLoad = dao.updateLoad(load);
	            ResponseStructure<Loads> structure = new ResponseStructure<Loads>();
	            structure.setBody(updatedLoad);
	            structure.setMessage("Load Updated");
	            structure.setCode(HttpStatus.OK.value());
	            return new ResponseEntity<ResponseStructure<Loads>>(structure, HttpStatus.OK);
	        })
	        .orElseGet(() -> {
	            newLoad.setLoadId(loadId);
	            Loads createdLoad = dao.saveLoad(newLoad);
	            ResponseStructure<Loads> structure = new ResponseStructure<Loads>();
	            structure.setBody(createdLoad);
	            structure.setMessage("Load Created");
	            structure.setCode(HttpStatus.CREATED.value());
	            return new ResponseEntity<ResponseStructure<Loads>>(structure, HttpStatus.CREATED);
	        });
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteLoad(int loadId){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		Optional<Loads> load = dao.findLoad(loadId);
		if(load.isPresent()) {
			dao.deleteLoad(loadId);
			structure.setBody("Load Deleted");
			structure.setMessage("Data Found");
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseStructure<Loads>> findByShipId(int shipId){
		ResponseStructure<Loads> structure = new ResponseStructure<Loads>();
		Optional<Loads> load = dao.findLoadByShipId(shipId);
		if(load.isPresent()) {
			structure.setBody(load.get());
			structure.setMessage("Load Found");
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Loads>>(structure,HttpStatus.OK);
		}else {
//			structure.setBody(load.get());
			structure.setMessage("Load Not Found");
			structure.setCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Loads>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
}
