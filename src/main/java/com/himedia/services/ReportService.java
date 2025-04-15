package com.himedia.services;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.ReportVo;

@Service
public interface ReportService {
	
	// 리포트 입력하는 기능
	int insertReport(ReportVo reportVo) throws SQLException;

}
