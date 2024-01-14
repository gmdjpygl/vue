package com.baseAdmin.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
@DS("db2")
public interface T2Mapper {
	List<Map<String, Object>> selectData(Map<String, Object> map);

}
