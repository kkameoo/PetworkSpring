package com.himedia.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.repository.vo.MapVo;
import com.himedia.services.MapService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/map")
public class MapController {
	private final MapService mapService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> selectMapById(@PathVariable Integer id) {
		MapVo mapVo = mapService.selectMap(id);
		if (mapVo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터가 존재하지 않습니다.");
		}
		return ResponseEntity.ok(mapVo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> UpdateMapByBoardId(@RequestBody MapVo mapVo) {
		int result = mapService.updateMapByBoardId(mapVo);
		if (result == 0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트에 실패했습니다.");
		}
		return ResponseEntity.ok(mapVo);
	}
}
