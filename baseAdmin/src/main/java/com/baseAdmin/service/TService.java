package com.baseAdmin.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baseAdmin.mapper.T1Mapper;
import com.baseAdmin.mapper.T2Mapper;


@Service
public class TService {
	@Autowired
	private T1Mapper t1Mapper;
	
	@Autowired
	private T2Mapper t2Mapper;
	public List<Map<String,Object>> getT1(Map<String,Object> params) {
		List<Map<String, Object>> list =t1Mapper.selectData(null);
		return list;
	}	
	public List<Map<String,Object>> getT2(Map<String,Object> params) {
		List<Map<String, Object>> list =t2Mapper.selectData(null);
		return list;
	}	
	
}
