package com.example.demo.board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.board.model.BoardEntity;
import com.example.demo.board.model.BoardParam;
import com.example.demo.board.model.BoardResult;
import com.example.demo.board.repository.BoardRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Transactional
    public List<BoardResult> getBoard(){
        List<BoardEntity> entityList = boardRepository.findAll();
        List<BoardResult> results = entityList.stream().map(boardEntity -> {
            BoardResult boardResult = new BoardResult();
            boardResult.setContent(boardEntity.getContent());
            boardResult.setUsername(boardEntity.getUsername());
            boardResult.setTitle(boardEntity.getTitle());
            boardResult.setSeq(boardEntity.getSeq());
            return boardResult;
        }).collect(Collectors.toList());

        return results;
    }

    @Transactional
    public Object getBoard(Long seq){
        ModelMapper modelmapper = new ModelMapper();
        Optional<BoardEntity> results = boardRepository.findById(seq);
        return results.isPresent() ? results.map(boardEntity -> {
            BoardResult boardResult = new BoardResult();
            modelmapper.map(boardEntity, boardResult);
            return boardResult;
        }).get() : null;
        // return boardRepository.findById(seq).map(boardEntity -> {
        //     BoardResult boardResult = new BoardResult();
        //     boardResult.setContent(boardEntity.getContent());
        //     boardResult.setUsername(boardEntity.getUsername());
        //     boardResult.setTitle(boardEntity.getTitle());
        //     boardResult.setSeq(boardEntity.getSeq());
        //     return boardResult;
        // }).get();
    }

    @Transactional
    public void edit(BoardParam param) {
        Optional<BoardEntity> getEntity = boardRepository.findById(param.getSeq());
        getEntity.ifPresent(entity -> {
            entity.setTitle(param.getTitle());
            entity.setContent(param.getContent());
            entity.setUsername(param.getUsername());
            boardRepository.save(entity);
        });
    }

    @Transactional
    public void add(BoardParam param) {
        BoardEntity entity = new BoardEntity();
        entity.setUsername(param.getUsername());
        entity.setContent(param.getContent());
        entity.setTitle(param.getTitle());
        boardRepository.save(entity);
    }
	
    @Transactional
    public void delete(Long seq) {
        boardRepository.deleteById(seq);
    }
}