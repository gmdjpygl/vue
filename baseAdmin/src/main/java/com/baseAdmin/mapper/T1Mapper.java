package com.baseAdmin.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
public interface T1Mapper {
	List<Map<String, Object>> selectData(Map<String, Object> map);

}
