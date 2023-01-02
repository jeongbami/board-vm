package com.project.boarddbproject.controller;

import com.project.boarddbproject.entity.Board;
import com.project.boarddbproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/write")
    public String boardWriteForm() {
        return "boardWrite";
    }
    //글쓰기
    @PostMapping("/boardWriteProcess")
    public String boardWriteProcess(Board board) {

        boardService.write(board);
        return "redirect:/board/list";
    }

    //게시판 리스트
    @GetMapping("/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());
        return "boardList";
    }
    //게시판 글 상세내용
    @GetMapping("/view")
    public String boardView(Model model, Integer boardIndex){
        model.addAttribute("board", boardService.boardView(boardIndex));

        return "boardView";
    }

    //특정 게시글 삭제
    @GetMapping("/delete")
    public String boardDelete(Integer boardIndex){

        boardService.boardDelete(boardIndex);
        return "redirect:/board/list";
    }

    //게시판 수정 ( 수정 form 이동)
    @GetMapping("/modify/{boardIndex}")
    public String boardModify(@PathVariable("boardIndex") Integer boardIndex,
                              Model model) {

        model.addAttribute("board", boardService.boardView(boardIndex));

        return "boardModify";
    }

    //게시판 수정 (수정과정)
    @PostMapping("/update/{boardIndex}")
    public String boardUpdate(@PathVariable("boardIndex") Integer boardIndex, Board board) {

        //기존의 글을 잠시 담아두는 공간
        Board boardTemp = boardService.boardView(boardIndex);
        System.out.println("boardIndex = " + boardIndex + ", board = " + board);
        //새로운 내용을 기존의 내용에 덮어씌우기
        boardTemp.setBoardTitle(board.getBoardTitle());
        boardTemp.setBoardContent(board.getBoardContent());
        System.out.println("boardIndex = " + boardIndex + ", board = " + board);
        boardService.write(boardTemp);

        return "redirect:/board/list";

    }
}
