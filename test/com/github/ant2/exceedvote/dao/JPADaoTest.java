package com.github.ant2.exceedvote.dao;

import org.junit.Test;

import com.github.ant2.exceedvote.dao.jpa.JPADaoFactory;

public class JPADaoTest {

	@Test
	public void test() {
		new DaoTest().testDaoFactory(new JPADaoFactory());
	}

}
