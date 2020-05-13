package com.example.demo.board.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.example.demo.board.model.BoardParam;
import com.example.demo.board.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Validated
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(
            value = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object getAll() throws Exception{
        return boardService.getBoard();
        // return ResponseEntity.ok(boardService.getBoard());
    }

    @GetMapping(
            value = "/{seq}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object get(@PathVariable("seq") @Min(1) Long seq) throws Exception{
        return boardService.getBoard(seq);
        // return ResponseEntity.ok(boardService.getBoard(seq));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object add(@RequestBody @Valid BoardParam param){
        boardService.add(param);
        return null;
        // return ResponseEntity.ok(null);
    }

    @PutMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object edit(@RequestBody @Valid BoardParam param,
                               @PathVariable("seq") @Min(1) Long seq){
        param.setSeq(seq);
        boardService.edit(param);
        return null;
        // return ResponseEntity.ok(null);
    }

    @DeleteMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object delete(@PathVariable("seq") @Min(1) Long seq){
        boardService.delete(seq);
        return null;
        // return ResponseEntity.ok(null);
    }
}