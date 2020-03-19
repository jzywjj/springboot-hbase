package com.lft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lft.dto.Student;
import com.lft.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	public static final String T_NAME="student";
	
	@RequestMapping("/hi")
	public Student findStudentByRowKey() {
		String rowKey="3d6a708982f444e08352c74c1f6d94bd";
		Student student = studentService.findStudentByRowKey(T_NAME, rowKey);
		return student;
	}
	
	@RequestMapping("/mok")
	public Boolean saveOrUpbdateStudent() {
		
		Student student=new Student(new Student.BaseInfo("人民",29, 75.3d), 
				new Student.OtherInfo("chinan@aliyun.com", "云南省玉溪市昭阳区188号"));
		Boolean saveOrUpbdateStudent = studentService.saveOrUpbdateStudent(T_NAME, student);
		return saveOrUpbdateStudent;
	}
}
