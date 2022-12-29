package com.project.boarddbproject.controller;

import com.project.boarddbproject.entity.Board;
import com.project.boarddbproject.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;
    @GetMapping("/write")
    public String boardWriteForm(){
        return "boardWrite";
    }
    @PostMapping("/boardWriteProcess")
    public String boardWriteProcess(Board board) {
        boardService.write(board);
        return "";
    }
}
