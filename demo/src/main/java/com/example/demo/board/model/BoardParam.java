package com.example.demo.board.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class BoardParam {
    @Min(0)
    Long seq;
    @NotEmpty
    String content;
    @NotEmpty
    String username;
    @NotEmpty
    String title;
}