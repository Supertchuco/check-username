package com.name.checkusername.controller;

import com.name.checkusername.service.UsernameService;
import com.name.checkusername.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rafae on 09/07/2017.
 */
@RestController
public class UserNameController {

    @Autowired
    UsernameService usernameService;

    @RequestMapping(value = "/userCheck/{userName}", method = RequestMethod.POST)
    public ResponseEntity<Result> checkUsername(@PathVariable("userName") String userName) {
        Result result;
        try {
            result = usernameService.checkUsername(userName);
        }catch (Exception e){
            result = new Result();
            result.setMessage(e.getMessage());
            result.setProcessResult(false);
        }

        return new ResponseEntity<Result>(result, HttpStatus.OK);
    }
}
