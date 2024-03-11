package com.example.demo.dto;

import com.example.demo.model.MyCRUDSampleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySampleDTO {
	private int id;
	private String txt;
	public MySampleDTO(MyCRUDSampleEntity entity) {
		id = entity.getId();
		txt = entity.getTxt();
	}
}
