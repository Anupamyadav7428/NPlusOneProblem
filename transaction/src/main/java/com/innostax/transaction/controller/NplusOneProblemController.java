package com.innostax.transaction.controller;

import com.innostax.transaction.entity.User;
import com.innostax.transaction.service.NPlusOneProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nplusoneproblem")
public class NplusOneProblemController {

    private final NPlusOneProblem nPlusOneProblem;

    public NplusOneProblemController(NPlusOneProblem nPlusOneProblem){
        this.nPlusOneProblem=nPlusOneProblem;
    }

    @GetMapping
    public List<User> test(){
        return nPlusOneProblem.run();
    }
}

