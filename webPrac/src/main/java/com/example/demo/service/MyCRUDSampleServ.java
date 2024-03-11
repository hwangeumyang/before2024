package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.MyCRUDSampleRepo;
import com.example.demo.model.MyCRUDSampleEntity;

//비즈니스 로직
@Service
public class MyCRUDSampleServ {
	@Autowired
	MyCRUDSampleRepo repo;
	
	//C
	public MyCRUDSampleEntity insert(MyCRUDSampleEntity entity) {
		repo.save(entity);
		
		return retrieve(entity.getId());
	}
	//R
	public MyCRUDSampleEntity retrieve(int id) {
		MyCRUDSampleEntity entity = null;
		
		//서비스 예외처리는 통하지 않았다...
//		try {
			entity = repo.getById(id);
//			System.out.println("service::" + entity);
//		} catch(javax.persistence.EntityNotFoundException ex) {
//			System.out.println("service::" + entity);
//			
//			return null;
//		}
		
		return entity;
	}
	public List<MyCRUDSampleEntity> retAll(){
//		List list = new java.util.ArrayList<>();
		return repo.findAll();
	}
	//U
	public MyCRUDSampleEntity update(int id, String txt) {
		MyCRUDSampleEntity entity = retrieve(id);
		entity.setTxt(txt);
		repo.save(entity);
		
		return entity;
	}
	//D
	public String delete(int id) {
		repo.deleteById(id);

		return String.format("req for deleting id %d is requested", id); 
	}
	
	

}
