package com.dity.argument;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {

		TestModel tm = new TestModel();
		BeanWrapper bw = new BeanWrapperImpl(tm);
		bw.setPropertyValue("good", "on");
		//bw.setPropertyValue("good", "1");
		//bw.setPropertyValue("good", "true");
		//bw.setPropertyValue("good", "yes");
		System.out.println(tm);
	}

}
