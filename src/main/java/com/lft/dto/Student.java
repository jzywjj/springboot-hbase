package com.lft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
/**
 * 简单的demo示例
 * @author Mr-King
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class Student {
	
	private BaseInfo baseInfo;
	
	private OtherInfo otherInfo;
	/**
	 * base 列簇
	 * @author Mr-King
	 *
	 */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	@Accessors(chain = true)
	public static class BaseInfo{
//		private String id;
		private String name;
		
		private Integer age;
		
		private Double weight;
	}
	
	/**
	 * 其他信息列簇
	 * @author Mr-King
	 *
	 */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	@Accessors(chain = true)
	public static class OtherInfo{
		
		private String email;
		
		private String address;
		
	}
}
