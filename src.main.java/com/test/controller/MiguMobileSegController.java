package com.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.service.MiguMobileService;

@RestController
public class MiguMobileSegController {

	private final static Logger logger = LoggerFactory
			.getLogger(MiguMobileSegController.class);

	@Autowired
	private MiguMobileService miguMobileService;

	@RequestMapping("/miguMobileSeg/update")
	public String update() {
		miguMobileService.readTxt();
		logger.info("aaaa");
		return "aa";
	}

}
