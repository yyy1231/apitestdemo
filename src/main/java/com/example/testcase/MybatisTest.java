package com.example.testcase;

import com.example.dao.StudentMapper;
import com.example.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

        public static void main(String[] args) throws IOException {
            System.out.println("use mybatis connect to database ... ");

            // 根据 mybatis-config.xml 配置的信息得到 sqlSessionFactory
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 然后根据 sqlSessionFactory 得到 session
            SqlSession session = sqlSessionFactory.openSession();

            StudentMapper mapper = session.getMapper(StudentMapper.class);

            Student stu = mapper.getStuById(1);
            System.out.println(stu);

        }
    }


