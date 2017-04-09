package com.iGame;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

public class AuthorPropertiesTest {

	@Autowired
	private AuthorProperties authorProperties;
	@Test
	public void getAuthorProperties() throws Exception {
		Assert.assertEquals(authorProperties.getAuthor(), "Charles");
		Assert.assertEquals(authorProperties.getProject(), "iGame");
	}
}
