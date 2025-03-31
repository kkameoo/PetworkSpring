package com.himedia.serviceimpl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.himedia.mappers.BoardMapper;
import com.himedia.mappers.ReportMapper;
import com.himedia.repository.vo.ReportVo;
import com.himedia.services.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
	
	private final ReportMapper reportMapper;
	private final BoardMapper boardMapper;

	@Override
	public int insertReport(ReportVo reportVo) throws SQLException {
		int result1 = boardMapper.increaseReportCount(reportVo.getBoardId());
		if (result1 == 0) {
			throw new SQLException("board의 report_count 업데이트가 실패했습니다.");
		}
	 	int result2 = reportMapper.insertReport(reportVo);
		return result2;
	}
	
//	@Override
//	public int selectReportCount(Integer boardId) {
//		List<ReportVo> reportVos = reportMapper.selectReportsByBoardId(boardId);
//		int size = reportVos.size();
//		return size;
//	}

}
