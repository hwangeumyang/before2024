package com.example.demo.controller;

/*
 * p.133 다음으로 넘어가기 전 crud만들기
 * 얘들은 db에서 커밋을 언제할까?
 * @Entity 어노테이션이 달려있다면 혹은 @GeneratedValue가 달려있다면 실제 db와 실제 연동이 되어 시작 시 id값을 서로 주고 받는 가?
 */

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import javax.persistence.GeneratedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.dto.MySampleDTO;
import com.example.demo.model.MyCRUDSampleEntity;
import com.example.demo.service.MyCRUDSampleServ;

@RestController
@RequestMapping("sample")
public class MyCRUDSampleController {
	@Autowired
	MyCRUDSampleServ serv;
	
	
//	@PostMapping("/cre")
	@RequestMapping("/cre")
	public String create(@RequestParam(required = true) String txt, @RequestParam(required = false) String test)  {
		MyCRUDSampleEntity entity=MyCRUDSampleEntity.builder().txt(txt).build();
		System.out.println(entity.getId());
		serv.insert(entity);
		
		return String.format("id: %d, txt : %s, test : %s", entity.getId(), txt, test);		
	}
	
//	@GetMapping("/ret")
	@RequestMapping("/ret")
	public MySampleDTO Retrieve(@RequestParam(required = true) int id) {
		MyCRUDSampleEntity entity;
//		try {
			entity = serv.retrieve(id);
//		} catch(javax.persistence.EntityNotFoundException ex) {
//			return new MySampleDTO();
//		}
		
		System.out.println(entity);
		if(entity == null) return null;
		
		MySampleDTO dto = new MySampleDTO(entity);

		return dto;
		
	}
	@RequestMapping("/ret/{id}")
	public MySampleDTO Retrieve2(@PathVariable(required = false) int id) {
		MyCRUDSampleEntity entity;
		try {
			entity= serv.retrieve(id);
			
//			if(entity == null) return new MySampleDTO(new MyCRUDSampleEntity());
		} catch(Exception e) {
			System.out.println("heyyy");
//			return null;
			return new MySampleDTO(new MyCRUDSampleEntity());
		}

		System.out.println(entity);
		
		MySampleDTO dto = new MySampleDTO(entity);
		
		return dto;
	}
	@RequestMapping("/retall")
	public List<MyCRUDSampleEntity> retAll(){
		return serv.retAll();
	}

//	@GetMapping("/upd")
	@RequestMapping("/upd")
	public String update(@RequestParam(required = true) int id, @RequestParam(required = true) String txt){
		StringBuffer outputbuf = new StringBuffer();
		MyCRUDSampleEntity entity;
		
		entity = serv.retrieve(id);
		if(entity == null) entity = null;
		
		outputbuf.append(entity);
		outputbuf.append("-->");
		
		serv.update(id, txt);
		entity = serv.retrieve(id);
		outputbuf.append(entity);
		
		return outputbuf.toString(); 		
	}
	
//	@GetMapping("/del")
	@RequestMapping("/del")
	public String delete(@RequestParam(required = true) int id) {
		return serv.delete(id);		
	}
	

}
