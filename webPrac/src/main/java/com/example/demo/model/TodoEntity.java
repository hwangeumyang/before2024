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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity //@Entity("TodoEntity"): 엔티티에 이름을 부여할 수 있다.
@Table(name = "Todo") //테이블 이름 지정, 테이블 어노테이션이 없으면 엔티티이름으로, 엔티티이름이 없다면 클래스 이름을 테이블 이름으로 보고 매핑한다.
public class TodoEntity {
	@Id
	@GeneratedValue(generator="system-uuid") // 자동으로생성하겠다는 의미
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id;
	private String userId;
	private String title;
	private boolean done;
}
