package com.test.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.test.po.MiguMobileSeg;

@Repository
public interface MiguMobileSegDao {
	
	MiguMobileSeg select(@Param("seg") String seg);
	
	int insert(MiguMobileSeg miguMobileSeg);
	
	int update(MiguMobileSeg miguMobileSeg);

}
