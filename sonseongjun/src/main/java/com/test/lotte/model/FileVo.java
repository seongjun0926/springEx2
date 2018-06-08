package com.test.lotte.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 
 * <pre>
 * com.test.lotte.model
 * FileVo.java
 * </pre>
 * ----------------------
 * @Auth  : 손성준
 * @Since : 2018. 6. 7.
 * @Desc  : 파일 VO
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class FileVo {
	private String fileName;
	private String fileOriName;
	private String fileUrl;
}
