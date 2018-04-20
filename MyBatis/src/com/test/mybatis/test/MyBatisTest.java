package com.test.mybatis.test;


import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.test.mybatis.bean.Use;
import com.test.mybatis.test.dao.UseMapper;

public class MyBatisTest {
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource = "mybatis-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		
		return new SqlSessionFactoryBuilder().build(reader);
	}
	

	@Test
	public void test() throws IOException {
		String resource = "mybatis-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		
		SqlSession openSession = sqlSessionFactory.openSession();	
		try {
			Use selectOne = (Use) openSession.selectOne("com.test.mybatis.useMapper.selectuse",1);
		    System.out.println(selectOne);
		} finally {
			openSession.close();
		}
		
		
	}
	
	@Test
	public void test01() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			UseMapper mapper = openSession.getMapper(UseMapper.class);
			Use useId = mapper.getUseById(1);
			System.out.println(useId);
		} finally {
			openSession.close();
		}
		
	}
	
	

}
