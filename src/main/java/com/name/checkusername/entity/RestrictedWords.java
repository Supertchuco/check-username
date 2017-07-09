package com.name.checkusername.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by rafae on 08/07/2017.
 */
@Data
@Entity
public class RestrictedWords {

    public RestrictedWords(){
    }

    public RestrictedWords(String word){
        this.word = word;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String word;
}
