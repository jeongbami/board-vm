package com.project.boarddbproject.service;

import com.project.boarddbproject.entity.Board;
import com.project.boarddbproject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;


    //글작성 처리
    public void write(Board board) {
        boardRepository.save(board);
    }


    //리스트 불러오기 처리
    public List<Board> boardList() {
        return boardRepository.findAll();

    }

    //내가 쓴 글보기
    public Board boardView(Integer boardIndex) {
        return boardRepository.findById(boardIndex).get();
    }

    //특정 게시글 삭제
    public void boardDelete(Integer boardIndex) {
        boardRepository.deleteById(boardIndex);
    }
}
