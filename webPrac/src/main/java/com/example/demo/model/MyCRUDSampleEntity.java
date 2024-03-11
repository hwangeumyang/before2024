package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Sample")
public class MyCRUDSampleEntity {
	@Id
	@GeneratedValue
	private int id;
	private String txt;
	
	public String toString() {
		return String.format("{ id : %s, txt : %s }", id, txt);
	}
}
