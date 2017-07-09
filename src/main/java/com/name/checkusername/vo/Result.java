package com.name.checkusername.vo;

import lombok.Data;

import java.util.TreeSet;

/**
 * Created by rafae on 09/07/2017.
 */
@Data
public class Result {

    private boolean processResult;
    private String message;
    private TreeSet<String> suggestedUserNames;
}
