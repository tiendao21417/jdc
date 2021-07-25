package com.jdc.onlineshopping;

import com.jdc.onlineshopping.aop.logging.LoggerProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineShoppingApplicationTests {

	@Test
	void contextLoads() {
		LoggerProvider.APP.info("DONE");
	}

}
