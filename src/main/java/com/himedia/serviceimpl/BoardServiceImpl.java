package com.himedia.serviceimpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.himedia.mappers.BoardHireMapper;
import com.himedia.mappers.BoardMapper;
import com.himedia.mappers.BoardTradeMapper;
import com.himedia.mappers.BoardWalkMapper;
import com.himedia.repository.vo.BoardHireRequestVo;
import com.himedia.repository.vo.BoardHireVo;
import com.himedia.repository.vo.BoardPhotoVo;
import com.himedia.repository.vo.BoardTradeRequestVo;
import com.himedia.repository.vo.BoardTradeVo;
import com.himedia.repository.vo.BoardVo;
import com.himedia.repository.vo.BoardWalkRequestVo;
import com.himedia.repository.vo.BoardWalkVo;
import com.himedia.services.BoardService;
import com.himedia.services.PhotoService;


@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private BoardWalkMapper boardWalkMapper;
	@Autowired
	private BoardTradeMapper boardTradeMapper;
	@Autowired
	private BoardHireMapper boardHireMapper;
	@Autowired
	private PhotoService photoService;

	@Override
	public List<BoardVo> selectAllBoard() {
		List<BoardVo> boardList = boardMapper.selectAllBoard();
		return boardList;
	}

	@Override
	public BoardVo selectOneBoard(Integer id) {
		BoardVo board = boardMapper.selectBoard(id);
		return board;
	}

	@Override
	@Transactional
	public int insertBoardWalk(MultipartFile file ,BoardWalkRequestVo boardWalkRequestVo) throws IOException {
		BoardVo board = BoardVo.builder()
				.userId(boardWalkRequestVo.getUserId())
				.title(boardWalkRequestVo.getTitle())
				.content(boardWalkRequestVo.getContent())
				.localSi(boardWalkRequestVo.getLocalSi())
				.localGu(boardWalkRequestVo.getLocalGu())
				.boardType(boardWalkRequestVo.getBoardType())
				.build();
				
		System.out.println(board);
		int result = boardMapper.insertBoard(board);
		System.out.println(board);
		BoardWalkVo boardWalkVo = BoardWalkVo.builder()
				.boardId(board.getBoardId())
				.walkCategory(boardWalkRequestVo.getWalkCategory())
				.build();
		System.out.println(boardWalkVo);
		int result2 = boardWalkMapper.insertBoardWalk(boardWalkVo);
		
		if (!file.isEmpty()) {
			photoService.uploadBoardPicture(file, board.getBoardId());
		}
		
		return result2;
	}
	
	@Override
	@Transactional
	public int insertBoardTrade(MultipartFile file ,BoardTradeRequestVo boardTradeRequestVo) throws IOException {
		BoardVo board = BoardVo.builder()
				.userId(boardTradeRequestVo.getUserId())
				.title(boardTradeRequestVo.getTitle())
				.content(boardTradeRequestVo.getContent())
				.localSi(boardTradeRequestVo.getLocalSi())
				.localGu(boardTradeRequestVo.getLocalGu())
				.boardType(boardTradeRequestVo.getBoardType())
				.build();
				
		System.out.println(board);
		int result = boardMapper.insertBoard(board);
		System.out.println(board);
		BoardTradeVo boardTradeVo = BoardTradeVo.builder()
				.boardId(board.getBoardId())
				.tradePrice(boardTradeRequestVo.getTradePrice())
				.tradeCategory(boardTradeRequestVo.getTradeCategory())
				.build();
		int result2 = boardTradeMapper.insertBoardTrade(boardTradeVo);
		
		if (!file.isEmpty()) {
			photoService.uploadBoardPicture(file, board.getBoardId());
		}
		return result2;
	}
	
	@Override
	@Transactional
	public int insertBoardHire(MultipartFile file ,BoardHireRequestVo boardHireRequestVo) throws IOException {
		BoardVo board = BoardVo.builder()
				.userId(boardHireRequestVo.getUserId())
				.title(boardHireRequestVo.getTitle())
				.content(boardHireRequestVo.getContent())
				.localSi(boardHireRequestVo.getLocalSi())
				.localGu(boardHireRequestVo.getLocalGu())
				.boardType(boardHireRequestVo.getBoardType())
				.build();
				
		System.out.println(board);
		int result = boardMapper.insertBoard(board);
		System.out.println(board);
		BoardHireVo boardHireVo = BoardHireVo.builder()
				.boardId(board.getBoardId())
				.hireCondition(boardHireRequestVo.getHireCondition())
				.hireDate(boardHireRequestVo.getHireDate())
				.hirePrice(boardHireRequestVo.getHirePrice())
				.hireCategory(boardHireRequestVo.getHireCategory())
				.build();
		int result2 = boardHireMapper.insertBoardHire(boardHireVo);
		
		if (!file.isEmpty()) {
			photoService.uploadBoardPicture(file, board.getBoardId());
		}
		return result2;
	}

	@Override
	public int updateBoard(BoardVo board) {
		int result = boardMapper.updateBoard(board);
		return result;
	}

	@Override
	public int deleteBoard(Integer id) {
		int result = boardMapper.deleteBoard(id);
		return result;
	}

	@Override
	public int increaseCount(Integer id) {
		int result = boardMapper.increaseCount(id);
		return result;
	}

	@Override
	public List<BoardWalkVo> selectAllBoardWalk() {
		List<BoardWalkVo> boardWalkVos = boardWalkMapper.selectAllBoardWalk();
		return boardWalkVos;
	}

	@Override
	public List<BoardTradeVo> selectAllBoardTrade() {
		List<BoardTradeVo> boardTradeVos = boardTradeMapper.selectAllBoardTrade();
		return boardTradeVos;
	}

	@Override
	public List<BoardHireVo> selectAllBoardHire() {
		List<BoardHireVo> boardHireVos = boardHireMapper.selectAllBoardHire();
		return boardHireVos;
	}

	@Override
	public BoardWalkVo selectBoardWalk(Integer id) {
		BoardWalkVo boardWalkVo = boardWalkMapper.selectBoardWalk(id);
		return boardWalkVo;
	}

	@Override
	public BoardTradeVo selectBoardTrade(Integer id) {
		BoardTradeVo boardTradeVo = boardTradeMapper.selectBoardTrade(id);
		return boardTradeVo;
	}

	@Override
	public BoardHireVo selectBoardHire(Integer id) {
		BoardHireVo boardHireVo = boardHireMapper.selectBoardHire(id);
		return boardHireVo;
	}

	@Override
	public int updateBoardWalk(BoardWalkVo boardWalkVo) {
		int result = boardWalkMapper.updateBoardWalk(boardWalkVo);
		return result;
	}

	@Override
	public int updateBoardTrade(BoardTradeVo boardTradeVo) {
		int result = boardTradeMapper.updateBoardTrade(boardTradeVo);
		return result;
	}

	@Override
	public int updateBoardHire(BoardHireVo boardHireVo) {
		int result = boardHireMapper.updateBoardHire(boardHireVo);
		return result;
	}
	
	@Override
	@Transactional
	public int insertOneBoard(MultipartFile file, BoardVo boardVo) throws IOException {
	    int result = boardMapper.insertBoard(boardVo); // board 등록
	    if (!file.isEmpty()) {
	        photoService.uploadBoardPicture(file, boardVo.getBoardId()); // 사진 등록
	    }
	    return result;
	}

	
	
}
