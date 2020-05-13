package com.example.demo.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardResult {
    Long seq;
    String content;
    String username;
    String title;
}