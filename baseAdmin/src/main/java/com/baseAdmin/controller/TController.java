package com.baseAdmin.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baseAdmin.service.TService;
import com.baseAdmin.util.GsonUtil;


@RestController
@RequestMapping("/t")
@CrossOrigin(origins = "*")
public class TController {
	@Autowired
	TService tService;

	@GetMapping(value = { "/getT1" })
	public Object getT1() throws Exception {
		return tService.getT1(null);
	}
	@GetMapping(value = { "/getT2" })
	public Object getT2() throws Exception {
		return tService.getT2(null);
	}
}
