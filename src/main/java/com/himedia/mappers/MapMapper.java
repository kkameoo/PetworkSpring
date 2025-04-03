package com.himedia.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.MapVo;

@Mapper
public interface MapMapper {
	MapVo selectMap(Integer id);
	int insertMap(MapVo mapVo);
	int updateMap(MapVo mapVo);
}
