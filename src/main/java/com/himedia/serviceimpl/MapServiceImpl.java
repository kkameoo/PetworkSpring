package com.himedia.serviceimpl;

import org.springframework.stereotype.Service;

import com.himedia.mappers.MapMapper;
import com.himedia.repository.vo.MapVo;
import com.himedia.services.MapService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService{
	
	private final MapMapper mapMapper;

	@Override
	public MapVo selectMap(Integer id) {
		MapVo mapVo = mapMapper.selectMap(id);
		return mapVo;
	}

}
