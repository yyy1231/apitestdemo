package com.example.dao;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    int insertStudent(Student student);

    List<Student> selectStu(Integer sno, String sname, String ssex);

    Student getStuById(Integer sno);
}
