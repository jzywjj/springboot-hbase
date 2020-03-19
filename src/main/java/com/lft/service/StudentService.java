package com.lft.service;

import java.util.List;

import com.lft.dto.Student;

public interface StudentService {
	/**
	 * 根据表名和rowkey 查询student
	 * @param tableName
	 * @param rowKey
	 * @return
	 */
	public Student findStudentByRowKey(String tableName,String rowKey);
	
	/**
	 * 根据表名和student 对象新增或者是修改
	 * @param tableName
	 * @param student
	 * @return
	 */
	public Boolean saveOrUpbdateStudent(String tableName,Student student);
	
	/**
	 * 根据表名和rowKey 删除对应的记录
	 * @param tableName
	 * @param rowKey
	 * @return
	 */
	public Boolean deleteStudentByRowKey(String tableName,String rowKey);
	/**
	 * 根据表名和rowKeys查询集合对象
	 * @param tableName
	 * @param rowKeys
	 * @return
	 */
	
	public List<Student> findStudentByRowKeys(String tableName,List<String> rowKeys);
}
