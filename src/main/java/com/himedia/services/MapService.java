package com.himedia.services;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.MapVo;
@Service
public interface MapService {
	MapVo selectMap(Integer id);
	int updateMapByBoardId(MapVo mapVo);
}
