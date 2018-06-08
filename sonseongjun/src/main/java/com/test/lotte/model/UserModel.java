package com.test.lotte.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
 * 
 * <pre>
 * com.test.lotte.model
 * UserModel.java
 * </pre>
 * ----------------------
 * @Auth  : 손성준
 * @Since : 2018. 6. 7.
 * @Desc  : 유저 정보 Model
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserModel {
	private String rankNum;
	private String id;
	private String stuNm;
	private int score1;
	private int score2;
	private int score3;
	private int score4;
	private int score5;
	private float scoAvg;
	
}