package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.ReportVo;

@Mapper
public interface ReportMapper {
//	리포트 인서트
//	<insert id="insertReport" parameterType="ReportVo">
	int insertReport(ReportVo reportVo);
	
//	모든 리포트 셀렉트
//	<select id="selectReports" resultType="ReportVo">
	List<ReportVo> selectReports();
	
//	보드아이디로 모든 리포트 셀렉트
//	<select id="selectReportsByBoardId" parameterType="int" resultType="ReportVo">
	List<ReportVo> selectReportsByBoardId(Integer boardId);
	
}
