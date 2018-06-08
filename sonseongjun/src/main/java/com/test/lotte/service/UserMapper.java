package com.test.lotte.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.lotte.model.UserModel;

@Repository
/**
 * 
 * 
 * <pre>
 * com.test.lotte.service
 * UserMapper.java
 * </pre>
 * ----------------------
 * @Auth  : 손성준
 * @Since : 2018. 6. 7.
 * @Desc  : 매퍼인터페이스
 *
 */
public interface UserMapper {
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : search
		 * @Desc   : 유저정보 조회
		 * ----------------------------------- 
		 * @return : List<UserModel>
		 *
	 */
	public List<UserModel> search();
	
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : 유저정보 삽입
		 * @Desc   : 
		 * ----------------------------------- 
		 * @param userModel : void
		 *
	 */
	public void insert(UserModel userModel);
	
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : fileDown
		 * @Desc   : 파일다운로드
		 * ----------------------------------- 
		 * @return : List<UserModel>
		 *
	 */
	public List<UserModel> fileDown();
}
