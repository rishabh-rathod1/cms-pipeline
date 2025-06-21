package com.rishabh.CMS;

import org.springframework.boot.SpringApplication;

public class TestCmsApplication {

	public static void main(String[] args) {
		SpringApplication.from(CmsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
