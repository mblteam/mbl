package com.mbl.common.util;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationMVC.xml","classpath:applicationContext.xml" })
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class AbstractTestBase extends AbstractTransactionalJUnit4SpringContextTests{

	@BeforeClass
	public static void prepareForTest() {
		
	}
}
