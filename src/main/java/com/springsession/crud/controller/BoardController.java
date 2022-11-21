package com.springsession.crud.controller;

import com.springsession.crud.entty.Crud;
import com.springsession.crud.service.BoardService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/write") //localhost:8080/board/write
    public  String main(){
        return  "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Crud crud){
        boardService.write(crud);
        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list",boardService.boardList());
        return  "boardlist";
    }

    @GetMapping("/board/view") //http://localhost:8080/board/view?id=1
    public String boardView(Model model,Integer id){
        model.addAttribute("crud",boardService.boardView(id));
        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }
    @GetMapping("/board/modifiy/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("crud",boardService.boardView(id));
        return "boardmodifiy";
    }

    @PostMapping("/board/update/{id}")
    public  String boardUpdate(@PathVariable("id") Integer id, Crud crud){
        Crud boardTemp=boardService.boardView(id);
        boardTemp.setTitle(crud.getTitle());
        boardTemp.setContent(crud.getContent());

        boardService.write(boardTemp);

        return "redirect:/board/list";
    }


}