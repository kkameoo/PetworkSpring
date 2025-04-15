package com.himedia.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PetVo {
	  private Integer petId;
	  private Integer userId;
	  private String petName;
	  private String petType;
	  private Integer petCategory;
	  private String petIntroduce;

	
}
