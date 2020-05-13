package com.example.demo.board.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "BoardEntity")
public class BoardEntity {
    @Id
    @GeneratedValue
    Long seq;
    String content;
    String username;
    String title;
}