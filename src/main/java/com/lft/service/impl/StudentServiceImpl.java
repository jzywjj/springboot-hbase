package com.lft.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lft.dto.Student;
import com.lft.dto.Student.BaseInfo;
import com.lft.dto.Student.OtherInfo;
import com.lft.hbase.api.HbaseTemplate;
import com.lft.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService {
	
	
	private static final byte[] COL_BASE = "baseInfo".getBytes();
   // private static final byte[] ID = "id".getBytes();
    private static final byte[] NAME = "name".getBytes();
    private static final byte[] AGE = "age".getBytes();
    private static final byte[] WEIGHT = "weight".getBytes();
    //other
    private static final byte[] COL_OTHER = "otherInfo".getBytes();
    private static final byte[] EMAIL = "email".getBytes();
    private static final byte[] ADDRESS = "address".getBytes();
    
	@Autowired
    private HbaseTemplate hbaseTemplate;
	
	@Override
	public Student findStudentByRowKey(String tableName, String rowKey) {
		
		Student student = hbaseTemplate.get(tableName, rowKey, (result,  rowNum)->{
			//Integer id = Bytes.toInt(result.getValue(COL_BASE, ID));
			String name = Bytes.toString(result.getValue(COL_BASE, NAME));
			Integer age = Bytes.toInt(result.getValue(COL_BASE, AGE));
			Double weight = Bytes.toDouble(result.getValue(COL_BASE, WEIGHT));
			BaseInfo baseInfo = new Student.BaseInfo(/* id, */name, age, weight);
			
			//other
			String email = Bytes.toString(result.getValue(COL_OTHER, EMAIL));
			String address = Bytes.toString(result.getValue(COL_OTHER, ADDRESS));
			OtherInfo otherInfo = new Student.OtherInfo(email, address);
			
			Student sd=new Student(baseInfo,otherInfo);
			return sd;
		});
		return student;
	}

	@Override
	public Boolean saveOrUpbdateStudent(String tableName ,Student student) {
		try {
			Put put=new Put(Bytes.toBytes(UUID.randomUUID().toString().replace("-", "")));
			put.addColumn(COL_BASE,NAME, Bytes.toBytes(student.getBaseInfo().getName()))
				.addColumn(COL_BASE, AGE, Bytes.toBytes(student.getBaseInfo().getAge()))
				.addColumn(COL_BASE, WEIGHT, Bytes.toBytes(student.getBaseInfo().getWeight()))
				.addColumn(COL_OTHER, EMAIL,  Bytes.toBytes(student.getOtherInfo().getEmail()))
				.addColumn(COL_OTHER, ADDRESS,  Bytes.toBytes(student.getOtherInfo().getAddress()));
			hbaseTemplate.saveOrUpdate(tableName, put);
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean deleteStudentByRowKey(String tableName, String rowKey) {
		try {
			Mutation mutation=new Delete(Bytes.toBytes(rowKey));
			hbaseTemplate.saveOrUpdate(tableName, mutation);
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@Override
	public List<Student> findStudentByRowKeys(String tableName, List<String> rowKeys) {
		List<Student> stds=new ArrayList<>();
		if(!rowKeys.isEmpty()&&rowKeys.size()>0) {
			rowKeys.forEach(x->{
				stds.add(findStudentByRowKey(tableName,x));
			});
		}
		
		return stds;
	}

}
